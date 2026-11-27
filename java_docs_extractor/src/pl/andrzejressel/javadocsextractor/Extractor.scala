package pl.andrzejressel.javadocsextractor

import org.apache.commons.text.StringEscapeUtils
import org.commonmark.node.AbstractVisitor
import org.commonmark.parser.Parser

import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import scala.collection.mutable.ListBuffer

object Extractor {

  def main(args: Array[String]): Unit = {
    val files = args.map(new File(_))

    val allSnippets = files.flatMap(extract)

    allSnippets.foreach { snippet =>
      val javaFile = generateJavaFileFromJavaSnippet(snippet)
      val outputPath =
        Paths.get(".", javaFile.packages :+ javaFile.fileName*).toFile
      outputPath.getParentFile.mkdirs()
      Files.writeString(outputPath.toPath, javaFile.content)
    }
  }

  def extract(file: File): Seq[JavaSnippet] = {
    val parser = Parser.builder().build()
    val document = parser.parseReader(new java.io.FileReader(file))

    val visitor = new JavaSnippetVisitor
    document.accept(visitor)
    val stringSnippets = visitor.getSnippets

    stringSnippets.map(convertStringToJavaSnippet)
  }

  def generateJavaFileFromJavaSnippet(javaSnippet: JavaSnippet): JavaFile = {
    // I have to use lines so the mismatch between Windows and Unix end lines won't affect the hash code.
    val fileHashCode = javaSnippet.code.lines().toList.hashCode().abs
    val className = s"GeneratedJavaSnippet$fileHashCode"

    val escapedExpectedOutput =
      StringEscapeUtils.escapeJava(javaSnippet.expectedOutput)

    val lines = ListBuffer.empty[String]
    lines += s"package pl.andrzejressel.javadocsextractor.generated;"
    lines += ""
    javaSnippet.imports.foreach { imp =>
      lines += imp
    }
    lines += "import org.junit.jupiter.api.Test;"
    lines += "import static org.junit.jupiter.api.Assertions.assertEquals;"
    lines += ""
    lines += s"public class $className {"
    lines += "    @Test"
    lines += "    public void testSnippet() throws Exception {"
    lines += "        var result = new java.util.concurrent.Callable<>() {"
    lines += "            @Override"
    lines += "            public Object call() throws Exception {"
    javaSnippet.code.linesIterator.foreach { line =>
      lines += s"                $line"
    }
    lines += "            }"
    lines += "        };"
    lines += s"        assertEquals(\"$escapedExpectedOutput\", result.call().toString());"
    lines += "    }"
    lines += "}"

    JavaFile(
      packages = Seq("pl", "andrzejressel", "javadocsextractor", "generated"),
      fileName = s"$className.java",
      content = lines.mkString(System.lineSeparator()).trim
    )
  }

  private def convertStringToJavaSnippet(snippet: String): JavaSnippet = {
    val imports = snippet.linesIterator.filter(_.startsWith("import")).toList
    val snippetWithoutImports = snippet.linesIterator
      .filterNot(_.startsWith("import"))
      .mkString(System.lineSeparator())
      .trim
    val lineWithExpectedResult =
      snippet.lines().filter(line => !line.isBlank).toList.getLast
    val expectedResultRegex = ".*Result:\\s*(.*?)\\s*$".r

    lineWithExpectedResult match {
      case expectedResultRegex(result) =>
        JavaSnippet(imports, snippetWithoutImports, result)
      case _ =>
        throw new IllegalArgumentException(
          s"Expected result not found in last line [$lineWithExpectedResult] of snippet: [$snippet]."
        )
    }
  }

  private class JavaSnippetVisitor extends AbstractVisitor {

    private val snippets = ListBuffer.empty[String]

    override def visit(node: org.commonmark.node.FencedCodeBlock): Unit = {
      if node.getInfo == "java" then {
        val code = node.getLiteral
        snippets += code
      }
      super.visit(node)
    }

    def getSnippets: Seq[String] = snippets.toSeq
  }

}

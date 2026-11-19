package pl.andrzejressel.javadocsextractor

import java.nio.file.Files
import java.nio.file.Paths

class ExtractorSuite extends munit.FunSuite {

  test("extract") {
    val fileUrl = getClass.getResource("/markdown.md")
    val file = new java.io.File(fileUrl.toURI)
    val snippets = Extractor.extract(file)
    assertEquals(
      snippets,
      Seq(
        JavaSnippet(
          imports = Seq("import pl.andrzejressel.joiners.JoinerRunner;"),
          expectedOutput = "Pair(\"asd\", 2)",
          code = """return JoinerRunner.jdk.anySuccessfulOrThrow.run(
                 |        () -> "1",
                 |        () -> 2
                 |); // Result: Pair("asd", 2)""".stripMargin
        )
      )
    )
  }

  test("generate java file") {
    val fileUrl = getClass.getResource("/markdown.md")
    val file = new java.io.File(fileUrl.toURI)
    val expectedUrl = getClass.getResource("/GeneratedJavaSnippet.java")
    val expectedFile = new java.io.File(expectedUrl.toURI)
    val snippets = Extractor.extract(file)
    val javaFiles = snippets.map(Extractor.generateJavaFileFromJavaSnippet)
    assertEquals(
      javaFiles,
      Seq(
        JavaFile(
          packages =
            Seq("pl", "andrzejressel", "javadocsextractor", "generated"),
          fileName =
            "GeneratedJavaSnippet761226766.java", // The hash code will vary, so this is just a placeholder
          content = Files.readString(Paths.get(expectedFile.toURI))
        )
      )
    )
  }
}

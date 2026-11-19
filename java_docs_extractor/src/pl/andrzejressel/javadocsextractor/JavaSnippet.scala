package pl.andrzejressel.javadocsextractor

case class JavaSnippet(
    imports: Seq[String],
    code: String,
    expectedOutput: String
)

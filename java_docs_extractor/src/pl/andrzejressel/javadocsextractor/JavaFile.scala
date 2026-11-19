package pl.andrzejressel.javadocsextractor

case class JavaFile(
    packages: Seq[String],
    fileName: String,
    content: String
)

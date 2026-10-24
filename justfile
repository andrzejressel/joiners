set windows-shell := ["pwsh.exe", "-c"]

fmt:
    ./mill "mill.scalalib.scalafmt/" + "__.fix"

test:
    ./mill __.test

intellij:
    ./mill mill.idea/

compile:
    ./mill __.compile

compile-ci $CI="1":
    ./mill __.compile

generate-scaladoc:
    ./mill __.scalaDocGenerated

generate-scaladoc-ci $CI="1":
    ./mill __.scalaDocGenerated

generate-scaladoc-watch:
    ./mill -w __.scalaDocGenerated

code-coverage:
    ./mill scoverage.xmlReportAll

docs-serve:
    uv run zensical serve

docs-build:
    uv run zensical build

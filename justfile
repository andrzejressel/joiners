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

publish-local:
    ./mill __.publishLocal

publish:
    ./mill mill.javalib.SonatypeCentralPublishModule/

docs-serve:
    uv run zensical serve

docs-build:
    uv run zensical build

update-version NEW_VERSION:
    sd "0.0.0-DEV" "{{NEW_VERSION}}"
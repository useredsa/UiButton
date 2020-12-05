# UI Button

Component to load songs from an xml file with spanish tags.

## Preview and testing

Compile the project and test classes with

```sh
mvn test-compile
```

and run the showcase with

```sh
mvn exec:java -Dexec.mainClass=um.tds.uibutton.test.ButtonShowcase -Dexec.classpathScope=test
```

## Code format plugin

[Reference].

To install a git pre-commit hook for auto formatting:

```sh
mvn git-code-format:install-hooks
```

To validate the code format:

```
mvn git-code-format:validate-code-format -Dgcf.globPattern=**/*
```

To format the code:

```
mvn git-code-format:format-code -Dgcf.globPattern=**/*
```

[Reference]: https://github.com/Cosium/git-code-format-maven-plugin

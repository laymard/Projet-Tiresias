# Projet-Tiresias
Software Testing project at INSA Rennes


## Build the jar
Clone the project to Intellij IDE
Build the jar with  : *Build -> Build Artifacts... -> Build*
Jar is now created in *out -> artifacts -> tyresias_jar*

## Launch testing for opal

Make sur that the following programms are installed :
* ruby
* opal ( as a ruby gem )
* node
* and java

Create and set the JSON configuration file

````JSON
{
    "srcFolder" : "executable\\testsruby\\",
    "genFolder" : "executable\\js\\",
    "launchGenFileCommand" : "node {0}",
    "launchSrcFileCommand" : "ruby {0}",
    "generationCommand" : "opal -c {0} > {1}",
    "typeOfGenFile" : ".js",
    "typeOfSrcFile" : ".rb"

}
````

* srcFolder : relative path to folder where all source files to be compiled are
* genFolder : relative path where the generated file should be placed
* launchGenFileCommand : command to launch generated files. "{0}" where the file name should be placed in the command
* launchSrcFileCommand : command to launch source files. "{0}" where the file name should be placed in the command
* generationCommand : command to generate files. "{0}" where the source file should be placed. "{1}" where the generated file should be placed.
* typeOfGenFile : extension of generated files
* typeOfSrcFile : extension of source files

Launch the programm test with this command :
> java -jar project.jar config.json

## Further progression
Test other kinds of outputs (files?)

Test multiple different inputs
> Grid-search like testing (range of inputs)

Test other compiler options

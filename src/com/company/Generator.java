package com.company;

import java.io.File;
import java.io.FilenameFilter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Lolo on 19/01/2017.
 */
public class Generator {

    // Command which launch the code generator
    private String generationCommand;

    // Folder where the files to be generated are
    private String srcFolder;

    // Folder where generated programms should be placed
    private String genFolder;

    // Command to launch source files
    private String launchSrcFileCommand;

    // Command to launch generated files
    private String launchGenFileCommand;


    private String typeOfSrcFile;
    private String typeOfGenFile;

    // Create a generator to test opal rb2js
    public Generator(){
        final String dir = System.getProperty("user.dir")+"\\";
        srcFolder = dir+"executable\\ruby\\";
        genFolder = dir+"executable\\js\\";
        launchGenFileCommand = "ruby {0}";
        launchSrcFileCommand = "node {0}";
        generationCommand = "opal -c {0} > {1}";
        typeOfGenFile=".js";
        typeOfSrcFile=".rb";

    }

    public void generateFiles(){
        File folder = new File(srcFolder);
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(typeOfSrcFile);
            }
        });
        ArrayList<File> listSrcFiles = new ArrayList<File>(Arrays.asList(files));

        // Avec ce beau lambda, on mérite de figurer dans vos tweet non ? (@aymard_laurent)
        listSrcFiles.forEach((e)->{
                    String command=MessageFormat.format(generationCommand,srcFolder+e.getName(),genFolder+e.getName()+typeOfGenFile);
                    try{
                        Process execOpal = new ProcessBuilder("CMD", "/C", command).start();
                        execOpal.waitFor();
                    }catch (Exception error){
                        System.err.println("Couldn't create the file "+e.getName()+typeOfGenFile);
                    }

        });







    }

    public String getTypeOfGenFile() {
        return typeOfGenFile;
    }

    public void setTypeOfGenFile(String typeOfGenFile) {
        this.typeOfGenFile = typeOfGenFile;
    }





    public String getGenerationCommand() {
        return generationCommand;
    }

    public void setGenerationCommand(String generationCommand) {
        this.generationCommand = generationCommand;
    }

    public String getSrcFolder() {
        return srcFolder;
    }

    public void setSrcFolder(String srcFolder) {
        this.srcFolder = srcFolder;
    }

    public String getGenFolder() {
        return genFolder;
    }

    public void setGenFolder(String genFolder) {
        this.genFolder = genFolder;
    }

    public String getLaunchSrcFileCommand() {
        return launchSrcFileCommand;
    }

    public void setLaunchSrcFileCommand(String launchSrcFileCommand) {
        this.launchSrcFileCommand = launchSrcFileCommand;
    }

    public String getLaunchGenFileCommand() {
        return launchGenFileCommand;
    }

    public void setLaunchGenFileCommand(String launchGenFileCommand) {
        this.launchGenFileCommand = launchGenFileCommand;
    }
    public String getTypeOfSrcFile() {
        return typeOfSrcFile;
    }

    public void setTypeOfSrcFile(String typeOfSrcFile) {
        this.typeOfSrcFile = typeOfSrcFile;
    }
}

package com.company;

import jdk.internal.util.xml.impl.Input;

import java.io.File;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.json.JSONException;
import org.json.JSONObject;

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

    // Extension of source file
    private String typeOfSrcFile;

    // Extension of generated file
    private String typeOfGenFile;

    // Create a generator to test opal rb2js
    public Generator(){
        final String dir = System.getProperty("user.dir")+"\\";
        srcFolder = dir+"executable\\testspython\\";
        genFolder = dir+"executable\\js\\";
        launchGenFileCommand = "node {0}";
        launchSrcFileCommand = "ruby {0}";
        generationCommand = "opal -c {0} > {1}";
        typeOfGenFile=".js";
        typeOfSrcFile=".rb";
    }

    public Generator(JSONObject o ) throws JSONException{
        final String dir = System.getProperty("user.dir")+"\\";
        srcFolder = dir+o.getString("srcFolder");
        genFolder = dir+o.getString("genFolder");
        launchGenFileCommand = o.getString("launchGenFileCommand");
        launchSrcFileCommand =o.getString("launchSrcFileCommand");
        generationCommand = o.getString("generationCommand");
        typeOfGenFile=o.getString("typeOfGenFile");
        typeOfSrcFile=o.getString("typeOfSrcFile");
    }

    public Generator(String pathtoconfig) throws JSONException{
        this(Main.JSONFromPath(pathtoconfig));
    }

    public void generateFiles(){
        File folder = new File(srcFolder);
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(typeOfSrcFile);
            }
        });

        File outFolder = new File(genFolder);
        if(!outFolder.exists()) outFolder.mkdir();

        ArrayList<File> listSrcFiles = new ArrayList<File>(Arrays.asList(files));
        // Avec ce beau lambda, on mérite de figurer dans vos tweet non ? (@aymard_laurent)
        listSrcFiles.forEach((e)->{
                    System.out.println("Generating from "+e.getName());
                    String command=MessageFormat.format(generationCommand,srcFolder+e.getName(),genFolder+e.getName()+typeOfGenFile);
                    try{
                        Process execOpal = new ProcessBuilder("CMD", "/C", command).start();
                        execOpal.waitFor();
                    }catch (Exception error){
                        System.err.println("Couldn't create the file "+e.getName()+typeOfGenFile);
                    }
        });
    }

    public void executeTest(){
        HashMap<String,String> resultSource = new HashMap<>();
        HashMap<String,String> resultGenerated = new HashMap<>();


        File folder = new File(srcFolder);
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(typeOfSrcFile);
            }
        });

        for (File f : files){
            System.out.println("Executing "+f.getName());
            String command=MessageFormat.format(launchSrcFileCommand,srcFolder+f.getName());
            try {
                Process execSource = new ProcessBuilder("CMD", "/C", command).start();
                execSource.waitFor();
                InputStream stdout = execSource.getInputStream();
                byte[] out = new byte[1000];
                stdout.read(out);
                String output = new String (out,"US-ASCII");
                output=Main.processOutput(output);
                resultSource.put(f.getName(),output);
            }catch (  Exception error){

            }
        }

        folder = new File(genFolder);

        files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(typeOfGenFile);
            }
        });
        for (File f : files){
            System.out.println("Executing "+f.getName());
            String command=MessageFormat.format(launchGenFileCommand,genFolder+f.getName());
            try {
                Process execGenerated = new ProcessBuilder("CMD", "/C", command).start();
                execGenerated.waitFor();
                InputStream stdout = execGenerated.getInputStream();
                InputStream stderr = execGenerated.getErrorStream();
                byte[] out = new byte[1000];
                stdout.read(out);
                String output = new String (out,"US-ASCII");
                output=Main.processOutput(output);
                resultGenerated.put(f.getName(), output);
            }catch (  Exception error){
            }
        }

        for(String key: resultSource.keySet()){
            System.out.println("Testing "+key);
            if(resultSource.get(key).equals(resultGenerated.get(key+typeOfGenFile))){
                System.out.println("Test "+key+" is valid");
                System.out.println("Source file produced : "+resultSource.get(key)+" Generated file produced : "+resultGenerated.get(key+typeOfGenFile));

            }else if(resultGenerated.get(key+typeOfGenFile) == null){
                System.out.println("Test "+key+" is invalid");
                System.out.println("No file was generated.");
            }
            else{
                    System.out.println("Test "+key+" is invalid");
                    System.out.println("Expected : "+resultSource.get(key)+" Got : "+resultGenerated.get(key+typeOfGenFile));
            }
        }
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
        this.srcFolder = System.getProperty("user.dir")+"\\"+srcFolder;
    }

    public String getGenFolder() {
        return genFolder;
    }

    public void setGenFolder(String genFolder) {

        this.genFolder = System.getProperty("user.dir")+"\\"+genFolder;
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

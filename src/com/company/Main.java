package com.company;

import jdk.nashorn.internal.parser.JSONParser;
import sun.misc.IOUtils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.Arrays;

import org.json.*;

public class Main {

    public static String getCurrentPath(){
        final String dir = System.getProperty("user.dir")+"\\";
        return dir;
    }

    // Enlève les \r et \n à la fin de la chaine, et les blocs vide liés au byte[]
    public static String processOutput(String out){
        out = out.replaceAll("[\r]", "");
        int i=0;
        boolean diffDe0 = out.charAt(i) !=0;
        while(i<out.length() && diffDe0){
            i++;
            diffDe0 = out.charAt(i) !=0;
        }
        return  out.substring(0,i);
    }



    public static void main(String[] args) {
        String configFileName;

        if(args.length == 0){
            configFileName = "config.json";
        }else{
            if(!args[0].toLowerCase().endsWith(".json")){
                System.out.println("Input file isn't a JSON file");
                System.exit(0);
            }
            configFileName = args[0];

        }


        String path = getCurrentPath();

        try{
            byte[] encodeFile = Files.readAllBytes(Paths.get(path,configFileName));
            String content = new String (encodeFile,"US-ASCII");
            JSONObject data = new JSONObject(content);
            Generator genFromJson  = new Generator(data);
            //genFromJson.setSrcFolder("executable\\testsruby\\");
            genFromJson.generateFiles();
            genFromJson.executeTest();
        }catch(Exception error){
            error.printStackTrace();
        }




    }
}

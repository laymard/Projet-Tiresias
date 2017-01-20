package com.company;

import sun.misc.IOUtils;

import java.io.InputStream;
import java.util.Scanner;

import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {

    public static String getCurrentPath(){
        final String dir = System.getProperty("user.dir")+"\\";
        return dir;
    }

    // Enlève les \r et \n à la fin de la chaine, et les blocs vide liés au byte[]
    public static String processOutput(String out){
        out = out.replaceAll("[\n\r]", "");
        int i=0;
        boolean diffDe0 = out.charAt(i) !=0;
        while(i<out.length() && diffDe0){
            i++;
            diffDe0 = out.charAt(i) !=0;
        }
        return  out.substring(0,i);
    }

    public static void main(String[] args) {
        String path = getCurrentPath();

        Generator generator = new Generator();
        generator.setSrcFolder("executable\\testsruby\\");
        generator.generateFiles();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which ruby file would you like to test ?");
        String rubyFile = scanner.next();
        String jsFile = rubyFile+".js";

        String jsPath = path+"executable\\js\\";
        String rbPath = path+"executable\\ruby\\";

        String queryRb = "ruby " +rbPath+rubyFile;
        String queryOpal = "opal -c " +rbPath+rubyFile+" > "+jsPath+jsFile;
        String queryRbJs = "node "+jsPath+jsFile;

        try {

            //Exec opal
            Process execOpal = new ProcessBuilder("CMD", "/C", queryOpal).start();
            execOpal.waitFor();
            InputStream stderrOpal = execOpal.getErrorStream();
            byte[] errOp = new byte[500];
            stderrOpal.read(errOp);

            if(errOp[0] != 0){

                String error = new String(errOp,"US-ASCII");
                System.out.println("Error of opal : "+error);
            }
            //Exec Rb
            Process execRb = new ProcessBuilder("CMD", "/C", queryRb).start();


            Process execRbJs = new ProcessBuilder("CMD", "/C", queryRbJs).start();
            execRbJs.waitFor();
            InputStream stdoutRb = execRb.getInputStream();
            InputStream stdoutRbJs = execRbJs.getInputStream();



            byte[] outRb = new byte[500];
            stdoutRb.read(outRb);

            byte[] outRbJs = new byte[500];
            stdoutRbJs.read(outRbJs);

            String srb = new String(outRb,"US-ASCII");
            String srbjs = new String(outRbJs,"US-ASCII");

            System.out.println("Path: "+path);

            srb = Main.processOutput(srb);
            srbjs = Main.processOutput(srbjs);




            System.out.println("res rb = "+srb);
            System.out.println("res rbjs = "+srbjs);
            boolean equal = srb.equals(srbjs);

            if(srbjs.equals(srb)){
                System.out.println("Good Result");
            }else{
                System.out.println("Bad Result, expected : "+srb+", got "+srbjs);
            }


        }catch ( Exception e  ){

        }

    }
}

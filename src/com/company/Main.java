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

    public static void main(String[] args) {
        String path = getCurrentPath();

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

            System.out.println("res rb = "+srb);
            System.out.println("res rbjs = "+srbjs);


        }catch ( Exception e  ){

        }

    }
}

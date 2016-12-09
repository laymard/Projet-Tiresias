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

        String jsPath = path+"executable\\js\\";
        String rbPath = path+"executable\\ruby\\";

        String query = "node "+jsPath+"test_hello.js";
        String queryRb = "ruby " +rbPath+"hello.rb";
        String queryOpal = "opal -c " +rbPath+"hello.rb > "+rbPath+"hello.rb.js";
        String queryRbJs = "node "+rbPath+"hello.rb.js";

        try {

            Process exec = new ProcessBuilder("CMD", "/C", query).start();
            Process execRb = new ProcessBuilder("CMD", "/C", queryRb).start();
            Process execOpal = new ProcessBuilder("CMD", "/C", queryOpal).start();
            execOpal.waitFor();

            Process execRbJs = new ProcessBuilder("CMD", "/C", queryRbJs).start();
            execRbJs.waitFor();
            InputStream stdout = exec.getInputStream();
            InputStream stdoutRb = execRb.getInputStream();
            InputStream stdoutRbJs = execRbJs.getInputStream();

            byte[] out = new byte[500];
            stdout.read(out);

            byte[] outRb = new byte[500];
            stdoutRb.read(outRb);

            byte[] outRbJs = new byte[500];
            stdoutRbJs.read(outRbJs);

            String s = new String(out, "US-ASCII");
            String srb = new String(outRb,"US-ASCII");
            String srbjs = new String(outRbJs,"US-ASCII");

            System.out.println("Path: "+path);

            System.out.println("res JS = "+s);
            System.out.println("res rb = "+srb);
            System.out.println("res rbjs = "+srbjs);


        }catch ( Exception e  ){

        }

    }
}

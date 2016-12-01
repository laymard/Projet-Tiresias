package com.company;

import sun.misc.IOUtils;

import java.io.InputStream;
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
        String jsPath = path+"executable\\js\\";

        String rbPath = path+"executable\\ruby\\";

        String query = "node "+jsPath+"test_hello.js";
        String queryRb = "ruby " +rbPath+"hello.rb";
        String queryOpal = "opal -c " +rbPath+"hello.rb > hello.rb.js";
        String queryRbJs = "node "+rbPath+"hello.rb.js";

        try {
           //Process process = Runtime.getRuntime().exec(query);
            Process exec = new ProcessBuilder("CMD", "/C", query).start();
            Process execRb = new ProcessBuilder("CMD", "/C", queryRb).start();
            Process execOpal = new ProcessBuilder("CMD", "/C", queryOpal).start();
            Process execRbjs = new ProcessBuilder("CMD", "/C", queryRbJs).start();
            InputStream stdout = exec.getInputStream();
            InputStream stdoutRb = execRb.getInputStream();
            InputStream stdoutOpal = execOpal.getInputStream();
            InputStream stdoutRbJS = execRbJs.getInputStream();
            byte[] out = new byte[500];
            stdout.read(out);

            byte[] outRb = new byte[500];
            stdoutRb.read(outRb);

            byte[] outRbJs = new byte[500];
            stdoutRbJs.read(outRbJs);

            String s = new String(out, "US-ASCII");
            String srb = new String(outRb,"US-ASCII");
            String srbjs = new String(outRbJs,"US-ASCII");
            System.out.println("res JS = "+s);
            System.out.println("res rb = "+srb);
            System.out.println("res rbjs = "+srbjs);


        }catch ( Exception e  ){

        }

    }
}

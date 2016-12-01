package com.company;

import sun.misc.IOUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

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
        String queryOpal = "opal -c " +rbPath+"hello.rb >" +jsPath+"hello.rb.js";
        String queryRbJs = "node "+jsPath+"hello.rb.js";

        try {
           //Process exec = Runtime.getRuntime().exec(query);
            //Process exec = new ProcessBuilder("CMD", "/C", query).start();
            //InputStream stdout = exec.getInputStream();
            //exec.waitFor();
            //exec.destroy();


            //Process execRb = new ProcessBuilder("CMD", "/C", queryRb).start();
            Process execRb = Runtime.getRuntime().exec(queryRb);
            InputStream stdoutRb = execRb.getInputStream();
            execRb.waitFor();


            //Process execOpal = new ProcessBuilder("CMD", "/C", queryOpal).start();
            //execOpal.waitFor(2000, TimeUnit.MILLISECONDS);


            Process execRbjs = new ProcessBuilder("CMD", "/C", queryRbJs).start();
            InputStream stdoutRbJS = execRbjs.getInputStream();
            execRbjs.waitFor(2000, TimeUnit.MILLISECONDS);

            //byte[] out = new byte[500];
            //stdout.read(out);

            byte[] outRb = new byte[500];
            stdoutRb.read(outRb);

            byte[] outRbJs = new byte[500];
            stdoutRbJS.read(outRbJs);

            //String s = new String(out, "US-ASCII");
            String srb = new String(outRb,"US-ASCII");
            String srbjs = new String(outRbJs,"US-ASCII");
            System.out.println("Path: "+path);
           // System.out.println("res JS = "+s);
            System.out.println("res rb = "+srb);
            System.out.println("res rbjs = "+srbjs);


        }catch ( Exception e  ){

        }

    }
}

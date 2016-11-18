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

        try {
           //Process process = Runtime.getRuntime().exec(query);
            Process exec = new ProcessBuilder("CMD", "/C", query).start();
            Process execRb = new ProcessBuilder("CMD", "/C", queryRb).start();
            InputStream stdout = exec.getInputStream();
            InputStream stdoutRb = execRb.getInputStream();
            byte[] out = new byte[500];
            stdout.read(out);

            byte[] outRb = new byte[500];
            stdoutRb.read(outRb);

            String s = new String(out, "US-ASCII");
            String srb = new String(outRb,"US-ASCII");
            System.out.println("res JS = "+s);
            System.out.println("res rb = "+srb);


        }catch ( Exception e  ){

        }

    }
}

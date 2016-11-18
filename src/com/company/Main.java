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

        String query = "node "+jsPath+"test_hello.js";

        try {
           //Process process = Runtime.getRuntime().exec(query);
            Process exec = new ProcessBuilder("CMD", "/C", query).start();
            OutputStream stdin = exec.getOutputStream(); //vers le programm qu'on lance
            InputStream stderr = exec.getErrorStream();
            InputStream stdout = exec.getInputStream();
            byte[] out = new byte[500];
            stdout.read(out);
            String s = new String(out, "US-ASCII");
            System.out.println("res = "+s);

        }catch ( Exception e  ){

        }

    }
}

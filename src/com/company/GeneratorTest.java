package com.company;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONObject;

/**
 * Created by Jenkar on 22/01/2017.
 */
public class GeneratorTest {

    Generator gen;
    @org.junit.Before
    public void init(){
        try {
            this.gen = new Generator("configJunit1.json");
        }catch(Exception error){
            error.printStackTrace();
        }

    }
    @org.junit.After
    public void teardown(){
        File res = new File("executable\\junittests");
        for(String s : res.list())
            (new File(res,s)).delete();
        res.delete();
    }
    @org.junit.Test
    public void generateFilesPossible() throws Exception {
        gen.generateFiles();
        File f = new File("executable\\junittests\\exist.rb.js");
        assertTrue(f.exists());
    }

    @org.junit.Test
    public void generateFilesImPossible() throws Exception {
        this.gen = new Generator("configJunit2.json");
        gen.generateFiles();
        File f = new File("executable\\junittests\\noexist.go.js");
        assertFalse(f.exists());
    }

    @org.junit.Test
    public void changeConfig() throws Exception {
        this.gen = new Generator("configJunit2.json");

        gen.generateFiles();
        File f = new File("executable\\junittests\\gotest.go.js");
        assertTrue(f.exists());
    }

}
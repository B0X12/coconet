package com.coconet.server.service;

import org.apache.tomcat.util.json.JSONParser;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class ReadFileService
{
    private final String filePath = "./logs/";

    public String getString(String fileName) throws IOException
    {
        byte[] data = Files.readAllBytes(Paths.get(filePath + fileName));
        String dataString = new String(data);

        return dataString;
    }

    public String[] getLines(String fileName) throws IOException
    {
        String str = getString(fileName);
        String[] lines = str.split(System.getProperty("line.separator"));

        return lines;
    }
}
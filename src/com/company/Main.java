package com.company;

import java.io.File;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here

        File file = new File("output.txt");
        PrintWriter printWriter = new PrintWriter(file);


        printWriter.println("hello world");

        printWriter.close();


    }
}
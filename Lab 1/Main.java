package com.company;

import java.io.File;
import java.io.FileNotFoundException;


public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\User\\IdeaProjects\\LFPC\\src\\com\\company\\file.txt";
        FA fa = new FA(path);

        if (fa.check("baaaaacaabac")){
            System.out.println("It is possible");
        }
        else System.out.println("It is not possible");
    }
}

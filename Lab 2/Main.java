package com.company;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        String path = "C:\\Users\\User\\IdeaProjects\\LFPC 2\\src\\com\\company\\file.txt";
        DFA dfa = new DFA(path);
        dfa.transform();
    }
}

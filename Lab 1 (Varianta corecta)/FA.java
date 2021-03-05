package com.company;

import java.io.File;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.concurrent.TransferQueue;

public class FA {

    FA(String path){
        this.path = path;
    }

    private String path;

    ArrayList<String> start = new ArrayList<String>();
    ArrayList<String> middle = new ArrayList<String>();
    ArrayList<String> finish  = new ArrayList<String>();

    public boolean check(String foo) throws FileNotFoundException {

        File myFile = new File(path);
        Scanner scan= new Scanner(myFile);

        while (scan.hasNextLine()) {
            String text = scan.nextLine();
            if (text.length() == 3) {
                text = text.concat("$");
            }

            start.add(text.substring(0, 1));
            middle.add(text.substring(2, 3));
            finish.add(text.substring(3));
        }

        String startState = start.get(0);
        String state = startState;
        for (int i = 0; i < foo.length(); i++) {
            state = (checkStates(state, foo.substring(i, i+1)));
            if(state.equals("-")){
                return false;
            }
        }
        if(!state.equals("$")){
            return false;
        }
        return true;
    }


    private String checkStates(String state, String c){
        for (int i = 0; i < start.size(); i++) {
            //System.out.println(start.get(i) + " " + middle.get(i));
            if(start.get(i).equals(state) && middle.get(i).equals(c)){
                return finish.get(i);
            }
        }
        return "-";
    }
}

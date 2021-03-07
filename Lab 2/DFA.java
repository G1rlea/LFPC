package com.company;

import javax.swing.*;
import java.io.File;
import java.util.*;
import java.io.FileNotFoundException;
import java.util.stream.Collectors;

public class DFA {

    DFA(String path) {
        this.path = path;
    }

    private String path;

    private ArrayList<String> start = new ArrayList<String>();
    private ArrayList<String> finish = new ArrayList<String>();
    private ArrayList<String> middle = new ArrayList<String>();
    private ArrayList<String> finishingState = new ArrayList<String>();
    private ArrayList<String> distinctStart = new ArrayList<String>();

    public void transform() throws FileNotFoundException {

        File myFile = new File(path);
        Scanner scan = new Scanner(myFile);

        while (scan.hasNextLine()) {

            String text = scan.nextLine();

            start.add(text.substring(0, 2));
            middle.add(text.substring(3, 4));
            finish.add(text.substring(4, 6));

            if(text.length()>6){
                String foo = text.substring(6);
                if (foo.contains("*") && !finishingState.contains(text.substring(4, 6))){
                    finishingState.add(text.substring(4,6));
                }
            }
        }
        List<String> middleStates = middle.stream().distinct().collect(Collectors.toList());
        distinctStart.add(start.get(0));

        for (int i = 0; i < distinctStart.size(); i++) {
            for (int j = 0; j < middleStates.size(); j++) {
                String foo = getNewStates(distinctStart.get(i), middleStates.get(j));
                if (!check(foo, distinctStart)){
                    distinctStart.add(foo);
                }
            }
        }
        
        drawTable(distinctStart, middleStates);
    }

    /**FUNCTIONS */
    private int longestStateSize(ArrayList<String> list){
        int num = list.get(0).length();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).length() > num){
                num = list.get(i).length();
            }
        }
        return num;
    }

    private String getNewStates(String s, String m){
        ArrayList<String> list = separateStates(s);
        String newState = "";
        for (int i = 0; i < middle.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if(start.get(i).equals(list.get(j)) && middle.get(i).equals(m) && !newState.contains(finish.get(i))){
                    newState += finish.get(i);
                }
            }

        }
        return newState;
    }

    private ArrayList<String> separateStates(String state){
        ArrayList<String> states = new ArrayList<>();
        for (int i = 0; i < state.length()-1; i+=2) {
            String foo = state.substring(i, i+2);
            states.add(foo);
        }
        return states;
    }

    private void drawTable(ArrayList<String> start,  List<String> middle){
        ArrayList<String> allElements = new ArrayList<>();
        ArrayList<String> finale = new ArrayList<String>();

        allElements.add(" ");
        for (int i = 0; i < middle.size(); i++) {
            allElements.add(middle.get(i));
        }
        for (int i = 0; i < distinctStart.size(); i++) {
            allElements.add(distinctStart.get(i));
            for (int j = 0; j < middle.size(); j++) {
                String state = getNewStates(start.get(i), middle.get(j));
                if(state.equals("")){
                    allElements.add("-");
                }else{
                    for (int k = 0; k < finishingState.size(); k++) {
                        if (state.contains(finishingState.get(k)) && !finale.contains(state)){
                            finale.add(state);
                        }
                    }
                    allElements.add(state);
                }
            }
        }
        for (int i = 0; i < allElements.size(); i++) {
            if (allElements.get(i).length() < longestStateSize(allElements)){
                for (int j = 0; j <= longestStateSize(allElements) - allElements.get(i).length()+1; j++) {
                    allElements.set(i, allElements.get(i).concat(" "));
                }
            }
        }

        for (int i = 0; i < allElements.size(); i++) {
           System.out.print(allElements.get(i) + "\t");
           if ((i- middle.size()) % (middle.size()+1)==0){
               System.out.println();
           }
        }
        System.out.println("\nStarting State: " + distinctStart.get(0));
        System.out.println("Finale State(s): " + finale);
    }

    private boolean check(String newState, List<String> stateList){

        if (newState.equals("")){
            return true;
        }

        ArrayList<String> states = separateStates(newState);
        int length = states.size();
        int index = 0;

        for (int i = 0; i < stateList.size(); i++) {
            if (stateList.get(i).equals(newState)){
                return true;
            }
        }

        for (int i = 0; i < stateList.size(); i++) {
            if (stateList.get(i).length() == newState.length()){
                for (int j = 0; j < states.size(); j++) {

                    if (stateList.get(i).contains(states.get(j))){
                        index++;
                    }
                }
                if (index==length){
                    return true;
                }
                else return false;
            }
        }
        return false;
    }

}
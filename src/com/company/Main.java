package com.company;

import java.util.BitSet;
import java.util.Scanner;

class StartingParameterStrings{
    String size;
    String numberOfRuns;
    String outputFilename;
    String startingBoad;

    public StartingParameterStrings(){}
    public StartingParameterStrings(String[] values){
        switch(values.length){
            default:
            case 4: startingBoad = values[3];
            case 3: outputFilename = values[2];
            case 2: numberOfRuns = values[1];
            case 1: size = values[0];
            case 0: break;
        }
    }
};

class StartingParameterValues{
    int width;
    int height;
    int numberOfRuns;
    int startingBoardLength;
    BitSet startingBoard;
    String outputFilename;

    public StartingParameterValues(){}
    public StartingParameterValues(StartingParameterStrings params){
        String[] size_str = params.size.split(",");
        if(size_str.length > 0){
            width = Integer.parseInt(size_str[0]);
            if(size_str.length > 1){
                height = Integer.parseInt(size_str[1]);
            }
        }

        numberOfRuns = Integer.parseInt(params.numberOfRuns);
        outputFilename = params.outputFilename;

        String[] initialBoard = params.startingBoad.split(",");
        startingBoardLength = initialBoard.length;
        startingBoard = new BitSet(startingBoardLength);
        for(int i = 0; i < startingBoardLength; ++i){
            if(initialBoard[i].equals("1") || initialBoard[i].equals("true")){
                startingBoard.set(i);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("StartingParameterValues{");
        sb.append("width=");
        sb.append(width);
        sb.append(", height=");
        sb.append(height);
        sb.append(", numberOfRuns=");
        sb.append(numberOfRuns);
        sb.append(", startingBoard=[");
        for(int i = 0; i < startingBoardLength; ++i){
            if(i > 0){
                sb.append(',');
            }
            sb.append(startingBoard.get(i)?'1':'0');
        }

        sb.append("], outputFilename=");
        sb.append(outputFilename);
        return sb.toString();
    }
};

public class Main {

    static StartingParameterValues startinParameters;

    static String[] getStartingFromUser(){
        String[] output = new String[4];
        Scanner scanner = new Scanner(System.in);
        System.out.print("Board Size: ");
        output[0] = scanner.next();
        System.out.print("Number Of Runs: ");
        output[1] = scanner.next();
        System.out.print("Output Filename: ");
        output[2] = scanner.next();
        System.out.print("Starting Board: ");
        output[3] = scanner.next();
        return output;
    }

    public static void main(String[] args) {
        if(args.length != 4){
            args = getStartingFromUser();
        }

        if(args.length != 4){
            System.out.println("Invalid starting information.");
        }else{
            startinParameters = new StartingParameterValues(new StartingParameterStrings(args));
            System.out.println("Starting Parameters: "+startinParameters);
        }
    }


}

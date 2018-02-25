package com.company;

import GameOfLife.Board;
import GameOfLife.BoardUtils;
import GameOfLife.ConwaysCellRules;
import GameOfLife.WrappedBoard;
import com.aaronco.GifSequenceWriter;

import java.io.IOException;
import java.util.BitSet;
import java.util.Scanner;



public class Main {

    static StartingParameterValues startingParameters;

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

    public static void main(String[] args) throws IOException{
        if(args.length != 4){
            args = getStartingFromUser();
        }

        if(args.length != 4){
            System.out.println("Invalid starting information.");
        }else{
            startingParameters = new StartingParameterValues(new StartingParameterStrings(args));
            System.out.println("Starting Parameters: "+startingParameters);

            Board board = new WrappedBoard(startingParameters.width, startingParameters.height, new ConwaysCellRules(), new BitSet(startingParameters.width * startingParameters.height));
            BoardUtils.makeGifFromRun(startingParameters.outputFilename, board, startingParameters.numberOfRuns, startingParameters.startingBoard, startingParameters.startingBoardWidth, startingParameters.startingBoardHeight);
        }
    }


}

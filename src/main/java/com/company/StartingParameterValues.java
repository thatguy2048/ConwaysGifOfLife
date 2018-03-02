package com.company;

import java.util.BitSet;

/**
 * A container for the string representation of the starting parameter values.
 */
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

/**
 * A container for the starting parameters used to run a board and output a GIF file.
 */
public class StartingParameterValues{
    public int width;
    public int height;
    public int numberOfRuns;
    public int startingBoardWidth;
    public int startingBoardHeight;
    public BitSet startingBoard;
    public String outputFilename;

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

        String[] initBoard = params.startingBoad.split(":");
        String[] initBoardSize = initBoard[0].split(",");
        startingBoardWidth = Integer.parseInt(initBoardSize[0]);
        startingBoardHeight = Integer.parseInt(initBoardSize[1]);

        String[] initialBoard =initBoard[1].split(",");
        int startingBoardLength = startingBoardWidth * startingBoardHeight;
        startingBoard = new BitSet(startingBoardLength);
        for(int i = 1; i < startingBoardLength; ++i){
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
        for(int i = 0; i < startingBoardWidth * startingBoardHeight; ++i){
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

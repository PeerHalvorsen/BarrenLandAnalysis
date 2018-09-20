package barrenlandanalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 This class will create a land grid to survey for barren land

 @author Peer-Anders C. Halvorsen
 */
public class LandGrid
{

    private int length = 600; //length of the grid corresponds to y-axis
    private int width = 400; //width of the grid corresponds to x-axis
    private int[][] land; //the land to be surveyed

    /**
     Constructor with a default length of 600 and width of 400

     @param input The vertices of the barren rectangles as lower left x and y
     and the upper right x and y. Example format for two rectangles: 15 30 35
     70, 300 300 380 480
     */
    public LandGrid(String input)
    {
        land = new int[length][width];

        //Fill grid with 1's symbolizing fertile land
        initializeGrid();
        //set the barren
        setBarren(input);
    }

    /**
     Constructor allows for custom length and width

     @param input The vertices of the barren rectangles as lower left x and y
     and the upper right x and y. Example format for two rectangles: 15 30 35
     70, 300 300 380 480
     @param length The length of the grid
     @param width The width of the grid
     */
    public LandGrid(String input, int length, int width)
    {
        this.length = length;
        this.width = width;
        land = new int[length][width];
        //Fill grid with 1's symbolizing fertile land
        initializeGrid();
        //set the barren
        setBarren(input);
    }

    //initializes grid with 1's symbolizing fertile land
    private void initializeGrid()

    {
        for (int y = 0; y < length; y++) //rows first based on y values
        {
            for (int x = 0; x < width; x++) //columns per row based on x values
            {
                land[y][x] = 1;
            }
        }
    }

    /**
     Sets all the barren land in the grid

     @param input The vertices of the barren rectangles as lower left x and y
     and the upper right x and y. Example format for two rectangles: 15 30 35
     70, 300 300 380 480
     */
    public final void setBarren(String input) throws ArrayIndexOutOfBoundsException, NumberFormatException
    {
        String cleanInput = input.trim().replace(",", ""); //remove commas
        String[] sVertices = cleanInput.split(" "); //split into string array delimited by spaces
        int[] vertices = new int[sVertices.length]; //intialize an integer array

        //parse user input into integers and put into array
        for (int i = 0; i < sVertices.length; i++) {
            vertices[i] = Integer.parseInt(sVertices[i]);
            if (vertices[i] < 0) {
                throw new NumberFormatException();
            }
        }

        //Loop through each rectangle and get lower bound x, y and upper bound x, y
        for (int i = 0; i < vertices.length; i += 4) {
            int lower_x = vertices[i];
            int lower_y = vertices[i + 1];
            int upper_x = vertices[i + 2];
            int upper_y = vertices[i + 3];

            //for each rectangle loop through their coords in the array and fill
            //with 0's signifying barren land
            for (int y = lower_y; y <= upper_y; y++) //loop through rows/y values
            {
                for (int x = lower_x; x <= upper_x; x++) //loop through columns per row (x values)
                {
                    land[y][x] = 0;
                }
            }

        }
    }

    /**
     Finds all the discrete fertile areas in a LandGrid

     @return the fertile areas from smallest to largest
     */
    public String getFertileAreas()
    {

        //create Integer list to store discrete fertile areas 
        List<Integer> fertileAreas = new ArrayList<>();

        //survey the fertile areas starting at (0,0)
        fertileAreas = surveyAreas(fertileAreas, 0, 0);

        //sort fertile areas from smallest to largest
        Collections.sort(fertileAreas);

        //output fertile areas
        String output = "No fertile land was found.";
        if (!fertileAreas.isEmpty()) {
            output = "";
            for (Integer fertile : fertileAreas) {

                output = output.concat(fertile.toString() + " ");
            }
        }
        resetGrid();
        return output.trim();

    }

    //for each coordinate on the grid will start a survey of surrounding area for fertile land
    private List<Integer> surveyAreas(List<Integer> recordedAreas, int yCoord, int xCoord)
    {

        for (int y = yCoord; y < length; y++) {
            for (int x = xCoord; x < width; x++) {
                if (land[y][x] == 1) //if the current coordinate has a 1 it is fertile. start survey
                {
                    int fertileSection = survey(land, y, x); //survey surrounding area
                    recordedAreas.add(fertileSection); //store area
                    surveyAreas(recordedAreas, y, x); //move to next coordinate
                }
            }
        }
        return recordedAreas;

    }

    //this method will survey the surrounding areas from a given coordinate on the grid
    private int survey(int[][] land, int y, int x)
    {

        int count = 0;
        Stack<int[]> stack = new Stack<int[]>();

        int[] coord = {y, x}; //starting coordinate

        stack.push(coord); //initialize stack

        while (!stack.isEmpty()) {
            int[] evalCoord = stack.pop(); //coordinate to evaluate
            y = evalCoord[0];
            x = evalCoord[1];

            //these are the surrounding coordinates
            int left = x - 1;
            int right = x + 1;
            int down = y - 1;
            int up = y + 1;

            //if area is fertile, examine surrounding areas and push fertile coordinates on stack
            if (land[y][x] == 1) {
                count++; //increment area count
                land[y][x] = 2; //mark area as surveyed to avoid recount

                //examine surrounding areas and put coords on stack if fertile
                if (left >= 0 && land[y][left] == 1) {
                    int[] leftCoord = {y, left};
                    stack.push(leftCoord);
                }
                if (right < width && land[y][right] == 1) {
                    int[] rightCoord = {y, right};
                    stack.push(rightCoord);
                }
                if (down >= 0 && land[down][x] == 1) {
                    int[] downCoord = {down, x};
                    stack.push(downCoord);
                }
                if (up < length && land[up][x] == 1) {
                    int[] upCoord = {up, x};
                    stack.push(upCoord);
                }

            }

        }
        return count;
    }

    //resets grid after all land is surveyed so that fertile areas are marked as 1 again
    private void resetGrid()
    {
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < width; x++) {
                if (land[y][x] == 2) {
                    land[y][x] = 1;
                }
            }
        }
    }

    /**
     Clears all barren land from the grid returning the entirety of it to
     fertile land
     */
    public final void clearBarren()
    {
        initializeGrid();
    }

    /**

     Will display the grid on the terminal
     */
    public void displayGrid()
    {
        for (int i = (length - 1); i >= 0; i--) {
            for (int j = 0; j < width; j++) {
                if (j == width - 1) {
                    System.out.println(land[i][j]);
                }
                else {
                    System.out.print(land[i][j]);
                }
            }
        }
    }
}

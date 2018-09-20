package barrenlandanalysis;

import java.util.Scanner;

/**
 The main application class will take user input from the terminal which will
 represent areas of "barren land" in 400-meter wide by 600-meter long area. It
 will then analyze all the land for each discrete area of "fertile land".
 Finally it will output the area in square meters of each fertile zone from
 smallest to largest.

 @author Peer-Anders C. Halvorsen
 */
public class BarrenLandAnalysis
{

    private static LandGrid land;

    public static void main(String[] args)
    {

        System.out.println("This program will survey a grid of "
                + "land that is 400 meters wide by 600 meters long");
        System.out.println("The lower left corner is 0, 0 "
                + "and the upper right 399, 599");
        System.out.println("Enter barren land rectangles as a set of "
                + "4 vertices within the bounds of the grid");
        System.out.println("Lower left corner x, y first followed by upper right x, y");
        System.out.println("(Format example 0 200 200 300, 232 350 314 415):");
        System.out.println("Output will be the area of each discrete portion of"
                + " the grid that contains fertile land");
        System.out.println("from smallest to largest.");
        
        boolean validInput = false;
        while (!validInput) {
            try {

                //get user input
                System.out.print("Enter input:");
                Scanner keyboard = new Scanner(System.in);
                String input = keyboard.nextLine();
                //String input = "0 292 399 307";
                //String input = "48 192 351 207, 48 392 351 407, 120 52 135 547, 260 52 275 547";

                land = new LandGrid(input);
                validInput = true;

            }
            catch (NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                System.out.println();
                System.out.println("Invalid input. Please follow the format. "
                        + "Total number of coordinates must be multiples of 4.");
                System.out.println("No negative coordinates are allowed");
                System.out.println();
            }
        }

        String output = land.getFertileAreas();
        System.out.println(output);
    }
}

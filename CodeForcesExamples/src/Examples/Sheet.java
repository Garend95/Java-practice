package Examples;

import java.util.Scanner;

public class Sheet {

    // This project copies the c# version of the code, found in the repository of CSharpening.

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("Please enter the spreadsheet coordinates in either format");    // For example row23col52 or AAB1234
        String answer = (userInput.nextLine()).toUpperCase();   // when only the uppercase is considered, ASCII comparisons become easier
        System.out.println(answer);

        boolean format1 , format2;   // ex: row48col24 or col79row23532 for format1
                                     // ex: aaa67 or idg253 for format2

        // Conditions for format1
        boolean condition1 = answer.contains("ROW") && answer.contains("COL")  // input string has to contain ROW and COL
               ,condition2 = answer.indexOf("ROW") == answer.lastIndexOf("ROW") && answer.indexOf("COL") == answer.lastIndexOf("COL")  // duplicate ROW and COL substrings cannot co-exist
               ,condition3 = Math.abs(answer.indexOf("ROW") - answer.indexOf("COL")) != 3  // ROW and COL must not be attached ex: ROWCOL COLROW
               ,condition4 = (answer.indexOf("ROW") + 2 < answer.length() - 1) && (answer.indexOf("COL") + 2 < answer.length() - 1)    // The last of either COL or ROW has to have integer coordinates coming right after.
               ,condition5 = answer.charAt(0) == 'C' || answer.charAt(0) == 'R';   //first characters must belong to either the COL or ROW coordinates

        //System.out.println("C1 is " + condition1 + "\nC2 is " + condition2 + "\nC3 is " + condition3 + "\nC4 is " + condition4 + "\nC5 is " + condition5
        //+ "\nRow index is " + answer.indexOf("ROW") + "\nCol index is " + answer.indexOf("COL") + "\nLength - 1 is " + (answer.length() - 1));

        boolean rowComesFirst = answer.indexOf("ROW") < answer.indexOf("COL");   // detects whether ROW comes first in the input or not.

        int rowCoord, colCoord; //coordinates for format1

        int numPart; // coordinates for format2
        String letterPart;


        if(condition1 && condition2 && condition3 && condition4 && condition5)
        {
            if (rowComesFirst) {    //if row comes first we store the first coordinate for the row, and the second for the column

                try {
                    //System.out.println(answer.substring(answer.indexOf("ROW") + 3 , answer.indexOf("COL"))); // prints the coordinates for the row
                    rowCoord = Integer.parseInt(answer.substring(answer.indexOf("ROW") + 3 , answer.indexOf("COL"))); //ensures correct int format
                }catch(NumberFormatException e1) {
                    System.out.println(e1);
                    format1 = false;
                }

                try {
                    //System.out.println(answer.substring(answer.indexOf("COL") + 3 , answer.length()));
                    colCoord = Integer.parseInt(answer.substring(answer.indexOf("COL") + 3 , answer.length()));
                }catch(NumberFormatException e1) {
                    System.out.println(e1);
                    format1 = false;
                }

                format1 = true;

            } else {

                try {
                    //System.out.println(answer.substring(answer.indexOf("COL") + 3 , answer.indexOf("ROW")));
                    colCoord = Integer.parseInt(answer.substring(answer.indexOf("COL") + 3 , answer.indexOf("ROW")));
                }catch(NumberFormatException e1) {
                    System.out.println(e1);
                    format1 = false;
                }

                try {
                    //System.out.println(answer.substring(answer.indexOf("ROW") + 3 , answer.length()));
                    colCoord = Integer.parseInt(answer.substring(answer.indexOf("ROW") + 3 , answer.length()));
                }catch(NumberFormatException e1) {
                    System.out.println(e1);
                    format1 = false;
                }

                format1 = true;

            }
        }else format1 = false;

        

        //System.out.println(format1);
    }
}

package Examples;

import java.text.MessageFormat;
import java.util.*;
/**
 * Created by lev on 5/25/2017.
 */
public class Sheet {

    // This project copies the c# version of the code, found in the repository of CSharpening.

    public static void main(String[] args) {

        Scanner userInput = new Scanner(System.in);

        System.out.println("Please enter the spreadsheet coordinates in either format");    // For example row23col52 or AAB1234
        String answer = (userInput.nextLine()).toUpperCase();   // when the uppercase is only considered, ASCII comparisons become easier
        System.out.println(answer);

        boolean format1;   // ex: row48col24 or col79row23532
        boolean format2;   // ex: aaa67 or idg253

        // Conditions for format1
        boolean condition1 = answer.contains("ROW") && answer.contains("COL");  // input string has to contain ROW and COL
        boolean condition2 = answer.indexOf("ROW") == answer.lastIndexOf("ROW") && answer.indexOf("COL") == answer.lastIndexOf("COL");  // No duplicate ROW and COL substrings should exist
        boolean condition3 = Math.abs(answer.indexOf("ROW") - answer.indexOf("COL")) != 3;  // ROW and COL should not be attached ex: ROWCOL COLROW
        boolean condition4 = (answer.indexOf("ROW") < answer.length() - 1) && (answer.indexOf("COL") < answer.length() - 1);    // The last of COL or ROW in the string should have integers coming right after.
        boolean condition5 = answer.charAt(0) == 'C' || answer.charAt(0) == 'R';   //first characters must belong to either the COL or ROW coordinates
        System.out.println("C1 is " + condition1 + "\nC2 is " + condition2 + "\nC3 is " + condition3 + "\nC4 is " + condition4 + "\nC5 is " + condition5);
    }
}

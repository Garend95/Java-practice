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
               ,condition5 = answer.charAt(0) == 'C' || answer.charAt(0) == 'R'   //first characters must belong to either the COL or ROW coordinates
               ,condition6; // this one's true when row and column coordinates are not 0

        //System.out.println("C1 is " + condition1 + "\nC2 is " + condition2 + "\nC3 is " + condition3 + "\nC4 is " + condition4 + "\nC5 is " + condition5
        //+ "\nRow index is " + answer.indexOf("ROW") + "\nCol index is " + answer.indexOf("COL") + "\nLength - 1 is " + (answer.length() - 1));

        boolean rowComesFirst = answer.indexOf("ROW") < answer.indexOf("COL");   // detects whether ROW comes first in the input or not.

        int rowCoord = 0, colCoord = 0; //coordinates for format1, initialized as 0, which will detect whether they receive new values.

        if(condition1 && condition2 && condition3 && condition4 && condition5)
        {
            if (rowComesFirst) {    //if row comes first we store the first coordinate for the row, and the second for the column

                try {
                    //System.out.println(answer.substring(answer.indexOf("ROW") + 3 , answer.indexOf("COL"))); // prints the coordinates for the row
                    rowCoord = Integer.parseInt(answer.substring(answer.indexOf("ROW") + 3 , answer.indexOf("COL"))); //ensures correct int format
                }catch(NumberFormatException e) {
                    System.out.println(e);
                }

                try {
                    //System.out.println(answer.substring(answer.indexOf("COL") + 3 , answer.length()));
                    colCoord = Integer.parseInt(answer.substring(answer.indexOf("COL") + 3 , answer.length()));
                }catch(NumberFormatException e) {
                    System.out.println(e);
                }

                condition6 = rowCoord != 0 && colCoord != 0;
                format1 = condition6;

            } else {

                try {
                    //System.out.println(answer.substring(answer.indexOf("COL") + 3 , answer.indexOf("ROW")));
                    colCoord = Integer.parseInt(answer.substring(answer.indexOf("COL") + 3 , answer.indexOf("ROW")));
                }catch(NumberFormatException e) {
                    System.out.println(e);
                }

                try {
                    //System.out.println(answer.substring(answer.indexOf("ROW") + 3 , answer.length()));
                    rowCoord = Integer.parseInt(answer.substring(answer.indexOf("ROW") + 3 , answer.length()));
                }catch(NumberFormatException e) {
                    System.out.println(e);
                }

                condition6 = rowCoord != 0 && colCoord != 0;
                format1 = condition6;

            }
        }else format1 = false;


        int numPart = 0; // coordinates for format2
        String letterPart = "none";

        char character = answer.charAt(0);
        int ascii = (int) character;    //  first character must be a letter in format2

        boolean _condition1 = ascii >= 65 && ascii <= 90   //  uppercase alphabet is from 65 = A to 90 = Z
               ,_condition2 = false // this will be true once we have a separate letter segment followed by a number segment, also detects whether integers exist.
               ,_condition3;  // this one's true if the integer part is not zero

        //System.out.println(ascii + " " + _condition1);

        if(_condition1){
            for (int i = 1; i < answer.length(); i++){

                character = answer.charAt(i);   // we'll observe each character in the string
                ascii = (int) character;

                if(ascii >= 48 && ascii <= 57) //  once we get to the number part we'll try to parse it
                {
                    
                    try {
                        numPart = Integer.parseInt(answer.substring(i , answer.length())); //number part comes after the letter part
                        _condition2 = true;
                        letterPart = answer.substring(0 , i);
                        //System.out.println(letterPart + " " + numPart);
                        break;
                    }catch(NumberFormatException e) {
                        if(answer.substring(i , answer.length()).contains("ROW") || answer.substring(i , answer.length()).contains("COL")) // could be that the "mistake" of finding letters
                        {                                                                                                                  // in the number part is due to it containing
                            _condition2 = false;                                                                                               // COL or ROW
                            break;
                        } else {
                            System.out.println(e);
                            _condition2 = false;
                            break;
                        }

                    }

                }
            }
        }

        _condition3 = (numPart != 0);
        format2 = _condition1 && _condition2 && _condition3;

        if (format1) {

            System.out.println("your input belongs in format 1");
            System.out.println("By converting to format 2, we get: " + convertToFormat2(rowCoord, colCoord));

        } else if (format2) {

            System.out.println("Your input belongs in format 2");
            System.out.println("By converting to format 1, we get: " + convertToFormat1(numPart, letterPart));

        }
    }

    public static String convertToFormat1 (int numbers, String letters) {

        int len = letters.length() - 1; // used as the exponent of 26, currently the leftmost degree
        double sum = 0; // once the following loop is done, this will be the accurate column number representation

        for (int i = 0; i < letters.length(); i++) {    // left to right with decreasing exponents

            sum += Math.pow( 26 , len - i) * ((int) letters.charAt(i) - 64); // the power of 26 (number of letters in alphabet) with the [len - i]'th
        }                                                                         // position will be multiplied by the adjusted ascii of said letter

        int result = (int) sum;                     // simply convert to the intended format and then print the format down below

        return "COl" + result + "ROW" + numbers;
    }

    public static String convertToFormat2 (int rowNumber, int colNumber) {

        int coordinate = 0;  // this will act as a guide on which letters to pick
        String lettersFinal = "";

        int i = 0;  // this will act as the exponent of 26 starting from 0 on the rightmost letter and will detect the number of letters needed
        while (colNumber > coordinate) {
            coordinate += Math.pow(26, i + 1) ;  // when the column number surpasses a coordinate "level" ex Z is 0th level ZZ is 1st etc
        }                                           // but is then smaller than the next level, that means that it can be expressed
                                                    // using just the number of letters of the next level

        int j = 26; // this will act as the index of letters which we'll decrement for each i to determine the letter at each position starting
                    // with the last

        while (i >= 0) {

            coordinate = (int) Math.pow(26, i) * j + allZsFromLowerExponent(i);     // this could be similar to a greedy algorithm ,beginning with the largest letter representations
            if (coordinate > colNumber) {
                j--;
                if (j == 1) {
                    lettersFinal += (char) (j + 64);
                    j = 26;
                    i--;
                }
                continue;
            }
            else if (coordinate < colNumber) {
                lettersFinal += (char) (j + 64);
                j = 26;
                i--;
            }
            else if (coordinate == colNumber) {
                lettersFinal += (char) (j + 64) + returnAllLowerZs(i);
                break;
            }
        }

        return lettersFinal + rowNumber;
    }

    public static int allZsFromLowerExponent (int exponent) {   // this tends to the lower letters of the letter that we are working on, because only the current letter cannot
                                                                // accurately express the number representation, it might overvalue or undervalue by itself
        if (exponent - 1 == 0) {
            return 26;                      // here it comes down to the smallest index 0 where Z is 26
        }
        else if (exponent - 1 < 0) {
            return 0;   // in case the the exponent was 0 to begin with, there's no need to add anything and the recursive method would break
        }
        else {
            return (int) (Math.pow(26, exponent) + allZsFromLowerExponent(exponent - 1));   // return the Z value of this level along with lower levels recursively until they halt
        }
    }

    public static String returnAllLowerZs (int exponent) {
        if (exponent == 0) return "";
        else return "Z" + returnAllLowerZs(exponent - 1);
    }
}

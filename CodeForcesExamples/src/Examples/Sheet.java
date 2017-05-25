package Examples;

import java.util.Scanner;
/**
 * Created by lev on 5/25/2017.
 */
public class Sheet {
    // This project copies the c# version of the code, found in the repository of CSharpening.

    Scanner userinput = new Scanner(System.in);

    System.out.println("Please enter the spreadsheet coordinates in either format");    // For example row23col52 or AAB1234
    String answer = userinput.readLine();

    bool format1;   // ex: row48col24 or col79row23532
    bool format2;   // ex: aaa67 or idg253

}

/*
 * Created by cravuri on 1/27/20
 */

import parser.Parser;
import scanner.Scanner;

import java.io.FileInputStream;
import java.io.IOException;

public class Main
{

    public static void main(String[] args) throws IOException
    {
        Scanner lex = new Scanner(new FileInputStream("ParserTest.txt"));
        Parser p = new Parser(lex);

        while (lex.hasNext())
        {
            p.parseStatement();
        }
    }

}

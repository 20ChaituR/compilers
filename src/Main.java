/*
 * Created by cravuri on 1/27/20
 */

import environment.Environment;
import parser.Parser;
import scanner.Scanner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class Main
{

    public static void main(String[] args) throws IOException
    {
        Scanner lex = new Scanner(new FileInputStream("tests/parserTest9.txt"));
        Parser p = new Parser(lex);

        Environment mainEnv = new Environment(new HashMap<>(), new HashMap<>(), null);
        p.parseProgram().compile("output.txt");
    }

}

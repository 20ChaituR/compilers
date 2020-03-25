package parser;

import scanner.ScanErrorException;
import scanner.Scanner;

import java.util.HashMap;
import java.util.Map;

import static scanner.Scanner.isDigit;

/**
 * The Parser class provides a simple parser for Compilers and Interpreters. It
 * currently has the ability to do basic arithmetic, read from the user, write
 * to the console, and store variables. The language it parses is Pascal.
 *
 * @author Chaitanya Ravuri
 * @version March 5, 2020
 *
 * Usage:
 * Create a new Parser by passing a Scanner object
 * Read and execute a statement with the parseStatement() method
 */
public class Parser
{

    private Scanner sc;                         // Used to read tokens from the input program
    private String curToken;                    // The current token that the parser is on
    private Map<String, Object> variableMap;    // The map of variable names to their values

    private java.util.Scanner consoleIn;        // Used to read user input from console

    /**
     * Parser constructor that uses the Scanner class for input.
     *
     * Usage:
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * Parser p = new Parser(lex);
     *
     * @param sc the scanner to use
     */
    public Parser(Scanner sc)
    {
        this.sc = sc;
        try
        {
            curToken = sc.nextToken();
        }
        catch (ScanErrorException e)
        {
            e.printStackTrace();
        }
        variableMap = new HashMap<>();
        consoleIn = new java.util.Scanner(System.in);
    }

    /**
     * Reads the next token if the current token matches with the given expected token.
     *
     * @param expectedToken the token that the parser expects to appear next
     * @throws IllegalArgumentException if expected token doesn't match with the current token
     */
    private void eat(String expectedToken)
    {
        if (curToken.equals(expectedToken))
        {
            try
            {
                curToken = sc.nextToken();
            }
            catch (ScanErrorException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            throw new IllegalArgumentException("Illegal Argument - Expected " + expectedToken +
                    " and found " + curToken);
        }
    }

    /**
     * Parses and returns the next integer in the token stream.
     *
     * @return the value of the parsed integer
     * @precondition the current token is an integer
     * @postcondition number token has been eaten
     */
    private int parseNumber()
    {
        int num = Integer.parseInt(curToken);
        eat(curToken);
        return num;
    }

    /**
     * Parses and returns the next ID in the token stream
     *
     * @return the value of the parsed ID
     * @precondition the current token is an ID
     * @postcondition the ID has been eaten
     */
    private String parseID()
    {
        String id = curToken;
        eat(curToken);
        return id;
    }

    /**
     * Parses the next factor in the token stream, consisting of
     * either a positive or negative number, with parentheses
     *
     * @return the simplified value of the factor
     */
    private int parseFactor()
    {
        if ("(".equals(curToken))
        {
            eat("(");
            int num = parseExpr();
            eat(")");
            return num;
        }
        else if ("-".equals(curToken))
        {
            eat("-");
            int num = parseFactor();
            return -num;
        }
        else
        {
            if (isDigit(curToken.charAt(0)))
            {
                return parseNumber();
            }
            else
            {
                return (int) variableMap.get(parseID());
            }
        }
    }

    /**
     * Parses the next term in the input stream, which
     * consists of factors multiplied, divided, or modded
     * together
     *
     * @return the simplified value of the term
     */
    private int parseTerm()
    {
        int factor = parseFactor();
        return parseWhileTerm(factor);
    }

    /**
     * Helper method for parseTerm(). Used to parse a series
     * of operations that are right recursive.
     *
     * @param factor the previous factor in the term, used to calculate the total value
     * @return the result of the multiplication, division, and modding
     */
    private int parseWhileTerm(int factor)
    {
        while ("*".equals(curToken) || "/".equals(curToken) || "mod".equals(curToken))
        {
            if ("*".equals(curToken))
            {
                eat("*");
                factor *= parseFactor();
            }
            else if ("/".equals(curToken))
            {
                eat("/");
                factor /= parseFactor();
            }
            else
            {
                eat("mod");
                factor %= parseFactor();
            }
        }
        return factor;
    }

    /**
     * Parses the next expression in the input stream,
     * consisting of a series of terms added or subtracted
     * together
     *
     * @return the result of the operations
     */
    private int parseExpr()
    {
        int term = parseTerm();
        return parseWhileExpr(term);
    }

    /**
     * Helper method for parseExpr(). Used to make the grammar
     * right recursive, and is able to parse a series of additions
     * and subtractions.
     *
     * @param term the previous term in the expression
     * @return the result of the operations
     */
    private int parseWhileExpr(int term)
    {
        while ("+".equals(curToken) || "-".equals(curToken))
        {
            if ("+".equals(curToken))
            {
                eat("+");
                term += parseTerm();
            }
            else
            {
                eat("-");
                term -= parseTerm();
            }
        }
        return term;
    }

    /**
     * Helper method for parseStatement(). Used to make
     * parsing a series of BEGIN and END statements right
     * recursive.
     */
    private void parseWhileBegin()
    {
        if ("END".equals(curToken))
        {
            eat("END");
            eat(";");
        }
        else
        {
            parseStatement();
            parseWhileBegin();
        }
    }

    /**
     * Parses and runs any statement consisting of variable
     * assignments, expressions using numbers or those variables,
     * reading from input, and writing to output.
     */
    public void parseStatement()
    {
        if ("WRITELN".equals(curToken))
        {
            eat("WRITELN");
            eat("(");
            int num = parseExpr();
            eat(")");
            eat(";");
            System.out.println(num);
        }
        else if ("READLN".equals(curToken))
        {
            eat("READLN");
            eat("(");
            String id = parseID();
            eat(")");
            eat(";");
            System.out.println("Enter a number: ");
            int val = consoleIn.nextInt();
            variableMap.put(id, val);
        }
        else if ("BEGIN".equals(curToken))
        {
            eat("BEGIN");
            parseWhileBegin();
        }
        else
        {
            String id = parseID();
            eat(":=");
            int val = parseExpr();
            eat(";");
            variableMap.put(id, val);
        }
    }

}

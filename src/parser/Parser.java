package parser;

import ast.Number;
import ast.*;
import scanner.ScanErrorException;
import scanner.Scanner;

import java.util.ArrayList;

import static scanner.Scanner.isDigit;

/**
 * The Parser class provides a simple parser for Compilers and Interpreters. It
 * currently has the ability to do basic arithmetic, read from the user, write
 * to the console, store variables, do conditionals, and execute loops. The language
 * it parses is Pascal.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 *
 * Usage:
 * Create a new Parser by passing a Scanner object
 * Read and generate an AST with the parseStatement() method
 * Execute that AST using the exec() method
 */
public class Parser
{

    private Scanner sc;                         // Used to read tokens from the input program
    private String curToken;                    // The current token that the parser is on

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
     * @return the AST representation of the factor
     */
    private Expression parseFactor()
    {
        if ("(".equals(curToken))
        {
            eat("(");
            Expression exp = parseExpr();
            eat(")");
            return exp;
        }
        else if ("-".equals(curToken))
        {
            eat("-");
            Expression exp = parseFactor();
            return new BinOp("-", new Number(0), exp);
        }
        else
        {
            if (isDigit(curToken.charAt(0)))
            {
                return new Number(parseNumber());
            }
            else
            {
                return new Variable(parseID());
            }
        }
    }

    /**
     * Parses the next term in the input stream, which
     * consists of factors multiplied, divided, or modded
     * together
     *
     * @return the AST representation of the term
     */
    private Expression parseTerm()
    {
        Expression factor = parseFactor();
        return parseWhileTerm(factor);
    }

    /**
     * Helper method for parseTerm(). Used to parse a series
     * of operations that are right recursive.
     *
     * @param prevTerm the previous factor in the term, used to find the complete AST
     * @return the AST for the term up to the current token
     */
    private Expression parseWhileTerm(Expression prevTerm)
    {
        if ("*".equals(curToken))
        {
            eat("*");
            Expression curTerm = new BinOp("*", prevTerm, parseFactor());
            return parseWhileTerm(curTerm);
        }
        else if ("/".equals(curToken))
        {
            eat("/");
            Expression curTerm = new BinOp("/", prevTerm, parseFactor());
            return parseWhileTerm(curTerm);
        }
        else if ("mod".equals(curToken))
        {
            eat("mod");
            Expression curTerm = new BinOp("mod", prevTerm, parseFactor());
            return parseWhileTerm(curTerm);
        }
        else
        {
            return prevTerm;
        }
    }

    /**
     * Parses the next expression in the input stream,
     * consisting of a series of terms added or subtracted
     * together
     *
     * @return the AST representation of the expression
     */
    private Expression parseExpr()
    {
        Expression term = parseTerm();
        return parseWhileExpr(term);
    }

    /**
     * Helper method for parseExpr(). Used to make the grammar
     * right recursive, and is able to parse a series of additions
     * and subtractions.
     *
     * @param prevExpr the previous term in the expression
     * @return the AST for the expression up to the current token
     */
    private Expression parseWhileExpr(Expression prevExpr)
    {
        if ("+".equals(curToken))
        {
            eat("+");
            Expression curExpr = new BinOp("+", prevExpr, parseTerm());
            return parseWhileTerm(curExpr);
        }
        else if ("-".equals(curToken))
        {
            eat("-");
            Expression curExpr = new BinOp("-", prevExpr, parseTerm());
            return parseWhileTerm(curExpr);
        }
        else
        {
            return prevExpr;
        }
    }

    /**
     * Parses the next condition in the input stream. A
     * condition consists of an expression, followed by a
     * relop, followed by another expression.
     *
     * @return the AST representation of the condition
     */
    private Condition parseCondition()
    {
        Expression exp1 = parseExpr();
        String relop;
        if (curToken.equals("=") || curToken.equals("<>") || curToken.equals("<") ||
                curToken.equals(">") || curToken.equals("<=") || curToken.equals(">="))
        {
            relop = curToken;
            eat(curToken);
        }
        else
        {
            throw new IllegalArgumentException("Expected a boolean operator, but found " + curToken);
        }
        Expression exp2 = parseExpr();
        return new Condition(relop, exp1, exp2);
    }

    /**
     * Helper method for parseStatement(). Used to make
     * parsing a series of BEGIN and END statements right
     * recursive.
     *
     * @return the AST representation of the block statement
     */
    private Block parseWhileBegin()
    {
        if ("END".equals(curToken))
        {
            eat("END");
            eat(";");
            return new Block(new ArrayList<>());
        }
        else
        {
            Statement stmt = parseStatement();
            Block blck = parseWhileBegin();
            blck.getStmts().add(0, stmt);
            return blck;
        }
    }

    /**
     * Parses and returns the AST representation of any statement
     * consisting of variable assignments, expressions using numbers
     * or those variables, reading from input, writing to output,
     * if conditions, and while loops.
     *
     * @return the AST representation of the full statement
     */
    public Statement parseStatement()
    {
        if ("WRITELN".equals(curToken))
        {
            eat("WRITELN");
            eat("(");
            Expression exp = parseExpr();
            eat(")");
            eat(";");
            return new Writeln(exp);
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
            return new Readln(id, val);
        }
        else if ("BEGIN".equals(curToken))
        {
            eat("BEGIN");
            return parseWhileBegin();
        }
        else if ("IF".equals(curToken))
        {
            eat("IF");
            Condition cond = parseCondition();
            eat("THEN");
            Statement stmt = parseStatement();
            return new If(cond, stmt);
        }
        else if ("WHILE".equals(curToken))
        {
            eat("WHILE");
            Condition cond = parseCondition();
            eat("DO");
            Statement stmt = parseStatement();
            return new While(cond, stmt);
        }
        else
        {
            String var = parseID();
            eat(":=");
            Expression exp = parseExpr();
            eat(";");
            return new Assignment(var, exp);
        }
    }

}

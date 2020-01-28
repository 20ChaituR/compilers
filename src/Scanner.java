import java.io.*;

/**
 * Scanner is a simple scanner for Compilers and Interpreters (2014-2015)
 * Lab Exercise 1
 *
 * @author Chaitanya Ravuri
 * @version January 27, 2019
 *
 * Usage:
 * Create a new Scanner using one of the methods given by the constructors
 * Read tokens using the nextToken() method
 * Check if there are any tokens remaining using the hasNext() method
 */
public class Scanner {

    private BufferedReader in; // The input stream
    private char currentChar;  // The character you are currently at
    private boolean eof;       // Whether end of file has been reached

    /**
     * Scanner constructor for construction of a scanner that uses an
     * InputStream object for input.
     *
     * Usage:
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     *
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream) {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }

    /**
     * Scanner constructor for constructing a scanner that scans a given
     * input string. It sets the end-of-file flag and then reads the first
     * character of the input string into the instance field currentChar.
     *
     * Usage:
     * Scanner lex = new Scanner(input_string);
     *
     * @param inString the string to scan
     */
    public Scanner(String inString) {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }

    /**
     * Sets currentCharacter to the next character in the input stream. If at
     * the end of file, sets eof to true.
     */
    private void getNextChar() {
        int nChar = 0;
        try {
            nChar = in.read();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (nChar == -1) {
                eof = true;
            } else {
                currentChar = (char) nChar;
            }
        }
    }

    /**
     * Scans the next character if the current character matches the given
     * expected character
     *
     * @param expected the character to match with the current character
     * @throws ScanErrorException if expected doesn't match with currentChar
     */
    private void eat(char expected) throws ScanErrorException {
        if (expected == currentChar) {
            getNextChar();
        } else {
            throw new ScanErrorException("Illegal character - Expected " +
                    expected + " and found " + currentChar);
        }
    }

    /**
     * Checks whether the input stream has any more characters remaining.
     *
     * @return whether there are any characters remaining in the input stream
     */
    public boolean hasNext() {
        return !eof;
    }

    /**
     * Gets the next lexeme in the input stream, currently consisting of either
     * a number, identifier, or operand. Throws a ScanErrorException if no lexeme
     * can be found.
     *
     * @return the next lexeme in the input stream
     */
    public String nextToken() throws ScanErrorException {
        while (isWhiteSpace(currentChar)) {
            eat(currentChar);
        }

        if (!hasNext()) {
            return "END";
        }

        if (isDigit(currentChar)) {
            return scanNumber();
        }

        if (isLetter(currentChar)) {
            return scanIdentifier();
        }

        return scanOperand();
    }

    /**
     * Scans the next number in the input stream, defined by the regex (digit)+
     *
     * @return the next number in the input stream
     * @throws ScanErrorException if the current character is not a digit
     */
    private String scanNumber() throws ScanErrorException {
        StringBuilder sb = new StringBuilder();

        while (isDigit(currentChar)) {
            sb.append(currentChar);
            eat(currentChar);
        }

        if (sb.length() == 0) {
            throw new ScanErrorException("Unrecognized character");
        }

        return sb.toString();
    }

    /**
     * Scans the next identifier in the input stream, which is defined by the
     * regex (letter)(letter|digit)*
     *
     * @return the next identifier in the input stream
     * @throws ScanErrorException if the current character is not a letter
     */
    private String scanIdentifier() throws ScanErrorException {
        StringBuilder sb = new StringBuilder();

        if (isLetter(currentChar)) {
            sb.append(currentChar);
            eat(currentChar);
        } else {
            throw new ScanErrorException("Unrecognized character");
        }

        while (isLetter(currentChar) || isDigit(currentChar)) {
            sb.append(currentChar);
            eat(currentChar);
        }

        return sb.toString();
    }

    /**
     * Scans the next operand in the input stream
     *
     * @return the next character if it is an operand
     * @throws ScanErrorException if the current character is not an operand
     */
    private String scanOperand() throws ScanErrorException {
        char[] operands = {'=', '+', '-', '*', '/', '%', '(', ')'};

        for (char op : operands) {
            if (currentChar == op) {
                eat(currentChar);
                return String.valueOf(op);
            }
        }

        throw new ScanErrorException("Unrecognized character");
    }

    /**
     * Checks whether the given character is a digit
     *
     * @param c the character to check
     * @return whether c is a digit
     */
    public static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }

    /**
     * Checks whether the given character is a letter, either lowercase or
     * uppercase
     *
     * @param c the character to check
     * @return whether c is a letter
     */
    public static boolean isLetter(char c) {
        return ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
    }

    /**
     * Checks whether the given character is a whitespace, either a space, tab,
     * return, or newline
     *
     * @param c the character to check
     * @return whether c is whitespace
     */
    public static boolean isWhiteSpace(char c) {
        return c == ' ' || c == '\t' || c == '\r' || c == '\n';
    }
}

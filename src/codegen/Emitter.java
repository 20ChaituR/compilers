package codegen;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The Emitter class helps write MIPS code
 * to an output file line by line.
 *
 * @author Chaitanya Ravuri
 * @version April 29, 2020
 */
public class Emitter
{
    private PrintWriter out;
    private int labelID = 0;

    /**
     * Emitter constructor that creates
     * an emitter for writing to a new file
     * with a given name.
     *
     * @param outputFileName the name of the output file
     */
    public Emitter(String outputFileName)
    {
        try
        {
            out = new PrintWriter(new FileWriter(outputFileName), true);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Prints one line of code to file
     * (with non-labels indented)
     *
     * @param code the code to print
     */
    public void emit(String code)
    {
        if (!code.endsWith(":"))
            code = "\t" + code;
        out.println(code);
    }

    /**
     * Prints the code for pushing a given
     * register onto the stack.
     *
     * @param reg the register to push
     */
    public void emitPush(String reg)
    {
        emit("# Pushes " + reg.substring(1) + " onto the stack");
        emit("subu $sp $sp 4");
        emit("sw " + reg + " ($sp)\n");
    }

    /**
     * Prints the code for popping the stack
     * onto a given register.
     *
     * @param reg the register to pop onto
     */
    public void emitPop(String reg)
    {
        emit("# Pops stack onto " + reg.substring(1));
        emit("lw " + reg + " ($sp)");
        emit("addu $sp $sp 4\n");
    }

    /**
     * Gets the next label id for if
     * statements and while loops.
     *
     * @return the next label id
     */
    public int nextLabelID()
    {
        return labelID++;
    }

    /**
     * Closes the file. Should be called
     * after all calls to emit.
     */
    public void close()
    {
        out.close();
    }
}
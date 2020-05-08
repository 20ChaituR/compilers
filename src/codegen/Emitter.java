package codegen;

import ast.ProcedureDeclaration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

    private ProcedureDeclaration currentContext = null;
    private int excessStackHeight = 0;

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
        excessStackHeight += 4;
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
        excessStackHeight -= 4;
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
     * Remember proc as current procedure
     * context
     *
     * @param proc the current context
     */
    public void setProcedureContext(ProcedureDeclaration proc)
    {
        currentContext = proc;
        excessStackHeight = 0;
    }

    /**
     * Clear current procedure context
     * (remember null)
     */
    public void clearProcedureContext()
    {
        currentContext = null;
    }

    /**
     * Checks if the given variable is a
     * local variable to the current context
     *
     * @param varName the given variable
     * @return whether it is local
     */
    public boolean isLocalVariable(String varName)
    {
        if (currentContext == null)
        {
            return false;
        }
        if (currentContext.getID().equals(varName))
        {
            return true;
        }
        for (String param : currentContext.getParams())
        {
            if (param.equals(varName))
            {
                return true;
            }
        }
        for (String param : currentContext.getLocalVars())
        {
            if (param.equals(varName))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the offset from the stack pointer
     * for the given variable.
     *
     * @param localVarName the variable to find
     * @return its position on the stack
     * @precondition localVarName is the name of
     * a local variable for the procedure currently
     * being compiled
     */
    public int getOffset(String localVarName)
    {
        List<String> localVars = currentContext.getLocalVars();
        for (int i = 0; i < localVars.size(); i++)
        {
            if (localVars.get(i).equals(localVarName))
            {
                return 4 * (localVars.size() - i - 1) + excessStackHeight;
            }
        }
        if (currentContext.getID().equals(localVarName))
        {
            return 4 * localVars.size() + excessStackHeight;
        }
        List<String> params = currentContext.getParams();
        for (int i = 0; i < params.size(); i++)
        {
            if (params.get(i).equals(localVarName))
            {
                return 4 * (params.size() - i) + 4 * localVars.size() + excessStackHeight;
            }
        }
        throw new IllegalArgumentException("Not a local variable.");
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
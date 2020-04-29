package ast;

import codegen.Emitter;
import environment.Environment;

import java.util.List;

/**
 * The Program class is a type of Statement
 * that encapsulates an entire program, consisting
 * of a list of procedures and a final statement
 * to be run.
 *
 * @author Chaitanya Ravuri
 * @version April 7, 2020
 */
public class Program extends Statement
{

    private List<VariableDeclaration> variables;
    private List<ProcedureDeclaration> procedures;
    private Statement stmt;

    /**
     * Program constructor that consists of a
     * List of ProcedureDeclarations for the
     * procedures and a Statement for the final
     * statement to be run.
     *
     * @param procedures the declarations at the start
     * @param stmt       the statement to be run
     */
    public Program(List<VariableDeclaration> variables, List<ProcedureDeclaration> procedures, Statement stmt)
    {
        this.variables = variables;
        this.procedures = procedures;
        this.stmt = stmt;
    }

    /**
     * Adds a given ProcedureDeclaration to the
     * list of procedures
     *
     * @param proc the procedure to be added
     */
    public void addProcedure(ProcedureDeclaration proc)
    {
        procedures.add(proc);
    }

    public void addVariable(VariableDeclaration var)
    {
        variables.add(var);
    }

    /**
     * This method executes the Program by declaring
     * the procedures, then executing the final Statement
     *
     * @param env the environment for the variables
     */
    @Override
    public void exec(Environment env)
    {
        for (VariableDeclaration var : variables)
        {
            var.exec(env);
        }
        for (ProcedureDeclaration proc : procedures)
        {
            proc.exec(env);
        }
        stmt.exec(env);
    }

    public void compile(String fileName)
    {
        Emitter e = new Emitter(fileName);
        e.emit(".text");
        e.emit(".globl main");
        e.emit("main:");

        stmt.compile(e);

        e.emit("# Halts the program");
        e.emit("li $v0 10");
        e.emit("syscall\n");

        e.emit(".data");
        e.emit("newline:");
        e.emit(".asciiz \"\\n\"");

        for (VariableDeclaration var : variables)
        {
            e.emit("var" + var.getID() + ":");
            e.emit(".word 0");
        }
    }
}

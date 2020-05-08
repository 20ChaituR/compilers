package ast;

import codegen.Emitter;
import environment.Environment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
public class Program
{

    private List<String> variableNames;
    private List<ProcedureDeclaration> procedures;
    private Statement stmt;

    /**
     * Program constructor that consists of a
     * List of Strings for the variable names, a
     * List of ProcedureDeclarations for the
     * procedures and a Statement for the final
     * statement to be run.
     *
     * @param variableNames the names of the variables
     * @param procedures    the declarations at the start
     * @param stmt          the statement to be run
     */
    public Program(List<String> variableNames, List<ProcedureDeclaration> procedures, Statement stmt)
    {
        this.variableNames = variableNames;
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

    /**
     * Adds a list of variables to the variable names
     *
     * @param newVariables the variables to be added
     */
    public void addVariables(List<String> newVariables)
    {
        variableNames.addAll(newVariables);
    }

    /**
     * This method executes the Program by declaring
     * the variables, then declaring the procedures,
     * then executing the final Statement
     *
     * @param env the environment for the variables
     */
    public void exec(Environment env)
    {
        for (String var : variableNames)
        {
            env.declareVariable(var, 0);
        }
        for (ProcedureDeclaration proc : procedures)
        {
            proc.exec(env);
        }
        stmt.exec(env);
    }

    /**
     * This method compiles the Program statement by
     * first emitting the start of the file, then
     * compiling the statement, then compiling all of
     * the variables.
     *
     * @param fileName the target file for the emitter
     */
    public void compile(String fileName)
    {
        Emitter e = new Emitter(fileName);
        e.emit("# @author Chaitu Ravuri");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        e.emit("# @version " + dtf.format(now));
        e.emit("");

        e.emit(".text");
        e.emit(".globl main\n");
        e.emit("main:");

        stmt.compile(e);

        e.emit("# Halts the program");
        e.emit("li $v0 10");
        e.emit("syscall\n");

        for (ProcedureDeclaration proc : procedures)
        {
            proc.compile(e);
        }

        e.emit(".data");
        e.emit("newline:");
        e.emit(".asciiz \"\\n\"");

        for (String var : variableNames)
        {
            e.emit("var" + var + ":");
            e.emit(".word 0");
        }

        e.close();
    }
}

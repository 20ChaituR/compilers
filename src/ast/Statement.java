package ast;

import environment.Environment;

/**
 * The Statement class consists of any instruction
 * which can be run, including variable assignments,
 * printing, reading, looping, and if conditions.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public abstract class Statement
{

    /**
     * This method is used to execute the statement,
     * with the specifics described in each subclass.
     *
     * @param env the environment for the variables
     */
    public abstract void exec(Environment env);

}

package ast;

import codegen.Emitter;
import environment.Environment;

/**
 * The Condition class is a special AST element that consists
 * two expressions and a relop, and either evaluates to true
 * or false.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class Condition
{

    String relop;
    Expression exp1;
    Expression exp2;

    /**
     * Condition constructor that consists of the relop,
     * and the two expressions which are to be compared by
     * the relop.
     *
     * @param relop the operator comparing the two expressions
     * @param exp1  the left side of the relop
     * @param exp2  the right side of the relop
     */
    public Condition(String relop, Expression exp1, Expression exp2)
    {
        this.relop = relop;
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * This method evaluates the Condition comparing the
     * values of the two expressions using the given relop.
     *
     * @param env the environment for the variables
     * @return either true or false depending on the comparison between both expressions
     */
    public boolean eval(Environment env)
    {
        switch (relop)
        {
            case "=":
                return exp1.eval(env) == exp2.eval(env);
            case "<>":
                return exp1.eval(env) != exp2.eval(env);
            case "<":
                return exp1.eval(env) < exp2.eval(env);
            case ">":
                return exp1.eval(env) > exp2.eval(env);
            case "<=":
                return exp1.eval(env) <= exp2.eval(env);
            case ">=":
                return exp1.eval(env) >= exp2.eval(env);
        }

        throw new IllegalArgumentException("Invalid boolean operator");
    }

    /**
     * This method compiles the Condition statement by compiling
     * the two Expressions, then jumping to the target label if
     * the comparison of the expressions using the relop is false.
     *
     * @param e           the emitter for the generated code
     * @param targetLabel the label to jump to if the condition is false
     */
    public void compile(Emitter e, String targetLabel)
    {
        exp1.compile(e);
        e.emitPush("$v0");
        exp2.compile(e);
        e.emitPop("$t0");

        switch (relop)
        {
            case "=":
                e.emit("# Continue if t0 = v0");
                e.emit("bne $t0 $v0 " + targetLabel + "\n");
                break;
            case "<>":
                e.emit("# Continue if t0 != v0");
                e.emit("beq $t0 $v0 " + targetLabel + "\n");
                break;
            case "<":
                e.emit("# Continue if t0 < v0");
                e.emit("bge $t0 $v0 " + targetLabel + "\n");
                break;
            case ">":
                e.emit("# Continue if t0 > v0");
                e.emit("ble $t0 $v0 " + targetLabel + "\n");
                break;
            case "<=":
                e.emit("# Continue if t0 <= v0");
                e.emit("bgt $t0 $v0 " + targetLabel + "\n");
                break;
            case ">=":
                e.emit("# Continue if t0 >= v0");
                e.emit("blt $t0 $v0 " + targetLabel + "\n");
                break;
        }
    }
}

package environment;

import ast.ProcedureDeclaration;

import java.util.HashMap;

/**
 * The Environment class consists of a variable map, which stores
 * every variable name and value that is available in its scope,
 * and a procedureMap, which stores each procedure name and a
 * pointer to its declaration.
 *
 * @author Chaitanya Ravuri
 * @version April 7, 2020
 */
public class Environment
{

    private HashMap<String, Integer> variableMap;
    private HashMap<String, ProcedureDeclaration> procedureMap;

    private Environment parent;

    /**
     * Environment constructor that consists of a
     * HashMap of variable names to their values
     *
     * @param variableMap the map of variable names and their values
     */
    public Environment(HashMap<String, Integer> variableMap,
                       HashMap<String, ProcedureDeclaration> procedureMap,
                       Environment parent)
    {
        this.variableMap = variableMap;
        this.procedureMap = procedureMap;
        this.parent = parent;
    }

    /**
     * Declares the given variable in the environment's
     * scope, associating it with a given value.
     *
     * @param variable the name of the variable
     * @param value    the value to be assigned to the variable
     */
    public void declareVariable(String variable, int value)
    {
        variableMap.put(variable, value);
    }

    /**
     * Associates the given variable name with the given
     * value if the variable is available in the environment's
     * scope. Otherwise, sets the variable in the global
     * environment.
     *
     * @param variable the name of the variable
     * @param value    the value to be assigned to the variable
     */
    public void setVariable(String variable, int value)
    {
        if (variableMap.containsKey(variable) || parent == null)
        {
            variableMap.put(variable, value);
        }
        else
        {
            parent.setVariable(variable, value);
        }
    }

    /**
     * Returns the value associated with the given variable name
     * if the variable is available in the environment's scope.
     * Otherwise, searches for the variable in the global environment.
     *
     * @param variable the name of the variable
     * @return the value assigned to that variable
     */
    public int getVariable(String variable)
    {
        if (variableMap.containsKey(variable))
        {
            return variableMap.get(variable);
        }
        else
        {
            return parent.getVariable(variable);
        }
    }

    /**
     * Associates the given procedure name with a pointer
     * to the procedure declaration in the global environment.
     *
     * @param proc the procedure name
     * @param decl the procedure declaration
     */
    public void setProcedure(String proc, ProcedureDeclaration decl)
    {
        if (parent == null)
        {
            procedureMap.put(proc, decl);
        }
        else
        {
            parent.setProcedure(proc, decl);
        }
    }

    /**
     * Returns the declaration of the given procedure
     * in the global environment.
     *
     * @param proc the procedure name
     * @return the declaration of the procedure
     */
    public ProcedureDeclaration getProcedure(String proc)
    {
        if (parent == null)
        {
            return procedureMap.get(proc);
        }
        else
        {
            return parent.getProcedure(proc);
        }

    }

}

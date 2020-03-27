package environment;

import java.util.HashMap;

/**
 * The Environment class consists of a variable map, storing
 * every variable name and value that is available in the scope
 * of the program.
 *
 * @author Chaitanya Ravuri
 * @version March 25, 2020
 */
public class Environment
{

    private HashMap<String, Integer> variableMap;

    /**
     * Environment constructor that consists of a
     * HashMap of variable names to their values
     *
     * @param variableMap the map of variable names and their values
     */
    public Environment(HashMap<String, Integer> variableMap)
    {
        this.variableMap = variableMap;
    }

    /**
     * Associates the given variable name with the given value
     *
     * @param variable the name of the variable
     * @param value    the value to be assigned to the variable
     */
    public void setVariable(String variable, int value)
    {
        variableMap.put(variable, value);
    }

    /**
     * Returns the value associated with the given variable name
     *
     * @param variable the name of the variable
     * @return the value assigned to that variable
     */
    public int getVariable(String variable)
    {
        return variableMap.get(variable);
    }

}

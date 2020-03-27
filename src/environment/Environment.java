package environment;
/*
 * Created by cravuri on 3/25/20
 */

import java.util.HashMap;

public class Environment
{

    private HashMap<String, Integer> variableMap;

    public Environment(HashMap<String, Integer> variableMap)
    {
        this.variableMap = variableMap;
    }

    //associates the given variable name with the given value
    public void setVariable(String variable, int value)
    {
        variableMap.put(variable, value);
    }
    //returns the value associated with the given variable
    //name
    public int getVariable(String variable)
    {
        return variableMap.get(variable);
    }

}

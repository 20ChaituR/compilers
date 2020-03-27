package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

public abstract class Expression
{

    public abstract int eval(Environment env);

}

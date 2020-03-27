package ast;
/*
 * Created by cravuri on 3/25/20
 */

import environment.Environment;

public abstract class Statement
{

    public abstract void exec(Environment env);

}

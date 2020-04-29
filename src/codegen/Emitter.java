package codegen;

import java.io.*;

public class Emitter
{
	private PrintWriter out;
	private int labelID = 0;

	//creates an emitter for writing to a new file with given name
	public Emitter(String outputFileName)
	{
		try
		{
			out = new PrintWriter(new FileWriter(outputFileName), true);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	//prints one line of code to file (with non-labels indented)
	public void emit(String code)
	{
		if (!code.endsWith(":"))
			code = "\t" + code;
		out.println(code);
	}

	public void emitPush(String reg)
	{
		emit("# Pushes " + reg + " onto the stack");
		emit("subu $sp $sp 4");
		emit("sw " + reg + " ($sp)\n");
	}

	public void emitPop(String reg)
	{
		emit("# Pops stack onto " + reg);
		emit("lw " + reg + " ($sp)");
		emit("addu $sp $sp 4\n");
	}

	public int nextLabelID()
	{
		return labelID++;
	}

	//closes the file.  should be called after all calls to emit.
	public void close()
	{
		out.close();
	}
}
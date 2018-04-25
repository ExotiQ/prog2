package ui.consoletest;

import ui.CommandHandle.CommandTypeInfo;

public enum MyFavoriteCommandType implements CommandTypeInfo {
	HELP("help", "  * list all commands", "help"),
	EXIT("exit", "  * exit program", "exitSystem"),
	ADDI("addi", "<param1>  <param2>   * simple integer add", "addIntegers", int.class, int.class),
	ADDF("addf", "<param1>  <param2>   * simple float add", "addFloats", float.class, float.class),
	ECHO("echo", "<param1>  <param2>   * echos param1 string param2 times","echo" , String.class, int.class)
	;
	
	private String name;
	private String helpText;
	private String methodToCall;
	private Class<?> [] paramTypes;
	
	private MyFavoriteCommandType(String name, String helpText, String methodToCall, Class<?>... params) {
		this.name = name;
		this.helpText = helpText;
		this.paramTypes = params;
		this.methodToCall = methodToCall;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getHelpText() {
		return this.helpText;
	}
	
	@Override
	public String getMethodToCall() {
		return this.methodToCall;
	}

	@Override
	public Class<?>[] getParamTypes() {
		return this.paramTypes;
	}
	
	@Override
	public String toString() {
		if(paramTypes.length == 0) {
			return this.name + this.helpText;
		} else {
			return this.name + " " + this.helpText + " param1 type: " + this.paramTypes[0] + " param2 type: " + this.paramTypes[1];
		}
	}
}

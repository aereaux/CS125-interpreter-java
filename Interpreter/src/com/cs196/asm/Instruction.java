package com.cs196.asm;

public class Instruction {
	
	private final String code;
	private final instructionTypes instruction;
	private final int[] args = new int[3];
	private int constarg;
	private Program program;
	
	
	public static enum instructionTypes {
		ZERO_REG, ADD, SUB, LOAD, STORE, BR
	}
	
	public Instruction(String originalCode, Program program) {
		code = originalCode;
		String[] codeArray = code.split("[\\. ]");
		switch (codeArray[0]) {
			case "zero_reg":
				instruction = instructionTypes.ZERO_REG;
				break;
			case "add":
				instruction = instructionTypes.ADD;
				break;
			case "sub":
				instruction = instructionTypes.SUB;
				break;
			case "load":
				instruction = instructionTypes.LOAD;
				break;
			case "store":
				instruction = instructionTypes.STORE;
				break;
			case "br":
				instruction = instructionTypes.BR;
				if(codeArray[1].contains("n")) args[0] = 1;
				if(codeArray[1].contains("z")) args[1] = 1;
				if(codeArray[1].contains("p")) args[2] = 1;
				constarg = Integer.parseInt(codeArray[2]);
				return;
			default:
				throw new Error();
		}
		for(int i = 1, count = 0; i < codeArray.length; i++) {
			if(codeArray[i].startsWith("r")) args[count] = Integer.parseInt(codeArray[i].substring(1));
			else {
				args[count] = 0;
				constarg = Integer.parseInt(codeArray[i]);
				assert(constarg >= 0);
			}
		}
	}
	
	public void execute() {
		switch(instruction) {
			case ZERO_REG:
				program.registers[args[0]] = 0;
				program.updateCodes(0);
				return;
			case ADD:
				int value;
				if(constarg >= 0) value = constarg;
				else value = program.registers[args[2]];
				program.registers[args[3]] = program.registers[args[0]] + value;
				return;
			case SUB:
				int value1;
				if(constarg >= 0) value1 = constarg;
				else value1 = program.registers[args[2]];
				program.registers[args[3]] = program.registers[args[0]] - value1;
				return;
			case LOAD:
				program.registers[args[0]] = program.memory.get(program.registers[args[1]] + constarg);
				return;
			case STORE:
				program.memory.set(program.registers[args[1]] + constarg, program.registers[args[0]]);
				return;
			case BR:
				if(intToBool(args[0]) == program.N || intToBool(args[1]) == program.Z || intToBool(args[2]) == program.P) {
					program.incrementPC(constarg - 1);
				}
				return;
		}
		program.incrementPC(1);
	}
	
	private boolean intToBool(int i) {
		if(i == 0) return false;
		else return true;
	}

}

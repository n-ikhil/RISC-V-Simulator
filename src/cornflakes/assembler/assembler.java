package assembler;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class assembler
	{
	/*
	 * creating supported registers array
	 * and converting into arraylist
	 */
	static  String[] register= new String[]{"x0","x1","x2","x3","x4","x5","x6","x7","x8","x9","x10","x11","x12","x13","x14","x15","x16","x17","x18","x19","x20","21","x22","x23","x24","x25","x26","x27","x28","x29","x30","x31"};
	static ArrayList<String> registers=(ArrayList<String>) Arrays.asList(register);
	/*
	 * wrapper class for labels
	 */
	static class label
	{
		int line;
		String name;
		label(int line,String name)
		{
			this.line=line;
			this.name=name;
		}
	}
	/*
	 * wrapper class for generic instruction
	 */

	static int line_no=0;
	static ArrayList<label>labels=new ArrayList<label>();
	static ArrayList<instruction>instructions=new ArrayList<instruction>();
	static void assemble() throws IOException
		{
		File file = new File("test.s");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
			while ((line = br.readLine()) != null)
				{
						LexicalAnalyser temp=new LexicalAnalyser(line);
						if(temp.islabel==true)
						{
							label temp1=new label(line_no,temp.label);
							labels.add(temp1);
							instruction.add_label(temp.label);
							if(temp.Tokens.size()==0)
							{
								line=br.readLine();
								LexicalAnalyser temp2=new LexicalAnalyser(line);
								parse_instruction(temp2);
							}
							
						}
						else
						{
							parse_instruction(temp);
						}
						line_no++;
				}
		}
	static void parse_instruction(LexicalAnalyser tokenlist)
	{
		String instr=tokenlist.Tokens.get(0);
		String temp=instruction_type(instr);
		if(temp==null)
		{
			System.out.println("no such instruction is found");
		}
		else
		{
			if(temp=="r")
			{
				temp=tokenlist.Tokens.get(1);
				int r1=registers.indexOf(temp);
				if(r1==-1)
				{
					System.out.println("no such register found");
				}
				temp=tokenlist.Tokens.get(2);
				int r2=registers.indexOf(temp);
				if(r2==-1)
				{
					System.out.println("no such register found");
				}
				temp=tokenlist.Tokens.get(3);
				int r3=registers.indexOf(temp);
				if(r3==-1)
				{
					System.out.println("no such register found");
				}
				instruction temp2=new instruction(instr,r1,r2,r3);
				instructions.add(temp2);
			}
			else if(temp=="i"||temp=="s")
			{
				temp=tokenlist.Tokens.get(1);
				int r1=registers.indexOf(temp);
				if(r1==-1)
				{
					System.out.println("no such register found");
				}
				temp=tokenlist.Tokens.get(2);
				int r2=registers.indexOf(temp);
				if(r2==-1)
				{
					try {
						   r2 = Integer.parseInt(temp);
						}
						catch (NumberFormatException e)
						{
							System.out.println("immediate value not accepted");
						}
				}
				temp=tokenlist.Tokens.get(3);
				int r3=registers.indexOf(temp);
				if(r3==-1)
				{
					try {
						   r3 = Integer.parseInt(temp);
						}
						catch (NumberFormatException e)
						{
							System.out.println("immediate value not accepted");
						}
				}
				instruction temp2=new instruction(instr,r1,r2,r3);
				instructions.add(temp2);
			}
		}
	}
	static String instruction_type(String instr)
	{
		switch (instr)
		{
		case "add":
			return "r";
		case "sub":
			return "r";
		case "sll":
			return "r";
		case "slt":
			return "r";
		case "sltu":
			return "r";
		case "xor":
			return "r";
		case "srl":
			return "r";
		case "sra":
			return "r";
		case "or":
			return "r";
		case "and":
			return "r";
		case "addw":
			return "r";
		case "subw":
			return "r";
		case "sllw":
			return "r";
		case "srlw":
			return "r";
		case "sraw":
			return "r";
		case "lb":
			return "i";
		case "lh":
			return "i";
		case "lw":
			return "i";
		case "ld":
			return "i";
		case "lbu":
			return "i";
		case "lhu":
			return "i";
		case "lwu":
			return "i";
		case "fence":
			return "i";
		case "fence.i":
			return "i";
		case "addi":
			return "i";
		case "slli":
			return "i";
		case "slti":
			return "i";
		case "sltiu":
			return "i";
		case "xori":
			return "i";
		case "srli":
			return "i";
		case "srai":
			return "i";
		case "ori":
			return "i";
		case "andi":
			return "i";
		case "addiw":
			return "i";
		case "slliw":
			return "i";
		case "srliw":
			return "i";
		case "sraiw":
			return "i";
		case "sd":
			return "i";
		case "jalr":
			return "i";
		case "sb":
			return "s";
		case "sh":
			return "s";
		case "sw":
			return "s";
		case "auipc":
			return "u";
		case "lui":
			return "u";
		case "beq":
			return "sb";
		case "bne":
			return "sb";
		case "blt":
			return "sb";
		case "bge":
			return "sb";
		case "bltu":
			return "sb";
		case "bgeu":
			return "sb";
		}
		return null;
	}
	}

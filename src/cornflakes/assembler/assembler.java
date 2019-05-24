package assembler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import assembler.primary_memory;
import java.util.HashMap;
import java.util.Map;

public class assembler {

    /*
		 * creating supported registers array and converting into arraylist
     */
    static String[] register = new String[]{"x0", "x1", "x2", "x3", "x4", "x5", "x6", "x7", "x8", "x9", "x10", "x11", "x12", "x13", "x14", "x15",
        "x16", "x17", "x18", "x19", "x20", "x21", "x22", "x23", "x24", "x25", "x26", "x27", "x28", "x29",
        "x30", "x31"};
    static List<String> registers = Arrays.asList(register);

    /*
		 * wrapper class for labels
     */
    class label {

        int line;
        String name;

        label(int line, String name) {
            this.line = line;
            this.name = name;
        }
    }
    /*
		 * wrapper class for generic instruction
     */

    int line_no = 0,pc = 0;
    ArrayList<label> labels = new ArrayList<label>();
    ArrayList<instruction> instructions = new ArrayList<instruction>();
    instruction[] instructions_temp;
    int data_address = 0x10000000;
    Map< String, Integer> data_map = new HashMap< String, Integer>();

    <instructions> void assemble(String file_location, primary_memory memory) throws IOException {
        File file = new File(file_location);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        if (".data".equals(line = br.readLine())) {

            line = br.readLine();
            while (!".text".equals(line)) {
                parse_directives(line, memory);
                line = br.readLine();
            }
            line = br.readLine();
        }
        //line =br.readLine();
        while (line != null) {
            if(line=="\n")
                continue;
            LexicalAnalyser temp = new LexicalAnalyser(line, false);
            if (temp.islabel == true) {
                label temp1 = new label(line_no, temp.label);
                labels.add(temp1);
                instruction.add_label(temp.label);
                if (temp.Tokens.size() == 0) {
                    line = br.readLine();
                    LexicalAnalyser temp2 = new LexicalAnalyser(line, false);
                    parse_instruction(temp2);
                    // continue;
                }

            } else if (temp.Tokens.size() != 0) {
                parse_instruction(temp);
            }
            line_no++;
            line = br.readLine();
        }
        instructions_temp = instructions.toArray(new instruction[instructions.size()]);
        instruction.assign_labels(instructions_temp);
        // instructions.removeAll(instruction);
    }

    void parse_directives(String line, primary_memory memory) {
        LexicalAnalyser tokenlist = new LexicalAnalyser(line, true);
        String label = tokenlist.Tokens.get(0);
        if (".word".equals(tokenlist.Tokens.get(1))) {
            data_map.put(label, new Integer(data_address));
            for (int i = 2; i < tokenlist.Tokens.size(); i++) {
            	if(data_address%4 == 0)
            	{
            		System.out.println(data_address+"fir");
            		memory.storeword(data_address, Integer.parseInt(tokenlist.Tokens.get(i)));
            		System.out.println(Integer.parseInt(tokenlist.Tokens.get(i)));
            		data_address += 4;
            	}
            	else
            	{
            		data_address = (data_address/4 + 1)*4;
            		System.out.println(data_address+"ro");
            		memory.storeword(data_address, Integer.parseInt(tokenlist.Tokens.get(i)));
            		data_address += 4;
            	}
            }

        }
        if (".byte".equals(tokenlist.Tokens.get(1))) {
            data_map.put(label, new Integer(data_address));
            for (int i = 2; i < tokenlist.Tokens.size(); i++) {
                memory.storeword(data_address, Integer.parseInt(tokenlist.Tokens.get(i)));
                data_address += 1;

            }

        }
    }

    void parse_instruction(LexicalAnalyser tokenlist) {
        String instr = tokenlist.Tokens.get(0);
        String temp = instruction_type(instr);
        if (temp == null) {
            System.out.println("no such instruction is found");
        } else {
            if (temp == "r") {
                temp = tokenlist.Tokens.get(1);
                int r1 = registers.indexOf(temp);
                if (r1 == -1) {
                    System.out.println("no such register found");
                }
                temp = tokenlist.Tokens.get(2);
                int r2 = registers.indexOf(temp);
                if (r2 == -1) {
                    System.out.println("no such register found");
                }
                temp = tokenlist.Tokens.get(3);
                int r3 = registers.indexOf(temp);
                if (r3 == -1) {
                    System.out.println("no such register found");
                }
                instruction temp2 = new instruction(instr, r1, r2, r3);
                instructions.add(temp2);
            } else if (temp == "i" || temp == "s") {
                temp = tokenlist.Tokens.get(1);
                int r1 = registers.indexOf(temp);
                if (r1 == -1) {
                    System.out.println("no such register found");
                }
                temp = tokenlist.Tokens.get(2);
                int r2 = registers.indexOf(temp);
                if (tokenlist.Tokens.size() == 3) {
                	r2 = 65536;
                    int addre = data_map.get(temp);
                    instr = "auipc";
                    int off = addre - (268435456 + pc);
                    instruction temp2 = new instruction(instr, r1, r2);
                    instructions.add(temp2);
                    instr = "lw";
                    r2 = off;
                    int r3 = r1;
                    System.out.println(instr+"-"+r1+"-"+r2+"-"+r3);
                    temp2 = new instruction(instr,r1,r2,r3);
                    instructions.add(temp2);
                    line_no++;
                    return;
                }
                if (r2 == -1) {
                    try {
                        r2 = Integer.parseInt(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("immediate value not accepted");
                    }
                }
                temp = tokenlist.Tokens.get(3);
                int r3 = registers.indexOf(temp);
                if (r3 == -1) {
                    try {
                        r3 = Integer.parseInt(temp);
                    } catch (NumberFormatException e) {
                        System.out.println("immediate value not accepted");
                    }
                }
                instruction temp2 = new instruction(instr, r1, r2, r3);
                instructions.add(temp2);
            } else if (temp == "u") {
                temp = tokenlist.Tokens.get(1);
                int r1 = registers.indexOf(temp);
                if (r1 == -1) {
                    System.out.println("no such register found");
                }
                temp = tokenlist.Tokens.get(2);
                int r2 = Integer.parseInt(temp);
                instruction temp2 = new instruction(instr, r1, r2);
                instructions.add(temp2);
            } else if (temp == "sb") {
                temp = tokenlist.Tokens.get(1);
                int r1 = registers.indexOf(temp);
                if (r1 == -1) {
                    System.out.println("no such register found");
                }
                temp = tokenlist.Tokens.get(2);
                int r2 = registers.indexOf(temp);
                if (r2 == -1) {
                    System.out.println("no such register found");
                }
                temp = tokenlist.Tokens.get(3);
                instruction temp2 = new instruction(instr, r1, r2, temp);
                instructions.add(temp2);
            } else if (temp == "uj") {

                temp = tokenlist.Tokens.get(1);
                int r1 = registers.indexOf(temp);
                if (r1 == -1) {
                    System.out.println("no such register found");
                }
                temp = tokenlist.Tokens.get(2);
                instruction temp2 = new instruction(instr, r1, temp);
                instructions.add(temp2);
            }
            else if(temp == "pi")
            {
            	switch(instr)
            	{
            	case "la":
            		String reg = tokenlist.Tokens.get(1);
                    int r1 = registers.indexOf(reg);
                    if (r1 == -1) {
                        System.out.println("no such register found");
                    }
                    String lab = tokenlist.Tokens.get(2);
                    System.out.println(lab);
                    if(data_map.get(lab)!=null)
                    {
                    	int addr = data_map.get(lab);
                    	System.out.println(addr+"this"+line_no);
                    	int pc = (line_no)*4;
                    	int offset = addr - (268435456 + pc);
                    	System.out.println(pc+"line"+addr);
                    	instr = "auipc";
                    	int r2 = 65536;
                    	instruction temp2 = new instruction(instr,r1,r2);
                    	instructions.add(temp2);
                    	instr = "addi";
                    	r2 = r1;
                    	int r3 = offset;
                    	System.out.println(instr+"-"+r1+"-"+r2+"-"+r3);
                    	temp2 = new instruction(instr,r1,r2,r3);
                    	instructions.add(temp2);
                    	line_no++;
                    	/*
                    	 * here goes the start address for the auipc of the la instruction
                    	 */
                    	
                    }
                    else
                    {
                    	System.out.println("no such label found 1");
                    }
                    
                    break;
                	
                case "li":
                	System.out.println("in correct");
                	 reg = tokenlist.Tokens.get(1);
                	 r1 = registers.indexOf(reg);
                	if(r1 == -1)
                	{
                		System.out.println("no such register found");
                	}
                	int r2 = 0;
                	 reg = tokenlist.Tokens.get(2);
                	int r3 = Integer.parseInt(reg);
                	instr = "addi";
                	instruction temp2 = new instruction(instr, r1, r2, r3);
                    instructions.add(temp2);
                	break;
                
                case "mv":
                	reg = tokenlist.Tokens.get(1);
               	 	r1 = registers.indexOf(reg);
               	 	if(r1 == -1)
               	 	{
               	 		System.out.println("no such register found");
               	 	}
               	 	reg = tokenlist.Tokens.get(2);
               	 	r2 = registers.indexOf(reg);
               	 	if(r2 == -1)
            	 	{
            	 		System.out.println("no such register found");
            	 	}
               	 	r3 = 0;
               	 	instr = "addi";
               	 	temp2 = new instruction(instr,r1,r2,r3);
               	 	instructions.add(temp2);
               	 	break;
                	
                case "neg":
                	reg = tokenlist.Tokens.get(1);
               	 	r1 = registers.indexOf(reg);
               	 	if(r1 == -1)
               	 	{
               	 		System.out.println("no such register found");
               	 	}
               	 	reg = tokenlist.Tokens.get(2);
               	 	r3 = registers.indexOf(reg);
            	 	if(r3 == -1)
            	 	{
            	 		System.out.println("no such register found");
            	 	}
            	 	r2 = 0;
            	 	instr = "sub";
            	 	temp2 = new instruction(instr,r1,r2,r3);
            	 	instructions.add(temp2);
            	 	break;
                	
                case "nop":
                	r1 = 0;
                	r2 = 0;
                	r3 = 0;
                	instr = "addi";
                	temp2 = new instruction(instr,r1,r2,r3);
                	instructions.add(temp2);
                	break;
                	
                case "not":
                	reg = tokenlist.Tokens.get(1);
               	 	r1 = registers.indexOf(reg);
               	 	if(r1 == -1)
               	 	{
               	 		System.out.println("no such register found");
               	 	}
               	 	reg = tokenlist.Tokens.get(1);
            	 	r2 = registers.indexOf(reg);
            	 	if(r2 == -1)
            	 	{
            	 		System.out.println("no such register found");
            	 	}
            	 	r3 = -1;
            	 	instr = "xori";
            	 	temp2 = new instruction(instr,r1,r2,r3);
            	 	instructions.add(temp2);
            	 	break;
            	 	
                	
                case "ret":
                	r1 = 0;
                	r2 = 1;
                	r3 = 0;
                	instr = "jalr";
                	temp2 = new instruction(instr,r1,r2,r3);
                	instructions.add(temp2);
                	break;
                	
                case "seqz":
                	reg = tokenlist.Tokens.get(1);
               	 	r1 = registers.indexOf(reg);
               	 	if(r1 == -1)
               	 	{
               	 		System.out.println("no such register found");
               	 	}
               	 	reg = tokenlist.Tokens.get(2);
            	 	r2 = registers.indexOf(reg);
            	 	if(r2 == -1)
            	 	{
            	 		System.out.println("no such register found");
            	 	}
            	 	r3 = 1;
            	 	instr = "sltiu";
            	 	temp2 = new instruction(instr,r1,r2,r3);
            	 	instructions.add(temp2);
            	 	break;
                	
                case "snez":
                	reg = tokenlist.Tokens.get(1);
               	 	r1 = registers.indexOf(reg);
               	 	if(r1 == -1)
               	 	{
               	 		System.out.println("no such register found");
               	 	}
               	 	r2 = 0;
               	 	reg = tokenlist.Tokens.get(1);
            	 	r3 = registers.indexOf(reg);
            	 	if(r3 == -1)
            	 	{
            	 		System.out.println("no such register found");
            	 	}
               	 	instr = "sltu";
               	 	temp2 = new instruction(instr,r1,r2,r3);
               	 	instructions.add(temp2);
               	 	break;
                	
                case "jr":
                	r1 = 0;
                	reg = tokenlist.Tokens.get(1);
               	 	r2 = registers.indexOf(reg);
               	 	if(r2 == -1)
               	 	{
               	 		System.out.println("no such register found");
               	 	}
               	 	r3 = 0;
               	 	instr = "jalr";
               	 	temp2 = new instruction(instr,r1,r2,r3);
               	 	instructions.add(temp2);
               	 	break;
                	
                case "j":
                	r1 = 0;
                	reg = tokenlist.Tokens.get(1);
                	instr = "jal";
                	temp2 = new instruction(instr,r1,reg);
                	instructions.add(temp2);
                	break;
                	
                case "bnez":
                	reg = tokenlist.Tokens.get(1);
               	 	r1 = registers.indexOf(reg);
               	 	if(r1 == -1)
               	 	{
               	 		System.out.println("no such register found");
               	 	}
               	 	r2 = 0;
               	 	reg = tokenlist.Tokens.get(2);
               	 	instr = "bne";
               	 	temp2 = new instruction(instr,r1,r2,reg);
               	 	instructions.add(temp2);
               	 	break;
                	
                case "beqz":
                	reg = tokenlist.Tokens.get(1);
               	 	r1 = registers.indexOf(reg);
               	 	if(r1 == -1)
               	 	{
               	 		System.out.println("no such register found");
               	 	}
               	 	r2 = 0;
               	 	reg = tokenlist.Tokens.get(2);
               	 	instr = "beq";
               	 	temp2 = new instruction(instr,r1,r2,reg);
               	 	instructions.add(temp2);
               	 	break;
                	
            	}
            }
        }
    }

    static String instruction_type(String instr) {
        switch (instr) {
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
            case "mul":

                return "r";
            case "div":
                //rformat("0110011", "100", "0000001", r1, r2, r3);
                return "r";
            case "rem":
                //rformat("0110011", "110", "0000001", r1, r2, r3);
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
            case "jal":
                return "uj";
            case "la":
            	return "pi";
            case "li":
            	return "pi";
            case "mv":
            	return "pi";
            case "neg":
            	return "pi";
            case "nop":
            	return "pi";
            case "not":
            	return "pi";
            case "ret":
            	return "pi";
            case "seqz":
            	return "pi";
            case "snez":
            	return "pi";
            case "jr":
            	return "pi";
            case "j":
            	return "pi";
            case "bnez":
            	return "pi";
            case "beqz":
            	return "pi";
        }
        return null;
    }

    public static void main(String file_location[]) throws IOException {
        /*  assembler obj =new assembler();
				try
					{
						obj.assemble("C:\\Users\\ramak\\Desktop\\Risc v\\RISC-V-Simulator\\src\\cornflakes\\assembler\\test.s");
					} catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				// PrintWriter p=null;
				PrintWriter p = new PrintWriter(
						"output.mc");
				for (int i = 0; i < obj.instructions_temp.length; i++)
					{
						Long decimal = Long.parseLong(obj.instructions_temp[i].binary, 2);
						// String hexStr = Integer.toString(decimal, 16);
						// System.out.println(decimal.toHexString());
						// System.out.println(String.format("0x%08X", decimal));
						// PrintWriter writer = nul
						p.println(String.format("0x%08X", decimal));
						 p.flush();

					}
                                System.out.println(obj.memory.memory[100000]);
                                System.out.println(obj.memory.memory[100004]);
                                
                                int a=obj.memory.loadword(100000);
                                p.close();
                                System.out.println(a);*/
    }
}

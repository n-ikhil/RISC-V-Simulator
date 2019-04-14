package datapath;
import assembler.primary_memory;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


public class datapath
{

	static boolean check(String inp)
	{
		String temp_comp="00000000000000000000000000000000";
		if( !inp.equals(temp_comp) ) return true;//correct condition
		return false;// not correct instruction
	}

	public static boolean fetch(primary_memory mem,instructions[] instr_que)
	{



			// move by one logic


		//  RISCV HARDWARE LEVEL FETCH START
		mem.ir = mem.loadwordstr(mem.pc);
		mem.pc = mem.pc + 4;
		//	RISCV HARDWARE LEVEL FETCH END

		if(check(mem.ir))//checks if the instrucition is null
		{
			instr_que[0]=new instructions();
		}
		else instr_que[0]=null;
		{
			instr_que[4]=instr_que[3];
			instr_que[3]=instr_que[2];
			instr_que[2]=instr_que[1];
			instr_que[1]=instr_que[0];
			instr_que[0]=null;
		}
		int empty_instr=0;
		for(instructions i: instr_que)
		{
			if(i==null) empty_instr++;
		}
		if(empty_instr==5) return true;
		return false;
	}

	public static void decode(primary_memory mem , instructions[] instr_que)
	{
		if(instr_que[1]==null) return;
		instructions obj=new instructions(mem);
		instr_que[1]=obj;
		System.out.println(obj.instruct+"at decode");
		// 	RISCV HARDWARE LEVEL DECODE START
		if (obj.rs1 >= 0 && obj.rs1 < 32)
                mem.ra = mem.register[obj.rs1];// data forwarding implement here
		if (obj.rs2 >= 0 && obj.rs2 < 32)
							mem.rb = mem.register[obj.rs2];//data forwarding implenent here
		mem.iv = obj.iv;// here also

		// RISCV HARDWARE LEVEL DECODE STAR

	}
	public static void execute(primary_memory mem , instructions[] instr_que)
	{
		if(instr_que[2]==null) return ;
		mem.rm = mem.rb;// data forwarding exectute here
		instructions obj=instr_que[2];
		// RISCV HARDWARE LEVEL ALU START

		switch (obj.type)
		{

		case 1:
			alu.executer(obj.id, mem.ra, mem.rb); // R-type
			mem.rx = alu.output;
			break;

		case 2:
			alu.executei(obj.id, mem.ra, mem.iv); // I-type
			int k = alu.output;
			if (obj.id == 50) // jalr
			{
				mem.rx = mem.pc;
				mem.pc = k;
			}
			else
			{
				mem.rx = k;
			}

			break;

		case 3:
			alu.executeS(obj.id, mem.ra, mem.iv); // S-type
			mem.rx = alu.output;
			break;

		case 5:
			alu.executeu(obj.id, mem.ra, mem.iv); // U-type
			mem.rx = alu.output;
			break;

		case 6:
			alu.executeuj(obj.id, mem.ra, mem.iv); // UJ-type
			mem.rx = mem.pc;
			mem.pc = mem.pc + mem.iv * 2 - 4;
			break;

		case 4:
			alu.executesb(obj.id, mem.ra, mem.rb, mem.iv); // SB-type
			mem.rx = alu.output;
			if (mem.rx == 1) {
				mem.pc = mem.iv * 2 + mem.pc - 4;
			}
			break;

		}

		// RISCV HARDWARE LEVEL ALU END
	}

	public static void memory(primary_memory mem , instructions[] instr_que)
	{
		if(instr_que[3]==null) return;
		instructions obj = instr_que[3];

		switch (obj.mem_switch)
		{
			case 1: // write to reg without memeory access;
			{
				mem.ry = mem.rx;

				break;
			}
			case 2: // write to memory
			{
				switch (obj.id)
				{

				case 24:
					mem.storeword(mem.rx, mem.rm);
					break;// change for half and byte;

				case 25:
					mem.storehalf(mem.rx, mem.rm);
					break;

				case 26:
					mem.storeword(mem.rx, mem.rm);
					break;

				}

				break;
			}
			case 3:// read from memory
			{
				switch (obj.id) {

				case 1:
					mem.ry = mem.loadbyte(mem.rx);
					break;

				case 2:
					mem.ry = mem.loadhalf(mem.rx);
					break;

				case 3:
					mem.ry = mem.loadword(mem.rx);
					break;

				}

				break;
			}
			case 4:
			{
				mem.ry = mem.rx;
				break;
			}
			case 5:
			{
				mem.ry = mem.rx;
				break;
			}

		}

		// HARDWARE LEVEL MEMORY END
	}

	public static void write(primary_memory mem , instructions[] instr_que)
	{
		System.out.println("Writing");


		instructions obj=instr_que[4];

		if(obj==null) return ;

		System.out.println(mem.ry+">");
		//instructions obj = instr_que[4];
		// HARDWARE LEVEL WRITE START
		if (obj.mem_switch == 1 || obj.mem_switch == 3 || obj.mem_switch == 5)
			mem.register[obj.rd] = mem.ry;
		if (obj.mem_switch == 4 && obj.id == 51)
			mem.register[obj.rd] = mem.ry;
		// HARDWARE LEVEL WRITE END
		obj.retired = true;
		mem.register[0] = 0;
		return ;
	}


	public static void run(primary_memory mem,boolean piplined)
	{
		Scanner s=new Scanner(System.in);
		mem.pc=0;
		instructions[] instr_que=new instructions[5];
			for(instructions i:instr_que)i=null;

		while(true)
		{
			System.out.println("instr_que");
			for(int n=0;n<5;n++)
			{
				System.out.print(n+":");
				if(instr_que[n]!=null) System.out.print(instr_que[n].instruct+":");
				System.out.println();
			}
			System.out.println("instr_que_end");

		// to handle no instruction condition



		 write   (mem,instr_que);
		 memory (mem,instr_que);
		 execute(mem,instr_que);
		 decode (mem,instr_que);
		 if(fetch(mem,instr_que)) break;
			//int t=s.nextInt();

		}
	}

}

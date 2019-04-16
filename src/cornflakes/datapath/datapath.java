package datapath;
import assembler.primary_memory;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;



public class datapath
{

	//knobs and stats
	Map <Integer,branchobject> branchbuffer= new HashMap<>();
<<<<<<< HEAD

=======
	
>>>>>>> 504a610fcbdb38b20b2daa70d92619f97ceb7d92
	public int no_of_stalls_data_hazards,no_of_stalls_ctrl_hazards,no_of_cycles,no_of_instructions,
	no_of_data_hazards,no_of_ctrl_hazards,no_of_branch_mispredictions,no_of_ctrl_instructions
	,no_of_alu_instructions,no_of_data_transfer_instructions,no_of_stalls;

	double cpi;

	public boolean stall_decode,pipelined,data_forwarding,disable_writing_to_registers,disable_writing_to_pipelined_regs,watch_pipline_reg;

	int stalls;// this is nothing related with number of stalls
	

	public datapath()
	{
		no_of_stalls=0;
		no_of_cycles=0;
		no_of_branch_mispredictions=0;
		no_of_data_hazards=0;
		no_of_ctrl_hazards=0;
		no_of_instructions=0;
		no_of_alu_instructions=0;
		no_of_ctrl_instructions=0;
		no_of_stalls_ctrl_hazards=0;
		no_of_stalls_data_hazards=0;
		no_of_data_transfer_instructions=0;
		cpi=0;
		pipelined=false;
		data_forwarding=true;
		disable_writing_to_registers=false;
		disable_writing_to_pipelined_regs=false;
		watch_pipline_reg=false;
		stall_decode=false;
	}

	 boolean check(String inp)
	{
		String temp_comp="00000000000000000000000000000000";
		if( !inp.equals(temp_comp) ) return true;//correct condition
		return false;// not correct instruction
	}


	public  boolean fetch(primary_memory mem,instructions[] instr_que)
	{

		System.out.println("fetch stage");
		int empty_instr=0;
		
		//condition for piplined
		for(instructions i: instr_que)
		{
			if(i==null) empty_instr++;
		}
		//  RISCV HARDWARE LEVEL FETCH START
		mem.irt = mem.loadwordstr(mem.pc);
		mem.pc = mem.pc + 4;
		//	RISCV HARDWARE LEVEL FETCH END
			
		
		if(check(mem.irt) )//checks if the instrucition is null
		{
			if(empty_instr==4 && !pipelined) {instr_que[0]=null;mem.pc=mem.pc-4;return true;}
			instr_que[0]=new instructions();return false;
		}
		else instr_que[0]=null;
		return false;
	}
	public  void detect_hazard(instructions[] instr_que)
	{
		int rd1=-100,rd2=-200,rs1=-300,rs2=-400;
		if(instr_que[2]!=null)
		{
			rd1=instr_que[2].rd;
		}

		if(instr_que[3]!=null)
		{
			rd2=instr_que[3].rd;
		}
		if(instr_que[1]!=null)
		{
			rs1=instr_que[1].rs1;
			rs2=instr_que[1].rs2;
		}
		//System.out.println("rs1 : "+rs1+",rs2"+rs2+",rd1 :"+rd1+",rd2"+rd2);
		{
			if(rs1==rd1)
				{
					instr_que[1].hazard_type_rs1=1;
					if(instr_que[2].mem_switch==3) instr_que[1].hazard_type_rs1=3;
				}
			else if(rs1==rd2) instr_que[1].hazard_type_rs1=2;
			else instr_que[1].hazard_type_rs1=0;
		}
		//switch(rs2)
		{
			if(rs2==rd1)
				{
					instr_que[1].hazard_type_rs2=1;
					if(instr_que[2].mem_switch==3) instr_que[1].hazard_type_rs2=3;
				}
			else if(rs2==rd2) instr_que[1].hazard_type_rs2=2;
			else instr_que[1].hazard_type_rs2=0;
		}

		if(instr_que[1].hazard_type_rs2!=0 && instr_que[1].hazard_type_rs2!=3)
			no_of_data_hazards++;
		if(instr_que[1].hazard_type_rs1!=0 && instr_que[1].hazard_type_rs1!=3)
			no_of_data_hazards++;



		return;
	}


	@SuppressWarnings("null")
	public  void decode(primary_memory mem , instructions[] instr_que)
	{

		if(instr_que[1]==null ) return;
		if(stall_decode) {stall_decode=false;return;}
		System.out.println("at decode");
		
		instructions obj=new instructions(mem);
		instr_que[1]=obj;
		if(obj.type==4)
		{
			branchobject bo = null;
			if(branchbuffer.get(mem.pc)==null)
			{
				bo.branchaddress=mem.pc;
				bo.targetaddress=mem.iv;
				bo.prediction=false;
				bo.is_there=false;
				branchbuffer.put(mem.pc,bo);
			}
		}
		detect_hazard(instr_que);
		// handling the nop hazards below
		if(obj.hazard_type_rs1==3 || obj.hazard_type_rs2==3)
		{
			stalls=1;
			System.out.println("stalled as data hazard");
			no_of_stalls++;
			no_of_stalls_data_hazards++;
		}
		// handled the nop hazards
		// 	RISCV HARDWARE LEVEL DECODE START
		if (obj.rs1 >= 0 && obj.rs1 < 32)
                mem.rat = mem.register[obj.rs1];// data forwarding implement here
		if (obj.rs2 >= 0 && obj.rs2 < 32)
							mem.rbt = mem.register[obj.rs2];//data forwarding implenent here
		mem.ivt = obj.iv;// here also

		// RISCV HARDWARE LEVEL DECODE STAR

	}

	public  void execute(primary_memory mem , instructions[] instr_que)
	{

		if(instr_que[2]==null) return ;
		System.out.println("at execute");

		mem.rmt = mem.rb;

		int ra,rb;
		instructions obj=instr_que[2];

		switch(obj.hazard_type_rs1)
		{
			case 1: ra=mem.rx;break;
			case 2: ra=mem.ry;break;
			case 3: ra=mem.ry;break;
			default: ra=mem.ra;
		}
		switch(obj.hazard_type_rs2)
		{
			case 1: rb=mem.rx;break;
			case 2: rb=mem.ry;break;
			case 3: rb=mem.ry;break;
			default: rb=mem.rb;
		}
		if(!data_forwarding)
		{
			rb=mem.ra;
			rb=mem.rb;
		}
		System.out.println(obj.instruct+"  :ra :"+ra+", rb :"+rb+"type rs1"+obj.hazard_type_rs1+"ry:"+mem.ry);


		;// data forwarding exectute here

		// RISCV HARDWARE LEVEL ALU START

		switch (obj.type)
		{


		case 1:
			alu.executer(obj.id, ra, rb); // R-type
			mem.rxt = alu.output;
			break;

		case 2:
			alu.executei(obj.id, ra, mem.iv); // I-type
			int k = alu.output;
			if (obj.id == 50) // jalr
			{
				mem.rxt = mem.pc;
				mem.pc = k;
			}
			else
			{
				mem.rxt = k;
			}

			break;

		case 3:
			alu.executeS(obj.id, ra, mem.iv); // S-type
			mem.rxt = alu.output;
			break;

		case 5:
			alu.executeu(obj.id, ra, mem.iv); // U-type
			mem.rxt = alu.output;
			break;

		case 6:
			alu.executeuj(obj.id, ra, mem.iv); // UJ-type
			mem.rxt = mem.pc;
			mem.pc = mem.pc + mem.iv * 2 - 4;
			break;

		case 4:
			alu.executesb(obj.id, ra, rb, mem.iv); // SB-type
			mem.rxt = alu.output;
			if (mem.rxt == 1) {
				mem.pc = mem.iv * 2 + mem.pc - 4;
			}
			break;

		}

		// RISCV HARDWARE LEVEL ALU END
	}

	public  boolean memory(primary_memory mem , instructions[] instr_que)

	{
		System.out.println("at memory");


		instructions obj = instr_que[3];
		if(instr_que[4]!=null)
			no_of_instructions++;
		{
			instr_que[4]=instr_que[3];
			instr_que[3]=instr_que[2];
			if(stalls==0)
			{
				instr_que[2]=instr_que[1];
				instr_que[1]=instr_que[0];
			}
			else
			{
				stalls--;
				stall_decode=true;
				instr_que[2]=null;
				instr_que[0]=null;
				mem.pc=mem.pc-4;
			}
		}


		int empty_instr=0;
		for(instructions i: instr_que)
		{
			if(i==null) empty_instr++;
		}
		if(obj==null && empty_instr==5)
		{
			toggle(mem);
		  return true;
		}
		if(obj==null)
		{
			toggle(mem);
 		 	return false;
		}
		switch (obj.mem_switch)
		{
			case 1: // write to reg without memeory access;
			{
				mem.ryt = mem.rx;

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
					mem.ryt = mem.loadbyte(mem.rx);
					break;

				case 2:
					mem.ryt = mem.loadhalf(mem.rx);
					break;

				case 3:
					mem.ryt = mem.loadword(mem.rx);
					break;

				}

				break;
			}
			case 4:
			{
				mem.ryt = mem.rx;
				break;
			}
			case 5:
			{
				mem.ryt = mem.rx;
				break;
			}

		}

		// HARDWARE LEVEL MEMORY END
		toggle(mem);

		return false;
	}
	public  void toggle(primary_memory mem)
	{
		if(!disable_writing_to_pipelined_regs)
		{
			mem.ra=mem.rat;
			mem.rb=mem.rbt;
			mem.iv=mem.ivt;
			mem.rx=mem.rxt;
			mem.ry=mem.ryt;
			mem.rm=mem.rmt;
<<<<<<< HEAD

=======
			mem.ir=mem.irt;
>>>>>>> 504a610fcbdb38b20b2daa70d92619f97ceb7d92
		}
		if(change_ir)
		mem.ir=mem.irt;
			// if(stalls==0)
			//
			// else stalls--;
	}

	public  void write(primary_memory mem , instructions[] instr_que)
	{



		instructions obj=instr_que[4];
		if(obj==null) return;
		System.out.println("at write");
		//System.out.println("Writing");

		//System.out.println(mem.ry+">");
		//instructions obj = instr_que[4];
		// HARDWARE LEVEL WRITE START
		if (obj.mem_switch == 1 || obj.mem_switch == 3 || obj.mem_switch == 5 && !disable_writing_to_registers)
		{
			if(obj.rd>=0 && obj.rd<32)
			mem.register[obj.rd] = mem.ry;
		}

		if (obj.mem_switch == 4 && obj.id == 51 && !disable_writing_to_registers)
		{
			if(obj.rd>=0 && obj.rd<32)
			mem.register[obj.rd] = mem.ry;
		}
		// HARDWARE LEVEL WRITE END
		mem.register[0] = 0;
		return ;
	}


	public  void run(primary_memory mem,boolean pip)
	{

		mem.pc=0;
		boolean flag;
		instructions[] instr_que=new instructions[5];
		for(instructions i:instr_que)i=null;
<<<<<<< HEAD
		pipelined=pip;
=======
		pipelined=true;
>>>>>>> 504a610fcbdb38b20b2daa70d92619f97ceb7d92
		while(true)
		{
		 flag=fetch (mem,instr_que);
		 write  (mem,instr_que);
		 decode (mem,instr_que);
		 execute(mem,instr_que);
		 no_of_cycles++;

		 print_que(instr_que);
		 if(memory (mem,instr_que) && !flag)break;

		}
		calculate_data();
		print_summary();
	}
	void calculate_data()
	{
		cpi=(double)no_of_cycles/(double)no_of_instructions;
	}
	void print_que(instructions[] instr_que)
	{
		System.out.println("<<<<<<<<<<<<");
		for(int i=0;i<5;i++)
		{
			System.out.print(i+" :");
			if(instr_que[i]!=null)
			System.out.print(instr_que[i].instruct);
			System.out.println();
		}
		System.out.println("<<<<<<<<<<<<");
	}
	void print_summary()
	{
		System.out.println("\nno_of_stalls  :"+no_of_stalls+"\nno_of_cycles  :"+no_of_cycles+
		"\nno_of_branch_mispredictions  :"+no_of_branch_mispredictions+"\nno_of_data_hazards:  "+no_of_data_hazards+
		"\nno_of_ctrl_hazards:  "+no_of_ctrl_hazards+"\nno_of_instructions  :"+no_of_instructions+
		"\nno_of_alu_instructions  :"+no_of_alu_instructions+"\nno_of_ctrl_instructions:  "+no_of_ctrl_instructions+
		"\nno_of_stalls_ctrl_hazards  :"+no_of_stalls_ctrl_hazards+"\nno_of_stalls_data_hazards  :"+no_of_stalls_data_hazards+
		"\nno_of_data_transfer_instructions  :"+no_of_data_transfer_instructions+"\ncpi  :"+cpi);
	}

}

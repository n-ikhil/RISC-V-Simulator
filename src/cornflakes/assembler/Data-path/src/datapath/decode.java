package datapath;

import datapath.register;


public class decode {

	String array;
	int id ;
	int r1 ;
	int r2;
	int rd;
	int imm;
	

	decode(String A)
	{
		String array = A;
		
		String opcode = array.substring(0, 6);
		String fun3 = array.substring(12, 14);
		String fun7 = array.substring(25, 31);
		String imm = array.substring(20, 31);
		
		switch(opcode)
		{
		case "0000011" : switch(fun3)
						{
							case "000" : //lb instruction
			
							case "001" : // lh instruction
			
							case "010" : // lw 
			
							case "011" : // ld
			
							case "100" : // lbu
			
							case "101" : // lhu
			
							case "110" : // lwu
			
						}
		
		case "0001111" : switch(fun3)
						{
							case "000" : // fence
								
							case "001" : // fence.i
						}
		
		case "0010011" : switch(fun3)
						{
							case "000" : // addi
								
							case "001" : // slli
								
							case "010" : // slti
								
							case "011" : // sltiu
								 
							case "100" : // xori
								
							case "101" : switch(fun7)
										{
											case "0000000" : // srli
												
											case "0100000" : // srai
												
										}
								
							case "110" : // ori
								 
							case "111" : // andi
								
						}
		
		case "0010111" : // auipc
			
		case "0011011" : switch(fun3)
						{
							case "000" : // addiw
								
							case "001" : // slliw
								
							case "101" : switch(fun7)
										{
											case "0000000" : // srliw
											
											case "0100000" : // sraiw
										}
						}
		
		case "0100011" : switch(fun3)
						{
							case "000" : // sb
								
							case "001" : // sh
								
							case "010" : // sw
								 
							case "011" : // sd
								
						}
		
		case "0110011" : switch(fun3)
						{
							case "000" : switch(fun7)
										{
											case "0000000" : // add
							 
											case "0100000" : // sub
										}
								
							case "001" : // sll
								
							case "010" : // slt
								 
							case "011" : // sltu
								
							case "100" : // xor
								
							case "101" :  switch(fun7)
										{
											case "0000000" : // srl
							
											case "0100000" :// sra
										}
								
							case "110" : // or
								
							case "111" : // and
						}
		case "0110111" : // lui
			
		case "0111011" :  switch(fun3)
						{
							case "000" : switch(fun7)
										{
											case "0000000" : // addw
		
											case "0100000" : // subw
										}
			
							case "001" : // sllw
			
							case "101" : switch(fun7)
										{
											case "0000000" : // srlw
						
											case "0100000" : // sraw
										}
						}
		
		case "1100011" : switch(fun3)
						{
							case "000" : // beq
								
							case "001" : // bne
								
							case "100" : // blt
								
							case "101" : // bge
								
							case "110" : // bltu
								
							case "111" : // bgeu
						}
		
		case "1100111" : // jalr
			
		case "1101111" : // jal
			
		case "1110011" :switch(fun3)
						{
							case "000" : switch(imm)
										{
											case "000000000000" : // ecall
												
											case "000000000001" : // ebreak
										}
								
							case "001" : // CSRRW
								
							case "010" : // CSRRS
								
							case "011" : // CSRRC
								
							case "101" : // CSRRWI
								
							case "110" : // CSRRSI
								
							case "111" : // CSRRCI
						}
		
		}
	}
	
}

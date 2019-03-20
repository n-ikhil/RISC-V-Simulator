package datapath;

import datapath.register;


public class decode {

	String array;
	int type ;
	int id;
	int rs1 ;
	int rs2;
	int rd;
	int imm;
	int rs1value;
	int rs2value;
	

	decode(String A,register[] REG)
	{
		String array = A;
		
		String opcode = array.substring(0, 6);
		String fun3 = array.substring(12, 14);
		String rds,rs1s,imms,rs2s,imms1,imms2,fun7s;
		String fun7 = array.substring(25, 31);
		String immidiate = array.substring(20, 31);
	
		switch(opcode)
		{
		// R TYPE-1
		// I TYPE-2
		// S TYPE -3
		// SB TYPE - 4
		// U TYPE - 5
		// UJ TYPE 6
		case "0000011" : switch(fun3)
						{
							case "000" ://lb instruction
								id=1;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
								
								
							case "001" : // lh instruction
								id=2;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
								
			
							case "010" : // lw 
								id=3;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
			
							case "011" : // ld
								id=4;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
			
							case "100" : // lbu
								id=5;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "101" : // lhu
								id=6;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "110" : // lwu
								id=7;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
						}
		
		case "0001111" : switch(fun3)
						{
							case "000" : // fence
								id=8;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
								
							case "001" : // fence.i
								id=9;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
						}
		
		case "0010011" : switch(fun3)
						{
							case "000" : // addi
								id=10;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "001" : // slli
								id=11;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "010" : // slti
								id=12;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "011" : // sltiu
								id=13;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "100" : // xori
								id=14;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "101" : switch(fun7)
										{
											case "0000000" : // srli
												id=15;
												
											case "0100000" : // srai
												id=16;
												
										}
								
							case "110" : // ori
								id=17;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "111" : // andi
								id=18;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
						}
		
		case "0010111" : // auipc
			id=19;
			type=5; //U
			rds= array.substring(7,11);
			rd=Integer.parseInt(rds,2);
			imms= array.substring(12 ,31);
			imm=Integer.parseInt(imms,2);
			
		case "0011011" : switch(fun3)
						{
							case "000" : // addiw
								id=20;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
								
							case "001" :
								id=21;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);// slliw
								
							case "101" : switch(fun7)
										{
											case "0000000" : // srliw
												id=22;
											
											case "0100000" : // sraiw
												id=23;
										}
						}
		
		case "0100011" : switch(fun3)
						{
							case "000" : // sb
								id=24;
								type=3; //S
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s=array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								imms1= array.substring(7 , 11);
								imms2=array.substring(25 ,31);
								imms=imms2.concat(imms1);
								imm=Integer.parseInt(imms,2);
							case "001" : // sh
								id=25;
								type=3; //S
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s=array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								imms1= array.substring(7 , 11);
								imms2=array.substring(25 ,31);
								imms=imms2.concat(imms1);
								imm=Integer.parseInt(imms,2);
							case "010" : // sw
								id=26;
								type=3; //S
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s=array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								imms1= array.substring(7 , 11);
								imms2=array.substring(25 ,31);
								imms=imms2.concat(imms1);
								imm=Integer.parseInt(imms,2);
							case "011" : // sd
								id=27;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
						}
		
		case "0110011" : switch(fun3)
						{
							case "000" : 
								
								switch(fun7)
										{
											case "0000000" : // add
												id=28;
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
											case "0100000" : // sub
												id=29;
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
										}
								
							case "001" : // sll
								id=30;
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								
							case "010" : // slt
								id=31;
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
							case "011" : // sltu
								id=32;
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
							case "100" : // xor
								id=33;
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
							case "101" :  switch(fun7)
										{
											case "0000000" : // srl
												id=34;
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
											case "0100000" :// sra
												id=35;
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
										}
							
								
							case "110" : // or
								id=36;
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
							case "111" : // and
								id=37;
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
						}
		
		case "0110111" : // lui
			id=38;
			type=5; //U
			rds= array.substring(7,11);
			rd=Integer.parseInt(rds,2);
			imms= array.substring(12 ,31);
			imm=Integer.parseInt(imms,2);
			
		case "0111011" :  switch(fun3)
						{
							case "000" : switch(fun7)
										{
											case "0000000" : // addw
												id=39;
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
											case "0100000" : // subw
												id=40;
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
										}
			
							case "001" : // sllw
								id=41;
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
							case "101" : switch(fun7)
										{
											case "0000000" : // srlw
												id=42;
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
											case "0100000" : // sraw
												id=43;
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
										}
						}
		
		case "1100011" : switch(fun3)
						{
							case "000" : // beq
								id=44;
								type=4;
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;                                   //to  be continued
								String temp1 = array.substring(7, 11);
								String temp2 = array.substring(25,31);
								String t1=temp1.substring(0,3); //1-4
								String t3=temp1.substring(4); //11
								String t2= temp2.substring(1,6); //10-5
								String t4=temp2.substring(0); //12
								String t5=t4.concat(t3); 
							case "001" : // bne
								id=45;
								
							case "100" : // blt
								id=46;
								
							case "101" : // bge
								id=47;
							case "110" : // bltu
								id=48;
							case "111" : // bgeu
								id=49;
						}
		
		case "1100111" : // jalr
			id=50;
			type=2;
			rds= array.substring(7,11);
			rd=Integer.parseInt(rds,2);
			rs1s= array.substring(15,19);
			rs1=Integer.parseInt(rs1s,2);
			rs1value=REG[rs1].x;
			imms= array.substring(20 ,31);
			imm=Integer.parseInt(imms,2);	
		case "1101111" : // jal
			id=51;
		case "1110011" :switch(fun3)
						{
							case "000" : switch(immidiate)
										{
											case "000000000000" : // ecall
												id=52;
												type=2;
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
											case "000000000001" : // ebreak
												id=53;
												type=2;
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
										}
								
							case "001" : // CSRRW
								id=54;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "010" : // CSRRS
								id=55;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "011" : // CSRRC
								id=56;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "101" : // CSRRWI
								id=57;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "110" : // CSRRSI
								id=58;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
							case "111" : // CSRRCI
								id=59;
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								imm=Integer.parseInt(imms,2);
						}
		
		}
	}


	public int typereturn() {
		// TODO Auto-generated method stub
		return type;
	}


	public int rs1return() {
		// TODO Auto-generated method stub
		return rs1value;
	}


	public int rs2return() {
		// TODO Auto-generated method stub
		return rs2value;
	}


	public int rdreturn() {
		// TODO Auto-generated method stub
		return rd;
	}


	public int idreturn() {
		// TODO Auto-generated method stub
		return id;
	}
	
}

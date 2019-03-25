package datapath;

import datapath.register;
import java.util.*;
import java.io.File;


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
	register[] REG;
	static String findTwoscomplement(StringBuffer str) 
    { 
        int n = str.length(); 
       
        // Traverse the string to get first '1' from 
        // the last of string 
        int i; 
        for (i = n-1 ; i >= 0 ; i--) 
            if (str.charAt(i) == '1') 
                break; 
       
        // If there exists no '1' concat 1 at the 
        // starting of string 
        if (i == -1) 
            return "1" + str; 
       
        // Continue traversal after the position of 
        // first '1' 
        for (int k = i-1 ; k >= 0; k--) 
        { 
            //Just flip the values 
            if (str.charAt(k) == '1') 
                str.replace(k, k+1, "0"); 
            else
                str.replace(k, k+1, "1"); 
        } 
       
        // return the modified string 
        return str.toString(); 
    } 

	decode(String A,register[] REG1)
	{
		String array = A;
		REG = REG1;
		String opcode = array.substring(0, 7);
		String fun3 = array.substring(12, 15);
		String rds,rs1s,imms,rs2s,imms1,imms2;
		String fun7 = array.substring(25, 31);
		String immidiate = array.substring(20, 30);
		String sign = array.substring(31);
		
		switch(opcode)
		{
		// R TYPE-1
		// I TYPE-2
		// S TYPE -3
		// SB TYPE - 4
		// U TYPE - 5
		// UJ TYPE 6
		case "0000011" : 
						switch(fun3)
						{
							case "000" ://lb instruction
								id=1;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(s,2); 
								}
								
								
							case "001" : // lh instruction
								id=2;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
								
			
							case "010" : // lw 
								id=3;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
								
			
							case "011" : // ld
								id=4;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
			
							case "100" : // lbu
								id=5;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "101" : // lhu
								id=6;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "110" : // lwu
								id=7;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
						}
		
		case "0001111" : switch(fun3)
						{
							case "000" : // fence
								id=8;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
								
							case "001" : // fence.i
								id=9;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
						}
		
		case "0010011" : switch(fun3)
						{
							case "000" : // addi
								id=10;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "001" : // slli
								id=11;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "010" : // slti
								id=12;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "011" : // sltiu
								id=13;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "100" : // xori
								id=14;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "101" : switch(fun7)
										{
											case "0000000" : // srli
												id=15;
												System.out.print(id);
												type=2;
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												imms= array.substring(20 ,24);
												imm=Integer.parseInt(imms,2);
												
												
											case "0100000" : // srai
												id=16;
												System.out.print(id);
												type=2;
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												imms= array.substring(20 ,24);
												imm=Integer.parseInt(imms,2);
												
										}
								
							case "110" : // ori
								id=17;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "111" : // andi
								id=18;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
						}
		
		case "0010111" : // auipc
			id=19;
			System.out.print(id);
			type=5; //U
			rds= array.substring(7,11);
			rd=Integer.parseInt(rds,2);
			imms= array.substring(12 ,31);
			if(sign=="1")
			{
				 StringBuffer str = new StringBuffer(imms); 
			     String s=findTwoscomplement(str); 
			     imm=Integer.parseInt(s,2);  
			     imm=imm*-1;
			}
			else
			{
				 imm=Integer.parseInt(imms,2); 
			}
			
		case "0011011" : switch(fun3)
						{
							case "000" : // addiw
								id=20;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
								
							case "001" :
								id=21;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
								
							case "101" : switch(fun7)
										{
											case "0000000" : // srliw
												id=22;
												System.out.print(id);
												type=2;
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												imms= array.substring(20 ,24);
												imm=Integer.parseInt(imms,2);
												
												
												case "0100000" : // sraiw
												id=23;
												System.out.print(id);
												type=2;
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												imms= array.substring(20 ,24);
												imm=Integer.parseInt(imms,2);
										}
						}
		
		case "0100011" : switch(fun3)
						{
							case "000" : // sb
								id=24;
								System.out.print(id);
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
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "001" : // sh
								id=25;
								System.out.print(id);
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
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "010" : // sw
								id=26;
								System.out.print(id);
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
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "011" : // sd
								id=27;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
						}
		
		case "0110011" : switch(fun3)
						{
							//System.out.print("sswitch");
							case "000" : 
								//System.out.print("sswitch");
								
								switch(fun7)
										{
											case "0000000" : // add
												System.out.print("add");
												id=28;
												System.out.print(id + "\n");
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
												break;
												
											case "0100000" : // sub
												id=29;
												System.out.print(id);
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
												break;
												
											case"0000001" : //mul
												id=60;
												System.out.print(id + "\n");
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
								break;
								
							case "001" : // sll
								id=30;
								System.out.print(id);
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								break;
								
								
							case "010" : // slt
								id=31;
								System.out.print(id);
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								break;
							case "011" : // sltu
								id=32;
								System.out.print(id);
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								break;
								
								
							case "100" : switch(fun7) 
							{
							case "0000000": //xor
								id=33;
								System.out.print(id);
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								break;
							case "0000001": //div
								id=61;
								System.out.print(id);
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								break;
								
							}
							case "101" :  switch(fun7)
										{
											case "0000000" : // srl
												id=34;
												System.out.print(id);
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
												break;
												
												
											case "0100000" :// sra
												id=35;
												System.out.print(id);
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
												break;
										}
							break;
							
								
							case "110" : // or
								id=36;
								System.out.print(id);
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								break;
								
								
							case "111" : // and
								id=37;
								System.out.print(id);
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								break;
								
								
						}
						break;
		
		case "0110111" : // lui
			id=38;
			System.out.print(id + "\n");
			type=5; //U
			rds= array.substring(7,11);
			rd=Integer.parseInt(rds,2);
			imms= array.substring(12 ,31);
			imm=Integer.parseInt(imms,2);
			break;
			
		case "0111011" :  switch(fun3)
						{
							case "000" : switch(fun7)
										{
											case "0000000" : // addw
												id=39;
												System.out.print(id);
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
												break;
												
											case "0100000" : // subw
												id=40;
												System.out.print(id);
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
												break;
												
										}
							break;
							
							
			
							case "001" : // sllw
								id=41;
								System.out.print(id);
								type=1; //R
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;
								break;
								
								
							case "101" : switch(fun7)
										{
											case "0000000" : // srlw
												id=42;
												System.out.print(id);
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
												break;
												
											case "0100000" : // sraw
												id=43;
												System.out.print(id);
												type=1; //R
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
												rs2s= array.substring(20,24);
												rs2=Integer.parseInt(rs2s,2);
												rs2value=REG[rs2].x;
												break;
												
										}
							break;
						}
		break;
		
		case "1100011" : switch(fun3)
						{
							case "000" : // beq
								id=44;
								System.out.print(id);
								type=4;
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								rs2s= array.substring(20,24);
								rs2=Integer.parseInt(rs2s,2);
								rs2value=REG[rs2].x;                                   //to  be continued
								String temp1 = array.substring(7, 11);
								String temp2 = array.substring(25,31);
								String t1=temp1.substring(0,3); //4-1
								String t3=temp1.substring(4); //11
								String t2= temp2.substring(1,6); //10-5
								String t4=temp2.substring(0); //12
								String t5=t4.concat(t3);
								String t6=t5.concat(t2);
								String t7=t6.concat(t1);
								String t8="0";
								String t9=t7.concat(t8);
								imms= t9.substring(0 ,11);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "001" : // bne
								id=45;
								System.out.print(id);
								
								
							case "100" : // blt
								id=46;
								System.out.print(id);
								
							case "101" : // bge
								id=47;
								System.out.print(id);
							case "110" : // bltu
								id=48;
								System.out.print(id);
							case "111" : // bgeu
								id=49;
								System.out.print(id);
						}
		
		case "1100111" : // jalr
			id=50;
			System.out.print(id + "\n");
			type=2;
			rds= array.substring(7,11);
			rd=Integer.parseInt(rds,2);
			rs1s= array.substring(15,19);
			rs1=Integer.parseInt(rs1s,2);
			//rs1value=REG[rs1].x;
			imms= array.substring(20 ,31);
			if(sign=="1")
			{
				 StringBuffer str = new StringBuffer(imms); 
			     String s=findTwoscomplement(str); 
			     imm=Integer.parseInt(s,2);  
			     imm=imm*-1;
			}
			else
			{
				 imm=Integer.parseInt(imms,2); 
			}
		case "1101111" : // jal
			id=51;
			System.out.print(id + "\n");
		case "1110011" :switch(fun3)
						{
							case "000" : switch(immidiate)
										{
											case "000000000000" : // ecall
												id=52;
												System.out.print(id);
												type=2;
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
											case "000000000001" : // ebreak
												id=53;
												System.out.print(id);
												type=2;
												rds= array.substring(7,11);
												rd=Integer.parseInt(rds,2);
												rs1s= array.substring(15,19);
												rs1=Integer.parseInt(rs1s,2);
												rs1value=REG[rs1].x;
										}
								
							case "001" : // CSRRW
								id=54;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "010" : // CSRRS
								id=55;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "011" : // CSRRC
								id=56;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "101" : // CSRRWI
								id=57;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "110" : // CSRRSI
								id=58;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
							case "111" : // CSRRCI
								id=59;
								System.out.print(id);
								type=2;
								rds= array.substring(7,11);
								rd=Integer.parseInt(rds,2);
								rs1s= array.substring(15,19);
								rs1=Integer.parseInt(rs1s,2);
								rs1value=REG[rs1].x;
								imms= array.substring(20 ,31);
								if(sign=="1")
								{
									 StringBuffer str = new StringBuffer(imms); 
								     String s=findTwoscomplement(str); 
								     imm=Integer.parseInt(s,2);  
								     imm=imm*-1;
								}
								else
								{
									 imm=Integer.parseInt(imms,2); 
								}
						}
		default :
			System.out.print("shitt\n");
		}
	}


	public int typereturn() {
		// TODO Auto-generated method stub
		//System.out.print(type);
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
	
	public int immreturn() {
		
		return imm;
	}
	
}

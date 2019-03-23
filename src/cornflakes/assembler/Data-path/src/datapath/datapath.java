package datapath;

import datapath.register;
import datapath.decode;
import datapath.execute;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class datapath {
	static register REG[];
	
	
	public static void main(String Args[]) throws Exception
	{
		File file = new File("/home/yashaswi/yash.txt");
		Scanner sc =  new Scanner(file);
		System.out.print("sdfjsgd\n");
		
		REG = new register[32];
		for(int h=0;h<32;h++)
		{
			register reg = new register();
			reg.x = 0;
			REG[h] = reg;
		}
		Vector v = new Vector();
		
		while (sc.hasNextLine())
		{
			System.out.print("sdfjsgd\n");
			String mcode = new String();
			mcode  = sc.nextLine();
			v.add(mcode);
		}
		
		int k = 0;
		int length = v.size();
		//System.out.print(length);
		
		while(k<length)
		{
			String mcode1 = new String();
			mcode1 = (String) v.get(k);
			//System.out.print(mcode1.length());
			decode D = new decode(mcode1,REG);
			int type = D.typereturn();
			//System.out.print(type);
			switch(type)
			{
			case 1 : //R
				int rs1 = D.rs1return();
				int rs2 = D.rs2return();
				int rd = D.rdreturn();
				int id = D.idreturn();
				execute ex1 = new execute(id,rd,rs1,rs2);
				int out = ex1.output;
				REG[rd].x = out;
					
				break;
				
			case 2 : //I
				int rs1 = D.rs1return();
				int rs2 = D.rs2return();
				int id  = D.idreturn();
				int imm = D.immreturn();	
		                execute ex2 = new executei(id,rs1,imm);	
				int out = ex2.output;
				
				break;	
				
			case 3 : //S
		                int rs1 = D.rs1return();
				int id = D.idreturn();
				int imm = D.immreturn();
				execute ex3 = new executes(id,rs1,imm);	
				int out = ex3.output;
				
				break;
					
			case 4 : //SB
				int rs1 = D.rs1return();
				int rs2 =D.rs2return();
				int id = D.idreturn();
				int imm = D.immreturn();
				execute ex4 = new executesb(id,rs1,rs2,imm);	
				int out = ex4.output;
				
				break;
				
			case 5 : //UJ
				int rs1 = D.rs1return();
				int id = D.idreturn();
				int imm = D.immreturn();
				execute ex5 = new executeuj(id,rs1,imm);	
				int out = ex5.output;
				
				break;
				
			case 6 : //U
				int rs1 = D.rs1return();
				int id = D.idreturn();
				int imm = D.immreturn();
				execute ex6 = new executeu(id,rs1,imm);	
				int out = ex6.output;
				
				break;
			}
			k++;
		}
	}

}

package datapath;

import datapath.register;
import datapath.decode;
import datapath.execute;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class datapath {
	
	public static void main(String Args[]) throws Exception
	{
		File file = new File("/home/yashaswi/yash.txt");
		Scanner sc =  new Scanner(file);
		System.out.print("sdfjsgd\n");
		static register REG[];
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
				System.out.print("yes");
					int rs1 = D.rs1return();
					int rs2 = D.rs2return();
					int rd = D.rdreturn();
					int id = D.idreturn();
					execute ex1 = new execute(id,rd,rs1,rs2);
					int out = ex1.output;
					REG[rd].x = out;
					
					
					
					break;
				
			case 2 : //I
				
			case 3 : //S
				
			case 4 : //SB
				
			case 5 : //UJ
				
			case 6 : //U
			}
			k++;
		}
	}

}

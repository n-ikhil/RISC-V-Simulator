package datapath;

import datapath.register;
import datapath.decode;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class datapath {
	
	public static void main(String Args[]) throws Exception
	{
		File file = new File("");
		Scanner sc =  new Scanner(file);
		
		register REG[];
		REG = new register[32];
		
		while (sc.hasNextLine())
		{
			String mcode = new String();
			mcode  = sc.nextLine();
			decode obj = new decode(mcode);
			
		}
	}

}

package datapath;

import datapath.register;


public class decode {

	String array;

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
							case "000" :
			
							case "001" :
			
							case "010" :
			
							case "011" :
			
							case "100" :
			
							case "101" :
			
							case "110" :
			
						}
		
		case "0001111" : switch(fun3)
						{
							case "000" :
								
							case "001" :
						}
		
		case "0010011" : switch(fun3)
						{
							case "000" :
								
							case "001" :
								
							case "010" :
								
							case "011" :
								 
							case "100" :
								
							case "101" : switch(fun7)
										{
											case "0000000" :
												
											case "0100000" :
												
										}
								
							case "110" :
								 
							case "111" :
								
						}
		
		case "0010111" :
			
		case "0011011" : switch(fun3)
						{
							case "000" :
								
							case "001" :
								
							case "101" : switch(fun7)
										{
											case "0000000" :
											
											case "0100000" :
										}
						}
		
		case "0100011" : switch(fun3)
						{
							case "000" :
								
							case "001" :
								
							case "010" :
								 
							case "011" :
								
						}
		
		case "0110011" : switch(fun3)
						{
							case "000" : switch(fun7)
										{
											case "0000000" :
							
											case "0100000" :
										}
								
							case "001" :
								
							case "010" :
								 
							case "011" :
								
							case "100" :
								
							case "101" :  switch(fun7)
										{
											case "0000000" :
							
											case "0100000" :
										}
								
							case "110" :
								
							case "111" :
						}
		case "0110111" :
			
		case "0111011" :  switch(fun3)
						{
							case "000" : switch(fun7)
										{
											case "0000000" :
		
											case "0100000" :
										}
			
							case "001" :
			
							case "101" : switch(fun7)
										{
											case "0000000" :
						
											case "0100000" :
										}
						}
		
		case "1100011" : switch(fun3)
						{
							case "000" :
								
							case "001" :
								
							case "100" :
								
							case "101" :
								
							case "110" :
								
							case "111" :
						}
		
		case "1100111" :
			
		case "1101111" :
			
		case "1110011" :switch(fun3)
						{
							case "000" : switch(imm)
										{
											case "000000000000" :
												
											case "000000000001" :
										}
								
							case "001" :
								
							case "010" :
								
							case "011" :
								
							case "101" :
								
							case "110" :
								
							case "111" :
						}
		
		}
	}
	
}

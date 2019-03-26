

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner; 

public class datapath {

	static boolean check(String inp,execution[] ex)
	{
		//System.out.println("< "+inp+" >"+" "+ex.length);
		String temp_comp="00000000000000000000000000000000";
		if( !inp.equals(temp_comp) ) return true;
		for(execution ui:ex)
		{ 
			if(ui==null) continue;
			if(!ui.retired)return true;
		}
		
		return false;
	}
	
	
	
	public static void run(primary_memory mem,boolean piplined)
	{
		mem.pc=0;
		execution[] ar = new execution[6];
		
		//test case 
		mem.register[1]=10;
		mem.register[2]=20;
		mem.register[3]=30;
 
		while(check(mem.loadwordstr(mem.pc),ar))
		{
			mem.ir=mem.loadwordstr(mem.pc);
			ar[0]=new execution(mem,piplined);//if instr=="000000000" retired=true
			mem.pc = mem.pc + 4;// stage 1 till here
			for(int instr_stg=0;instr_stg<5;instr_stg++)
			{
				
				 if(ar[instr_stg]==null) continue;
				 if(ar[instr_stg].retired) continue;
				 
				 ar[instr_stg].run(mem);
				 
			
			}
			for(int shiftbyone=4;shiftbyone>=0;shiftbyone--)
			{
				ar[shiftbyone+1]=ar[shiftbyone];
			}
 

		}
		for(int uio:mem.register)
			System.out.println(uio);
	}

}

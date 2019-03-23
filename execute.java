/*auipc jal jalr*/ 	

public class execute {
	
	int rs1value;
	int rs2value;
	int rd;
	static int output;
	
	static int executefunc(int id,int rs1value,int rs2value,int iv)  // R-type constructor
	{
		
		switch(id)
		{
		case 28 : // add
			//System.out.print("yesss");
			output = rs1value + rs2value;
			break;
			
		case 29 : // sub
			output = rs1value - rs2value;
			break;
			
		case 30 : // sll
			output = rs1value << rs2value;
			break;
			
		case 31 : // slt
			if(rs1value < rs2value)
			{
				output = 1;
			}
			else
			{
				output = 2;
			}
			break;
			
		case 32 : //sltu
			if(rs1value < rs2value)
			{
				output = 1;
			}
			else
			{
				output = 2;
			}
			break;
			
		case 33 : //xor
			output = rs1value^rs2value;
			break;
			
		case 34 : //srl
			output = rs1value >>> rs2value;
			break;
			
		case 35 : // sra
			output = rs1value >> rs2value;
			break;
			
		case 36 : // or
			output = rs1value | rs2value;
			break;
			
		case 37 : // and
			output = rs1value & rs2value;
			break;
			
			
		case 39 : // addw
			output = rs1value + rs2value;
			break;
			
		case 40 : // subw
			output = rs1value - rs2value;
			break;
			
		case 41 : // sllw
			output = rs1value << rs2value;
			break;
			
		case 42 : //srlw
			output = rs1value >>> rs2value;
			break;
			
		case 43 : //sraw
			output = rs1value >> rs2value;
			break;
			
			
			
			
		}
		
		
	return output;	
	}
	
	
	
	
	
}

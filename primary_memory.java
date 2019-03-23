
public class primary_memory
{
    //
    private String[] memory;
    final int stack_start=268435452;//2^28-3-1
    final int heap_start=134217728;//2^28
    final int instruction_start=0;//no reserved,obvio !!
    int[] register;
    String ir;
    int ra,rb,rx,ry,rz,pc,iv;
    //the above values are chosen arbitrarily
    primary_memory()
    {       
        memory=new String[268435456]; //max-memory=2^28;
        for(int i=0;i<268435456;i++) memory[i]="00000000";
        register=new int[32];

        for(int i=0;i<32;i++)
        {
            register[i]=0;
        }

        register[2]=stack_start;
        register[3]=heap_start;
        pc=ra=rb=rx=ry=rz=0;
    }
    //////////////////////////////////////////////////////////////////
    //  input is binary string  //
    
    void storebyte(int addr,String byte_in)
    {
        memory[addr]=byte_in.substring(0,8);

    }
    void storeword(int addr,String word_in)
    {
        //little endian//
        //System.out.println(word_in);
        memory[addr]  =word_in.substring(24,32);
        memory[addr+1]=word_in.substring(16,24);
        memory[addr+2]=word_in.substring(8 ,16);
        memory[addr+3]=word_in.substring(0 , 8);
        //System.out.println(memory[addr + 3] + memory[addr + 2] + memory[addr + 1] + memory[addr]+"-");
        
    }
    void storehalf(int addr,String half_in)
    {
        memory[addr]=half_in.substring(8 ,16);
        memory[addr+1]=half_in.substring(0 , 8);
    }

    ///////////////////////////// output is binary string /////////
    
    String loadbyte(int addr)
    {
        return memory[addr];
    }
    String loadhalf(int addr)
    {
        return memory[addr+1]+memory[addr];
    }
    String loadword(int addr)
    {

        //System.out.println(addr+memory[addr+3]+memory[addr+2]+memory[addr+1]+memory[addr]);
        return memory[addr+3]+memory[addr+2]+memory[addr+1]+memory[addr];
    }
    

}
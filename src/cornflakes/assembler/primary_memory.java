package assembler;


public class primary_memory
{
    //
    private String[] memory;
    final long stack_start=268435452;//2^28-3-1
    final long heap_start=134217728;//2^28
    final long instruction_start=0;//no reserved,obvio !!
    //the above values are chosen arbitrarily
    primary_memory()
    {       
        memory=new String[268435456]; //max-memory=2^28;
        for(String ini:memory) ini="00000000";
    }
    //////////////////////////////////////////////////////////////////
    //  input is binary string  //
    
    void storebyte(long addr,String byte_in)
    {
        memory[(int)addr]=byte_in.substring(0,8);
    }
    void storeword(long addr,String word_in)
    {
        //little endian//
        memory[(int)addr]  =word_in.substring(24,32);
        memory[(int)addr+1]=word_in.substring(16,24);
        memory[(int)addr+2]=word_in.substring(8 ,16);
        memory[(int)addr+3]=word_in.substring(0 , 8);
    }
    void storehalf(long addr,String half_in)
    {
        memory[(int)addr]=half_in.substring(8 ,16);
        memory[(int)addr+1]=half_in.substring(0 , 8);
    }

    ///////////////////////////// output is binary string /////////
    
    String loadbyte(long addr)
    {
        return memory[(int)addr];
    }
    String loadhalf(long addr)
    {
        return memory[(int)addr+1]+memory[(int)addr];
    }
    String loadword(long addr)
    {
        return memory[(int)addr+3]+memory[(int)addr+2]+memory[(int)addr+1]+memory[(int)addr];
    }
    

}
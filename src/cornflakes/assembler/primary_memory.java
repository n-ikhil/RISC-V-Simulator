
public class primary_memory
{
    //
    private String[] memory;
    final Long stack_start=268435456;//2^27
    final Long heap_start=134217728;//2^28
    final Long instruction_start=0;//no reserved,obvio !!

    primary_memory()
    {       
        memory=new String[268435456]; //max-memory=2^28;
        for(String ini:memory) ini="00000000";
    }
    //////////////////////////////////////////////////////////////////
    //  input is binary string  //
    
    void storebyte(Long addr,String byte_in)
    {
        memory[addr]=byte_in.substring(0,9);
    }
    void storeword(Long addr,String word_in)
    {
        //little endian//
        memory[addr]  =word_in.substring(24,32);
        memory[addr+1]=word_in.substring(16,24);
        memory[addr+2]=word_in.substring(8 ,16);
        memory[addr+3]=word_in.substring(0 , 8);
    }
    void storehalf(Long addr,String half_in)
    {
        memory[addr]=word_in.substring(8 ,16);
        memory[addr+1]=word_in.substring(0 , 8);
    }

    ///////////////////////////// output is binary string /////////
    
    String readbyte(Long addr)
    {
        ;
    }

    

}
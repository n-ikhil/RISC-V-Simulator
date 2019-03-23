import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class tes_main
{
    public static void main(String []file_location)
    {
        int mem_index=0;
        
        //first create .mc file;
        try{assembler create_file=new assembler(file_location[0]);}
        catch(Exception e){
            System.out.println("No input");}
        //create a memory object
        primary_memory mem=new primary_memory();
        //save all the instructions in the memory
        File file = new File("output.mc");
                try
                {
                    
                    BufferedReader br = new BufferedReader(new FileReader(file));                
                    String line;
                    //System.out.println("No input");
                    while ((line = br.readLine()) != null)
                        {
                            //System.out.println(line);
                            long itemp = Long.parseLong(line.substring(2), 16);
                            String bin_line = Long.toBinaryString(itemp);
                            String temp="";
                            for (int i = 0; i < 32 - bin_line.length(); i++)
                            {
                                    temp = temp + "0";
                            }
                            mem.storeword(mem_index,temp+bin_line);
                            //System.out.println(mem.loadword(mem_index)+"p");
                            mem_index=mem_index+4;
                            
                        }
                }
                catch(Exception e){System.out.println("No input");}

        datapath.run(mem,false);

    }


}

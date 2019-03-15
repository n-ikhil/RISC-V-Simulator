
public class instruction

{
    String binary;
    String self_label;
    boolean has_label;
    boolean wants_label;
    String required_label;
    char type;
    ////////////////////
    String[] binary_temp;
    static boolean next;
    static String next_label;
    ///////////////////
    public static void add_label(String s)
    {
        next=true;
        next_label=s;
        return;

    }
    instruction(String opcode,int r1,int r2, int r3)
    {
        if(next) {has_label=true; self_label=next_label;}
        next_label="\0";
        next=false;
        switch(opcode)
        {
          case  "add"    : rformat("0110011","000","0000000",r1,r2,r3);break;
          case  "sub"    : rformat("0110011","000","0100000",r1,r2,r3);break;
          case  "sll"    : rformat("0110011","001","0000000",r1,r2,r3);break;
          case  "slt"    : rformat("0110011","010","0000000",r1,r2,r3);break;
          case  "sltu"   : rformat("0110011","011","0000000",r1,r2,r3);break;
          case  "xor"    : rformat("0110011","100","0000000",r1,r2,r3);break;
          case  "srl"    : rformat("0110011","101","0000000",r1,r2,r3);break;
          case  "sra"    : rformat("0110011","101","0100000",r1,r2,r3);break;
          case  "or"     : rformat("0110011","110","0000000",r1,r2,r3);break;
          case  "and"    : rformat("0110011","111","0000000",r1,r2,r3);break;   
          case  "addw"   : rformat("0111011","000","0000000",r1,r2,r3);break;
          case  "subw"   : rformat("0111011","000","0100000",r1,r2,r3);break;
          case  "sllw"   : rformat("0111011","001","0000000",r1,r2,r3);break;
          case  "srlw"   : rformat("0111011","101","0000000",r1,r2,r3);break;
          case  "sraw"   : rformat("0111011","101","0100000",r1,r2,r3);break;
          case  "lb"     : iformat("0000011","000","0000000",r1,r2,r3);break;
          case  "lh"     : iformat("0000011","001","0000000",r1,r2,r3);break;
          case  "lw"     : iformat("0000011","010","0000000",r1,r2,r3);break;
          case  "ld"     : iformat("0000011","011","0000000",r1,r2,r3);break;
          case  "lbu"    : iformat("0000011","100","0000000",r1,r2,r3);break;
          case  "lhu"    : iformat("0000011","101","0000000",r1,r2,r3);break;
          case  "lwu"    : iformat("0000011","110","0000000",r1,r2,r3);break;
          case  "fence"  : iformat("0001111","000","0000000",r1,r2,r3);break;
          case  "fence.i": iformat("0001111","001","0000000",r1,r2,r3);break;
          case  "addi"   : iformat("0010011","000","0000000",r1,r2,r3);break;
          case  "slli"   : iformat("0010011","001","0000000",r1,r2,r3);break;
          case  "slti"   : iformat("0010011","010","0000000",r1,r2,r3);break;
          case  "sltiu"  : iformat("0010011","011","0000000",r1,r2,r3);break;
          case  "xori"   : iformat("0010011","100","0000000",r1,r2,r3);break;
          case  "srli"   : iformat("0010011","101","0000000",r1,r2,r3);break;
          case  "srai"   : iformat("0010011","101","0100000",r1,r2,r3);break;
          case  "ori"    : iformat("0010011","110","0000000",r1,r2,r3);break;
          case  "andi"   : iformat("0010011","111","0000000",r1,r2,r3);break;
          case  "addiw"  : iformat("0110011","000","0000000",r1,r2,r3);break;
          case  "slliw"  : iformat("0110011","001","0000000",r1,r2,r3);break;
          case  "srliw"  : iformat("0110011","101","0000000",r1,r2,r3);break;
          case  "sraiw"  : iformat("0110011","101","0100000",r1,r2,r3);break;
          case  "sd"     : iformat("0100011","011","0000000",r1,r2,r3);break;
          case  "jalr"   : iformat("1100011","000","0000000",r1,r2,r3);break;
        //   case  "auipc"  : uformat("0010011","111","0000000",r1,r2,r3);break;
        //   case  "lui"    : uformat("0110111","000","0000000",r1,r2,r3);break;          
        //   case  "jal"    :ujformat("1101111","000","0000000",r1,r2,r3);break;

          default: System.out.println("No such instruction found !");
        }
    }

    instruction(String opcode,int r1,int iv)
    {
        if(next) {has_label=true;self_label=next_label;}
        next_label="\0";
        next=false;
        switch(opcode)
        {
          case  "auipc"  : uformat("0010011","111","0000000",r1,iv);break;
          case  "lui"    : uformat("0110111","000","0000000",r1,iv);break;
          default: System.out.println("No such instruction found !");          
        }
        return;

    }
    instruction(String opcode,int r1,int r2, String lab)
    {
        if(next) {has_label=true;self_label=next_label;}
        next_label="\0";
        next=false;
        switch (opcode)
        {
          case  "sb"     :sbformat("0100011","000","0000000",r1,r2,lab);break;
          case  "sh"     :sbformat("0100011","001","0000000",r1,r2,lab);break; 
          case  "sw"     :sbformat("0100011","010","0000000",r1,r2,lab);break;    
          case  "beq"    :sbformat("1100011","000","0000000",r1,r2,lab);break;
          case  "bne"    :sbformat("1100011","001","0000000",r1,r2,lab);break;
          case  "blt"    :sbformat("1100011","100","0000000",r1,r2,lab);break;
          case  "bge"    :sbformat("1100011","101","0000000",r1,r2,lab);break;
          case  "bltu"   :sbformat("1100011","110","0000000",r1,r2,lab);break;
          case  "bgeu"   :sbformat("1100011","111","0000000",r1,r2,lab);break;
          default:  System.out.println("No such instruction found !");
        }
    }
    instruction(String opcode,int r1, String lab)
    {
        if(next) {has_label=true;self_label=next_label;}
        next_label="\0";
        next=false;
        ujformat("1101111","000","0000000",r1,lab);
    }

    void uformat(String s1,String s2,String s3,int r1,int iv)
    {
        type='u';
        String temp=new String();
        String temp2=new String();
        binary_temp=new String[6];
        binary_temp[0]=s1;
        

        binary_temp[1]=Integer.toBinaryString(r1);
        for(int i=0;i<5-binary_temp[1].length();i++)
        {
            temp=temp+"0";
        }
        temp=temp+binary_temp[1];
        binary_temp[1]=temp;
        
        temp="";

        temp2=Integer.toBinaryString(iv);
        for(int i=0;i<20-temp2.length();i++)
        {
            temp=temp+"0";
        }
        temp2=temp+temp2;
        //System.out.println(temp2+"\n"+temp2.length());
        binary_temp[2]=temp2.substring(11,19);
        binary_temp[3]=temp2.substring(10,11);
        binary_temp[4]=temp2.substring(0,10);
        binary_temp[5]=temp2.substring(19,20);

        binary=new String();
        

        for(int temp_s=5;temp_s>=0;temp_s--)
        {
        
            binary=binary+binary_temp[temp_s];
            //System.out.println(temp_s+" "+binary);
            
        }
        
        //System.out.println(binary);
        wants_label=false;  
        return ;        
    }


    void ujformat(String s1,String s2,String s3,int r1,String lab)
    {
        type='j';
        String temp=new String();
        binary_temp=new String[2];
        binary_temp[0]=s1;
        

        binary_temp[1]=Integer.toBinaryString(r1);
        for(int i=0;i<5-binary_temp[1].length();i++)
        {
          temp=temp+"0";
        }
        temp=temp+binary_temp[1];
        binary_temp[1]=temp;
        ////////////////////////////////
        temp="";
        ///////////////////////////////
        // temp2=Integer.toBinaryString(iv);
        // for(int i=0;i<20-temp2.length();i++)
        // {
        //     temp=temp+"0";
        // }
        // temp2=temp+temp2;
        // binary_temp[2]=temp2;

        binary=new String();
        
        for(int temp_s=1;temp_s>=0;temp_s--)
        {
        
            binary=binary+binary_temp[temp_s];
            
        }
        
        required_label=lab;
        wants_label=true;  
        return ;

        
    }
    
    

    void rformat(String s1,String s2,String s3,int r1,int r2, int r3)
    {
        type='r';
        String temp=new String();
        binary_temp=new String[6];
        binary_temp[0]=s1;

    
        binary_temp[1]=Integer.toBinaryString(r1);
        for(int i=0;i<5-binary_temp[1].length();i++)
        {
            temp=temp+"0";
        }
        temp=temp+binary_temp[1];
        binary_temp[1]=temp;

        temp="";

        binary_temp[2]=s2;

        binary_temp[3]=Integer.toBinaryString(r2);
        for(int i=0;i<5-binary_temp[3].length();i++)
        {
            temp=temp+"0";
        }
        temp=temp+binary_temp[3];
        binary_temp[3]=temp;

        temp="";
        
       

        binary_temp[4]=Integer.toBinaryString(r3);
        for(int i=0;i<5-binary_temp[4].length();i++)
        {
            temp=temp+"0";
        }

        temp=temp+binary_temp[4];
        binary_temp[4]=temp;

        
        temp="";
        
        binary_temp[5]=s3;
        
        binary=new String();
       

        for( int temp_s=5;temp_s>=0;temp_s--)
        {
        
            binary=binary+binary_temp[temp_s];
            
        }
        
        wants_label=false;  
        return ;      

    }

    void iformat(String s1,String s2,String s3,int r1,int r2, int r3)
    {
        type='i';
        String temp=new String();
        binary_temp=new String[5];
        binary_temp[0]=s1;
        //System.out.println(binary_temp[0]);

        binary_temp[1]=Integer.toBinaryString(r1);
        for(int i=0;i<5-binary_temp[1].length();i++)
        {
            temp=temp+"0";
        }
        temp=temp+binary_temp[1];
        binary_temp[1]=temp;
        //System.out.println(binary_temp[1]);

        temp="";

        binary_temp[2]=s2;
        //System.out.println(binary_temp[2]);

        binary_temp[3]=Integer.toBinaryString(r2);
        for(int i=0;i<5-binary_temp[3].length();i++)
        {
            temp=temp+"0";
        }
        temp=temp+binary_temp[3];
        binary_temp[3]=temp;
        //System.out.println(binary_temp[3]);

        temp="";
        
        
        binary_temp[4]=Integer.toBinaryString(r3);
        for(int i=0;i<12-binary_temp[4].length();i++)
        {
            temp=temp+"0";
        }
        temp=temp+binary_temp[4];
        binary_temp[4]=temp;
        //System.out.println(binary_temp[4]);
        temp="";       

        binary=new String();

       for(int temp_s=4;temp_s>=0;temp_s--)
        {
            binary=binary+binary_temp[temp_s];
            //System.out.println(binary);
        }
       
        wants_label=false;    
        return ;    

    }

    void sbformat(String s1,String s2,String s3,int r1,int r2, String lab)
    {
        type='b';
        String temp=new String();
        binary_temp=new String[6];
        
        binary_temp[0]=s1;

        
        temp="";
        binary_temp[2]=s2;
        
        binary_temp[3]=Integer.toBinaryString(r2);
        for(int i=0;i<5-binary_temp[3].length();i++)
        {
            temp=temp+"0";
        }
        temp=temp+binary_temp[3];
        binary_temp[3]=temp;
        
        temp="";
        
        binary_temp[4]=Integer.toBinaryString(r2);
        for(int i=0;i<12-binary_temp[4].length();i++)
        {
            temp=temp+"0";
        }
        temp=temp+binary_temp[4];
        binary_temp[4]=temp;
        
        binary=new String();        

        for(int temp_s=5;temp_s!=0;temp_s--)
        {
            binary=binary+binary_temp[temp_s];
        }
        wants_label=true;
        required_label=lab;
        return;        

    }

    void substitute(int  val)
    {
        String local;
        if(type=='b')
        {
            local=Integer.toBinaryString(val);
            binary_temp[1]=local.substring(0,4);
            binary_temp[1]=binary_temp[1]+local.substring(10,11);
            binary_temp[5]=local.substring(11,12);
            binary_temp[5]=binary_temp[5]+local.substring(4,9);
            for(int temp_s=5;temp_s!=0;temp_s--)
            {
                binary=binary+binary_temp[temp_s];
            }
        }
        else if(type=='j')
        {
            local = Integer.toBinaryString(val);
            //binary_temp[2]=
            


        }
    }
    instruction(){;}

    void assign_labels(instruction [] all)
    {
        instruction temp=new instruction();
        instruction temp_2=new instruction();
        for(int ext=0;ext<all.length;ext++)
        {temp=all[ext];
            if (temp.wants_label)
            {
                for(int in=0;in<all.length;in++)
                    {temp_2=all[in];
                        if(temp_2.has_label)
                        {
                            if(temp_2.self_label==temp.required_label) 
                                {
                                    temp.substitute(in-ext);
                                }
                        }
                    }
            }
        }
    }

}
public class tes_main
{
    /*lw,sw,auipc,add,beq,jalr,jal*/
    /*
        top:lw x1 1234 x17
        sw x31 783 x5
        addi x1 x1 1024
        bottom:beq x1 x9 top
        jalr x1 991 x9
        jal x1 bottom

    */
    public static void main(String [] args)
    {
        long beforeUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
      
        // instruction ar[]=new instruction[6];
        // instruction.add_label("top");
        // ar[0]=new instruction("lw",1,1234,17);//1
        // ar[1]=new instruction("sw",31,783,5);//0
        // ar[2]=new instruction("addi",1,1,1024);//1
        // instruction.add_label("bottom");
        // ar[3]=new instruction("beq",1,9,"top");//0
        // ar[4]=new instruction("jalr",1,991,9);//0
        // ar[5]=new instruction("jal",1,"botto");//0
        // instruction.assign_labels(ar);
        // //ar[0]=new instruction("sw",1,846,31);
        // // ar[0]=new instruction("lw",1,1234,17);
        // // ar[1]=new instruction("sh",31, 783, 5);
        // for(instruction temp:ar)
        // System.out.println(temp.hex);

        primary_memory temp=new primary_memory();
        temp.storebyte(268435455,"10101010");
        String in=temp.loadbyte(268435455);
        System.out.println(in);
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        System.out.println(beforeUsedMem-afterUsedMem);

    }
}

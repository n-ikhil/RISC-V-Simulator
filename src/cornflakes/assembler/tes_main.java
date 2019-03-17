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
        // instruction temp=new instruction("add",1,2,3);
        
        // System.out.println(temp.binary);
        // instruction.add_label("test");
        
        // instruction temp2=new instruction("addi",1,2,256);
        // System.out.println(temp2.binary);
        // instruction temp3=new instruction("auipc",1,256);
        // System.out.println(temp3.binary);
        // instruction temp4=new instruction("jal",2,"lnks");
        // System.out.println(temp2.binary);
        // instruction.add_label("top");
        // instruction temp5=new instruction("beq",1,2,"test");
        // instruction[] 
        // System.out.println(temp5.binary);
        // String s=new String("123");
        //System.out.println(s.charAt(2));
        // int i=1;
        // String s= new String();
        // s="0";
        // s=Integer.toBinaryString(-1);
        // System.out.println(s);
        // return;
        instruction ar[]=new instruction[6];
        instruction.add_label("top");
        ar[0]=new instruction("lw",1,1234,17);//1
        ar[1]=new instruction("sw",31,783,5);//0
        ar[2]=new instruction("addi",1,1,1024);//1
        instruction.add_label("bottom");
        ar[3]=new instruction("beq",1,9,"top");//0
        ar[4]=new instruction("jalr",1,991,9);//0
        ar[5]=new instruction("jal",1,"bottom");//0
        instruction.assign_labels(ar);
        for(instruction temp:ar)
        System.out.println(temp.binary);

    }
}
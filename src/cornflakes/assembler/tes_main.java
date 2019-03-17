public class tes_main
{
    public static void main(String [] args)
    {
        instruction temp=new instruction("add",1,2,3);
        
        System.out.println(temp.binary);
        instruction.add_label("test");
        
        instruction temp2=new instruction("addi",1,2,256);
        System.out.println(temp2.binary);
        instruction temp3=new instruction("auipc",1,256);
        System.out.println(temp3.binary);
        instruction temp4=new instruction("jal",2,"lnks");
        System.out.println(temp2.binary);
        instruction.add_label("top");
        instruction temp5=new instruction("beq",1,2,"test");
        instruction[] 
        System.out.println(temp5.binary);
        String s=new String("123");
        //System.out.println(s.charAt(2));
        // int i=1;
        // String s= new String();
        // s="0";
        // s=Integer.toBinaryString(-1);
        // System.out.println(s);
        // return;
    }
}
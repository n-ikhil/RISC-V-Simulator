
package datapath;
import assembler.primary_memory;
public class instructions
{

    int hazard_type_rs1,hazard_type_rs2;
    //boolean write_to_register,write_to_pc,write_to_memory;
    int stage;
    int rs1,rs2,iv,rd,id,type;
    String opcode,fun3,fun7,instruct;
    int mem_switch;// no action :0,write to register:1 , write to memory : 2,load from memory :3,write to pc with offset:4,write to pc as reg value:5
    boolean bypass;
    int parseint(String binaryInt,int t) {
        //Check if the number is negative.
        //We know it's negative if it starts with a 1
        if (binaryInt.charAt(0) == '1') {
            //Call our invert digits method
            String invertedInt = invertDigits(binaryInt);
            //Change this to decimal format.
            int decimalValue = Integer.parseInt(invertedInt, 2);
            //Add 1 to the curernt decimal and multiply it by -1
            //because we know it's a negative number
            decimalValue = (decimalValue + 1) * -1;
            //return the final result
            //System.out.println(";-)"+binaryInt+":"+ decimalValue);
            return decimalValue;
        } else {
            //Else we know it's a positive number, so just convert
            //the number to decimal base.
            return Integer.parseInt(binaryInt, 2);
        }
    }

    String invertDigits(String binaryInt) {
        String result = binaryInt;
        result = result.replace("0", " "); //temp replace 0s
        result = result.replace("1", "0"); //replace 1s with 0s
        result = result.replace(" ", "1"); //put the 1s back in
        return result;
    }

    instructions()
    {
      bypass=false;
    }
    instructions(primary_memory mem)
        {

            bypass=false;
            /////////////////////////
            String array=mem.ir;
            //String array=mem.ir; //stage 1
            ////////////////////////
            instruct=array;
            // long decimal1 = Long.parseLong(array,2);
            //  instruct = Integer.toString("0"+decimal1,16);
            rs1=rs2=iv=rd=id=type=0;

            mem_switch=1;
            //////////////////////////////////
            // System.out.println(parseint("1", 2));
            // System.out.println(parseint("01", 2));



            /////////////////////////
            String rds, rs1s, imms, rs2s, imms1, imms2,imms3,imms4;
            /////////////////////////
            opcode = array.substring(25, 32);
            fun3 = array.substring(17, 20);
            fun7 = array.substring(0, 7);

                rds =  array.substring(20, 25);
                rds="0"+rds;
            rd = parseint(rds, 2);
            //if(rd<0 || rd>31) rd=0;

                rs2s = array.substring(7,12);
                rs2s = "0" + rs2s;
            rs2 = parseint(rs2s, 2);
           //if(rs2<0||rs2>31) rs2=0;

                rs1s = array.substring(12, 17);
                rs1s="0"+rs1s;
            rs1 = parseint(rs1s, 2);
            //if(rs1<0||rs1>31) rs1=0;

                imms = array.substring(0, 12);
            iv = parseint(imms, 2);
            instruct=opcode+" "+rds;

            //////////////////////////
            //System.out.println(iv+"::"+imms);
            //////////////////////////

            ///////////////////////negative




            switch (opcode)
                {

                case "0000011":
                            type=2;
                            mem_switch=3;

                        switch (fun3)
                    {

                        case "000":// lb instruction
                            id = 1;return;

                        case "001": // lh instruction
                            id = 2;return;

                        case "010": // lw
                            id = 3;return;////////////////////////////////////

                        case "011": // ld
                            id = 4;return;

                        case "100": // lbu
                            id = 5;return;

                        case "101": // lhu
                            id = 6;return;

                        case "110": // lwu
                            id = 7;return;

                        default: ;return;

                    }

                case "0001111":
                            type = 2;

                    switch (fun3)
                        {
                            case "000"://fence
                                id = 8;return;


                            case "001": // fence.i
                                id = 9;return;

                            default: ;return;
                        }


                case "0010011":
                            type=2;mem_switch=1;
                    switch (fun3)
                    {
                        case "000": // addi
                            id = 10;return;

                        case "001": // slli
                            id = 11;return;

                        case "010": // slti
                            id = 12;return;
                        case "011": // sltiu
                            id = 13;return;
                        case "100": // xori
                            id = 14;return;
                        case "101":
                            switch (fun7)
                            {
                                case "0000000": // srli
                                    id = 15;return;

                                case "0100000": // srai
                                    id = 16;return;

                                default: ;return;

                            }

                        case "110": // ori
                            id = 17;return;
                        case "111": // andi
                            id = 18;return;

                        default : ;return;
                    }

                case "0010111":type=5; // auipc
                    {
                        id = 19;type=5;
                        mem_switch=1;
                        imms = array.substring(0, 21);
                        iv = parseint(imms, 2);
                    }

                case "0011011":type=2;mem_switch=1;
                    switch (fun3)
                        {
                            case "000": // addiw
                                id = 20;return;
                            case "001"://slliw
                                id = 21;return;
                            case "101":
                                switch (fun7)
                                {
                                    case "0000000": // srliw
                                        id = 22;return;

                                    case "0100000": // sraiw
                                        id = 23;return;

                                    default: ;return;

                                }
                            default: ;return;
                        }

                case "0100011":
                                type=3;
                                imms1 = array.substring(0,  7);
                                imms2 = array.substring(20,25);
                                imms = imms1+imms2;
                                iv = parseint(imms, 2);
                                mem_switch=2;


                    switch (fun3)
                        {
                            case "000"://sb
                                id = 24;return;

                            case "001": // sh
                                id = 25;return;

                            case "010": // sw
                                id = 26;return;

                            case "011": // sd
                                id = 27;return;

                        }

                case "0110011":
                    //System.out.println("opcorrect");
                    type=1;
                    mem_switch=1;
                    switch (fun3)
                        {
                            case "000":
                                switch (fun7)
                                    {
                                        case "0000001":
                                            id = 60; return;//mul
                                        case "0000000": // add
                                            id = 28;return;

                                        case "0100000": // sub
                                            id = 29;return;

                                        default: ;return;

                                    }


                            case "001": // sll
                                id = 30;return;

                            case "010": // slt
                                id = 31;return;

                            case "011": // sltu
                                id = 32;return;

                            case "100": // xor
                                if(fun7=="0000001")
                                {id=61;//div
                                return;}
                                id = 33;return;

                            case "101":
                                switch (fun7)
                                    {
                                        case "0000000": // srl
                                            id = 34;return;


                                        case "0100000":// sra
                                            id = 35;return;

                                        default: ;return;



                                    }


                            case "110": // or
                                if(fun7=="0000001")
                                {id=62;
                                return;}
                                id = 36;return;


                            case "111": // and
                                id = 37;return;

                            default: return;
                        }

                case "0110111": // lui
                        type=5;id = 38;
                        mem_switch=1;
                        imms=array.substring(0, 20);
                        iv = parseint(imms, 2);

                        return;


                case "0111011":
                    type=1;
                    mem_switch=1;
                    switch (fun3)
                        {
                            case "000":
                                switch (fun7)
                                    {
                                        case "0000000": // addw
                                            id = 39;return;

                                        case "0100000": // subw
                                            id = 40;return;

                                    }
                                break;

                            case "001": // sllw
                                id = 41;return;


                            case "101":
                                switch (fun7)
                                    {
                                        case "0000000": // srlw
                                            id = 42;return;


                                        case "0100000": // sraw
                                            id = 43;return;

                                    }

                        }

                case "1100011":
                type=4;
                imms1=array.substring(0,1);
                imms2=array.substring(1,7);
                imms3=array.substring(20, 24);
                imms4=array.substring(24,25);
                imms=imms1+imms4+imms2+imms3;
                iv = parseint(imms, 2);
                mem_switch=4;


                    switch (fun3)
                        {
                            case "000": //
                                id = 44;return;
                            case "001": // bne
                                id = 45;return;

                            case "100": // blt
                                id = 46;return;

                            case "101": // bge
                                id = 47;return;
                            case "110": // bltu
                                id = 48;return;
                            case "111": // bgeu
                                id = 49;return;
                        }

                case "1100111": // jalr
                    id = 50;type=2;mem_switch=5;return;

                case "1101111": // jal
                    id = 51;
                    mem_switch=4;
                    type=6;
                    imms1=array.substring(0, 1);
                    imms2=array.substring(1,11);
                    imms3=array.substring(11,12);
                    imms4=array.substring(12,20);

                    imms=imms1+imms4+imms3+imms2;
                    iv = parseint(imms, 2);return;

                case "1110011":
                type=2;
                mem_switch=0;
                    switch (fun3)
                        {
                            case "000":
                                switch (iv)
                                    {
                                        case 0: // ecall
                                            id = 52;return;
                                        case 1: // ebreak
                                            id = 53;return;
                                    }

                            case "001": // CSRRW
                                id = 54;return;
                            case "010": // CSRRS
                                id = 55;return;
                            case "011": // CSRRC
                                id = 56;return;
                            case "101": // CSRRWI
                                id = 57;return;
                            case "110": // CSRRSI
                                id = 58;return;
                            case "111": // CSRRCI
                                id = 59;return;
                        }
                default:
                    return;


            }

    }


}

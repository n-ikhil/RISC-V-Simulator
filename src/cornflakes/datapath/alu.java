
package datapath;
import assembler.primary_memory;

public class alu {

	int rs1value;
	int rs2value;
	int rd;
	static int id;
	static int output = 0;

	static void executer(int id0, int rs1value, int rs2value)// R-type constructor
	{
		id = id0;

		switch (id) {

		case 28: // add

			output = rs1value + rs2value;
			break;

		case 29: // sub

			output = rs1value - rs2value;
			break;

		case 30: // sll

			output = rs1value << rs2value;
			break;

		case 31: // slt

			if (rs1value < rs2value)

			{
				output = 1;
			} else {
				output = 0;
			}
			break;

		case 32: // sltu

			if (rs1value < rs2value) {
				output = 1;
			} else {
				output = 0;
			}
			break;

		case 33: // xor

			output = rs1value ^ rs2value;
			break;

		case 34: // srl

			output = rs1value >>> rs2value;
			break;

		case 35: // sra

			output = rs1value >> rs2value;
			break;

		case 36: // or

			output = rs1value | rs2value;
			break;

		case 37: // and

			output = rs1value & rs2value;
			break;

		case 39: // addw

			output = rs1value + rs2value;
			break;

		case 40: // subw

			output = rs1value - rs2value;
			break;

		case 41: // sllw

			output = rs1value << rs2value;
			break;

		case 42: // srlw

			output = rs1value >>> rs2value;
			break;

		case 43: // sraw

			output = rs1value >> rs2value;
			break;
		case 60:
			output=rs1value*rs2value;
			break;

		case 61:
			output= rs1value/rs2value;
			break;

		case 62:
			output=rs1value%rs2value;
			break;

		}

	}

	static void executei(int idi, int rs1i, int immi) {
		int id = idi;

		switch (id) {

		case 1: // lb

			output = rs1i + immi;
			break;

		case 2: // lh

			output = rs1i + immi;
			//////////////////////////////

			break;

		case 3: // lw

			output = rs1i + immi;
			//System.out.println(rs1i + ":" + immi);
			break;

		case 5: // lbu

			output = rs1i + immi;

			break;

		case 6: // lhu

			output = rs1i + immi;
			break;

		case 7: // lwu

			output = rs1i + immi;
			break;

		case 10: // addi

			output = rs1i + immi;
			break;

		case 11: // slli

			output = rs1i << immi;
			break;

		case 12: // slti

			if (rs1i < immi)

			{
				output = 1;
			} else {
				output = 0;
			}
			break;

		case 13: // sltiu

			if (rs1i < immi) {
				output = 1;
			} else {
				output = 0;
			}
			break;

		case 14: // xori

			output = rs1i ^ immi;
			break;

		case 15: // srli

			output = rs1i >>> immi;
			break;

		case 16: // srai

			output = rs1i >> immi;
			break;

		case 17: // ori

			output = rs1i | immi;
			break;

		case 18: // andi

			output = rs1i & immi;
			break;

		case 20: // addiw

			output = rs1i + immi;
			break;

		case 21: // slliw

			output = rs1i << immi;
			break;

		case 22: // srliw
			output = rs1i >>> immi;

			break;

		case 23: // sraiw

			output = rs1i >> immi;
			break;

		case 50: // jalr



			output = rs1i + immi;
			System.out.println(rs1i + ":" + immi+":"+output);

			break;

		case 52: // ecall

			break;

		case 53: // ebreak

			break;

		case 54: // csrrw

			break;

		case 55: // csrrs

			break;

		case 56: // csrrc

			break;

		case 57: // csrrwi

			break;

		case 58: // csrrsi

			break;

		case 59: // csrrci

			break;

		}
	}

	static void executeS(int ids, int rs1s, int imms) {

		int id = ids;
		switch (id) {

		case 24: // sb

			output = rs1s + imms;
			break;

		case 25: // sh

			output = rs1s + imms;
			break;

		case 26: // sw

			output = rs1s + imms;
			break;

		}

	}

	static void executeu(int idu, int rs1u, int immu) {
		int id = idu;
		switch (id) {

		case 19: // auipc

			output = rs1u + immu;
			break;

		case 38: // lui

			output = rs1u + immu;
			break;

		}

	}

	static void executeuj(int iduj, int rs1uj, int immuj) {
		int id = iduj;
		switch (id) {

		case 51: // jal

			break;
		}
	}

	static void executesb(int idsb, int rs1sb, int rs2sb, int immsb) {
		int id = idsb;
		switch (id) {

		case 44: // beq

			if (rs1sb == rs2sb) {
				output = 1;
			} else {
				output = 0;
			}
			break;

		case 45: // bne

			if (rs1sb != rs2sb) {
				output = 1;
			} else {
				output = 0;
			}
			break;

		case 46: // blt

			if (rs1sb < rs2sb) {
				output = 1;
			} else {
				output = 0;
			}
			break;

		case 47: // bge

			if (rs1sb >= rs2sb) {
				output = 1;
			} else {
				output = 0;
			}
			break;

		case 48: // bltu

			if (rs1sb < rs2sb) {
				output = 1;
			} else {
				output = 0;
			}
			break;

		case 49: // bgeu

			if (rs1sb >= rs2sb) {
				output = 1;
			} else {
				output = 0;
			}
			break;

		}
	}

}

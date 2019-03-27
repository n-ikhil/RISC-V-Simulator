package assembler;

public class instruction

	{
		String binary;
		String self_label;
		boolean has_label;
		boolean wants_label;
		String required_label;
		String hex;
		char type;
		////////////////////
		String[] binary_temp;
		static boolean next;
		static String next_label;

		///////////////////
		public static void add_label(String s)
			{
				next = true;
				next_label = s;
				return;

			}

		// r3<==>r2
		instruction(String opcode, int r1, int r2, int r3)
			{
				if (next)
					{
						has_label = true;
						self_label = next_label;
					}
				next_label = "\0";
				next = false;
				switch (opcode)
					{
					case "add":
						rformat("0110011", "000", "0000000", r1, r2, r3);
						break;
					case "sub":
						rformat("0110011", "000", "0100000", r1, r2, r3);
						break;
					case "sll":
						rformat("0110011", "001", "0000000", r1, r2, r3);
						break;
					case "slt":
						rformat("0110011", "010", "0000000", r1, r2, r3);
						break;
					case "sltu":
						rformat("0110011", "011", "0000000", r1, r2, r3);
						break;
					case "xor":
						rformat("0110011", "100", "0000000", r1, r2, r3);
						break;
					case "srl":
						rformat("0110011", "101", "0000000", r1, r2, r3);
						break;
					case "sra":
						rformat("0110011", "101", "0100000", r1, r2, r3);
						break;
					case "or":
						rformat("0110011", "110", "0000000", r1, r2, r3);
						break;
					case "and":
						rformat("0110011", "111", "0000000", r1, r2, r3);
						break;
					case "addw":
						rformat("0111011", "000", "0000000", r1, r2, r3);
						break;
					case "subw":
						rformat("0111011", "000", "0100000", r1, r2, r3);
						break;
					case "sllw":
						rformat("0111011", "001", "0000000", r1, r2, r3);
						break;
					case "srlw":
						rformat("0111011", "101", "0000000", r1, r2, r3);
						break;
					case "sraw":
						rformat("0111011", "101", "0100000", r1, r2, r3);
						break;
					case "mul":
						rformat("0110011", "000", "0000001", r1, r2, r3);
						break;
					case "div":
						rformat("0110011", "100", "0000001", r1, r2, r3);
						break;
					case "rem":
						rformat("0110011", "110", "0000001", r1, r2, r3);
						break;
					case "fence":
						iformat("0001111", "000", "0000000", r1, r2, r3);
						break;
					case "fence.i":
						iformat("0001111", "001", "0000000", r1, r2, r3);
						break;
					case "addi":
						iformat("0010011", "000", "0000000", r1, r2, r3);
						break;
					case "slli":
						iformat("0010011", "001", "0000000", r1, r2, r3);
						break;
					case "slti":
						iformat("0010011", "010", "0000000", r1, r2, r3);
						break;
					case "sltiu":
						iformat("0010011", "011", "0000000", r1, r2, r3);
						break;
					case "xori":
						iformat("0010011", "100", "0000000", r1, r2, r3);
						break;
					case "srli":
						iformat("0010011", "101", "0000000", r1, r2, r3);
						break;
					case "srai":
						iformat("0010011", "101", "0100000", r1, r2, r3);
						break;
					case "ori":
						iformat("0010011", "110", "0000000", r1, r2, r3);
						break;
					case "andi":
						iformat("0010011", "111", "0000000", r1, r2, r3);
						break;
					case "addiw":
						iformat("0110011", "000", "0000000", r1, r2, r3);
						break;
					case "slliw":
						iformat("0110011", "001", "0000000", r1, r2, r3);
						break;
					case "srliw":
						iformat("0110011", "101", "0000000", r1, r2, r3);
						break;
					case "sraiw":
						iformat("0110011", "101", "0100000", r1, r2, r3);
						break;
					///////////////////////////////////////////////////////////////////
					case "jalr":
						iformat("1100111", "000", "0000000", r1, r3, r2);
						break;
					//////////////////////////////////// note:interchanged r2 r3 below
					case "lb":
						iformat("0000011", "000", "0000000", r1, r3, r2);
						break;
					case "lh":
						iformat("0000011", "001", "0000000", r1, r3, r2);
						break;
					case "lw":
						iformat("0000011", "010", "0000000", r1, r3, r2);
						break;
					case "ld":
						iformat("0000011", "011", "0000000", r1, r3, r2);
						break;
					case "lbu":
						iformat("0000011", "100", "0000000", r1, r3, r2);
						break;
					case "lhu":
						iformat("0000011", "101", "0000000", r1, r3, r2);
						break;
					case "lwu":
						iformat("0000011", "110", "0000000", r1, r3, r2);
						break;
					case "sd":
						iformat("0100011", "011", "0000000", r3, r1, r2);
						break;
					case "sb":
						sformat("0100011", "000", "0000000", r3, r1, r2);
						break;
					case "sh":
						sformat("0100011", "001", "0000000", r3, r1, r2);
						break;
					case "sw":
						sformat("0100011", "010", "0000000", r3, r1, r2);
						break;
					//////////////////////////////////

					default: // System.out.println("No such instruction found !");
					}
			}

		instruction(String opcode, int r1, int iv)
			{
				if (next)
					{
						has_label = true;
						self_label = next_label;
					}
				next_label = "\0";
				next = false;
				switch (opcode)
					{
					case "auipc":
						uformat("0010011", "111", "0000000", r1, iv);
						break;
					case "lui":
						uformat("0110111", "000", "0000000", r1, iv);
						break;
					default: // System.out.println("No such instruction found !");
					}
				return;

			}

		instruction(String opcode, int r1, int r2, String lab)
			{
				if (next)
					{
						has_label = true;
						self_label = next_label;
					}
				next_label = "\0";
				next = false;
				switch (opcode)
					{
					case "beq":
						sbformat("1100011", "000", "0000000", r1, r2, lab);
						break;
					case "bne":
						sbformat("1100011", "001", "0000000", r1, r2, lab);
						break;
					case "blt":
						sbformat("1100011", "100", "0000000", r1, r2, lab);
						break;
					case "bge":
						sbformat("1100011", "101", "0000000", r1, r2, lab);
						break;
					case "bltu":
						sbformat("1100011", "110", "0000000", r1, r2, lab);
						break;
					case "bgeu":
						sbformat("1100011", "111", "0000000", r1, r2, lab);
						break;
					default: // System.out.println("No such instruction found !");
					}
			}

		instruction(String opcode, int r1, String lab)
			{
				if (next)
					{
						has_label = true;
						self_label = next_label;
					}
				next_label = "\0";
				next = false;
				// only jal instruction
				ujformat("1101111", "000", "0000000", r1, lab);
			}

		instruction()
			{
				;
			}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		void rformat(String s1, String s2, String s3, int r1, int r2, int r3)
			{
				type = 'r';
				String temp = new String();
				binary_temp = new String[6];
				binary_temp[0] = s1;

				binary_temp[1] = Integer.toBinaryString(r1);
				for (int i = 0; i < 5 - binary_temp[1].length(); i++)
					{
						if (r1 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[1];
				temp = temp.substring(temp.length() - 5, temp.length());
				binary_temp[1] = temp;

				temp = "";

				binary_temp[2] = s2;

				binary_temp[3] = Integer.toBinaryString(r2);
				for (int i = 0; i < 5 - binary_temp[3].length(); i++)
					{
						if (r2 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[3];
				temp = temp.substring(temp.length() - 5, temp.length());

				binary_temp[3] = temp;

				temp = "";

				binary_temp[4] = Integer.toBinaryString(r3);
				for (int i = 0; i < 5 - binary_temp[4].length(); i++)
					{
						if (r3 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}

				temp = temp + binary_temp[4];
				temp = temp.substring(temp.length() - 5, temp.length());

				binary_temp[4] = temp;

				temp = "";

				binary_temp[5] = s3;

				binary = new String();

				for (int temp_s = 5; temp_s >= 0; temp_s--)
					{

						binary = binary + binary_temp[temp_s];

					}

				wants_label = false;
				return;

			}

		void iformat(String s1, String s2, String s3, int r1, int r2, int r3)
			{
				type = 'i';
				String temp = new String();
				binary_temp = new String[5];
				binary_temp[0] = s1;
				 System.out.println(r1+":"+r2+":"+r3);
				temp = "";
				binary_temp[1] = Integer.toBinaryString(r1);
				for (int i = 0; i < 5 - binary_temp[1].length(); i++)
					{
						if (r1 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				// System.out.println(temp);
				temp = temp + binary_temp[1];
				// System.out.println(temp);
				//temp=temp.substring(temp.length()-5,temp.length());
					//1:40
				binary_temp[1] = temp;

				temp = "";

				binary_temp[2] = s2;
				//// System.out.println(binary_temp[2]);

				binary_temp[3] = Integer.toBinaryString(r2);
				for (int i = 0; i < 5 - binary_temp[3].length(); i++)
					{
						if (r2 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[3];
				temp = temp.substring(temp.length() - 5, temp.length());

				binary_temp[3] = temp;
				//// System.out.println(binary_temp[3]);

				temp = "";

				binary_temp[4] = Integer.toBinaryString(r3);
				for (int i = 0; i < 12 - binary_temp[4].length(); i++)
					{
						if (r3 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[4];
				temp = temp.substring(temp.length() - 12, temp.length());

				binary_temp[4] = temp;
				//// System.out.println(binary_temp[4]);
				temp = "";

				binary = new String();

				for (int temp_s = 4; temp_s >= 0; temp_s--)
					{
						binary = binary + binary_temp[temp_s];

					}
				System.out.println(binary);

				wants_label = false;
				return;

			}

		void sformat(String s1, String s2, String s3, int r1, int r2, int r3)
			{
				binary_temp = new String[6];
				String temp = new String();

				binary_temp[0] = s1;

				binary_temp[2] = s2;
				temp = "";

				binary_temp[3] = Integer.toBinaryString(r1);
				for (int i = 0; i < 5 - binary_temp[3].length(); i++)
					{
						if (r1 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[3];
				temp = temp.substring(temp.length() - 5, temp.length());

				binary_temp[3] = temp;
				//// System.out.println(binary_temp[3]);

				temp = "";

				binary_temp[4] = Integer.toBinaryString(r2);
				for (int i = 0; i < 5 - binary_temp[4].length(); i++)
					{
						if (r2 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[4];
				temp = temp.substring(temp.length() - 5, temp.length());

				binary_temp[4] = temp;

				temp = "";

				binary_temp[5] = Integer.toBinaryString(r3);

				for (int i = 0; i < 12 - binary_temp[5].length(); i++)
					{
						if (r3 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[5];///////////
				temp = temp.substring(temp.length() - 12, temp.length());

				binary_temp[5] = temp.substring(0, 7);
				binary_temp[1] = temp.substring(7, 12);

				binary = new String();
				for (int temp_s = 5; temp_s >= 0; temp_s--)
					{
						binary = binary + binary_temp[temp_s];
					}
				// for(String h:binary_temp)
				// //System.out.println(h);
				wants_label = false;
				return;
			}

		void sbformat(String s1, String s2, String s3, int r1, int r2, String lab)
			{
				type = 'b';
				String temp = new String();
				binary_temp = new String[6];

				binary_temp[0] = s1;

				temp = "";
				binary_temp[2] = s2;

				binary_temp[3] = Integer.toBinaryString(r1);
				for (int i = 0; i < 5 - binary_temp[3].length(); i++)
					{
						if (r1 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[3];
				temp = temp.substring(temp.length() - 5, temp.length());

				binary_temp[3] = temp;

				temp = "";

				binary_temp[4] = Integer.toBinaryString(r2);
				for (int i = 0; i < 5 - binary_temp[4].length(); i++)
					{
						if (r2 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[4];
				temp = temp.substring(temp.length() - 5, temp.length());

				binary_temp[4] = temp;
				wants_label = true;
				required_label = lab;
				return;

			}

		void uformat(String s1, String s2, String s3, int r1, int iv)
			{
				type = 'u';
				String temp = new String();

				binary_temp = new String[3];
				binary_temp[0] = s1;

				binary_temp[1] = Integer.toBinaryString(r1);
				for (int i = 0; i < 5 - binary_temp[1].length(); i++)
					{

						if (r1 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[1];
				temp = temp.substring(temp.length() - 5, temp.length());

				binary_temp[1] = temp;

				temp = "";

				binary_temp[2] = Integer.toBinaryString(iv);
				for (int i = 0; i < 32 - binary_temp[2].length(); i++)
					{

						if (iv < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[2];
				temp = temp.substring(temp.length() - 32, temp.length());

				binary_temp[2] = temp.substring(0, 20);

				binary = new String();

				for (int temp_s = 2; temp_s >= 0; temp_s--)
					{

						binary = binary + binary_temp[temp_s];

					}

				wants_label = false;
				return;
			}

		void ujformat(String s1, String s2, String s3, int r1, String lab)
			{
				type = 'j';
				String temp = new String();
				binary_temp = new String[3];
				binary_temp[0] = s1;

				binary_temp[1] = Integer.toBinaryString(r1);
				for (int i = 0; i < 5 - binary_temp[1].length(); i++)
					{
						if (r1 < 0)
							temp = temp + "1";
						else
							temp = temp + "0";
					}
				temp = temp + binary_temp[1];
				temp = temp.substring(temp.length() - 5, temp.length());

				binary_temp[1] = temp;

				required_label = lab;
				wants_label = true;
				return;

			}

		void substitute(int val, instruction ins)
			{
				String local, temp = "";
				// System.out.println(val);
				if (ins.type == 'b')
					{
						local = Integer.toBinaryString(val);
						// System.out.println(local);
						for (int i = 0; i < 13 - local.length(); i++)
							{
								if (val < 0)
									temp = temp + "1";
								else
									temp = temp + "0";
							}
						local = temp + local;
						local = local.substring(local.length() - 13, local.length());
						ins.binary_temp[1] = local.substring(8, 12);
						ins.binary_temp[1] = ins.binary_temp[1] + local.substring(1, 2);

						ins.binary_temp[5] = local.substring(0, 1);
						ins.binary_temp[5] = ins.binary_temp[5] + local.substring(2, 8);

						ins.binary = new String();
						for (int temp_s = 5; temp_s >= 0; temp_s--)
							{
								ins.binary = ins.binary + ins.binary_temp[temp_s];
							}
						// System.out.println("b-----------------------------------------");
						//
						// System.out.println(h);
						// System.out.println("b-----------------------------------------/");
					}

				else if (ins.type == 'j')
					{
						local = Integer.toBinaryString(val);
						for (int i = 0; i < 21 - local.length(); i++)
							{
								if (val < 0)
									temp = temp + "1";
								else
									temp = temp + "0";
							}
						local = temp + local;
						local = local.substring(local.length() - 21, local.length());
						ins.binary_temp[2] = local.substring(0, 1);
						ins.binary_temp[2] = ins.binary_temp[2] + local.substring(10, 20);
						ins.binary_temp[2] = ins.binary_temp[2] + local.substring(9, 10);
						ins.binary_temp[2] = ins.binary_temp[2] + local.substring(1, 9);
						ins.binary = new String();
						for (int temp_s = 2; temp_s >= 0; temp_s--)
							{
								ins.binary = ins.binary + ins.binary_temp[temp_s];
							}
						// System.out.println("j-----------------------------------------");
						//
						// System.out.println(h);
						// System.out.println("j-----------------------------------------/");

					}
			}

		static void assign_labels(instruction[] all)
			{
				instruction temp = new instruction();
				instruction temp_2 = new instruction();
				for (int ext = 0; ext < all.length; ext++)
					{
						temp = all[ext];

						if (temp.wants_label)
							{
								for (int in = 0; in < all.length; in++)
									{
										temp_2 = all[in];
										if (temp_2.has_label)
											{
												if (temp_2.self_label.equals(temp.required_label))
													{
														temp.substitute((in - ext) * 4, temp);
													}
											}
									}
							}
						try
							{
								int hex_temp = (int) Long.parseLong(temp.binary, 2);
								temp.hex = Integer.toHexString(hex_temp);
								temp.hex = "0x" + temp.hex;

							} catch (Exception e)
							{
								System.out.println("No matching label found || Null string detected");
							}

					}
			}

	}

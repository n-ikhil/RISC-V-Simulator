/*
 * this class takes one instruction line as input to
 * constructor and returns tokens and if label is found it returns label
 * in label string and is label is set to true
*/
package assembler;

import java.util.ArrayList;

class LexicalAnalyser
	{

		boolean ifDelimiter(char c)
			{
				if (c == ' ' || c == '\t' || c == '(' || c == ')' || c == ','||c==':')
					return true;
				return false;
			}

		public ArrayList<String> Tokens = new ArrayList<String>();
		public String label;
		boolean islabel = false;
		StringBuilder word = new StringBuilder();
           

		// ArrayList<Integer> tokens = new ArrayList<>(Integer);
		LexicalAnalyser(String line,boolean assemblydirective)
			{
                                
				boolean foundcomment = false;
				boolean isDelimitter = false;
                                //boolean instring=false;
                                boolean is=false;
				StringBuilder str = new StringBuilder();
				for (int i = 0; i < line.length(); i++)
					{
						char temp = line.charAt(i);
						if (temp == '#')
							foundcomment = true;
						else if (temp == ':'&&!assemblydirective)
							{
								islabel = true;
								if (Tokens.size() != 0)
									{
										System.out.println("error");
									}

							} else if (ifDelimiter(temp))
							{
								isDelimitter = true;

							}
                                                        else
                                                        {
                                                            is=true;
                                                        }
						if (foundcomment)
							return;
						if (isDelimitter&&is)
							{
								isDelimitter = false;
                                                                is=false;
								Tokens.add(str.toString());
								str.delete(0, str.length());
                                                                
							} else if (islabel&&!assemblydirective)
							{
								label = str.toString();
								str.delete(0, str.length());
								islabel = true;
								//return;
							} else if(is)
							{
								str.append(temp);
							}
					}
				Tokens.add(str.toString()); // adding the last token
				str.delete(0, str.length());
			}

		/*
		 * public static void main(String args[]) {
		 * 
		 * LexicalAnalyser l=new LexicalAnalyser("label:add X1 X2 X3 ");
		 * System.out.println(l.label); System.out.println(l.Tokens.get(3));
		 * 
		 * 
		 * }
		 */
	}

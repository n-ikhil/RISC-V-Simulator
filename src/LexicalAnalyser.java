package assembler;

import java.util.Scanner;
import java.util.ArrayList;

class LexicalAnalyser {
	bool ifDelimiter(char c) {
		if (c == ' ' || temp == '\t' || temp == '(' || temp == ')' || temp == ',')
			return true;
	}

	ArrayList<String> Tokens = new ArrayList<String>();
	String label;
	bool islabel = false;
	StringBuilder word = new StringBuilder();

	// ArrayList<Integer> tokens = new ArrayList<>(Integer);
	LexicalAnalyser(String line)
	{
		//bool label=false;
		bool foundcomment=false;
		bool isDelimitter=false;

		StringBuilder str =new StringBuilder();
		for(int i=0;i<line.length();i++)
		{
			char temp=line.charAt(i)
			if(temp=='#')
				foundcomment=true;
			else if(temp==':')
			{
				islabel=true;
				if(Tokens.size()==0)
				{
					System.out.println("error");
				}

			}
			else if(ifDelimiter(temp))
			{
				isDelimitter=true;
			
			}
			if(foundcomment)
				break;
			if(isDelimitter)
			{
					isDelimitter=false;
					Tokens.add(str.toString());
			}
			else if(islabel)
			{
				
			}
		}
	}
}

package datapath;

public class branchobject {
	int branchaddress=0;
	int targetaddress=0;
	int prediction=0;
	int two_bit=0;
	boolean is_there=false;

	void add(int branchaddress,int targetaddress)
	{
		this.branchaddress=branchaddress;
		this.targetaddress=targetaddress;
	}
	void updateprediction(int predictedvalue)
	{
		if(predictedvalue==1) two_bit++;
		else two_bit--;
		if(two_bit<0) two_bit=0;
		if(two_bit>3)two_bit=3;
		if(two_bit<2) prediction=0;
		else prediction=1;

		this.prediction=predictedvalue;
	}
}

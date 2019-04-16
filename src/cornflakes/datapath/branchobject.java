package datapath;

public class branchobject {
	int branchaddress=0;
	int targetaddress=0;
	boolean prediction=false;
	boolean is_there=false;
	
	void add(int branchaddress,int targetaddress)
	{
		this.branchaddress=branchaddress;
		this.targetaddress=targetaddress;
	}
	void updateprediction(boolean predictedvalue)
	{
		this.prediction=predictedvalue;
	}
}

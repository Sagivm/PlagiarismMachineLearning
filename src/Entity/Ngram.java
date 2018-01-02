package Entity;

import java.util.ArrayList;

public class Ngram {
	public ArrayList<Term> data=null;
	public Ngram(String segment)
	{
		data=new ArrayList<Term>();
		for(int i=0;i<segment.length()-2;i++)
		{
			String sub=segment.substring(i,i+3);
			for(int j=0;j<data.size();i++)
			{
				if(Term.cmpTerm(data.get(j).getTerm(), sub))
					data.get(j).incFrequncy();
			}
		}
		
	}
	public ArrayList<Term> getData() {
		return data;
	}

	public void setData(ArrayList<Term> data) {
		this.data = data;
	}
	

}

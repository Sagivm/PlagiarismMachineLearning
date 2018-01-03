package Entity;

import java.util.ArrayList;

public class Ngram {
	public ArrayList<Term> data=null;
	public Ngram(String segment)
	{
		data=new ArrayList<Term>();
		for(int i=0;i<segment.length()-2;i++)
		{
			boolean newTerm=true;
			String sub=segment.substring(i,i+3);
			for(int j=0;j<data.size();j++)
			{
				if(Term.cmpTerm(data.get(j).getTerm(), sub))
				{
					newTerm=false;
					data.get(j).incFrequncy();
				}
			}
			if(newTerm)
				data.add(new Term(sub));
		}
		
	}
	public ArrayList<Term> getData() {
		return data;
	}

	public void setData(ArrayList<Term> data) {
		this.data = data;
	}
	

}

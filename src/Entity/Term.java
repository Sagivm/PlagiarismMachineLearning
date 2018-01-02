package Entity;

public class Term {
	
	private String term;
	private int frequncy;
	
	public Term(String term, int frequncy) {
		super();
		this.term = term;
		this.frequncy = frequncy;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public int getFrequncy() {
		return frequncy;
	}
	public void incFrequncy() {
		this.frequncy++;
	}
	public static boolean cmpTerm(String a,String b)
	{
		return a.equals(b);
	}
	
	

}

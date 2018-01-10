package Entity;

public class Term {
	
	private String term;
	private int frequncy;
	
	public Term(String term) {
		super();
		this.term = term;
		this.frequncy = 1;
	}
	public Term(String term,int frequency) {
		super();
		this.term = term;
		this.frequncy = frequency;
	}
	public Term(Term term) {
		super();
		this.term = term.getTerm();
		this.frequncy = term.getFrequncy();
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public void setFrequency(int frequncy) {
		this.frequncy=frequncy;
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

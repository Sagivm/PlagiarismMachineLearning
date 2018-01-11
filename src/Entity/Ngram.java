package Entity;

import java.util.ArrayList;

public class Ngram {
	public ArrayList<Term> data = null;

	public Ngram(String segment) {
		data = new ArrayList<Term>();
		for (int i = 0; i < segment.length() - 2; i++) {
			boolean newTerm = true;
			String sub = segment.substring(i, i + 3);
			for (int j = 0; j < data.size(); j++) {
				if (Term.cmpTerm(data.get(j).getTerm(), sub)) {
					newTerm = false;
					data.get(j).incFrequncy();
				}
			}
			if (newTerm)
				data.add(new Term(sub));
		}

	}

	public Ngram() {
		data = new ArrayList<Term>();
	}

	public ArrayList<Term> getData() {
		return data;
	}

	public void setData(ArrayList<Term> data) {
		this.data = data;
	}

	public int getTermValue(Term term) {
		for (int i = 0; i < data.size(); i++) {
			if (Term.cmpTerm(data.get(i).getTerm(), term.getTerm())) {
				return data.get(i).getFrequncy();
			}
		}
		return 0;
	}

	public int getTermindex(Term term) {
		for (int i = 0; i < data.size(); i++) {
			if (Term.cmpTerm(data.get(i).getTerm(), term.getTerm())) {
				return i;
			}
		}
		return -1;
	}

	public void alignToGeneral(Ngram generalNgram) {
		ArrayList<Term> data = new ArrayList<Term>();
		for (int i = 0; i < generalNgram.getData().size(); i++) {
			Term term = generalNgram.data.get(i);
			int index = this.getTermindex(term);
			if (index == -1)
				data.add(new Term(term.getTerm(), 0));
			else
				data.add(new Term(this.getData().get(index)));
		}
		setData(data);
	}

	public float avg() {
		float sum = 0;
		for (int i = 0; i < data.size(); i++) {
			sum += data.get(i).getFrequncy();
		}
		return sum / data.size();
	}
	

}

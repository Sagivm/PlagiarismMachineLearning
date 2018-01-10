package Controller;

import java.util.ArrayList;

import Entity.Ngram;
import Entity.Term;

public class CorrelationCoefficientController {
	public static double computeSingleZV(ArrayList<Ngram> ngrams, Ngram query) {
		float zv = 0;
		for (int i = 0; i < ngrams.size(); i++)
			zv += computePearson(ngrams.get(i), query);
		return zv / ngrams.size();
	}
//check for zero values in unimplemented dimensions
	public static double computePearson(Ngram n, Ngram m) {
		double top = 0, avgN = n.avg(), avgM = m.avg();
		for (int i = 0; i < n.getData().size(); i++) {
			Term termN = n.getData().get(i);
			Term termM = m.getData().get(i);
			top += (termN.getFrequncy() - avgN) * (termM.getFrequncy() - avgM);
		}
		double botN = 0, botM = 0;
		for (int i = 0; i < n.getData().size(); i++) {
			Term termN = n.getData().get(i);
			Term termM = m.getData().get(i);
			botN+=Math.pow(termN.getFrequncy() - avgN, 2);
			botM+=Math.pow(termM.getFrequncy() - avgM, 2);
		}
		return (top/Math.sqrt(botN*botM));
	}
	
	
}

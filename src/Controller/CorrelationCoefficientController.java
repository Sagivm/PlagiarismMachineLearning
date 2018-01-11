package Controller;

import java.util.ArrayList;

import Entity.Ngram;
import Entity.Term;

public class CorrelationCoefficientController {
	public static double computeSingleZV(ArrayList<Ngram> previousNgrams, Ngram query) {
		float zv = 0;
		for (int i = 0; i < previousNgrams.size(); i++)
			zv += computePearson(previousNgrams.get(i), query);
		return zv / previousNgrams.size();
	}

	// check for zero values in unimplemented dimensions
	private static double computePearson(Ngram n, Ngram m) {
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
			botN += Math.pow(termN.getFrequncy() - avgN, 2);
			botM += Math.pow(termM.getFrequncy() - avgM, 2);
		}
		return (top / Math.sqrt(botN * botM));
	}

	public static double DZV(ArrayList<Ngram> previousNgramsN, Ngram n, ArrayList<Ngram> previousNgramsM, Ngram m) {
		double a = computeSingleZV(previousNgramsN, n) + computeSingleZV(previousNgramsM, m);
		double b = computeSingleZV(previousNgramsM, n) + computeSingleZV(previousNgramsN, m);
		return Math.abs(a - b);

	}
	// x and y are indexes in n grams
	public static double DZVE(ArrayList<Ngram> ngrams, int x,int y) {
		ArrayList<Double> piX=new ArrayList<>();
		ArrayList<Double> piY=new ArrayList<>();
		ArrayList<Double> result=new ArrayList<>();
		ArrayList<Ngram> prevX=(ArrayList<Ngram>) ngrams.subList(0, x);
		ArrayList<Ngram> prevY=(ArrayList<Ngram>) ngrams.subList(0, y);
		Ngram ngramX=ngrams.get(x);
		Ngram ngramY=ngrams.get(y);
		for(int i=0;i<ngrams.size();i++)
		{
			ArrayList<Ngram> prev=new ArrayList(ngrams.subList(0,i));
			piX.add(DZV(prevX, ngramX, prev, ngrams.get(i)));
			piX.add(DZV(prevY, ngramY, prev, ngrams.get(i)));
		}
		for(int i=0;i<piX.size();i++)
		{
			result.add(piX.get(i)-piY.get(i));
		}
		return length(result);

	}
	public static double length(ArrayList<Double> vector)
	{
		double sum=0;
		for(int i=0;i<vector.size();i++)
			sum+=Math.pow(vector.get(i), 2.0);
		return Math.sqrt(sum);
	}

}

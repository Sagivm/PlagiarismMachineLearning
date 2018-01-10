package Controller;

import java.util.ArrayList;
import Entity.Ngram;
import Entity.Term;

public class NgramController {
	private static Ngram generalNgram;
	public static  ArrayList<Ngram> GenerateNgrams(ArrayList<String> segments)
	{
		ArrayList<Ngram> ngrams=new ArrayList<Ngram>();
		for(int i=0;i<segments.size();i++)
			ngrams.add(new Ngram(segments.get(i)));
		generateGeneralNgram(ngrams);
		return aligndim(ngrams);
	}
	private static void generateGeneralNgram(ArrayList<Ngram> ngrams)
	{
		generalNgram=new Ngram();
		for(int i=0;i<ngrams.size();i++)
		{
			Ngram ngram=ngrams.get(i);
			for(int j=0;j<ngram.getData().size();j++)
			{
				Term term=ngram.getData().get(j);
				int index=generalNgram.getTermindex(term);
				if(index==-1)//if general contains term
					generalNgram.getData().add(new Term(term));
				else
					generalNgram.getData().get(index).setFrequency(generalNgram.getData().get(index).getFrequncy()+term.getFrequncy());
			}
		}
	}
	private static ArrayList<Ngram> aligndim(ArrayList<Ngram> rawNgrams)
	{
		ArrayList<Ngram> ngrams=new ArrayList<Ngram>();
		for(int i=0;i<rawNgrams.size();i++)
		{
			Ngram rawNgram=rawNgrams.get(i);
			rawNgram.alignToGeneral(generalNgram);
			ngrams.add(rawNgram);
		}
		return ngrams;
		
	}
	
	
			
}

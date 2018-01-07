package Controller;

import java.util.ArrayList;
import Entity.Ngram;

public class NgramGeneratorController {
	
	public static  ArrayList<Ngram> GenerateNgrams(ArrayList<String> segments)
	{
		ArrayList<Ngram> ngrams=new ArrayList<Ngram>();
		for(int i=0;i<segments.size();i++)
			ngrams.add(new Ngram(segments.get(i)));
		return ngrams;
	}
}

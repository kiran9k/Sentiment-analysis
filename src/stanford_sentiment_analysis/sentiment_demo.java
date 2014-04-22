package stanford_sentiment_analysis;

import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.SentimentAnnotator;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.TypesafeMap.Key;

public class sentiment_demo {

	public static void sentiment_analysis(String line)
	{
		//Uses Stanford NLP sentimnet analysis
		//found in latest model released from stanford
		// ver 3.3.1
		//applies sentiment analysis to text 
		
		Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        int mainSentiment = 0;
        if (line != null && line.length() > 0) {
        	int longest = 0;
            Annotation annotation = pipeline.process(line);
            for (CoreMap sentence : annotation.get(CoreAnnotations.SentencesAnnotation.class)) {
            	System.out.println(sentence);
            	for (Tree token: sentence.get(SentimentCoreAnnotations.AnnotatedTree.class))
   	    	 	{
            		//System.out.println(token);
   	    	 	}
            	Tree tree = sentence.get(SentimentCoreAnnotations.AnnotatedTree.class);
                int sentiment = RNNCoreAnnotations.getPredictedClass(tree);
                System.out.println(sentiment);
                String partText = sentence.toString();
                //System.out.println(partText);
                if (partText.length() > longest) {
                    mainSentiment = sentiment;
                    longest = partText.length();
                }
 
            }
        }
        if(mainSentiment==2)
        {
        	System.out.println("Average");
        }
        else if(mainSentiment>2)
        {
        	System.out.println("Positive");
        }
        else if(mainSentiment<2)
        {
        	System.out.println("Negative ");
        }
        
        if (mainSentiment == 2 || mainSentiment > 4 || mainSentiment < 0) {
            //return null;
        }
	}
	public static void main(String[] args)
	{
		sentiment_analysis("All species in the genus are closely related, and it is difficult to identify Aurelia medusae without genetic sampling; most of what follows applies equally to all species of the genus.");
	}
}

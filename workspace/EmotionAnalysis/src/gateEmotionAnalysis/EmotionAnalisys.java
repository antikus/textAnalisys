package gateEmotionAnalysis;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import gate.Annotation;
import gate.AnnotationSet;
import gate.Corpus;
import gate.CorpusController;
import gate.Document;
import gate.Factory;
import gate.FeatureMap;
import gate.Gate;
import gate.ProcessingResource;
import gate.creole.ANNIEConstants;
import gate.creole.ExecutionException;
import gate.creole.ResourceInstantiationException;
import gate.creole.SerialAnalyserController;
import gate.gui.docview.AnnotationList;
import gate.persist.PersistenceException;
import gate.util.GateException;
import gate.util.Out;
import gate.util.persistence.PersistenceManager;

public class EmotionAnalisys {
	
	private static SerialAnalyserController annieController;

	public static void main(String [] args) throws GateException, IOException{  
		try{    
	      Gate.init();
//	      try {
//	    	  Gate.getCreoleRegister().registerDirectories(new File(Gate.getPluginsHome(),ANNIEConstants.PLUGIN_DIR).toURI().toURL());
//	      } 
//	      catch (MalformedURLException e) { e.printStackTrace(); }
	    }
		catch(GateException e){ e.printStackTrace(); }

//		Out.prln("Initialising ANNIE...");
//	  // create a serial analyser controller to run ANNIE with
//		annieController = (SerialAnalyserController)
//		Factory.createResource("gate.creole.SerialAnalyserController", Factory.newFeatureMap(),
//								Factory.newFeatureMap(), "ANNIE_" + Gate.genSym());
//	  // load each PR as defined in ANNIEConstants
//		for(int i = 0; i < ANNIEConstants.PR_NAMES.length; i++){
//			FeatureMap params = Factory.newFeatureMap(); // use default parameters
//			ProcessingResource pr = (ProcessingResource)
//			Factory.createResource(ANNIEConstants.PR_NAMES[i], params);
//			// add the PR to the pipeline controller
//			annieController.add(pr);
//		}
//		Out.prln("...ANNIE loaded");

		
	  	loadSavedApplication();
	  	}
	public void EmotionAnalisys() throws GateException, IOException{
		Gate.init();
		loadSavedApplication();
	}
	public static void loadSavedApplication() throws PersistenceException, ResourceInstantiationException, IOException, ExecutionException{
			CorpusController applicationController = (CorpusController)  
					PersistenceManager.loadObjectFromUrl(new URL("file:///C:/Users/Anatoli/Desktop/GATE/gate_workspace/GateData/ApplicationModel/EmotionAnalisysApplication.gapp"));

			Document doc = loadDocument(new File("file:///c:/Users/Anatoli/Desktop/cv009_29417.txt.xml"));
			Corpus corpus = loadCorpus(doc);
		  	applicationController.setCorpus(corpus);
		  	applicationController.execute();
		  	AnnotationSet allAnnotation = (AnnotationSet) doc.getAnnotations();
		  	//System.out.println(allAnnotation.get("Text"));
		  	AnnotationSet textAnn = allAnnotation.get("Text");
		  	for (Iterator iterator = textAnn.iterator(); iterator.hasNext();) {
				Annotation annotation = (Annotation) iterator.next();
				System.out.println(annotation.toString());	
			}
		  	
		  	Out.prln("Application complete");	
	}
	
	public static Document loadDocument(File file) throws ResourceInstantiationException{
		FeatureMap params = Factory.newFeatureMap();
		params.put("sourceUrl", file.getPath());
		params.put("preserveOriginalContent", new Boolean(true));
	  	params.put("collectRepositioningInfo", new Boolean(true));
	  	Out.prln("Creating doc for " + file.getPath().toString());
	  	Document doc = (Document)Factory.createResource("gate.corpora.DocumentImpl", params);
	  	return doc;
	}
	
	public static Corpus loadCorpus(Document doc) throws ResourceInstantiationException{
		Corpus corpus = (Corpus)Factory.createResource("gate.corpora.CorpusImpl");
	  	corpus.add(doc);
	  	return corpus;
	}
	
	public void processingDocument(){
		
	}
}


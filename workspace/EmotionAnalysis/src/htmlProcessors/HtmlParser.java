package htmlProcessors;

import org.jsoup.Jsoup;

public class HtmlParser {

		String docToParse = null;
		String parsedDoc = null;
		
		public HtmlParser(String loadedDoc) {
			docToParse = loadedDoc;
		}
		
		public String parse(){
			StringBuilder sb = new StringBuilder(this.docToParse);
		    this.parsedDoc = Jsoup.parse(sb.toString()).text();
		    return this.parsedDoc;
		}   
}
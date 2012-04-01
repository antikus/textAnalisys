package htmlProcessors;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.apache.commons.io.IOUtils;

public class htmlLoader {
	
	private URL url = null;
	private String loadedHTML = null;
	
	public htmlLoader(String loadingUrl) throws IOException{
		this.url = new URL(loadingUrl);
		this.executeLoading();
	}
	
	public String getLoadedHTML(){
		return this.loadedHTML;
	}
	
	public void executeLoading() throws IOException{
		if (this.url != null){
			URL url = this.url;
			URLConnection con = url.openConnection();
			Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
			Matcher m = p.matcher(con.getContentType());
			/* If Content-Type doesn't match this pre-conception, choose default and 
			 * hope for the best. */
			String charset = m.matches() ? m.group(1) : "utf-8";
			this.loadedHTML = IOUtils.toString(con.getInputStream(), charset);
		}
	}

}

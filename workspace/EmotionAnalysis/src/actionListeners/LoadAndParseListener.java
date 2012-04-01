package actionListeners;

import htmlProcessors.HtmlParser;
import htmlProcessors.htmlLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextArea;
import javax.swing.JTextField;

public class LoadAndParseListener implements ActionListener {
	
	private JTextField url = null;
	private JTextArea textCont = null;
	
	public LoadAndParseListener(JTextField urlContainer, JTextArea parsedDocCont){
		this.url = urlContainer;
		this.textCont = parsedDocCont;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			htmlLoader loader = new htmlLoader(this.url.getText());
			HtmlParser parser = new HtmlParser(loader.getLoadedHTML());
			String parsedDoc = parser.parse();
			this.textCont.setText(parsedDoc);
		} catch (IOException e) {
			System.out.println("Some errors with html loading.");
		}
	}
}

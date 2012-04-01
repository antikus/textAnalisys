package actionListeners;

import htmlProcessors.HtmlParser;
import htmlProcessors.htmlLoader;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import frame.MainFrame;

public class LoadFromURLListener implements ActionListener {

	private JTextArea textCont = null;
	
	public LoadFromURLListener(JTextArea textCont){
		this.textCont = textCont;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		try {
            String val = (String) JOptionPane.showInputDialog(null, "URL:",
                    "Please, enter the URL", JOptionPane.PLAIN_MESSAGE, null, null, "http://");
            String url = val;
            htmlLoader loader = new htmlLoader(val);
			HtmlParser parser = new HtmlParser(loader.getLoadedHTML());
			String parsedDoc = parser.parse();
			this.textCont.setText(parsedDoc);        
        } catch (Exception e1) {}
	}

}

package frame;

import htmlProcessors.HtmlParser;

import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.commons.io.IOUtils;

import java.util.regex.Matcher;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private static final int WIDTH = 800; 
	private static final int HEIGHT = 600; 
	private JFileChooser fileChooser = null;
	private Container contentContainer;
	private Content frameContent = new Content();
	
	public MainFrame() throws IOException { 
		super( "GATE App" ); 
		setSize( WIDTH, HEIGHT ); 
		Toolkit kit = Toolkit.getDefaultToolkit(); 
		setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2); 
		JMenuBar menuBar = new JMenuBar(); 
		setJMenuBar( menuBar ); 
		JMenu fileMenu = new JMenu("File"); 
		menuBar.add(fileMenu); 
		
		Action openDocumentAction = new AbstractAction("Open") { 
			public void actionPerformed(ActionEvent event) { 
				if ( fileChooser == null ) { 
					fileChooser = new JFileChooser(); 
					fileChooser.setCurrentDirectory(new File(".")); 
				} 
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					System.out.println(fileChooser.getSelectedFile());
				}
			}
		}; 
		
		Action loadDocumentAction = new AbstractAction("Load from URL") { 
			public void actionPerformed(ActionEvent event) { 
				if ( fileChooser == null ) { 
					fileChooser = new JFileChooser(); 
					fileChooser.setCurrentDirectory(new File(".")); 
				} 
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					System.out.println(fileChooser.getSelectedFile());
				}
			}
		}; 
		
		fileMenu.add( openDocumentAction );
		fileMenu.add( loadDocumentAction );
	
		
		URL url = new URL("http://www.bbc.co.uk/news/world-asia-17503733");
		URLConnection con = url.openConnection();
		Pattern p = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
		Matcher m = p.matcher(con.getContentType());
		/* If Content-Type doesn't match this pre-conception, choose default and 
		 * hope for the best. */
		String charset = m.matches() ? m.group(1) : "utf-8";
		String str = IOUtils.toString(con.getInputStream(), charset);
		System.out.println("Loaded DOCUMENT:\n" + str + "End\n");
		HtmlParser parser = new HtmlParser(str);
		String parsedDoc = parser.parse();
		System.out.println("Parsed DOCUMENT:\n" + parsedDoc + "End\n");
	
		JScrollPane jScrollPane1 = new JScrollPane();
		JTextArea text = new JTextArea();

        text.setColumns(5);
        text.setRows(5);
        text.setText(parsedDoc);
	    text.setMargin(new java.awt.Insets(10, 12, 12, 12));
        jScrollPane1.setViewportView(text);

		
		contentContainer = getContentPane();
		contentContainer.add(text);
	} 
	
	private void initComponents() {
		
      
    }
	
	
	
	public static void main(String[] args) throws IOException {
		MainFrame frame = new MainFrame(); 
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
		frame.setVisible(true);
	} 
}

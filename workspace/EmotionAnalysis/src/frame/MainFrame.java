package frame;

import htmlProcessors.HtmlParser;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Event;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import org.apache.commons.io.IOUtils;

import actionListeners.CloseAppListener;
import actionListeners.LoadAndParseListener;
import actionListeners.LoadFromURLListener;
import actionListeners.OpenDocumentListener;
import actionListeners.RunAppListener;

import java.util.regex.Matcher;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private static final int WIDTH = 800; 
	private static final int HEIGHT = 600; 
	private JFileChooser fileChooser = null;
	private Container contentContainer;
	private JTextArea userText = new JTextArea(15, 60);
	private JTextField userInput = new JTextField(40);
	private JPanel controls = new JPanel(new BorderLayout());
	private int fontSize = 14;
	private JButton runApp = new JButton("Load & Parse");
	
	public MainFrame() throws IOException { 
		super( "GATE App" ); 
		setSize( WIDTH, HEIGHT ); 
		Toolkit kit = Toolkit.getDefaultToolkit(); 
		setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2); 
		JMenuBar menuBar = new JMenuBar(); 
		setJMenuBar( menuBar ); 
		JMenu fileMenu = new JMenu("File"); 
		menuBar.add(fileMenu); 
		
		JMenuItem item = new JMenuItem("Open");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Event.CTRL_MASK));
		item.setMnemonic(KeyEvent.VK_C);
		fileMenu.add(item);
		item.addActionListener(new OpenDocumentListener(userText));
				
		item = new JMenuItem("Load");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK));
		item.setMnemonic(KeyEvent.VK_C);
		fileMenu.add(item);
		item.addActionListener(new LoadFromURLListener(userText));
		
		item = new JMenuItem("Exit");
		item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Event.CTRL_MASK));
		item.setMnemonic(KeyEvent.VK_X);
		fileMenu.add(item);
		item.addActionListener(new CloseAppListener());
	

		JPanel parsedTextContainer = new JPanel();
		JPanel urlContainer = new JPanel();
		parsedTextContainer.add(new JScrollPane(userText)); // Decorator pattern
		JLabel prompt = new JLabel("URL for loading:");
		runApp.addActionListener(new LoadAndParseListener(userInput, userText));
		
		urlContainer.add(prompt);
		urlContainer.add(userInput);
		urlContainer.add(runApp);
		runApp.addActionListener(new RunAppListener());
		
        userText.setFont(new Font("Helvetica", Font.PLAIN, fontSize));
		userText.setEditable(true);
		userText.setLineWrap(true);
		userText.setWrapStyleWord(true);

		
		contentContainer = getContentPane();
		contentContainer.add(parsedTextContainer);
		contentContainer.add(urlContainer, BorderLayout.NORTH);
	} 
	
	public static void main(String[] args) throws IOException {
		MainFrame frame = new MainFrame(); 
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
		frame.setVisible(true);
	} 
}
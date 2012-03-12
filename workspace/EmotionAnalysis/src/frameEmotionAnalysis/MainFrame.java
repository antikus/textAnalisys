package frameEmotionAnalysis;

import gate.creole.ResourceInstantiationException;
import gateEmotionAnalysis.EmotionAnalisys;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;


@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private static final int WIDTH = 800; 
	private static final int HEIGHT = 600; 
	private JFileChooser fileChooser = null; 
	private static EmotionAnalisys emotionAnalisysController;
	
	public MainFrame() { 
		super( "EmotionApplication" ); 
		setSize( WIDTH, HEIGHT ); 
		Toolkit kit = Toolkit.getDefaultToolkit(); 
		setLocation((kit.getScreenSize().width - WIDTH)/2, (kit.getScreenSize().height - HEIGHT)/2); 
		JMenuBar menuBar = new JMenuBar(); 
		setJMenuBar( menuBar ); 
		JMenu fileMenu = new JMenu( "" ); 
		menuBar.add( fileMenu ); 
		
		Action openDocumentAction = new AbstractAction( "" ) { 
			public void actionPerformed( ActionEvent event ) { 
				if ( fileChooser == null ) { 
					fileChooser = new JFileChooser(); 
					fileChooser.setCurrentDirectory( new File(".") ); 
				} 
				if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION){
					System.out.println(fileChooser.getSelectedFile());
					//try {
						//emotionAnalisysController.loadDocument(fileChooser.getSelectedFile());
					//} catch (ResourceInstantiationException e) {
						// TODO Auto-generated catch block
					//	e.printStackTrace();
					//}
					
				}
			}
		}; 
		fileMenu.add( openDocumentAction ); 
		//JFRAME.	
	} 
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame(); 
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE ); 
		frame.setVisible(true); 
		emotionAnalisysController = new EmotionAnalisys();
	} 
}

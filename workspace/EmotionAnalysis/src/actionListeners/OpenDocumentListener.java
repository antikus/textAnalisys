package actionListeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import frame.MainFrame;
import gate.creole.morph.ReadFile;

public class OpenDocumentListener implements ActionListener {
	
	private JFileChooser fileChooser = null;
	private JTextArea textCont = null;
	
	public OpenDocumentListener(JTextArea textCont){
		this.textCont = textCont;
	}
	
	public void actionPerformed(ActionEvent event) { 
		if ( fileChooser == null ) { 
			fileChooser = new JFileChooser(); 
			fileChooser.setCurrentDirectory(new File(".")); 
		} 
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
			try
			{
			    FileInputStream fin = new FileInputStream(fileChooser.getSelectedFile());
			    String loadedText = "";
		        BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));
		        String currentLine;;
				while ( (currentLine = myInput.readLine()) != null) {  
		             loadedText += currentLine;
		        }
			    
			    this.textCont.setText(loadedText);
			    fin.close();		
			}
			catch (IOException e) {
				System.err.println ("Unable to read from file");
			}
		}
	}

}

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;


public class GUIBar extends JFrame {

	static JFrame f = new JFrame();
	
	
	

	public static void main(String args[]){
	//	System.out.println(args);
	
		JPanel panel2 = new JPanel();
		
		JButton  btn = new JButton("<HTML><FONT color=\"#009900\"><U>Bar plot Representation of All Emoticons</U><FONT></HTML>");
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//JDialog d= new JDialog(f, "woah "+n, true);
				
				try {
					Desktop.getDesktop().open(new File("/home/tuss/workspace/EmoticonFuzzyCMeans/svm/barplot_clusterSize_.pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		panel2.add(btn, BorderLayout.CENTER);
		
		
		f.add(panel2);

		
		
		f.setSize(800, 400);
		
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		
	

		
		}
		
		
		
}
	


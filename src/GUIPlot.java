import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUIPlot {

	
	 static JFrame frame = new JFrame();
     
	
    public static void main(String[] args) {

    	SentimentAnalysisCall s = new SentimentAnalysisCall();
    	
        JPanel panel = new JPanel();

       // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        	
        JButton btn = new JButton("<html>Accuracy Plot     <br>       </html>");

        
        
btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//JDialog d= new JDialog(f, "woah "+n, true);
				
				try {
					Desktop.getDesktop().open(new File("/home/tuss/workspace/EmoticonFuzzyCMeans/svm/barplot_accuracy_.pdf"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		panel.add(btn);
		
		
        
        frame.add(panel);

        
        frame.setVisible(true);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//main

}
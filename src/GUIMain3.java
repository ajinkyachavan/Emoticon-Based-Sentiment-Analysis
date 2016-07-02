import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUIMain3 {

	
	 static JFrame frame = new JFrame();
     
	
    public static void main(String[] args) {

    	SentimentAnalysisCall s = new SentimentAnalysisCall();
    	
        JPanel panel = new JPanel();

       // panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        	
        JButton button1 = new JButton("<html>SVM Accuracy     <br>       </html>");

        final JLabel label = new JLabel(""+s.getSVMAcc());

        label.setVisible(false);
       
        panel.add(button1);
        panel.add(label);
        
        

        button1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                //JOptionPane.showMessageDialog(frame.getComponent(0), "Hello World");
                label.setVisible(true);
            }
        });

        /*
        JButton button2 = new JButton("<html>     FCM Accuracy</html>");

        final JLabel label2 = new JLabel(""+s.getFCMAcc());

        label2.setVisible(false);
       
        panel.add(button2);
        panel.add(label2);
        

        button2.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent arg0) {
                //JOptionPane.showMessageDialog(frame.getComponent(0), "Hello World");
                label2.setVisible(true);
            }
        });      */

       
        
        frame.add(panel);

        
        frame.setVisible(true);
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//main

}
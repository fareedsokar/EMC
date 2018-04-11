package Preprocessing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class PreprocessingResults extends JFrame{
	public PreprocessingResults() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnShowClustring = new JButton("Show Clustring ");
		btnShowClustring.setBounds(282, 199, 118, 33);
		panel.add(btnShowClustring);
	}
	
	

}

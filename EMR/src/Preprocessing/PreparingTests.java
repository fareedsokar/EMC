package Preprocessing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Panel;
import javax.swing.JButton;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PreparingTests extends JFrame {
	public PreparingTests() {
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Browse Tests");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
			}
		});
		btnNewButton.setBounds(121, 186, 139, 39);
		panel.add(btnNewButton);
		
		JButton btnBrowseTests = new JButton("Start Preprocessing");
		btnBrowseTests.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		btnBrowseTests.setBounds(270, 186, 148, 39);
		panel.add(btnBrowseTests);
	}
}

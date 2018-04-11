package HandlingNewBugfix;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PreparingSearch extends JFrame{
	public PreparingSearch() {
		setTitle("Preapring Search");
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 434, 1);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Browse DataBase");
		btnNewButton.setBounds(297, 95, 107, 42);
		getContentPane().add(btnNewButton);
		
		JButton button = new JButton("Browse Bugfix");
		button.setBounds(297, 164, 107, 42);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Search");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button_1.setBounds(297, 35, 107, 42);
		getContentPane().add(button_1);
	}
	

}

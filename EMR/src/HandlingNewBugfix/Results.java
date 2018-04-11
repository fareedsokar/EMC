package HandlingNewBugfix;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Results extends JFrame 
{
	/**
	 * @wbp.nonvisual location=-10,19
	 */
	private final JPanel panel = new JPanel();
	public Results() {
		setTitle("Results");
		panel.setLayout(null);
		getContentPane().setLayout(null);
		
		JButton btnViewResults = new JButton("View Results");
		btnViewResults.setBounds(22, 189, 117, 36);
		getContentPane().add(btnViewResults);
		
		JButton button = new JButton("Get Test ID");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.setBounds(149, 189, 117, 36);
		getContentPane().add(button);
		
		JButton button_1 = new JButton("Save Results");
		button_1.setBounds(276, 189, 117, 36);
		getContentPane().add(button_1);
	}
}

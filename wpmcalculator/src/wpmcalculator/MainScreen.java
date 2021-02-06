package wpmcalculator;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	public MainScreen() {
		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel panel1 = new JPanel();
		panel1.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
		JPanel panel2 = new JPanel(new GridLayout(3, 1, 10, 10));
		panel2.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
		JPanel panel3 = new JPanel();
		panel3.setBorder(BorderFactory.createEmptyBorder(0, 300, 10, 0));
		
		JLabel instructions = new JLabel("Select a mode");
		instructions.setFont(new Font("Times New Roman", Font.PLAIN, 36));
		instructions.setHorizontalAlignment(JLabel.CENTER);
		JButton option1 = new JButton("One Syllable Words");
		JButton option2 = new JButton("Two Syllable Words");
		JButton option3 = new JButton("Three Syllable Words");
		JButton exit = new JButton("Exit");
		
		//Button Listeners
		option1.addActionListener(this);
		option2.addActionListener(this);
		option3.addActionListener(this);
		exit.addActionListener(this);
		option1.setActionCommand("one");
		option2.setActionCommand("two");
		option3.setActionCommand("three");
		exit.setActionCommand("exit");
		
		panel1.add(instructions);
		panel2.add(option1);
		panel2.add(option2);
		panel2.add(option3);
		panel3.add(exit);
		
		add(panel1, BorderLayout.PAGE_START);
		add(panel2, BorderLayout.CENTER);
		add(panel3, BorderLayout.PAGE_END);
	}
	
	public static void showGUI() {
		JFrame frame1 = new MainScreen();
		frame1.setTitle("Typing Test - Select a mode");
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setSize(400, 600);
		frame1.setResizable(false);
		frame1.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case"exit":
			System.exit(0);
		case"one":
			this.setVisible(false);
			TypingTest.mode = "One Syllable";
			TypingTest.display();
			break;
		case"two":
			this.setVisible(false);
			TypingTest.mode = "Two Syllable";
			TypingTest.display();
			break;
		case"three":
			this.setVisible(false);
			TypingTest.mode = "Three Syllable";
			TypingTest.display();
			break;
		}
		
	}
}
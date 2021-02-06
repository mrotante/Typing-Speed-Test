package wpmcalculator;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.MessageFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class EndScreen extends JFrame implements ActionListener {
	private static final long serialVersionUID = 3L;
	
	public EndScreen() {
		this.getContentPane().setLayout(new BorderLayout());
		JPanel screen = new JPanel(new BorderLayout());
		JPanel buttons = new JPanel(new GridLayout(1,3,20,35));
		JPanel stat = new JPanel(new BorderLayout());
		JLabel stats = new JLabel(MessageFormat.format("<html>Results:<br />WPM: {0}<br /> CPM: {1}<br />Accuracy: {2}</html>",
				calculate_wpm(TypingTest.elapsed), calculate_cpm(TypingTest.elapsed, TypingTest.random_string),
				calculate_accuracy(TypingTest.user_string, TypingTest.random_string)));
		
		JButton exit = new JButton("Ok");
		
		exit.addActionListener(this);
		exit.setActionCommand("exit");
		
		
		buttons.add(exit);
		buttons.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
		stat.setBorder(BorderFactory.createEmptyBorder(0, 35, 0, 0));
		stat.add(stats, BorderLayout.CENTER);
		stats.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		screen.setBorder(BorderFactory.createEmptyBorder(20,0,0,0));
		screen.add(stat, BorderLayout.PAGE_START);
		screen.add(buttons, BorderLayout.CENTER);
		add(screen, BorderLayout.CENTER);
		
	}
	
	public static void display() {
		JFrame popup = new EndScreen();
		popup.setTitle("Typing test - " + TypingTest.mode);
		popup.pack();
		popup.setLocationRelativeTo(null);
		popup.setSize(480, 260);
		popup.setResizable(false);
		popup.setVisible(true);
		
	}
	
	public static String calculate_wpm(float elapsed) {
		if(elapsed < 1) {
			return "N/A";
		} else {
		return MessageFormat.format("{0}", 60/(elapsed/30));
		}
	}
	
	public static String calculate_cpm(float elapsed, String passage) {
		if(elapsed < 1) {
			return "N/A";
		} else {
		return MessageFormat.format("{0}", 60/((float) (elapsed/passage.length())));
		}
	}
	
	public static String calculate_accuracy(String user_input, String passage) {
		float accuracy = 0;
		int char_identical = 0;
		if(user_input == passage) {
			accuracy = 100;
		} else if(passage.length() == user_input.length()){
			for(int i = 0; i < passage.length(); i++) {
				if(passage.charAt(i) == user_input.charAt(i)) {
					char_identical += 1;
				}
			}
			accuracy = ((float) (char_identical)/passage.length()) * 100;
		} else if(passage.length() > user_input.length()) {
			for(int i = 0; i < user_input.length(); i++) {
				if(passage.charAt(i) == user_input.charAt(i)) {
					char_identical += 1;
				}
			}
			accuracy = ((float) (char_identical)/passage.length()) * 100;
		} else {
			for(int i = 0; i < passage.length(); i++) {
				if(user_input.charAt(i) == passage.charAt(i)) {
					char_identical += 1;
				}
			}
			accuracy = ((float) (char_identical)/passage.length()) * 100;
		}
		return (String) (accuracy + "%");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == "exit") {
			this.dispose();
		}
	}	
}

package wpmcalculator;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TypingTest extends JFrame implements ActionListener {
	public static String mode = "";
	public static String random_string;
	public static String user_string;
	public static long starttime = 0;
	public static long endtime;
	public static float elapsed;
	private boolean timer = false;
	private boolean finished = false;

	private static final long serialVersionUID = 1L;

	public TypingTest() {
		this.getContentPane().setLayout(new BorderLayout());
		
		JPanel top = new JPanel(new BorderLayout());
		
		JPanel body = new JPanel(new GridLayout(3, 1, 0, 5));
		JPanel passage = new JPanel(new BorderLayout());
		JPanel entry = new JPanel(new BorderLayout());
		JPanel bot = new JPanel(new GridLayout(1, 3, 35, 0));
		top.setBorder(BorderFactory.createEmptyBorder(10,25,0,25));
		body.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		bot.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
		passage.setBorder(BorderFactory.createMatteBorder(2, 1, 2, 1, Color.BLACK));
		passage.setBackground(Color.LIGHT_GRAY);
		
		if(mode == "One Syllable") {
			random_string = Main.generate("one");
		} else if(mode == "Two Syllable") {
			random_string = Main.generate("two");
		} else {
			random_string = Main.generate("three");
		}
		
		JLabel words = new JLabel("<html>" + random_string + "</html>");
		JLabel instructions = new JLabel("<html>Copy the words below exactly as you see them, at the end you will be provided"
				+ " with an estimated WPM and CPM. When you're done typing press the \"Submit\" button.</html>");
		TextArea user_input = new TextArea();
		instructions.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		user_input.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		words.setFont(new Font("Times New Roman", Font.BOLD, 26));
		words.setHorizontalAlignment(JLabel.LEADING);
		JButton reset = new JButton("Reset");
		JButton submit = new JButton("Submit");
		JButton main_menu = new JButton("Main Menu");
		
		entry.add(user_input, BorderLayout.CENTER);
		top.add(instructions, BorderLayout.NORTH);
		passage.add(words, BorderLayout.NORTH);
		bot.add(main_menu);
		bot.add(reset);
		bot.add(submit);
		body.add(passage);
		body.add(entry);
		body.add(bot);
		add(top, BorderLayout.NORTH);
		add(body, BorderLayout.CENTER);
		
		reset.addActionListener(this);
		submit.addActionListener(this);
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				user_string = user_input.getText();
			}
		});
		main_menu.addActionListener(this);
		reset.setActionCommand("reset");
		submit.setActionCommand("submit");
		main_menu.setActionCommand("main_menu");
		
		//start timer on first keystroke
		user_input.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(!timer) {
					timer = true;
					starttime = System.currentTimeMillis();
				}
			}
		});
	}
	
	public static void display() {
		JFrame frame = new TypingTest();
		frame.setTitle("Typing Test - " + mode);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setSize(1280, 720);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		switch(action) {
		case"reset":
			this.dispose();
			TypingTest.display();
			break;
			
		case"submit":
			if(!finished) {
				endtime = System.currentTimeMillis();
			}
			finished = true;
			if(starttime == 0) {
				elapsed = 0;
			} else {
				elapsed = (float) ((endtime-starttime)*.001);
			}
			EndScreen.display();
			break;
			
		case"main_menu":
			this.dispose();
			MainScreen.showGUI();
			break;
		}
	}	
	
}
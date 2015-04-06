package mario.main;
import javax.swing.*;

import mario.listener.ButtonHandler;

import java.awt.*;
/**
 * This is the startup game window
 * @author Veng
 *
 */
public class SelectWindow extends JFrame
{
		private JButton instructions, start;
		public SelectWindow()
		{
			super("Mario vs. Bowser Game");
			this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			instructions = new JButton("Instructions");
			ButtonHandler handler = new ButtonHandler();
			instructions.addActionListener(handler);
			start = new JButton("Start Game");
			start.addActionListener(handler);
			
//			Background
			JPanel background = new JPanel();
			background.setLayout(new BorderLayout());
			background.setBackground(Color.black);
			this.add(background);
			
//			Logo top panel
			ImageIcon title = new ImageIcon("img\\title.jpg");
			JLabel ml = new JLabel(title);
			JPanel logo = new JPanel();
			logo.setLayout(new GridLayout(1, 1, 10, 10));
			logo.add(ml);
			logo.setBackground(Color.black);
			background.add(logo, BorderLayout.NORTH);
			
//			Center panel for Character Label and Character Icons
			JPanel centerPanel = new JPanel();
			centerPanel.setLayout(new BorderLayout());
			centerPanel.setBackground(Color.black);
			background.add(centerPanel, BorderLayout.CENTER);
			
//			Choose Character Label Panel
			JPanel label = new JPanel();
			JLabel choose = new JLabel("Choose your character:");
			choose.setForeground(Color.white);
			label.add(choose);
			label.setBackground(Color.black);
			centerPanel.add(label, BorderLayout.NORTH);
			
//			Character choosing Panel
			ImageIcon mario2 = new ImageIcon("img\\mario.jpg");
			ImageIcon wario = new ImageIcon("img\\wario.jpg");
			ImageIcon peach = new ImageIcon("img\\peach.jpg");
			JButton two = new JButton("Under Construction", wario);
			two.setForeground(Color.white);
			two.setBackground(Color.black);
			JButton one = new JButton("Mario", mario2);
			one.setForeground(Color.white);
			one.setBackground(Color.black);
			JButton three = new JButton("Under Construction", peach);
			three.setForeground(Color.white);
			three.setBackground(Color.black);
			JPanel characters = new JPanel();
			characters.add(two);
			characters.add(one);
			characters.add(three);
			characters.setBackground(Color.black);
			centerPanel.add(characters, BorderLayout.SOUTH);
			
//			Panel for buttons
			JPanel buttons = new JPanel();
			buttons.setBackground(Color.black);
			buttons.add(instructions);
			buttons.add(start);
			
			background.add(buttons, BorderLayout.SOUTH);
			
		}
		
		public static void main(String [] args)
		{
			SelectWindow mario3 = new SelectWindow();
			mario3.pack();
			mario3.setVisible(true);
			mario3.setResizable(false);
		}
}
package mario.listener;
import javax.swing.*;

import mario.main.Starter;

import java.awt.*;
import java.awt.event.*;

/**
 * This handles the buttons on GameWindow
 * @author Veng
 *
 */
public class ButtonHandler extends Frame implements ActionListener
{

	private JButton ok;
	private JDialog instructions;
	
	public ButtonHandler()
	{

	}
	
	public void actionPerformed(ActionEvent e)
	{
		String label = e.getActionCommand();
		if(label.equals("Instructions"))
		{
			this.showInstructions();
		}
		if(label.equals("OKAY"))
		{
			instructions.setVisible(false);
		}
		if(label.equals("Start Game")){
			Starter main = new Starter();
			//main.run();
		}
	}
	public JDialog showInstructions()
	{
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		//prepare the button
		ok = new JButton("OKAY");
		ok.addActionListener(this);
		//add ok bottom panel
		bottom.add(ok);
		//prepare the instructions paragraph
		JTextArea textArea = new JTextArea("Use arrow keys to move your character around the \n screen. Avoid the enemies and have fun. Good luck", 2, 1);
		//add textArea to top
		top.add(textArea);
		//prepare the dialog window
		instructions = new JDialog(this, "Instructions", true);
		instructions.setLayout(new FlowLayout());
		instructions.add(bottom, BorderLayout.SOUTH);
		instructions.add(top, BorderLayout.NORTH);
		instructions.setBounds(300, 300, 300, 300);
		instructions.setVisible(true);
		
		return instructions;
	}

}
package tools.chap3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class Action implements ActionListener{
	int value = 0;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
	
	public void TrueEnabled(JTextField j)
	{
		j.setEnabled(true);
	}
	
	public void FalseEnabled(JTextField j)
	{
		j.setEnabled(false);
	}
}
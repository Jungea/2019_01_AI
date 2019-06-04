package tools.chap2;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.IndexPanel;
import tools.MainFrame;

public class ResultIPanel extends JPanel {

	public ResultIPanel(MainFrame mainFrame, int check, String[] header, String[][] context) {
		// TODO Auto-generated constructor stub

		setLayout(null);
		setBackground(Color.WHITE);

		// 처음으로 이동하는 버튼
		JButton home_img = new JButton(new ImageIcon("image/home.png"));
		home_img.setBorderPainted(false);
		home_img.setContentAreaFilled(false);
		home_img.setFocusPainted(false);
		home_img.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.changeRoom(new IndexPanel(mf));
			}
		});
		home_img.setBounds(10, 10, 45, 29);
		add(home_img);

		JLabel iTitle_img = new JLabel(new ImageIcon("image/ICal.png"));
		iTitle_img.setBounds(45, 50, 540, 86);
		add(iTitle_img);
		
		

	}
}

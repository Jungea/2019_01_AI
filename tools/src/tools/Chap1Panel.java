package tools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Chap1Panel extends JPanel {
	public Chap1Panel(MainFrame mainFrame) {
		// TODO Auto-generated constructor stub
		setLayout(null);
		setBackground(Color.WHITE);

		ImageIcon home_ImgIcon = new ImageIcon("image/home.png");
		JButton home_img = new JButton(home_ImgIcon);
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

		ImageIcon chap1_ImgIcon = new ImageIcon("image/index_chap1.png");
		JButton chap1_img = new JButton(chap1_ImgIcon);
		chap1_img.setBorderPainted(false);
		chap1_img.setContentAreaFilled(false);
		chap1_img.setFocusPainted(false);
		chap1_img.setBounds(45, 50, 540, 57);
		add(chap1_img);

		
		
		JPanel inputPanel = new ExInputPanel();
		LineBorder lb = new LineBorder(Color.BLACK, 2);
		ImageIcon ex_check_ImgIcon = new ImageIcon("image/chap1_ex_check.png");
		JButton ex_check_Img = new JButton(ex_check_ImgIcon);
		ex_check_Img.setBorderPainted(false);
		ex_check_Img.setContentAreaFilled(false);
		ex_check_Img.setFocusPainted(false);
		ex_check_Img.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		ex_check_Img.setBounds(45, 110, 187, 65);
		
		
		ImageIcon user_xcheck_ImgIcon = new ImageIcon("image/chap1_user_check.png");
		JButton user_xcheck_Img = new JButton(user_xcheck_ImgIcon);
		user_xcheck_Img.setBorderPainted(false);
		user_xcheck_Img.setContentAreaFilled(false);
		user_xcheck_Img.setFocusPainted(false);
		user_xcheck_Img.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			}
		});
		user_xcheck_Img.setBounds(230, 115, 187, 61);
		add(user_xcheck_Img, 1);
		
		

		inputPanel.setBorder(lb);
		inputPanel.setBounds(45, 160, 540, 530);
		add(inputPanel, 2);
		add(ex_check_Img, 3);
	}

	class ExInputPanel extends JPanel {
		public ExInputPanel() {
			// TODO Auto-generated constructor stub
			setLayout(null);
			setBackground(Color.WHITE);

			ImageIcon ex_ImgIcon = new ImageIcon("image/ex_chap1.png");
			JLabel ex_Img = new JLabel(ex_ImgIcon);
			ex_Img.setBounds(0, 0, 540, 230);
			add(ex_Img);
			
			
		}
	}
}

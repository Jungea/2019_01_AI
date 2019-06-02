package tools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import tools.chap1.Chap1Panel;

public class IndexPanel extends JPanel {

	LineBorder lb = new LineBorder(Color.RED, 3);

	public IndexPanel(MainFrame mainFrame) {
		setLayout(null);
		setBackground(Color.WHITE);

		ImageIcon title_ImgIcon = new ImageIcon("image/index_title.png");
		JLabel title_img = new JLabel(title_ImgIcon);
		title_img.setBounds(45, 10, 540, 230);
		add(title_img);

		ImageIcon chap1_ImgIcon = new ImageIcon("image/index_chap1.png");
		JButton chap1_img = new JButton(chap1_ImgIcon);
		chap1_img.setBorderPainted(false);
		chap1_img.setContentAreaFilled(false);
		chap1_img.setFocusPainted(false);
		chap1_img.setBounds(45, 280, 540, 57);
		add(chap1_img);
		chap1_img.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.changeRoom(new Chap1Panel(mf));
			}
		});

		ImageIcon chap2_ImgIcon = new ImageIcon("image/index_chap2.png");
		JButton chap2_img = new JButton(chap2_ImgIcon);
		chap2_img.setBorderPainted(false);
		chap2_img.setContentAreaFilled(false);
		chap2_img.setFocusPainted(false);
		chap2_img.setBounds(45, 430, 540, 57);
		add(chap2_img);
		chap2_img.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.changeRoom(new Chap2Panel(mf));
			}
		});

		ImageIcon chap3_ImgIcon = new ImageIcon("image/index_chap3.png");
		JButton chap3_img = new JButton(chap3_ImgIcon);
		chap3_img.setBorderPainted(false);
		chap3_img.setContentAreaFilled(false);
		chap3_img.setFocusPainted(false);
		chap3_img.setBounds(45, 580, 540, 57);
		add(chap3_img);
		chap3_img.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.changeRoom(new Chap3Panel(mf));
			}
		});

	}
}

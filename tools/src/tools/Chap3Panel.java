package tools;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class Chap3Panel extends JPanel {
	public Chap3Panel(MainFrame mainFrame) {
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

		ImageIcon chap3_ImgIcon = new ImageIcon("image/index_chap3.png");
		JButton chap3_img = new JButton(chap3_ImgIcon);
		chap3_img.setBorderPainted(false);
		chap3_img.setContentAreaFilled(false);
		chap3_img.setFocusPainted(false);
		chap3_img.setBounds(45, 50, 540, 57);
		add(chap3_img);
	}

}

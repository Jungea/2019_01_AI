package tools;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import tools.chap1.Chap1Panel;

public class MainFrame extends JFrame {

	public void frameLocation() {
		Dimension screen1 = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension screen2 = getSize();
		int xpos = (int) (screen1.getWidth() / 2 - screen2.getWidth() / 2);
		int ypos = (int) (screen1.getHeight() / 2 - screen2.getHeight() / 2);
		setLocation(xpos, ypos);
	}

	public MainFrame() {
		setSize(645, 845);
		setTitle("AI 실습 도구");

		frameLocation();
		setLayout(new GridLayout());
		URL url = getClass().getClassLoader().getResource("AI_Title.jpg");
		ImageIcon backgroundImg = new ImageIcon(url);
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(backgroundImg.getImage(), 0, 0, null);
				setOpaque(false); // 그림을 표시하게 설정,투명하게 조절
				super.paintComponent(g);
				this.repaint();
			}
		};
		add(background);

		background.setLayout(new BorderLayout());

		ImageIcon next_ImgIcon = new ImageIcon("image/next.png");
		JButton nextButton = new JButton(next_ImgIcon);
		nextButton.setBorderPainted(false);
		nextButton.setContentAreaFilled(false);
		nextButton.setFocusPainted(false);
		nextButton.setFont(new Font("Dialog", Font.BOLD, 25));
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changeRoom(new IndexPanel(MainFrame.this));
			}
		});

		background.add("East", nextButton);

		changeRoom(new Chap1Panel(this));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setVisible(true);
	}

	public void changeRoom(JPanel panel) {
		setResizable(false);
		getContentPane().removeAll();
		getContentPane().add(panel);
		revalidate();
		repaint();

	}

	public static void main(String[] args) {
		new MainFrame();
	}
}

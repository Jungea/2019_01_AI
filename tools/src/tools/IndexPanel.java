package tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class IndexPanel extends JPanel {

	JLabel[] label = new JLabel[4];
	LineBorder lb = new LineBorder(Color.RED, 3);

	public IndexPanel() {
		setLayout(null);

		JPanel bPanel = new JPanel();
		bPanel.setLayout(new GridLayout(6, 1));
		label[0] = new JLabel("목차", 0);
		label[1] = new JLabel("탐색            ........................................1");
		label[2] = new JLabel("ID3     ........................................2");
		label[3] = new JLabel("단층 퍼셉트론........................................3");
		bPanel.add(label[0]);
		bPanel.add(label[1]);
		bPanel.add(label[2]);
		bPanel.add(label[3]);
		bPanel.add(new JLabel("개발 중..."));
		bPanel.add(new JLabel());
		
		label[0].setFont(new Font("굴림", Font.BOLD, 25));
		label[1].setFont(new Font("굴림", Font.BOLD, 25));
		label[2].setFont(new Font("굴림", Font.BOLD, 25));
		label[3].setFont(new Font("굴림", Font.BOLD, 25));
		
		bPanel.setOpaque(false);
		//bPanel.setBorder(lb);
		setBackground(Color.WHITE);
		bPanel.setBounds(50, 0, 600, 800);
		add(bPanel);
	}
}

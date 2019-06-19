package tools.chap2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import tools.IndexPanel;
import tools.MainFrame;

public class DecisionTree extends JPanel {

	static LineBorder lb = new LineBorder(Color.BLUE, 1);

	public DecisionTree(MainFrame mainFrame, ID3 id3) {
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

		// title 이미지
		JLabel iTitle_img = new JLabel(new ImageIcon("image/decisionTree.png"));
		iTitle_img.setBounds(45, 50, 540, 86);
		add(iTitle_img);

		JButton resetButton = new JButton("다시 입력");
		resetButton.setBounds(485, 140, 100, 30);
		resetButton.setFocusPainted(false);
		add(resetButton);
		resetButton.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.changeRoom(new Chap2Panel(mf));

			}
		});

		// Gain 패널
		JTextArea gainTF = new JTextArea();
		JScrollPane sp1 = new JScrollPane(gainTF);
		sp1.setBorder(lb);
		for (int i = 1; i < id3.gain.length; i++)
			if (i != id3.id)
				gainTF.append("Gain(" + id3.header[i] + ") = " + id3.getGain(i) + "\n");
		gainTF.setFont(new Font("돋움", Font.BOLD, 20));
		sp1.setBounds(45, 170, 540, 100);
		add(sp1);

		// id3 계산 결과 모음 패널
		JPanel showPanel = new JPanel();
		showPanel.setLayout(null);
		showPanel.setBackground(Color.WHITE);
		showPanel.setBorder(lb);
		showPanel.setBounds(45, 280, 540, 400);
		add(showPanel);

		BTreePanel2 treePanel = new BTreePanel2();
		JScrollPane treeSP = new JScrollPane(treePanel);
		treeSP.setBounds(50, 10, 450, 370);
		treePanel.setBackground(Color.WHITE);
		// treePanel.setBorder(lb);
		treeSP.setBorder(null);
		showPanel.add(treeSP);

		id3.mt();

		treePanel.tree = id3.tree;

		treePanel.capture();

		// 이전 ID3 계산 패널로 이동하는 버튼
		/*
		 * JButton preButton = new JButton("<"); preButton.setBounds(450, 710, 59, 59);
		 * preButton.setFocusPainted(false); add(preButton);
		 * preButton.addActionListener(new ActionListener() { MainFrame mf = mainFrame;
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { // TODO Auto-generated
		 * method stub int index = id3.header.length - 1; if (index == id3.id) index -=
		 * 1; mainFrame.changeRoom(new ResultEPanel(mf, id3.id, index, id3, null,
		 * null)); } });
		 */
	}

}

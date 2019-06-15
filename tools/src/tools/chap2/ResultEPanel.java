package tools.chap2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import tools.IndexPanel;
import tools.MainFrame;
import tools.chap1.Chap1Panel;

public class ResultEPanel extends JPanel {

	static LineBorder lb = new LineBorder(Color.BLUE, 1);

	public ResultEPanel(MainFrame mainFrame, int I, int index, ID3 id3Object, String[] header, String[][] context) {
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

		// title 이미지
		JLabel iTitle_img = new JLabel(new ImageIcon("image/e_title.png"));
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

		// id3 계산 결과 모음 패널
		JPanel showPanel = new JPanel();
		showPanel.setLayout(null);
		showPanel.setBackground(Color.WHITE);
//		showPanel.setBorder(lb);
		showPanel.setBounds(45, 170, 540, 530);
		add(showPanel);

		// ID3 객체
		ID3 id3 = id3Object == null ? new ID3(I, header, context) : id3Object;

		// 테이블
		Integer[] b = (Integer[]) id3.idMap.values().toArray(new Integer[id3.idMap.values().size()]);
		Integer[][] bb = new Integer[1][b.length];
		bb[0] = b;
		String[] a = (String[]) id3.idMap.keySet().toArray(new String[id3.idMap.keySet().size()]);

		JTable tt = new JTable(bb, a);
		JScrollPane tableSP = new JScrollPane(tt, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tableSP.setBounds(20, 50, 300, 60);
		tt.setRowHeight(30);
		tt.getTableHeader().setPreferredSize(new Dimension(30, 30));
		tt.setFont(new Font("돋움", Font.BOLD, 20));
		showPanel.add(tableSP);

		// 풀이과정 레이블
		JLabel processLabel = new JLabel("풀이 과정");
		processLabel.setFont(new Font("돋움", Font.BOLD, 20));
		processLabel.setBounds(20, 170, 100, 20);
		showPanel.add(processLabel);

		// 풀이과정 텍스트필드
		JTextArea processTF = new JTextArea();
		JScrollPane sp1 = new JScrollPane(processTF);
		sp1.setBorder(lb);
		processTF.append("E(" + id3.header[index] + ") = " + id3.getProcess(index) + "\n\n");
		processTF.append("E(" + id3.header[index] + ") =  " + id3.getResultValue(index) + "\n\n");
		processTF
				.append("Gain(" + id3.header[index] + ") =  " + id3.getResultValue(index) + " - " + id3.getGain(index));
		processTF.setFont(new Font("돋움", Font.BOLD, 20));
		showPanel.add(sp1);
		sp1.setBounds(20, 200, 500, 200);

		// 결과 Gain 레이블
		JLabel resultLabel = new JLabel("결과");
		resultLabel.setFont(new Font("돋움", Font.BOLD, 20));
		resultLabel.setBounds(20, 420, 100, 20);
		showPanel.add(resultLabel);

		// 결과 Gain 텍스트 필드
		JTextArea resultTF = new JTextArea();
		resultTF.setBorder(lb);
		resultTF.append("Gain(" + id3.header[index] + ") =  " + id3.getGain(index) + "\n\n");
		resultTF.setFont(new Font("돋움", Font.BOLD, 20));
		resultTF.setBounds(20, 450, 500, 50);
		showPanel.add(resultTF);

		// 의사결정 트리로 이동하는 버튼
		JButton treeButton = new JButton("의사결정트리");
		treeButton.setBounds(230, 710, 150, 59);
		treeButton.setFocusPainted(false);
		add(treeButton);
		treeButton.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				mainFrame.changeRoom(new DecisionTree(mf, id3));
			}
		});

		// 이전 ID3 계산 패널로 이동하는 버튼
		if (index > 0) {
			JButton preButton = new JButton("<");
			preButton.setBounds(450, 710, 59, 59);
			preButton.setFocusPainted(false);
			add(preButton);
			preButton.addActionListener(new ActionListener() {
				MainFrame mf = mainFrame;

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					if (index - 1 == 0)
						mainFrame.changeRoom(new ResultIPanel(mf, I, index - 1, id3, null, null));
					else
						mainFrame.changeRoom(new ResultEPanel(mf, I, index - 1, id3, null, null));
				}
			});
		}
		// 다음 ID3 계산 패널로 이동하는 버튼 3 length 5
		JButton postButton = new JButton(">");
		postButton.setBounds(525, 710, 59, 59);
		postButton.setFocusPainted(false);
		add(postButton);
		postButton.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (index + 1 == I && (I == id3.header.length - 1)) // 다음게 I인데 I가 마지막
					mainFrame.changeRoom(new DecisionTree(mf, id3));
				else if (index == id3.header.length - 1)
					mainFrame.changeRoom(new DecisionTree(mf, id3));
				else if (index + 1 == I) // 다음게 I
					mainFrame.changeRoom(new ResultEPanel(mf, I, index + 2, id3, null, null));
				else
					mainFrame.changeRoom(new ResultEPanel(mf, I, index + 1, id3, null, null));
			}
		});
	}
}

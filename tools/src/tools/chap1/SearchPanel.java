package tools.chap1;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import tools.IndexPanel;
import tools.MainFrame;

public class SearchPanel extends JPanel {

	LineBorder lb = new LineBorder(Color.BLACK, 1);

	public SearchPanel(MainFrame mainFrame, int num, UndirectedListGraph graph) {
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

		ImageIcon icon;
		if (num == 1) {
			// 언덕 등반 탐색 타이틀
			icon = new ImageIcon("image/hill_title.png");

		} else {
			icon = new ImageIcon("image/astar_title.png");
		}

		JLabel title_img = new JLabel(icon);

		title_img.setBounds(45, 50, 540, 86);
		add(title_img);
		/*
		 * JPanel solvingPanel = new JPanel(); solvingPanel.setLayout(null);
		 * solvingPanel.setBackground(Color.WHITE);
		 * 
		 * solvingPanel.setBorder(lb); solvingPanel.setBounds(45, 160, 540, 450);
		 * add(solvingPanel);
		 */

		JTextArea solvingTA = new JTextArea();
		solvingTA.setBounds(45, 160, 540, 450);
		solvingTA.setBorder(lb);
		add(solvingTA);

		if (num == 1)
			solvingTA.setText(graph.hillClimbing(0));
		else
			solvingTA.setText(graph.aStartSearch(0));

		if (num == 1) {
			// A* 탐색 이동 버튼
			JButton aStarButton = new JButton(new ImageIcon("image/astar.png"));
			aStarButton.setBounds(80, 710, 200, 59);
			aStarButton.setBorderPainted(false);
			aStarButton.setContentAreaFilled(false);
			aStarButton.setFocusPainted(false);
			add(aStarButton);
			aStarButton.addActionListener(new ActionListener() {
				MainFrame mf = mainFrame;

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mainFrame.changeRoom(new SearchPanel(mf, 2, graph));

				}
			});
		} else if (num == 2) {

			// 언덕등반탐색 이동 버튼
			JButton hillButton = new JButton(new ImageIcon("image/hill.png"));
			hillButton.setBounds(80, 710, 200, 59);
			hillButton.setBorderPainted(false);
			hillButton.setContentAreaFilled(false);
			hillButton.setFocusPainted(false);
			add(hillButton);
			hillButton.addActionListener(new ActionListener() {
				MainFrame mf = mainFrame;

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mainFrame.changeRoom(new SearchPanel(mf, 1, graph));

				}
			});
		}
		// A* 탐색 이동 버튼
		JButton resetButton = new JButton("다시 입력");
		resetButton.setBounds(350, 710, 200, 59);
		resetButton.setFocusPainted(false);
		add(resetButton);
		resetButton.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.changeRoom(new Chap1Panel(mf));

			}
		});

	}

}

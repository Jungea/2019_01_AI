package tools.chap2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import tools.IndexPanel;
import tools.MainFrame;

public class Chap2Panel extends JPanel {

	static LineBorder lb = new LineBorder(Color.BLUE, 1);
	static LineBorder whitelb = new LineBorder(Color.WHITE, 1);
	JPanel tablePanel = new JPanel();
	JTable table;
	String[] header;
	String[][] context;
	JRadioButton[] radioButton;
	int check = 0;

	public Chap2Panel(MainFrame mainFrame) {
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

		// chap2 타이틀
		ImageIcon chap2_ImgIcon = new ImageIcon("image/index_chap2.png");
		JButton chap2_img = new JButton(chap2_ImgIcon);
		chap2_img.setBorderPainted(false);
		chap2_img.setContentAreaFilled(false);
		chap2_img.setFocusPainted(false);
		chap2_img.setBounds(45, 50, 540, 57);
		add(chap2_img);

		/*-------------------------------------------------------------------------------------*/
		// 파일 열기 작동을 하는 패널
		JPanel openPanel = new JPanel();
		openPanel.setLayout(null);
		openPanel.setBackground(Color.WHITE);
		openPanel.setBorder(lb);

		// 파일 선택 버튼
		JButton chooseButton = new JButton("파일 선택");
		chooseButton.setBounds(15, 20, 110, 35);
		chooseButton.setFocusPainted(false);
		openPanel.add(chooseButton);

		// 파일 열기 버튼
		JButton openButton = new JButton("열기");
		openButton.setBounds(15, 75, 110, 35);
		openButton.setFocusPainted(false);
		openPanel.add(openButton);

		openPanel.setBounds(45, 140, 540, 130);
		add(openPanel);

		/*-------------------------------------------------------------------------------------*/
		// 실습 예제 양식을 채워주는 버튼
		JButton ex_Button = new JButton("실습 예제");
		ex_Button.setBounds(45, 300, 150, 30);
		ex_Button.setFocusPainted(false);
		add(ex_Button);
		ex_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				header = new String[] { "no", "큰수술", "가족", "60세이상", "집" };
				context = new String[][] { { "1", "그렇다", "없다", "아니다", "아니다" }, { "2", "그렇다", "없다", "그렇다", "아니다" },
						{ "3", "아니다", "없다", "아니다", "그렇다" }, { "4", "아니다", "없다", "그렇다", "아니다" },
						{ "5", "아니다", "있다", "그렇다", "그렇다" } };

				table = new JTable(context, header);
				// 표 생성
				JScrollPane tableSP = new JScrollPane(table);
				tableSP.setOpaque(false);
				tableSP.getViewport().setOpaque(false);
				table.setRowHeight(30);
				table.getColumnModel().getColumn(0).setPreferredWidth(10);
				table.getTableHeader().setPreferredSize(new Dimension(30, 30));

				table.setFocusable(false); // 열
				table.setRowSelectionAllowed(false); // 행
				table.getTableHeader().setReorderingAllowed(false); // 이동불가
				table.getTableHeader().setResizingAllowed(false); // 크기 조절 불가

				tablePanel.add(tableSP);

				ButtonGroup group = new ButtonGroup();
				radioButton = new JRadioButton[header.length];
				int x = 95;
				for (int i = 1; i < radioButton.length; i++) {
					radioButton[i] = new JRadioButton(header[i]);
					group.add(radioButton[i]);
					radioButton[i].setBackground(Color.WHITE);
					radioButton[i].setBounds(x, 560, 125, 100);
					x += 130;
					radioButton[i].setFont(new Font("돋움", Font.BOLD, 15));
					add(radioButton[i]);
				}
				radioButton[1].setSelected(true);

				JButton calStart = new JButton("계산 시작");
				calStart.setBounds(385, 680, 200, 60);
				calStart.addActionListener(new ActionListener() {
					MainFrame mf = mainFrame;

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						check = checkRadio(radioButton);
						mainFrame.changeRoom(new ResultIPanel(mf, check, header, context));
					}
				});

				add(calStart);

				revalidate();
				repaint();
			}

		});

		tablePanel.setLayout(new BorderLayout());
		tablePanel.setBackground(Color.white);
		tablePanel.setBounds(45, 350, 540, 200);
		tablePanel.setBorder(lb);
		add(tablePanel);

	}

	public static int checkRadio(JRadioButton[] radioButton) {
		for (int i = 1; i < radioButton.length; i++)
			if (radioButton[i].isSelected())
				return i;
		return 0;
	}

}

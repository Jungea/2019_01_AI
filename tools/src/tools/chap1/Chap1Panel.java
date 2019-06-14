package tools.chap1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import tools.IndexPanel;
import tools.MainFrame;

public class Chap1Panel extends JPanel {

	static LineBorder lb = new LineBorder(Color.BLUE, 1);
	static LineBorder errorLB = new LineBorder(Color.RED, 3);

	JTextField vNumTF = new JTextField();
	JTextField startTF = new JTextField();
	JTextField eNumTF = new JTextField();
	JCheckBox returnCB = new JCheckBox("시작정점 도착", true);
	static JTextArea VWTA;
	static JScrollPane VWScrollPane;

	public Chap1Panel(MainFrame mainFrame) {
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

		// chap1 타이틀
		JLabel chap1_img = new JLabel(new ImageIcon("image/index_chap1.png"));
		chap1_img.setBounds(45, 50, 540, 57);
		add(chap1_img);

		// 실습 예제 양식을 채워주는 버튼
		JButton ex_Check_Button = new JButton("실습 예제");
		ex_Check_Button.setBounds(45, 130, 150, 30);
		ex_Check_Button.setFocusPainted(false);
		add(ex_Check_Button);
		ex_Check_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vNumTF.setText("4");
				returnCB.setSelected(true);
				eNumTF.setText("6");
				startTF.setText("0");
				VWTA.setText("0 1 1\n0 2 5\n0 3 4\n1 2 2\n1 3 6\n2 3 3");
			}
		});

		// 텍스트 필드나 텍스트 에리어를 비워주는 버튼
		JButton reset_Button = new JButton("초기화");
		reset_Button.setBounds(195, 130, 150, 30);
		reset_Button.setFocusPainted(false);
		add(reset_Button);
		reset_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				vNumTF.setText("");
				returnCB.setSelected(true);
				eNumTF.setText("");
				startTF.setText("");
				VWTA.setText("");
			}
		});

		// 입력 컨포넌트가 모여있는 패널
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(null);
		inputPanel.setBackground(Color.WHITE);

		// 실습 예제 사진
		JLabel ex_Img = new JLabel(new ImageIcon("image/ex_chap1.png"));
		ex_Img.setBounds(50, 10, 450, 225);
		inputPanel.add(ex_Img);

		// 정점수 레이블과 텍스트 필드
		JLabel vNumLabel = new JLabel("정점 수", 0);
		vNumLabel.setBounds(50, 270, 100, 50);
		inputPanel.add(vNumLabel);
		vNumTF.setBounds(150, 270, 100, 50);
		inputPanel.add(vNumTF);

		returnCB.setBounds(330, 270, 150, 50);
		returnCB.setBackground(Color.WHITE);
		inputPanel.add(returnCB);

		// 간선수 레이블과 텍스트 필드
		JLabel eNumLabel = new JLabel("간선 수", 0);
		eNumLabel.setBounds(50, 340, 100, 50);
		inputPanel.add(eNumLabel);
		eNumTF.setBounds(150, 340, 100, 50);
		inputPanel.add(eNumTF);

		JLabel startLabel = new JLabel("시작 노드", 0);
		startLabel.setBounds(250, 340, 100, 50);
		inputPanel.add(startLabel);
		startTF.setBounds(350, 340, 100, 50);
		inputPanel.add(startTF);

		// 간선 레이블과 텍스트 에리어
		JLabel VWLabel = new JLabel("간선 입력 \n(V1, V2, W)");
		VWLabel.setBounds(50, 410, 150, 50);
		inputPanel.add(VWLabel);
		VWTA = new JTextArea();
		VWScrollPane = new JScrollPane(VWTA);
		VWScrollPane.setBounds(200, 410, 250, 100);
		inputPanel.add(VWScrollPane);

		// input패널 설정
		inputPanel.setBorder(lb);
		inputPanel.setBounds(45, 160, 540, 530);
		add(inputPanel);

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
				if (vNumTF.getText().length() != 0 && eNumTF.getText().length() != 0 && VWTA.getText().length() != 0) {
					UndirectedListGraph graph = makeGraph(vNumTF.getText(), eNumTF.getText(), VWTA.getText());
					if (graph != null)
						mainFrame.changeRoom(new SearchPanel(mf, 1, Integer.parseInt(startTF.getText()),
								returnCB.isSelected(), graph));
				}
			}
		});

		// A* 탐색 이동 버튼
		JButton aStarButton = new JButton(new ImageIcon("image/astar.png"));
		aStarButton.setBounds(350, 710, 200, 59);
		aStarButton.setBorderPainted(false);
		aStarButton.setContentAreaFilled(false);
		aStarButton.setFocusPainted(false);
		add(aStarButton);
		aStarButton.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (vNumTF.getText().length() != 0 && eNumTF.getText().length() != 0 && VWTA.getText().length() != 0) {
					UndirectedListGraph graph = makeGraph(vNumTF.getText(), eNumTF.getText(), VWTA.getText());
					if (graph != null)
						mainFrame.changeRoom(new SearchPanel(mf, 2, Integer.parseInt(startTF.getText()),
								returnCB.isSelected(), graph));
				}
			}
		});

	}

	public static UndirectedListGraph makeGraph(String vNumTF, String eNumTF, String VWTA) {
		int n = Integer.parseInt(vNumTF);

		// 정점 수가 n인 무방향 그래프를 생성
		UndirectedListGraph graph = new UndirectedListGraph(n);

		// 간선 수 e 입력
		int e = Integer.parseInt(eNumTF);

		String[] ls = VWTA.split("[ \n]+");
		// e개의 간선(정점 쌍)을 입력받아 그래프에 삽입
		int l = 0;
		try {
			while (l < 3 * e) {
				int v1 = Integer.parseInt(ls[l++]);
				int v2 = Integer.parseInt(ls[l++]);
				int w = Integer.parseInt(ls[l++]);
				if (!graph.addEdge(v1, v2, w)) { // 잘못된 삽입
					Chap1Panel.VWScrollPane.setBorder(errorLB);
					return null;
				}
			}
		} catch (ArrayIndexOutOfBoundsException exception) { // 잘못된 입력
			// TODO: handle exception
			Chap1Panel.VWTA.setBorder(errorLB);
			return null;
		}

		return graph;
	}

}

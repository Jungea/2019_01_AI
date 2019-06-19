package tools.chap3;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import tools.IndexPanel;
import tools.MainFrame;

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
		// -----------------------------------------------------------위에까지 은애가 한 것.

		int i = 0;

		// ※ 라디오 버튼 생성, 그룹화, and를 초기값 설정, 위치 초기화, 패널 추가------------------------
		JRadioButton[] r = new JRadioButton[3];
		r[0] = new JRadioButton("AND");
		r[1] = new JRadioButton("OR");
		r[2] = new JRadioButton("NAND");

		ButtonGroup group = new ButtonGroup();

		for (i = 0; i < r.length; i++)
			group.add(r[i]);

		i = 0;
		for (int x = 155; i < r.length; i++) {
			r[i].setBounds(x, 130, 100, 50);
			r[i].setBackground(Color.white);
			r[i].setFont(new Font("굴림", Font.BOLD, 20));
			x += 105;
			add(r[i]);
		}

		r[0].setSelected(true);

		// ※ 새 패널에 이미지 넣고 z-order값 조절해서 이미지 위에 글씨 표시
		ImageIcon picture = new ImageIcon("image/picture.png");
		JLabel img = new JLabel(picture);

		JPanel imgPanel = new JPanel();
		imgPanel.setBounds(55, 335, 530, 300);
		imgPanel.setBackground(Color.white);
		imgPanel.add(img);

		add(imgPanel);

		JLabel[] pictureText = new JLabel[6];

		i = 0;
		for (; i < pictureText.length; i++) {
			pictureText[i] = new JLabel("");
			pictureText[i].setFont(new Font("Dialog", Font.BOLD, 20));
			pictureText[i].setSize(70, 30);
			setComponentZOrder(pictureText[i], i);
			add(pictureText[i]);
		}

		pictureText[0].setHorizontalAlignment(JLabel.LEFT);
		pictureText[1].setHorizontalAlignment(JLabel.LEFT);
		pictureText[2].setHorizontalAlignment(JLabel.CENTER);
		pictureText[3].setHorizontalAlignment(JLabel.CENTER);
		pictureText[4].setHorizontalAlignment(JLabel.LEFT);
		pictureText[5].setHorizontalAlignment(JLabel.LEFT);

		pictureText[0].setLocation(127, 365);
		pictureText[1].setLocation(127, 595);
		pictureText[2].setLocation(235, 390);
		pictureText[3].setLocation(235, 570);
		pictureText[4].setLocation(352, 479);
		pictureText[5].setLocation(450, 460);

		i = 0;
		for (; i < pictureText.length; i++)
			setComponentZOrder(pictureText[i], i);
		setComponentZOrder(imgPanel, 6);

		// ※ 버튼 생성과 위치 초기화, 패널에 추가------------------------------------------------
		ImageIcon start_p = new ImageIcon("image/C3_start.png");
		ImageIcon reset_p = new ImageIcon("image/C3_reset.png");
		ImageIcon left_p = new ImageIcon("image/left.png");
		ImageIcon right_p = new ImageIcon("image/right.png");
		JButton start = new JButton(start_p);
		JButton pre = new JButton(left_p);
		JButton next = new JButton(right_p);
		JButton reset = new JButton(reset_p);

		start.setBounds(55, 680, 150, 50);
		pre.setBounds(480, 680, 50, 50);
		next.setBounds(535, 680, 50, 50);
		reset.setBounds(210, 680, 150, 50);

		add(start);
		add(pre);
		add(next);
		add(reset);

		// ※ 라벨 선언과 초기화 ------------------------------------------------------------
		JLabel[] text = new JLabel[4];

		text[0] = new JLabel("w1");
		text[1] = new JLabel("w2");
		text[2] = new JLabel("θ");
		text[3] = new JLabel("a");

		i = 0;
		for (int x = 90; i < text.length; i++) {
			text[i].setBounds(x, 220, 30, 20);
			text[i].setHorizontalAlignment(JLabel.CENTER);
			text[i].setFont(new Font("Dialog", Font.BOLD, 17));
			x += 115;
		}

		for (i = 0; i < text.length; i++)
			add(text[i]);

		// ※ step 글자 표시 ------------------------------------------------------------
		JLabel step = new JLabel("Step");
		step.setFont(new Font("Dialog", Font.BOLD, 20));
		step.setBounds(55, 310, 100, 30);
		add(step);

		// ※ 텍스트 필드 배열 선언, 위치 초기화, 추가--------------------------------------------
		JTextField[] input = new JTextField[4];

		i = 0;
		for (int x = 120; i < input.length; i++) {
			input[i] = new JTextField();
			input[i].setBounds(x, 210, 80, 40);
			x += 115;
			add(input[i]);
		}

		ArrayList<String> list = new ArrayList<>(); // 퍼셉트론 출력 결과값을 저장할 배열

		// 출력 결과값 텍스트 표시 ----------------------------------------------------------
//		JLabel index = new JLabel("X1 X2 F   W1   W2  Y  d  W1   W2\n");
//		index.setBounds(210, 260, 200, 30);
//		index.setHorizontalAlignment(JLabel.CENTER);
//		add(index);

//		JLabel value = new JLabel();
//		value.setBounds(210, 290, 200, 30);
//		value.setHorizontalAlignment(JLabel.CENTER);
//		add(value);

		// 각 버튼들의 actionListener ----------------------------------------------------
		Action action = new Action(); // 버튼들의 actionListener 객체

		pre.setEnabled(false);
		next.setEnabled(false);

		start.addActionListener(new ActionListener() { // 시작 버튼 actionListener
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// 값 입력 없을 때 에러 메시지 출력
				if (!isStringDouble(input)) {
					JOptionPane.showMessageDialog(null, "값을 다시 입력하세요.", "값 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}

				pre.setEnabled(true);
				next.setEnabled(true);
				reset.setEnabled(true);

				step.setText("Step 1");

				radioButtonFalse(r);

				int a = 0;

				// 라디오 버튼 선택에 따라 and, or, nand 값 입력
				if (r[0].isSelected())
					a = 1;
				else if (r[1].isSelected())
					a = 2;
				else if (r[2].isSelected())
					a = 3;

				double[] array = new double[4]; // 입력창에 입력된 값들 저장

				for (int i = 0; i < array.length; i++)
					array[i] = Double.parseDouble(input[i].getText());

				new perceptron();
				perceptron.array.clear();
				perceptron.main(a, array);

				textFieldFalse(input);

				pre.setEnabled(false);
				start.setEnabled(false);

				if (perceptron.array.size() >= 100) {
					textFieldFalse(input);

					pre.setEnabled(false);
					next.setEnabled(false);

					radioButtonFalse(r);

					JOptionPane.showMessageDialog(null, "학습에 실패하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				} else {
					pictureText[4].setText(input[2].getText().trim());
					list.addAll(perceptron.array);
					// value.setText(perceptron.array.get(0));

					action.s = list.get(0).split("   ");

					pictureText[0].setText(action.s[0]);
					pictureText[1].setText(action.s[1]);
					pictureText[2].setText(action.s[3]);
					pictureText[3].setText(action.s[4]);
					pictureText[5].setText(action.s[5]);
				}
			}
		});

		pre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// value.setText(list.get(--action.value)); // 현재 값 이전 위치의 값을 저장.
				--action.value;

				if (action.value == 0)
					pre.setEnabled(false);

				if (action.value < list.size() - 1)
					next.setEnabled(true);

				action.s = list.get(action.value).split("   ");

				pictureText[0].setText(action.s[0]);
				pictureText[1].setText(action.s[1]);
				pictureText[2].setText(action.s[3]);
				pictureText[3].setText(action.s[4]);
				pictureText[5].setText(action.s[5]);

				step.setText("Step " + Integer.toString(action.value / 4 + 1));
			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// value.setText(list.get(++action.value)); // 현재 값 다음 위치의 값 저장.
				++action.value;

				if (action.value == list.size() - 1)
					next.setEnabled(false);

				if (action.value > 0)
					pre.setEnabled(true);

				action.s = list.get(action.value).split("   ");

				pictureText[0].setText(action.s[0]);
				pictureText[1].setText(action.s[1]);
				pictureText[2].setText(action.s[3]);
				pictureText[3].setText(action.s[4]);
				pictureText[5].setText(action.s[5]);

				step.setText("Step " + Integer.toString(action.value / 4 + 1));
			}
		});

		reset.addActionListener(new ActionListener() { // 초기화 버튼 - 모든 입력창과 버튼 초기화(사용 가능), 값 표시할 배열과 라벨 초기화
			@Override
			public void actionPerformed(ActionEvent e) {
				textFieldTrue(input);

				for (int i = 0; i < input.length; i++)
					input[i].setText("");

				r[0].setSelected(true);

				list.clear();

				// value.setText("");

				action.value = 0;

				pre.setEnabled(false);
				next.setEnabled(false);
				start.setEnabled(true);
				step.setText("Step");

				for (int j = 0; j < pictureText.length; j++)
					pictureText[j].setText("");

				radioButtonTrue(r);
			}
		});
	}

	public static boolean isStringDouble(JTextField[] t) { // 입력창의 값이 double형이 맞는지 확인
		try {
			for (int i = 0; i < t.length; i++)
				Double.parseDouble(t[i].getText());
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static void textFieldTrue(JTextField[] j1) { // textfield 전부 입력 가능
		for (int i = 0; i < j1.length; i++)
			j1[i].setEnabled(true);
	}

	public static void textFieldFalse(JTextField[] j2) { // textfield 전부 입력 불가능
		for (int i = 0; i < j2.length; i++)
			j2[i].setEnabled(false);
	}

	public static void radioButtonTrue(JRadioButton[] r1) { // radiobutton 전부 사용 가능
		for (int i = 0; i < r1.length; i++)
			r1[i].setEnabled(true);
	}

	public static void radioButtonFalse(JRadioButton[] r2) { // radiobutton 전부 사용 불가능
		for (int i = 0; i < r2.length; i++)
			r2[i].setEnabled(false);
	}
}
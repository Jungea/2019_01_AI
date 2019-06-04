package tools.chap3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

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
		// -----------------------------------------------------------
		JRadioButton r1 = new JRadioButton("AND");
		JRadioButton r2 = new JRadioButton("OR");
		JRadioButton r3 = new JRadioButton("NAND");

		ButtonGroup group = new ButtonGroup();
		group.add(r1);
		group.add(r2);
		group.add(r3);
		r1.setSelected(true);

		// 임시로 사진 위치와 크기 확인용
		JButton img = new JButton("사진 위치와 크기");
		img.setBounds(55, 350, 530, 300);
		add(img);

		JButton start = new JButton("학습 시작");
		JButton pre = new JButton("◀");
		JButton next = new JButton("▶");
		JButton reset = new JButton("초기화");

		start.setBounds(55, 680, 150, 50);
		pre.setBounds(480, 680, 50, 50);
		next.setBounds(535, 680, 50, 50);
		reset.setBounds(210, 680, 150, 50);

		add(start);
		add(pre);
		add(next);
		add(reset);

		JLabel l1 = new JLabel("w1");
		JLabel l2 = new JLabel("w2");
		JLabel l3 = new JLabel("θ");
		JLabel l4 = new JLabel("a");
		JLabel step = new JLabel("step");
		step.setBounds(55, 320, 50, 30);
		add(step);

		JTextField j1 = new JTextField();
		JTextField j2 = new JTextField();
		JTextField j3 = new JTextField();
		JTextField j4 = new JTextField();

		r1.setBounds(155, 130, 100, 50);
		r2.setBounds(260, 130, 100, 50);
		r3.setBounds(365, 130, 100, 50);
		add(r1);
		add(r2);
		add(r3);

		l1.setBounds(90, 220, 20, 20);
		l1.setHorizontalAlignment(JLabel.CENTER);
		l2.setBounds(205, 220, 20, 20);
		l2.setHorizontalAlignment(JLabel.CENTER);
		l3.setBounds(320, 220, 20, 20);
		l3.setHorizontalAlignment(JLabel.CENTER);
		l4.setBounds(435, 220, 20, 20);
		l4.setHorizontalAlignment(JLabel.CENTER);

		j1.setBounds(120, 210, 80, 40);
		j2.setBounds(235, 210, 80, 40);
		j3.setBounds(350, 210, 80, 40);
		j4.setBounds(455, 210, 80, 40);

		add(l1);
		add(l2);
		add(l3);
		add(l4);

		add(j1);
		add(j2);
		add(j3);
		add(j4);

		ArrayList<String> list = new ArrayList<>();

		JLabel index = new JLabel("X1 X2 F   W1   W2  Y  d  W1   W2\n");
		index.setBounds(210, 260, 200, 30);
		index.setHorizontalAlignment(JLabel.CENTER);
		// index.setBorder(new LineBorder(Color.black,2));
		add(index);

		JLabel value = new JLabel();
		value.setBounds(210, 290, 200, 30);
		value.setHorizontalAlignment(JLabel.CENTER);
		// value.setBorder(new LineBorder(Color.black,2));
		add(value);

		// -------------------------------------------위 소스 gui 추가용
		Action action = new Action();

		start.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (!isStringDouble(j1.getText()) || !isStringDouble(j2.getText()) || !isStringDouble(j3.getText())
						|| !isStringDouble(j4.getText())) {
					JOptionPane.showMessageDialog(null, "값을 다시 입력하세요.", "값 오류", JOptionPane.ERROR_MESSAGE);
					return;
				}

				int a = 0;
				if (r1.isSelected())
					a = 1;
				else if (r2.isSelected())
					a = 2;
				else if (r3.isSelected())
					a = 3;
				double[] array = new double[4];

				array[0] = Double.parseDouble(j1.getText());
				array[1] = Double.parseDouble(j2.getText());
				array[2] = Double.parseDouble(j3.getText());
				array[3] = Double.parseDouble(j4.getText());

				new Perceptron();
				Perceptron.main(a, array);

				if (Perceptron.array.size() >= 100) {
					pre.setEnabled(false);
					next.setEnabled(false);
					j1.setEnabled(false);
					j2.setEnabled(false);
					j3.setEnabled(false);
					j4.setEnabled(false);
					start.setEnabled(false);
					r1.setEnabled(false);
					r2.setEnabled(false);
					r3.setEnabled(false);
					JOptionPane.showMessageDialog(null, "학습에 실패하였습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				} else {
					list.addAll(Perceptron.array);
					value.setText(Perceptron.array.get(0));
				}
			}
		});

		pre.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.isEmpty()) {
					JOptionPane.showMessageDialog(null, "학습 시작 버튼을 눌러주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}

				value.setText(list.get(--action.value)); // 현재 값 이전 위치의 값을 저장.
				if (action.value == 0)
					pre.setEnabled(false);

				action.FalseEnabled(j1);
				action.FalseEnabled(j2);
				action.FalseEnabled(j3);
				action.FalseEnabled(j4);

				if (action.value < list.size() - 1)
					next.setEnabled(true);

				if (action.value % 4 == 0) {
					int a = action.value / 4 + 1;
					step.setText("step " + a);
				}
			}
		});

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (list.isEmpty()) {
					JOptionPane.showMessageDialog(null, "학습 시작 버튼을 눌러주세요.", "오류", JOptionPane.ERROR_MESSAGE);
					return;
				}

				value.setText(list.get(++action.value)); // 현재 값 다음 위치의 값 저장.
				if (action.value == list.size() - 1)
					next.setEnabled(false);

				action.FalseEnabled(j1);
				action.FalseEnabled(j2);
				action.FalseEnabled(j3);
				action.FalseEnabled(j4);

				if (action.value > 0)
					pre.setEnabled(true);

				if (action.value % 4 == 0) {
					int a = action.value / 4 + 1;
					step.setText("step " + a);
				}
			}
		});

		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				j1.setEnabled(true);
				action.TrueEnabled(j1);
				action.TrueEnabled(j2);
				action.TrueEnabled(j3);
				action.TrueEnabled(j4);

				j1.setText("");
				j2.setText("");
				j3.setText("");
				j4.setText("");
				r1.setSelected(true);
				list.clear();
				value.setText("");
				action.value = 0;
				pre.setEnabled(true);
				next.setEnabled(true);
				start.setEnabled(true);
				r1.setEnabled(true);
				r2.setEnabled(true);
				r3.setEnabled(true);
			}
		});
	}

	public static boolean isStringDouble(String s) { // 받은 문자열이 정수나 실수로 변환 가능한지 여부
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
package tools.chap2;

import java.awt.Color;
import java.awt.Dimension;
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

public class ResultIPanel extends JPanel {

	static LineBorder lb = new LineBorder(Color.BLUE, 1);

	static int id = 0;
	static double idCalValue = 0;

	public ResultIPanel(MainFrame mainFrame, int check, String[] header, String[][] context) {
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

		JLabel iTitle_img = new JLabel(new ImageIcon("image/ICal.png"));
		iTitle_img.setBounds(45, 50, 540, 86);
		add(iTitle_img);

		JPanel showPanel = new JPanel();
		showPanel.setLayout(null);
		showPanel.setBackground(Color.WHITE);

		showPanel.setBounds(45, 160, 540, 530);
		showPanel.setBorder(lb);
		add(showPanel);

		ID3 id3 = new ID3(check, header, context);

		Integer[] b = (Integer[]) id3.idMap.values().toArray(new Integer[id3.idMap.values().size()]);
		Integer[][] bb = new Integer[1][b.length];
		bb[0] = b;
		String[] a = (String[]) id3.idMap.keySet().toArray(new String[id3.idMap.keySet().size()]);

		JTable tt = new JTable(bb, a);
		JScrollPane tableSP = new JScrollPane(tt, JScrollPane.VERTICAL_SCROLLBAR_NEVER,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tableSP.setBounds(20, 20, 300, 60);
		tt.setRowHeight(30);
		tt.getTableHeader().setPreferredSize(new Dimension(30, 30));
		showPanel.add(tableSP);

		JTextArea tf = new JTextArea();
		tf.setBounds(20, 20, 500, 100);
		tf.setBorder(lb);
		showPanel.add(tf);
		tf.setText(id3.id + "\n" + id3.idMap.values() + "\n");

	}

	public static void addI(int id, String[] header, String[][] context) {
		// I(?)를 위한 Map 만들기
		int totalSize = context.length;
		String[][] cutIdTable = cutColTable(id, context);
		Map<String, Integer> idMap = new TreeMap<>();
		for (int k = 0; k < cutIdTable.length; k++) {
			Integer i = idMap.get(cutIdTable[k][0]);
			if (i == null)
				i = 0;
			idMap.put(cutIdTable[k][0], i + 1);
		}

		// System.out.println(idMap);

		int[] idValueArr = convertInt(idMap.values(), totalSize); // Map의 value값을 int[]로
		idCalValue = floor(rowCal(idValueArr));
		System.out.println("I(" + header[id] + ") = " + idCalValue);
		System.out.println();
	}

	public static double floor(double d) {
		return (int) (d * 100) / 100.0;
	}

	// Collection<Integer> -> int[]
	public static int[] convertInt(Collection<Integer> integers, int totalSize) {
		int[] ret = new int[integers.size() + 1];
		Iterator<Integer> it = integers.iterator();
		int i = 0;
		while (it.hasNext())
			ret[i++] = it.next();

		ret[i] = totalSize;
		return ret;
	}

	// 열 잘라 배열만들기
	static String[][] cutColTable(int col, String[][] value) {
		String[][] table = new String[value.length][1];

		for (int i = 0; i < value.length; i++)
			table[i][0] = value[i][col];

		return table;
	}

	// 배열 2개 합치기
	static String[][] joinTable(String[][] t1, String[][] t2) {
		String[][] table = new String[t1.length][2];

		for (int i = 0; i < t1.length; i++)
			table[i][0] = t1[i][0];
		for (int i = 0; i < t2.length; i++)
			table[i][1] = t2[i][0];

		return table;
	}

	// 로그계산
	// log2(2/5) => logCal(2, 2/5)
	static double logCal(double base, double x) {
		return Math.log10(x) / Math.log10(base);
	}

	// 열 잘라 배열 만들기
	static int[] cutRowTable(int row, int[][] value) {
		int[] table = new int[value[0].length];

		for (int i = 0; i < value[0].length; i++)
			table[i] = value[row][i];

		return table;
	}

	// static int[][] createCountTable()

	// 행 E(P) 계산 중 일부
	static double rowCal(int[] count) {
		double total = 0.0;
		BigDecimal[] big = new BigDecimal[4];
		for (int i = 0; i < count.length - 1; i++) { // 로그 계산값 더하기(합이 있는 열 전까지)
			if (count[i] == 0)
				continue;
			double fraction = (double) count[i] / count[count.length - 1]; // 분수(2/5)
			big[0] = new BigDecimal(String.valueOf(-fraction)); // 앞 수식

			big[1] = new BigDecimal(String.valueOf(logCal(2, fraction))); // 뒤 수식

			double result = big[0].multiply(big[1]).doubleValue();

			big[2] = new BigDecimal(String.valueOf(total));
			big[3] = new BigDecimal(String.valueOf(result));
			total = big[2].add(big[3]).doubleValue(); // row 계산 결과

			// System.out.println("----------------------------");

			// System.out.println(baseLog(l, 2));
			// System.out.println(big1.multiply(big2).doubleValue());
			// System.out.println(aaa);

		}
		return total;
	}
}

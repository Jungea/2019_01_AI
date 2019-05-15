package id3;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {
	static int id = 0;
	static double idValue = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] title = new String[] { "����", "����", "��" };
		String[][] value = new String[][] { { "�׷���", "����", "�ƴϴ�" }, { "�׷���", "����", "�ƴϴ�" }, { "�ƴϴ�", "����", "�׷���" },
				{ "�ƴϴ�", "����", "�ƴϴ�" }, { "�ƴϴ�", "�ִ�", "�׷���" } };
		int totalSize = value.length;
		try (Scanner scan = new Scanner(System.in)) {
			int opt;
			int j = 1;

			do {
				System.out.println("I(?)�� ���ÿ�.");
				for (int i = 0; i < title.length; i++)
					System.out.print((i + 1) + "." + title[i] + "  ");
				System.out.print((title.length + 1) + ".���� : ");
				id = scan.nextInt();
				if (id == (title.length))
					break;

				String[][] idTable = cutColTable(id+1, value);
				Map<String, Integer> idMap = new TreeMap<>();
				for (int k = 0; k < idTable.length; k++) {
					Integer i = idMap.get(idTable[k][0]);
					if (i == null)
						i = 0;
					idMap.put(idTable[k][0], i + 1);
				}
				
				int[] idArr = convertInt(idMap.values(), totalSize);
				System.out.println("I(" + title[id] + ") = " + rowCal(idArr));
				System.out.println();

				
				System.out.println("E(?)�� ���ÿ�.");
				for (int i = 0; i < title.length; i++) {
					if ((i + 1) != id) {
						System.out.print(j + "." + title[i] + "  ");
						j++;
					}
				}
				System.out.print(j + ".���� : ");
				opt = scan.nextInt();

				System.out.println();
			} while (opt != j);

			System.out.println("���α׷��� �����մϴ�.");
		}

		System.out.println();
		Set<String> idSet = new TreeSet<>();
		for (int k = 0; k < value.length; k++)
			idSet.add(value[k][0]);

		Set<String> bSet = new TreeSet<>();
		for (int k = 0; k < value.length; k++)
			bSet.add(value[k][1]);
		System.out.println("idSet" + idSet);
		System.out.println("bSet" + bSet);

		String[] idArr = idSet.toArray(new String[0]);
		System.out.println("idArr" + Arrays.toString(idArr));

		String[] b = bSet.toArray(new String[0]);
		System.out.println("b" + Arrays.toString(b));

		int[][] count = new int[idArr.length + 1][b.length + 1]; // ǥ ����
		int rowLast = count.length - 1; // �� ������ �ε���
		int colLast = count[0].length - 1; // �� ������ �ε���
		int row;
		int col;
		for (int k = 0; k < value.length; k++) {
			row = Arrays.binarySearch(idArr, value[k][0]);
			col = Arrays.binarySearch(b, value[k][1]);

			count[row][col]++;
			count[row][colLast]++;
			count[rowLast][col]++;
			count[rowLast][colLast]++;
		}

		System.out.println("count" + Arrays.deepToString(count));

		double sum = 0.0;
		BigDecimal[] big = new BigDecimal[3];

		for (int i = 0; i < idArr.length; i++) {
			if (count[i][colLast] == 0)
				continue;
			double fraction = (double) count[i][colLast] / count[rowLast][colLast];

			big[0] = new BigDecimal(String.valueOf(fraction));
			big[1] = new BigDecimal(String.valueOf(rowCal(cutRowTable(i, count))));
			big[2] = new BigDecimal(String.valueOf(sum));

			sum = big[0].multiply(big[1]).add(big[2]).doubleValue();
			sum = (int) (sum * 1000) / 1000.0; // �Ҽ��� 4��° �ڸ����� ����
		}

		System.out.println(sum);
	}

	//Collection<Integer> -> int[]
	public static int[] convertInt(Collection<Integer> integers, int totalSize) {
		int[] ret = new int[integers.size() + 1];
		Iterator<Integer> it = integers.iterator();
		int i = 0;
		while (it.hasNext())
			ret[i++] = it.next();

		ret[i] = totalSize;
		return ret;
	}

	//�� �߶� �迭�����
	static String[][] cutColTable(int col, String[][] value) {
		String[][] table = new String[value.length][1];

		for (int i = 0; i < value.length; i++)
			table[i][0] = value[i][col];

		return table;
	}

	//�迭 2�� ��ġ��
	static String[][] joinTable(String[][] t1, String[][] t2) {
		String[][] table = new String[t1.length][2];

		for (int i = 0; i < t1.length; i++)
			table[i][0] = t1[i][0];
		for (int i = 0; i < t2.length; i++)
			table[i][1] = t2[i][0];

		return table;
	}

	// �αװ��
	// log2(2/5) => logCal(2, 2/5)
	static double logCal(double base, double x) {
		return Math.log10(x) / Math.log10(base);
	}

	// �� �߶� �迭 �����
	static int[] cutRowTable(int row, int[][] value) {
		int[] table = new int[value[0].length];

		for (int i = 0; i < value[0].length; i++)
			table[i] = value[row][i];

		return table;
	}

	//�� E(P) ��� �� �Ϻ�
	static double rowCal(int[] count) {
		double total = 0.0;
		BigDecimal[] big = new BigDecimal[4];
		for (int i = 0; i < count.length - 1; i++) { // �α� ��갪 ���ϱ�(���� �ִ� �� ������)
			if (count[i] == 0)
				continue;
			double fraction = (double) count[i] / count[count.length - 1]; // �м�(2/5)
			big[0] = new BigDecimal(String.valueOf(-fraction)); // �� ����

			big[1] = new BigDecimal(String.valueOf(logCal(2, fraction))); // �� ����

			double result = big[0].multiply(big[1]).doubleValue();
			double floorResult = (int) (result * 1000) / 1000.0; // �Ҽ��� 4��° �ڸ����� ����

			big[2] = new BigDecimal(String.valueOf(total));
			big[3] = new BigDecimal(String.valueOf(floorResult));
			total = big[2].add(big[3]).doubleValue(); // row ��� ���

			// System.out.println("----------------------------");

			// System.out.println(baseLog(l, 2));
			// System.out.println(big1.multiply(big2).doubleValue());
			// System.out.println(aaa);

		}
		return total;
	}

	static double rowCal2(int row, int[][] count) {
		double total = 0.0;
		BigDecimal[] big = new BigDecimal[4];
		for (int i = 0; i < count[0].length - 1; i++) { // �α� ��갪 ���ϱ�(���� �ִ� �� ������)
			if (count[row][i] == 0)
				continue;
			double fraction = (double) count[row][i] / count[row][count.length - 1]; // �м�(2/5)
			big[0] = new BigDecimal(String.valueOf(-fraction)); // �� ����

			big[1] = new BigDecimal(String.valueOf(logCal(2, fraction))); // �� ����

			double result = big[0].multiply(big[1]).doubleValue();
			double floorResult = (int) (result * 1000) / 1000.0; // �Ҽ��� 4��° �ڸ����� ����

			big[2] = new BigDecimal(String.valueOf(total));
			big[3] = new BigDecimal(String.valueOf(floorResult));
			total = big[2].add(big[3]).doubleValue(); // row ��� ���

			// System.out.println("----------------------------");

			// System.out.println(baseLog(l, 2));
			// System.out.println(big1.multiply(big2).doubleValue());
			// System.out.println(aaa);

		}
		return total;
	}
}

package id3;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {
	static int id3 = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] title = new String[] { "수술", "가족", "집" };
		String[][] value = new String[][] { { "그렇다", "없다", "아니다" }, { "그렇다", "없다", "아니다" }, { "아니다", "없다", "그렇다" },
				{ "아니다", "없다", "아니다" }, { "아니다", "있다", "그렇다" } };

		System.out.println("value" + Arrays.deepToString(value));

		try (Scanner scan = new Scanner(System.in)) {
			int opt;
			int j = 1;

			do {
				System.out.println("I(?)를 고르시오.");
				for (int i = 0; i < title.length; i++)
					System.out.print((i + 1) + "." + title[i] + "  ");
				System.out.print((title.length + 1) + ".종료 : ");
				id3 = scan.nextInt();
				if (id3 == (title.length + 1))
					break;

				System.out.println("E(?)를 고르시오.");

				for (int i = 0; i < title.length; i++) {
					if ((i + 1) != id3) {
						System.out.print(j + "." + title[i] + "  ");
						j++;
					}
				}
				System.out.print(j + ".종료 : ");
				opt = scan.nextInt();

				switch (opt) {
				case 1:
					System.out.println("\nAND");

					break;

				case 2:
					System.out.println("\nOR");

					break;

				case 3:
					System.out.println("\nNAND");

					break;

				}

				System.out.println();
			} while (opt != j);

			System.out.println("프로그램을 종료합니다.");
		}

		System.out.println();
		Set<String> aSet = new TreeSet<>();
		for (int k = 0; k < value.length; k++)
			aSet.add(value[k][0]);
		Set<String> bSet = new TreeSet<>();
		for (int k = 0; k < value.length; k++)
			bSet.add(value[k][1]);
		System.out.println("aSet" + aSet);
		System.out.println("bSet" + bSet);

		String[] a = aSet.toArray(new String[0]);
		System.out.println("a" + Arrays.toString(a));
		String[] b = bSet.toArray(new String[0]);
		System.out.println("b" + Arrays.toString(b));

		int[][] count = new int[a.length + 1][b.length + 1]; // 표 생성
		int rowLast = count.length - 1; // 행 마지막 인덱스
		int colLast = count[0].length - 1; // 열 마지막 인덱스
		int row;
		int col;
		for (int k = 0; k < value.length; k++) {
			row = Arrays.binarySearch(a, value[k][0]);
			col = Arrays.binarySearch(b, value[k][1]);

			count[row][col]++;
			count[row][colLast]++;
			count[rowLast][col]++;
			count[rowLast][colLast]++;
		}

		System.out.println("count" + Arrays.deepToString(count));

		System.out.println("I(" + title[title.length - 1] + ") = " + rowCal(rowLast, value, count));

		double sum = 0.0;
		BigDecimal[] big = new BigDecimal[3];

		for (int i = 0; i < a.length; i++) {
			if (count[i][colLast] == 0)
				continue;
			double fraction = (double) count[i][colLast] / count[rowLast][colLast];

			big[0] = new BigDecimal(String.valueOf(fraction));
			big[1] = new BigDecimal(String.valueOf(rowCal(i, value, count)));
			big[2] = new BigDecimal(String.valueOf(sum));

			sum = big[0].multiply(big[1]).add(big[2]).doubleValue();
			sum = (int) (sum * 1000) / 1000.0; // 소수점 4번째 자리부터 버림
		}

		System.out.println(sum);
	}

	// 로그계산
	// log2(2/5) => logCal(2, 2/5)
	static double logCal(double base, double x) {
		return Math.log10(x) / Math.log10(base);
	}

	static double rowCal(int row, String[][] value, int[][] count) {
		double total = 0.0;
		BigDecimal[] big = new BigDecimal[4];
		for (int i = 0; i < count[0].length - 1; i++) { // 로그 계산값 더하기(합이 있는 열 전까지)
			if (count[row][i] == 0)
				continue;
			double fraction = (double) count[row][i] / count[row][count.length - 1]; // 분수(2/5)
			big[0] = new BigDecimal(String.valueOf(-fraction)); // 앞 수식

			big[1] = new BigDecimal(String.valueOf(logCal(2, fraction))); // 뒤 수식

			double result = big[0].multiply(big[1]).doubleValue();
			double floorResult = (int) (result * 1000) / 1000.0; // 소수점 4번째 자리부터 버림

			big[2] = new BigDecimal(String.valueOf(total));
			big[3] = new BigDecimal(String.valueOf(floorResult));
			total = big[2].add(big[3]).doubleValue(); // row 계산 결과

			// System.out.println("----------------------------");

			// System.out.println(baseLog(l, 2));
			// System.out.println(big1.multiply(big2).doubleValue());
			// System.out.println(aaa);

		}
		return total;
	}
}

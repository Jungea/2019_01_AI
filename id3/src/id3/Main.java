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
	static double idCalValue = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] title = new String[] 
			{ "큰수술", "가족", "60세이상", "집" };
		String[][] value = new String[][] { 
			{ "그렇다", "없다", "아니다", "아니다" }, 
			{ "그렇다", "없다", "그렇다", "아니다" },
			{ "아니다", "없다", "아니다", "그렇다" }, 
			{ "아니다", "없다", "그렇다", "아니다" }, 
			{ "아니다", "있다", "그렇다", "그렇다" } 
		};
		
		int totalSize = value.length;
		try (Scanner scan = new Scanner(System.in)) {
			int opt;

			while (true) {
				System.out.println("I(?)를 고르시오.");
				for (int i = 0; i < title.length; i++)
					System.out.print((i + 1) + "." + title[i] + "  ");
				System.out.print((title.length + 1) + ".종료 : ");
				id = scan.nextInt() - 1; // 2
				if (id == title.length)
					break;

				// I(?)를 위한 Map 만들기
				String[][] cutIdTable = cutColTable(id, value);
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
				System.out.println("I(" + title[id] + ") = " + idCalValue);
				System.out.println();

				//////////////////////////////////////////////

				while (true) {
					System.out.println("E(?)를 고르시오.");
					for (int i = 0; i < title.length; i++)
						System.out.print((i + 1) + "." + title[i] + "  ");
					System.out.print((title.length + 1) + ".종료 : ");
					opt = scan.nextInt() - 1;
					if (opt == id) {
						System.out.println("I(" + title[id] + ")와 같은 선택입니다. 다시고르시오.\n");
						continue;
					}
					if (opt == title.length) {
						System.out.println("\n=================================");
						break;
					}

					String[] idKeyArr = idMap.keySet().toArray(new String[0]);
					// System.out.println("idKeyArr" + Arrays.toString(idKeyArr));

					String[][] cutEpTable = cutColTable(opt, value);
					Set<String> eSet = new TreeSet<>();
					for (int k = 0; k < value.length; k++)
						eSet.add(cutEpTable[k][0]);
					// System.out.println("bSet" + eSet);

					String[] eArr = eSet.toArray(new String[0]);
					// System.out.println("eArr" + Arrays.toString(eArr));

					String[][] idEpTable = joinTable(cutEpTable, cutIdTable);

					// System.out.println(Arrays.deepToString(idEpTable));

					int[][] count = new int[eArr.length][idKeyArr.length + 1]; // 표 생성
					int colLast = count[0].length - 1; // 열 마지막 인덱스
					int row;
					int col;
					for (int k = 0; k < idEpTable.length; k++) {

						row = Arrays.binarySearch(eArr, idEpTable[k][0]);
						col = Arrays.binarySearch(idKeyArr, idEpTable[k][1]);

						// System.out.println(row + " " + col);
						count[row][col]++;
						count[row][colLast]++;
					}

					// System.out.println("count" + Arrays.deepToString(count));

					double sum = 0.0;
					BigDecimal[] big = new BigDecimal[3];

					for (int i = 0; i < idKeyArr.length; i++) {
						if (count[i][colLast] == 0)
							continue;
						double fraction = (double) count[i][colLast] / idValueArr[idValueArr.length - 1];

						big[0] = new BigDecimal(String.valueOf(fraction));
						big[1] = new BigDecimal(String.valueOf(rowCal(cutRowTable(i, count))));
						big[2] = new BigDecimal(String.valueOf(sum));

						sum = big[0].multiply(big[1]).add(big[2]).doubleValue();
						sum = floor(sum);
					}

					System.out.println("E(" + title[opt] + ") = " + sum);

					BigDecimal big1 = new BigDecimal(String.valueOf(idCalValue));
					BigDecimal big2 = new BigDecimal(String.valueOf(sum));
					double gain = big1.subtract(big2).doubleValue();
					System.out.println("Gain(" + title[opt] + ") = " + gain);
					System.out.println("\n---------------------------------");
				}

			}

			System.out.println("프로그램을 종료합니다.");
		}

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

	static double rowCal2(int row, int[][] count) {
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

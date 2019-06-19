package tools.chap2;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import tools.MyBTree;
import tools.MyNode;

public class ID3 {
	int id = 0;

	Map<String, Integer> idMap;
	String[][] cutIdTable;
	int[] idValueArr;
	String[] idKeyArr;
	String[] header;
	String[][] context;
	int totalSize;
	StringBuilder sb = new StringBuilder();

	double[] resultValue;
	String[] process;
	double[] gain;
	int[] gainNum;
	String[][] copyContext;

	MyBTree tree;

	public ID3(int check, String[] header, String[][] context) {
		id = check;
		this.header = header;
		this.context = context;
		this.copyContext = new String[context.length][context[0].length];
		for (int i = 0; i < context.length; i++)
			System.arraycopy(context[i], 0, copyContext[i], 0, context[0].length);

		totalSize = context.length;
		resultValue = new double[header.length];
		process = new String[header.length];
		gain = new double[header.length];
		gainNum = new int[header.length - 2];
		ICalculator();
		process[check] = sb.toString();
		sb.setLength(0);
		for (int i = 1; i < header.length; i++) {
			if (i == id)
				continue;

			ECalculator(i);
			process[i] = sb.toString();
			sb.setLength(0);
		}

		for (int i = 1; i < header.length; i++) {
			System.out.println(process[i]);
			System.out.println(resultValue[i]);
			System.out.println(gain[i]);
			System.out.println();
		}
		gainDESC();
	}

	public void mt() {
		tree = new MyBTree(new MyNode(header[gainNum[0]]));
		makeTree(0);
	}

	public void makeTree(int num) {

		String[][] cutEpTable = cutColTable(gainNum[num], copyContext);
		System.out.println("******++++" + Arrays.deepToString(cutEpTable));

		Set<String> eSet = new TreeSet<>();
		for (int k = 0; k < context.length; k++) {
			if (cutEpTable[k][0] != null) {
				eSet.add(cutEpTable[k][0]);
			}
		}

		String[] eArr = eSet.toArray(new String[0]);
		System.out.println("++++" + Arrays.deepToString(eArr));
		cutIdTable = cutColTable(id, copyContext); // 표 열단위로 자르기

		String[][] idEpTable = joinTable(cutEpTable, cutIdTable); // I와 opt열만 있는 배열

		int[][] count = new int[eArr.length][idKeyArr.length + 1]; // 표 생성
		int colLast = count[0].length - 1; // 열 마지막 인덱스
		int row;
		int col;
		for (int k = 0; k < idEpTable.length; k++) {
			if (idEpTable[k][0] != null && idEpTable[k][1] != null) {
				row = Arrays.binarySearch(eArr, idEpTable[k][0]);
				col = Arrays.binarySearch(idKeyArr, idEpTable[k][1]);

				count[row][col]++;
				count[row][colLast]++;
			}
		}
		System.out.println("******" + Arrays.deepToString(count));

		for (int a = 0; a < count.length; a++) {
			if (count[a] != null || count[a][count[a].length - 1] != 0) {
				for (int b = 0; b < count[a].length - 1; b++) {
					if (count[a][b] == count[a][count[a].length - 1]) {
						System.out.println(idKeyArr[b] + "  ///////////");
						tree.add(new MyNode(idKeyArr[b]));
						removeRow(gainNum[num], eArr[a], idKeyArr[b]);

						if (num + 1 < gainNum.length) {
							System.out.println(header[gainNum[num + 1]]);
							tree.add(new MyNode(header[gainNum[num + 1]]));
							tree.moveTemp(1);
							makeTree(num + 1);
						}
						break;
					}
				}
			}
		}

	}

	public void removeRow(int opt, String row, String col) {
		System.out.println(opt + "   " + row + "   " + col);

		for (int i = 0; i < copyContext.length; i++) {
			if (copyContext[i][opt] == null)
				continue;
			if (copyContext[i][opt].equals(row) && copyContext[i][id].equals(col))
				setNull(i);
		}
		System.out.println("******" + Arrays.deepToString(copyContext));
		System.out.println("@@@@@@@@@@@@@" + Arrays.deepToString(context));
	}

	public void setNull(int i) {
		for (int j = 0; j < copyContext[0].length; j++)
			copyContext[i][j] = null;
	}

	public void gainDESC() {
		double[] tempGain = Arrays.copyOf(gain, gain.length);
		selectionSort(tempGain);
		for (int i = 0; i < tempGain.length - 2; i++) {
			for (int j = 0; j < gain.length; j++) {
				if (tempGain[i] == gain[j]) {
					gainNum[i] = j;
					break;
				}
			}
		}
		System.out.println(Arrays.toString(gainNum));

	}

	// 배열 a에서 i 위치와 j 위치의 값을 서로 바꾼다
	static void swap(double[] a, int i, int j) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;

	}

	// 배열 a의 start 위치부터 끝까지에서 가장 작은 값의 위치(index)를 리턴한다.
	static int findMax(double[] a, int start) {
		int index = start;
		for (int i = start + 1; i < a.length; i++)
			if (a[i] > a[index])
				index = i;

		return index;

	}

	// selection sort
	static void selectionSort(double[] a) {
		for (int i = 0; i < a.length - 1; ++i) {
			int minIndex = findMax(a, i); // 배열 a의 i 위치부터 끝까지에서 가장 작은 값을 찾아서
			swap(a, i, minIndex); // 그 값을 i 위치로 이동한다
		}
	}

	public String getProcess(int index) {
		return process[index];
	}

	public double getResultValue(int index) {
		return resultValue[index];
	}

	public double getGain(int index) {
		return gain[index];
	}

	public int minValueIndex() {
		return 0;
	}

	// I(?)를 위한 Map 만들기
	public void ICalculator() {
		cutIdTable = cutColTable(id, context); // 표 열단위로 자르기
		idMap = new TreeMap<>(); // context value 집합
		for (int k = 0; k < cutIdTable.length; k++) { // Map 만들기
			Integer i = idMap.get(cutIdTable[k][0]);
			if (i == null)
				i = 0;
			idMap.put(cutIdTable[k][0], i + 1);
		}
		idValueArr = convertInt(idMap.values(), totalSize); // Map의 Value값을 int[]로
		resultValue[id] = floor(rowCal(idValueArr));
		System.out.println("I(" + header[id] + ") = " + resultValue[id]);
		System.out.println();
		idKeyArr = idMap.keySet().toArray(new String[0]); // I 집합->배열

	}

	public String[][] makeContext(int index) {
		int[][] a = makeCount(index, context);
		String[][] tableContext = new String[a.length + 1][a[0].length + 1];

		String[][] cutEpTable = cutColTable(index, context);

		Set<String> eSet = new TreeSet<>();
		for (int k = 0; k < context.length; k++)
			eSet.add(cutEpTable[k][0]);

		String[] eArr = eSet.toArray(new String[0]);

		int i = 0;
		for (; i < a.length; i++) {
			tableContext[i][0] = eArr[i];
			for (int j = 0; j < a[i].length; j++) {
				tableContext[i][j + 1] = String.valueOf(a[i][j]);
			}
		}
		tableContext[i][0] = "합계";
		for (int j = 0; j < idValueArr.length; j++)
			tableContext[i][j + 1] = String.valueOf(idValueArr[j]);

		System.out.println("idEpTable " + Arrays.deepToString(tableContext));

		return tableContext;
	}

	public int[][] makeCount(int index, String[][] context) {
		String[][] cutEpTable = cutColTable(index, context);

		Set<String> eSet = new TreeSet<>();
		System.out.println(Arrays.deepToString(cutEpTable));
		for (int k = 0; k < context.length; k++)
			eSet.add(cutEpTable[k][0]);

		String[] eArr = eSet.toArray(new String[0]);

		cutIdTable = cutColTable(id, context); // 표 열단위로 자르기
		System.out.println("count" + Arrays.deepToString(cutIdTable));
		String[][] idEpTable = joinTable(cutEpTable, cutIdTable); // I와 opt열만 있는 배열

		int[][] count = new int[eArr.length][idKeyArr.length + 1]; // 표 생성
		int colLast = count[0].length - 1; // 열 마지막 인덱스
		int row;
		int col;
		for (int k = 0; k < idEpTable.length; k++) {

			row = Arrays.binarySearch(eArr, idEpTable[k][0]);
			col = Arrays.binarySearch(idKeyArr, idEpTable[k][1]);

			count[row][col]++;
			count[row][colLast]++;
		}

		return count;
	}

	// E 계산
	public void ECalculator(int opt) {
		int[][] count = makeCount(opt, context);
		int colLast = count[0].length - 1; // 열 마지막 인덱스
	

//		double sum = 0.0;
		BigDecimal[] big = new BigDecimal[3];

		for (int i = 0; i < idKeyArr.length; i++) {
			if (count[i][colLast] == 0)
				continue;
			double fraction = (double) count[i][colLast] / idValueArr[idValueArr.length - 1];

			sb.append(" +" + count[i][colLast] + "/" + idValueArr[idValueArr.length - 1] + "(");

			big[0] = new BigDecimal(String.valueOf(fraction));
			big[1] = new BigDecimal(String.valueOf(rowCal(cutRowTable(i, count))));
			big[2] = new BigDecimal(String.valueOf(resultValue[opt]));

			sb.append(" ) \n         ");

			resultValue[opt] = big[0].multiply(big[1]).add(big[2]).doubleValue();
			resultValue[opt] = floor(resultValue[opt]);

		}

		System.out.println("E(" + header[opt] + ") = " + resultValue[opt]);

		BigDecimal big1 = new BigDecimal(String.valueOf(resultValue[id]));
		BigDecimal big2 = new BigDecimal(String.valueOf(resultValue[opt]));
		gain[opt] = big1.subtract(big2).doubleValue();
		System.out.println("Gain(" + header[opt] + ") = " + gain[opt]);
		System.out.println("\n---------------------------------");
	}

	public double floor(double d) {
		return (int) (d * 100) / 100.0;
	}

	// Collection<Integer> -> int[]
	public int[] convertInt(Collection<Integer> integers, int totalSize) {
		int[] ret = new int[integers.size() + 1];
		Iterator<Integer> it = integers.iterator();
		int i = 0;
		while (it.hasNext())
			ret[i++] = it.next();

		ret[i] = totalSize;
		return ret;
	}

	// 열 잘라 배열만들기
	String[][] cutColTable(int col, String[][] context) {
		String[][] table = new String[context.length][1];

		for (int i = 0; i < context.length; i++)
			if (context[i] != null)
				table[i][0] = context[i][col];

		return table;
	}

	// 배열 2개 합치기
	String[][] joinTable(String[][] t1, String[][] t2) {
		String[][] table = new String[t1.length][2];

		for (int i = 0; i < t1.length; i++)
			table[i][0] = t1[i][0];
		for (int i = 0; i < t2.length; i++)
			table[i][1] = t2[i][0];

		return table;
	}

	// 로그계산
	// log2(2/5) => logCal(2, 2/5)
	double logCal(double base, double x) {
		return Math.log10(x) / Math.log10(base);
	}

	// 열 잘라 배열 만들기
	int[] cutRowTable(int row, int[][] context) {
		int[] table = new int[context[0].length];

		for (int i = 0; i < context[0].length; i++)
			table[i] = context[row][i];

		return table;
	}

	// 행 E(P) 계산 중 일부
	double rowCal(int[] count) {
		double total = 0.0;
		BigDecimal[] big = new BigDecimal[4];
		for (int i = 0; i < count.length - 1; i++) { // 로그 계산값 더하기(합이 있는 열 전까지)
			if (count[i] == 0)
				continue;
			double fraction = (double) count[i] / count[count.length - 1]; // 분수(2/5)
			sb.append(" " + -count[i] + "/" + count[count.length - 1] + "log(" + count[i] + "/"
					+ count[count.length - 1] + ")");
			big[0] = new BigDecimal(String.valueOf(-fraction)); // 앞 수식

			big[1] = new BigDecimal(String.valueOf(logCal(2, fraction))); // 뒤 수식

			double result = big[0].multiply(big[1]).doubleValue();

			big[2] = new BigDecimal(String.valueOf(total));
			big[3] = new BigDecimal(String.valueOf(result));
			total = big[2].add(big[3]).doubleValue(); // row 계산 결과

			// System.out.println("----------------------------");

			// System.out.println(baseLog(l, 2));
			// System.out.println(big1.multiply(big2).doublecontext());
			// System.out.println(aaa);

		}
		return total;
	}

}

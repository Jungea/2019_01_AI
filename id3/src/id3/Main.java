package id3;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] value = new String[][] { { "그렇다", "그렇다" }, { "아니다", "아니다" }, { "그렇다", "아니다" } };

		System.out.println("value" + Arrays.deepToString(value));

		Map<String, Integer> aMap = new TreeMap<>();
		for (int k = 0; k < value.length; k++) {
			Integer i = aMap.get(value[k][0]);
			if (i == null)
				i = 0;
			aMap.put(value[k][0], i + 1);
		}
		System.out.println(aMap);

		Map<String, Integer> bMap = new TreeMap<>();
		for (int k = 0; k < value.length; k++) {
			Integer i = bMap.get(value[k][1]);
			if (i == null)
				i = 0;
			bMap.put(value[k][1], i + 1);
		}
		System.out.println(bMap);

		/*
		 * Set<String> aSet = new TreeSet<>(); for (int k = 0; k < value.length; k++)
		 * aSet.add(value[k][0]); Set<String> bSet = new TreeSet<>(); for (int k = 0; k
		 * < value.length; k++) bSet.add(value[k][1]); System.out.println("aSet" +
		 * aSet); System.out.println("bSet" + bSet);
		 */

		String[] a = aMap.keySet().toArray(new String[0]);
		System.out.println("a" + Arrays.toString(a));
		String[] b = bMap.keySet().toArray(new String[0]);
		System.out.println("b" + Arrays.toString(b));

		int row;
		int col;
		int[][] result = new int[a.length + 1][b.length + 1];
		for (int k = 0; k < value.length; k++) {
			row = Arrays.binarySearch(a, value[k][0]);
			col = Arrays.binarySearch(b, value[k][1]);
			result[row][col]++;
			result[row][b.length]++;
			result[a.length][col]++;
		}

		System.out.println("result" + Arrays.deepToString(result));

		System.out.println(aMap.get(a[0]));
		
		System.out.println("I(집)="+-Math.log(result[result.length-1][0]/value.length));
	}

}

package perceptron;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
	static int opt;
	static int[][] x = new int[][] { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } };
	static int[][] f = new int[4][1]; // 목표 출력 F
	static double[][] w = new double[2][2]; // 가중치
	static double theta;
	static double a; // 학습률
	static int count; // 모든 d가 0일때 step 종료
	static int y;
	static int d;
	static int step;
	static BigDecimal[] b = new BigDecimal[2];

	public static void main(String[] args) {
		try (Scanner scan = new Scanner(System.in)) {

			do {
				System.out.println("원하는 단층 퍼셉트론 학습을 선택하시오.");
				System.out.print("1.AND 2.OR 3.NAND 4.종료 : ");
				opt = scan.nextInt();
				step = 1;
				switch (opt) {
				case 1:
					System.out.println("\nAND");

					f = new int[][] { { 0 }, { 0 }, { 0 }, { 1 } };
					learn(f, scan);
					break;

				case 2:
					System.out.println("\nOR");

					f = new int[][] { { 0 }, { 1 }, { 1 }, { 1 } };
					learn(f, scan);
					break;

				case 3:
					System.out.println("\nNAND");

					f = new int[][] { { 1 }, { 1 }, { 1 }, { 0 } };
					learn(f, scan);

					break;

				}

				System.out.println();
			} while (opt != 4);

			System.out.println("프로그램을 종료합니다.");
		}
	}

	public static void learn(int[][] f, Scanner scan) {
		System.out.print("w1, w2, θ, a : ");
		w[0][0] = scan.nextDouble();
		w[1][0] = scan.nextDouble();
		theta = scan.nextDouble();
		a = scan.nextDouble();

		System.out.println("X1 X2 F   W1   W2  Y  d  W1   W2");
		do {
			count = 0;
			for (int i = 0; i < 4; i++) {
				w[0][1] = w[0][0];
				w[1][1] = w[1][0];
				y = (x[i][0] * w[0][0] + x[i][1] * w[1][0] > theta) ? 1 : 0;
				d = f[i][0] - y;
				if (d == 0)
					count++;
				else {
					b[0] = new BigDecimal(String.valueOf(a * d * x[i][0]));
					b[1] = new BigDecimal(String.valueOf(w[0][0]));
					w[0][0] = b[0].add(b[1]).doubleValue();

					b[0] = new BigDecimal(String.valueOf(a * d * x[i][1]));
					b[1] = new BigDecimal(String.valueOf(w[1][0]));
					w[1][0] = b[0].add(b[1]).doubleValue();
				}
				print(x[i], f[i], w, y, d);
			}
			System.out.println();
		} while (count != 4);

	}

	public static void print(int[] x, int[] f, double[][] w, int y, int d) {
		System.out.println(x[0] + "  " + x[1] + "  " + f[0] + "  " + w[0][1] + "  " + w[1][1] + "  " + y + "  " + d
				+ "  " + w[0][0] + "  " + w[1][0]);
	}
}

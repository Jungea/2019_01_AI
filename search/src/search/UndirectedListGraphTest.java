package search;

/*
 * 작성자: 정은애
 * 작성일: 2019.05.13.
 * 내용: 인접 무방향 그래프
 */

import java.util.Scanner;

public class UndirectedListGraphTest {
	public static void main(String[] args) {
		// 정점 수 n 입력
		Scanner scan = new Scanner(System.in);
		System.out.print("정점 수 입력: ");
		int n = scan.nextInt();
		System.out.println();

		// 정점 수가 n인 무방향 그래프를 생성
		UndirectedListGraph graph = new UndirectedListGraph(n);

		// 간선 수 e 입력
		System.out.print("간선 수 입력: ");
		int e = scan.nextInt();
		System.out.println();

		// e개의 간선(정점 쌍)을 입력받아 그래프에 삽입
		System.out.println(e + "개의 간선을 입력하세요 \n(각 간선은 정점 번호 2개를 whitespace로 구분하여 입력):");
		for (int i = 0; i < e; i++) {
			int v1 = scan.nextInt();
			int v2 = scan.nextInt();
			graph.addEdge(v1, v2);
		}

		// 각 정점의 인접 정점들을 출력
		System.out.println();
		for (int i = 0; i < n; i++) {
			System.out.print("정점 " + i + "에 인접한 정점 = ");
			graph.printAdjacentVertices(i);
			System.out.println();
		}

		for (int i = 0; i < n; i++) {
			System.out.print("\n" + i + ": ");
			graph.breadthFirstSearch(i);
		}
	}
}
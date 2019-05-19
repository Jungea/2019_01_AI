package search;

import java.util.Collections;
import java.util.LinkedList;

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
		System.out.println(e + "개의 간선을 입력하세요 (v1, v2, w)");
		int l = 0;
		while (l < e) {
			int v1 = scan.nextInt();
			int v2 = scan.nextInt();
			int w = scan.nextInt();
			if (graph.addEdge(v1, v2, w))
				l++;
		}

		// 각 정점의 인접 정점, 가중치들을 출력
//		System.out.println();
//		for (int i = 0; i < n; i++) {
//			System.out.print("정점 " + i + "에 인접한 정점 = ");
//			graph.printVW(i);
//			System.out.println();
//		}
		System.out.println("[암벽 등반 탐색]");
		graph.hillClimbing(0);
		System.out.println("=============================================== \n");
		
		System.out.println("[A* 알고리즘]");
		graph.aStartSearch(0);
		System.out.println("=============================================== \n");

	}
}
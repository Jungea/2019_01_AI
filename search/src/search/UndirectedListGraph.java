package search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedListGraph {
	private Node[] list;
	private int n; // 정점 수
	LinkedList<Integer> route;

	private class Node {
		int vertex;
		int weight;
		Node link;

		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}
	}

	public UndirectedListGraph(int n) {
		this.n = n;
		list = new Node[n];
	}

	public boolean hasEdge(int v1, int v2) { // 이미 존재하는 간선인지 확인
		// 무방향 그래프이므로 한쪽만 확인해도 된다.
		Node t = list[v1];

		while (t != null) {
			if (t.vertex == v2)
				return true;
			t = t.link;
		}
		return false;
	}

	public boolean isValid(int v) { // 정점 번호 확인
		return (0 <= v && v < n);
	}

	public boolean addEdge(int v1, int v2, int w) {
		if (!isValid(v1) || !isValid(v2)) {
			System.out.println("간선 삽입 오류 - 잘못된 정점 번호입니다. (" + v1 + ", " + v2 + ")");
			return false;
		} else if (hasEdge(v1, v2)) {
			System.out.println("간선 삽입 오류 - 이미 존재하는 간선입니다. (" + v1 + ", " + v2 + ")");
			return false;
		} else {
			// list[v1]이 가리키는 단순연결리스트의 맨 앞에 v2 삽입
			Node v1AinsertNode = new Node(v2, w);
			v1AinsertNode.link = list[v1];
			list[v1] = v1AinsertNode;

			// list[v2]이 가리키는 단순연결리스트의 맨 앞에 v1 삽입
			Node v2insertNode = new Node(v1, w);
			v2insertNode.link = list[v2];
			list[v2] = v2insertNode;
			return true;
		}
	}

	public int getWeight(int v1, int v2) {
		if (!isValid(v1) || !isValid(v2)) {
			System.out.println("간선 삽입 오류 - 잘못된 정점 번호입니다. (" + v1 + ", " + v2 + ")");
			return -1;
		} else {
			Node t = list[v1];
			while (t != null) {
				if (t.vertex == v2)
					return t.weight;
				t = t.link;
			}

			return 0;
		}
	}

	public LinkedList<Integer> getVertices(int v) {
		if (!isValid(v))
			return null;
		else {
			LinkedList<Integer> result = new LinkedList<>();
			Node t = list[v];
			while (t != null) {
				result.addFirst(t.vertex);
				t = t.link;
			}

			return result;
		}
	}

	public void printVW(int v) {
		if (!isValid(v))
			System.out.println("잘못된 정점 번호입니다.");
		else {
			Node t = list[v];
			while (t != null) {
				System.out.print("(" + t.vertex + " " + t.weight + ") ");
				t = t.link;
			}
		}
	}

	// 암벽 등반 탐색
	public void hillClimbing(int start) {
		route = new LinkedList<>(); // 경로
		LinkedList<Integer> resultList = hill(start, start, route);
		resultList.add(start);

		int weight = 0;
		for (int i = 0; i < resultList.size() - 1; i++)
			weight += getWeight(resultList.get(i), resultList.get(i + 1));

		System.out.println("\n결과 = " + resultList + " " + weight);
	}

	// 암벽 등반 탐색 재귀문 (시작정점, 현재정점, 지나온 경로)
	public LinkedList<Integer> hill(int start, int now, LinkedList<Integer> route) {
		route.add(now);
		System.out.println(now + " " + route);

		LinkedList<Integer> vs = getVertices(now);
		vs.removeAll(route);
		System.out.println("vs " + vs); // 앞으로 갈 정점

		if (vs.isEmpty()) // 종료 조건
			return route;

		ArrayList<Integer> fList = new ArrayList<Integer>(); // 평가 함수
		int vsIndex = 0; // 다음에 갈 정점 인덱스(평가함수에 의해)

		for (Integer i : vs)
			fList.add(heuristicsR(start, i, route)); // 각 정점별 평가함수
		System.out.println(fList + "\n");

		vsIndex = minHIndex(fList); // 평가함수가 작은 정점의 인덱스

		return hill(start, vs.get(vsIndex), route);

	}

	// A* 알고리즘 (암벽등반 + g)
	public void aStartSearch(int start) {
		route = new LinkedList<>();
		LinkedList<Integer> resultList = aStar(start, start, route, 0);
		resultList.add(start);

		int weight = 0;
		for (int i = 0; i < resultList.size() - 1; i++)
			weight += getWeight(resultList.get(i), resultList.get(i + 1));

		System.out.println("\n결과 = " + resultList + " " + weight);
	}

	// A* 알고리즘 재귀문 (시작정점, 현재정점, 지나온 경로, 지나온 가중치)
	public LinkedList<Integer> aStar(int start, int now, LinkedList<Integer> route, int g) {
		route.add(now);
		System.out.println(now + " " + route);

		LinkedList<Integer> vs = getVertices(now);
		vs.removeAll(route);
		System.out.println("vs " + vs); // 앞으로 갈 정점

		if (vs.isEmpty()) // 종료 조건
			return route;

		ArrayList<Integer> fList = new ArrayList<Integer>();
		int vsIndex = 0;

		for (Integer i : vs)
			fList.add(heuristicsR(start, i, route) + (g + getWeight(now, i))); // 평가함수(h* + g)
		System.out.println(fList + "\n");

		vsIndex = minHIndex(fList); // 평가함수 최소값인 정점의 인덱스

		return aStar(start, vs.get(vsIndex), route, g + getWeight(now, vs.get(vsIndex)));

	}

	// 평가함수 최소값인 정점의 인덱스 계산
	public int minHIndex(ArrayList<Integer> fList) {
		int index = 0;
		int min = fList.get(index);

		for (int i = 1; i < fList.size(); i++) {
			if (min > fList.get(i)) {
				min = fList.get(i);
				index = i;
			}
		}

		return index;

	}

	// h* 계산(앞으로 가야할 거리, 가중치)
	public int heuristics(int v, LinkedList<Integer> route) {
		LinkedList<Integer> vs = getVertices(v);
		vs.removeAll(route);
		int h = 0;
		for (Integer i : vs)
			h += getWeight(v, i);

		return h;
	}

	// h* 계산(앞으로 가야할 거리, 가중치) <시작정점 복귀>
	public int heuristicsR(int start, int v, LinkedList<Integer> route) {
		LinkedList<Integer> vs = getVertices(v);
		vs.removeAll(route);
		int h = getWeight(start, v); // 시작 정점과의 가중치 추가
		for (Integer i : vs)
			h += getWeight(v, i);

		return h;
	}

}
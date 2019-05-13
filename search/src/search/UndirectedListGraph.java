package search;

import java.util.LinkedList;
import java.util.Queue;

public class UndirectedListGraph {
	private Node[] list;
	private int n; // 정점 수
	
	private boolean[] visited;
	private Queue<Integer> q;

	private class Node {
		int vertex;
		Node link;

		public Node(int vertex) {
			this.vertex = vertex;
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

	public void addEdge(int v1, int v2) {
		if (!isValid(v1) || !isValid(v2))
			System.out.println("간선 삽입 오류 - 잘못된 정점 번호입니다. (" + v1 + ", " + v2 + ")");
		else if (hasEdge(v1, v2))
			System.out.println("간선 삽입 오류 - 이미 존재하는 간선입니다. (" + v1 + ", " + v2 + ")");
		else {
			// list[v1]이 가리키는 단순연결리스트의 맨 앞에 v2 삽입
			Node v1AinsertNode = new Node(v2);
			v1AinsertNode.link = list[v1];
			list[v1] = v1AinsertNode;

			// list[v2]이 가리키는 단순연결리스트의 맨 앞에 v1 삽입
			Node v2insertNode = new Node(v1);
			v2insertNode.link = list[v2];
			list[v2] = v2insertNode;
		}
	}

	public void printAdjacentVertices(int v) {
		if (!isValid(v))
			System.out.println("잘못된 정점 번호입니다.");
		else {
			Node t = list[v];
			while (t != null) {
				System.out.print(t.vertex + " ");
				t = t.link;
			}
		}
	}

	public void breadthFirstSearch(int v) {
		if (!isValid(v))
			System.out.println("잘못된 정점 번호입니다.");
		else {
			visited = new boolean[n];
			q = new LinkedList<>();
			System.out.print(v + " ");
			visited[v] = true;
			bfs(v);
		}
	}

	private void bfs(int v) {
		Node t = list[v];
		while (t != null) {
			if (!visited[t.vertex]) {
				q.add(t.vertex);
				System.out.print(t.vertex + " ");
				visited[t.vertex] = true;
			}
			t = t.link;
		}
		if (q.isEmpty()) // 큐가 비었으면
			return;

		if (t == null) // 인접한 정점이 없으면
			bfs(q.poll());

	}

}
package tools.chap1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyNode {
	int count;
	int[] data; // 데이터 목록
	MyNode parent; // 부모 노드에 대한 참조
	List<MyNode> child; // 자식 노드에 대한 참조

	MyNode() {
	}

	MyNode(int v1) {
		count = 1;
		data = new int[count];
		child = new ArrayList<MyNode>();
		data[0] = v1;
	}

	MyNode(int v1, int v2) {
		count = 2;
		data = new int[count];
		child = new ArrayList<MyNode>();
		data[0] = v1;
		data[1] = v2;
	}

	MyNode(int v1, int v2, int v3) {
		count = 3;
		data = new int[count];
		child = new ArrayList<MyNode>();
		data[0] = v1;
		data[1] = v2;
		data[2] = v3;
	}

	boolean isLeaf() {
		return this.child.size() == 0;
	}

	@Override
	public String toString() {
		return "[data=" + Arrays.toString(data) + "]";
	}
}

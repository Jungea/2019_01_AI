package tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyNode {
	public int count;
	public int[] data; // 데이터 목록
	public String[] sData;
	public MyNode parent; // 부모 노드에 대한 참조
	public List<MyNode> child; // 자식 노드에 대한 참조

	public MyNode() {
	}

	public MyNode(int v1) {
		count = 1;
		data = new int[count];
		child = new ArrayList<MyNode>();
		data[0] = v1;
	}

	public MyNode(int v1, int v2) {
		count = 2;
		data = new int[count];
		child = new ArrayList<MyNode>();
		data[0] = v1;
		data[1] = v2;
	}

	public MyNode(int v1, int v2, int v3) {
		count = 3;
		data = new int[count];
		child = new ArrayList<MyNode>();
		data[0] = v1;
		data[1] = v2;
		data[2] = v3;
	}

	public MyNode(String s) {
		count = 1;
		this.sData = new String[count];
		child = new ArrayList<MyNode>();
		this.sData[0] = s;
		// this.sData[1] = "";
	}

	public boolean isLeaf() {
		return this.child.size() == 0;
	}

	@Override
	public String toString() {
		return "[data=" + Arrays.toString(sData) + "]";
	}
}

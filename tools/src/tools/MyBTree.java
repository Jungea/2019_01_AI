package tools;

import java.util.ArrayList;

public class MyBTree {
	public MyNode root = new MyNode();
	MyNode temp;
	public int height;

	public MyBTree(MyNode n) {
		root = n;
		temp = root;
		height = 0;
	}

	public void add(MyNode n) {
		temp.child.add(n);
	}

	public void setGHValue(int i, int data1, int data2) {
		temp.child.get(i).data[1] = data1;
		temp.child.get(i).data[2] = data2;
	}

	public void setValue(int dataIndex, ArrayList<Integer> fList) {
		for (int i = 0; i < fList.size(); i++)
			temp.child.get(i).data[dataIndex] = fList.get(i);
	}

	public void moveTemp(int index) {
		temp = temp.child.get(index);
		height++;
	}

	public void print() {
		System.out.println("부모 " + temp);
		if (temp.isLeaf())
			return;
		for (MyNode n : temp.child)
			System.out.println(n);

		System.out.println();
	}

	public static void main(String[] args) {
		MyBTree tree = new MyBTree(new MyNode(1, 0, 0));

		tree.add(new MyNode(4, 5, 6));
		tree.add(new MyNode(7, 8, 9));
		tree.add(new MyNode(10, 11, 12));

		tree.print();
		tree.moveTemp(0);

		tree.add(new MyNode(7, 8, 9));
		tree.add(new MyNode(10, 11, 12));

		tree.print();
	}

}

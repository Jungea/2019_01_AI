package tools.chap1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class BTreeViewer {
	static JFrame frame = new JFrame("Btree Viewer");
	static BTreePanel treePanel = new BTreePanel();
	static JScrollPane scrollPane = new JScrollPane(treePanel);

	public static void main(String[] args) {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel buttonPanel = new JPanel();
		JTextField txtNodeSize = new JTextField(3);
		txtNodeSize.setText("2");
		JButton btnNodeSize = new JButton("노드 최대 크기 변경");
		JButton btnPrev = new JButton("previous");
		JButton btnNext = new JButton("next");
		JTextField txtValue = new JTextField(20);
		JButton btnAdd = new JButton("add");
		JButton btnRemove = new JButton("remove");

		ActionListener action = e -> {
			treePanel.capture();
//			try {
//				String[] a = txtValue.getText().split("[ ,]+");
//				for (String s : a) {
//					int value = Integer.parseInt(s);
//					if (e.getSource() == btnAdd) {
////						treePanel.tree.add(value);
//						treePanel.title = "ADD " + txtValue.getText();
//					} else {
////						treePanel.tree.remove(value);
//						treePanel.title = "REMOVE " + txtValue.getText();
//					}
//				}
//				treePanel.capture();
//				scrollPane.setViewportView(treePanel);
//				txtValue.setText("");
//				txtValue.requestFocus();
//				btnNext.setEnabled(false);
//				btnPrev.setEnabled(treePanel.images.size() >= 2);
//			} catch (Exception ex) {
//			}
		};
		ActionListener action2 = e -> {
			if (e.getSource() == btnPrev)
				treePanel.index = Math.max(treePanel.index - 1, 0);
			else
				treePanel.index = Math.min(treePanel.index + 1, treePanel.images.size() - 1);
			boolean b = treePanel.index == treePanel.images.size() - 1;
			btnAdd.setEnabled(b);
			btnRemove.setEnabled(b);
			btnNext.setEnabled(treePanel.index < treePanel.images.size() - 1);
			btnPrev.setEnabled(treePanel.index > 0);
			treePanel.repaint();
			txtValue.requestFocus();
		};
		btnAdd.addActionListener(action);
		btnRemove.addActionListener(action);
		btnPrev.addActionListener(action2);
		btnNext.addActionListener(action2);
		btnNext.setEnabled(false);
		btnPrev.setEnabled(false);
		buttonPanel.add(txtValue);
		buttonPanel.add(btnAdd);
		buttonPanel.add(btnRemove);
		buttonPanel.add(txtNodeSize);
		buttonPanel.add(btnNodeSize);
		buttonPanel.add(btnPrev);
		buttonPanel.add(btnNext);
		buttonPanel.setMaximumSize(new Dimension(2000, 60));
		mainPanel.add(buttonPanel);
		mainPanel.add(scrollPane);

		frame.setSize(1024, 1024);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add("Center", mainPanel);
		frame.setVisible(true);
		frame.getRootPane().setDefaultButton(btnAdd);
	}
}

//@SuppressWarnings("serial")
//class BTreePanel extends JPanel {
//	static final int DATA_WIDTH = 21, LINK_WIDTH = 2, NODE_HEIGHT = 20, X_MARGIN = 3, LAYER_HEIGHT = 60;
//	static Font narrowFont = new Font(Font.SERIF, Font.PLAIN, 12), defaultFont;
//	static MyBTree tree = new MyBTree(new MyNode(1, 0, 0));
//	String title;
//	ArrayList<BufferedImage> images = new ArrayList<>();
//	int index = -1;
//
//	public BTreePanel() {
//		tree = new MyBTree(new MyNode(1, 0, 0));
//		tree.add(new MyNode(4, 5, 6));
//		tree.add(new MyNode(7, 8, 9));
//		tree.add(new MyNode(10, 11, 12));
//
//		tree.print();
//		tree.moveTemp(0);
//
//		tree.add(new MyNode(7, 8, 9));
//		tree.add(new MyNode(10, 11, 12));
//
//		tree.print();
//
//		tree.moveTemp(1);
//
//		tree.add(new MyNode(7, 8, 9));
//		tree.add(new MyNode(10, 11, 12));
//
//		tree.print();
//	}
//
//	@Override
//	public Dimension getPreferredSize() {
//		return new Dimension(getSubTreeWidth(tree.root), 1024);
//	}
//
//	@Override
//	public void paint(Graphics graphics) {
//		super.paint(graphics);
//		if (index >= 0)
//			((Graphics2D) graphics).drawImage(images.get(index), 0, 20, null);
//	}
//
//	static void drawNode(Graphics2D g, MyNode node, int x0, int y0) {
//		if (node == null)
//			return;
//		int thisY = y0, thisX = x0 + (getSubTreeWidth(node) - getNodeWidth(node)) / 2;
//
//		Rectangle2D.Double rect = new Rectangle2D.Double(thisX, thisY, getNodeWidth(node), NODE_HEIGHT);
//		g.setColor(Color.WHITE);
//		g.fill(rect);
//		g.setColor(Color.BLACK);
//		g.draw(rect);
//		for (int i = 0; i < node.count; ++i) {
//			Rectangle2D.Double r = new Rectangle2D.Double(thisX + LINK_WIDTH + (DATA_WIDTH + LINK_WIDTH) * i, thisY,
//					DATA_WIDTH, NODE_HEIGHT);
//			drawCenteredString(g, String.valueOf(node.data[i]), r);
//		}
//		if (node.isLeaf() == false) {
//			int childX = x0, childY = y0 + LAYER_HEIGHT;
//			int linkY0 = thisY + NODE_HEIGHT, linkY1 = childY;
//			for (int i = 0; i < node.child.size(); ++i) {
//				int linkX0 = thisX + LINK_WIDTH / 2 + (DATA_WIDTH + LINK_WIDTH) * i;
//				int linkX1 = childX + getSubTreeWidth(node.child.get(i)) / 2;
//				g.drawLine(linkX0, linkY0, linkX1, linkY1);
//				drawNode(g, node.child.get(i), childX, childY);
//				childX += getSubTreeWidth(node.child.get(i)) + X_MARGIN;
//			}
//		}
//	}
//
//	static void drawCenteredString(Graphics2D g, String s, Rectangle2D rect) {
//		g.setFont(s.length() >= 3 ? narrowFont : defaultFont);
//		FontMetrics metrics = g.getFontMetrics();
//		int x = (int) (rect.getX() + (rect.getWidth() - metrics.stringWidth(s)) / 2);
//		int y = (int) (rect.getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());
//		g.drawString(s, x, y);
//	}
//
//	static int getNodeWidth(MyNode node) {
//		return node.count * (DATA_WIDTH + LINK_WIDTH) + LINK_WIDTH;
//	}
//
//	static int getSubTreeWidth(MyNode node) {
//		if (node.child.isEmpty())
//			return getNodeWidth(node);
//		int sum = 0;
//		for (int i = 0; i < node.child.size(); ++i)
//			sum += getSubTreeWidth(node.child.get(i)) + X_MARGIN;
//		return sum;
//	}
//
//	static int getHeight(MyNode node) {
//		return tree.height * LAYER_HEIGHT + 100;
//	}
//
//	void capture() {
//		BufferedImage image = new BufferedImage(getSubTreeWidth(tree.root) + 100, getHeight(tree.root) + 100,
//				BufferedImage.TYPE_INT_ARGB);
//		Graphics2D g = image.createGraphics();
//		super.paint(g);
//		g.drawString("(" + (index + 2) + ")  " + title, 20, 20);
//		defaultFont = g.getFont();
//		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//		drawNode(g, tree.root, X_MARGIN, X_MARGIN + 30);
//		images.add(image);
//		index = images.size() - 1;
//		revalidate();
//		repaint();
//
//	}
//}

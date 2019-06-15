package tools;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BTreePanel extends JPanel {
	static final int DATA_WIDTH = 21, LINK_WIDTH = 2, NODE_HEIGHT = 20, X_MARGIN = 3, LAYER_HEIGHT = 60;
	static Font narrowFont = new Font(Font.SERIF, Font.PLAIN, 12), defaultFont;
	public static MyBTree tree = new MyBTree(null);
	String title;
	ArrayList<BufferedImage> images = new ArrayList<>();
	int index = -1;

	public BTreePanel() {
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getSubTreeWidth(tree.root), 1024);
	}

	@Override
	public void paint(Graphics graphics) {
		super.paint(graphics);
		if (index >= 0)
			((Graphics2D) graphics).drawImage(images.get(index), 0, 20, null);
	}

	static void drawNode(Graphics2D g, MyNode node, int x0, int y0) {
		if (node == null)
			return;
		int thisY = y0, thisX = x0 + (getSubTreeWidth(node) - getNodeWidth(node)) / 2;

		Rectangle2D.Double rect = new Rectangle2D.Double(thisX, thisY, getNodeWidth(node), NODE_HEIGHT);
		g.setColor(Color.WHITE);
		g.fill(rect);
		g.setColor(Color.BLACK);
		g.draw(rect);
		for (int i = 0; i < node.count; ++i) {
			Rectangle2D.Double r = new Rectangle2D.Double(thisX + LINK_WIDTH + (DATA_WIDTH + LINK_WIDTH) * i, thisY,
					DATA_WIDTH, NODE_HEIGHT);
			drawCenteredString(g, String.valueOf(node.data[i]), r);
		}
		if (node.isLeaf() == false) {
			int childX = x0, childY = y0 + LAYER_HEIGHT;
			int linkY0 = thisY + NODE_HEIGHT, linkY1 = childY;
			for (int i = 0; i < node.child.size(); ++i) {
				int linkX0 = thisX + LINK_WIDTH / 2 + (DATA_WIDTH + LINK_WIDTH) * i;
				int linkX1 = childX + getSubTreeWidth(node.child.get(i)) / 2;
				g.drawLine(linkX0, linkY0, linkX1, linkY1);
				drawNode(g, node.child.get(i), childX, childY);
				childX += getSubTreeWidth(node.child.get(i)) + X_MARGIN;
			}
		}
	}

	static void drawCenteredString(Graphics2D g, String s, Rectangle2D rect) {
		g.setFont(s.length() >= 3 ? narrowFont : defaultFont);
		FontMetrics metrics = g.getFontMetrics();
		int x = (int) (rect.getX() + (rect.getWidth() - metrics.stringWidth(s)) / 2);
		int y = (int) (rect.getY() + ((rect.getHeight() - metrics.getHeight()) / 2) + metrics.getAscent());
		g.drawString(s, x, y);
	}

	static int getNodeWidth(MyNode node) {
		return node.count * (DATA_WIDTH + LINK_WIDTH) + LINK_WIDTH;
	}

	static int getSubTreeWidth(MyNode node) {
		if (node.child.isEmpty())
			return getNodeWidth(node);
		int sum = 0;
		for (int i = 0; i < node.child.size(); ++i)
			sum += getSubTreeWidth(node.child.get(i)) + X_MARGIN;
		return sum;
	}

	static int getHeight(MyNode node) {
		return tree.height * LAYER_HEIGHT + 100;
	}

	public void capture() {
		BufferedImage image = new BufferedImage(getSubTreeWidth(tree.root) + 100, getHeight(tree.root) + 100,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		super.paint(g);
//		g.drawString("(" + (index + 2) + ")  " + title, 20, 20);
		defaultFont = g.getFont();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		drawNode(g, tree.root, X_MARGIN, X_MARGIN + 30);
		images.add(image);
		index = images.size() - 1;
		revalidate();
		repaint();

	}
}

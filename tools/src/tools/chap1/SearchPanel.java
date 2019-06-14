package tools.chap1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

import tools.IndexPanel;
import tools.MainFrame;

public class SearchPanel extends JPanel {

	LineBorder lb = new LineBorder(Color.BLUE, 1);

	public SearchPanel(MainFrame mainFrame, int num, int start, boolean rz, UndirectedListGraph graph) {
		// TODO Auto-generated constructor stub
		setLayout(null);
		setBackground(Color.WHITE);

		// 처음으로 이동하는 버튼
		JButton home_img = new JButton(new ImageIcon("image/home.png"));
		home_img.setBorderPainted(false);
		home_img.setContentAreaFilled(false);
		home_img.setFocusPainted(false);
		home_img.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.changeRoom(new IndexPanel(mf));
			}
		});
		home_img.setBounds(10, 10, 45, 29);
		add(home_img);

		ImageIcon icon;
		if (num == 1) {
			// 언덕 등반 탐색 타이틀
			icon = new ImageIcon("image/hill_title.png");

		} else {
			icon = new ImageIcon("image/astar_title.png");
		}

		JLabel title_img = new JLabel(icon);
		title_img.setBounds(45, 50, 540, 86);
		add(title_img);

		JPanel solvingPanel = new JPanel();
		solvingPanel.setLayout(null);
		solvingPanel.setBackground(Color.WHITE);
		solvingPanel.setBorder(lb);
		solvingPanel.setBounds(45, 160, 540, 450);
		add(solvingPanel);

		BTreePanel treePanel = new BTreePanel();
		JScrollPane treeSP = new JScrollPane(treePanel);
		treeSP.setBounds(50, 10, 450, 370);
		treePanel.setBackground(Color.WHITE);
		treeSP.setBorder(null);
		solvingPanel.add(treeSP);

		String searchResult;
		if (num == 1)
			searchResult = graph.hillClimbing(rz, start);
		else
			searchResult = graph.aStartSearch(rz, start);

		treePanel.tree = graph.tree;
		treePanel.capture();

		JLabel resultLabel = new JLabel(searchResult);
		resultLabel.setFont(new Font("돋움", Font.BOLD, 20));
		resultLabel.setBounds(50, 365, 300, 100);
		solvingPanel.add(resultLabel);

		if (num == 1) {
			// A* 탐색 이동 버튼
			JButton aStarButton = new JButton(new ImageIcon("image/astar.png"));
			aStarButton.setBounds(80, 710, 200, 59);
			aStarButton.setBorderPainted(false);
			aStarButton.setContentAreaFilled(false);
			aStarButton.setFocusPainted(false);
			add(aStarButton);
			aStarButton.addActionListener(new ActionListener() {
				MainFrame mf = mainFrame;

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mainFrame.changeRoom(new SearchPanel(mf, 2, start, rz, graph));

				}
			});
		} else if (num == 2) {

			// 언덕등반탐색 이동 버튼
			JButton hillButton = new JButton(new ImageIcon("image/hill.png"));
			hillButton.setBounds(80, 710, 200, 59);
			hillButton.setBorderPainted(false);
			hillButton.setContentAreaFilled(false);
			hillButton.setFocusPainted(false);
			add(hillButton);
			hillButton.addActionListener(new ActionListener() {
				MainFrame mf = mainFrame;

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					mainFrame.changeRoom(new SearchPanel(mf, 1, start, rz, graph));

				}
			});
		}

		JButton resetButton = new JButton("다시 입력");
		resetButton.setBounds(350, 710, 200, 59);
		resetButton.setFocusPainted(false);
		add(resetButton);
		resetButton.addActionListener(new ActionListener() {
			MainFrame mf = mainFrame;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				mainFrame.changeRoom(new Chap1Panel(mf));

			}
		});

	}

}

@SuppressWarnings("serial")
class BTreePanel extends JPanel {
	static final int DATA_WIDTH = 21, LINK_WIDTH = 2, NODE_HEIGHT = 20, X_MARGIN = 3, LAYER_HEIGHT = 60;
	static Font narrowFont = new Font(Font.SERIF, Font.PLAIN, 12), defaultFont;
	static MyBTree tree = new MyBTree(new MyNode(1, 0, 0));
	String title;
	ArrayList<BufferedImage> images = new ArrayList<>();
	int index = -1;

	public BTreePanel() {
		tree = new MyBTree(new MyNode(1, 0, 0));
		tree.add(new MyNode(4, 5, 6));
		tree.add(new MyNode(7, 8, 9));
		tree.add(new MyNode(10, 11, 12));

		tree.print();
		tree.moveTemp(0);

		tree.add(new MyNode(7, 8, 9));
		tree.add(new MyNode(10, 11, 12));

		tree.print();

		tree.moveTemp(1);

		tree.add(new MyNode(7, 8, 9));
		tree.add(new MyNode(10, 11, 12));

		tree.print();
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

	void capture() {
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

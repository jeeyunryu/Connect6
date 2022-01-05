import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Connect6 extends JFrame implements MouseListener {

	private static int leftTopCornerX = 50; // ������ x��ǥ
	private static int leftTopCornerY = 50; // ������ y��ǥ
	private static int width = 540; // �ٵ��� ���α���
	private static int height = 540; // �ٵ��� ���α���
	private static int currentX = 0; // ���� ���콺 ��ġ x��ǥ
	private static int currentY = 0;// ���� ���콺 ��ġ y��ǥ
	private static int stoneCount; // ���� ���� (����ؼ� ������Ʈ �Ǵ� ����)
	private static int neutralStoneCount; // �߸�������(3��)
	
	private static boolean mouseClicked = false;
	private static boolean isWhiteStoneTurn = false; // ���� ������ �� �ʿ��� (isBlackStoneTurn�� ������)
	private static boolean isBlackStoneTurn = false;
	
	private static ArrayList<Boolean> stoneExistsArrayList = new ArrayList<Boolean>(); // 361��
	private static ArrayList<Boolean> isBlackStoneArrayList = new ArrayList<Boolean>(); // 361��
	private static ArrayList<Boolean> isWhiteStoneArrayList = new ArrayList<Boolean>(); // 361��
	
	private static Point[] everyCoordinateArray = new Point[361]; // �ٵ����� ��� ������ ��ǥ�� �����ص�
	private static ArrayList<Point> everyCoordinateArrayList = new ArrayList<Point>(); 
	private static ArrayList<Point> coordinateOfStoneArrayList = new ArrayList<Point>(); // ���� ���� ��ŭ(�鵹, �浹, �߸��� ��� �����)
	
	private static ArrayList<Color> colorOfStoneArrayList = new ArrayList<Color>(); // ���� ���� ��ŭ (�鵹, �浹, �߸��� ��� �����)

	private JPanel contentPane;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Connect6 frame = new Connect6();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
	}

	public void paint(Graphics g) {

		super.paint(g);
		// ������ �׸���
		for (int i = 0; i < 19; i++) {
			g.drawLine(leftTopCornerX, leftTopCornerY + 30 * i, leftTopCornerX + width, leftTopCornerY + 30 * i);
		}
		// ������ �׸���
		for (int i = 0; i < 19; i++) {

			g.drawLine(leftTopCornerX + 30 * i, leftTopCornerY, leftTopCornerX + 30 * i, leftTopCornerY + height);

		}

		// ���콺 Ŭ�� ��
		if (mouseClicked) {

			if (isBlackStoneTurn) {
				colorOfStoneArrayList.add(stoneCount++, Color.black); // �浹�� ���
			} else if (isWhiteStoneTurn) {
				colorOfStoneArrayList.add(stoneCount++, Color.white); // �鵹�ϰ��
			} else {
				colorOfStoneArrayList.add(stoneCount++, Color.red); // �߸����� ���
			} // �̶�, �浹, �鵹, �ߵ� ��� ���� ������

			// Ŭ�� �� �ٵϵ��� �� (�����Ǵ� ������ ���� �׷������� ��ġ ����)
			for (int i = 0; i < 361; i++) {

				if (currentX >= everyCoordinateArray[i].x - 15 && currentX <= everyCoordinateArray[i].x + 15
						&& currentY >= everyCoordinateArray[i].y - 15 && currentY <= everyCoordinateArray[i].y + 15 && !stoneExistsArrayList.get(i)) {

					currentX = everyCoordinateArray[i].x;
					currentY = everyCoordinateArray[i].y;
					Point newPnt = new Point(currentX, currentY);
					coordinateOfStoneArrayList.add(newPnt);
					stoneExistsArrayList.set(i, true);

					if (isBlackStoneTurn) {
						isBlackStoneArrayList.set(i, true);
					} else if (isWhiteStoneTurn) {
						isWhiteStoneArrayList.set(i, true);
					}
				}

			}
			// �ٵϵ� �׸���
			for (int i = 0; i < coordinateOfStoneArrayList.size(); i++) {
				if (colorOfStoneArrayList.get(i) == Color.black) {
					g.setColor(Color.black);
				} else if (colorOfStoneArrayList.get(i) == Color.white) {
					g.setColor(Color.white);
				} else {
					g.setColor(Color.red);
				}
				g.fillOval((int) coordinateOfStoneArrayList.get(i).getX() - 10, (int) coordinateOfStoneArrayList.get(i).getY() - 10, 20, 20);
			}
			
			mouseClicked = false;

			// ���� ���� ����

			if (stoneCount % 2 == 0) {

				if (isBlackStoneTurn) {
					System.out.print("asdf");
					isBlackStoneTurn = false;
					isWhiteStoneTurn = true;

				} else if (isWhiteStoneTurn) {
					isWhiteStoneTurn = false;
					isBlackStoneTurn = true;
				}
			}

			// �밢���� 6�� (\)
			for (int i = 0; i < 261; i++) {

				int blkcnt = 0;
				int whtcnt = 0;

				for (int j = 0; j < 7; j++) {

					if (isBlackStoneArrayList.get(i + 19 * j + j)) {
						blkcnt++;
					} else {
						break;
					}
				}

				for (int j = 0; j < 7; j++) {

					if (isWhiteStoneArrayList.get(i + 19 * j + j)) {

						whtcnt++;
						System.out.println(whtcnt + " " + (i + 19 * j));
					} else {
						break;
					}

				}

				if (blkcnt == 6) {
					if (isBlackStoneArrayList.get(i - 20)) {
						blkcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "Black stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}

				if (whtcnt == 6) {
					if (isWhiteStoneArrayList.get(i - 20)) {
						whtcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "White stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}
				}
			}

			// �밢���� 6�� (/)
			for (int i = 0; i < 270; i++) {

				int blkcnt = 0;
				int whtcnt = 0;

				for (int j = 0; j < 7; j++) {

					try {

						if (isBlackStoneArrayList.get(i + 19 * j - j)) {
							blkcnt++;
						} else {
							break;
						}

					}

					catch (IndexOutOfBoundsException e) {

						System.out.println(e);

					}

				}

				for (int j = 0; j < 7; j++) {

					try {

						if (isWhiteStoneArrayList.get(i + 19 * j - j)) {

							whtcnt++;
							System.out.println(whtcnt + " " + (i + 19 * j));
						} else {
							break;
						}

					}

					catch (IndexOutOfBoundsException e) {

						System.out.println(e);

					}

				}

				if (blkcnt == 6) {
					if (isBlackStoneArrayList.get(i - 18)) {
						blkcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "Black stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}

				if (whtcnt == 6) {
					if (isWhiteStoneArrayList.get(i - 18)) {
						whtcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "White stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}
			}

			// ������ 6��
			for (int i = 0; i < 356; i++) {

				int blkcnt = 0;
				int whtcnt = 0;

				for (int j = 0; j < 7; j++) {

					if (isBlackStoneArrayList.get(i + j)) {
						blkcnt++;
					} else {
						break;
					}
				}

				for (int j = 0; j < 7; j++) {

					if (isWhiteStoneArrayList.get(i + j)) {

						whtcnt++;
						System.out.println(whtcnt + " " + (i + 19 * j));
					} else {
						break;
					}

				}

				if (blkcnt == 6) {
					if (isBlackStoneArrayList.get(i - 1)) {
						blkcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "Black stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}

				if (whtcnt == 6) {
					if (isWhiteStoneArrayList.get(i - 1)) {
						whtcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "White stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}
			}

			// ������ 6��
			for (int i = 0; i < 266; i++) {

				int blkcnt = 0;
				int whtcnt = 0;

				for (int j = 0; j < 19; j++) {

					if (isBlackStoneArrayList.get(i + 19 * j)) {
						blkcnt++;
					} else {
						break;
					}
				}

				for (int j = 0; j < 19; j++) {

					if (isWhiteStoneArrayList.get(i + 19 * j)) {

						whtcnt++;
						System.out.println(whtcnt + " " + (i + 19 * j));
					} else {
						break;
					}

				}

				if (blkcnt == 6) {
					if (isBlackStoneArrayList.get(i - 19)) {
						blkcnt = 0;

					} else {
						JOptionPane.showMessageDialog(null, "Black stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}

				if (whtcnt == 6) {
					if (isWhiteStoneArrayList.get(i - 19)) {
						whtcnt = 0;

					} else {
						JOptionPane.showMessageDialog(null, "White stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}

//			
			}

		}

	}

	public void mouseClicked(MouseEvent e) {

		currentX = e.getX();
		currentY = e.getY();

		mouseClicked = true;
		if (neutralStoneCount < 3) {
			neutralStoneCount++;
			repaint();
		} else if (neutralStoneCount == 3) {
			isBlackStoneTurn = true;
			neutralStoneCount++;
			repaint();
		}

		else {
			repaint();
		}

	}

	public Connect6() {

		int k = 0;
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				everyCoordinateArray[k] = new Point(leftTopCornerX + i * 30, leftTopCornerY + j * 30);
				System.out.println("K = " + k + "(" + everyCoordinateArray[k].x + "," + everyCoordinateArray[k].y + ")");
				k++;
			}
		}

		for (int i = 0; i < 361; i++) {
			stoneExistsArrayList.add(i, false);
			isWhiteStoneArrayList.add(i, false);
			isBlackStoneArrayList.add(i, false);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 570, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 600, 0, 0, 0, 0, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		JButton btnNewButton = new JButton("Reset");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == btnNewButton) {

					stoneCount = 0;
					neutralStoneCount = 0;
					mouseClicked = false;
					isWhiteStoneTurn = false;
					isBlackStoneTurn = false;

					coordinateOfStoneArrayList.removeAll(coordinateOfStoneArrayList);
					colorOfStoneArrayList.removeAll(colorOfStoneArrayList);
					stoneExistsArrayList.removeAll(stoneExistsArrayList);
					isBlackStoneArrayList.removeAll(isBlackStoneArrayList);
					isWhiteStoneArrayList.removeAll(isWhiteStoneArrayList);

					for (int i = 0; i < 361; i++) {
						everyCoordinateArray[i].x = 0;
						everyCoordinateArray[i].y = 0;
					}

					int k = 0;
					for (int i = 0; i < 19; i++) {
						for (int j = 0; j < 19; j++) {
							everyCoordinateArray[k] = new Point(leftTopCornerX + i * 30, leftTopCornerY + j * 30);
							System.out.println(
									"K = " + k + "(" + everyCoordinateArray[k].x + "," + everyCoordinateArray[k].y + ")");
							k++;
						}
					}

					for (int i = 0; i < 361; i++) {
						stoneExistsArrayList.add(i, false);
						isWhiteStoneArrayList.add(i, false);
						isBlackStoneArrayList.add(i, false);
					}

					repaint();

				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 18;
		gbc_btnNewButton.gridy = 19;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		setSize(700, 700);
		setResizable(false);

		addMouseListener(this);

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

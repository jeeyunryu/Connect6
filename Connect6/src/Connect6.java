import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Connect6 extends JFrame implements MouseMotionListener, MouseListener {

	private static int leftTopCornerX = 50;
	private static int leftTopCornerY = 50;
	private static int width = 540;
	private static int height = 540;
	private static int rightBottomCornerX = leftTopCornerX + width;
	private static int rightBottomCornerY = leftTopCornerY + height;
	private static int clkX = 0;
	private static int clkY = 0;
	private static boolean isClkd = false;
	private static ArrayList<Point> everyStone = new ArrayList<Point>();
	private static ArrayList<Color> clrOfStone = new ArrayList<Color>();
	private static ArrayList<Boolean> isFull = new ArrayList<Boolean>();
	private static ArrayList<Boolean> isBlackStone = new ArrayList<Boolean>();
	private static ArrayList<Boolean> isWhiteStone = new ArrayList<Boolean>();
	private static Point[] everyCoordinate = new Point[361];
	private static boolean isWht = false;
	private static boolean isBlk = false;
	// private static boolean isNeutral = false;
	private static boolean isInProgress = false;

	private JPanel contentPane;
	private static int stoneCnt;
	private static int neutralCount;

	/**
	 * Launch the application.
	 */
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
		// 가로줄
		for (int i = 0; i < 19; i++) {
			g.drawLine(leftTopCornerX, leftTopCornerY + 30 * i, leftTopCornerX + width, leftTopCornerY + 30 * i);
		}
		// 세로줄
		for (int i = 0; i < 19; i++) {

			g.drawLine(leftTopCornerX + 30 * i, leftTopCornerY, leftTopCornerX + 30 * i, leftTopCornerY + height);

		}

		if (isClkd) {

			if (isBlk) {
				clrOfStone.add(stoneCnt, Color.black);
			} else if (isWht) {
				clrOfStone.add(stoneCnt, Color.white);
			} else {
				clrOfStone.add(stoneCnt, Color.red);
			}

			for (int i = 0; i < 361; i++) {

				if (clkX >= everyCoordinate[i].x - 15 && clkX <= everyCoordinate[i].x + 15
						&& clkY >= everyCoordinate[i].y - 15 && clkY <= everyCoordinate[i].y + 15 && !isFull.get(i)) {

					clkX = everyCoordinate[i].x;
					clkY = everyCoordinate[i].y;
					Point newPnt = new Point(clkX, clkY);
					everyStone.add(newPnt);
					isFull.set(i, true);

					if (isBlk) {
						isBlackStone.set(i, true);
					} else if (isWht) {
						isWhiteStone.set(i, true);
					}
				}

			}

			isClkd = false;
			stoneCnt++;

			if (stoneCnt % 2 == 0) {

				if (isBlk) {
					System.out.print("asdf");
					isBlk = false;
					isWht = true;

				} else if (isWht) {
					isWht = false;
					isBlk = true;
				}
			}

			// 바둑돌 그리기
			for (int i = 0; i < everyStone.size(); i++) {
				if (clrOfStone.get(i) == Color.black) {
					g.setColor(Color.black);
				} else if (clrOfStone.get(i) == Color.white) {
					g.setColor(Color.white);
				} else {
					g.setColor(Color.red);
				}
				g.fillOval((int) everyStone.get(i).getX() - 10, (int) everyStone.get(i).getY() - 10, 20, 20);
			}

			// 대각선형 6목 (\)
			for (int i = 0; i < 261; i++) {

				int blkcnt = 0;
				int whtcnt = 0;

				for (int j = 0; j < 7; j++) {

					if (isBlackStone.get(i + 19 * j + j)) {
						blkcnt++;
					} else {
						break;
					}
				}

				for (int j = 0; j < 7; j++) {

					if (isWhiteStone.get(i + 19 * j + j)) {

						whtcnt++;
						System.out.println(whtcnt + " " + (i + 19 * j));
					} else {
						break;
					}

				}

				if (blkcnt == 6) {
					if (isBlackStone.get(i - 20)) {
						blkcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "Black stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}

				if (whtcnt == 6) {
					if (isWhiteStone.get(i - 20)) {
						whtcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "White stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}
				}
			}

			// 대각선형 6목 (/)
			for (int i = 0; i < 270; i++) {

				int blkcnt = 0;
				int whtcnt = 0;

				for (int j = 0; j < 7; j++) {
					
					try {

						if (isBlackStone.get(i + 19 * j - j)) {
							blkcnt++;
						} else {
							break;
						}

				      }

				      catch(IndexOutOfBoundsException e) {

				         System.out.println(e);

				      }


					
				}

				for (int j = 0; j < 7; j++) {
					
					try {

						if (isWhiteStone.get(i + 19 * j - j)) {

							whtcnt++;
							System.out.println(whtcnt + " " + (i + 19 * j));
						} else {
							break;
						}

				      }

				      catch(IndexOutOfBoundsException e) {

				         System.out.println(e);

				      }


					

				}

				if (blkcnt == 6) {
					if (isBlackStone.get(i - 18)) {
						blkcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "Black stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}

				if (whtcnt == 6) {
					if (isWhiteStone.get(i - 18)) {
						whtcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "White stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}
			}

			// 세로형 6목
			for (int i = 0; i < 356; i++) {

				int blkcnt = 0;
				int whtcnt = 0;

				for (int j = 0; j < 7; j++) {

					if (isBlackStone.get(i + j)) {
						blkcnt++;
					} else {
						break;
					}
				}

				for (int j = 0; j < 7; j++) {

					if (isWhiteStone.get(i + j)) {

						whtcnt++;
						System.out.println(whtcnt + " " + (i + 19 * j));
					} else {
						break;
					}

				}

				if (blkcnt == 6) {
					if (isBlackStone.get(i - 1)) {
						blkcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "Black stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}

				if (whtcnt == 6) {
					if (isWhiteStone.get(i - 1)) {
						whtcnt = 0;
					} else {
						JOptionPane.showMessageDialog(null, "White stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}
			}

			// 가로형 6목
			for (int i = 0; i < 266; i++) {

				int blkcnt = 0;
				int whtcnt = 0;

				for (int j = 0; j < 19; j++) {

					if (isBlackStone.get(i + 19 * j)) {
						blkcnt++;
					} else {
						break;
					}
				}

				for (int j = 0; j < 19; j++) {

					if (isWhiteStone.get(i + 19 * j)) {

						whtcnt++;
						System.out.println(whtcnt + " " + (i + 19 * j));
					} else {
						break;
					}

				}

				if (blkcnt == 6) {
					if (isBlackStone.get(i - 19)) {
						blkcnt = 0;

					} else {
						JOptionPane.showMessageDialog(null, "Black stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}

				if (whtcnt == 6) {
					if (isWhiteStone.get(i - 19)) {
						whtcnt = 0;

					} else {
						JOptionPane.showMessageDialog(null, "White stone wins!", "Game Over",
								JOptionPane.PLAIN_MESSAGE);
						break;
					}

				}

//			if(isFull.get(i) && isBlackStone.get(i)) {
//				if ((isFull.get(i+19) && isBlackStone.get(i+19))) {
//					if ((isFull.get(i+38) && isBlackStone.get(i+38))) {
//						if ((isFull.get(i+57) && isBlackStone.get(i+57))) {
//							if ((isFull.get(i+76) && isBlackStone.get(i+76)) ) {
//								if ((isFull.get(i+95) && isBlackStone.get(i+95))) {
//									JOptionPane.showMessageDialog(null, "Black stone wins!", "Game Over", JOptionPane.PLAIN_MESSAGE);
//									break;
//								}
//							}
//						}
//					}
//					
//				}
//			}
//			
//			if(isFull.get(i) && isWhiteStone.get(i)) {
//				if ((isFull.get(i+19) && isWhiteStone.get(i+19))) {
//					if ((isFull.get(i+38) && isWhiteStone.get(i+38))) {
//						if ((isFull.get(i+57) && isWhiteStone.get(i+57))) {
//							if ((isFull.get(i+76) && isWhiteStone.get(i+76)) ) {
//								if ((isFull.get(i+95) && isWhiteStone.get(i+95))) {
//									JOptionPane.showMessageDialog(null, "White stone wins!", "Game Over", JOptionPane.PLAIN_MESSAGE);
//									break;
//								}
//							}
//						}
//					}
//					
//				}
//			}

			}

		}

	}

	public void mouseClicked(MouseEvent e) {

		clkX = e.getX();
		clkY = e.getY();

		isClkd = true;
		if (neutralCount < 3) {
			neutralCount++;
			repaint();
		} else if (neutralCount == 3) {
			isBlk = true;
			neutralCount++;
			repaint();
		}

		else {
			repaint();
		}

	}

	public void mouseReleased(MouseEvent e) {

	}

	/**
	 * Create the frame.
	 */
	public Connect6() {

		int k = 0;
		for (int i = 0; i < 19; i++) {
			for (int j = 0; j < 19; j++) {
				everyCoordinate[k] = new Point(leftTopCornerX + i * 30, leftTopCornerY + j * 30);
				System.out.println("K = " + k + "(" + everyCoordinate[k].x + "," + everyCoordinate[k].y + ")");
				k++;
			}
		}

		for (int i = 0; i < 361; i++) {
			isFull.add(i, false);
			isWhiteStone.add(i, false);
			isBlackStone.add(i, false);
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

					stoneCnt = 0;
					neutralCount = 0;
					isClkd = false;
					isWht = false;
					isBlk = false;

					everyStone.removeAll(everyStone);
					clrOfStone.removeAll(clrOfStone);
					isFull.removeAll(isFull);
					isBlackStone.removeAll(isBlackStone);
					isWhiteStone.removeAll(isWhiteStone);

					for (int i = 0; i < 361; i++) {
						everyCoordinate[i].x = 0;
						everyCoordinate[i].y = 0;
					}

					int k = 0;
					for (int i = 0; i < 19; i++) {
						for (int j = 0; j < 19; j++) {
							everyCoordinate[k] = new Point(leftTopCornerX + i * 30, leftTopCornerY + j * 30);
							System.out.println(
									"K = " + k + "(" + everyCoordinate[k].x + "," + everyCoordinate[k].y + ")");
							k++;
						}
					}

					for (int i = 0; i < 361; i++) {
						isFull.add(i, false);
						isWhiteStone.add(i, false);
						isBlackStone.add(i, false);
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
		addMouseMotionListener(this);
		addMouseListener(this);

	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

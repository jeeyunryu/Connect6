import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JFrame implements MouseMotionListener, MouseListener{
	
	private static JPanel gridPnl, infoPnl, btnPnl, lblPnl, otherPnl, rghtPnl;
	private static JLabel warnLbl, plyrTurnLbl, ply1Lbl, ply2Lbl, blkStone, whtStone, fstPlyrLbl, crsrLbl;
	private static JRadioButton fstPly1Btn, fstPly2Btn;
	private static JButton plyGameBtn;
	private static ButtonGroup btnGrp;
	private static BufferedImage blkStoneIcon;
	private static BufferedImage whtStoneIcon;

	ArrayList<Point> pntArry = new ArrayList<Point>();
	private static int clkX = 0;
	private static int clkY = 0;
	private static boolean isClkd = false;
	
	
	public Main() {
		
		addMouseMotionListener(this);
		addMouseListener(this);
		
		setTitle("Connect6");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 600);
		setResizable(false);
		setLocationRelativeTo(null);
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new GridLayout(1, 2));
		
		gridPnl = new JPanel();
		
		contentPane.add(gridPnl);


		rghtPnl = new JPanel();
		rghtPnl.setLayout(new GridLayout(2, 1));
		otherPnl = new JPanel();
		otherPnl.setLayout(new GridLayout(3, 1));
		infoPnl = new JPanel();
		infoPnl.setLayout(new GridLayout(2, 3));

		fstPlyrLbl = new JLabel("First-player");
		
		blkStone = new JLabel("бс");
		whtStone = new JLabel("бр");
		ply1Lbl = new JLabel("Player 1");
		ply2Lbl = new JLabel("Player 2");
		fstPly1Btn = new JRadioButton();
		fstPly2Btn = new JRadioButton();
		fstPly1Btn.setSelected(true);
		btnGrp = new ButtonGroup();
		btnGrp.add(fstPly1Btn);
		btnGrp.add(fstPly2Btn);
		
		infoPnl.add(blkStone);
		infoPnl.add(ply1Lbl);
		infoPnl.add(fstPly1Btn);
		infoPnl.add(whtStone);
		infoPnl.add(ply2Lbl);
		infoPnl.add(fstPly2Btn);
		
		plyGameBtn = new JButton("Click to play!");
	
		otherPnl.add(fstPlyrLbl);
		otherPnl.add(infoPnl);
		otherPnl.add(plyGameBtn);
		
		lblPnl = new JPanel();
		lblPnl.setLayout(new GridLayout(3, 1));
		
		warnLbl = new JLabel("Please place obstacles in the grid to begin (/3)");
		plyrTurnLbl = new JLabel("It's Player 1's turn!");
		crsrLbl = new JLabel("Hi~");
	
		lblPnl.add(plyrTurnLbl);
		lblPnl.add(warnLbl);
		lblPnl.add(crsrLbl);
		
		rghtPnl.add(otherPnl);
		rghtPnl.add(lblPnl);
	
		contentPane.add(rghtPnl);
		setVisible(true);
	
	}
	
	public void paint(Graphics g) {
		
		super.paint(g);
		
		g.drawRect(0, 0, 380, 380);
		
		for (int i = 0; i < 18; i++) {
			
			g.drawLine(20*(i+1),0, 20*(i+1), 380);
			
		}
		
		for (int i = 0; i < 18; i++) {
			
			g.drawLine(0, 20*(i+1), 380, 20*(i+1));
			
		}
		
	
		
		if (isClkd) {
			System.out.println("ASfas");
			g.fillOval(clkX-10, clkY-10, 20, 20);
			Point newPnt = new Point(clkX, clkY);
			pntArry.add(newPnt);
			isClkd = false;
			
		}
		
		for (int i = 0; i < pntArry.size(); i++) {
			g.fillOval((int)pntArry.get(i).getX()-10, (int)pntArry.get(i).getY()-10, 20, 20);
		}
	
	}
	
	public void mouseMoved(MouseEvent e) {
	
		int curX = e.getX();
		int curY = e.getY();
		
		crsrLbl.setText("(" + curX + ", "+ curY + ")");
	
	}
	
	public void mouseClicked(MouseEvent e) {
		
		clkX = e.getX();
		clkY = e.getY();
		isClkd = true;
		System.out.println(isClkd);
		
		repaint();
		
	}
	
	public static void main(String[] args) {
		new Main();
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}

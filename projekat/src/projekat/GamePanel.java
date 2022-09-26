package projekat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private MyMouseMotionListener theMouseListener;
	private int mousex;
	Ball theBall;
	Paddle thePaddle;
	Map theMap;
	HUD theHud;
	
	public GamePanel() {
		this.init();
	}
	
	public void init() {//inicijalizuje
		mousex=0;
		theBall=new Ball();
		thePaddle=new Paddle();
		theMap=new Map(6,10);
		theHud=new HUD();
		theMouseListener=new MyMouseMotionListener();
		addMouseMotionListener(theMouseListener);
		running=true;
		image=new BufferedImage(BBmain.WIDTH, BBmain.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g=(Graphics2D)image.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
	}
	public void run() {
		while(running) {
			update();
			
			draw();
			
			repaint();
			try{
				Thread.sleep(10);
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
	public void checkColisions() {
		Rectangle ballRect=theBall.getRect();
		Rectangle paddleRect=thePaddle.getRect();
		if(ballRect.intersects(paddleRect)) {
			theBall.setDY(-theBall.getDY());
			if(theBall.getX()<mousex+thePaddle.getWidth()/4) {
				theBall.setDX(theBall.getDX()-.5);
			}
			if(theBall.getX()<mousex+thePaddle.getWidth()&&theBall.getX() >mousex+thePaddle.getWidth()/4) {
				theBall.setDX(theBall.getDX()+.5);
			}
		}
			A:	for(int row=0;row<theMap.getMapArray().length;row++) {
					for(int col=0;col<theMap.getMapArray()[0].length;col++) {
						if(theMap.getMapArray()[row][col]>0) {
							int brickx=col*theMap.getBrickWitdh()+theMap.HOR_PAD;
							int bricky=row*theMap.getBrickHight()+theMap.VERT_PAD;
							int brickWidth=theMap.getBrickWitdh();
							int brickHeight=theMap.getBrickHight();
							Rectangle brickRect= new Rectangle(brickx,bricky,brickWidth,brickHeight);
					
							if(ballRect.intersects(brickRect)) {
								theMap.hitBrick(row, col);
								theBall.setDY(-theBall.getDY());
								g.setFont(new Font("Courier New",Font.PLAIN,20));
								theHud.addScore(1);
								break A;
					}
						}
						
			}
		}
	}
	public void update() {
		checkColisions();
		theBall.update();
	}
	public void drawWin() {
		g.setColor(Color.blue);
		g.setFont(new Font("Courier New",Font.PLAIN,30));
		g.drawString("winner", 200, 200);
		
	}
	public void drawGameOver() {
		g.setColor(Color.blue);
		g.setFont(new Font("Courier New",Font.PLAIN,30));
		g.drawString("gameover", 220, 220);
	}
	public void draw() {
		g.setColor(Color.white);
		g.fillRect(0, 0, BBmain.WIDTH, BBmain.HEIGHT);
		theBall.draw(g);
		thePaddle.draw(g);
		theMap.Draw(g);
		theHud.draw(g);
		if(theMap.isThereAWin()) {
			drawWin();
			running=false;
		}
		if(theBall.gameOver()) {
			drawGameOver();
			running=false;
		}
	}
	public void paintComponent(Graphics g) {
		Graphics2D g2=(Graphics2D)g;
		
		g2.drawImage(image, 0, 0, BBmain.WIDTH, BBmain.HEIGHT,null);
		
		g2.dispose();
	}
	private class MyMouseMotionListener implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			mousex=e.getX();
			thePaddle.mouseMoved(e.getX());
			
		}
		
	}
	}


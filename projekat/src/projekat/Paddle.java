package projekat;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Paddle {
	private double x;
	private Rectangle paddleRect;
	private int width,height;
	public final int YPOS=BBmain.HEIGHT-100;;
	public Paddle() {
		width=100;
		height=20;
		x=BBmain.WIDTH/2-width/2;
		
	}
	public void update() {
		
	}
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect((int)x, YPOS, width, height);
	}
	public void mouseMoved(int mouseXPos) {
		x=mouseXPos;
		if(x>BBmain.WIDTH-width) {
			x=BBmain.WIDTH-width;		
		}
	}
	public Rectangle getRect() {
		return new Rectangle((int)x,YPOS,width,height);
	}
	public int getWidth() {
		return width;
	}
}

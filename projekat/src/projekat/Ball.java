package projekat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.*;

public class Ball {
	private double x,y,dx,dy;
	private int ballSize=30;
	
	public Ball(){
		x=200;
		y=200;
		dx=1;
		dy=3;
	}
	public void update() {
		setPosition();
	}
	public void setPosition() {
		x+=dx;
		y+=dy;
		if(x<0)
			dx=-dx;
		if(y<0)
			dy=-dy;
		if(x>BBmain.WIDTH-ballSize)
			dx=-dx;
		if(y>BBmain.HEIGHT-ballSize)
			dy=-dy;
	}
	public void draw(Graphics2D g) {
		g.setColor(Color.DARK_GRAY);
		g.setStroke(new BasicStroke(4));
		g.drawOval((int)x,(int)y, ballSize, ballSize);
	}
	public Rectangle getRect() {
		return new Rectangle((int)x,(int)y,ballSize,ballSize);
	}
	public void setDY(double theDY) {
		dy=theDY;
	}
	public double getDY() {
		return dy;
	}
	public void setDX(double theDx) {
		dx=theDx;
	}
	public double getDX() {
		return dx;
	}
	public double getX() {
		return x;
	}
	public boolean gameOver() {
		boolean gameover=false;
		if(y>BBmain.HEIGHT-ballSize*2)
			gameover=true;
		return gameover;
	}
}

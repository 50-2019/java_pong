package projekat;

import java.awt.Color;
import java.awt.Graphics2D;

public class HUD {
	private int score;
	
	public HUD(){
		init();
	}
	public void init() {
		score=0;
	}
	public void draw(Graphics2D g) {
		g.setColor(Color.red);
		g.drawString("Score: "+score, 20, 20);
	}
	public int getScore() {
		return score;	
	}
	public void addScore(int scoreToAdd) {
		score+=scoreToAdd;
	}
}

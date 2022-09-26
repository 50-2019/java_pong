package projekat;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Map {
	private int  [][]theMap;
	private int brickHeight,brickWidth;
	
	public final int  HOR_PAD = 80,VERT_PAD=50;
	
	public Map(int row,int col) {
		initMap(row, col);
		brickWidth=(BBmain.WIDTH-2*HOR_PAD)/col;
		brickHeight=(BBmain.HEIGHT/2-2*VERT_PAD)/row;
	}
	public void initMap(int row,int col) {
		theMap=new int[row][col];
			for(int i=0;i<theMap.length;i++) {
				for(int j=0;j<theMap[0].length;j++) {
					int r=(int)(Math.random()*4+1);
					theMap[i][j]=r;
				}
			}
	}
	public boolean isThereAWin() {
		boolean thereIsAWin=false;
		int bricksRemaining=0;
		for(int row=0;row<theMap.length;row++) {
			for(int col=0;col<theMap[0].length;col++) {
				bricksRemaining+=theMap[row][col];
			}
		}
		if(bricksRemaining==0)
			thereIsAWin=true;
		return thereIsAWin;
	}
	public void Draw(Graphics2D g) {
		
		for(int row=0;row<theMap.length;row++) {
			for(int col=0;col<theMap[0].length;col++) {
				if(theMap[row][col]>0) {
				if(theMap[row][col]==1)
					g.setColor(Color.LIGHT_GRAY);
				if(theMap[row][col]==2)
					g.setColor(Color.gray);
				if(theMap[row][col]==3)
					g.setColor(Color.black);
				if(theMap[row][col]==4)
					g.setColor(Color.DARK_GRAY);
				g.fillRect(col*brickWidth+HOR_PAD, row*brickHeight+VERT_PAD, brickWidth, brickHeight);
				g.setStroke(new BasicStroke(2));
				g.setColor(Color.gray);
				g.drawRect(col*brickWidth+HOR_PAD, row*brickHeight+VERT_PAD, brickWidth, brickHeight);
				}
			}
		}
	}
	public int[][] getMapArray(){
		return theMap;
	}
	public void setBrick(int row,int col,int value) {
		theMap[row][col]=value;
	}
	public int getBrickWitdh() {
		return brickWidth;
	}
	public int getBrickHight() {
		return brickHeight;
	}
	public void hitBrick(int row,int col) {
		theMap[row][col]-=1;
		if(theMap[row][col]<0) {
			theMap[row][col]=0;
		}
	}
}

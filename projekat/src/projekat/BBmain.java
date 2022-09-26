package projekat;

import javax.swing.JFrame;

public class BBmain {
	public static final  int WIDTH=650,HEIGHT=480;
	
	public static void main(String[] args) {
		JFrame theFrame = new JFrame("Brick Breaker");
		GamePanel thePanel=new GamePanel();
		Thread theThread =new Thread(thePanel);
		theFrame.setLocationRelativeTo(null);//postavlja prozor na sredinu ekrana ali ne radi nesto
		theFrame.setResizable(false);
		theFrame.setSize(WIDTH, HEIGHT);
		theFrame.add(thePanel);
		theThread.start();
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.setVisible(true);
		
	}
}

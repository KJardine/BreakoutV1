import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.*;
import java.awt.image.*;
import javax.imageio.*;
import java.awt.Rectangle.*;

public class BreakoutPanel extends JPanel{
	//Properties
	int intX;
	int intBallX = 395;
	int intBallY = 290;
	int intBallDefX = 0;
	int intBallDefY = 0;
	int intLives = 3;
	String strLives = "";
	int intServe = 0;
	
	int intLooper = 0; 
	
	//image as bricks
	BufferedImage brick1;
	BufferedImage brick2;
	BufferedImage brick3;
	
	//loss, win, all lives left images
	BufferedImage lose;
	BufferedImage win;
	BufferedImage nicewin;
	
	//ball
	Rectangle ball = new Rectangle(intBallX,intBallY,10,10);
	
	//the bricks 3 sets of 10
	int[][]bricks = new int[3][10];
	int intBricksRemaining = 30;
	
	Font myFont = new Font("Serif", Font.BOLD, 20);
	
	//Methods
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Background
		strLives = String.valueOf(intLives);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 600);
		//P1
		g.setColor(Color.WHITE);
		g.fillRect(intX, 500, 100, 10);
		//Ball
		g.fillRect(intBallX, intBallY, 10, 10);
		ball.setRect(intBallX, intBallY,1,1);
		//lives
		g.setFont(myFont);
		g.drawString("Lives: ", 10, 20);
		g.drawString(strLives, 75, 20);
		//brick layer 1
		for(int count=0;count<1;count++) {
			for(int count2=0;count2<10;count2++) {
				//don't show deleted bricks
				if(bricks[count][count2]>0) {
					g.drawImage(brick1, count2*80, count*50 + 50, null);
				}
			}
		}
		//brick layer 2
		for(int count=1;count<2;count++) {
			for(int count2=0;count2<10;count2++) {
				if(bricks[count][count2]>0) {
					g.drawImage(brick2, count2*80, count*50 + 50, null);
				}
			}
		}
		//brick layer 3
		for(int count=2;count<3;count++) {
			for(int count2=0;count2<10;count2++) {
				if(bricks[count][count2]>0) {
					g.drawImage(brick3, count2*80, count*50 + 50, null);
				}
			}
		}
		
		
		//ball movement 
		intBallX = intBallX + intBallDefX;
		intBallY = intBallY + intBallDefY;
		if(intBallY > 590){
			intBallDefY = -3;
			intBallX = 395;
			intBallY = 290;
			intBallDefX = 0;
			intBallDefY = 0;
			intLives = intLives - 1;
			System.out.println(intLives);
			intServe = 0;
		}
		if(intBallY < 0){
			intBallDefY = 3;
		}
		if(intBallX > 790){
			intBallDefX = -3;
		}
		if(intBallX < 0){
			intBallDefX = 3;
		}
		
		//paddle collision detection
		if(intBallY >= 490 && intBallY <= 492 && intBallX > intX -10 && intBallX < intX +100){
			intBallDefY = -3;
		}
		
		//brick collision detection
		for(int count=0;count<3;count++) {
			for(int count2=0;count2<10;count2++) {
				//don't take into account deleted bricks
				if(bricks[count][count2]>0) {
					Rectangle FalseBrick = new Rectangle(count2*80, count*50 + 50, 80, 50);
					//does the ball intersect with the "false brick" in the same location as the real one?
					if(ball.intersects(FalseBrick)) {
						bricks[count][count2]=0;
						intBricksRemaining--;
						System.out.println(intBricksRemaining);
						//means it will hit the top/bottom of the brick, bounce it opposite since we don't know direction.
						if(intBallY >= FalseBrick.y && intBallY <= FalseBrick.y + 50 && intBallX >= FalseBrick.x && intBallX <= FalseBrick.x + 80){
							intBallDefY = -intBallDefY;
						//means it will hit the side, bounce it opposite since we don't know direction. 
						}else{
							intBallDefX = -intBallDefX;
						}			
					}
				}
			}
		}
		
		if(intBricksRemaining == 0 && intLives == 3){
			g.drawImage(nicewin, 0, 0, null);
		}else if(intBricksRemaining == 0 && intLives < 3){
			g.drawImage(win, 0, 0, null);
		}else if(intLives == 0){
			g.drawImage(lose, 0, 0, null);
		}
		
	}

	
	//Constructor
	public BreakoutPanel(){
		super();
		try{
			brick1 = ImageIO.read(new File("brick1.png"));
		}catch(IOException e){
			System.out.println("Invalid Picture");
		}
		try{
			brick2 = ImageIO.read(new File("brick2.png"));
		}catch(IOException e){
			System.out.println("Invalid Picture");
		}
		try{
			brick3 = ImageIO.read(new File("brick3.png"));
		}catch(IOException e){
			System.out.println("Invalid Picture");
		}
		try{
			win = ImageIO.read(new File("win.jpg"));
		}catch(IOException e){
			System.out.println("Invalid Picture");
		}
		try{
			lose = ImageIO.read(new File("lose.jpg"));
		}catch(IOException e){
			System.out.println("Invalid Picture");
		}
		try{
			nicewin = ImageIO.read(new File("ez.jpg"));
		}catch(IOException e){
			System.out.println("Invalid Picture");
		}
		//helps when needing to delete the bricks
		for(int count=0;count<3;count++) {
			for(int count2=0;count2<10;count2++) {
				bricks[count][count2]=1;
			}
		}
	}
}

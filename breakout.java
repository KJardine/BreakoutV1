import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class breakout implements ActionListener, MouseListener, MouseMotionListener{
	//Properties
	JFrame theFrame = new JFrame("Breakout");
	BreakoutPanel thePanel = new BreakoutPanel();
	Timer theTimer = new Timer(1000/60, this);
	
	
	//Methods
	public void mouseMoved(MouseEvent evt){
		thePanel.intX = evt.getX() - 50;
			
	}
	public void mouseDragged(MouseEvent evt){
	}
	
	public void mouseExited(MouseEvent evt){
	}
	
	public void mouseEntered(MouseEvent evt){
	}
	
	public void mouseReleased(MouseEvent evt){
	}
	public void mousePressed(MouseEvent evt){
		//Serve with right mouse press
		if(evt.getButton() == MouseEvent.BUTTON3 && thePanel.intServe == 0){
			thePanel.intBallDefX = 3;
			thePanel.intBallDefY = 3;
			thePanel.intServe = 1;
		} 
	}
	public void mouseClicked(MouseEvent evt){
	}
	
	
	
	public void actionPerformed(ActionEvent evt){
		if(evt.getSource() == theTimer){
			thePanel.repaint();
		}
	}
	
	
	//Constructor
	public breakout(){
		thePanel.setPreferredSize(new Dimension(800, 600));
		thePanel.addMouseMotionListener(this);
		thePanel.addMouseListener(this);
		theFrame.setContentPane(thePanel);
		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theFrame.pack();
		theFrame.setVisible(true);
		theFrame.setResizable(false);
		theTimer.start();
		
		
		
	}
	
	//main method
	public static void main(String[] args){
		new breakout(); 
	}
}

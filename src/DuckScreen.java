import java.awt.Color;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

//JPANEL
//duckdriver and duckscreen names should be switched around to make the most sense
public class DuckScreen extends JPanel implements Runnable, MouseListener{

	private ArrayList<Quacker> ducks = new ArrayList<Quacker>();
	private int x,y;
	private int clickcount =0; 
	private int DuckHits =0;
	private boolean running;
	private int maxshots =0;
	private int remaining =0; 
	private int points = 0;

	
	//default constructor
	public DuckScreen(int max){
		//mouse listener=click detection
		addMouseListener(this);
		running = true;
		maxshots = max;
		Thread t = new Thread(this);
		t.start();
		remaining = maxshots;
		//four quackers, two are subclasses of quacker
		Quacker D = new BonusDuck("green", 50, 100);
		//adds to arraylist of quackers
		addDuck(D);
		Quacker B = new AngledDuck("blue", 100, 100);
		addDuck(B);
		Quacker C = new Quacker("red", -100, 50);
		addDuck(C);
		Quacker A = new Quacker("blue", 75, 75);
		addDuck(A);
	
	}
	
//Paints background and ducks
	public void paint(Graphics g){
		
		g.clearRect(0,0,getWidth(),getHeight());
		/*
		Image i = new ImageIcon("background.png").getImage();
		g.drawImage(i, 0, 0, null);
		*/
		Image h = new ImageIcon("background_front.png").getImage();
		g.drawImage(h, 0, 0, null);
	
		//g.drawLine(x,y,x+400,y+400);
		//Put the image in the program folder, then get the image.
		//Can try substringing file names to make the picswp variable work?
		// str.substring(0,picswp)+picswp+str.substring(picswp, str.length())
		
		//Checks if ducks are hit, then animates them accordingly.
		for(Quacker q:ducks){
		//the enhanced for loops moves through the duck pngs 
			if(!q.getIfHit()){
		Image j = new ImageIcon(q.getColor()+"_duck_"+q.getPic()/*number duck has progressed to*/+".png").getImage();
		g.drawImage(j, q.getX(), q.getY(), null);
		q.draw(g);
			}
			else if(q.getIfHit()){
				Image j = new ImageIcon(q.getColor()+"_duck_dead.png").getImage();
				g.drawImage(j, q.getX(), q.getY(), null);
				q.draw(g);
			}
		}
		//Output to user.
		//g.drawString("Shots: "+maxshots,685,543);
		//g.drawString("Shots fired: "+clickcount,685,583);
		g.drawString("Ducks hit: "+DuckHits,685,573);
		
		//keeps track of remaining shots- string
		g.drawString("Shots remaining: "+remaining,670,553);

	}
	
	public void run(){ //main run method
		
		//While the program is running, if the duck is no longer onscreen, it is reset and randomized.
		while(running){
		for(int i=0;i<ducks.size();i++){
			Quacker quacker = (Quacker)ducks.get(i);
			if(quacker.getX()>800||quacker.getY()>600){
				randomize(quacker);
				quacker.changeX(-100);
				quacker.resetSpeedAngle();
			}
			if(!quacker.getIfHit()){
			quacker.changeX(quacker.getX()+quacker.getSpeed());
			quacker.changeY(quacker.getY()+quacker.getAngle());
			}
			else if(quacker.getIfHit()){
				quacker.changeY(quacker.getY()+80);
		
			}
			//q.changeX(q.getX()+q.getSpeed());
		//y+=D.getAngle();
	
		repaint();
		try{
		Thread.sleep(50);
		
	} catch (Exception ex){
		ex.printStackTrace();
	}
	
	System.out.println("Duck's Y:"+quacker.getY()+"Duck's X:"+quacker.getX());
	System.out.println(running);
	
		}
	}
}
	//method which adds ducks to the array.
	public void addDuck(Quacker q){
		ducks.add(q);
	}

	
	//Randomizes the ducks, sets them as alive again.
	//makes it look like there are a ton of different ducks even when there are only a set number. just shifting position
	public void randomize(Quacker d){
		String rand = "";
		
		int rannum = (int)(Math.random()*3)+1;
		
		if(rannum==1){
			rand = "blue";
		}
		else if(rannum==2){
			rand = "red";
		}
		else if(rannum==3){
			rand = "green";
		}
		d.resetSpeedAngle();
		d.changeColor(rand);
		d.backToLife();
		d.changeY((int)(Math.random()*500)+1);
		
	}
	

	//              *Note: mouse clicks will not register if the cursor is moved while clicking.
	public void mouseClicked(MouseEvent arg0) {

	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
	//checks if mouse has been clicked, if so, checks if all the shots allotted have been spent. 
	//if they are, it displays a dialog box to stop or restart game. 
	public void mousePressed(MouseEvent e) {
		int xcoord = e.getX();
		int ycoord = e.getY();
		
		clickcount++;
		remaining--;
		if(clickcount==maxshots){
			running = false;
			JFrame frame = new JFrame("FrameDemo");
			
			int n = JOptionPane.showConfirmDialog(
		            frame, "You earned "+points+" points. \nYour accuracy was "+(((double)DuckHits/(double)maxshots)*100)+"%.\nDo you want to play again?",
		            "Game over!",
		            JOptionPane.YES_NO_OPTION);
			
			if(n==JOptionPane.YES_OPTION){
				running = true;
				clickcount = 0;
				Thread t = new Thread(this);
				t.start();
				remaining = maxshots;
				DuckHits =0;
				points =0;
				for(int i=0;i<ducks.size();i++){
					Quacker quacker = (Quacker)ducks.get(i);
					randomize(quacker);
					quacker.changeX(-100);
				}
				
				
			}
			else if(n==JOptionPane.NO_OPTION){
				System.exit(0);
				//DuckDriver.dispose(); 
			}
		}
		
		
		for(Quacker q:ducks){
			//Note: X1(front)&& X2(behind) && Y1(above) & & Y2(below)
			if(((q.getX()+75>xcoord)&&(q.getX()-20<xcoord))&&((q.getY()+60>ycoord)&&(q.getY()-5<ycoord))&&!q.getIfHit()){
				q.die();
				DuckHits++;
				points+= q.getVal();
			}
		}
	}

	public void mouseReleased(MouseEvent e) {
	}

}



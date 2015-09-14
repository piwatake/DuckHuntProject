import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
//duckdriver and duckscreen names should be switched around to make the most sense
public class DuckDriver extends JFrame {

	//default constructor. replaces cursor image.
    public DuckDriver(){
        super("Shoot down as many ducks as possible!");
       
        //cursor 
 	   Image cursorImage = Toolkit.getDefaultToolkit().createImage("crosshairs[1] copy.png");
       Point hotspot = new Point(10, 10);
        Cursor crosshair = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, hotspot, "duck");
        setCursor(crosshair);
        
        
        /*
        Cursor crosshair = new Cursor(Cursor.CROSSHAIR_CURSOR);
        setCursor(crosshair);
        */
        
        //screen size
        setSize(800,620);
        //user shots
        setContentPane(new DuckScreen(25));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }

    //runs the class, makes it visible
	public static void main(String [] args){
	
		DuckDriver dd = new DuckDriver();
		dd.setVisible(true);

	}
	

}

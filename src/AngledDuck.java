
public class AngledDuck extends Quacker implements Runnable {
	private int angle;
	private int pointVal;
	
	// Make a sideways duck!
	public AngledDuck(String color, int xco, int yco) {
		super(color, xco, yco);
		angle = (int)(Math.random()*90)+20;
		pointVal = 5;
		// TODO Auto-generated constructor stub
		
	}
	
	//overrides getangle function to actually return an angle
	public int getAngle(){
		return (int)angle;
	}
	
	//overrides getval function for the new point value
	public int getVal(){
		return pointVal;
	}
	
}


public class BonusDuck extends Quacker implements Runnable {

	private int pointVal;
	private int speed;
	
	
	

	//Makes a duck that moves faster and is worth more points.
	public BonusDuck(String color, int xco, int yco) {
		super(color, xco, yco);
		pointVal = 3;
		speed = 100;
	
	}

	//overrides getval function to return the new point value.
	public int getVal(){
		return pointVal;
	}
	
	//overrides the angle and speed reset to always reset the speed to 100.
	public void resetSpeedAngle(){
		speed = 100;

	}
	
}

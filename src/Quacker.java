import javax.swing.*;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;


//Random Generator = new Random();

public class Quacker implements Runnable{
private Boolean isHit;
private Boolean isVisible;
private int speed;
private int angle;
private Image image;
private String clr;
private int x,y;
private int picnum;
private int pointVal;

//default constructor
public Quacker(String color, int xco, int yco){
	picnum = 0;
	clr = color;
	x = xco;
	y = yco;
	isHit = false;
	speed = (int)(Math.random()*120)+40;
	angle = 0;
	isVisible = true;
	pointVal = 1;
	
	//setImage(imagefile);
	
}

public void run(){
	//addMouseListener(this);
}

//determines whether to draw duck or not.
public void draw(Graphics g){
    if (!getIfHit()) {
        g.drawImage(image,x,y,null);
    }
    
}

//changes the x value of the duck.
public void changeX(int nwx){
	this.x=nwx;
}

//changes the y value of the duck
public void changeY(int nwy){
	this.y=nwy;
}

//changes the color of the duck.
public void changeColor(String color){
	this.clr = color;
}

//retrieves x value of duck
public int getX(){
	return x;
}

//retrieves y value of duck
public int getY(){
	return y;
}

//gets the "status" of duck - "dead" or "alive"
public boolean getIfHit(){
	return isHit;
}

//returns speed of duck
public int getSpeed(){
	return (int)speed;
}

//returns angle of duck
public int getAngle(){
	return (int)angle;
}

//makes the duck "dead"
public void die(){
	isHit = true;
}


//retrieves color of duck
public String getColor(){
	return clr;
}

//Sets the duck as "alive"
public void backToLife(){
	isHit = false;
}

//randomizes the speed and angle
public void resetSpeedAngle(){
	speed = (int)(Math.random()*/*120*/60)+40;
	//angle = (int)(Math.random()*90)+1;
}

//changes the image of the duck.
public void setImage(String imgFile){
	   if (imgFile == null) {
           return;
       }
image = new ImageIcon(imgFile).getImage();
}

//helps animate duck.
//called by duckscreen, increments each time called to animate.
public int getPic(){
	picnum++;
	if(picnum>10){
		picnum=1;
	}
	return picnum;
	
}

//retrives point value of duck
public int getVal(){
	return pointVal;
}

}

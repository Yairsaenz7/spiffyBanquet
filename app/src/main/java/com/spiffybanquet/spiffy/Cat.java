package com.spiffybanquet.spiffy;


import java.util.ArrayList;

import android.graphics.Rect;

public class Cat {

	// Constants are Here
	final int JUMPSPEED = -21;
	final int MOVESPEED = 5;

	private int centerX = 301;
	private int centerY = 177;
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean readyToFire = true;
	private boolean hasCollided = false;

	private int speedX = 1;
	private int speedY = 0;
	public static Rect rect = new Rect(0, 0, 0, 0);
	public static Rect rect2 = new Rect(0, 0, 0, 0);
	public static Rect rect3 = new Rect(0, 0, 0, 0);
	public static Rect rect4 = new Rect(0, 0, 0, 0);
	public static Rect yellowRed = new Rect(0, 0, 0, 0);
	
	public static Rect footleft = new Rect(0,0,0,0);
	public static Rect footright = new Rect(0,0,0,0);
	
	
	private Background bg1 = GameScreen.getBg1();
	private Background bg2 = GameScreen.getBg2();

	
	//here we declare vars to be used to plot the collision squares of
	//Cat
	public int rectl = 0;
	public int rectt = 0;
	public int rectr = 0;
	public int rectb = 0;
	
	
	public int rect2l = 0;
	public int rect2t = 0;
	public int rect2r = 0;
	public int rect2b = 0;
	
	
	public int rect3l = 0;
	public int rect3t = 0;
	public int rect3r = 0;
	public int rect3b = 0;
	
	
	public int rect4l = 0;
	public int rect4t = 0;
	public int rect4r = 0;
	public int rect4b = 0;
	
	
	public int footLeftl = 0;
	public int footLeftt = 0;
	public int footLeftr = 0;
	public int footLeftb = 0;
	
	
	public int footRightl = 0;
	public int footRightt = 0;
	public int footRightr = 0;
	public int footRightb = 0;
	
	public int yellowRedl = 0;
	public int yellowRedt = 0;
	public int yellowRedr = 0;
	public int yellowRedb = 0;
	
	private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();

	public void update() {
		// Moves Character or Scrolls Background accordingly.

		//if speed is negative,then move Cat back
		if (speedX < 0) {
			centerX += speedX;
			speedX += 1;
			
		}
		//if speed is 0 or below, dont move the backgrounds
		if (speedX == 0 || speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);

		}
		//if Cat is in the first half of screen, and speed  is positive
		//then move Cat one speed unit
		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}
		//if Cat is moving and at the center of screen, move the backgrounds
		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED / 5);
			bg2.setSpeedX(-MOVESPEED / 5);
		}

		// Updates Y Position
		centerY += speedY;

		// Handles Jumping

			speedY += 1;//this is gravity

		if (speedY > 3){
			jumped = true;
			speedX = 1;
		}

		// Prevents going beyond X coordinate of 0
		if (centerX + speedX <= 60) {
			centerX = 61;
		}
		
		
		//Now lets set the collision squares by using left (l) top (t) right (r) and bottom (b)
		//here we set the coordinates.
		
		//head red
		rectl = centerX-34;
		rectt = centerY +10;
		rectr = centerX +4;
		rectb = centerY + 0;
		rect.set(rectl, rectt , rectr , rectb);
		
		//torso blue
		rect2l = rect.left;
		rect2t = rect.top + 33;
		rect2r = rect.left+30;
		rect2b = rect.top + 57;
		rect2.set(rect2l, rect2t, rect2r,rect2b );
		
		
		
		//left hand white
		rect3l = centerX - 40;
		rect3t = centerY + 15;
		rect3r =  centerX -3;
		rect3b = centerY + 30;
		rect3.set(rect3l,rect3t ,rect3r ,rect3b );
		
		//right hand black
		rect4l = centerX + 15;
		rect4t = centerY + 10;
		rect4r = centerX + 5;
		rect4b = centerY + 30;
		rect4.set(rect4l,rect4t ,rect4r , rect4b);
		
		
		
		//yellow square
		footLeftl = centerX - 30;
		footLeftt = centerY + 45;
		footLeftr = centerX -25;
		footLeftb = centerY + 56;
		footleft.set(footLeftl,footLeftt ,footLeftr ,footLeftb );

		//green square
		footRightl = centerX - 10;
		footRightt = centerY + 45;
		footRightr = centerX;
		footRightb = centerY + 55;
		rect.set(rectl, rectt , rectr , rectb);
		footright.set(footRightl, footRightt, footRightr, footRightb);
		
		yellowRedl = centerX - 80;
		yellowRedt = centerY - 60;
		yellowRedr = centerX + 60;
		yellowRedb = centerY + 70;
		yellowRed.set(yellowRedl,yellowRedt ,yellowRedr ,yellowRedb );
		
		if (this.isDucked()){
			
			
			
			
			//left hand white
			rect3l = centerX;
			rect3t = centerY+30;
			rect3r =  centerX+5;
			rect3b = centerY+35;
			rect3.set(rect3l,rect3t ,rect3r ,rect3b );
			
			//right hand black
			rect4l = centerX;
			rect4t = centerY +25;
			rect4r = centerX + 5 ;
			rect4b = centerY + 35;
			rect4.set(rect4l,rect4t ,rect4r , rect4b);
			
			//head red
			rectl = rect4l;
			rectt = rect4t;
			rectr = rect4r ;
			rectb = rect4b;
			rect.set(rectl, rectt , rectr , rectb);
			
			
		}
	
	}

	
	
	
	
	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 1;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}

	public void jump() {
		if (jumped == false) {
			speedY = JUMPSPEED;
			speedX = 2;
			jumped = true;
			
			Assets.jump.play(.85f);
		}

	}

	public void megaJump() {
		
			speedY = JUMPSPEED - 15;
			speedX = 5;
			jumped = true;
			
			

	}
	
	
	public void shoot() {
		if (readyToFire) {
			Projectile p = new Projectile(centerX + 50, centerY - 25);
			projectiles.add(p);
		}
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public boolean isJumped() {
		return jumped;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getSpeedY() {
		return speedY;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	public boolean isDucked() {
		return ducked;
	}

	public void setDucked(boolean ducked) {
		this.ducked = ducked;
		
	}

	public boolean isMovingRight() {
		return movingRight;
	}

	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	public boolean isMovingLeft() {
		return movingLeft;
	}

	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	public ArrayList getProjectiles() {
		return projectiles;
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}

	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

	public void collided() {
		this.hasCollided = true;
	}
	public boolean hasCollided() {
		return this.hasCollided;
	}

	public static Rect getRect() {
		return rect;
	}

	public static Rect getRect2() {
		return rect2;
	}

	public static Rect getRect3() {
		return rect3;
	}

	public static Rect getRect4() {
		return rect4;
	}

	public static void setRect(Rect rect) {
		Cat.rect = rect;
	}

	public static void setRect2(Rect rect2) {
		Cat.rect2 = rect2;
	}

	public static void setRect3(Rect rect3) {
		Cat.rect3 = rect3;
	}

	public static void setRect4(Rect rect4) {
		Cat.rect4 = rect4;
	}
}

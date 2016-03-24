package com.kilobolt.robotgame;

import com.kilobolt.robotgame.GameScreen.GameState;

import android.graphics.Rect;


public class Enemy {

	private int power, centerX, speedX, centerY;
	private Background bg = GameScreen.getBg1();
	private Robot robot = GameScreen.getRobot();
	private int speedY = 0;
	boolean canPlayCollisionSound = true;
	
	public Rect r = new Rect(0, 0, 0, 0);
	public int health = 5;

	public boolean gameIsOver = false;
	
	private int movementSpeed;
	private int movementSpeedVertical;

	// Behavioral Methods
	public void update() {
		follow();
		centerX += speedX;
		speedX = bg.getSpeedX() * 5 + movementSpeed;
		//speedY =  movementSpeedVertical;//not using the follow vertically
		r.set(centerX - 25, centerY - 45, centerX + 25, centerY + 35);

		if (Rect.intersects(r, Robot.yellowRed)) {
			checkCollision();
		}
		
		// Updates Y Position
	    centerY += speedY;
	}

	private void checkCollision() {
		if (Rect.intersects(r, Robot.rect)
				|| Rect.intersects(r, Robot.rect3) || Rect.intersects(r, Robot.rect4)) {
				//if(!robot.isDucked())
					//robot.collided();
			//gameIsOver = true;
			
			//we have hit a gnome, so we stop benito's jump
			if(canPlayCollisionSound){//play collision sound only once, to avoid nasty repetitions
				if(!robot.isDucked() ){//we crash only if benito is not rolling
					if(!robot.isJumped()){
						Assets.bounceBack.play(.85f);
						canPlayCollisionSound = false;
						this.gameIsOver = true;
					}
					else{
						Assets.bounceBack.play(.85f);
						canPlayCollisionSound  =false; 
						robot.setSpeedX(-10);
						robot.setSpeedY(10);
						speedY=-7;//set the gnome to go up
					}
				}
				else if(robot.isDucked()){//else benito is rolling
					if(canPlayCollisionSound){
						
						Assets.obliterateGnome.play(.85f);
						canPlayCollisionSound  =false;
						speedY=12;//set the gnome to fall down fast
					}
				}
				
			}
		}
		//else if we hit the top of the gnome, namely,the balloons then we jump
		else if (Rect.intersects(r, Robot.rect2)){
			robot.setJumped(false);
			if(!robot.isDucked())//we jump only if benito is not rolling
				robot.jump();
			else{//else benito is rolling, so we play the obliterate sound
				if(canPlayCollisionSound){
					Assets.obliterateGnome.play(.85f);
					canPlayCollisionSound  =false;
					
				}
				speedY=12;//set the gnome to fall down fast
			}
				
			r.setEmpty();//disappear the gnomes collision square
			
			if(!gameIsOver && canPlayCollisionSound){
				Assets.funkyJump.play(.85f);
				speedY=8;//set the gnome to fall down
			}
			
		
		}
				
	}

	public void follow() {
		
		//make the gnomes follow benito horizontally
		if (centerX < -95 || centerX > 810){
			movementSpeed = 0;
		}

		else if (Math.abs(robot.getCenterX() - centerX) < 5) {
			movementSpeed = 0;
		}

		else {

			if (robot.getCenterX() >= centerX) {
				movementSpeed = 1;
			} else {
				movementSpeed = -1;
			}
		}

		//make the gnomes follow benito verticlly
		if (centerY < -95 || centerY > 810){
			movementSpeedVertical = 0;
		}

		else if (Math.abs(robot.getCenterY() - centerY) < 5) {
			movementSpeedVertical = 0;
		}

		else {

			if (robot.getCenterY() >= centerY) {
				movementSpeedVertical = 1;
			} else {
				movementSpeedVertical = -1;
			}
		}
		
		
	}

	public void die() {

	}

	public void attack() {

	}

	public int getPower() {
		return power;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public Background getBg() {
		return bg;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	public void setBg(Background bg) {
		this.bg = bg;
	}

}
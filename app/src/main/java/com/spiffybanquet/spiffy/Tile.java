package com.spiffybanquet.spiffy;

import android.graphics.Rect;

import com.spiffybanquet.framework.Image;

public class Tile {

	private int tileX, tileY, speedX;
	public int type;
	public Image tileImage;

	private Cat cat = GameScreen.getCat();
	private Background bg = GameScreen.getBg1();
	public static boolean gameIsOver = false;
	private Rect r;
	public static boolean benitoIsDonezo = false;
	
	public Tile(int x, int y, int typeInt) {
		tileX = x * 40;
		tileY = y * 40;
		gameIsOver = false;
		benitoIsDonezo = false;//this one is used to send benito falling done when he just collided
		type = typeInt;

		r = new Rect();

		if (type == 5) {
			tileImage = Assets.tiledirt;
		} else if (type == 8) {
			tileImage = Assets.tilegrassTop;
		} else if (type == 4) {
			tileImage = Assets.tilegrassLeft;

		} else if (type == 6) {
			tileImage = Assets.tilegrassRight;

		} else if (type == 2) {
			tileImage = Assets.tilegrassBot;
		} 
		else if (type == 9) {
			tileImage = Assets.cactus;
		} 
		else if (type == 3) {
			tileImage = Assets.avila;
		}
		else if (type == 1) {
			
			tileImage = Assets.fish;
		}
		else if (type == 7) {
			
			tileImage = Assets.beachBall;
		}
		else {
			type = 0;
		}

	}

		public void update() {
			speedX = bg.getSpeedX() * 5;
			tileX += speedX;
	
			//if we hit a spiffy dinner we make the icon a  little smaller so 
			//it doesnt seem like benito is eating it without touching it
			if(type == 1)
				r.set(tileX+35, tileY, tileX+38, tileY+20);
			else
				r.set(tileX, tileY, tileX+40, tileY+40);
			
			
			if (Rect.intersects(r, Cat.yellowRed) && type != 0) {
				if(!benitoIsDonezo){
					checkVerticalCollision(Cat.rect, Cat.rect2);
					checkSideCollision(Cat.rect3, Cat.rect4, Cat.footleft, Cat.footright);
				}
			}
	
		}

	public int getTileX() {
		return tileX;
	}

	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	public int getTileY() {
		return tileY;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	public Image getTileImage() {
		return tileImage;
	}

	public void setTileImage(Image tileImage) {
		this.tileImage = tileImage;
	}

	public void checkVerticalCollision(Rect rtop, Rect rbot) {
		if (Rect.intersects(rtop, r)) {
			
		}

		//rbot is rect 2
		if (Rect.intersects(rbot, r) && type == 8) {
			if(cat.getSpeedY() >= 0){//if benito's gravity is already in falling mode, that is positive, then ground benito
				cat.setJumped(false);
				cat.setSpeedY(1);
			}
			//cat.setSpeedX(1);
			cat.setCenterY(tileY - 63);
			//System.out.println("+++++++++++++++++++++++++++++++vertical collision!");
		}
		
		else if(type ==1){//we just hit a spiffy banquet
			Assets.eat.play(.99f);
			type = 0;
			GameScreen.setScore(GameScreen.getScore() + 1);
		}
		
		
		
		
		//if benito hit a cactus (tile 9) then, he is donezo
		if (Rect.intersects(rbot, r) && type == 9) {
			gameIsOver = true;
		}
	}

	public void checkSideCollision(Rect rleft, Rect rright, Rect leftfoot, Rect rightfoot) {
		if (type != 5 && type != 2 && type != 0){
			
			
			//rect3 (white)
			if (Rect.intersects(rleft, r)) {
				cat.setCenterX(tileX + 102);
	
				//cat.setSpeedX(0);
				
				//System.out.println("+++++++++++++++++++++++++++++++rleft!");
	
			//left foot (yellow)
			}else if (Rect.intersects(leftfoot, r)) {
				
				cat.setCenterX(tileX + 85);
				//cat.setSpeedX(0);
				//System.out.println("+++++++++++++++++++++++++++++++leftfoot!");
			}
			
			
			//rect4 (black)
			//if benito hits tile, stop him
			if (Rect.intersects(rright, r)) {
				
				if(type == 3)//if benito hit an avila plant, donezo!
					gameIsOver = true;
				else if(type ==1){
					Assets.eat.play(.99f);
					type = 0;
					GameScreen.setScore(GameScreen.getScore() + 1);
					
				}
				else if(type ==7){//we just hit a beach ball
					if(cat.isDucked()){
						type = 0;
						Assets.ballPonchades.play(.85f);
						cat.setCenterX(tileX - 5);
					}
					else{
						Assets.funkyJump.play(.85f);
						cat.setCenterX(tileX + 15);
						cat.megaJump();
					}
					
				}	
				else{//else everything is normal,simply stop benito
					cat.setCenterX(tileX - 5);
					//System.out.println("+++++++++++++++++++++++++++++++rright!");
	
					cat.setSpeedX(0);
				}
			}
			
			//right foot (green)
			
			else if (Rect.intersects(rightfoot, r)) {
				cat.setCenterX(tileX + 15);
				//cat.setSpeedX(0);
				//System.out.println("+++++++++++++++++++++++++++++++rightfoot!");
			}
		}
	}

}
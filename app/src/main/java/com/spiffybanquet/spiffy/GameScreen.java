package com.spiffybanquet.spiffy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.spiffybanquet.framework.Game;
import com.spiffybanquet.framework.Graphics;
import com.spiffybanquet.framework.Image;
import com.spiffybanquet.framework.Input.TouchEvent;
import com.spiffybanquet.framework.Screen;


//this is the main one, the big cheese. the one
//that does the update and such.
//Remember that loadingScreen is the one that
//initializes things like sound and the screens
//This one has animation
public class GameScreen extends Screen {
	enum GameState {
		Ready, Running, Paused, GameOver
	}

    GameState state = GameState.Ready;

	// Variable Setup

	private static Background bg1, bg2;
	private static Cat cat;
	public static Heliboy hb, hb2,hb3,hb4,hb5,hb6,hb7,hb8,hb9,hb10,hb11,hb12,hb13;
	private static boolean firstTap;
    private static int score = 0;
	private Image currentSprite, character, character2, character3, heliboy,
			heliboy2, heliboy3, heliboy4, heliboy5,roll,roll2,roll3,donezo,donezo2,donezo3,donezo4;
	private Animation anim, hanim,duckanim,donezoAnim;

	private ArrayList<Tile> tilearray = new ArrayList<Tile>();

	int livesLeft = 3;
	boolean canPlayCollisionSound = true;
	
	Paint paint, paint2,paint3;

	public GameScreen(Game game) {
		super(game);

		Assets.theme.stop();//stop the menu music
		
		// Initialize game objects here

		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);
		cat = new Cat();
		hb = new Heliboy(590, 200);
		hb2 = new Heliboy(1300, 160);
		hb3 = new Heliboy(2600, 240);
		hb4 = new Heliboy(7010, 300);
		hb5 = new Heliboy(7510, 350);
		hb6 = new Heliboy(8010, 350);
		hb7 = new Heliboy(8910, 350);
		hb8 = new Heliboy(9510, 350);
		hb9 = new Heliboy(10510, 350);
		hb10 = new Heliboy(11510, 350);
		hb11= new Heliboy(12510, 350);
		hb12 = new Heliboy(12510, 350);
		hb12 = new Heliboy(12610, 350);

		character = Assets.character;
		character2 = Assets.character2;
		character3 = Assets.character3;

		roll = Assets.roll;
		roll2 = Assets.roll2;
		roll3 = Assets.roll3;
		
		donezo = Assets.donezo;
		donezo2 = Assets.donezo2;
		donezo3 = Assets.donezo3;
		donezo4 = Assets.donezo4;
		
		heliboy = Assets.heliboy;
		heliboy2 = Assets.heliboy2;
		heliboy3 = Assets.heliboy3;
		heliboy4 = Assets.heliboy4;
		heliboy5 = Assets.heliboy5;

		
		anim = new Animation();
		anim.addFrame(character, 100);
		anim.addFrame(character2, 100);
		anim.addFrame(character3,100);
		anim.addFrame(character2, 100);

		duckanim = new Animation();
		duckanim.addFrame(roll, 100);
		duckanim.addFrame(roll2, 100);
		duckanim.addFrame(roll3,100);
		
		donezoAnim = new Animation();
		donezoAnim.addFrame(donezo, 100);
		donezoAnim.addFrame(donezo2, 100);
		donezoAnim.addFrame(donezo3,100);
		donezoAnim.addFrame(donezo4,100);
		
		hanim = new Animation();
		hanim.addFrame(heliboy, 500);
		hanim.addFrame(heliboy2, 500);
		hanim.addFrame(heliboy3, 500);
		hanim.addFrame(heliboy2, 500);
		hanim.addFrame(heliboy, 500);
		hanim.addFrame(heliboy4, 500);
		hanim.addFrame(heliboy5, 500);
		hanim.addFrame(heliboy4, 500);
		hanim.addFrame(heliboy, 500);
		hanim.addFrame(heliboy2, 500);

		currentSprite = anim.getImage();

		firstTap = true;
		
		loadMap();

		// Defining a paint object
		Typeface tf = Typeface.create("System",Typeface.NORMAL);
		paint = new Paint();
		paint.setTextSize(30);
		paint.setTextAlign(Paint.Align.CENTER);
		paint.setAntiAlias(true);
		paint.setColor(Color.rgb(220,20,60));
		paint.setTypeface(tf);

		paint2 = new Paint();
		paint2.setTextSize(100);
		paint2.setTextAlign(Paint.Align.CENTER);
		paint2.setAntiAlias(true);
		paint2.setColor(Color.WHITE);

		
		paint3 = new Paint();
		paint3.setTextSize(40);
		paint3.setTextAlign(Paint.Align.CENTER);
		paint3.setAntiAlias(true);
		paint3.setColor(Color.rgb(0,0,0));
		paint3.setTypeface(tf);
	}

	private void loadMap() {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;
		int rows = 12;
		int cols = 100;//this is the width of each random map
		Scanner scanner = null;
	   
		//set the score to 0
		score = 0;






		//now we shuffle the middle maps for randomness
		Collections.shuffle(Arrays.asList(SampleGame.maps));
		
		//then we go through each map and build it
		for (int currentMap= 0;currentMap<SampleGame.maps.length;currentMap++){

						scanner = new Scanner(SampleGame.maps[currentMap]);//here we get a random map
						while (scanner.hasNextLine()) {

							String line = scanner.nextLine();

							// no more lines to read
							if (line == null) {
								break;
							}

							if (!line.startsWith("!")) {//if line is not a comment,load the line
								lines.add(line);
								width = Math.max(width, line.length());//lets get the longest line and save it in width

							}
						}
						height = lines.size();///determine the height based on the lines loaded



						for (int currentRow = 0; currentRow < rows; currentRow++) {//for each row, which is 12 of them
							String line = (String) lines.get(currentRow);//line is the current line

							for (int currentColumn = cols *currentMap; currentColumn < cols*(currentMap+1); currentColumn++) {//from 0 to cols which is 100, the width of each random map

								if (currentColumn < line.length()+(cols*currentMap)) {//if is in bounds of the current line
									char ch = line.charAt(currentColumn-(cols*currentMap));//lets get the tile number
									Tile t = new Tile(currentColumn, currentRow, Character.getNumericValue(ch));
									tilearray.add(t);

								}

							}
						}
						lines.clear();//clear the lines list, otherwise the map will repeat
		}//end for loop


		//now we write the final map, the fun one
		int currentMap = 6;
		scanner = new Scanner(SampleGame.finalMap);//here we get a random map
		while (scanner.hasNextLine()) {

			String line = scanner.nextLine();

			// no more lines to read
			if (line == null) {
				break;
			}

			if (!line.startsWith("!")) {//if line is not a comment,load the line
				lines.add(line);
				width = Math.max(width, line.length());//lets get the longest line and save it in width

			}
		}
		height = lines.size();///determine the height based on the lines loaded



		for (int currentRow = 0; currentRow < rows; currentRow++) {//for each row, which is 12 of them
			String line = (String) lines.get(currentRow);//line is the current line

			for (int currentColumn = cols *currentMap; currentColumn < cols*(currentMap+1); currentColumn++) {//from 0 to cols which is 100, the width of each random map

				if (currentColumn < line.length()+(cols*currentMap)) {//if is in bounds of the current line
					char ch = line.charAt(currentColumn-(cols*currentMap));//lets get the tile number
					Tile t = new Tile(currentColumn, currentRow, Character.getNumericValue(ch));
					tilearray.add(t);

				}

			}
		}
		lines.clear();//clear the lines list, otherwise the map will repeat


	}//end load map

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();

		// We have four separate update methods in this example.
		// Depending on the state of the game, we call different update methods.
		// Refer to Unit 3's code. We did a similar thing without separating the
		// update methods.
		if (state == GameState.Ready)
			updateReady(touchEvents);
		if (state == GameState.Running)
			updateRunning(touchEvents, deltaTime);
		if (state == GameState.Paused)
			updatePaused(touchEvents);
		if (state == GameState.GameOver)
			updateGameOver(touchEvents);
	}

	private void updateReady(List<TouchEvent> touchEvents) {

		// This example starts with a "Ready" screen.
		// When the user touches the screen, the game begins.
		// state now becomes GameState.Running.
		// Now the updateRunning() method will be called!
		
		if (touchEvents.size() >=1)
			state = GameState.Running;
	}

	
	
	
	
	private void updateRunning(List<TouchEvent> touchEvents, float deltaTime) {

	
		// 1. All touch input is handled here:
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_DOWN &&!firstTap) {

//				if (inBounds(event, 0, 285, 65, 65)) {
//					cat.jump();
//					currentSprite = anim.getImage();
//					cat.setDucked(false);
//				}
//
//				else if (inBounds(event, 0, 350, 65, 65)) {
//
//					if (cat.isDucked() == false && cat.isJumped() == false
//							&& cat.isReadyToFire()) {
//						cat.shoot();
//					}
//				}
//
//				else if (inBounds(event, 0, 285, 265, 265)
//						&& cat.isJumped() == false) {
//					currentSprite = Assets.characterDown;
//					cat.setDucked(true);
//					cat.setSpeedX(1);
//
//				}

				//if pressing bottom left screen, duck benito
				if (event.x < 400 && event.y > 100 ) {
					if(cat.isJumped() == false){
						currentSprite = Assets.roll;
						Assets.duck.play(.85f);
						cat.setDucked(true);
						cat.setSpeedX(1);
					}
					else if (cat.isJumped() == true){
						cat.setDucked(true);
						currentSprite = Assets.roll;
						Assets.duckAir.play(.85f);
						
					}
					

				}
				
				//if tapping right side, then jump benito
				if (event.x > 400) {
					
					cat.jump();
					currentSprite = anim.getImage();
					cat.setDucked(false);
					
					// Move right.
					//cat.moveRight();
					//cat.setMovingRight(true);

				}
				

			}//end the if touch down
			
			
			else//we only go here the first time
				firstTap = false;//firsttap is to detect when player is tapping the first time to prevent the action when game starts
			
			
			
			//Im not using the touch up, I took it off so ignore this
			if (event.type == TouchEvent.TOUCH_UP) {

				if (inBounds(event, 0, 415, 65, 65)) {
					currentSprite = anim.getImage();
					cat.setDucked(false);

				}

				//set area to detect pause button
				if (inBounds(event, 0, 0, 200, 200)) {
					pause();

				}

				if (event.x > 400) {
					// stop moving the cat if over half the screen,the bg should start moving
					cat.stopRight();
				}
			}

		}

		// 2. Check miscellaneous events like death:

		if (livesLeft == 0) {
			state = GameState.GameOver;
		}
		
		//this code detects a collision with the cacti
		if(hb2.gameIsOver || hb.gameIsOver|| hb3.gameIsOver|| hb4.gameIsOver||
				hb5.gameIsOver || hb6.gameIsOver || hb7.gameIsOver || hb8.gameIsOver || hb9.gameIsOver || hb10.gameIsOver
				 || hb10.gameIsOver || hb11.gameIsOver || hb12.gameIsOver	|| Tile.gameIsOver){
			currentSprite = donezoAnim.getImage();
			if(canPlayCollisionSound){//play collision sound only once, to avoid nasty repetitions
				Assets.hit.play(.85f);
				canPlayCollisionSound  =false; 
			}
			Tile.benitoIsDonezo = true;
			//state = GameState.GameOver;
		}
		
		
		
	   if( Tile.gameIsOver){
			currentSprite = donezoAnim.getImage();
			if(canPlayCollisionSound){//play collision sound only once, to avoid nasty repetitions
				Assets.hit.play(.85f);
				canPlayCollisionSound  =false; 
			}
			Tile.benitoIsDonezo = true;
		}
		
		// 3. Call individual update() methods here.
		// This is where all the game updates happen.
		// For example, cat.update();
		cat.update();
		if (cat.isJumped()) {
			if(Tile.benitoIsDonezo == true)
				currentSprite = donezoAnim.getImage();
			else{//else benito is in the air
				if(cat.isDucked()== true){//if ducked show the rolling image
					currentSprite = duckanim.getImage();
				}
				else{//else he is in the air and not rolling. he is either falling or jumping
				currentSprite = Assets.characterJump;
				cat.setDucked(false);//set benito to not jumping since he is falling from edge now
				}
			}
		}
		
		//if benito is not jumping or rolling, show the walking animation
		else if (cat.isJumped() == false && cat.isDucked() == false) {
			currentSprite = anim.getImage();
		}
		
		//if benito is duck, play the rollin animation
		else if (cat.isJumped() == false && cat.isDucked() == true) {
			currentSprite = duckanim.getImage();
		}

//		ArrayList projectiles = cat.getProjectiles();
//		for (int i = 0; i < projectiles.size(); i++) {
//			Projectile p = (Projectile) projectiles.get(i);
//			if (p.isVisible() == true) {
//				p.update();
//			} else {
//				projectiles.remove(i);
//			}
//		}

		updateTiles();
		hb.update();
		hb2.update();
		hb3.update();
		hb4.update();
		hb5.update();
		hb6.update();
		hb7.update();
		hb8.update();
		hb9.update();
		hb10.update();
		hb11.update();
		hb12.update();
		bg1.update();
		bg2.update();
		animate();

		if (cat.getCenterY() > 500) {//cat has fallen
			Assets.gameover.play(.85f);
			state = GameState.GameOver;
		}
		
		
		
		
	}

	private boolean inBounds(TouchEvent event, int x, int y, int width,
			int height) {
		if (event.x > x && event.x < x + width - 1 && event.y > y
				&& event.y < y + height - 1)
			return true;
		else
			return false;
	}

	private void updatePaused(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if (event.type == TouchEvent.TOUCH_UP) {
				if (inBounds(event, 0, 0, 800, 240)) {

					if (!inBounds(event, 0, 0, 35, 35)) {
						resume();
					}
				}

				if (inBounds(event, 0, 240, 800, 240)) {
					nullify();
					goToMenu();
				}
			}
		}
	}

	private void updateGameOver(List<TouchEvent> touchEvents) {
		int len = touchEvents.size();
		
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(0);
			if (event.type == TouchEvent.TOUCH_DOWN) {
				if (inBounds(event, 0, 0, 800, 480)) {
					nullify();
					
					game.setScreen(new GameScreen(game));//this brings you to the game right away
					
					//game.setScreen(new MainMenuScreen(game));
					return;
				}
			}
		}

	}

	private void updateTiles() {

		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			t.update();
		}

	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();

		g.drawImage(Assets.background, bg1.getBgX(), bg1.getBgY());
		g.drawImage(Assets.background, bg2.getBgX(), bg2.getBgY());
		paintTiles(g);

		ArrayList projectiles = cat.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = (Projectile) projectiles.get(i);
			g.drawRect(p.getX(), p.getY(), 10, 5, Color.YELLOW);
		}
		
		
		
		//draw the rects for debugging purposes
//		g.drawRect(cat.rectl, cat.rectt, cat.rectr - cat.rectl, cat.rectb-cat.rectt,Color.RED );
//		g.drawRect(cat.rect2l, cat.rect2t, cat.rect2r - cat.rect2l, cat.rect2b-cat.rect2t,Color.BLUE );
//		g.drawRect(cat.rect3l, cat.rect3t, cat.rect3r - cat.rect3l, cat.rect3b-cat.rect3t,Color.WHITE);//left hand
//		g.drawRect(cat.rect4l, cat.rect4t, cat.rect4r - cat.rect4l, cat.rect4b-cat.rect4t,Color.BLACK );//right hand
//		g.drawRect(cat.footLeftl, cat.footLeftt, cat.footLeftr - cat.footLeftl, cat.footLeftb-cat.footLeftt,Color.YELLOW );
//		g.drawRect(cat.footRightl, cat.footRightt, cat.footRightr - cat.footRightl, cat.footRightb-cat.footRightt,Color.GREEN );
		//g.drawRect(cat.yellowRedl, cat.yellowRedt, cat.yellowRedr - cat.yellowRedl, cat.yellowRedb-cat.yellowRedt,Color.LTGRAY );
		
		// First draw the game elements.		
		g.drawImage(currentSprite, cat.getCenterX() - 61, cat.getCenterY() - 13);
		g.drawImage(hanim.getImage(), hb.getCenterX() - 48,
				hb.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb2.getCenterX() - 48,
				hb2.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb3.getCenterX() - 48,
				hb3.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb4.getCenterX() - 48,
				hb4.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb5.getCenterX() - 48,
				hb5.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb6.getCenterX() - 48,
				hb6.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb7.getCenterX() - 48,
				hb7.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb8.getCenterX() - 48,
				hb8.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb9.getCenterX() - 48,
				hb9.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb10.getCenterX() - 48,
				hb10.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb11.getCenterX() - 48,
				hb11.getCenterY() - 48);
		g.drawImage(hanim.getImage(), hb12.getCenterX() - 48,
				hb12.getCenterY() - 48);
		
		
		
		

		// Secondly, draw the UI above the game elements.
		if (state == GameState.Ready)
			drawReadyUI();
		if (state == GameState.Running)
			drawRunningUI();
		if (state == GameState.Paused)
			drawPausedUI();
		if (state == GameState.GameOver)
			drawGameOverUI();

	}

	private void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tile t = (Tile) tilearray.get(i);
			if (t.type != 0 ) {
				g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY());
			}
		}
	}

	public void animate() {
		//the bigger the numbers the faster the animation runs
		anim.update(10);
		duckanim.update(20);
		hanim.update(50);
		donezoAnim.update(30);
	}

	private void nullify() {

		// Set all variables to null. You will be recreating them in the
		// constructor.
		paint = null;
		bg1 = null;
		bg2 = null;
		cat = null;
		hb = null;
		hb2 = null;
		currentSprite = null;
		character = null;
		character2 = null;
		character3 = null;
		heliboy = null;
		heliboy2 = null;
		heliboy3 = null;
		heliboy4 = null;
		heliboy5 = null;
		anim = null;
		hanim = null;
		duckanim = null;
		donezoAnim = null;
		
		// Call garbage collector to clean up memory.
		System.gc();

	}

	private void drawReadyUI() {
		Graphics g = game.getGraphics();

		g.drawARGB(155, 0, 0, 0);
		//g.drawImage(Assets.gotIt, 260, 210);
		//g.drawImage(Assets.tapToRoll, 60, 310);
		//g.drawImage(Assets.gotIt, 260, 290);
		g.drawImage(Assets.rollButton, 50, 390);
		g.drawImage(Assets.jumpButton, 600, 390);
		//g.drawString("tap here to roll", 200, 310, paint);
		//g.drawString("OK!", 400, 410, paint);
		//g.drawImage(Assets.tapToJump, 430, 310);
		//g.drawString("tap here to jump", 600, 310, paint);

	}

	private void drawRunningUI() {
		Graphics g = game.getGraphics();
		//these are the controls to show on the screen
		g.drawImage(Assets.rollButton, 50, 390);
		g.drawImage(Assets.jumpButton, 600, 390);
		/*g.drawImage(Assets.button, 0, 415, 0, 130, 65, 65);*/
		//g.drawImage(Assets.button, 0, 0, 0, 195, 35, 35);
		g.drawImage(Assets.button, 0, 0);
	}

	private void drawPausedUI() {
		Graphics g = game.getGraphics();
		// Darken the entire screen so you can display the Paused screen.
		g.drawARGB(155, 0, 0, 0);
		g.drawString("Resume", 400, 165, paint2);
		g.drawString("Menu", 400, 360, paint2);

	}

	private void drawGameOverUI() {
		Graphics g = game.getGraphics();
		//g.drawRect(0, 0, 1281, 801, Color.BLACK);
		
		g.drawImage(Assets.end, 0, 0);
		g.drawString("SCORE:"+ GameScreen.getScore(), 600, 240, paint3);
		g.drawString("Tap to try again!", 600, 290, paint3);
		
	}

	@Override
	public void pause() {
		if (state == GameState.Running)
			state = GameState.Paused;

	}

	@Override
	public void resume() {
		if (state == GameState.Paused)
			state = GameState.Running;
	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {
		pause();
	}

	private void goToMenu() {
		Assets.theme.play();
		game.setScreen(new MainMenuScreen(game));

	}

	public static Background getBg1() {
		// TODO Auto-generated method stub
		return bg1;
	}

	public static Background getBg2() {
		// TODO Auto-generated method stub
		return bg2;
	}

	public static Cat getCat() {
		// TODO Auto-generated method stub
		return cat;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int theScore) {
		score = theScore;
	}

	
}
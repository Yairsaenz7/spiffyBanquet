package com.kilobolt.robotgame;

import com.kilobolt.framework.Game;
import com.kilobolt.framework.Graphics;
import com.kilobolt.framework.Graphics.ImageFormat;
import com.kilobolt.framework.Screen;

public class LoadingScreen extends Screen {
	public LoadingScreen(Game game) {
		
		super(game);
	}

	@Override
	public void update(float deltaTime) {
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.png", ImageFormat.RGB565);
		Assets.end = g.newImage("end.png", ImageFormat.RGB565);
		Assets.background = g.newImage("background.png", ImageFormat.RGB565);
		Assets.character = g.newImage("character.png", ImageFormat.ARGB4444);
		Assets.character2 = g.newImage("character2.png", ImageFormat.ARGB4444);
		Assets.character3  = g.newImage("character3.png", ImageFormat.ARGB4444);
		Assets.characterJump = g.newImage("jumped.png", ImageFormat.ARGB4444);
		
		Assets.roll = g.newImage("roll.png", ImageFormat.ARGB4444);
		Assets.roll2 = g.newImage("roll2.png", ImageFormat.ARGB4444);
		Assets.roll3 = g.newImage("roll3.png", ImageFormat.ARGB4444);

		Assets.donezo = g.newImage("donezo.png", ImageFormat.ARGB4444);
		Assets.donezo2 = g.newImage("donezo2.png", ImageFormat.ARGB4444);
		Assets.donezo3  = g.newImage("donezo3.png", ImageFormat.ARGB4444);
		Assets.donezo4 = g.newImage("donezo4.png", ImageFormat.ARGB4444);
		
		
		Assets.heliboy = g.newImage("gnome.png", ImageFormat.ARGB4444);
		Assets.heliboy2 = g.newImage("gnome2.png", ImageFormat.ARGB4444);
		Assets.heliboy3  = g.newImage("gnome3.png", ImageFormat.ARGB4444);
		Assets.heliboy4  = g.newImage("gnome4.png", ImageFormat.ARGB4444);
		Assets.heliboy5  = g.newImage("gnome5.png", ImageFormat.ARGB4444);


		
		Assets.tiledirt = g.newImage("tiledirt.png", ImageFormat.RGB565);
		Assets.tilegrassTop = g.newImage("tilegrasstop.png", ImageFormat.RGB565);
		Assets.tilegrassBot = g.newImage("tilegrassbot.png", ImageFormat.RGB565);
		Assets.tilegrassLeft = g.newImage("tilegrassleft.png", ImageFormat.RGB565);
		Assets.tilegrassRight = g.newImage("tilegrassright.png", ImageFormat.RGB565);
		Assets.cactus = g.newImage("cactus.png", ImageFormat.RGB565);
		Assets.avila = g.newImage("avila.png", ImageFormat.RGB565);
		Assets.fish = g.newImage("fish.png", ImageFormat.RGB565);
		Assets.beachBall = g.newImage("beachBall.png", ImageFormat.RGB565);
		Assets.tapToRoll = g.newImage("tapToRoll.png", ImageFormat.RGB565);
		Assets.tapToJump = g.newImage("tapToJump.png", ImageFormat.RGB565);
		Assets.gotIt = g.newImage("gotit.png", ImageFormat.RGB565);
		Assets.jumpButton = g.newImage("jumpButton.png", ImageFormat.RGB565);
		Assets.rollButton = g.newImage("rollButton.png", ImageFormat.RGB565);

		Assets.button = g.newImage("button.png", ImageFormat.RGB565);

		//This is how you would load a sound if you had one.
		Assets.hit = game.getAudio().createSound("hit.wav");
		Assets.jump = game.getAudio().createSound("jump.wav");
		Assets.gameover = game.getAudio().createSound("gameover.wav");
		Assets.funkyJump = game.getAudio().createSound("funkyJump.wav");
		Assets.duck = game.getAudio().createSound("duck.wav");
		Assets.eat = game.getAudio().createSound("eat.wav");
		Assets.bounceBack = game.getAudio().createSound("bounceBack.wav");
		Assets.duckAir = game.getAudio().createSound("duckAir.wav");
		Assets.obliterateGnome = game.getAudio().createSound("obliterateGnome.wav");
		Assets.ballPonchades = game.getAudio().createSound("ballPonchades.wav");
		
		
		game.setScreen(new MainMenuScreen(game));
		
		//sleep to let the green tea company show for a bit
		
		try {
            Thread.sleep(2500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}

	@Override
	public void paint(float deltaTime) {
		Graphics g = game.getGraphics();
		g.drawImage(Assets.splash, 0, 0);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

	@Override
	public void backButton() {

	}
	
	
	
}
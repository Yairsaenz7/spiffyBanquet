package com.spiffybanquet.spiffy;

import com.spiffybanquet.framework.Image;
import com.spiffybanquet.framework.Music;
import com.spiffybanquet.framework.Sound;

public class Assets {
	
	//remember most of the assets are initialized in the loadingScreen
	public static Image menu,end, splash, background, character, character2, character3, 
	heliboy, heliboy2, heliboy3, heliboy4, heliboy5,donezo,donezo2,donezo3,donezo4;
	public static Image tiledirt, tilegrassTop, tilegrassBot,
	tilegrassLeft, tilegrassRight,cactus,avila,fish, characterJump, roll,roll2,roll3,home,beachBall;
	public static Image button,tapToRoll,tapToJump,gotIt,jumpButton,rollButton;
	public static Sound jump,funkyJump,hit,gameover,duck,eat,bounceBack,duckAir,obliterateGnome,ballPonchades;
	public static Music theme;
	
	public static void load(SampleGame sampleGame) {
		// TODO Auto-generated method stub
		
		/*here the theme starts*/
		theme = sampleGame.getAudio().createMusic("theme.wav");
		theme.setLooping(true);
		theme.setVolume(0.85f);
		theme.play();
	}
	
}

package com.kilobolt.robotgame;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.util.Arrays;
        import java.util.Collections;
        import java.util.Random;

        import android.util.Log;

        import com.kilobolt.framework.Screen;
        import com.kilobolt.framework.implementation.AndroidGame;


//this class loads the map and that's about it
public class SampleGame extends AndroidGame {

    public static String[] maps = new String[6] ;
    public static String map ;
    public static String finalMap;
    boolean firstTimeCreate = true;

    @Override
    public Screen getInitScreen() {

        if (firstTimeCreate) {
            Assets.load(this);
            firstTimeCreate = false;
        }

        String theMap = "";
        for(int i = 0;i< maps.length ; i++){
            theMap = "map" +i;
            InputStream is = getResources().openRawResource(getResources().getIdentifier(theMap, "raw", getPackageName()));
            maps[i] = convertStreamToString(is);

        }

        theMap = "map" +6;
        InputStream is = getResources().openRawResource(getResources().getIdentifier(theMap, "raw", getPackageName()));
        finalMap = convertStreamToString(is);

        return new SplashLoadingScreen(this);

    }

    @Override
    public void onBackPressed() {
        getCurrentScreen().backButton();
    }

    private static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line + "\n"));
            }
        } catch (IOException e) {
            Log.w("LOG", e.getMessage());
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                Log.w("LOG", e.getMessage());
            }
        }
        return sb.toString();
    }

    @Override
    public void onResume() {
        super.onResume();

        Assets.theme.play();

    }

    @Override
    public void onPause() {
        super.onPause();
        Assets.theme.pause();

    }

    public static String[] randomizeArray(String[] array){
        Random rgen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            String temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
}
package com.aldrinarciga.linearjumper.screen;

/**
 * Created by perpetualwave on 18/04/16.
 */
public class ScreenManager {

    private static Screen currentScreen;

    public static void setScreen(Screen screen){
        if(currentScreen != null)
            currentScreen.dispose();
        currentScreen = screen;
        currentScreen.create();
    }

    public static Screen getCurrentScreen(){
        return currentScreen;
    }
}

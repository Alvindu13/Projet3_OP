package com.jeremie;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    /**
     * Run program.
     * @param args dev mode (args[0] = false, args[1] = true).
     */
    public static void main(String[] args) {
        devMode(args);
        GameSelector game = new GameSelector(Boolean.parseBoolean(args[0]));
        game.numberRun();
    }

    public static void devMode(String[] args){
        Properties prop = new Properties();
        String propertiesDevMode;
        try {
            prop.load(new FileReader("src/main/resources/config.properties"));
            propertiesDevMode = prop.getProperty("dev.mode");


        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}

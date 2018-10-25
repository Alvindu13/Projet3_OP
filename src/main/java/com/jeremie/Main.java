package com.jeremie;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    /**
     * Run program.
     * @param args dev mode (add parameters args to choice dev mode or not. The dev mode args is priotary on dev mode of properties file).
     * edit configuration program args : true = dev mode on and false = dev mode off.
     */
    public static void main(String[] args) {
        boolean devMode = devMode(args);
        GameSelector game = new GameSelector(devMode);;
        game.numberRun();
    }

    /**
     * Assign a value to devMode according to the parameters and their priority.
     * @param args devMode parameters.
     * @return value of devMode.
     */
    public static boolean devMode(String[] args){
        Properties prop = new Properties();
        String propertiesDevMode;
        Boolean devMode = false;
        Boolean.parseBoolean(args[0]);
        try {
            prop.load(new FileReader("src/main/resources/config.properties"));
            propertiesDevMode = prop.getProperty("dev.mode");
            if(args.length == 1 && (args[0].equals("true") || args[0].equals("false"))){
                devMode = Boolean.parseBoolean(args[0]);
            } else if(propertiesDevMode.equals("true") || (propertiesDevMode.equals("false"))) {
                devMode = Boolean.parseBoolean(propertiesDevMode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return devMode;
    }
}

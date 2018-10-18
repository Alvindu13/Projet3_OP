package com.jeremie;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Main {

    /**
     * Run program.
     * @param args dev mode (add parameters args to choice dev mode or not. The dev mode args is priotary on dev mode of properties file).
     * edit configuration parameters args : true = dev mode on and false = dev mode off.
     */
    public static void main(String[] args) {
        String[] devMode = new String[1];
        GameSelector game;
        boolean noArgs = false;
        try {
            Boolean.parseBoolean(args[0]);
        }catch (ArrayIndexOutOfBoundsException e){
            noArgs = true;
        }
        System.out.println(noArgs);
        devMode = devMode(devMode, noArgs, args);
        System.out.println(devMode[0]);
        game = new GameSelector(Boolean.parseBoolean(devMode[0]));
        game.numberRun();
    }

    /**
     * Assign a value to devMode according to the parameters and their priority.
     * @param devMode dev mode value. If dev mode = true, then secret combination will be displayed to the user.
     * @param noArgs variable to know if args is empty or not.
     * @param args devMode parameters.
     * @return value of devMode.
     */
    public static String[] devMode(String[] devMode, boolean noArgs, String[] args){
        Properties prop = new Properties();
        String propertiesDevMode;
        try {
            prop.load(new FileReader("src/main/resources/config.properties"));
            propertiesDevMode = prop.getProperty("dev.mode");
            System.out.println(propertiesDevMode);
            System.out.println(noArgs);
            if(propertiesDevMode == null && noArgs == true)
                devMode[0] = "false";
            else if(noArgs == false)
                devMode[0] = args[0];
            else if((propertiesDevMode == "true" || propertiesDevMode == "false") && noArgs == true)
                devMode[0] = propertiesDevMode;
            else
                devMode[0] = "false";
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(devMode[0]);
        return devMode;
    }
}

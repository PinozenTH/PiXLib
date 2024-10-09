package com.pinont.piXLib.configurations;

public class Configurations {

    Double version = 1.0;

    public void checkVersion() {
        if (Config.VERSION.getInt() < version) {
            System.out.println("Please update your plugin to the latest version.");
        }
    }

}

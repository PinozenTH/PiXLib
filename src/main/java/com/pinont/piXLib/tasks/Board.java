package com.pinont.piXLib.tasks;

public class Board implements Runnable{

    private Board instance = new Board();

    public Board() {
    }

    @Override
    public void run() {

    }

    public Board getInstance() {
        return instance;
    }
}

package com.core.application;

import com.core.models.Game;

import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        Game game = new Game();
        game.start();
        game.body();
        game.happyEnd();

    }
}

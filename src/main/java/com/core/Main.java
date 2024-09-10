package com.core;

import com.core.models.Game;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            Game game = new Game();
            game.start(br, bw);
            game.body(br, bw);
            game.happyEnd(br, bw);
        }
    }
}

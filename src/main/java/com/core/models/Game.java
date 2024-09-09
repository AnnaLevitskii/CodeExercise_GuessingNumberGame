package com.core.models;

import com.core.exceptions.IncorrectInput;
import lombok.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Getter
@Setter
@ToString
@Data
public class Game {
    private String secretNumber;
    private int attempts;
    private long startTime;
    private User user;

    public Game() {
        this.secretNumber = generateSecretNumber();
        this.attempts = 0;
        this.startTime = System.currentTimeMillis();
        this.user=new User();
    }

    private String generateSecretNumber() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        StringBuilder secretNumber = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            secretNumber.append(numbers.get(i));
        }
        System.out.println("generateSecretNumber "+secretNumber);
        return secretNumber.toString();
    }

    private boolean setUserName(String name) {
        if(name.isBlank() || Character.isDigit(name.charAt(0))) return false;
        this.user.setName(name);
        return true;
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void start() throws IOException {
        String str;
        boolean validName = false;

        while (!validName) {
            bw.write("Enter name:  ");
            bw.flush();
            str = br.readLine();

            validName = setUserName(str);
            if (!validName) {
                bw.write("Invalid name. Please try again.\n");
                bw.flush();
            }
        }
    }
    public void body() throws IOException {
        String str;
        boolean validNum = false;
        bw.write("Try to guess the four-digit number, no duplicate digits.");
        bw.newLine();
        bw.flush();
        while (!validNum) {
            ++this.attempts;
            bw.write("Enter number:  ");
            bw.flush();
            str = br.readLine();

            validNum = str.equals(this.secretNumber);

            if (!validNum) {
                bw.write(numberCheck(str) + " \n");
                bw.flush();
            }
        }

    }
    private String numberCheck(String str) {
        if(str.length()!=4 || str.chars().anyMatch(c -> !Character.isDigit(c)))
            return "Incorrect Input 4 digits";
        StringBuilder res = new StringBuilder();
        String secretNum = String.valueOf(this.secretNumber);
        int index = 0;
        for(char c : str.toCharArray()){
            if(secretNum.charAt(index)==c){
                res.append("+");
            }else if(str.contains(secretNum.charAt(index)+"")){
                res.append("-");
            }else{
                res.append("_");
            }
            index++;
        }
        return res.toString();
    }
    public void happyEnd() throws IOException {
        long time = System.currentTimeMillis() - startTime;
        user.saveNewUser(this.attempts, time);
        bw.write("Congratulations! Number of attempts "+ this.attempts +" \n");
        bw.flush();
        bw.newLine();
        bw.write("Your score "+ this.user.getScore() +" \n");
        bw.flush();
        bw.newLine();
        bw.write("Best score "+ this.user.getBestScore() +" \n");
        bw.flush();
        bw.newLine();


        if (br != null) br.close();
        if (bw != null) bw.close();
    }


}
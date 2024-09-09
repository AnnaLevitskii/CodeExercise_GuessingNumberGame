package com.core.models;

import com.core.providers.StorageProvider;
import lombok.*;

import java.util.List;
import java.util.Optional;

import static com.core.providers.StorageProvider.setStorage;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@Data
@AllArgsConstructor

public class User {
    private String name;
    private int score;
    private List<User> userList;

    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }

    private List<User> getUserList() {
        this.userList = StorageProvider.getStorage();
        return userList;
    }

    public boolean saveNewUser(int attempts, long time){
        getUserList();
        this.userList.add(new User(name, calcScore(attempts, time)));
        try {
            setStorage(this.userList);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    private int calcScore(int attempts, long time) {
        double timeInDouble = (double) time;
        System.out.println(time);
        double result = 1000000 / (timeInDouble * attempts);
        setScore ((int) Math.ceil(result));
        System.out.println("this.score "+this.score);
        return this.score;
    }

    public String getBestScore() {

        getUserList();
        User maxScoreUser = null;
        for (User user : userList) {
            if (maxScoreUser == null || user.getScore() > maxScoreUser.getScore()) {
                maxScoreUser = user;
            }
        }
        return String.valueOf(maxScoreUser.getScore());
    }
}


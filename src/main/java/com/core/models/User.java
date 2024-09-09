package com.core.models;

import com.core.providers.StorageProvider;
import lombok.*;

import java.util.List;
import java.util.Optional;

import static com.core.providers.StorageProvider.setStorage;

@Getter
@Setter

@Builder
@NoArgsConstructor
@Data


public class User {
    private String name;
    private int score;


    public User(String name, int score) {
        this.name = name;
        this.score = score;
    }


    public boolean saveNewUser(int attempts, long time){
        List<User> userList = StorageProvider.getStorage();
        userList.add(new User(name, calcScore(attempts, time)));
        try {
            setStorage(userList);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    private int calcScore(int attempts, long time) {
        double timeInDouble = (double) time;
        double result = 3000000 / (timeInDouble * attempts);
        setScore ((int) Math.ceil(result));
        return this.score;
    }

    public String getBestScore() {
        List<User> userList = StorageProvider.getStorage();
        User maxScoreUser = null;
        for (User user : userList) {
            if (maxScoreUser == null || user.getScore() > maxScoreUser.getScore()) {
                maxScoreUser = user;
            }
        }
        return String.valueOf(maxScoreUser.getScore());
    }
}


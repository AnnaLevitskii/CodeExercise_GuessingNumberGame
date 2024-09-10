package com.tests;

import com.core.models.User;
import com.core.providers.CleanUpProvider;
import com.core.providers.SetUpProvider;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.core.providers.StorageProvider.getStorage;
import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

public class UserTest {
    User user1 = new User("Jane", 15);
    User user2 = new User("Ron", 125);
    @BeforeSuite
    public void bs(){
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        SetUpProvider.setUpStorage(list);
    }

    @Test
    public void testSaveNewUser() {
        User user = new User("John Doe", 40);
        user.saveNewUser(30, 50000);
        List<User> list1 = getStorage();
        Assert.assertTrue(list1.stream()
                .anyMatch(u -> "John Doe".equals(user.getName())));


    }

    @AfterSuite
    public void as(){
        CleanUpProvider.cleanUpStorage();
    }

}
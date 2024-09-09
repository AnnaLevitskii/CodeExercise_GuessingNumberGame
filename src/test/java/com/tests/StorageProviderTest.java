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
import static com.core.providers.StorageProvider.setStorage;
import static org.testng.Assert.*;

public class StorageProviderTest {
    User user1 = new User("Jane", 15);
    User user2 = new User("Ron", 25);
    @BeforeSuite
    public void bs(){
        List<User> list = new ArrayList<>();
        list.add(user1);
        list.add(user2);
        SetUpProvider.setUpStorage(list);
    }

    @Test
    public void testGetStorage() {
        List<User> list1 = getStorage();
        Assert.assertTrue(list1.contains(user1));
        Assert.assertTrue(list1.contains(user2));
        Assert.assertEquals(list1.size(), 2);
    }

    @Test
    public void testSetStorage() {
        User user3 = new User("Linda", 66);
        User user4 = new User("Keren", 90);
        List<User> list2 = new ArrayList<>();
        list2.add(user3);
        list2.add(user4);
        setStorage(list2);
        List<User> list1 = getStorage();
        System.out.println(list1);
        Assert.assertTrue(list1.contains(user3));
        Assert.assertTrue(list1.contains(user4));
        Assert.assertEquals(list1.size(), 2);
    }

    @AfterSuite
    public void as(){
        CleanUpProvider.cleanUpStorage();
    }
}
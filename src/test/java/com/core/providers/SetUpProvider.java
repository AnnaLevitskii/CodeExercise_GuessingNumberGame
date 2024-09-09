package com.core.providers;

import com.core.models.User;

import java.util.List;
import java.util.Map;

import static com.core.providers.StorageProvider.getStorage;
import static com.core.providers.StorageProvider.setStorage;

public class SetUpProvider {
    static List<User> dataFromStorageSetUp;
    public static void setUpStorage(List<User> list){
        dataFromStorageSetUp = getStorage();
        System.out.println(dataFromStorageSetUp);
        setStorage(list);
    }
}

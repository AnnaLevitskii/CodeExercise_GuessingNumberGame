package com.core.providers;


import com.core.models.User;

import java.util.List;

import static com.core.providers.SetUpProvider.dataFromStorageSetUp;
import static com.core.providers.StorageProvider.getStorage;
import static com.core.providers.StorageProvider.setStorage;

public class CleanUpProvider {


    public static void cleanUpStorage(){
        setStorage(dataFromStorageSetUp);
    }

}

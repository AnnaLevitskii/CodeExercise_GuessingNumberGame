package com.core.providers;

import java.util.Arrays;
import java.util.stream.Collectors;

public class MethodProvider {
    public static String secretNumberOneWrong(String num){
        for (int i = 0; i<10; i++){
            if(!num.contains(i+"")){
                String strI = i+" ";
                StringBuilder sb = new StringBuilder(num);
                sb.setCharAt(i, strI.charAt(0));
                String str = sb.toString();
                return str.substring(0,4);
            }
        }
        return null;
    }
    public static String secretNumberWrongPlace(String num){
        StringBuilder sb = new StringBuilder(num);
        char c = num.charAt(0);
        sb.setCharAt(0, num.charAt(1));
        sb.setCharAt(1, c);
        String str = sb.toString();
        return str.substring(0,4);
    }

}

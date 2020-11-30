package com.fortuneloan.tenmileslotus.utils;

public abstract class RandomUtils {
    /**
     * 产生m~n之间的随机数m
     */
    public static int random(int m,int n){
        int i = (int) (Math.random()*(n-m)+m);
        return i;
    }
}

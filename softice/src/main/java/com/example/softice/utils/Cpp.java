package com.example.softice.utils;

public class Cpp {

    static {
        System.loadLibrary("sft");
    }

    public static native String baseApi();

    public static final String baseApi = baseApi();
}

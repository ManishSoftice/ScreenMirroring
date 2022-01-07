package com.example.softice.utils;

public class Cpp {

    public static final String baseApi = baseApi();

    static {
        System.loadLibrary("sft");
    }

    public static native String baseApi();
}

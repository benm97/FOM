package com.example.fom;

public class Data {
    private static Data mInstance= null;

    protected Data(){}

    public int step=0;
    public String path1 = "";
    public String path2 = "";
    public String path3 = "";
    public String name = "";
    public String mail = "";
    public String phone = "";
    public String date = "";
    public String question1 = "";
    public String question2 = "";
    public String question3 = "";


    public static synchronized Data getInstance() {
        if(null == mInstance){
            mInstance = new Data();
        }
        return mInstance;
    }
}


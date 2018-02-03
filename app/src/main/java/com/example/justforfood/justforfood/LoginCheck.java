package com.example.justforfood.justforfood;

/**
 * Created by 972791 on 5/6/2016.
 */
public class LoginCheck {
    private static int FLAG = 0;
    private static String name;
    public static String textchange = "Sign Out";
    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        LoginCheck.name = name;
    }


    public static int getFLAG() {
        return FLAG;
    }

    public static void setFLAG(int FLAG) {
        LoginCheck.FLAG = FLAG;
    }


}

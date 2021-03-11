package com.codecool.dungeoncrawl.logic;

import java.util.ArrayList;

public class DevelopersNames {

    private static final ArrayList<String> developersNames = new ArrayList<>();

    static {
        developersNames.add("Dominik");
        developersNames.add("Piotr");
        developersNames.add("Kuba");
        developersNames.add("Asia");

    }

    public static ArrayList<String> getDevelopersNames(){
        return developersNames;
    }
}

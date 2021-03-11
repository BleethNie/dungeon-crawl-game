package com.codecool.dungeoncrawl.gui;

import javafx.scene.text.Text;

public class StatusLine extends Text {

    public StatusLine(String message){
        this.setText(message);
        this.setWrappingWidth(190);
    }


    public void setMessage(String message) {

        this.setText(message);
    }
}

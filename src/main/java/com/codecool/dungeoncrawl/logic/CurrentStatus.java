package com.codecool.dungeoncrawl.logic;

import java.util.function.Consumer;

public class CurrentStatus {
    private static  CurrentStatus STATUS = null;
    private Consumer<String> updatingMessage;

    private CurrentStatus(){

    }

    public static CurrentStatus getInstance() {
        if (STATUS == null) {
            STATUS = new CurrentStatus();
        }
        return STATUS;
    }

    public void setStatus(String newMessage){
        updatingMessage.accept(newMessage);
    }

    public void bind(Consumer<String> updatingMessage) {
        this.updatingMessage = updatingMessage;
    }


}

package com.intensivao.dslist.exceptions;

public class GameTitleAlreadyExitsException extends RuntimeException{
    public GameTitleAlreadyExitsException(String message) {
        super(message);
    }
}

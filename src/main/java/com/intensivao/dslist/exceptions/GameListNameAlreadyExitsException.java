package com.intensivao.dslist.exceptions;

public class GameListNameAlreadyExitsException extends RuntimeException{
    public GameListNameAlreadyExitsException(String message) {
        super(message);
    }
}

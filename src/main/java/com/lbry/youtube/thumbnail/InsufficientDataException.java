package com.lbry.youtube.thumbnail;

/**
 * Created by niko on 3/2/17.
 */
public class InsufficientDataException extends RuntimeException {
    public InsufficientDataException(String message) {
        super(message);
    }
}

package org.hacklist.controller;

/**
 * @author Aidar Shaifutdinov.
 */
public class ApiResponse<T> {

    private T response;

    public ApiResponse(T response) {
        this.response = response;
    }

    public T getResponse() {
        return response;
    }

}

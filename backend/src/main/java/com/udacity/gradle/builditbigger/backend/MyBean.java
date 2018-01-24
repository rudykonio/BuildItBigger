package com.udacity.gradle.builditbigger.backend;

import rodionkonioshko.com.javajokelibrary.JokeProvider;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean
{

    private String myData;

    public String getData()
    {
        return JokeProvider.getInstance().getRandomJoke();
    }

    public void setData(String data)
    {
        myData = data;
    }
}
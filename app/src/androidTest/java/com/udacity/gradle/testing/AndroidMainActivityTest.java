package com.udacity.gradle.testing;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.udacity.gradle.builditbigger.MainActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AndroidMainActivityTest extends AndroidTestCase
{
    //waits for AsyncTask to finish and returns true if data loaded and false if not
    //this method has its own configured AsyncTask which uses the same logic but for testing
    @Test
    public void checkAsyncTask()
    {
        assertTrue(MainActivity.testAsyncTask());
    }
}

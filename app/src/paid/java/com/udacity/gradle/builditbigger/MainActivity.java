package com.udacity.gradle.builditbigger;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Pair;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import rodionkonioshko.com.androidjokelibrary.JokeActivity;


public class MainActivity extends AppCompatActivity
{

    public static String joke = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.buttonJoke);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (joke != null && !joke.isEmpty())
                {
                    Intent intent = new Intent(MainActivity.this, JokeActivity.class);
                    intent.putExtra(JokeActivity.JOKE, joke);
                    startActivity(intent);
                } else
                {
                    Toast.makeText(MainActivity.this, "server offline", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        new EndpointsAsyncTask().execute(new Pair<Context, String>(null, "getJoke"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings)
        {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


        @SuppressLint("StaticFieldLeak")
        static class EndpointsAsyncTask extends AsyncTask<Pair<Context, String>, Void, String>
        {
            private MyApi myApiService = null;

            @Override
            protected String doInBackground(Pair<Context, String>... params)
            {
                if (myApiService == null)
                {  // Only do this once
                    MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                            new AndroidJsonFactory(), null)
                            // options for running against local devappserver
                            // - 10.0.2.2 is localhost's IP address in Android emulator
                            // - turn off compression when running against local devappserver
                            .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                            .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer()
                            {
                                @Override
                                public void initialize(AbstractGoogleClientRequest<?> abstractGoogleClientRequest) throws IOException
                                {
                                    abstractGoogleClientRequest.setDisableGZipContent(true);
                                }
                            });
                    // end options for devappserver

                    myApiService = builder.build();
                }

                try
                {
                    return myApiService.getJoke().execute().getData();
                } catch (IOException e)
                {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String result)
            {
                joke = result;
            }

        }

        public static boolean testAsyncTask()
        {
            String res = "";
            try
            {

                res = new EndpointsAsyncTask().execute(new Pair<Context, String>(null, "getJoke")).get();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            } catch (ExecutionException e)
            {
                e.printStackTrace();
            }

            //checks if the asyncTask result is empty or null,will return false that will make our test fail
            //otherwise it retrieved something and that something can only be our joke.
            if (res == null || res.isEmpty())
                return false;
            return true;

        }
}



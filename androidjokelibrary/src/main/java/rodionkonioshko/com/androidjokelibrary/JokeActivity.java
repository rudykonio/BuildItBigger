package rodionkonioshko.com.androidjokelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity
{
    public static final String JOKE = "joke";
    private String joke;
    private String currentJoke = null;
    private static final String CURRENT_JOKE = "currentJoke";


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        TextView textView = findViewById(R.id.jokeTextView);
        if (savedInstanceState != null)
        {

            if (savedInstanceState.containsKey(CURRENT_JOKE))
            {
                currentJoke = savedInstanceState.getString(CURRENT_JOKE);
            }
            textView.setText(currentJoke);

        } else
        {
            if (!(getIntent().getStringExtra(JOKE) != null && getIntent().getStringExtra(JOKE).isEmpty()))
            {
                joke = getIntent().getStringExtra(JOKE);
            }

            textView.setText(joke);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if (joke != null)
            outState.putString(CURRENT_JOKE, joke);
        else if (currentJoke != null)
            outState.putString(CURRENT_JOKE, currentJoke);
    }
}

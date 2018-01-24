package rodionkonioshko.com.javajokelibrary;

import java.util.ArrayList;
import java.util.Random;

public class JokeProvider
{
    private static final JokeProvider ourInstance = new JokeProvider();
    //json jokes file starts at 0
    private static final int ID_MIN = 0;
    //json jokes file end at 8
    private static final int ID_MAX = 8;
    //jokes ArrayList
    static ArrayList<Joke> jokes = null;

    public static JokeProvider getInstance()
    {
        Joke.initializeJokes();
        return ourInstance;
    }

    private JokeProvider()
    {
    }

    public String getRandomJoke()
    {
        Random random = new Random();
        int randomNumber = random.nextInt(ID_MAX - ID_MIN + 1) + ID_MIN;
        return jokes.get(randomNumber).joke;
    }


    private static class Joke
    {
        private String joke;

        public Joke(String joke)
        {
            this.joke = joke;
        }

        //thanks to stackoverflow for those awesome jokes
        public static void initializeJokes()
        {
            jokes = new ArrayList<>();
            jokes.add(new Joke(
                    "Two bytes meet. The first byte asks, \"Are you ill?\"" +
                            "\n" +
                            "The second byte replies, \"No, just feeling a bit off.\""));

            jokes.add(new Joke("two strings walk into a bar and sit down. The bartender says,So what'll it be?" +
                    "\n" +
                    "The first string says, I think I'll have a beer dslkjadlskahjsakjdh2ui2u1" +
                    "\n" +
                    "Please excuse my friend, the second string says, He isn't null-terminated."));

            jokes.add(new Joke(
                    "Two threads walk into a bar. The barkeeper looks up and yells," + "\n" +
                            "\"hey, i want don't any conditions race like time last!\""));

            jokes.add(new Joke("Two fish are sitting in a tank. One looks over at the other and says:" + "\n" + "Hey, do you know how to drive this thing?"));

            jokes.add(new Joke("Moses had the first tablet that could connect to the cloud."));

            jokes.add(new Joke("Q: how many programmers does it take to change a light bulb?" + "\n" +
                    "A: none, that's a hardware problem"));

            jokes.add(new Joke("Q:Whats the object-oriented way to become wealthy?" + "\n" +
                    "A: Inheritance"));

            jokes.add(new Joke("To understand what recursion is, you must first understand recursion."));

            jokes.add(new Joke("A SQL query goes into a bar, walks up to two tables and asks,Can I join you?"));
        }


    }
}

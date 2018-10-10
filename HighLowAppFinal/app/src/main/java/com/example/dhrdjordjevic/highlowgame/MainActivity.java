package com.example.dhrdjordjevic.highlowgame;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int[] mDiceImages;
    private int mPrevThrow;
    private ListView mListThrows;
    private FloatingActionButton mButtonHigher;
    private FloatingActionButton mButtonLower;
    private ImageButton mDiceButton;
    private TextView mHighScoreText;
    private TextView mScoreText;

    private boolean mIsLower = false;

    private int mScore = 0;
    private int mHighScore = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing the variables
        mListThrows = findViewById(R.id.dice_throws);
        mButtonHigher = findViewById(R.id.button_higher);
        mButtonLower = findViewById(R.id.button_lower);
        mDiceButton = findViewById(R.id.imageButton);
        mHighScoreText = findViewById(R.id.highscore);
        mScoreText = findViewById(R.id.score);


        //Throwing all the images in an Array so I could easely acces them later on.
        mDiceImages = new int[] {R.drawable.d1, R.drawable.d2, R.drawable.d3,
                                 R.drawable.d4, R.drawable.d5, R.drawable.d6};

        BasicInit();

        mDiceButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                int nextInt = GetRandomInt();
                mDiceButton.setImageResource(mDiceImages[nextInt]);

                CalculateLowerOrHigher(mPrevThrow, nextInt);
                System.out.println(mScore);
            }
        });

        mButtonLower.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mIsLower = true;
            }
        });

        mButtonHigher.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mIsLower = false;
            }
        });

    }

    //sets the first random on the go and remembers it.
    public void BasicInit()
    {
        int randomInt = GetRandomInt();
        mPrevThrow = randomInt;
        mDiceButton.setImageResource(mDiceImages[mPrevThrow]);
    }
    // Gets a pseudo random int ranging between 0 to 5
    public int GetRandomInt()
    {
        int randomNumber = new Random().nextInt(6);

        return randomNumber;
    }

    public void CalculateLowerOrHigher(int previous, int next)
    {
        if(mIsLower && (previous > next))
        {
            mScore++;
        } else if (mIsLower && previous < next)
        {
            mScore = 0;
        } else if(!mIsLower && previous < next)
        {
            mScore++;
        } else if (!mIsLower && previous > next)
        {
            mScore = 0;
        }
    }


}

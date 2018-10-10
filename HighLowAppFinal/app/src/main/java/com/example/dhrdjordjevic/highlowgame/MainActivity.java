package com.example.dhrdjordjevic.highlowgame;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
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

    private ArrayAdapter<String> mAdapter;
    private List<String> mItems;

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

        mScoreText.setText("SCORE: " + mScore);
        mHighScoreText.setText("HIGHSCORE: " + mHighScore);

        mItems = new ArrayList<>();

        UpdateUI();

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
                AddThrows(nextInt);
            }
        });

        mButtonLower.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mIsLower = true;
                Toast.makeText(MainActivity.this, "Next throw will be lower!", Toast.LENGTH_SHORT).show();

            }
        });

        mButtonHigher.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mIsLower = false;
                Toast.makeText(MainActivity.this, "Next throw will be higher!", Toast.LENGTH_SHORT).show();
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
            Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show();
        } else if (mIsLower && previous < next)
        {
            mScore = 0;
            Toast.makeText(this, "You Suck!", Toast.LENGTH_SHORT).show();
        } else if(!mIsLower && previous < next)
        {
            mScore++;
            Toast.makeText(this, "Good Job!", Toast.LENGTH_SHORT).show();
        } else if (!mIsLower && previous > next)
        {
            mScore = 0;
            Toast.makeText(this, "You Suck!", Toast.LENGTH_SHORT).show();
        }

        mScoreText.setText("SCORE: " + mScore);
        UpdateUI();

        if(mScore > mHighScore)
        {
            mHighScore = mScore;
            mHighScoreText.setText("HIGHSCORE: " + mHighScore);
            UpdateUI();
        }
        UpdateUI();
    }

    private void UpdateUI()
    {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mItems);
            mListThrows.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void AddThrows(int dice)
    {
        int diceThrow = dice + 1;

        String newItem = "" + diceThrow;

        if(!newItem.isEmpty())
        {
            mItems.add(newItem);
            UpdateUI();
        }
    }


}

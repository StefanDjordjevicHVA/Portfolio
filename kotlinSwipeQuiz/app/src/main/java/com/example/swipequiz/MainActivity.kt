package com.example.swipequiz

import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.contentValuesOf
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.nio.file.attribute.PosixFileAttributeView

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>()
    private val questionAdapter = QuestionAdapter(questions)

    private lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        constraintLayout = findViewById(R.id.constraintLayout)
        initViews()
    }

    private fun initViews()
    {
        rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))

        for (i in Question.QUESTIONS.indices)
        {
            questions.add(Question(Question.QUESTIONS[i], Question.ANSWERS[i]))
        }
        questionAdapter.notifyDataSetChanged()

        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper
    {
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean
            {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                if(direction == ItemTouchHelper.LEFT) onSwipeLeft()
                else if(direction == ItemTouchHelper.RIGHT) onSwipeRight(position)
            }
        }
        return ItemTouchHelper(callback)
    }

    private fun onSwipeLeft()
    {
        var snack = Snackbar.make(constraintLayout, "LEFT", Snackbar.LENGTH_LONG)
        snack.show()
    }

    private fun onSwipeRight(Pos: Int)
    {

    }
}

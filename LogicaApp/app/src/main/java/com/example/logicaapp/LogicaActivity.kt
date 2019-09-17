package com.example.logicaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_logica_app.*

class LogicaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logica_app)

        btnSubmit.setOnClickListener {onAwnserClick()}
    }

    private fun onAwnserCorrect()
    {
        Toast.makeText(this, R.string.correct, Toast.LENGTH_SHORT).show()
    }

    private fun onAwnserIncorrect()
    {
        Toast.makeText(this, R.string.incorrect, Toast.LENGTH_SHORT).show()
    }

    private fun onAwnserClick()
    {
        if(tiAwnser1.toString().equals("T")) onAwnserCorrect()
    }



}

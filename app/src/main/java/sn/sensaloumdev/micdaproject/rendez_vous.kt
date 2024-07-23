package sn.sensaloumdev.micdaproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class rendez_vous : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rendez_vous)

        val greetings = findViewById<TextView>(R.id.tvWelcome)
        val inputField = findViewById<EditText>(R.id.tvInputName)
        val submitButton = findViewById<Button>(R.id.tvSubmit)

        submitButton.setOnClickListener{

            val enteredName = inputField.text.toString()
        val message = "Welcome $enteredName"
        greetings.text = message

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
            

        }
    }
}
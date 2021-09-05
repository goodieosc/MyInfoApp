package au.com.myinfoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.room.*
import au.com.myinfoapp.roomdatabase.*
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDB()
        savebutton.setOnClickListener { addDbEntry(nameBox.text.toString(), emailBox.text.toString(), phoneBox.id, dobBox.id, "Male") }
        queryDbEntires()

    }








package au.com.myinfoapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Room.MASTER_TABLE_NAME
import androidx.sqlite.db.SupportSQLiteDatabase
import au.com.myinfoapp.roomdatabase.DatabaseDao
import au.com.myinfoapp.roomdatabase.DatabaseEntity1
import au.com.myinfoapp.roomdatabase.localDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.flow.Flow
import java.io.IOException



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        createDB()
        savebutton.setOnClickListener { addDbEntry(nameBox.text.toString(), emailBox.text.toString(),phoneBox.id, dobBox.id,"Male")}
        queryDbEntires()

}


    private lateinit var dao: DatabaseDao

    fun createDB(){
        val db = Room.inMemoryDatabaseBuilder(this, localDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        var dao = db.DatabaseDao

    }

    fun addDbEntry(usersName: String,emailAddress: String, phoneNumber: Int, dateOfBirth: Int, gender: String ) {
        val userDetails = DatabaseEntity1(0L ,usersName,emailAddress,phoneNumber,dateOfBirth,gender)
        dao.insert(userDetails)

    }

    fun queryDbEntires() {
        val usersList = dao.getAllUsers()
        Log.e("Users in Room DB",usersList.toString())
    }

}
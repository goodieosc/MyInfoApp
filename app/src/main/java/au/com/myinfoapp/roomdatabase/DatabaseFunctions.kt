package au.com.myinfoapp.roomdatabase

import android.app.Application
import android.util.Log
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class DatabaseTest {

private lateinit var dao: DatabaseDao
private lateinit var db: LocalDatabase


    @Before
    fun createDB() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        db = Room.inMemoryDatabaseBuilder(this, LocalDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        dao = db.DatabaseDao

    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun addDbEntry(usersName: String, emailAddress: String, phoneNumber: Int, dateOfBirth: Int, gender: String) {
        val userDetails = UserInfoTable(0L, usersName, emailAddress, phoneNumber, dateOfBirth, gender)
        dao.insert(userDetails)

    }

    @Throws(Exception::class)
    fun queryDbEntires() {
        val usersList = dao.getAllUsers()
        Log.e("Users in Room DB", usersList.toString())
    }

}
package au.com.myinfoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.room.*
import au.com.myinfoapp.roomdatabase.*
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_main.*

import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Create the Room database
        createDB()



        // reference thje view using findviewby id or view binding or databinding
        findViewById<TextView>(R.id.dobBoxChooser).setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            builder.setTitleText("Select date of birth")

            val constraintsBuilder = CalendarConstraints.Builder()
            val calendar = Calendar.getInstance()
            val dateValidator = DateValidatorPointForward.from(calendar.timeInMillis)
            constraintsBuilder.setValidator(dateValidator)
            builder.setCalendarConstraints(constraintsBuilder.build())

            val picker = builder.build()
            picker.show(supportFragmentManager, picker.toString())

            picker.addOnPositiveButtonClickListener {
                // Get the offset from our timezone and UTC.
                val timeZoneUTC = TimeZone.getDefault()
                // It will be negative, so that's the -1
                val offsetFromUTC = timeZoneUTC.getOffset(Date().time) * -1
                // Create a date format, then a date object with our offset
                val simpleFormat = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                val date = Date(it + offsetFromUTC)
                findViewById<TextView>(R.id.dobBox).setText((simpleFormat.format(date)))


        }


            //Save values to DB when save button is clicked.
            savebutton.setOnClickListener {

                //Set variables to the values input into the boxes in the layout
                var usersName = findViewById<TextView>(R.id.nameBox).text.toString()
                var usersEmail = findViewById<TextView>(R.id.emailBox).text.toString()
                var usersPhone = findViewById<TextView>(R.id.phoneBox).text.toString()
                var usersDob = findViewById<TextView>(R.id.dobBox).text.toString()
                var gender = if (male.isChecked) "Male" else "Female"

                //Add user details into database
                addDbEntry(usersName, usersEmail, usersPhone.toInt(), usersDob, gender)
            }

            //Query DB when query button is clicked
            queryButton.setOnClickListener { queryAllDbEntries() }


        }
    }

    lateinit var dao: DatabaseDao


    //Function to create room database
    fun createDB() {

        val db: LocalDatabase = Room.inMemoryDatabaseBuilder(this, LocalDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        dao = db.DatabaseDao

    }

    //Function to add user profile information into database
    fun addDbEntry(
            usersName: String,
            emailAddress: String,
            phoneNumber: Int,
            dateOfBirth: String,
            gender: String) {
        val userDetails = UserInfoTable(0L, usersName, emailAddress, phoneNumber, dateOfBirth, gender)
        dao.insert(userDetails)
        Log.e("Users in Room DB", userDetails.toString())

    }

    //Function to query last DB entry.
    fun queryDbEntries() {
        val usersList = dao.getUser()
        Log.e("Users in Room DB", usersList.toString())
    }


    //Function to list all entries in table
    fun queryAllDbEntries() {
        val usersList = dao.getAllUsers().listIterator()

        for (i in usersList) {
            Log.e("Users in Room DB", i.toString())  //To export a particular column use i.columnId.toSting()
        }

    }
}











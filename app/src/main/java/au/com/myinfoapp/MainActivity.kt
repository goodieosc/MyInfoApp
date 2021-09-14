package au.com.myinfoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.room.*
import au.com.myinfoapp.databinding.ActivityMainBinding
import au.com.myinfoapp.recycler_view.UserProfilePost
import au.com.myinfoapp.roomdatabase.*
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.DateValidatorPointForward
import com.google.android.material.datepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_main.*

import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Configure view binding
        //val binding = ActivityMainBinding.inflate(layoutInflater)

        //Create the Room database
        createDB()

        // reference thje view using findviewby id or view binding or databinding
        dobBoxChooser.setOnClickListener() {
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
                dobBox.editText?.setText((simpleFormat.format(date)))

            }


            //Save values to DB when save button is clicked.
            savebutton.setOnClickListener {

                //Set variables to the values input into the boxes in the layout
                var usersName = nameBox.editText?.text.toString()
                var usersEmail = emailBox.editText?.text.toString()
                var usersPhone = phoneBox.editText?.text.toString()
                var usersDob = dobBox.editText?.text.toString()
                var gender = if (male.isChecked) "Male" else "Female"

                //Add user details into database
                addDbEntry(usersName, usersEmail, usersPhone.toInt(), usersDob, gender)
            }


            //Query and log all DB entries and then move to the UserListActivity
            listUsersButton.setOnClickListener {
                queryAllDbEntries()
                try {
                    val intent = Intent(this, UserListActivity::class.java)
                    startActivity(intent)
                } catch(ex:Exception){
                    Log.e("Log", "Intent failure", ex)
                }

            }

        }
    }

    lateinit var dao: DatabaseDao


    //Function to create room database
    fun createDB() {

        val db: LocalDatabase = Room.inMemoryDatabaseBuilder(applicationContext, LocalDatabase::class.java)
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
    fun queryAllDbEntries() :ArrayList<UserProfilePost> {
        val usersList = dao.getAllUsers().listIterator()

        val list = ArrayList<UserProfilePost>()

        for (i in usersList) {
            Log.e("Users in Room DB", i.toString())  //To export a particular column use i.columnId.toSting()

            list.add(
                    UserProfilePost(
                            i.usersName,
                            i.emailAddress,
                            i.phoneNumber.toString(),
                            i.dateOfBirth,
                            i.gender
                            //i.photo
                    )
            )

        }
        return list

    }
}











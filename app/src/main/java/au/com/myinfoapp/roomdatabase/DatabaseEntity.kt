package au.com.myinfoapp.roomdatabase

import android.provider.ContactsContract
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


//Configures the columns in a table.
// Essentially 'Entity' is a table.
// Create new data classes for each table needed.

@Entity(tableName = "User_Info_Table")
data class DatabaseEntity1(

    @PrimaryKey(autoGenerate = true)
    var userID: Long = 0L,

    @ColumnInfo(name = "Users_Name")
    val usersName: String,

    //Change type from string to email later
    @ColumnInfo(name = "Email_Address")
    var emailAddress: String,

    @ColumnInfo(name = "Phone_Number")
    var phoneNumber: Int,

    @ColumnInfo(name = "Date_Of_Birth")
    var dateOfBirth: Int,

    @ColumnInfo(name = "Gender")
    var gender: String


)
package au.com.myinfoapp.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//Database queries
//Update DatabaseEntity1 to the class for the table needed to be accessed.


@Dao
interface DatabaseDao {

    @Insert
    fun insert(user: DatabaseEntity1)

    @Update
    fun update(user: DatabaseEntity1)

    @Query("SELECT * from User_Info_Table WHERE Users_Name = :key")
    fun get(key: Long): DatabaseEntity1?

    @Query("DELETE FROM User_Info_Table")
    fun clear()

    @Query("SELECT * FROM User_Info_Table ORDER BY Users_Name DESC LIMIT 1")
    fun getUser(): DatabaseEntity1?

    @Query("SELECT * FROM User_Info_Table ORDER BY Users_Name DESC")
    fun getAllUsers(): LiveData<List<DatabaseEntity1>>



}
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
    fun insert(user: UserInfoTable)

    @Update
    fun update(user: UserInfoTable)

    @Query("SELECT * from User_Info_Table WHERE Users_Name = :key")
    fun get(key: Long): UserInfoTable?

    @Query("DELETE FROM User_Info_Table")
    fun clear()

    @Query("SELECT * FROM User_Info_Table ORDER BY Users_Name DESC LIMIT 1")
    fun getUser(): UserInfoTable?

    @Query("SELECT * FROM User_Info_Table ORDER BY Users_Name DESC")
    fun getAllUsers(): LiveData<List<UserInfoTable>>



}
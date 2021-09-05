package au.com.myinfoapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserInfoTable::class], version = 1, exportSchema = false)
abstract class LocalDatabase : RoomDatabase() {

    abstract val DatabaseDao: DatabaseDao
    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null
        fun getInstance(context: Context): LocalDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                            LocalDatabase::class.java,
                        "user_info_database"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance


            }
        }
    }


}
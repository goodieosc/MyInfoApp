package au.com.myinfoapp.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [DatabaseEntity1::class], version = 1, exportSchema = false)
abstract class localDatabase : RoomDatabase() {

    abstract val DatabaseDao: DatabaseEntity1
    companion object {
        @Volatile
        private var INSTANCE: localDatabase? = null
        fun getInstance(context: Context): localDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        localDatabase::class.java,
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
package au.com.myinfoapp

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import au.com.myinfoapp.recycler_view.RecyclerAdapter
import au.com.myinfoapp.recycler_view.TopSpacingItemDecoration
import au.com.myinfoapp.recycler_view.UserProfilePost
import au.com.myinfoapp.roomdatabase.DatabaseDao
import au.com.myinfoapp.roomdatabase.LocalDatabase
import kotlinx.android.synthetic.main.activity_user_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class UserListActivity : AppCompatActivity() {

    lateinit var userAdapter: RecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)

        CoroutineScope(Dispatchers.IO).launch {
            createDataSet()
        }
    }

//        private fun addDataSet(){
//        val data = DataSource.createDataSet()
//        userAdapter.submitList(data)
//    }

    suspend fun createDataSet() {
        initRecyclerView()
        val DataSet = addDataSet2()
        userAdapter.submitList(DataSet)

    }

    private suspend fun addDataSet2(): ArrayList<UserProfilePost> {
        Log.e("Log", "Get DB")
        val db: LocalDatabase = LocalDatabase.getInstance(applicationContext)
        val dao = db.DatabaseDao
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
                            i.gender)
            )
        }
        return list
    }

    //Setup RecyclerView
    private fun initRecyclerView() {
        recycler_view.apply {
            layoutManager = LinearLayoutManager(this@UserListActivity)
            val topSpacingDecoration = TopSpacingItemDecoration(30)
            addItemDecoration(topSpacingDecoration)
            userAdapter = RecyclerAdapter()
            adapter = userAdapter
        }
    }
}



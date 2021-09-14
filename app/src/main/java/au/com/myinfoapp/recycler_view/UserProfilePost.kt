package au.com.myinfoapp.recycler_view

import kotlinx.android.synthetic.main.activity_main.*

data class UserProfilePost(

    //var usersImage: String,

    var usersName: String,

    var usersEmail: String,

    var usersPhone: String,

    var usersDob: String,

    var gender: String

//    var photo: ByteArray?


) {

    override fun toString(): String {
        return "UserProfile(usersName='$usersName', usersEmail='$usersEmail', usersPhone='$usersPhone', usersDob='$usersDob', gender='$gender')"
    }


}



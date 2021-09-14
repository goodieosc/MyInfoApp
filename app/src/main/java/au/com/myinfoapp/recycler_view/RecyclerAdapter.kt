package au.com.myinfoapp.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import au.com.myinfoapp.R
import au.com.myinfoapp.roomdatabase.LocalDatabase
import au.com.myinfoapp.roomdatabase.UserInfoTable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.layout_user_profile_list.view.*

class RecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val TAG: String = "AppDebug"

    private var items: List<UserProfilePost> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_user_profile_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {

            is UserViewHolder -> {
                holder.bind(items.get(position))
            }

        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(userList: List<UserProfilePost>){
        items = userList
    }

    class UserViewHolder
    constructor(
            itemView: View
    ): RecyclerView.ViewHolder(itemView){

        //val user_image = itemView.user_image
        val user_name = itemView.user_name
        val user_email = itemView.user_email
        val user_phone = itemView.user_phone
        val user_dob = itemView.user_dob
        val user_gender = itemView.user_gender

        fun bind(userProfilePost: UserProfilePost){

            val requestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
//                    .applyDefaultRequestOptions(requestOptions)
//                    .load(userProfilePost.usersImage)
//                    .into(user_image)
            user_name.setText(userProfilePost.usersName)
            user_email.setText(userProfilePost.usersEmail)
            user_phone.setText(userProfilePost.usersPhone)
            user_dob.setText(userProfilePost.usersDob)
            user_gender.setText(userProfilePost.gender)


        }

    }

}
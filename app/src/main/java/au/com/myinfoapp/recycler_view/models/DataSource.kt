package au.com.myinfoapp.recycler_view.models

import android.util.Log
import au.com.myinfoapp.recycler_view.UserProfilePost
import au.com.myinfoapp.roomdatabase.LocalDatabase


class DataSource{

    companion object{

        fun createDataSet(): ArrayList<UserProfilePost>{

            val list = ArrayList<UserProfilePost>()

            list.add(
                UserProfilePost(
                    //"https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png\"",
                    "You made it to the end of the course!\r\n\r\nNext we'll be building the REST API!",
                    "https://raw.githubusercontent.com/mitchtabian/Blog-Images/master/digital_ocean.png",
                    "Sally",
                    "Male",
                    "Male"
                )
            )
            list.add(
                UserProfilePost(
                   // "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/time_to_build_a_kotlin_app.png",
                    "The REST API course is complete. You can find the videos here: https://codingwithmitch.com/courses/build-a-rest-api/.",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/time_to_build_a_kotlin_app.png",
                    "mitch",
                    "Male",
                    "Male"

                )
            )

            list.add(
                UserProfilePost(
                  //  "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/coding_for_entrepreneurs.png",
                    "Justin has been producing online courses for YouTube, Udemy, and his website CodingForEntrepreneurs.com for over 5 years.",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/coding_for_entrepreneurs.png",
                    "John",
                    "45676",
                    "Male"
                )
            )
            list.add(
                UserProfilePost(
                  //  "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/freelance_android_dev_vasiliy_zukanov.png",
                    "Vasiliy has been a freelance android developer for several years. He also has some of the best android development courses I've had the pleasure of taking on Udemy.com.",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/freelance_android_dev_vasiliy_zukanov.png",
                    "Steven",
                    "134665",
                    "Male"
                )
            )
            list.add(
                UserProfilePost(
                  //  "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/freelance_android_dev_donn_felker.png",
                    "Freelancing as an Android developer with Donn Felker.\\r\\n\\r\\nDonn is also:\\r\\n\\r\\n1) Founder of caster.io\\r\\n\\r\\n2) Co-host of the fragmented podcast (fragmentedpodcast.com).",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/freelance_android_dev_donn_felker.png",
                    "Richelle",
                    "23456",
                    "Male"
                )
            )
            list.add(
                UserProfilePost(
                   // "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/work_life_balance.png",
                    "What kind of hobbies do software developers have? It sounds like many software developers don't have a lot of hobbies and choose to focus on work. Is that a good idea?",
                    "https://raw.githubusercontent.com/mitchtabian/Kotlin-RecyclerView-Example/json-data-source/app/src/main/res/drawable/work_life_balance.png",
                    "Jessica",
                    "34436",
                    "Male"
                )
            )

            return list
        }
    }
}
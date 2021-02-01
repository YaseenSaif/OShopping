package com.yemen.oshopping.setting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.yemen.oshopping.R
import com.yemen.oshopping.model.User
import com.yemen.oshopping.viewmodel.OshoppingViewModel

class ShowUserActivity : AppCompatActivity(),ShowUserFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_user)
        supportFragmentManager.beginTransaction()
            .add(R.id.user_fragment_containter,ShowUserFragment())
            .commit()
    }


    override fun onClicked(user: User) {
        val userInfo=User(user_id = user.user_id,first_name =user.first_name,
            last_name = user.last_name,email = user.email,phone_number = user.phone_number,
            image = user.image,details =  user.details,address=user.address)
        supportFragmentManager.beginTransaction()
            .replace(R.id.user_fragment_containter,UpdateUserFragment.newInstance(userInfo))
            .commit()
    }
}
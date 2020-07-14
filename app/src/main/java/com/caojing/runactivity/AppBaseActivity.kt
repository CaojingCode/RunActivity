package com.caojing.runactivity

import androidx.lifecycle.ViewModelProvider
import com.jijia.kotlinlibrary.base.BaseActivity

abstract class AppBaseActivity :BaseActivity(){

     val userViewModel: UserViewModel by lazy {
         ViewModelProvider(this).get(UserViewModel::class.java)
    }


}

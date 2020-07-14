package com.caojing.runactivity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.jijia.kotlinlibrary.net.RetrofitManage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PracticeActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {
            Log.d("", processData(getData()))

        }


    }

    private suspend fun getData(): String {
        return withContext(Dispatchers.IO) {
            return@withContext "hen_coder"
        }
    }

    private suspend fun processData(data: String): String {
        return withContext(Dispatchers.IO) {
            return@withContext data.split("_")
                .map { it.capitalize() }
                .reduce { acc, s -> acc + s }
        }
    }
}
package com.codelab

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.sdk27.coroutines.onClick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_save.onClick {
            saveData()
        }

        btn_refresh.onClick {
            refreshData()
        }
    }

    private fun refreshData() {
        database.use {
            val result = select(ManusiaContract.TABLE_HUMAN)
            val list = result.parseList(classParser<ManusiaContract>())
            Log.d("LIST", list.toString())
        }
    }

    private fun saveData() {
        database.use {
            insert(
                ManusiaContract.TABLE_HUMAN,
                ManusiaContract.NAME to edit_nama.text.toString(),
                ManusiaContract.ADDRESS to edit_address.text.toString()
            )
        }
    }
}

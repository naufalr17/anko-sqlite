package com.codelab

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseHelpers private constructor(context: Context) : ManagedSQLiteOpenHelper(context, "human.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            ManusiaContract.TABLE_HUMAN, true, ManusiaContract.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            ManusiaContract.NAME to TEXT,
            ManusiaContract.ADDRESS to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, p2: Int) {
        db?.dropTable(ManusiaContract.TABLE_HUMAN)
    }

    companion object {
        private var instance: MyDatabaseHelpers? = null

        fun getInstance(context: Context): MyDatabaseHelpers {
            if (instance == null) {
                instance = MyDatabaseHelpers(context)
            }

            return instance as MyDatabaseHelpers
        }
    }
}

val Context.database: MyDatabaseHelpers
    get() = MyDatabaseHelpers.getInstance(applicationContext)
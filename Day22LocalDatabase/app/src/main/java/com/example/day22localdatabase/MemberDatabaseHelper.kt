package com.example.day22localdatabase

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.day22localdatabase.model.ItemModel
import java.lang.Exception

const val TABLE_NAME = "member"

class MemberDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "example.db", null, 4) {

    private val TAG = "MemberDatabaseHelper"

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {}

    override fun onCreate(db: SQLiteDatabase?) {
        // 檢查 database 是否存在，若否，則創建一個
        val sql = "CREATE TABLE if not exists $TABLE_NAME (id integer PRIMARY KEY autoincrement, name text)"
        db?.execSQL(sql)
    }

    fun addName(name: String) {
        val values = ContentValues()
        values.put("name", name)
        writableDatabase.insert(TABLE_NAME, null, values)
    }

    @SuppressLint("Recycle")
    fun getNames(): ArrayList<ItemModel> {
        val cursor = readableDatabase.query(TABLE_NAME, arrayOf("id", "name"), null, null, null, null, null)
        val members = ArrayList<ItemModel>()

        try {
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val id = cursor.getInt(cursor.getColumnIndex("id"))
                    val item =  ItemModel(id, name)
                    members.add(item)
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {

        } finally {
            if (cursor != null && !(cursor.isClosed))
                cursor.close()
        }

        Log.d(TAG, "getNames: 總共有${cursor.count}筆資料")
        return members
    }
}
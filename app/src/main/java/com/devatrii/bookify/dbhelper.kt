package com.devatrii.bookify

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, VERSION) {

    companion object {
        const val DATABASE_NAME = "TRADE_MENTOR"
        const val VERSION = 1
        const val TABLE_NAME = "USER_INFO"
        const val USER_INFO_ID = "ID"
        const val USER_INFO_NAME = "NAME"
        const val USER_INFO_EMAIL = "EMAIL"
        const val USER_INFO_PASSWORD = "PASSWORD"
        const val USER_INFO_MOBILE_NUMBER = "MOBILE_NUMBER"

        // Create table query
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE $TABLE_NAME " +
                "($USER_INFO_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$USER_INFO_NAME TEXT," +
                "$USER_INFO_EMAIL TEXT," +
                "$USER_INFO_PASSWORD TEXT," +
                "$USER_INFO_MOBILE_NUMBER TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUserInfo(name: String?, email: String, password: String, mobileNumber: String) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(USER_INFO_NAME, name)
        value.put(USER_INFO_EMAIL, email)
        value.put(USER_INFO_PASSWORD, password)
        value.put(USER_INFO_MOBILE_NUMBER, mobileNumber)
        db.insert(TABLE_NAME, null, value)
    }
    fun addUserInfo(email: String, password: String, mobileNumber: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(USER_INFO_EMAIL, email)
            put(USER_INFO_PASSWORD, password)
            put(USER_INFO_MOBILE_NUMBER, mobileNumber)
        }
        db.insert(TABLE_NAME, null, values)
    }


    fun fetchUserInfo(): ArrayList<UserInfoModel> {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        val arrayList = ArrayList<UserInfoModel>()
        while (cursor.moveToNext()) {
            val model = UserInfoModel()
            model.id = cursor.getInt(0)
            model.name = cursor.getString(1)
            model.email = cursor.getString(2)
            model.password = cursor.getString(3)
            model.mobileNumber = cursor.getString(4)
            arrayList.add(model)
        }
        cursor.close()
        return arrayList
    }

    fun updateUserInfo(userInfoModel: UserInfoModel) {
        val db = this.writableDatabase
        val value = ContentValues()
        value.put(USER_INFO_NAME, userInfoModel.name)
        value.put(USER_INFO_EMAIL, userInfoModel.email)
        value.put(USER_INFO_PASSWORD, userInfoModel.password)
        value.put(USER_INFO_MOBILE_NUMBER, userInfoModel.mobileNumber)
        db.update(TABLE_NAME, value, "$USER_INFO_ID = ${userInfoModel.id}", null)
    }
}

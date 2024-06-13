package com.example.myapplicationdip.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplicationdip.classProj.PersonalInfo


class DBHelperPerson(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    private var db: SQLiteDatabase? = null

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                HEIGHT + " INTEGER," +
                WEIGHT + " INTEGER," +
                AGE + " INTEGER," +
                ACTIVITY + " REAL," +
                NORM_CALORIE + " INTEGER," +
                GENDER + " INTEGER," +
                TIME + " INTEGER," +
                INTERVAL + " INTEGER" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addName(weight: Int,
                height: Int,
                age: Int,
                activity: Float,
                gender: Int,
                calorie: Int,
                time: Int,
                interval : Int) {

        val values = ContentValues().apply {

            put(HEIGHT, height)
            put(WEIGHT, weight)
            put(AGE, age)
            put(ACTIVITY, activity)
            put(GENDER, gender)
            put(NORM_CALORIE, calorie)

            put(TIME, time)
            put(INTERVAL, interval)
        }
        db = this.writableDatabase

        db?.insert(TABLE_NAME, null, values)
        db?.close()
    }

    fun updateItem(
        weight: Int,
        height: Int,
        age: Int,
        activity: Float,
        gender: Int,
        calorie: Int,
        time: Int,
        interval : Int,
        id: Int
    ) {
        val selection = "$ID_COL=$id"
        val values = ContentValues().apply {

            put(HEIGHT, height)
            put(WEIGHT, weight)
            put(AGE, age)
            put(ACTIVITY, activity)
            put(GENDER, gender)
            put(NORM_CALORIE, calorie)

            put(TIME, time)
            put(INTERVAL, interval)
        }
        db = this.writableDatabase

        db?.update(TABLE_NAME, values, selection, null)
        db?.close()
    }

    fun getName(): Cursor? {

        db = this.readableDatabase
        return db?.rawQuery("SELECT * FROM $TABLE_NAME", null)

    }

    fun deleteAllDb() {
        db = this.writableDatabase
        db?.insert(TABLE_NAME, null, null)
        db?.close()
    }

    fun readDbData(): PersonalInfo {
        db = this.readableDatabase
        val cursor = db?.query(
            TABLE_NAME, null, null, null,
            null, null, null
        )

        val item = PersonalInfo()

        while (cursor?.moveToNext()!!) {
            val weightR = cursor.getInt(cursor.getColumnIndexOrThrow(WEIGHT))
            val heightR = cursor.getInt(cursor.getColumnIndexOrThrow(HEIGHT))
            val ageR = cursor.getInt(cursor.getColumnIndexOrThrow(AGE))
            val activityR = cursor.getFloat(cursor.getColumnIndexOrThrow(ACTIVITY))
            val genderR = cursor.getInt(cursor.getColumnIndexOrThrow(GENDER))
            val calorieR = cursor.getInt(cursor.getColumnIndexOrThrow(NORM_CALORIE))

            item.weight = weightR
            item.height = heightR
            item.age = ageR
            item.physActRatio = activityR
            item.gender = genderR
            item.norm_calorie = calorieR

            item.time = cursor.getInt(cursor.getColumnIndexOrThrow(TIME))
            item.interval = cursor.getInt(cursor.getColumnIndexOrThrow(INTERVAL))

        }
        cursor?.close()
        return item
    }


    companion object {
        private val DATABASE_NAME = "user_parameters"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "gfg_table"
        val ID_COL = "id"

        const val NORM_CALORIE = "norm_calorie"
        const val HEIGHT = "height"
        const val WEIGHT = "weight"
        const val AGE = "age"
        const val ACTIVITY = "activity"
        const val GENDER = "gender"

        const val TIME = "time"
        const val INTERVAL = "interval"
    }
}

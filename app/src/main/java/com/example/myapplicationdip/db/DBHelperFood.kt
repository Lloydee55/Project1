package com.example.myapplicationdip.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.myapplicationdip.classProj.FoodInfo
import com.example.myapplicationdip.classProj.PersonalInfo

class DBHelperFood(context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = ("CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY, " +
                PRODUCT + " TEXT," +
                CALORIES + " INTEGER," +
                PROTEINS + " INTEGER," +
                FATS + " INTEGER," +
                CARBOHYDRATES + " INTEGER," +
                GRAM + " INTEGER" + ")")

        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME)
        onCreate(db)
    }

    fun addName(product: String,
                proteins: Int,
                fats: Int,
                carbohydrates: Int,
                calories: Int,
                gram: Int){

        val values = ContentValues()

        values.put(PRODUCT, product)
        values.put(CALORIES, calories)
        values.put(PROTEINS, proteins)

        values.put(FATS, fats)
        values.put(CARBOHYDRATES, carbohydrates)
        values.put(GRAM, gram)

        val db = this.writableDatabase

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getName(): Cursor? {

        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null)

    }

    fun readDbData(context: Context): ArrayList<FoodInfo> {
        val db = DBHelperFood(context, null)

        val dataList = ArrayList<FoodInfo>()
        val item = FoodInfo()

        val cursor = db.getName()
        while (cursor?.moveToNext()!!) {
            item.product = cursor.getString(cursor.getColumnIndexOrThrow(PRODUCT))
            item.calories = cursor.getInt(cursor.getColumnIndexOrThrow(CALORIES))
            item.proteins = cursor.getInt(cursor.getColumnIndexOrThrow(PROTEINS))
            item.fats = cursor.getInt(cursor.getColumnIndexOrThrow(FATS))
            item.carbohydrates =
                    cursor.getInt(cursor.getColumnIndexOrThrow(CARBOHYDRATES))
            item.gram = cursor.getInt(cursor.getColumnIndexOrThrow(GRAM))

            dataList.add(item)
        }
        db.close()
        cursor?.close()
        return dataList
    }

    fun updateItem(product: String,
                   proteins: Int,
                   fats: Int,
                   carbohydrates: Int,
                   calories: Int,
                   gram: Int,
                   id:Int) {
        val selection = "$ID_COL=$id"
        val values = ContentValues().apply {

            put(PRODUCT, product)
            put(CALORIES, calories)
            put(PROTEINS, proteins)
            put(FATS, fats)
            put(CARBOHYDRATES, carbohydrates)
            put(GRAM, gram)

        }
        val db = this.writableDatabase

        db?.update(TABLE_NAME, values, selection, null)
        db?.close()
    }


    fun deleteAllDb() {
        val db = this.writableDatabase
        db?.insert(TABLE_NAME, null, null)
        db?.close()
    }


    companion object {
        private val DATABASE_NAME = "calories_person"
        private val DATABASE_VERSION = 1
        val TABLE_NAME = "caloriaes_BZHY"
        val ID_COL = "id"

        const val PRODUCT = "product"
        const val CALORIES = "calories"
        const val PROTEINS = "proteins"
        const val FATS = "fats"
        const val CARBOHYDRATES = "carbohydrates"
        const val GRAM = "gram"
    }
}
package com.Maulanazhrny.jobsheet25

import android.content.Context
import android.database.sqlite.SQLiteCantOpenDatabaseException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.io.FileOutputStream
import java.io.IOException
import java.sql.SQLException
import java.util.prefs.PreferencesFactory

class DBHelper(private val context: Context, factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, DATABASE_NAME, factory, DATABASE_VERSION) {
        companion object{
            private val DATABASE_NAME = "hoho.db"
            private val DATABASE_VERSION = 1
        }

    override fun onCreate(db: SQLiteDatabase?) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {


    }
    private fun checkDataBase(): Boolean {
        var checkDB: SQLiteDatabase? = null
        try {
            val myPath: String = context.getDatabasePath(DATABASE_NAME).toString()
            checkDB = SQLiteDatabase
                .openDatabase(
                    myPath, null,
                    SQLiteDatabase.OPEN_READONLY
                )
        } catch (e: SQLiteCantOpenDatabaseException) {
            Log.e("message", "" + e)
        }
        checkDB?.close()
        return checkDB !=null
    }
    private fun copyDatabase() {
        val iss = context.assets.open(DATABASE_NAME)
        val os = FileOutputStream(context.getDatabasePath(DATABASE_NAME))

        val buffer = ByteArray(1024)
        while (iss.read(buffer) > 0) {
            os.write(buffer)
        }
        os.flush()
        os.close()
        iss.close()
    }
    @Throws(SQLException::class)
    fun openDataBase() {
        val myPath: String = context.getDatabasePath(DATABASE_NAME).toString()
        var myDatabase: SQLiteDatabase? = null
        myDatabase = SQLiteDatabase
            .openDatabase(
                myPath, null,
                SQLiteDatabase.OPEN_READONLY
            )
    }
    fun createDataBase() {
        val dbExist = checkDataBase()
        if (!dbExist) {
            this.writableDatabase
            try {
                copyDatabase()
            }catch (e: IOException) {
                throw Error(
                    "Error Coppying database"
                )
            }
        }
    }
    fun getSiswa(): ArrayList<Siswa>? {
        val list = ArrayList<Siswa>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM hoho", null)

        if (cursor.moveToFirst()) {
            do {
                list.add(Siswa(cursor.getString(1), cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        cursor.close()
        return list;
    }

}
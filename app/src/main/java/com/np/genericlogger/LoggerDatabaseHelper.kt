package com.np.genericlogger

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LoggerDatabaseHelper (context: Context): SQLiteOpenHelper(context, DATABASE_NAME,null,
    DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "logger app.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "all_logger"
        private const val COLUMN_ID = "ID"
        private const val COLUMN_TITLE = "title"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery =
            "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY,$COLUMN_TITLE TEXT)"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertLogger(logger: Logger) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE, logger.title)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllLogger(): List<Logger> {
        val loggerList = mutableListOf<Logger>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))

            val logger = Logger(id, title)
            loggerList.add(logger)
        }
        cursor.close()
        db.close()
        return loggerList
    }

    fun updateLogger(note: Logger){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE,note.title)
        }
        val whereClause ="$COLUMN_ID=?"
        val whereArgs = arrayOf(note.id.toString())
        db.update(TABLE_NAME,values,whereClause,whereArgs)
        db.close()
    }

    fun getLoggerByID(noteId:Int):Logger {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=$noteId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))

        cursor.close()
        db.close()
        return Logger(id, title)
    }
}
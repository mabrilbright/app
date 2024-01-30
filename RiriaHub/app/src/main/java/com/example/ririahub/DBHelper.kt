package com.example.ririahub

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        const val DATABASE_NAME = "UserCredentials.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "user_table"
        const val COLUMN_ID = "id"
        const val COLUMN_USERNAME = "username"
        const val COLUMN_PASSWORD = "password"
    }

    // SQL statement to create the user_table
    private val CREATETABLEQUERY = """
        CREATE TABLE $TABLE_NAME (
            $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
            $COLUMN_USERNAME TEXT,
            $COLUMN_PASSWORD TEXT
        )
    """.trimIndent()

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATETABLEQUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // If you need to upgrade the database schema, you can handle it here
    }

    // Additional methods for CRUD operations can be added as needed

    // Example of adding a user
    fun addUser(username: String, password: String) {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_PASSWORD, password)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    // Method to retrieve user by username and password
    fun getUser(username: String, password: String): Cursor? {
        val db = this.readableDatabase
        val projection = arrayOf(COLUMN_ID, COLUMN_USERNAME, COLUMN_PASSWORD)
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)

        return db.query(
            TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )
    }
    fun checkUserCredentials(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val projection = arrayOf(COLUMN_ID)
        val selection = "$COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)

        val cursor = db.query(
            TABLE_NAME,
            projection,
            selection,
            selectionArgs,
            null,
            null,
            null
        )

        val userExists = cursor.count > 0
        cursor.close()
        db.close()
        return userExists
    }

    }

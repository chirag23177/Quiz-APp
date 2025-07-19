package com.example.quizapp.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LeaderboardDBHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
        companion object {
            const val DATABASE_NAME = "leaderboard.db"
            const val DATABASE_VERSION = 1
            const val TABLE_NAME = "leaderboard"
            const val COLUMN_NAME = "name"
            const val COLUMN_SCORE = "score"
        }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_NAME TEXT,
                $COLUMN_SCORE INTEGER
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldversion: Int, newVersion: Int){
        // Handle database upgrade logic if needed
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUserScore(score: UserScore){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, score.name)
            put(COLUMN_SCORE, score.score)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getTopScores(limit: Int = 10): List<UserScore>{
        val topScores = mutableListOf<UserScore>()
        val db = readableDatabase
        val cursor = db.rawQuery(
            "Select * FROM $TABLE_NAME ORDER BY $COLUMN_SCORE DESC LIMIT ?",
            arrayOf(limit.toString())
        )

        while (cursor.moveToNext()){
            val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
            val score = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_SCORE))
            topScores.add(UserScore(name, score))
        }

        cursor.close()
        db.close()
        return topScores
    }
}
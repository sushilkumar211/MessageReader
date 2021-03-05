package com.bravo.assignment.backend

import android.database.Cursor
import java.util.*

class Parser
{
    companion object
    {
        fun getSMSsFromCursor(cursor: Cursor): List<SMS> {
            var smsList = listOf<SMS>()
            if (cursor.moveToFirst()) { // must check the result to prevent exception
                do {
                    smsList += SMS(
                        cursor.getString(cursor.getColumnIndex("_id")).toLong(),
                        cursor.getString(cursor.getColumnIndex("address")),
                        cursor.getString(cursor.getColumnIndex("body")),
                        Date(cursor.getLong(cursor.getColumnIndex("date")))
                    )
                }
                while (cursor.moveToNext())
            }
            return smsList
        }
    }
}
package com.bravo.assignment.ui.smsFragment

import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bravo.assignment.backend.Parser
import com.bravo.assignment.backend.SMS

class SMSReaderViewModel : ViewModel()
{
    fun readSMS(contentResolver : ContentResolver) : List<SMS> {
        val inboxURI: Uri = Uri.parse("content://sms/inbox")
        val reqCols = arrayOf("_id", "address", "body", "date")
        val  cursor = contentResolver.query(inboxURI, reqCols, null, null, null)

        return Parser.getSMSsFromCursor(cursor!!)
    }
}
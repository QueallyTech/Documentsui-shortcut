package com.documentsui.shortcut

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val tag = "DocumentsUI-Shortcut"

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent()
        intent.action = "android.intent.action.VIEW"
        intent.data = Uri.parse("content://com.android.externalstorage.documents/root/primary")
        try {
            intent.setClassName(
                "com.android.documentsui",
                "com.android.documentsui.files.FilesActivity"
            )
            Log.d(tag,"try to start com.android.documentsui")
            startActivity(intent)
        } catch (e: Exception) {
            Log.i(tag,"fail to start com.android.documentsui")
            try {
                intent.setClassName(
                    "com.google.android.documentsui",
                    "com.android.documentsui.files.FilesActivity"
                )
                Log.d(tag,"try to start com.google.android.documentsui")
                startActivity(intent)
            } catch (e1: Exception) {
                Log.i(tag,"fail to start com.google.android.documentsui")
                e.addSuppressed(e1);
                Log.e(tag, e.toString())
            }
        }
        finish()
    }
}
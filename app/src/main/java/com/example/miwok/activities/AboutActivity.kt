package com.example.miwok.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.miwok.R

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        findViewById<TextView>(R.id.about_text).setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://"+getString(R.string.github))))
        }
    }
}
package com.example.notefab.activity

import android.content.ContentValues
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.notefab.database.NoteDbManager
import com.example.notefab.R
import kotlinx.android.synthetic.main.activity_note.*


class NoteActivity : AppCompatActivity() {
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note)
        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)


            // getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        try {
            var bundle: Bundle = intent.extras
            id = bundle.getInt("MainActId", 0)
            if (id != 0) {
                edtTitle.setText(bundle.getString("MainActTitle"))
                edtContent.setText(bundle.getString("MainActContent"))
            }
        } catch (ex: Exception) {
        }

        btAdd.setOnClickListener {

            var dbManager = NoteDbManager(this)
            var values = ContentValues()
            values.put("Title", edtTitle.text.toString())
            values.put("Content", edtContent.text.toString())
            if (id == 0) {
                val mID = dbManager.insert(values)
                if (mID > 0) {
                    // Toast.makeText(this, "Add note successfully!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    //  Toast.makeText(this, "Fail to add note!", Toast.LENGTH_LONG).show()
                }
            } else {
                var selectionArs = arrayOf(id.toString())
                val mID = dbManager.update(values, "Id=?", selectionArs)
                if (mID > 0) {
                    // Toast.makeText(this, "Add note successfully!", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    //Toast.makeText(this, "Fail to add note!", Toast.LENGTH_LONG).show()
                }
            }
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
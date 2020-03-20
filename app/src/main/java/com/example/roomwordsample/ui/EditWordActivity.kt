package com.example.roomwordsample.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.roomwordsample.R
import com.example.roomwordsample.entities.Word
import com.example.roomwordsample.utils.EXTRA_WORD
import kotlinx.android.synthetic.main.activity_edit_word.*
import kotlinx.android.synthetic.main.activity_edit_word.btnSave
import kotlinx.android.synthetic.main.activity_edit_word.editWord
import kotlinx.android.synthetic.main.activity_new_word.*

class EditWordActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_word)

        val word: Word = intent.getSerializableExtra(EXTRA_WORD) as Word

        editWord.setText(word.word)

        btnSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(editWord.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                word.word = editWord.text.toString()
                replyIntent.putExtra(EXTRA_WORD, word)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }
}

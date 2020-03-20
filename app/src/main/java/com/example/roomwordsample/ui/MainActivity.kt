package com.example.roomwordsample.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.roomwordsample.*
import com.example.roomwordsample.entities.Word
import com.example.roomwordsample.utils.EXTRA_WORD
import com.example.roomwordsample.utils.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val newWordActivityRequestCode = 1
    private val editWordActivityRequestCode = 2

    private lateinit var wordViewModel: WordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = WordListAdapter(this)

        adapter.onEditClick = { word ->
            val intent = Intent(this@MainActivity, EditWordActivity::class.java)
            intent.putExtra(EXTRA_WORD, word)
            startActivityForResult(intent, editWordActivityRequestCode)
        }

        adapter.onDeleteClick = { word ->
            wordViewModel.delete(word)
        }

        recyclerWords.adapter = adapter

        wordViewModel = ViewModelProvider(this).get(WordViewModel::class.java)
        wordViewModel.allWords.observe(this, Observer { words ->
            words?.let { adapter.setWords(it) }
        })

        btnAddWord.setOnClickListener {
            val intent = Intent(this@MainActivity, NewWordActivity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK){

            if (requestCode == newWordActivityRequestCode) {
                data?.getStringExtra(EXTRA_WORD)?.let {
                    val word = Word(word = it)
                    wordViewModel.insert(word)
                }
            }
            else if (requestCode == editWordActivityRequestCode) {
                data?.getSerializableExtra(EXTRA_WORD)?.let {
                    wordViewModel.update(it as Word)
                }
            }
        }
       else {
            toast(R.string.empty_not_saved)
        }
    }
}
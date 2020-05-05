package com.kele.android_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kele.android_room.database.Word
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var wordViewModel: WordViewModel
    private var wordsAdapter: WordAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        wordViewModel = WordViewModel(application)
        initListeners()
        initObservers()
        initDatabase()
    }

    private fun initListeners() {
        bt_insert_to_database.setOnClickListener {
            wordViewModel.insertWordLocally(Word(et_word_id.text.toString(), et_word_input.text.toString()))
            et_word_id.text?.clear()
            et_word_input.text?.clear()
        }
        bt_update_database.setOnClickListener {
            wordViewModel.getWordsLocally()
        }
        bt_delete_database.setOnClickListener {
            wordViewModel.deleteAllWordsLocally()
        }
    }

    private fun initDatabase() {
        rv_database.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        wordsAdapter = WordAdapter(arrayListOf(), this)
        rv_database.adapter = wordsAdapter
    }

    private fun initObservers() {
        wordViewModel.wordsLocal.observe(this, Observer{ wordList ->
            wordsAdapter?.setList(wordList)
        })
    }
}

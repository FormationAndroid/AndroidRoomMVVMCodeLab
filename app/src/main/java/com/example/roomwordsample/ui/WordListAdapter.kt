package com.example.roomwordsample.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsample.R
import com.example.roomwordsample.entities.Word

class WordListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var words = emptyList<Word>()

    var onEditClick: ((word: Word) -> Unit)? = null
    var onDeleteClick: ((word: Word) -> Unit)? = null

    inner class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
        val wordItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)
        return WordViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val current = words[position]
        holder.apply {
            wordItemView.text = current.word
            btnEdit.setOnClickListener { onEditClick?.invoke(current) }
            btnDelete.setOnClickListener { onDeleteClick?.invoke(current) }
        }

    }

    internal fun setWords(words: List<Word>) {
        this.words = words
        notifyDataSetChanged()
    }

    override fun getItemCount() = words.size

}
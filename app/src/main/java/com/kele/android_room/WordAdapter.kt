package com.kele.android_room

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kele.android_room.database.Word
import kotlinx.android.synthetic.main.item_word.view.*

class WordAdapter(private var items: List<Word>, private val context: Context) :
    RecyclerView.Adapter<WordAdapter.ViewHolder>() {

    fun setList(updatedItems: List<Word>) {
        items = updatedItems
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.item_word, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(word: Word) {
            itemView.tv_id.text = word.id.toString()
            itemView.tv_word.text = word.word
        }
    }


}
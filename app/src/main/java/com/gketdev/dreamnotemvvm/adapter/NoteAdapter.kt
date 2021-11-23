package com.gketdev.dreamnotemvvm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gketdev.dreamnotemvvm.data.Note
import com.gketdev.dreamnotemvvm.databinding.ItemNoteBinding

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.ViewHolder>() {

    var items: List<Note> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNoteBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val noteByPositioned = items[position]
        holder.bind(noteByPositioned)
    }

    inner class ViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Note) {
            binding.textViewNote.text = item.noteId.toString()
            binding.textViewPriority.text = item.notePoint.toString()
        }
    }
}
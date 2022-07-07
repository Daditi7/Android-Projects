package com.example.roomdatabase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class NotesRVAdapter(private val context: Context, private val listener: INotesRVAdapter) :
    RecyclerView.Adapter<NotesRVAdapter.NoteViewHolder>() {
    private val allNotes: ArrayList<Note>

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
        val deleteButton: ImageView

        init {
            textView = itemView.findViewById(R.id.text)
            deleteButton = itemView.findViewById(R.id.delete_button)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_item_note, parent, false)
        val viewHolder: NoteViewHolder = NoteViewHolder(view)
        viewHolder.deleteButton.setOnClickListener(View.OnClickListener { it: View? ->
            val item = listener
            val sample_string: Any =
                allNotes[viewHolder.adapterPosition]
            item.onItemClicked(sample_string as Note)
        })
        return viewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val curr_note = allNotes[position]
        holder.textView.text = curr_note.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun update(notes: List<Note>?) {
        allNotes.clear()
        allNotes.addAll(notes!!)
        notifyDataSetChanged()
    }

    init {
        allNotes = ArrayList()
    }
}


interface INotesRVAdapter {
    fun onItemClicked(note: Note?)
}
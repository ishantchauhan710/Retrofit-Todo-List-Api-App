package com.ishant.retrofittutorial

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ishant.retrofittutorial.databinding.TodoLayoutBinding

class TodoAdapter: RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    inner class TodoViewHolder(val binding: TodoLayoutBinding): RecyclerView.ViewHolder(binding.root)

    private val differCallback = object: DiffUtil.ItemCallback<Todo>() {
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this,differCallback)

    var todos: List<Todo>
        get() = differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(TodoLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.binding.apply {
            val todo = todos[position]
            tvTitle.text = todo.title
            cbDone.isChecked = todo.completed
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}
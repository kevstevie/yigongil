package com.created.team201.presentation.home.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.created.team201.databinding.ItemHomeOptionalTodoBinding
import com.created.team201.presentation.home.HomeClickListener
import com.created.team201.presentation.home.model.TodoWithRoundIdUiModel

class OptionalToDoViewHolder(
    onClick: HomeClickListener,
    private val binding: ItemHomeOptionalTodoBinding,
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.onClick = onClick
    }

    fun bind(todoUiModel: TodoWithRoundIdUiModel) {
        binding.todoUiModel = todoUiModel
    }

    companion object {
        fun getView(
            parent: ViewGroup,
            layoutInflater: LayoutInflater,
        ): ItemHomeOptionalTodoBinding =
            ItemHomeOptionalTodoBinding.inflate(layoutInflater, parent, false)
    }
}
package com.created.team201.presentation.studyManagement.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.created.team201.presentation.studyManagement.StudyManagementClickListener

abstract class StudyOptionalTodoViewHolder(
    val binding: ViewDataBinding,
    studyManagementClickListener: StudyManagementClickListener,
) : RecyclerView.ViewHolder(binding.root)
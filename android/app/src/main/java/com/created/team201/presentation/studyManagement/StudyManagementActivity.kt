package com.created.team201.presentation.studyManagement

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import com.created.team201.R
import com.created.team201.databinding.ActivityStudyManagementBinding
import com.created.team201.presentation.common.BindingActivity
import com.created.team201.presentation.studyManagement.adapter.StudyManagementAdapter

class StudyManagementActivity :
    BindingActivity<ActivityStudyManagementBinding>(R.layout.activity_study_management) {

    private val studyManagementViewModel by viewModels<StudyManagementViewModel>()
    private val studyManagementAdapter: StudyManagementAdapter by lazy {
        StudyManagementAdapter(studyManagementClickListener, memberClickListener)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()
        initStudyRounds()
        initAdapter()
        observeStudyManagement()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbStudyManagement)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back)
    }

    private fun initStudyRounds() {
        val studyId = intent.getLongExtra(KEY_STUDY_ID, KEY_ERROR)
        val roundId = intent.getLongExtra(KEY_ROUND_ID, KEY_ERROR)
        studyManagementViewModel.getStudyRounds(studyId, roundId)
    }

    private fun initAdapter() {
        binding.vpStudyManagement.adapter = studyManagementAdapter
    }

    private fun observeStudyManagement() {
        studyManagementViewModel.studyRounds.observe(this) { studyRoundDetails ->
            studyManagementAdapter.submitList(studyRoundDetails)
        }
    }

    private val studyManagementClickListener = object : StudyManagementClickListener {
        override fun clickOnTodo(id: Long, isDone: Boolean) {
            val currentItemId = binding.vpStudyManagement.currentItem
            studyManagementViewModel.updateTodo(currentItemId, id, !isDone)
        }

        override fun onClickAddTodo(todoContent: String) {
            studyManagementViewModel.addOptionalTodo(todoContent)
        }
    }

    private val memberClickListener = object : StudyMemberClickListener {
        override fun onClickMember(id: Long) {
            // 프로필 페이지로 이동
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val KEY_ERROR = 0L
        private const val KEY_STUDY_ID = "KEY_STUDY_ID"
        private const val KEY_ROUND_ID = "KEY_ROUND_ID"
        fun getIntent(context: Context, studyId: Long, roundId: Long): Intent =
            Intent(context, StudyManagementActivity::class.java).apply {
                putExtra(KEY_STUDY_ID, studyId)
                putExtra(KEY_ROUND_ID, roundId)
            }
    }
}

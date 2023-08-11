package com.created.domain.repository

import com.created.domain.model.StudySummary

interface StudyListRepository {

    suspend fun getStudyList(page: Int): List<StudySummary>

    suspend fun getSearchedStudyList(searchWord: String, page: Int): List<StudySummary>
}
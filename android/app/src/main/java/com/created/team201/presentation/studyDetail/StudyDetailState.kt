package com.created.team201.presentation.studyDetail

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.created.team201.R

sealed class StudyDetailState(
    @StringRes
    val appBarTitle: Int,
    @StringRes
    val mainButtonText: Int,
    @ColorRes
    val mainButtonTextColor: Int,
    val mainButtonIsEnabled: Boolean,
    @DrawableRes
    val subButtonSrc: Int,
) {
    data class Master(private val canStartStudy: Boolean) : StudyDetailState(
        appBarTitle = R.string.study_detail_app_bar_study_master_title,
        mainButtonText = R.string.study_detail_button_start_study,
        mainButtonTextColor = R.color.white,
        subButtonSrc = R.drawable.ic_edit_20,
        mainButtonIsEnabled = canStartStudy,
    )

    object Member :
        StudyDetailState(
            appBarTitle = R.string.study_detail_app_bar_title,
            mainButtonText = R.string.study_detail_study_start_waiting,
            mainButtonTextColor = R.color.grey02_78808B,
            subButtonSrc = R.drawable.ic_dm,
            mainButtonIsEnabled = false,
        )

    object Applicant :
        StudyDetailState(
            appBarTitle = R.string.study_detail_app_bar_title,
            mainButtonText = R.string.study_detail_study_accept_waiting,
            mainButtonTextColor = R.color.grey02_78808B,
            subButtonSrc = R.drawable.ic_dm,
            mainButtonIsEnabled = false,
        )

    data class Nothing(private val isFullMember: Boolean) :
        StudyDetailState(
            appBarTitle = R.string.study_detail_app_bar_title,
            mainButtonText = if (isFullMember) {
                R.string.study_detail_notify_cant_participant
            } else {
                R.string.study_detail_study_capacity
            },
            mainButtonTextColor = R.color.white,
            subButtonSrc = R.drawable.ic_dm,
            mainButtonIsEnabled = !isFullMember,
        )

    data class Guest(private val isFullMember: Boolean) : StudyDetailState(
        appBarTitle = R.string.study_detail_app_bar_title,
        mainButtonText = if (isFullMember) {
            R.string.study_detail_notify_cant_participant
        } else {
            R.string.study_detail_study_capacity
        },
        mainButtonTextColor = R.color.white,
        subButtonSrc = R.drawable.ic_info,
        mainButtonIsEnabled = !isFullMember,
    )
}

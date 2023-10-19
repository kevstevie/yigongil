package com.created.domain.model

data class StudyDetail(
    val id: Long,
    val processingStatus: Int,
    val name: String,
    val numberOfCurrentMembers: Int,
    val numberOfMaximumMembers: Int,
    val studyMasterId: Long,
    val meetingDaysCountPerWeek: Int,
    val introduction: String,
    val members: List<Member>,
    val minimumWeeks: Int,
) {
    companion object {
        private const val START_MEMBER_CONDITION = 2
        fun getPeriod(periodOfRound: String): Int =
            periodOfRound.replace("[^0-9]".toRegex(), "").toInt()

        fun canStartStudy(numberOfCurrentMembers: Int): Boolean =
            numberOfCurrentMembers >= START_MEMBER_CONDITION
    }
}

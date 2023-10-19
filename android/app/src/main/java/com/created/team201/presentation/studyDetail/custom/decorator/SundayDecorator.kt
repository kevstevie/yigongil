package com.created.team201.presentation.studyDetail.custom.decorator

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate

class SundayDecorator(private val maxDate: LocalDate) : DayViewDecorator {
    override fun shouldDecorate(day: CalendarDay): Boolean {
        val sunday = day.date.with(DayOfWeek.SUNDAY).dayOfMonth
        return sunday == day.day && LocalDate.now()
            .isBefore(day.date) && maxDate.isAfter(day.date)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(ForegroundColorSpan(Color.RED))
    }
}

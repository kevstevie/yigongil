package com.yigongil.backend.domain.study.studyquery;

import com.yigongil.backend.domain.study.ProcessingStatus;
import com.yigongil.backend.domain.study.Study;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface StudyQueryRepository {

    Slice<Study> findStudiesByConditions(String search, ProcessingStatus status, Pageable page);
}
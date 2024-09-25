package com.yigongil.backend.domain.feedpost;

import com.yigongil.backend.domain.member.domain.Member;
import com.yigongil.backend.domain.study.Study;
import com.yigongil.backend.domain.study.StudyRepository;
import com.yigongil.backend.request.FeedPostCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedService {

    private final JpaFeedPostRepository jpaFeedPostRepository;
    private final StudyRepository studyRepository;

    public FeedService(JpaFeedPostRepository jpaFeedPostRepository, StudyRepository studyRepository) {
        this.jpaFeedPostRepository = jpaFeedPostRepository;
        this.studyRepository = studyRepository;
    }

    @Transactional
    public void createFeedPost(Member member, Long studyId, FeedPostCreateRequest request) {
        Study study = studyRepository.getById(studyId);

        FeedPost regularFeedPost = FeedPost.builder()
                                           .author(member)
                                           .study(study)
                                           .imageUrl(request.imageUrl())
                                           .content(request.content())
                                           .build();

        jpaFeedPostRepository.save(regularFeedPost);
    }
}

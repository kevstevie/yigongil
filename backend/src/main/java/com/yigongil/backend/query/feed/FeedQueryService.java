package com.yigongil.backend.query.feed;

import com.yigongil.backend.domain.feedpost.JpaFeedPostRepository;
import com.yigongil.backend.response.FeedPostResponse;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
public class FeedQueryService {

    private final JpaFeedPostRepository jpaFeedPostRepository;

    public FeedQueryService(JpaFeedPostRepository jpaFeedPostRepository) {
        this.jpaFeedPostRepository = jpaFeedPostRepository;
    }

    @Transactional(readOnly = true)
    public List<FeedPostResponse> findFeedPosts(Long studyId, Long oldestFeedPostId) {
        return jpaFeedPostRepository.findAllByStudyIdStartWithOldestFeedPostId(studyId, oldestFeedPostId)
                                 .stream()
                                 .map(FeedPostResponse::from)
                                 .toList();
    }
}

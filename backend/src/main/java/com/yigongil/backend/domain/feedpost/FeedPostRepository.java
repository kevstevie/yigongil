package com.yigongil.backend.domain.feedpost;

import com.yigongil.backend.response.FeedPostResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FeedPostRepository {

    private final JpaFeedPostRepository jpaFeedPostRepository;
    private final MemoryFeedPostRepository memoryFeedPostRepository;

    public FeedPostRepository(
            JpaFeedPostRepository jpaFeedPostRepository,
            MemoryFeedPostRepository memoryFeedPostRepository
    ) {
        this.jpaFeedPostRepository = jpaFeedPostRepository;
        this.memoryFeedPostRepository = memoryFeedPostRepository;
    }

    public void save(FeedPost feedPost) {
        memoryFeedPostRepository.save(feedPost);
        if (memoryFeedPostRepository.isFull()) {
            jpaFeedPostRepository.saveAll(memoryFeedPostRepository.findAll());
            memoryFeedPostRepository.clear();
        }
    }

    public List<FeedPostResponse> findAllByStudyId(Long studyId, Long oldestFeedPostId) {
        List<FeedPost> memoryFeeds = memoryFeedPostRepository.findAllByStudyIdAndDescCreatedAt(studyId);
        List<FeedPost> savedFeeds = jpaFeedPostRepository.findAllByStudyIdStartWithOldestFeedPostId(studyId, oldestFeedPostId);

        memoryFeeds.addAll(savedFeeds);
        return memoryFeeds.stream()
                .map(FeedPostResponse::from)
                .toList();
    }
}

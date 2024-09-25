package com.yigongil.backend.domain.feedpost;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

public interface JpaFeedPostRepository extends Repository<FeedPost, Long> {

    @Query("""
                select fp from FeedPost fp
                where fp.study.id = :studyId
                and fp.id < :oldestFeedPostId
                order by fp.id desc
            """)
    List<FeedPost> findAllByStudyIdStartWithOldestFeedPostId(
            @Param("studyId") Long studyId,
            @Param("oldestFeedPostId") Long oldestFeedPostId
    );

    FeedPost save(FeedPost feedPost);

    void saveAll(List<FeedPost> all);
}

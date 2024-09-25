package com.yigongil.backend.domain.feedpost;

import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class MemoryFeedPostRepository {

    private final CopyOnWriteArrayList<FeedPost> feedPosts = new CopyOnWriteArrayList<>();

    public FeedPost save(FeedPost feedPost) {
        feedPosts.add(feedPost);
        return feedPost;
    }

    public void clear() {
        feedPosts.clear();
    }

    public boolean isFull() {
        return feedPosts.size() >= 100;
    }

    public List<FeedPost> findAll() {
        return feedPosts.stream().toList();
    }

    public List<FeedPost> findAllByStudyIdAndDescCreatedAt(Long studyId) {
        return feedPosts.stream()
                .filter(feedPost -> feedPost.getStudy().getId().equals(studyId))
                .sorted(Comparator.comparing(FeedPost::getCreatedAt).reversed())
                .collect(CopyOnWriteArrayList::new, CopyOnWriteArrayList::add, CopyOnWriteArrayList::addAll);
    }
}

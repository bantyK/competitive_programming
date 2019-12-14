import java.util.*;

// 355 https://leetcode.com/problems/design-twitter/
class Twitter {
    // map of all tweets, by all users
    Map<Integer, PriorityQueue<Tweet>> tweets;
    // key is the userId and value is the list of users which follows userId
    Map<Integer, List<Integer>> followersMap;
    private int timeStamp;

    public Twitter() {
        timeStamp = 0;
        tweets = new HashMap<>();
        followersMap = new HashMap<>();
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        Tweet tweet = new Tweet(++timeStamp, tweetId);
        if (!tweets.containsKey(userId)) {
            PriorityQueue<Tweet> pq = new PriorityQueue<>((t1, t2) -> t2.timestamp - t1.timestamp);
            tweets.put(userId, pq);
        }
        tweets.get(userId).add(tweet);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feeds = new LinkedList<>();

        PriorityQueue<Tweet> allTweets = new PriorityQueue<>((t1, t2) -> t2.timestamp - t1.timestamp);

        if (tweets.get(userId) != null) {
            allTweets.addAll(tweets.get(userId));
        }

        if(followersMap.get(userId) != null) {
            for (int follower : followersMap.get(userId)) {
                PriorityQueue<Tweet> followerTweets = tweets.get(follower);
                if (followerTweets != null)
                    allTweets.addAll(followerTweets);
            }
        }

        if (allTweets.size() == 0) return feeds;

        while (!allTweets.isEmpty() && feeds.size() < 10) {
            Tweet poll = allTweets.poll();
            feeds.add(poll.tweetId);
        }

        return feeds;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followeeId, int followerId) {
        if (followeeId == followerId) return;
        if (!followersMap.containsKey(followeeId)) {
            followersMap.put(followeeId, new LinkedList<>());
        }

        if (followersMap.get(followeeId).contains(followerId)) return;

        followersMap.get(followeeId).add(followerId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followeeId, int followerId) {
        if (followeeId == followerId) return;
        if (followersMap.containsKey(followeeId)) {
            if (followersMap.get(followeeId).contains(followerId)) {
                followersMap.get(followeeId).remove(Integer.valueOf(followerId));
            }
        }
    }

    static class Tweet {
        final int timestamp;
        final int tweetId;

        Tweet(int timestamp, int tweetId) {
            this.timestamp = timestamp;
            this.tweetId = tweetId;
        }

        @Override
        public boolean equals(Object obj) {
            Tweet tweet = (Tweet) obj;
            return this.timestamp == tweet.timestamp && this.tweetId == tweet.tweetId;
        }
    }
}

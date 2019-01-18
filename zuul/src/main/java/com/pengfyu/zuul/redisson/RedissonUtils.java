package com.pengfyu.zuul.redisson;

import com.pengfyu.zuul.util.ApplicationContextUtil;
import org.redisson.api.*;

import java.util.concurrent.TimeUnit;

/**
 * 锁工具类
 * 考虑锁的缓存
 *
 *
 * 锁对象缓存，最多缓存200个
 * private static LRUCache<String, RLock> lockCache = new LRUCache(200);
 *
 */
public class RedissonUtils {

    private static RedissonClient redissonClient;

    static {
        try {
            redissonClient =  ApplicationContextUtil.getBean(RedissonClient.class);
        }catch (Exception e){

        }
    }


    public static void lock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock();
    }

    public static void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    public static void lock(String lockKey, TimeUnit unit, int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lockAsync(timeout, unit);
    }

    public static boolean tryLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock();
    }

    public static boolean tryLock(String lockKey, long waitTime, long leaseTime,TimeUnit unit) throws InterruptedException {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock(waitTime, leaseTime, unit);
    }

    public static boolean isLocked(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.isLocked();
    }

    /**
     * 获取 redissonClient
     * @return redissonclient
     */
    public static RedissonClient getRedissonClient(){
        return redissonClient;
    }

    /**
     * 获取延时队列
     * 延时队列的构造函数是一个队列
     * @param delayQueueName
     * @return
     */
    public static RDelayedQueue<Integer> getDelayQuene(String delayQueueName){
        RQueue<Integer> queue = redissonClient.getQueue(delayQueueName);
        RDelayedQueue<Integer> delayedQueue = redissonClient.getDelayedQueue(queue);
        return delayedQueue;
    }


    /**
     * 关闭 redissonClient
     * @return
     */
    public static void close(){
        redissonClient.shutdown();
    }

    /**
     * 通用对象桶
     * Redisson的分布式RBucketJava对象是一种通用对象桶可以用来存放任类型的对象*
     * @param <V>        泛型
     * @param objectName 对象名
     * @return RBucket
     */
    public static <V>RBucket<V> getRBucket(String objectName){
        return redissonClient.getBucket(objectName);
    }

    /**
     * 获取map对象
     *
     * @param <K>        the type parameter
     * @param <V>        the type parameter
     * @param objectName the object name
     * @return the r map
     */
    public static <K,V>RMap<K,V> getMap(String objectName){
        return redissonClient.getMap(objectName);
    }

    /**
     * 获取支持单个元素过期的map对象
     *
     * @param <K>        the type parameter
     * @param <V>        the type parameter
     * @param objectName the object name
     * @return the r map cache
     */
    public static <K,V> RMapCache<K,V> getMapCache(String objectName){
        return redissonClient.getMapCache(objectName);
    }

    /**
     * 获取set对象
     *
     * @param <V>        the type parameter
     * @param objectName the object name
     * @return the r set
     */
    public static <V> RSet<V> getSet(String objectName){
        return redissonClient.getSet(objectName);
    }

    /**
     * 获取SortedSet对象
     *
     * @param <V>        the type parameter
     * @param objectName the object name
     * @return the r sorted set
     */
    public static <V> RSortedSet<V> getSorteSet(String objectName){
        return redissonClient.getSortedSet(objectName);
    }

    /**
     * 获取ScoredSortedSett对象
     * @param objectName
     * @param <V>
     * @return
     */
    public static <V> RScoredSortedSet<V> getScoredSorteSet(String objectName) {
        return redissonClient.getScoredSortedSet(objectName);
    }

    /**
     * 获取list对象
     *
     * @param <V>        the type parameter
     * @param objectName the object name
     * @return the r list
     */
    public static <V> RList<V> getList(String objectName){
        return redissonClient.getList(objectName);
    }

    /**
     * 获取queue对象
     *
     * @param <V>        the type parameter
     * @param objectName the object name
     * @return the r queue
     */
    public static <V> RQueue<V> getQueue(String objectName){
        return redissonClient.getQueue(objectName);
    }


    /**
     * Get blocking queue r blocking queue.
     *
     * @param <V>        the type parameter
     * @param objectName the object name
     * @return the r blocking queue
     */
    public static <V> RBlockingQueue<V> getBlockingQueue(String objectName){
        return redissonClient.getBlockingQueue(objectName);
    }

    /**
     * Get atomic long r atomic long.
     *
     * @param objectName the object name
     * @return the r atomic long
     */
    public static RAtomicLong getAtomicLong(String objectName){
        return redissonClient.getAtomicLong(objectName);
    }

    /**
     * Get lock r lock.
     * @param objectName the object name
     * @return the r lock
     */
    public static RLock getLock(String objectName){
        return redissonClient.getLock(objectName);
    }

}

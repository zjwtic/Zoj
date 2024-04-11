package com.zhou.zoj.utils;

import java.time.temporal.ChronoUnit;
import java.util.concurrent.*;
import java.time.*;
import java.util.*;

public class ScheduledTaskManager {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final static Map<Long, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();
//    private final Map<Long, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

//    public void scheduleTask(Long contestId, LocalDateTime targetTime, Runnable task) {
//        // 取消之前的任务（如果存在）
//        cancelTask(contestId);
//
//        // 计算延迟时间
//        long delay = ChronoUnit.MILLIS.between(LocalDateTime.now(), targetTime);
//
//        if (delay < 0) {
//            System.err.println("目标时间已过，无法安排任务: " + contestId);
//            return;
//        }
//
//        // 安排新任务
//        ScheduledFuture<?> future = scheduler.schedule(task, delay, TimeUnit.MILLISECONDS);
//        scheduledTasks.put(contestId, future);
//    }

    public void scheduleTask(Long contestId, Date targetDate, Runnable task) {
        // 取消之前的任务（如果存在）
        cancelTask(contestId);

        Instant nowInstant =Instant.now();
        Instant targetInstant= targetDate.toInstant();

        // 计算两个Instant之间的Duration
        Duration duration = Duration.between(nowInstant, targetInstant);

        // 将Duration转换为毫秒
        long delay = duration.toMillis();


        if (delay < 0) {
            System.err.println("目标时间已过，无法安排任务: " + contestId);
            return;
        }

        // 安排新任务
        ScheduledFuture<?> future = scheduler.schedule(task, delay, TimeUnit.MILLISECONDS);
        scheduledTasks.put(contestId, future);
    }

    public void updateTask(Long contestId, Date newtargetDate, Runnable newTask) {
        // 取消之前的任务（如果存在）
        cancelTask(contestId);

        // 重新安排任务
        scheduleTask(contestId, newtargetDate, newTask);
    }

    public void cancelTask(Long contestId) {
        ScheduledFuture<?> future = scheduledTasks.remove(contestId);
        if (future != null && !future.isDone()) {
            Boolean cancelled = future.cancel(true);
            if (cancelled) {
                System.out.println("任务 " + contestId + " 已被取消。");
            } else {
                System.out.println("任务 " + contestId + " 无法取消，因为它已经完成或已被取消。");
            }
        } else {
            System.out.println("任务 " + contestId + " 不存在或已完成。");
        }
    }


    // 在程序结束前，关闭ScheduledExecutorService
    public void shutdown() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException ex) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        ScheduledTaskManager manager = new ScheduledTaskManager();

        // 示例：安排任务
        manager.scheduleTask(1213113l,new Date(), () -> {
            System.out.println("contest1 的任务执行了");
        });

        // 示例：更新任务时间或任务内容
        // 假设在某个时间点，需要更改contest1的任务时间或内容
        manager.updateTask(1213113l, new Date(), () -> {
            System.out.println("contest1 的任务（更新后）执行了");
        });

        // 示例：取消任务
        // 如果需要取消某个任务，可以调用 cancelTask 方法
        // manager.cancelTask("contest1");

        // 假设程序运行一段时间后需要关闭ScheduledExecutorService
        // manager.shutdown();
    }
}
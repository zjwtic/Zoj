create table post_thumb
(
    id         bigint auto_increment comment 'id'
        primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '帖子点赞';

create index idx_postId
    on post_thumb (postId);

create index idx_userId
    on post_thumb (userId);

INSERT INTO zoj.post_thumb (id, postId, userId, createTime, updateTime) VALUES (22, 1782278351141576705, 1782061672771977217, '2024-04-22 13:21:25', '2024-04-22 13:21:25');
INSERT INTO zoj.post_thumb (id, postId, userId, createTime, updateTime) VALUES (23, 1782274422697730050, 1782061672771977217, '2024-04-22 13:21:36', '2024-04-22 13:21:36');
INSERT INTO zoj.post_thumb (id, postId, userId, createTime, updateTime) VALUES (24, 1782278351141576705, 1782062139048558594, '2024-04-22 13:32:15', '2024-04-22 13:32:15');
INSERT INTO zoj.post_thumb (id, postId, userId, createTime, updateTime) VALUES (25, 1782278351141576705, 1782061954323021825, '2024-04-22 13:52:50', '2024-04-22 13:52:50');
INSERT INTO zoj.post_thumb (id, postId, userId, createTime, updateTime) VALUES (26, 1782278351141576705, 1782061165294747650, '2024-05-07 09:18:08', '2024-05-07 09:18:08');
INSERT INTO zoj.post_thumb (id, postId, userId, createTime, updateTime) VALUES (27, 1782274422697730050, 1782061165294747650, '2024-05-24 10:11:48', '2024-05-24 10:11:48');

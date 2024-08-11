create table post_dislike
(
    id         bigint auto_increment
        primary key,
    postId     bigint                             null,
    userId     bigint                             null,
    createTime datetime default CURRENT_TIMESTAMP null,
    updateTime datetime default CURRENT_TIMESTAMP null
);

create index idx_postId
    on post_dislike (postId);

create index idx_userId
    on post_dislike (userId);

INSERT INTO zoj.post_dislike (id, postId, userId, createTime, updateTime) VALUES (21, 1782278351141576705, 1782061165294747650, '2024-05-07 09:18:17', '2024-05-07 09:18:17');

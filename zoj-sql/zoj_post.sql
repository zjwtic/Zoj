create table post
(
    id         bigint auto_increment comment 'id'
        primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    dislikeNum int      default 0                 null comment '/*点踩数*/',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
)
    comment '帖子' collate = utf8mb4_unicode_ci;

create index idx_userId
    on post (userId);

INSERT INTO zoj.post (id, title, content, tags, thumbNum, dislikeNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782274422697730050, '大学生如何学到就业的水平', '本人大三民办的计算机学生，现在是C，Java，python，都会一点点，数据结构还不错，反正就是专业课都会一些，但是不精通，我应该怎么做然后达到就业的水平，本人想从事Java后端方向，路过的大佬们帮忙给个建议吧，孩子很听劝的

', '["求职求助"]', 2, 0, 0, 1782061672771977217, '2024-04-22 13:05:10', '2024-05-25 09:41:03', 0);
INSERT INTO zoj.post (id, title, content, tags, thumbNum, dislikeNum, favourNum, userId, createTime, updateTime, isDelete) VALUES (1782278351141576705, 'Zoj 系统的建议', '  首先恭喜ZOj系统已经成功运营了1周了，本系统主要就是为算法爱好者和开发者提供一个集中、高效、便捷的测试环境。这有助于促进算法技术的快速发展，使得更多的优秀算法得以展示和应用。也希望各位用户可以多多分享自己宝贵的建议，欢迎在评论区留言，商务合作请发email至3233568197@qq.com.', '["询问"]', 4, 1, 0, 1782061165294747650, '2024-04-22 13:20:46', '2024-05-07 09:18:17', 0);

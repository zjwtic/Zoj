create table contest
(
    id                 bigint                             not null comment 'id'
        primary key,
    userId             bigint                             null comment '创建用户id',
    selectQuestionIds  varchar(1024)                      null comment '选择的题目id集合',
    contestName        varchar(512)                       null comment '比赛名称',
    contestDescription varchar(512)                       null comment '比赛描述',
    startTime          datetime default CURRENT_TIMESTAMP null comment '比赛开始时间',
    endTime            datetime default CURRENT_TIMESTAMP null comment '比赛结束时间',
    duration           int      default 3600              null comment '比赛时间',
    createTime         datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime         datetime default CURRENT_TIMESTAMP null comment '更新时间',
    isDelete           tinyint  default 0                 null comment '是否逻辑删除'
)
    comment '比赛';

create index idex_userId
    on contest (userId);

INSERT INTO zoj.contest (id, userId, selectQuestionIds, contestName, contestDescription, startTime, endTime, duration, createTime, updateTime, isDelete) VALUES (1782284176702676994, 1782061165294747650, '["1782236265553960961","1782244128879910914","1782250709189238786"]', 'ZOJ NOI Round #1 Day 1', 'ZOJ NOI 模拟赛 ~ Take it easy~
', '2024-04-22 13:00:25', '2024-04-22 15:00:25', 7200, '2024-04-22 13:43:55', '2024-04-22 13:43:55', 0);
INSERT INTO zoj.contest (id, userId, selectQuestionIds, contestName, contestDescription, startTime, endTime, duration, createTime, updateTime, isDelete) VALUES (1782284902388572162, 1782061165294747650, '["1782249777370079234","1782263404173041665","1782272440981364738","1782224642772643841"]', 'ZOJ CONTEST  1 ', 'ZOJ NOI1 模拟赛 ~ Take it easy~', '2024-04-22 13:00:18', '2024-04-22 16:00:18', 10800, '2024-04-22 13:46:48', '2024-04-22 13:46:48', 0);
INSERT INTO zoj.contest (id, userId, selectQuestionIds, contestName, contestDescription, startTime, endTime, duration, createTime, updateTime, isDelete) VALUES (1782324935569022977, 1782061165294747650, '["1782236265553960961","1782244128879910914","1782250709189238786","1782252665483927554"]', 'test_1', '比赛测试', '2024-04-22 16:25:32', '2024-04-22 16:55:32', 3600, '2024-04-22 16:25:53', '2024-04-22 16:25:53', 0);
INSERT INTO zoj.contest (id, userId, selectQuestionIds, contestName, contestDescription, startTime, endTime, duration, createTime, updateTime, isDelete) VALUES (1782325198786764801, 1782061165294747650, '["1782224642772643841","1782236265553960961","1782244128879910914","1782249777370079234"]', 'test 2', '未开始比赛', '2024-04-22 17:44:40', '2024-04-22 18:26:40', 3600, '2024-04-22 16:26:56', '2024-04-22 16:26:56', 1);
INSERT INTO zoj.contest (id, userId, selectQuestionIds, contestName, contestDescription, startTime, endTime, duration, createTime, updateTime, isDelete) VALUES (1785603459415355393, 1782061165294747650, '["1782250709189238786","1782249777370079234"]', '测试比赛', '这是测试比赛', '2024-05-01 17:33:20', '2024-05-09 17:33:20', 3600, '2024-05-01 17:33:34', '2024-05-01 17:33:34', 0);
INSERT INTO zoj.contest (id, userId, selectQuestionIds, contestName, contestDescription, startTime, endTime, duration, createTime, updateTime, isDelete) VALUES (1787654651586904066, 1782061165294747650, '["1782236265553960961","1782240357810544642","1782244128879910914"]', 'tec', '1212', '2024-05-07 09:24:12', '2024-05-22 09:24:12', 3600, '2024-05-07 09:24:16', '2024-05-07 09:24:16', 0);
INSERT INTO zoj.contest (id, userId, selectQuestionIds, contestName, contestDescription, startTime, endTime, duration, createTime, updateTime, isDelete) VALUES (1793450886101020673, 1782061165294747650, '["1782224642772643841","1782250709189238786"]', '12', '12', '2024-05-23 09:16:22', '2024-05-30 09:16:22', 3600, '2024-05-23 09:16:26', '2024-05-23 09:16:26', 1);
INSERT INTO zoj.contest (id, userId, selectQuestionIds, contestName, contestDescription, startTime, endTime, duration, createTime, updateTime, isDelete) VALUES (1793532680041136130, 1782061165294747650, '["1782224642772643841","1782240357810544642"]', '1', '1', '2024-05-23 14:41:23', '2024-05-30 14:41:23', 3600, '2024-05-23 14:41:27', '2024-05-23 14:41:27', 1);
INSERT INTO zoj.contest (id, userId, selectQuestionIds, contestName, contestDescription, startTime, endTime, duration, createTime, updateTime, isDelete) VALUES (1794183266034049026, 1782061165294747650, '["1782236265553960961","1782249777370079234","1782270640039501826"]', '测试比赛', 'yyq测试', '2024-05-25 09:45:52', '2030-07-31 09:45:52', 3600, '2024-05-25 09:46:39', '2024-05-25 09:46:39', 0);

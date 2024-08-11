create table count_down
(
    id             bigint                             not null
        primary key,
    userId         bigint                             not null comment '用户id',
    contestId      bigint                             not null comment '比赛id',
    startTimeStamp bigint                             not null comment '开始时间戳',
    endTimeStamp   bigint                             not null comment '结束时间戳',
    createTime     datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime     datetime                           null comment '更新时间'
);

INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782285319465967617, 1782061672771977217, 1782284902388572162, 1713764908085, 1713775708085, '2024-04-22 13:48:28', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782286069088755713, 1782061672771977217, 1782284176702676994, 1713765086803, 1713772286803, '2024-04-22 13:51:26', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782286551437910017, 1782061954323021825, 1782284902388572162, 1713765201808, 1713776001808, '2024-04-22 13:53:21', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782286976056664066, 1782061954323021825, 1782284176702676994, 1713765303052, 1713772503052, '2024-04-22 13:55:03', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782287719299915777, 1782061165294747650, 1782284902388572162, 1713765480256, 1713776280256, '2024-04-22 13:58:00', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782287761075183617, 1782061165294747650, 1782284176702676994, 1713765490202, 1713772690202, '2024-04-22 13:58:10', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782287874321391617, 1782061852149776385, 1782284902388572162, 1713765517217, 1713776317217, '2024-04-22 13:58:37', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782288018873884673, 1782061852149776385, 1782284176702676994, 1713765551678, 1713772751678, '2024-04-22 13:59:11', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782288131847462914, 1782061917853548546, 1782284902388572162, 1713765578606, 1713776378606, '2024-04-22 13:59:38', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782288406431768578, 1782061917853548546, 1782284176702676994, 1713765644080, 1713772844080, '2024-04-22 14:00:44', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782288643359612930, 1782061982609408002, 1782284902388572162, 1713765700561, 1713776500561, '2024-04-22 14:01:40', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782288693884198913, 1782061982609408002, 1782284176702676994, 1713765712606, 1713772912606, '2024-04-22 14:01:52', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1782326037811142657, 1782061165294747650, 1782324935569022977, 1713774616087, 1713778216087, '2024-04-22 16:30:16', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1785603510854299650, 1782061165294747650, 1785603459415355393, 1714556026557, 1714559626557, '2024-05-01 17:33:46', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1787654663054131201, 1782061165294747650, 1787654651586904066, 1715045059345, 1715048659345, '2024-05-07 09:24:19', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1793450902605606914, 1782061165294747650, 1793450886101020673, 1716426990549, 1716430590549, '2024-05-23 09:16:30', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1793532698949058561, 1782061165294747650, 1793532680041136130, 1716446492316, 1716450092316, '2024-05-23 14:41:32', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1793929030373306369, 1782061827072032770, 1793532680041136130, 1716540985084, 1716544585084, '2024-05-24 16:56:25', null);
INSERT INTO zoj.count_down (id, userId, contestId, startTimeStamp, endTimeStamp, createTime, updateTime) VALUES (1794183280357597186, 1782061165294747650, 1794183266034049026, 1716601603014, 1716605203014, '2024-05-25 09:46:43', null);

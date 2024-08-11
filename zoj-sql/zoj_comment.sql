create table comment
(
    id              bigint auto_increment
        primary key,
    postId          bigint            null comment '帖子id',
    rootId          bigint default -1 null comment '根评论id',
    content         varchar(512)      null comment '评论内容',
    toCommentUserId bigint default -1 null comment '所回复的目标评论的userid',
    toCommentId     bigint default -1 null comment '回复目标评论id',
    createBy        bigint            null,
    createTime      datetime          null,
    updateBy        bigint            null,
    updateTime      datetime          null,
    isDelete        int    default 0  null comment '删除标志（0代表未删除，1代表已删除）'
)
    comment '评论表';

INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (70, 1782274422697730050, -1, '不过你这力扣一道题也没刷，怎么谈得上数据结构还不错，有实践过吗？（纯好奇）[疑问]', -1, -1, 1782061827072032770, '2024-04-22 13:06:14', 1782061827072032770, '2024-04-22 13:06:14', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (71, 1782274422697730050, -1, '还来得及的话 ssm mysql docker git', -1, -1, 1782061852149776385, '2024-04-22 13:06:43', 1782061852149776385, '2024-04-22 13:06:43', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (72, 1782274422697730050, -1, '后端至少得会springboot和redis吧 然后八股文也得熟', -1, -1, 1782061917853548546, '2024-04-22 13:07:13', 1782061917853548546, '2024-04-22 13:07:13', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (73, 1782274422697730050, -1, '看你对工作的要求了，要求高点还是考个研吧[偷笑]', -1, -1, 1782061982609408002, '2024-04-22 13:07:59', 1782061982609408002, '2024-04-22 13:07:59', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (74, 1782274422697730050, -1, '都会一点点是会什么，到了什么程度了，是会基础语法还是说语言对应的开发框架和高级特性都掌握了一部分？
如果想走Java的话培训班一把梭吧[棒棒]', -1, -1, 1782062063676915713, '2024-04-22 13:08:30', 1782062063676915713, '2024-04-22 13:08:30', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (75, 1782274422697730050, 71, '大佬，学习顺序能不能说一下[心]', 1782061852149776385, 71, 1782062139048558594, '2024-04-22 13:09:37', 1782062139048558594, '2024-04-22 13:09:37', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (76, 1782274422697730050, 71, '先把java基础搞好，然后ssm 框架去找个网课过一下，mysql 也同理，其他就按部就班学嘛', 1782062139048558594, 75, 1782061852149776385, '2024-04-22 13:11:46', 1782061852149776385, '2024-04-22 13:11:46', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (77, 1782274422697730050, 71, 'okok,感谢感谢[抱抱]', 1782061852149776385, 76, 1782062139048558594, '2024-04-22 13:12:53', 1782062139048558594, '2024-04-22 13:12:53', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (78, 1782274422697730050, 73, '现在考研不好考啊[泪]', 1782061982609408002, 73, 1782062139048558594, '2024-04-22 13:14:03', 1782062139048558594, '2024-04-22 13:14:03', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (79, 1782278351141576705, -1, '前排围观[哈哈]', -1, -1, 1782061672771977217, '2024-04-22 13:21:53', 1782061672771977217, '2024-04-22 13:21:53', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (80, 1782278351141576705, -1, '题目太少，不够做，根本不够做[挖鼻]', -1, -1, 1782061852149776385, '2024-04-22 13:23:23', 1782061852149776385, '2024-04-22 13:23:23', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (81, 1782278351141576705, -1, '第一', -1, -1, 1782061917853548546, '2024-04-22 13:24:40', 1782061917853548546, '2024-04-22 13:24:40', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (82, 1782278351141576705, -1, '大大，能不能加个排行榜寻找功能啊，我找不到我的排名[泪]', -1, -1, 1782061982609408002, '2024-04-22 13:26:36', 1782061982609408002, '2024-04-22 13:26:36', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (83, 1782278351141576705, -1, '[鼓掌]', -1, -1, 1782062008077221889, '2024-04-22 13:30:43', 1782062008077221889, '2024-04-22 13:30:43', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (84, 1782278351141576705, 81, '第二', 1782061917853548546, 81, 1782062063676915713, '2024-04-22 13:31:22', 1782062063676915713, '2024-04-22 13:31:22', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (85, 1782278351141576705, -1, '[哈哈]', -1, -1, 1782062063676915713, '2024-04-22 13:31:50', 1782062063676915713, '2024-04-22 13:31:50', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (86, 1782278351141576705, 82, '对啊对啊[生病]', 1782061982609408002, 82, 1782062139048558594, '2024-04-22 13:33:32', 1782062139048558594, '2024-04-22 13:33:32', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (87, 1782274422697730050, -1, '[棒棒]', -1, -1, 1782061954323021825, '2024-04-22 13:53:12', 1782061954323021825, '2024-04-22 13:53:12', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (88, 1782274422697730050, -1, '测试讨论[嘻嘻]', -1, -1, 1782061165294747650, '2024-05-01 17:38:41', 1782061165294747650, '2024-05-01 17:38:41', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (89, 1782274422697730050, -1, '建议美团大厂直招', -1, -1, 1787458805113102337, '2024-05-06 20:27:56', 1787458805113102337, '2024-05-06 20:27:56', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (90, 1782278351141576705, -1, '[睡]', -1, -1, 1782061165294747650, '2024-05-07 09:18:31', 1782061165294747650, '2024-05-07 09:18:31', 0);
INSERT INTO zoj.comment (id, postId, rootId, content, toCommentUserId, toCommentId, createBy, createTime, updateBy, updateTime, isDelete) VALUES (91, 1782274422697730050, -1, 'haha', -1, -1, 1782061165294747650, '2024-05-25 09:39:00', 1782061165294747650, '2024-05-25 09:39:00', 0);

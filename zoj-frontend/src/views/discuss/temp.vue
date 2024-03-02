<!--<template></template>-->
<!--<script>-->
<!--import {-->
<!--  sendComment,-->
<!--  getArticleComment,-->
<!--  getLinkComment,-->
<!--} from "../api/comment.js";-->
<!--import { getToken } from "../utils/auth.js";-->

<!--export default {-->
<!--  data() {-->
<!--    //选项 / 数据-->
<!--    return {-->
<!--      respondBox: "", //评论表单-->
<!--      listDom: "", //评论列表-->
<!--      tmsgBox: "", //总评论盒子-->
<!--      // 查询参数-->
<!--      queryParams: {-->
<!--        pageNum: 1,-->
<!--        pageSize: 10,-->
<!--        articleId: 0,-->
<!--      },-->
<!--      isRespond: false,-->
<!--      textarea: "", //文本框输入内容-->
<!--      pBody: true, //表情打开控制-->
<!--      commentList: [], //评论列表数据-->
<!--      aid: 0, //文章id-->
<!--      hasMore: false,-->
<!--      haslogin: false,-->
<!--      userId: "", //用户id-->
<!--      type: 0, //回复评论的当前的commentId-->
<!--      leavePid: "", //赞赏等其他模块的分类id-->
<!--      pid: "", //回复评论的一级commentId-->
<!--      rootId: -1, //根评论id，如果是针对文字评论直接用-1表示-->
<!--      toCommentId: -1, //所回复评论的id-->
<!--      toCommentUserId: -1, //所评论的用户id-->
<!--      sendTip: "发送~",-->
<!--      OwOlist: [-->
<!--        //表情包和表情路径-->
<!--        { title: "微笑", url: "weixiao.gif" },-->
<!--        { title: "嘻嘻", url: "xixi.gif" },-->
<!--        { title: "哈哈", url: "haha.gif" },-->
<!--        { title: "可爱", url: "keai.gif" },-->
<!--        { title: "可怜", url: "kelian.gif" },-->
<!--        { title: "挖鼻", url: "wabi.gif" },-->
<!--        { title: "吃惊", url: "chijing.gif" },-->
<!--        { title: "害羞", url: "haixiu.gif" },-->
<!--        { title: "挤眼", url: "jiyan.gif" },-->
<!--        { title: "闭嘴", url: "bizui.gif" },-->
<!--        { title: "鄙视", url: "bishi.gif" },-->
<!--        { title: "爱你", url: "aini.gif" },-->
<!--        { title: "泪", url: "lei.gif" },-->
<!--        { title: "偷笑", url: "touxiao.gif" },-->
<!--        { title: "亲亲", url: "qinqin.gif" },-->
<!--        { title: "生病", url: "shengbing.gif" },-->
<!--        { title: "太开心", url: "taikaixin.gif" },-->
<!--        { title: "白眼", url: "baiyan.gif" },-->
<!--        { title: "右哼哼", url: "youhengheng.gif" },-->
<!--        { title: "左哼哼", url: "zuohengheng.gif" },-->
<!--        { title: "嘘", url: "xu.gif" },-->
<!--        { title: "衰", url: "shuai.gif" },-->
<!--        { title: "吐", url: "tu.gif" },-->
<!--        { title: "哈欠", url: "haqian.gif" },-->
<!--        { title: "抱抱", url: "baobao.gif" },-->
<!--        { title: "怒", url: "nu.gif" },-->
<!--        { title: "疑问", url: "yiwen.gif" },-->
<!--        { title: "馋嘴", url: "chanzui.gif" },-->
<!--        { title: "拜拜", url: "baibai.gif" },-->
<!--        { title: "思考", url: "sikao.gif" },-->
<!--        { title: "汗", url: "han.gif" },-->
<!--        { title: "困", url: "kun.gif" },-->
<!--        { title: "睡", url: "shui.gif" },-->
<!--        { title: "钱", url: "qian.gif" },-->
<!--        { title: "失望", url: "shiwang.gif" },-->
<!--        { title: "酷", url: "ku.gif" },-->
<!--        { title: "色", url: "se.gif" },-->
<!--        { title: "哼", url: "heng.gif" },-->
<!--        { title: "鼓掌", url: "guzhang.gif" },-->
<!--        { title: "晕", url: "yun.gif" },-->
<!--        { title: "悲伤", url: "beishang.gif" },-->
<!--        { title: "抓狂", url: "zhuakuang.gif" },-->
<!--        { title: "黑线", url: "heixian.gif" },-->
<!--        { title: "阴险", url: "yinxian.gif" },-->
<!--        { title: "怒骂", url: "numa.gif" },-->
<!--        { title: "互粉", url: "hufen.gif" },-->
<!--        { title: "书呆子", url: "shudaizi.gif" },-->
<!--        { title: "愤怒", url: "fennu.gif" },-->
<!--        { title: "感冒", url: "ganmao.gif" },-->
<!--        { title: "心", url: "xin.gif" },-->
<!--        { title: "伤心", url: "shangxin.gif" },-->
<!--        { title: "猪", url: "zhu.gif" },-->
<!--        { title: "熊猫", url: "xiongmao.gif" },-->
<!--        { title: "兔子", url: "tuzi.gif" },-->
<!--        { title: "喔克", url: "ok.gif" },-->
<!--        { title: "耶", url: "ye.gif" },-->
<!--        { title: "棒棒", url: "good.gif" },-->
<!--        { title: "不", url: "no.gif" },-->
<!--        { title: "赞", url: "zan.gif" },-->
<!--        { title: "来", url: "lai.gif" },-->
<!--        { title: "弱", url: "ruo.gif" },-->
<!--        { title: "草泥马", url: "caonima.gif" },-->
<!--        { title: "神马", url: "shenma.gif" },-->
<!--        { title: "囧", url: "jiong.gif" },-->
<!--        { title: "浮云", url: "fuyun.gif" },-->
<!--        { title: "给力", url: "geili.gif" },-->
<!--        { title: "围观", url: "weiguan.gif" },-->
<!--        { title: "威武", url: "weiwu.gif" },-->
<!--        { title: "话筒", url: "huatong.gif" },-->
<!--        { title: "蜡烛", url: "lazhu.gif" },-->
<!--        { title: "蛋糕", url: "dangao.gif" },-->
<!--        { title: "发红包", url: "fahongbao.gif" },-->
<!--      ],-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    //事件处理器-->
<!--    setData(initData, result) {-->
<!--      var msg = result.rows;-->
<!--      if (initData) {-->
<!--        //刷新列表-->
<!--        this.commentList = msg;-->
<!--      } else {-->
<!--        //加载更多-->
<!--        this.commentList = this.commentList.concat(msg);-->
<!--      }-->

<!--      this.hasMore = result.total > this.commentList.length;-->
<!--    },-->
<!--    //选择表情包-->
<!--    choseEmoji: function (inner) {-->
<!--      this.textarea += "[" + inner + "]";-->
<!--    },-->
<!--    analyzeEmoji: function (cont) {-->
<!--      //编译表情替换成图片展示出来-->
<!--      var pattern1 = /\[[\u4e00-\u9fa5]+\]/g;-->
<!--      var pattern2 = /\[[\u4e00-\u9fa5]+\]/;-->
<!--      var content = cont.match(pattern1);-->
<!--      var str = cont;-->
<!--      if (content) {-->
<!--        for (var i = 0; i < content.length; i++) {-->
<!--          for (var j = 0; j < this.OwOlist.length; j++) {-->
<!--            if ("[" + this.OwOlist[j].title + "]" == content[i]) {-->
<!--              var src = this.OwOlist[j].url;-->
<!--              break;-->
<!--            }-->
<!--          }-->
<!--          str = str.replace(-->
<!--            pattern2,-->
<!--            '<img src="static/img/emot/image/' + src + '" />'-->
<!--          );-->
<!--        }-->
<!--        // console.log(str);-->
<!--      }-->
<!--      return str;-->
<!--    },-->
<!--    //发送留言-->
<!--    sendMsg: function () {-->
<!--      //留言-->
<!--      var that = this;-->
<!--      if (that.textarea) {-->
<!--        that.sendTip = "咻~~";-->
<!--        sendComment(-->
<!--          that.type,-->
<!--          that.aid,-->
<!--          that.rootId,-->
<!--          that.toCommentId,-->
<!--          that.toCommentUserId,-->
<!--          that.textarea-->
<!--        ).then((response) => {-->
<!--          that.textarea = "";-->
<!--          that.rootId = -1;-->
<!--          that.toCommentId = -1;-->
<!--          that.toCommentUserId = -1;-->

<!--          that.routeChange();-->
<!--          that.removeRespond();-->
<!--          var timer02 = setTimeout(function () {-->
<!--            that.sendTip = "发送~";-->
<!--            clearTimeout(timer02);-->
<!--          }, 1000);-->
<!--        });-->
<!--      } else {-->
<!--        that.sendTip = "内容不能为空~";-->
<!--        var timer = setTimeout(function () {-->
<!--          that.sendTip = "发送~";-->
<!--          clearTimeout(timer);-->
<!--        }, 3000);-->
<!--      }-->
<!--    },-->
<!--    respondMsg: function (rootId, toCommentId, toCommentUserId) {-->
<!--      //回复留言-->
<!--      // console.log(leavePid,pid);-->
<!--      var that = this;-->
<!--      if (localStorage.getItem("userInfo")) {-->
<!--        var dom = event.currentTarget;-->
<!--        dom = dom.parentNode;-->
<!--        this.isRespond = true;-->
<!--        //   this.leavePid = leavePid;-->
<!--        this.rootId = rootId;-->
<!--        this.toCommentId = toCommentId;-->
<!--        this.toCommentUserId = toCommentUserId;-->
<!--        dom.appendChild(this.$refs.respondBox);-->
<!--      } else {-->
<!--        that-->
<!--          .$confirm("登录后即可点赞和收藏，是否前往登录页面?", "提示", {-->
<!--            confirmButtonText: "确定",-->
<!--            cancelButtonText: "取消",-->
<!--            type: "warning",-->
<!--          })-->
<!--          .then(() => {-->
<!--            //确定，跳转至登录页面-->
<!--            //储存当前页面路径，登录成功后跳回来-->
<!--            localStorage.setItem("logUrl", that.$route.fullPath);-->
<!--            that.$router.push({ path: "/Login?login=1" });-->
<!--          })-->
<!--          .catch(() => {});-->
<!--      }-->
<!--    },-->
<!--    removeRespond: function () {-->
<!--      //取消回复留言-->
<!--      this.isRespond = false;-->
<!--      this.rootId = -1;-->
<!--      this.toCommentId = -1;-->
<!--      this.toCommentUserId = -1;-->
<!--      this.$refs.tmsgBox.insertBefore(-->
<!--        this.$refs.respondBox,-->
<!--        this.$refs.listDom-->
<!--      );-->
<!--    },-->
<!--    showCommentList: function (initData) {-->
<!--      //评论列表-->
<!--      var that = this;-->
<!--      that.aid =-->
<!--        that.$route.query.aid == undefined-->
<!--          ? 1-->
<!--          : parseInt(that.$route.query.aid); //获取传参的aid-->
<!--      that.queryParams.articleId = that.aid;-->
<!--      //判断当前用户是否登录-->
<!--      var token = getToken();-->
<!--      if (token) {-->
<!--        that.haslogin = true;-->
<!--      } else {-->
<!--        that.haslogin = false;-->
<!--      }-->

<!--      //公用设置数据方法-->

<!--      if (that.$route.name == "DetailArticle") {-->
<!--        //文章列表的评论-->
<!--        that.type = 0;-->
<!--        getArticleComment(that.queryParams).then((response) => {-->
<!--          that.setData(initData, response);-->
<!--        });-->
<!--      } else {-->
<!--        //其他评论-->
<!--        if (that.$route.name == "FriendsLink") {-->
<!--          that.type = 1;-->
<!--          getLinkComment(that.queryParams).then((response) => {-->
<!--            that.setData(initData, response);-->
<!--          });-->
<!--        }-->
<!--      }-->
<!--    },-->
<!--    addMoreFun: function () {-->
<!--      //查看更多-->
<!--      this.queryParams.pageNum++;-->
<!--      this.showCommentList(false);-->
<!--    },-->
<!--    routeChange: function () {-->
<!--      //重新加载-->
<!--      var that = this;-->
<!--      this.queryParams.pageNum = 1;-->
<!--      this.showCommentList(true);-->
<!--    },-->
<!--  },-->
<!--  components: {-->
<!--    //定义组件-->
<!--  },-->
<!--  watch: {-->
<!--    // 如果路由有变化，会再次执行该方法-->
<!--    // '$route':'routeChange'-->
<!--  },-->
<!--  created() {-->
<!--    //生命周期函数-->
<!--    // console.log(this.$route);-->
<!--    var that = this;-->
<!--    that.routeChange();-->
<!--  },-->
<!--  mounted() {-->
<!--    //页面加载完成后-->
<!--  },-->
<!--};-->
<!--</script>-->

<!--<style scoped></style>-->

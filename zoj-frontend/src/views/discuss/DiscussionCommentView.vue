<!-- 留言评论模块 -->
<template>
  <div class="tmsgBox" ref="tmsgBox">
    <div class="tmsg-respond" ref="respondBox">
      <h3>
        发表评论
        <small v-show="isRespond" style="color: #64609e" @click="removeRespond"
          >取消回复</small
        >
      </h3>
      <form>
        <a-space
          direction="vertical"
          size="large"
          fill
          style="padding-bottom: 20px"
        >
          <a-textarea
            v-model="textarea"
            placeholder="说点什么呢``"
            :max-length="{ length: 1000, errorOnly: true }"
            allow-clear
            show-word-limit
          />
        </a-space>
        <div :class="pBody ? 'OwO' : 'OwO OwO-open'">
          <div class="OwO-logo" @click="pBody = !pBody">
            <span>OwO表情</span>
          </div>
          <div class="OwO-body">
            <ul class="OwO-items OwO-items-show">
              <li
                class="OwO-item"
                v-for="(oitem, index) in OwOlist"
                :key="'oitem' + index"
                @click="choseEmoji(oitem.title)"
              >
                <img :src="require(`../../static/emot/${oitem.url}`)" alt="" />
              </li>
            </ul>
            <div class="OwO-bar">
              <ul class="OwO-packages">
                <li class="OwO-package-active">Emoji</li>
              </ul>
            </div>
          </div>
        </div>
        <a-space direction="vertical" fill style="padding-top: 15px">
          <a-button type="primary" long @click="sendMsg">
            {{ sendTip }}
          </a-button>
        </a-space>
      </form>
    </div>

    <div class="tmsg-comments" ref="listDom">
      <a class="tmsg-comments-tip"
        >活捉 {{ commentList ? commentList.length : 0 }} 条</a
      >
      <div class="tmsg-commentshow">
        <div v-for="(item, index) in commentList" :key="'common' + index">
          <a-comment
            style="font-size: 17px"
            :author="item.username"
            :avatar="item.userAvatar"
            :datetime="moment(item.createTime).format('YYYY-MM-DD HH:mm:ss')"
          >
            <template #content>
              <p v-html="analyzeEmoji(item.content)"></p>
            </template>
            <template #actions>
              <span
                v-if="hasLogin"
                class="action"
                @click="respondMsg(item.id, item.id, item.createBy, $event)"
              >
                <IconMessage /> Reply
              </span>
            </template>
            <div
              v-show="item.children"
              v-for="(citem, cindex) in item.children"
              :key="'citem' + cindex"
            >
              <a-comment
                style="font-size: 17px"
                :avatar="citem.userAvatar"
                :datetime="
                  moment(citem.createTime).format('YYYY-MM-DD HH:mm:ss')
                "
              >
                <template #author>
                  {{ citem.username }} <span>回复 </span>
                  <span style="color: #00bfff">{{
                    citem.toCommentUserName
                  }}</span>
                </template>
                <template #content>
                  <p v-html="analyzeEmoji(citem.content)"></p>
                </template>
                <template #actions>
                  <span
                    v-show="hasLogin"
                    class="action"
                    @click="
                      respondMsg(item.id, citem.id, citem.createBy, $event)
                    "
                  >
                    <IconMessage /> Reply
                  </span>
                </template>
              </a-comment>
            </div>
          </a-comment>
        </div>
        <h3 v-show="hasMore" class="tcolors-bg" @click="addMoreFun">
          查看更多
        </h3>
        <h3 v-show="!hasMore" class="tcolors-bg">没有更多</h3>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import DiscussionCommentView from "@/views/discuss/DiscussionCommentView.vue";
import { IconHeart, IconMessage, IconStar } from "@arco-design/web-vue/es/icon";
import moment from "moment";
import { useRouter } from "vue-router";
import { onMounted, ref, watchEffect, defineProps, withDefaults } from "vue";
import {
  CommentAddRequest,
  CommentControllerService,
  CommentQueryRequest,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { defineExpose } from "vue";
import { useStore } from "vuex";

interface Props {
  id: string;
}

const props = withDefaults(defineProps<Props>(), {
  id: () => "",
});

const hasMore = ref(false); // 是否有更多

const sendTip = ref("发送");
// const mdid = BigInt(1759947916743127042);
const textarea = ref("");

const commentList = ref([]); //

const hasLogin = ref(false);

const store = useStore();
// 使用 ref 创建响应式数据

const router = useRouter();

const isRespond = ref(false);
const respondBox = ref<HTMLElement | null>(null); // 假设 respondBox 是一个元素引用
const tmsgBox = ref<HTMLElement | null>(null); // 假设 respondBox 是一个元素引用
const listDom = ref<HTMLElement | null>(null); // 假设 respondBox 是一个元素引用

const queryParams = ref({
  current: 1,
  pageSize: 10,
  postId: props.id,
}) as any;

const addParams = ref({
  postId: props.id,
  rootId: -1,
  toCommentId: -1,
  toCommentUserId: -1,
}) as any;

const pBody = ref(true);

const OwOlist = ref([
  { title: "微笑", url: "weixiao.gif" },
  { title: "嘻嘻", url: "xixi.gif" },
  { title: "哈哈", url: "haha.gif" },
  { title: "可爱", url: "keai.gif" },
  { title: "可怜", url: "kelian.gif" },
  { title: "挖鼻", url: "wabi.gif" },
  { title: "吃惊", url: "chijing.gif" },
  { title: "害羞", url: "haixiu.gif" },
  { title: "挤眼", url: "jiyan.gif" },
  { title: "闭嘴", url: "bizui.gif" },
  { title: "鄙视", url: "bishi.gif" },
  { title: "爱你", url: "aini.gif" },
  { title: "泪", url: "lei.gif" },
  { title: "偷笑", url: "touxiao.gif" },
  { title: "亲亲", url: "qinqin.gif" },
  { title: "生病", url: "shengbing.gif" },
  { title: "太开心", url: "taikaixin.gif" },
  { title: "白眼", url: "baiyan.gif" },
  { title: "右哼哼", url: "youhengheng.gif" },
  { title: "左哼哼", url: "zuohengheng.gif" },
  { title: "嘘", url: "xu.gif" },
  { title: "衰", url: "shuai.gif" },
  { title: "吐", url: "tu.gif" },
  { title: "哈欠", url: "haqian.gif" },
  { title: "抱抱", url: "baobao.gif" },
  { title: "怒", url: "nu.gif" },
  { title: "疑问", url: "yiwen.gif" },
  { title: "馋嘴", url: "chanzui.gif" },
  { title: "拜拜", url: "baibai.gif" },
  { title: "思考", url: "sikao.gif" },
  { title: "汗", url: "han.gif" },
  { title: "困", url: "kun.gif" },
  { title: "睡", url: "shui.gif" },
  { title: "钱", url: "qian.gif" },
  { title: "失望", url: "shiwang.gif" },
  { title: "酷", url: "ku.gif" },
  { title: "色", url: "se.gif" },
  { title: "哼", url: "heng.gif" },
  { title: "鼓掌", url: "guzhang.gif" },
  { title: "晕", url: "yun.gif" },
  { title: "悲伤", url: "beishang.gif" },
  { title: "抓狂", url: "zhuakuang.gif" },
  { title: "黑线", url: "heixian.gif" },
  { title: "阴险", url: "yinxian.gif" },
  { title: "怒骂", url: "numa.gif" },
  { title: "互粉", url: "hufen.gif" },
  { title: "书呆子", url: "shudaizi.gif" },
  { title: "愤怒", url: "fennu.gif" },
  { title: "感冒", url: "ganmao.gif" },
  { title: "心", url: "xin.gif" },
  { title: "伤心", url: "shangxin.gif" },
  { title: "猪", url: "zhu.gif" },
  { title: "熊猫", url: "xiongmao.gif" },
  { title: "兔子", url: "tuzi.gif" },
  { title: "喔克", url: "ok.gif" },
  { title: "耶", url: "ye.gif" },
  { title: "棒棒", url: "good.gif" },
  { title: "不", url: "no.gif" },
  { title: "赞", url: "zan.gif" },
  { title: "来", url: "lai.gif" },
  { title: "弱", url: "ruo.gif" },
  { title: "草泥马", url: "caonima.gif" },
  { title: "神马", url: "shenma.gif" },
  { title: "囧", url: "jiong.gif" },
  { title: "浮云", url: "fuyun.gif" },
  { title: "给力", url: "geili.gif" },
  { title: "围观", url: "weiguan.gif" },
  { title: "威武", url: "weiwu.gif" },
  { title: "话筒", url: "huatong.gif" },
  { title: "蜡烛", url: "lazhu.gif" },
  { title: "蛋糕", url: "dangao.gif" },
  { title: "发红包", url: "fahongbao.gif" },
]);
/**
 * 页面加载时，请求数据
 */
onMounted(() => {
  loadData();
});
const loadData = async () => {
  const res = await CommentControllerService.listCommentVoByPageUsingPost({
    ...queryParams.value,
  });
  if (res.code === 0) {
    commentList.value = commentList.value.concat(res.data.records);
  } else {
    message.error("加载失败，" + res.message);
  }
  if (islogin()) {
    hasLogin.value = true;
  } else {
    hasLogin.value = false;
  }
  hasMore.value = res.data.total > commentList.value.length;
};
//选择表情包
const choseEmoji = (inner: any) => {
  textarea.value += "[" + inner + "]";
};
const analyzeEmoji = (cont: any) => {
  //编译表情替换成图片展示出来
  var pattern1 = /\[[\u4e00-\u9fa5]+\]/g;
  var pattern2 = /\[[\u4e00-\u9fa5]+\]/;
  var content = cont.match(pattern1);
  var str = cont;
  var src = "";
  if (content) {
    for (var i = 0; i < content.length; i++) {
      for (var j = 0; j < OwOlist.value.length; j++) {
        if ("[" + OwOlist.value[j].title + "]" == content[i]) {
          src = require(`../../static/emot/${OwOlist.value[j].url}`);
          break;
        }
      }
      str = str.replace(pattern2, '<img src="' + src + '"/>');
    }
    console.log(str);
  }
  return str;
};
const islogin = () => {
  return store.state.user?.loginUser?.userName !== "未登录";
};
const respondMsg = (
  rootid: number,
  toCommentid: number,
  toCommentUserid: number,
  event: MouseEvent
) => {
  //回复留言
  if (islogin()) {
    const dom = event.currentTarget as HTMLElement;
    const parentDom = dom.parentNode;
    // 更新 isRespond 的值
    isRespond.value = true;
    addParams.value.rootId = rootid;
    addParams.value.toCommentId = toCommentid;
    addParams.value.toCommentUserId = toCommentUserid;
    // 假设你要将 respondBox 添加到父元素中
    if (parentDom && respondBox.value) {
      parentDom.appendChild(respondBox.value);
    }
  } else {
    if (confirm("登录后即可点赞和收藏，是否前往登录页面?")) {
      // 用户点击了“确定”
      // 存储当前页面路径，登录成功后跳回来
      localStorage.setItem("logUrl", router.currentRoute.value.fullPath);
      router.push({ path: "/user/login" });
    } else {
      // 用户点击了“取消”
      // 可以在这里执行取消操作，如果需要的话
    }
  }
};

// sendMsg 函数
const sendMsg = async () => {
  if (textarea.value) {
    sendTip.value = "咻~~";
    const res = await CommentControllerService.addCommentUsingPost({
      ...addParams.value,
      content: textarea.value,
    });
    if (res.code === 0) {
      addParams.value.rootId = -1;
      addParams.value.toCommentId = -1;
      addParams.value.toCommentUserId = -1;
      textarea.value = "";

      routeChange();
      removeRespond();
      var timer02 = setTimeout(function () {
        sendTip.value = "发送~";
        clearTimeout(timer02);
      }, 1000);
    } else {
      message.error("评论失败,请先登录");
    }
  } else {
    sendTip.value = "内容不能为空~";
    const timer = setTimeout(() => {
      sendTip.value = "发送~";
      clearTimeout(timer);
    }, 3000);
  }
};

const routeChange = () => {
  //重新加载
  //是否要 重置 current（当前页面数量）取决于你
  // queryParams.value.current = 1;
  loadData();
};

const removeRespond = () => {
  //取消回复留言
  isRespond.value = false;
  addParams.value.rootId = -1;
  addParams.value.toCommentId = -1;
  addParams.value.toCommentUserId = -1;
  if (tmsgBox.value && respondBox.value && listDom.value) {
    tmsgBox.value.insertBefore(respondBox.value, listDom.value);
  }
};

const addMoreFun = () => {
  //查看更多
  queryParams.value.current++;
  loadData();
};

defineExpose({
  respondMsg,
});
</script>

<style scoped>
.tcolors-bg {
  text-align: center;
  background: #97dffd;
  transition: all 0.3s ease-in-out;
}

.tcolors-bg:hover {
  background: #48456d;
}

.action {
  display: inline-block;
  padding: 0 4px;
  color: var(--color-text-1);
  line-height: 24px;
  background: transparent;
  border-radius: 2px;
  cursor: pointer;
  transition: all 0.1s ease;
}

.action:hover {
  background: var(--color-fill-3);
}

.tmsgBox {
  position: relative;
  background: #fff;
  padding: 15px;
  margin-bottom: 20px;
  border-radius: 5px;
}

.tmsg-respond h3 {
  font-size: 18px;
  font-weight: bold;
  margin-bottom: 8px;
}

.tmsg-respond h3 small {
  font-size: smaller;
  cursor: pointer;
}

.tmsg-respond textarea {
  background: #f4f6f7;
  height: 100px;
  margin-bottom: 10px;
}

.OwO {
  position: relative;
  z-index: 1;
}

.OwO .OwO-logo {
  position: relative;
  border-radius: 4px;
  color: #444;
  display: inline-block;
  background: #fff;
  border: 1px solid #ddd;
  font-size: 13px;
  padding: 0 6px;
  cursor: pointer;
  height: 30px;
  box-sizing: border-box;
  z-index: 2;
  line-height: 30px;
}

.OwO .OwO-logo:hover {
  animation: a 5s infinite ease-in-out;
  -webkit-animation: a 5s infinite ease-in-out;
}

.OwO .OwO-body {
  position: absolute;
  background: #fff;
  border: 1px solid #ddd;
  z-index: 1;
  top: 29px;
  border-radius: 0 4px 4px 4px;
  display: none;
}

.OwO-open .OwO-body {
  display: block;
}

.OwO-open .OwO-logo {
  border-radius: 4px 4px 0 0;
  border-bottom: none;
}

.OwO-open .OwO-logo:hover {
  animation: none;
  -webkit-animation: none;
}

.OwO .OwO-items {
  max-height: 197px;
  overflow: scroll;
  font-size: 0;
  padding: 10px;
  z-index: 1;
}

.OwO .OwO-items .OwO-item {
  background: #f7f7f7;
  padding: 5px 10px;
  border-radius: 5px;
  display: inline-block;
  margin: 0 10px 12px 0;
  transition: 0.3s;
  line-height: 19px;
  font-size: 20px;
  cursor: pointer;
}

.OwO .OwO-items .OwO-item:hover {
  background: #eee;
  box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.2),
    0 1px 5px 0 rgba(0, 0, 0, 0.12);
  animation: a 5s infinite ease-in-out;
  -webkit-animation: a 5s infinite ease-in-out;
}

.OwO .OwO-body .OwO-bar {
  width: 100%;
  height: 30px;
  border-top: 1px solid #ddd;
  background: #fff;
  border-radius: 0 0 4px 4px;
  color: #444;
}

.OwO .OwO-body .OwO-bar .OwO-packages li {
  display: inline-block;
  line-height: 30px;
  font-size: 14px;
  padding: 0 10px;
  cursor: pointer;
  margin-right: 3px;
  text-align: center;
}

.OwO .OwO-body .OwO-bar .OwO-packages li:first-of-type {
  border-radius: 0 0 0 3px;
}

@-webkit-keyframes a {
  2% {
    -webkit-transform: translateY(1.5px) rotate(1.5deg);
    transform: translateY(1.5px) rotate(1.5deg);
  }
  4% {
    -webkit-transform: translateY(-1.5px) rotate(-0.5deg);
    transform: translateY(-1.5px) rotate(-0.5deg);
  }
  6% {
    -webkit-transform: translateY(1.5px) rotate(-1.5deg);
    transform: translateY(1.5px) rotate(-1.5deg);
  }
  8% {
    -webkit-transform: translateY(-1.5px) rotate(-1.5deg);
    transform: translateY(-1.5px) rotate(-1.5deg);
  }
  10% {
    -webkit-transform: translateY(2.5px) rotate(1.5deg);
    transform: translateY(2.5px) rotate(1.5deg);
  }
  12% {
    -webkit-transform: translateY(-0.5px) rotate(1.5deg);
    transform: translateY(-0.5px) rotate(1.5deg);
  }
  14% {
    -webkit-transform: translateY(-1.5px) rotate(1.5deg);
    transform: translateY(-1.5px) rotate(1.5deg);
  }
  16% {
    -webkit-transform: translateY(-0.5px) rotate(-1.5deg);
    transform: translateY(-0.5px) rotate(-1.5deg);
  }
  18% {
    -webkit-transform: translateY(0.5px) rotate(-1.5deg);
    transform: translateY(0.5px) rotate(-1.5deg);
  }
  20% {
    -webkit-transform: translateY(-1.5px) rotate(2.5deg);
    transform: translateY(-1.5px) rotate(2.5deg);
  }
  22% {
    -webkit-transform: translateY(0.5px) rotate(-1.5deg);
    transform: translateY(0.5px) rotate(-1.5deg);
  }
  24% {
    -webkit-transform: translateY(1.5px) rotate(1.5deg);
    transform: translateY(1.5px) rotate(1.5deg);
  }
  26% {
    -webkit-transform: translateY(0.5px) rotate(0.5deg);
    transform: translateY(0.5px) rotate(0.5deg);
  }
  28% {
    -webkit-transform: translateY(0.5px) rotate(1.5deg);
    transform: translateY(0.5px) rotate(1.5deg);
  }
  30% {
    -webkit-transform: translateY(-0.5px) rotate(2.5deg);
    transform: translateY(-0.5px) rotate(2.5deg);
  }
  32%,
  34% {
    -webkit-transform: translateY(1.5px) rotate(-0.5deg);
    transform: translateY(1.5px) rotate(-0.5deg);
  }
  36% {
    -webkit-transform: translateY(-1.5px) rotate(2.5deg);
    transform: translateY(-1.5px) rotate(2.5deg);
  }
  38% {
    -webkit-transform: translateY(1.5px) rotate(-1.5deg);
    transform: translateY(1.5px) rotate(-1.5deg);
  }
  40% {
    -webkit-transform: translateY(-0.5px) rotate(2.5deg);
    transform: translateY(-0.5px) rotate(2.5deg);
  }
  42% {
    -webkit-transform: translateY(2.5px) rotate(-1.5deg);
    transform: translateY(2.5px) rotate(-1.5deg);
  }
  44% {
    -webkit-transform: translateY(1.5px) rotate(0.5deg);
    transform: translateY(1.5px) rotate(0.5deg);
  }
  46% {
    -webkit-transform: translateY(-1.5px) rotate(2.5deg);
    transform: translateY(-1.5px) rotate(2.5deg);
  }
  48% {
    -webkit-transform: translateY(-0.5px) rotate(0.5deg);
    transform: translateY(-0.5px) rotate(0.5deg);
  }
  50% {
    -webkit-transform: translateY(0.5px) rotate(0.5deg);
    transform: translateY(0.5px) rotate(0.5deg);
  }
  52% {
    -webkit-transform: translateY(2.5px) rotate(2.5deg);
    transform: translateY(2.5px) rotate(2.5deg);
  }
  54% {
    -webkit-transform: translateY(-1.5px) rotate(1.5deg);
    transform: translateY(-1.5px) rotate(1.5deg);
  }
  56% {
    -webkit-transform: translateY(2.5px) rotate(2.5deg);
    transform: translateY(2.5px) rotate(2.5deg);
  }
  58% {
    -webkit-transform: translateY(0.5px) rotate(2.5deg);
    transform: translateY(0.5px) rotate(2.5deg);
  }
  60% {
    -webkit-transform: translateY(2.5px) rotate(2.5deg);
    transform: translateY(2.5px) rotate(2.5deg);
  }
  62% {
    -webkit-transform: translateY(-0.5px) rotate(2.5deg);
    transform: translateY(-0.5px) rotate(2.5deg);
  }
  64% {
    -webkit-transform: translateY(-0.5px) rotate(1.5deg);
    transform: translateY(-0.5px) rotate(1.5deg);
  }
  66% {
    -webkit-transform: translateY(1.5px) rotate(-0.5deg);
    transform: translateY(1.5px) rotate(-0.5deg);
  }
  68% {
    -webkit-transform: translateY(-1.5px) rotate(-0.5deg);
    transform: translateY(-1.5px) rotate(-0.5deg);
  }
  70% {
    -webkit-transform: translateY(1.5px) rotate(0.5deg);
    transform: translateY(1.5px) rotate(0.5deg);
  }
  72% {
    -webkit-transform: translateY(2.5px) rotate(1.5deg);
    transform: translateY(2.5px) rotate(1.5deg);
  }
  74% {
    -webkit-transform: translateY(-0.5px) rotate(0.5deg);
    transform: translateY(-0.5px) rotate(0.5deg);
  }
  76% {
    -webkit-transform: translateY(-0.5px) rotate(2.5deg);
    transform: translateY(-0.5px) rotate(2.5deg);
  }
  78% {
    -webkit-transform: translateY(-0.5px) rotate(1.5deg);
    transform: translateY(-0.5px) rotate(1.5deg);
  }
  80% {
    -webkit-transform: translateY(1.5px) rotate(1.5deg);
    transform: translateY(1.5px) rotate(1.5deg);
  }
  82% {
    -webkit-transform: translateY(-0.5px) rotate(0.5deg);
    transform: translateY(-0.5px) rotate(0.5deg);
  }
  84% {
    -webkit-transform: translateY(1.5px) rotate(2.5deg);
    transform: translateY(1.5px) rotate(2.5deg);
  }
  86% {
    -webkit-transform: translateY(-1.5px) rotate(-1.5deg);
    transform: translateY(-1.5px) rotate(-1.5deg);
  }
  88% {
    -webkit-transform: translateY(-0.5px) rotate(2.5deg);
    transform: translateY(-0.5px) rotate(2.5deg);
  }
  90% {
    -webkit-transform: translateY(2.5px) rotate(-0.5deg);
    transform: translateY(2.5px) rotate(-0.5deg);
  }
  92% {
    -webkit-transform: translateY(0.5px) rotate(-0.5deg);
    transform: translateY(0.5px) rotate(-0.5deg);
  }
  94% {
    -webkit-transform: translateY(2.5px) rotate(0.5deg);
    transform: translateY(2.5px) rotate(0.5deg);
  }
  96% {
    -webkit-transform: translateY(-0.5px) rotate(1.5deg);
    transform: translateY(-0.5px) rotate(1.5deg);
  }
  98% {
    -webkit-transform: translateY(-1.5px) rotate(-0.5deg);
    transform: translateY(-1.5px) rotate(-0.5deg);
  }
  0%,
  to {
    -webkit-transform: translate(0) rotate(0deg);
    transform: translate(0) rotate(0deg);
  }
}

@keyframes a {
  2% {
    -webkit-transform: translateY(1.5px) rotate(1.5deg);
    transform: translateY(1.5px) rotate(1.5deg);
  }
  4% {
    -webkit-transform: translateY(-1.5px) rotate(-0.5deg);
    transform: translateY(-1.5px) rotate(-0.5deg);
  }
  6% {
    -webkit-transform: translateY(1.5px) rotate(-1.5deg);
    transform: translateY(1.5px) rotate(-1.5deg);
  }
  8% {
    -webkit-transform: translateY(-1.5px) rotate(-1.5deg);
    transform: translateY(-1.5px) rotate(-1.5deg);
  }
  10% {
    -webkit-transform: translateY(2.5px) rotate(1.5deg);
    transform: translateY(2.5px) rotate(1.5deg);
  }
  12% {
    -webkit-transform: translateY(-0.5px) rotate(1.5deg);
    transform: translateY(-0.5px) rotate(1.5deg);
  }
  14% {
    -webkit-transform: translateY(-1.5px) rotate(1.5deg);
    transform: translateY(-1.5px) rotate(1.5deg);
  }
  16% {
    -webkit-transform: translateY(-0.5px) rotate(-1.5deg);
    transform: translateY(-0.5px) rotate(-1.5deg);
  }
  18% {
    -webkit-transform: translateY(0.5px) rotate(-1.5deg);
    transform: translateY(0.5px) rotate(-1.5deg);
  }
  20% {
    -webkit-transform: translateY(-1.5px) rotate(2.5deg);
    transform: translateY(-1.5px) rotate(2.5deg);
  }
  22% {
    -webkit-transform: translateY(0.5px) rotate(-1.5deg);
    transform: translateY(0.5px) rotate(-1.5deg);
  }
  24% {
    -webkit-transform: translateY(1.5px) rotate(1.5deg);
    transform: translateY(1.5px) rotate(1.5deg);
  }
  26% {
    -webkit-transform: translateY(0.5px) rotate(0.5deg);
    transform: translateY(0.5px) rotate(0.5deg);
  }
  28% {
    -webkit-transform: translateY(0.5px) rotate(1.5deg);
    transform: translateY(0.5px) rotate(1.5deg);
  }
  30% {
    -webkit-transform: translateY(-0.5px) rotate(2.5deg);
    transform: translateY(-0.5px) rotate(2.5deg);
  }
  32%,
  34% {
    -webkit-transform: translateY(1.5px) rotate(-0.5deg);
    transform: translateY(1.5px) rotate(-0.5deg);
  }
  36% {
    -webkit-transform: translateY(-1.5px) rotate(2.5deg);
    transform: translateY(-1.5px) rotate(2.5deg);
  }
  38% {
    -webkit-transform: translateY(1.5px) rotate(-1.5deg);
    transform: translateY(1.5px) rotate(-1.5deg);
  }
  40% {
    -webkit-transform: translateY(-0.5px) rotate(2.5deg);
    transform: translateY(-0.5px) rotate(2.5deg);
  }
  42% {
    -webkit-transform: translateY(2.5px) rotate(-1.5deg);
    transform: translateY(2.5px) rotate(-1.5deg);
  }
  44% {
    -webkit-transform: translateY(1.5px) rotate(0.5deg);
    transform: translateY(1.5px) rotate(0.5deg);
  }
  46% {
    -webkit-transform: translateY(-1.5px) rotate(2.5deg);
    transform: translateY(-1.5px) rotate(2.5deg);
  }
  48% {
    -webkit-transform: translateY(-0.5px) rotate(0.5deg);
    transform: translateY(-0.5px) rotate(0.5deg);
  }
  50% {
    -webkit-transform: translateY(0.5px) rotate(0.5deg);
    transform: translateY(0.5px) rotate(0.5deg);
  }
  52% {
    -webkit-transform: translateY(2.5px) rotate(2.5deg);
    transform: translateY(2.5px) rotate(2.5deg);
  }
  54% {
    -webkit-transform: translateY(-1.5px) rotate(1.5deg);
    transform: translateY(-1.5px) rotate(1.5deg);
  }
  56% {
    -webkit-transform: translateY(2.5px) rotate(2.5deg);
    transform: translateY(2.5px) rotate(2.5deg);
  }
  58% {
    -webkit-transform: translateY(0.5px) rotate(2.5deg);
    transform: translateY(0.5px) rotate(2.5deg);
  }
  60% {
    -webkit-transform: translateY(2.5px) rotate(2.5deg);
    transform: translateY(2.5px) rotate(2.5deg);
  }
  62% {
    -webkit-transform: translateY(-0.5px) rotate(2.5deg);
    transform: translateY(-0.5px) rotate(2.5deg);
  }
  64% {
    -webkit-transform: translateY(-0.5px) rotate(1.5deg);
    transform: translateY(-0.5px) rotate(1.5deg);
  }
  66% {
    -webkit-transform: translateY(1.5px) rotate(-0.5deg);
    transform: translateY(1.5px) rotate(-0.5deg);
  }
  68% {
    -webkit-transform: translateY(-1.5px) rotate(-0.5deg);
    transform: translateY(-1.5px) rotate(-0.5deg);
  }
  70% {
    -webkit-transform: translateY(1.5px) rotate(0.5deg);
    transform: translateY(1.5px) rotate(0.5deg);
  }
  72% {
    -webkit-transform: translateY(2.5px) rotate(1.5deg);
    transform: translateY(2.5px) rotate(1.5deg);
  }
  74% {
    -webkit-transform: translateY(-0.5px) rotate(0.5deg);
    transform: translateY(-0.5px) rotate(0.5deg);
  }
  76% {
    -webkit-transform: translateY(-0.5px) rotate(2.5deg);
    transform: translateY(-0.5px) rotate(2.5deg);
  }
  78% {
    -webkit-transform: translateY(-0.5px) rotate(1.5deg);
    transform: translateY(-0.5px) rotate(1.5deg);
  }
  80% {
    -webkit-transform: translateY(1.5px) rotate(1.5deg);
    transform: translateY(1.5px) rotate(1.5deg);
  }
  82% {
    -webkit-transform: translateY(-0.5px) rotate(0.5deg);
    transform: translateY(-0.5px) rotate(0.5deg);
  }
  84% {
    -webkit-transform: translateY(1.5px) rotate(2.5deg);
    transform: translateY(1.5px) rotate(2.5deg);
  }
  86% {
    -webkit-transform: translateY(-1.5px) rotate(-1.5deg);
    transform: translateY(-1.5px) rotate(-1.5deg);
  }
  88% {
    -webkit-transform: translateY(-0.5px) rotate(2.5deg);
    transform: translateY(-0.5px) rotate(2.5deg);
  }
  90% {
    -webkit-transform: translateY(2.5px) rotate(-0.5deg);
    transform: translateY(2.5px) rotate(-0.5deg);
  }
  92% {
    -webkit-transform: translateY(0.5px) rotate(-0.5deg);
    transform: translateY(0.5px) rotate(-0.5deg);
  }
  94% {
    -webkit-transform: translateY(2.5px) rotate(0.5deg);
    transform: translateY(2.5px) rotate(0.5deg);
  }
  96% {
    -webkit-transform: translateY(-0.5px) rotate(1.5deg);
    transform: translateY(-0.5px) rotate(1.5deg);
  }
  98% {
    -webkit-transform: translateY(-1.5px) rotate(-0.5deg);
    transform: translateY(-1.5px) rotate(-0.5deg);
  }
  0%,
  to {
    -webkit-transform: translate(0) rotate(0deg);
    transform: translate(0) rotate(0deg);
  }
}

.tmsg-commentshow {
  /*background: #97dffd;*/
  /*transform: scale(1.05);*/
  /*padding-left: 70px;*/
  /*width: 80%;*/
}

/*评论列表*/
.tmsg-comments .tmsg-comments-tip {
  display: block;
  border-left: 2px solid #363d4c;
  padding: 0 10px;
  margin: 40px 0;
  font-size: 20px;
}

.tmsg-commentshow > .tmsg-commentlist {
  border-bottom: 1px solid #e5eaed;
}
</style>

<template>
  <div id="DiscussContentView">
    <a-layout style="min-height: 150vh">
      <a-layout-content class="content">
        <h1 align="center">{{ post?.title }}</h1>
        <h3 align="center">
          发表于
          <span>
            {{ moment(post?.createTime).format("YYYY-MM-DD HH:mm:ss") }}</span
          >
        </h3>
        <div align="center">
          <a-space>
            <a-tag
              size="large"
              v-for="(tag, index) of post?.tagList"
              :key="index"
              color="cyan"
              >{{ tag }}
            </a-tag>
          </a-space>
        </div>

        <a-divider />
        <a-space size="large" direction="vertical">
          <a-space size="large">
            <a-avatar shape="circle" :size="50">
              <img alt="avatar" :src="post?.user?.userAvatar" />
            </a-avatar>
            {{ post?.user?.userName }}
          </a-space>
        </a-space>
        <h2 style="font-weight: normal; font-size: 25px">
          <MdViewer :value="post?.content || ''" />
        </h2>
        <a-divider />
        <a-space>
          <a-space v-if="!post?.hasThumb" style="cursor: pointer">
            <icon-thumb-up :size="25" @click="click_up" />
            {{ post?.thumbNum }}
          </a-space>
          <a-space v-if="post?.hasThumb" style="cursor: pointer">
            <icon-thumb-up-fill :size="25" @click="click_up" />
            {{ post?.thumbNum }}
          </a-space>

          <a-space v-if="!post?.hasDislike" style="cursor: pointer">
            <icon-thumb-down :size="25" @click="click_down" />
            {{ post?.dislikeNum }}
          </a-space>
          <a-space v-if="post?.hasDislike" style="cursor: pointer">
            <icon-thumb-down-fill :size="25" @click="click_down" />
            {{ post?.dislikeNum }}
          </a-space>
        </a-space>
        <div style="padding-top: 15px">
          <DiscussionCommentView :id="props.id" />
        </div>
      </a-layout-content>
    </a-layout>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, defineProps, withDefaults } from "vue";
import {
  PostControllerService,
  PostDislikeControllerService,
  PostThumbControllerService,
  PostVO,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment";
import MdViewer from "@/components/MdViewer.vue";
import DiscussionCommentView from "@/views/discuss/DiscussionCommentView.vue";

interface Props {
  id: string;
}

const post = ref<PostVO>() as any;
const props = withDefaults(defineProps<Props>(), {
  id: () => "",
});

const loadData = async () => {
  const res = await PostControllerService.getPostVoByIdUsingGet(
    props.id as any
  );
  if (res.code === 0) {
    post.value = res.data;
  } else {
    message.error("加载失败，" + res.message);
  }
};

const click_up = async () => {
  const res = await PostThumbControllerService.doThumbUsingPost({
    postId: props?.id as any,
  });
  if (res.code === 0) {
    post.value = res.data;
  } else {
    message.error("点赞失败，" + res.message);
  }
  loadData();
};

const click_down = async () => {
  const res = await PostDislikeControllerService.doDislikeUsingPost({
    postId: props?.id as any,
  });
  if (res.code === 0) {
    post.value = res.data;
  } else {
    message.error("点踩失败，" + res.message);
  }
  loadData();
};

/**
 * 页面加载时，请求数据
 */
onMounted(() => {
  loadData();
});
</script>

<style scoped>
#DiscussContentView {
  max-width: 1400px;
  margin: 0 auto;
}

#DiscussContentView .arco-space-horizontal .arco-space-item {
  margin-bottom: 0 !important;
}

#DiscussContentView .content {
  /*background-repeat: no-repeat;*/
  /*background-position: center top;*/
  background-size: cover;
  background: linear-gradient(to right, #f6f6f6, #f6f6f6);
  /*background-image: url("../../assets/postbackground.jpg");*/
  margin-bottom: 16px;
  padding: 50px;
  margin-left: 200px;
  margin-right: 200px;
}
</style>

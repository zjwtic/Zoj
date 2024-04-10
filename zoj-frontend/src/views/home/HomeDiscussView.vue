<template>
  <div id="homeDiscussView">
    <div :style="{ display: 'flex' }">
      <a-card
        :style="{ width: '700px' }"
        title="最新讨论"
        :header-style="{ backgroundColor: '#F3F4F5' }"
      >
        <!--   form 表单与searchParams连结 搜索界面-->
        <a-list
          class="list-demo-action-layout"
          :bordered="false"
          :data="dataList"
          :pagination="false"
        >
          <template #item="{ item }">
            <a-list-item
              class="list-demo-item"
              action-layout="vertical"
              @click="toDiscussionPage(item)"
            >
              <template #actions>
                <span style="cursor: auto"
                  ><icon-thumb-up />{{ item.thumbNum }}</span
                >
                <span style="cursor: auto"
                  ><icon-thumb-down />{{ item.dislikeNum }}</span
                >
              </template>
              <a-list-item-meta
                style="cursor: pointer"
                :title="item.user.userName"
                :description="item.title"
              >
                <template #avatar>
                  <a-avatar shape="square">
                    <img alt="avatar" :src="item.user.userAvatar" />
                  </a-avatar>
                </template>
              </a-list-item-meta>
              <template #extra>
                <a-space>
                  <a-tag
                    size="medium"
                    v-for="(tag, index) of item.tagList"
                    :key="index"
                    color="orange"
                    >{{ tag }}
                  </a-tag>
                </a-space>
              </template>
            </a-list-item>
          </template>
        </a-list>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import {
  PostControllerService,
  PostQueryRequest,
  PostVO,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { reactive } from "vue";
import moment from "moment";

const dataList = ref([]);
const searchParams = ref<PostQueryRequest>({
  pageSize: 5,
  current: 1,
});

const loadData = async () => {
  const res = await PostControllerService.listPostVoByPageUsingPost({
    ...searchParams.value,
    sortField: "createTime",
    sortOrder: "descend",
  });
  if (res.code === 0) {
    dataList.value = res.data.records;
  } else {
    message.error("加载失败，" + res.message);
  }
};

/**
 * 页面加载时，请求数据
 */
onMounted(() => {
  loadData();
});

const router = useRouter();

const toDiscussionPage = (post: PostVO) => {
  router.push({
    path: `/view/discussion/${post.id}`,
  });
};
</script>

<style scoped>
#homeDiscussView {
  /*max-width: 1280px;*/
  /*margin: 0 auto;*/
  /*border-radius: 10px;*/
}

.list-demo-action-layout .image-area {
  width: 183px;
  height: 119px;
  border-radius: 2px;
  overflow: hidden;
}

.list-demo-action-layout .list-demo-item {
  padding: 20px 0;
  border-bottom: 1px solid var(--color-fill-3);
}

.list-demo-action-layout .image-area img {
  width: 100%;
}

.list-demo-action-layout .arco-list-item-action .arco-icon {
  margin: 0 4px;
}
</style>

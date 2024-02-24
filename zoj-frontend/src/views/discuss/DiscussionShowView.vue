<template>
  <div id="discussionShowView">
    <a-space>
      <a-form :model="searchParams" layout="inline" style="min-width: 300px">
        <a-form-item field="title" label="标题：" tooltip="请输入搜索的标题">
          <a-input
            v-model="searchParams.title"
            placeholder="请输入搜索的标题"
          />
        </a-form-item>
        <a-form-item
          field="tags"
          label="标题标签："
          tooltip="请输入搜索标题标签"
          style="min-width: 280px"
        >
          <a-input-tag
            v-model="searchParams.tags"
            placeholder="请输入标题标签"
          />
        </a-form-item>
        <a-form-item>
          <a-button
            type="outline"
            shape="round"
            status="normal"
            @click="doSubmit"
            >搜索
          </a-button>
        </a-form-item>
      </a-form>
      <a-button
        type="primary"
        shape="square"
        status="normal"
        size="large"
        @click="addDiscussion"
        style="margin-left: 450px"
        >新建讨论
      </a-button>
    </a-space>
    <!--   form 表单与searchParams连结 搜索界面-->

    <a-divider size="0" />
    <a-list
      class="list-demo-action-layout"
      :bordered="false"
      :data="dataList"
      :pagination-props="{
        showTotal: true,
        pageSize: searchParams.pageSize,
        current: searchParams.current,
        total,
        showJumper: true,
        showPageSize: true,
      }"
      @page-change="onPageChange"
      @pageSizeChange="onPageSizeChange"
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
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watchEffect } from "vue";
import {
  Post,
  PostControllerService,
  PostQueryRequest,
  PostVO,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import { reactive } from "vue";
import moment from "moment";

const dataList = ref([]);
const total = ref(0);
const searchParams = ref<PostQueryRequest>({
  title: "",
  tags: [],
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
    total.value = res.data.total;
  } else {
    message.error("加载失败，" + res.message);
  }
};

/**
 * 监听 searchParams 变量，改变时触发页面的重新加载
 */
watchEffect(() => {
  loadData();
});

/**
 * 页面加载时，请求数据
 */
onMounted(() => {
  loadData();
});

/**
 * 当前分页
 * @param page
 */
const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
};
/**
 * 分页大小
 * @param size
 */
const onPageSizeChange = (size: number) => {
  searchParams.value = {
    ...searchParams.value,
    pageSize: size,
  };
};

/**
 * 确认搜索，重新加载数据
 */
const doSubmit = () => {
  // 这里需要重置搜索页号
  searchParams.value = {
    ...searchParams.value,
    current: 1,
  };
};

/**
 * 新建讨论
 */
const router = useRouter();
const addDiscussion = () => {
  router.push({
    path: `/add/discussion`,
  });
};

const toDiscussionPage = (post: PostVO) => {
  router.push({
    path: `/view/discussion/${post.id}`,
  });
};
</script>

<style scoped>
#discussionShowView {
  max-width: 1280px;
  margin: 0 auto;
  border-radius: 10px;
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

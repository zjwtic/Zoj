<template>
  <div id="manageDiscussionView">
    <a-table
      :columns="modifiedColumns"
      :data="datalist"
      :pagination="{
        showTotal: true,
        pageSize: searchParams.pageSize,
        current: searchParams.current,
        total,
      }"
      @page-change="onPageChange"
    >
      <template #optional="{ record }">
        <a-space>
          <a-button type="primary" @click="doDelete(record)">删除</a-button>
          <a-button status="danger" @click="doUpdate(record)">修改</a-button>
          <a-button status="success" @click="doshow(record)">查看</a-button>
        </a-space>
      </template>
      <template #tags="{ record }">
        <a-space wrap>
          <a-tag
            size="medium"
            v-for="(tag, index) of record.tagList"
            :key="index"
            >{{ tag }}
          </a-tag>
        </a-space>
      </template>
      <template #createTime="{ record }">
        {{ moment(record.createTime).format("YYYY-MM-DD HH:mm:ss") }}
      </template>
    </a-table>
  </div>
</template>

<script setup lang="ts">
import { computed, onMounted, ref, watchEffect, watch } from "vue";
import message from "@arco-design/web-vue/es/message";
import { Post, PostControllerService } from "../../../generated";
import { useRoute, useRouter } from "vue-router";
import moment from "moment";
// 如果页面地址包含 own，视为查看自己的讨论
const route = useRoute();
const manageOwnDiscussion = route.path.includes("own");

const show = ref(true);

const datalist = ref([]);
const total = ref(0);
const searchParams = ref({
  pageSize: 2,
  current: 1,
});

const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
};

const doDelete = async (post: Post) => {
  const res = await PostControllerService.deletePostUsingPost({
    id: post.id,
  });
  if (res.code === 0) {
    message.success("删除成功");
    loadData();
  } else {
    message.error("删除失败");
  }
};

const router = useRouter();

// onBeforeRouteUpdate(async (to, from) => {
//   loadData();
//   next();
// });

// 如果需要监听整个 $route 对象的变化
watch(
  () => route.path,
  (newPath, oldPath) => {
    // 当路由路径变化时执行的逻辑
    location.reload();
  }
);

const doUpdate = (post: Post) => {
  router.push({
    path: "/update/discussion",
    query: {
      id: post.id,
    },
  });
};
const doshow = (post: Post) => {
  // router.push({
  //   path: "/update/discussion",
  //   query: {
  //     id: post.id,
  //   },
  // });
};

const loadData = async () => {
  if (manageOwnDiscussion) {
    const res = await PostControllerService.listMyPostVoByPageUsingPost(
      searchParams.value
    );
    if (res.code === 0) {
      datalist.value = res.data.records;
      total.value = res.data.total;
    } else {
      message.error("加载失败" + res.message);
    }
  } else {
    const res = await PostControllerService.listPostVoByPageUsingPost(
      searchParams.value
    );
    if (res.code === 0) {
      datalist.value = res.data.records;
      total.value = res.data.total;
    } else {
      message.error("加载失败" + res.message);
    }
  }
};

/**
 * 监听searchParams 变量，改变时触发页面的重新加载
 */
watchEffect(() => {
  loadData();
});

onMounted(() => {
  loadData();
});
const columns = [
  {
    title: "id",
    dataIndex: "id",
  },
  {
    title: "标题",
    dataIndex: "title",
  },
  {
    title: "讨论标签",
    slotName: "tags",
    align: "center",
  },
  {
    title: "点赞数",
    dataIndex: "thumbNum",
  },
  {
    title: "点踩数",
    dataIndex: "dislikeNum",
  },
  {
    title: "用户id",
    dataIndex: "userId",
  },
  {
    title: "创建时间",
    slotName: "createTime",
    align: "center",
    sortable: {
      sortDirections: ["ascend"],
    },
  },
  {
    title: "操作",
    slotName: "optional",
  },
];
const modifiedColumns = columns.filter((column) => {
  if (column.title === "用户id") {
    if (manageOwnDiscussion) {
      return null;
    }
  }
  return column;
});
</script>

<style scoped>
#manageDiscussionView {
}
</style>

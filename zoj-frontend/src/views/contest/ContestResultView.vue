<template>
  <div id="contestResultView">
    <a-table
      :columns="columns"
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
        </a-space>
      </template>
      <template #selectContestIds="{ record }">
        <h5 v-for="(id, index) in record.selectContestIds" :key="index">
          {{ getQuestionName(id) }}({{ id }})
        </h5>
      </template>
      <template #duration="{ record }">
        <p>
          {{ makeHoursMinutes(record.duration) }}
        </p>
      </template>
      <template #startTime="{ record }">
        {{ moment(record.startTime).format("YYYY-MM-DD HH:mm:ss") }}
      </template>
      <template #endTime="{ record }">
        {{ moment(record.endTime).format("YYYY-MM-DD HH:mm:ss") }}
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
import {
  Contest,
  ContestControllerService,
  QuestionControllerService,
} from "../../../generated";
import { useRoute, useRouter } from "vue-router";
import moment from "moment";
// 如果页面地址包含 own，视为查看自己的讨论
const route = useRoute();

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
const questionList = ref();

const doDelete = async (contest: Contest) => {
  const res = await ContestControllerService.deleteContestUsingPost({
    id: contest.id,
  });
  if (res.code === 0) {
    message.success("删除成功");
    loadDataNoQuestionList();
  } else {
    message.error("删除失败");
  }
};

// 如果需要监听整个 $route 对象的变化
watch(
  () => route.path,
  (newPath, oldPath) => {
    // 当路由路径变化时执行的逻辑
    location.reload();
  }
);

const router = useRouter();

const doUpdate = (contest: Contest) => {
  router.push({
    path: "/update/contest",
    query: {
      id: contest.id,
    },
  });
};

const loadData = async () => {
  const qres = await QuestionControllerService.listAllQuestionVoUsingPost();
  if (qres.code === 0) {
    questionList.value = qres.data as any;
  } else {
    message.error("加载失败，" + qres.message);
  }

  loadDataNoQuestionList();
};

const loadDataNoQuestionList = async () => {
  const res = await ContestControllerService.listContestVoByPageUsingPost(
    searchParams.value
  );
  if (res.code === 0) {
    datalist.value = res.data.records;
    total.value = res.data.total;
  } else {
    message.error("加载失败" + res.message);
  }
};

/**
 * 监听searchParams 变量，改变时触发页面的重新加载
 */
watchEffect(() => {
  loadDataNoQuestionList();
});

onMounted(() => {
  loadData();
});

const getQuestionName = (id: any) => {
  for (var i = 0; i < questionList?.value?.length; i++) {
    var item = questionList.value[i];
    if (item.id === id) {
      return item.title;
    }
  }
};
const makeHoursMinutes = (duration: number) => {
  let durationRemainder = duration % 3600;
  let displayString = `${Math.floor(duration / 3600)}小时`;

  if (durationRemainder > 0) {
    displayString += ` ${Math.floor(durationRemainder / 60)}分`;
  }
  return displayString;
};
const columns = [
  {
    title: "id",
    dataIndex: "id",
  },
  {
    title: "比赛名",
    dataIndex: "contestName",
  },
  {
    title: "比赛描述",
    dataIndex: "contestDescription",
  },
  {
    title: "所选择的题目",
    slotName: "selectContestIds",
  },
  {
    title: "比赛开始时间",
    slotName: "startTime",
  },
  {
    title: "比赛结束时间",
    slotName: "endTime",
  },
  {
    title: "比赛时间",
    slotName: "duration",
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
</script>

<style scoped>
#manageContestView {
}
</style>

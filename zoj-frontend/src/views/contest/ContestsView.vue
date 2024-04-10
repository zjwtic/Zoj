<template>
  <div id="contestsView">
    <!--   form 表单与searchParams连结 搜索界面-->
    <a-form :model="searchParams" layout="inline" style="min-width: 300px">
      <a-form-item
        field="contestName"
        label="比赛名："
        tooltip="请输入搜索的比赛名"
      >
        <a-input
          v-model="searchParams.contestName"
          placeholder="请输入搜索的比赛名~"
        />
      </a-form-item>
      <a-form-item>
        <a-button type="outline" shape="round" status="normal" @click="doSubmit"
          >搜索
        </a-button>
      </a-form-item>
    </a-form>
    <a-divider size="0" />
    <a-table
      column-resizable
      wrapper
      :ref="tableRef"
      :columns="columns"
      :data="dataList"
      :pagination="{
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
      <template #optional="{ record }">
        <a-space>
          <a-button
            v-if="!record.isParticipated && isBegin(record) && !isEnd(record)"
            shape="round"
            status="normal"
            type="primary"
            @click="toContestPage(record)"
            >比赛
          </a-button>
          <a-button
            v-if="!isBegin(record)"
            shape="round"
            status="normal"
            type="primary"
            disabled
            >比赛
          </a-button>
          <a-button
            v-if="record.isParticipated || isEnd(record)"
            shape="round"
            status="normal"
            type="primary"
            @click="toContestResult(record)"
            >查看结果
          </a-button>
        </a-space>
      </template>
    </a-table>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watchEffect } from "vue";
import {
  Contest,
  ContestControllerService,
  ContestQueryRequest,
  Question,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment";
import DoContestView from "@/views/contest/DoContestView.vue";

const tableRef = ref();

const dataList = ref([]);
const total = ref(0);
const searchParams = ref<ContestQueryRequest>({
  contestName: "",
  pageSize: 2,
  current: 1,
});
const makeHoursMinutes = (duration: number) => {
  let durationRemainder = duration % 3600;
  let displayString = `${Math.floor(duration / 3600)}小时`;

  if (durationRemainder > 0) {
    displayString += ` ${Math.floor(durationRemainder / 60)}分`;
  }
  return displayString;
};

const loadData = async () => {
  const res = await ContestControllerService.listContestVoByPageUsingPost({
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

const columns = [
  {
    title: "比赛名",
    dataIndex: "contestName",
    align: "center",
  },
  {
    title: "比赛开始时间",
    slotName: "startTime",
    align: "center",
    sortable: {
      sortDirections: ["ascend"],
    },
  },

  {
    title: "比赛结束时间",
    slotName: "endTime",
    align: "center",
    sortable: {
      sortDirections: ["ascend"],
    },
  },

  {
    title: "比赛持续时间",
    slotName: "duration",
    align: "center",
  },
  {
    title: "比赛描述",
    dataIndex: "contestDescription",
    align: "center",
    ellipsis: true,
    tooltip: { position: "left" },
    width: 255,
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
    align: "center",
  },
];
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
const router = useRouter();

/**
 * 跳转到比赛页面
 * @param contest
 */
const toContestPage = (contest: Contest) => {
  router.push({
    path: `/online/contest/${contest.id}`,
  });
};

/**
 * 跳转到题目结果页面
 * @param contest
 */
const toContestResult = (contest: Contest) => {
  router.push({
    path: `contestresult/${contest.id}`,
  });
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

const isBegin = (record: any) => {
  // 给定的字符串时间
  const startTimeString = record.startTime;

  // 解析字符串时间为Date对象
  const startTime = new Date(startTimeString);

  // 获取当前时间的Date对象
  const currentTime = new Date();

  // 比较时间
  return currentTime > startTime;
};
const isEnd = (record: any) => {
  // 给定的字符串时间
  const endTimeString = record.endTime;

  // 解析字符串时间为Date对象
  const endTime = new Date(endTimeString);

  // 获取当前时间的Date对象
  const currentTime = new Date();

  // 比较时间
  return currentTime >= endTime;
};
</script>

<style scoped>
#contestsView {
  max-width: 1280px;
  margin: 0 auto;
  border-radius: 10px;
}

.popup {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.popup-content {
  background: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}
</style>

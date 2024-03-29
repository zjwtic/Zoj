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
            shape="round"
            status="normal"
            type="primary"
            @click="toContestPage(record)"
          >
            比赛
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
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment";

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
  const res = await ContestControllerService.listContestByPageUsingPost({
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
 * @param question
 */
const toContestPage = (contest: Contest) => {
  router.push({
    path: `/do/contest/${contest.id}`,
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
</script>

<style scoped>
#contestsView {
  max-width: 1280px;
  margin: 0 auto;
  border-radius: 10px;
}
</style>

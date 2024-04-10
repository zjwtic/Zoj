<template>
  <div id="homeRankView">
    <div :style="{ display: 'flex' }">
      <a-card
        :style="{ width: '360px' }"
        title="积分排行榜"
        :header-style="{ backgroundColor: '#F3F4F5' }"
      >
        <!--   form 表单与searchParams连结 搜索界面-->
        <a-table
          column-resizable
          wrapper
          :columns="columns"
          :data="dataList"
          :pagination="false"
          :bordered="false"
        >
          <template #rank="{ rowIndex }">{{ rowIndex + 1 }}</template>
          <template #indexTitle><h3>排名</h3></template>
          <template #userNameTitle><h3>用户名</h3></template>
          <template #pointsTitle><h3>积分数</h3></template>
        </a-table>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { UserControllerService, UserQueryRequest } from "../../../generated";
import message from "@arco-design/web-vue/es/message";

const dataList = ref([]) as any;
const searchParams = ref<UserQueryRequest>({
  pageSize: 10,
  current: 1,
}) as any;

const loadData = async () => {
  const res = await UserControllerService.listUserVoByPageUsingPost({
    ...searchParams.value,
    sortField: "points",
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

const columns = [
  {
    titleSlotName: "indexTitle",
    slotName: "rank",
  },
  {
    titleSlotName: "userNameTitle",
    dataIndex: "userName",
    align: "center",
  },
  {
    titleSlotName: "pointsTitle",
    dataIndex: "points",
    align: "center",
  },
];
</script>

<style scoped>
#homeRankView {
  max-width: 1280px;
  margin: 0 auto;
  border-radius: 10px;
}
</style>

<template>
  <div id="rankAcceptedView">
    <!--   form 表单与searchParams连结 搜索界面-->
    <a-table
      column-resizable
      wrapper
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
      <template #index="{ record }">
        {{ record.index }}
      </template>
      <template #indexTitle><h3>排名</h3></template>
      <template #userNameTitle><h3>用户名</h3></template>
      <template #userProfileTitle><h3>个人简介</h3></template>
      <template #acceptedNumTitle><h3>通过题目数</h3></template>
    </a-table>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watchEffect } from "vue";
import { UserControllerService, UserQueryRequest } from "../../../generated";
import message from "@arco-design/web-vue/es/message";

const dataList = ref([]) as any;
const total = ref(0);
const searchParams = ref<UserQueryRequest>({
  pageSize: 6,
  current: 1,
}) as any;

const loadData = async () => {
  const res = await UserControllerService.listUserVoByPageUsingPost({
    ...searchParams.value,
    sortField: "acceptedNum",
    sortOrder: "descend",
  });
  if (res.code === 0) {
    dataList.value = res.data.records;
    total.value = res.data.total;
    for (let i = 0; i < dataList.value.length; i++) {
      dataList.value[i].index =
        (searchParams.value.current - 1) * searchParams.value.pageSize +
        (i + 1);
    }
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
    titleSlotName: "indexTitle",
    slotName: "index",
  },
  {
    titleSlotName: "userNameTitle",
    dataIndex: "userName",
    align: "center",
  },
  {
    titleSlotName: "userProfileTitle",
    dataIndex: "userProfile",
    align: "center",
  },
  {
    titleSlotName: "acceptedNumTitle",
    dataIndex: "acceptedNum",
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
</script>
<style scoped>
#rankAcceptedView {
  max-width: 1280px;
  margin: 0 auto;
  border-radius: 10px;
}
</style>

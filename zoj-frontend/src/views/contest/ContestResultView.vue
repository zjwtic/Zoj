<template>
  <div id="contestResultView">
    <div class="title">实时比赛结果</div>
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
      <template #pointsTitle><h3>总分</h3></template>
      <template #submitStatusTitle><h3>比赛情况</h3></template>
      <template #questionInfo><h3>做题信息</h3></template>
      <template #rewordTitle><h3>奖励</h3></template>
      <template #rank="{ rowIndex }">{{ getRank(rowIndex) }}</template>

      <template #reword="{ rowIndex }">
        {{ getReword(rowIndex) }}
      </template>

      <template #submitStatus="{ record }">
        {{ record.submitStatus ? "已结束" : "进行中" }}
      </template>

      <template #questionScore="{ column, record }">
        <div class="data-container">
          <div class="main-data">{{ getQuestionScore(record, column) }}</div>
          <div class="sub-data">{{ getQuestionConsume(record, column) }}ms</div>
        </div>
      </template>
    </a-table>
    <!--    {{ dataList }}-->
    <!--    {{ questionColumns }}-->
    <div class="footer">
      注：非比赛最终结果，等比赛时间截止会公布最终结果，到时会发放对应排名积分奖励
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, defineProps, withDefaults, watchEffect } from "vue";
import {
  ContestResultControllerService,
  UserQueryRequest,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";

interface Props {
  id: string;
}

const props = withDefaults(defineProps<Props>(), {
  id: () => "",
});

const dataList = ref([]) as any;
const total = ref(0);
const searchParams = ref<UserQueryRequest>({
  pageSize: 6,
  current: 1,
}) as any;

const questionColumns = ref([]);

const loadData = async () => {
  const res =
    await ContestResultControllerService.listAllContestResultPointVoUsingPost(
      props.id as any
    );
  if (res.code === 0) {
    dataList.value = res.data;
    total.value = dataList.value.length;
    questionColumns.value = getQuestionColumns(
      dataList?.value[0]?.questionScoreList
    );
    // 遍历columns数组，找到titleSlotName为'questionInfo'的对象
    columns.value.forEach((column) => {
      if (column.titleSlotName === "questionInfo") {
        // 将新数组添加到该对象的children数组中
        column.children = questionColumns.value;
      }
    });
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

const columns = ref([
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
    titleSlotName: "questionInfo",
    children: [],
  },
  {
    titleSlotName: "pointsTitle",
    dataIndex: "point",
    align: "center",
  },
  {
    titleSlotName: "submitStatusTitle",
    slotName: "submitStatus",
    align: "center",
  },
  {
    titleSlotName: "rewordTitle",
    slotName: "reword",
    align: "center",
  },
]);

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

const getRank = (rowIndex: number) => {
  return (
    (searchParams?.value?.current - 1) * searchParams?.value?.pageSize +
    (rowIndex + 1)
  );
};

const getReword = (rowIndex: number) => {
  let rank = getRank(rowIndex);
  if (rank == 1) {
    return "100积分";
  }
  if (rank == 2) {
    return "50积分";
  }
  if (rank == 3) {
    return "30积分";
  }
  if (rank <= 10) {
    return "10积分";
  }

  return "5积分";
};

const getQuestionScore = (record: any, column: any) => {
  const questionIndex = getQuestionIndex(column);
  return record.questionScoreList[questionIndex]?.score;
};

const getQuestionConsume = (record: any, column: any) => {
  const questionIndex = getQuestionIndex(column);
  return record.questionScoreList[questionIndex]?.consume;
};

const getQuestionIndex = (column: any) => {
  return parseInt(column.dataIndex.slice(-1)) - 2;
};

const getQuestionColumns = (questionScoreList: any) => {
  const newArray = questionScoreList?.map((item: any) => {
    // 提取title属性
    const { title } = item;
    // 设置固定的slotName属性
    const slotName = "questionScore";
    const align = "center";
    // 返回新对象
    return { title, slotName, align };
  });
  return newArray;
};
</script>
<style scoped>
#contestResultView {
  max-width: 1280px;
  margin: 0 auto;
  border-radius: 10px;
}

.data-container {
  position: relative;
  width: 100px; /* 你可以设置合适的宽度 */
  height: 60px; /* 你可以设置合适的高度 */
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  text-align: center;
  padding-left: 60px;
}

.main-data {
  font-size: 20px; /* 主数据字体大一点 */
  color: lawngreen; /* 主数据颜色为绿色 */
  font-weight: bold; /* 主数据加粗 */
  z-index: 2; /* 确保主数据在子数据之上 */
}

.sub-data {
  font-size: 11px; /* 子数据字体小一点 */
  color: rgba(0, 0, 0, 0.5); /* 子数据颜色半透明，更模糊一点 */
  position: relative;
  bottom: 0; /* 子数据位于容器底部 */
  left: 17%; /* 水平居中 */
  transform: translateX(-50%); /* 使用变换来确保水平居中 */
}

.title {
  font-size: 20px; /* 主数据字体大一点 */
  color: black; /* 主数据颜色为绿色 */
  font-weight: bold; /* 主数据加粗 */
  margin-bottom: 20px;
}

.footer {
  font-size: 15px; /* 子数据字体小一点 */
  position: relative;
  bottom: 0; /* 子数据位于容器底部 */
}
</style>

<template>
  <div id="questionSubmitAnalyseView">
    <a-table
      v-if="isShow"
      :bordered="false"
      :columns="columns"
      :data="dataList"
      :pagination="{
        showTotal: true,
        pageSize: searchParams.pageSize,
        current: searchParams.current,
        total,
      }"
      @page-change="onPageChange"
    >
      <template #message="{ record }">
        {{ record.judgeInfo.message }}
      </template>
      <template #time="{ record }">
        {{ record.judgeInfo.time }}
      </template>
      <template #memory="{ record }">
        {{ record.judgeInfo.memory }}
      </template>
      <template #createTime="{ record }">
        {{ moment(record.createTime).format("YYYY-MM-DD HH:mm:ss") }}
      </template>
      <template #detail="{ record }">
        <a-button
          type="primary"
          size="small"
          @click="showQuestionSubmitDetail(record)"
          >详情
        </a-button>
      </template>
    </a-table>
    <div v-if="!isShow">
      <a-button @click="backQuestionSubmitAnalyse" style="float: right"
        >返回
      </a-button>
      <QuestionSubmitDetailView :record="nowrecord" />
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, watchEffect, defineProps, withDefaults } from "vue";
import {
  Question,
  QuestionControllerService,
  QuestionSubmitQueryRequest,
  UserControllerService,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment";
import { useStore } from "vuex";
import QuestionSubmitDetailView from "@/views/questionSubmit/QuestionSubmitDetailView.vue";

const store = useStore();
const dataList = ref([]);
const total = ref(0);

interface Props {
  id: string;
}

const nowrecord = ref();
const isShow = ref(true);

const props = withDefaults(defineProps<Props>(), {
  id: () => "",
});

const searchParams = ref<QuestionSubmitQueryRequest>({
  userId: store.state.user?.loginUser?.id,
  questionId: props.id as any,
  language: undefined,
  pageSize: 10,
  current: 1,
});
const loadData = async () => {
  const res = await QuestionControllerService.listQuestionSubmitByPageUsingPost(
    {
      ...searchParams.value,
      sortField: "createTime",
      sortOrder: "descend",
    }
  );
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
    title: "状态",
    slotName: "message",
  },
  {
    title: "编程语言",
    dataIndex: "language",
  },

  {
    title: "耗时(ms)",
    slotName: "time",
  },
  {
    title: " 内存占用(MB)",
    slotName: "memory",
  },
  {
    title: "提交时间",
    slotName: "createTime",
  },
  {
    slotName: "detail",
  },
];

const onPageChange = (page: number) => {
  searchParams.value = {
    ...searchParams.value,
    current: page,
  };
};

const showQuestionSubmitDetail = (record: any) => {
  nowrecord.value = record;
  isShow.value = !isShow.value;
};
const backQuestionSubmitAnalyse = () => {
  nowrecord.value = "";
  isShow.value = !isShow.value;
};
</script>

<style scoped>
#questionSubmitAnalyseView {
}
</style>

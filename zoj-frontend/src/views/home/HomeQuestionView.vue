<template>
  <div id="homeQuestionView">
    <div :style="{ display: 'flex' }">
      <a-card
        :style="{ width: '700px' }"
        title="最新题目"
        :header-style="{ backgroundColor: '#F3F4F5' }"
      >
        <!--   form 表单与searchParams连结 搜索界面-->
        <a-table
          column-resizable
          wrapper
          :ref="tableRef"
          :columns="columns"
          :data="dataList"
          :pagination="false"
          :bordered="false"
        >
          <template #isAccepted="{ record }">
            <a-steps v-if="record.isAccepted" :current="2">
              <a-step></a-step>
            </a-steps>
          </template>

          <template #tags="{ record }">
            <a-space wrap>
              <a-tag
                size="medium"
                v-for="(tag, index) of record.tags"
                :key="index"
                color="green"
                >{{ tag }}
              </a-tag>
            </a-space>
          </template>
          <template #acceptedRate="{ record }">
            {{
              `${
                Math.round(
                  (record.submitNum > 0
                    ? (record.acceptedNum / record.submitNum) * 100
                    : "0" * 100) * 100
                ) / 100
              }% (${record.acceptedNum}/${record.submitNum})`
            }}
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
                @click="toQuestionPage(record)"
              >
                做题
              </a-button>
            </a-space>
          </template>
        </a-table>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import {
  Question,
  QuestionControllerService,
  QuestionQueryRequest,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment";

const tableRef = ref();

const dataList = ref([]);
const total = ref(0);
const searchParams = ref<QuestionQueryRequest>({
  title: "",
  tags: [],
  pageSize: 5,
  current: 1,
});

const loadData = async () => {
  const res = await QuestionControllerService.listQuestionVoByPageUsingPost({
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
 * 页面加载时，请求数据
 */
onMounted(() => {
  loadData();
});

const columns = [
  {
    title: "",
    slotName: "isAccepted",
  },
  {
    title: "题号",
    dataIndex: "id",
    align: "center",
  },
  {
    title: "题目",
    dataIndex: "title",
    align: "center",
  },
  {
    title: "题目标签",
    slotName: "tags",
    align: "center",
  },
  {
    title: "通过率",
    slotName: "acceptedRate",
    align: "center",
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

const router = useRouter();

/**
 * 跳转到做题页面
 * @param question
 */
const toQuestionPage = (question: Question) => {
  router.push({
    path: `/view/question/${question.id}`,
  });
};
</script>

<style scoped>
#homeQuestionView {
  /*max-width: 1280px;*/
  /*margin: 0 auto;*/
  /*border-radius: 10px;*/
}

.custom-card-header {
  background-color: #363d4c;
}
</style>

<template>
  <div id="doContestView">
    <a-space style="padding-bottom: 20px">
      <a-col :flex="1">
        <a-countdown
          :value="now + 1000 * remainingTime"
          :now="now + 1000 * remainingTime"
          @finish="submitContest"
        />
      </a-col>

      <a-collapse
        style="padding-left: 350px"
        :default-active-key="['1']"
        :bordered="false"
      >
        <a-collapse-item header="实时通过情况" key="1">
          <a-descriptions
            column="6"
            :data="contestQuestionAcceptedList"
            bordered
            size="mini"
            layout="inline-horizontal"
          >
            <template #label="{ data }">
              <a-space style="color: black">{{ data.title }}</a-space>
            </template>
            <template #value="{ data }">
              <a-space style="color: red; padding-left: 10px"
                >{{ data.questionAcceptedNum }}
              </a-space>
            </template>
          </a-descriptions>
        </a-collapse-item>
      </a-collapse>

      <a-button
        type="primary"
        shape="round"
        status="danger"
        size="large"
        @click="handleClick"
        style="position: fixed; top: 20px; right: 30px"
        >交卷
      </a-button>
      <a-modal
        v-model:visible="visible"
        @ok="submitContest"
        @cancel="handleCancel"
        draggable
      >
        <template #title> 提示</template>
        <div>你确定要交卷吗，一旦交卷后就不能在悔改哦~</div>
      </a-modal>
    </a-space>
    <a-tabs type="card-gutter" default-active-key="question0">
      <a-tab-pane
        v-for="(question, index) of questions"
        :key="'question' + index"
      >
        <template #title>
          <span :style="{ color: questionColor(question.id) }">{{
            question.title
          }}</span>
        </template>
        <a-row :gutter="[24, 24]">
          <a-col :md="12" xs="24">
            <a-card v-if="question" :title="question.title">
              <a-descriptions
                title="判题条件"
                :column="{ xs: 1, md: 2, lg: 3 }"
              >
                <a-descriptions-item label="时间限制">
                  {{ question.judgeConfig.timeLimit ?? 0 }}
                </a-descriptions-item>
                <a-descriptions-item label="内存限制">
                  {{ question.judgeConfig.memoryLimit ?? 0 }}
                </a-descriptions-item>
                <a-descriptions-item label="堆栈限制">
                  {{ question.judgeConfig.stackLimit ?? 0 }}
                </a-descriptions-item>
              </a-descriptions>
              <MdViewer :value="question.content || ''" />
              <template #extra>
                <a-space wrap>
                  <a-tag
                    v-for="(tag, index) of question.tags"
                    :key="index"
                    color="green"
                    >{{ tag }}
                  </a-tag>
                </a-space>
              </template>
            </a-card>
          </a-col>
          <a-col :md="12" xs="24">
            <a-form :model="form" layout="inline">
              <a-form-item
                field="language"
                label="编程语言"
                style="min-width: 240px"
              >
                <a-select
                  v-model="form.language"
                  :style="{ width: '320px' }"
                  placeholder="选择编程语言"
                >
                  <a-option>java</a-option>
                  <a-option>cpp</a-option>
                  <a-option>python</a-option>
                </a-select>
              </a-form-item>
            </a-form>
            <CodeEditor
              :value="form.code"
              :language="form.language"
              :handle-change="changeCode"
            />
            <a-divider size="0" />
            <a-button
              type="primary"
              style="min-width: 200px"
              @click="doSubmit(question.id)"
            >
              提交代码
            </a-button>
          </a-col>
        </a-row>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, defineProps, withDefaults } from "vue";
import {
  ContestControllerService,
  ContestResultControllerService,
  ContestVO,
  CountdownControllerService,
  QuestionControllerService,
  QuestionSubmitAddRequest,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import moment from "moment";
import MdEditor from "@/components/MdEditor.vue";
import CodeEditor from "@/components/CodeEditor.vue";
import MdViewer from "@/components/MdViewer.vue";
import QuestionSubmitAnalyseView from "@/views/questionSubmit/QuestionSubmitAnalyseView.vue";

const now = Date.now();

const contestQuestionAcceptedList = ref();

const thisTimeIsQuestionAccepted = ref();

interface Props {
  id: string;
}

const data = [
  {
    label: "Name",
    value: "123",
  },
  {
    label: "Mobile",
    value: "123",
  },
  {
    label: "Hometown",
    value: "123",
  },
];

const questionAccepted = (questionId: number) => {
  const contestQuestionDatas = contestResult.value?.contestQuestionDatas;
  for (var i = 0; i < contestQuestionDatas?.length; i++) {
    var item = contestQuestionDatas[i];
    if (item.questionId === questionId) {
      return item.isAccepted;
    }
  }
  return false;
};

const getAllQuestionAcceptedNum = async () => {
  const res =
    await ContestResultControllerService.getQuestionAcceptedNumUsingPost(
      props.id as any
    );
  if (res.code === 0) {
    contestQuestionAcceptedList.value = res.data;
  } else {
    message.error("加载失败，" + res.message);
  }
};

const questionColor = (questionId: number) => {
  return questionAccepted(questionId) ? "green" : "blue";
};

const visible = ref(false);

const handleClick = () => {
  visible.value = true;
};
const handleCancel = () => {
  visible.value = false;
};

const router = useRouter();
/**
 * 交卷
 */
const submitContest = async () => {
  visible.value = false;
  const res = await ContestResultControllerService.contestResultSubmitUsingPost(
    {
      contestId: props.id as any,
    }
  );
  if (res.code === 0) {
    //todo 跳转到
    message.success("交卷成功");
    await router.push({
      path: `../../contestresult/${props.id}`,
    });
  } else {
    message.error("提交失败," + res.message);
  }
};

/**
 * 提交代码
 */
const doSubmit = async (questionId: number) => {
  if (!questionId) {
    return;
  }
  message.success(
    "提交成功，请等待结果，结果运行完成后有提示，成功了题目会变绿色"
  );
  console.log(form.value);
  const res = await QuestionControllerService.doQuestionSubmitUsingPost({
    ...form.value,
    questionId: questionId,
  });
  if (res.code === 0) {
    await updateContestResult(res.data);
    await getContestResult(contestResultId.value);
    await getAllQuestionAcceptedNum();
    if (thisTimeIsQuestionAccepted.value) {
      message.success("运行完成,代码正确");
    } else {
      message.error("运行错误，代码或者算法存在问题，请仔细检查");
    }
  } else {
    message.error(" 运行失败,可能是服务器缘故，请联系工作人员" + res.message);
  }
};

/**
 * 更改比赛结果
 */
const updateContestResult = async (questionSubmitId: number) => {
  if (!questionSubmitId || !contestResultId.value) {
    return;
  }
  const res = await ContestResultControllerService.updateContestResultUsingPost(
    {
      questionSubmitId: questionSubmitId,
      contestResultId: contestResultId.value,
    }
  );
  if (res.code === 0) {
    thisTimeIsQuestionAccepted.value = res.data.thisTimeIsQuestionAccepted;
    // message.success("比赛结果更改成功");
  } else {
    message.error("比赛结果更改失败," + res.message);
  }
};

const contest = ref<ContestVO>();
const form = ref<QuestionSubmitAddRequest>({
  language: "java",
  code: "",
});
const changeCode = (value: string) => {
  form.value.code = value;
};

const contestResultId = ref();
const contestResult = ref();

const props = withDefaults(defineProps<Props>(), {
  id: () => "",
});

const handleFinish = () => {
  message.success("比赛完成");
};

const questions = ref() as any;

const remainingTime = ref();

const getContestData = async () => {
  const res = await ContestControllerService.getContestVoByIdUsingGet(
    props.id as any
  );
  if (res.code === 0) {
    contest.value = res.data;
    console.log("123");
  } else {
    message.error("加载失败，" + res.message);
  }
};

const getQuestionData = async () => {
  const res = await QuestionControllerService.getQuestionVoByIdsUsingPost(
    contest?.value?.selectQuestionIds as any
  );
  if (res.code === 0) {
    questions.value = res.data as any;
  } else {
    message.error("加载失败，" + res.message);
  }
};

const getRemainingTime = async (duration: number) => {
  const res = await CountdownControllerService.getRemainingTimeUsingPost({
    contestId: props.id as any,
  });
  if (res.code === 0) {
    remainingTime.value = res.data as any;
  } else {
    await startCountDown(duration);
    remainingTime.value = duration;
  }
};

const startCountDown = async (duration: number) => {
  const res = await CountdownControllerService.startCountdownUsingPost({
    contestId: props.id as any,
    duration: duration,
  });
  if (res.code === 0) {
    console.log("开始倒计时");
  } else {
    message.error("加载失败，" + res.message);
  }
};

const genContestResult = async () => {
  const res = await ContestResultControllerService.addContestResultUsingPost({
    contestId: props.id as any,
  });
  if (res.code === 0) {
    contestResultId.value = res.data;
  } else {
    message.error("加载失败" + res.message);
  }
};

const getContestResult = async (contestResultId: number) => {
  const res =
    await ContestResultControllerService.getContestResultVoByIdUsingGet(
      contestResultId
    );
  if (res.code === 0) {
    contestResult.value = res.data;
  } else {
    message.error("加载失败" + res.message);
  }
};

const columns = [
  {
    label: "id",
    value: "questionId",
  },
  {
    label: "比赛名",
    value: "title",
  },
];

const loadData = async () => {
  await getContestData();
  await getQuestionData();
  await getRemainingTime(contest?.value?.duration as any);
  await genContestResult();
  await getContestResult(contestResultId.value);
  await getAllQuestionAcceptedNum();
};

/**
 * 页面加载时，请求数据
 */
onMounted(async () => {
  await loadData();
  // loadRemainingTime();
});
</script>

<style scoped>
#doContestView {
  max-width: 1400px;
  margin: 0 auto;
}

#doContestView .arco-space-horizontal .arco-space-item {
  margin-bottom: 0 !important;
}
</style>

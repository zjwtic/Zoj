<template>
  <div id="addContestView">
    <h2 v-if="!updatePage">添加比赛</h2>
    <h2 v-if="updatePage">更新比赛</h2>
    <a-form :model="form" label-align="left">
      <a-form-item field="contestName" label="比赛名称" style="width: 900px">
        <a-input v-model="form.contestName" placeholder="请输入比赛名称" />
      </a-form-item>
      <a-form-item
        field="contestDescription"
        label="比赛描述"
        style="width: 1000px"
      >
        <MdEditor
          :value="form.contestDescription"
          :handle-change="onContentChange"
        />
      </a-form-item>
      <a-form-item field="selectQuestionIds" label="添加的问题">
        <a-select
          v-model="form.selectQuestionIds"
          :style="{
            width: '700px',
          }"
          :size="medium"
          placeholder="请选择要添加的问题..."
          allow-search
          multiple
        >
          <template #label="{ data }">
            <a-trigger>
              <span>{{ data?.value }}</span>
              <template #content>
                <div class="trigger-demo-translate">
                  {{ getQuestionName(data?.label, data?.value) }}
                </div>
              </template>
            </a-trigger>
          </template>
          <a-option
            v-for="(item, index) in questionList"
            :value="item.id"
            :key="'common' + index"
          >
            <h3>{{ item.title }}</h3>
            <p style="opacity: 0.4">{{ item.id }}</p>
            <a-space wrap>
              <a-tag
                size="medium"
                v-for="(tag, index) of item.tags"
                :key="index"
                color="green"
                >{{ tag }}
              </a-tag>
            </a-space>
          </a-option>
        </a-select>
      </a-form-item>

      <a-form-item field="beginAndEndTimes" label="比赛开始结束时间设置">
        <a-range-picker
          style="width: 450px; margin: 0 12px 12px 120px"
          show-time
          v-model="beginAndEndTimes"
          :time-picker-props="{ defaultValue: ['00:00:00', '09:09:06'] }"
          format="YYYY-MM-DD HH:mm"
          @change="onChange"
          @select="onSelect"
          @ok="onOk"
          :shortcuts="[
            {
              label: '持续3天',
              value: () => [dayjs(), dayjs().add(3, 'day')],
            },
            {
              label: '持续一周',
              value: () => [dayjs(), dayjs().add(1, 'week')],
            },
            {
              label: '持续一个月',
              value: () => [dayjs(), dayjs().add(1, 'month')],
            },
          ]"
        />
      </a-form-item>
      <a-form-item field="duration" label="持续时间">
        <a-space size="large" style="margin-left: 200px">
          <a-space>
            <a-input-number
              v-model="hours"
              :style="{ width: '110px' }"
              placeholder="Please Enter"
              :default-value="1"
              mode="button"
              class="input-demo"
            />
            <p>小时</p>
          </a-space>
          <a-space>
            <a-input-number
              v-model="minutes"
              :style="{ width: '110px' }"
              placeholder="Please Enter"
              :default-value="0"
              mode="button"
              class="input-demo"
            />
            <p>分钟</p>
          </a-space>
        </a-space>
      </a-form-item>
      <div style="margin-top: 12px" />
      <a-form-item>
        <a-button
          type="primary"
          style="min-width: 200px; left: 33%"
          @click="doSubmit"
          >提交
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import MdEditor from "@/components/MdEditor.vue";
import dayjs from "dayjs";
import {
  ContestControllerService,
  QuestionControllerService,
} from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRoute } from "vue-router";

const route = useRoute();
// 如果页面地址包含 update，视为更新页面
const updatePage = route.path.includes("update");

let form = ref({
  contestDescription: "",
  contestName: "",
  selectQuestionIds: [],
  duration: 3600,
  endTime: "",
  startTime: "",
});

const beginAndEndTimes = ref();
const hours = ref();
const minutes = ref();
const questionList = ref();
/**
 * 根据题目 id 获取老的数据
 */
const loadData = async () => {
  const qres = await QuestionControllerService.listAllQuestionVoUsingPost();
  if (qres.code === 0) {
    questionList.value = qres.data as any;
  } else {
    message.error("加载失败，" + qres.message);
  }

  const id = route.query.id;
  if (!id) {
    return;
  }
  const res = await ContestControllerService.getContestByIdUsingGet(id as any);
  if (res.code === 0) {
    form.value = res.data as any;
    if (!form.value.selectQuestionIds) {
      form.value.selectQuestionIds = [];
    } else {
      form.value.selectQuestionIds = JSON.parse(
        form.value.selectQuestionIds as any
      );
      beginAndEndTimes.value = [form.value.startTime, form.value.endTime];
      hours.value = Math.floor(form.value.duration / 3600);
      minutes.value = Math.floor(
        (form.value.duration - hours.value * 3600) / 60
      );
    }
  } else {
    message.error("加载失败，" + res.message);
  }
};

onMounted(() => {
  loadData();
});
const getQuestionName = (label: string, id: any) => {
  return label && id ? label.split(id)[0] : "";
};

const doSubmit = async () => {
  console.log(form.value);
  form.value.startTime = beginAndEndTimes.value[0];
  form.value.endTime = beginAndEndTimes.value[1];
  form.value.duration =
    (hours.value > 0 || minutes.value > 0) &&
    hours.value >= 0 &&
    minutes.value >= 0
      ? hours.value * 3600 + minutes.value * 60
      : 3600;
  console.log(form.value.duration);
  // 区分更新还是创建
  if (updatePage) {
    const res = await ContestControllerService.updateContestUsingPost(
      form.value as any
    );
    if (res.code === 0) {
      message.success("更新成功");
    } else {
      message.error("更新失败，" + res.message);
    }
  } else {
    const res = await ContestControllerService.addContestUsingPost(
      form.value as any
    );
    if (res.code === 0) {
      message.success("创建成功");
    } else {
      message.error("创建失败，" + res.message);
    }
  }
};

const onContentChange = (value: string) => {
  form.value.contestDescription = value;
};

// const onSelect = (dateString, date) => {
//   console.log("onSelect", dateString, date);
// };
//
// const onChange = (dateString, date) => {
//   console.log("onChange: ", dateString, date);
// };
//
// const onOk = (dateString, date) => {
//   console.log("onOk: ", dateString, date);
// };
</script>

<style scoped>
#addContestView {
  position: relative;
  left: 50%;
  margin-left: -35%;
  width: 65%;
}

.trigger-demo-translate {
  padding: 10px;
  width: 150px;
  background-color: var(--color-bg-popup);
  border-radius: 4px;
  box-shadow: 0 2px 8px 0 rgba(0, 0, 0, 0.15);
}
</style>

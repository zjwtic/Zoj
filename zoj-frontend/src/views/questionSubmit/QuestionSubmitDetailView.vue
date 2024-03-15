<template>
  <div id="questionSubmitDetail">
    <div>
      <a-space>
        提交于
        <p>
          {{ moment(props.record.createTime).format("YYYY-MM-DD HH:mm:ss") }}
        </p></a-space
      >

      <h1
        :class="{
          success: props.record.judgeInfo.message === '成功',
          failure: props.record.judgeInfo.message !== '成功',
        }"
      >
        {{ props.record.judgeInfo.message }}
      </h1>
      <!--      {{ question?.submitNum }}-->
      <!--      {{ question?.acceptedNum }}-->
      <h3 v-if="props.record.judgeInfo.message === '成功'">
        恭喜你,打败了{{ 100 - passrate }}%的用户
        <!--        恭喜你,这道题的通过率是{{ passrate }}%,而你就在其中-->
      </h3>
      <h3 v-if="props.record.judgeInfo.message !== '成功'">
        <a-space v-if="passrate < 50">
          这道题的通过率是{{ passrate }}%,请不要灰心
        </a-space>
        <a-space v-if="passrate >= 50">
          这道题的通过率是{{ passrate }}%,请加油
        </a-space>
      </h3>
      <a-space size="large">
        <a-statistic
          :value="Number(props.record.judgeInfo.time)"
          show-group-separator
        >
          <template #title><h3>时间消耗</h3></template>
        </a-statistic>

        <a-statistic
          :value="Number(props.record.judgeInfo.memory)"
          show-group-separator
        >
          <template #title><h3>空间消耗</h3></template>
        </a-statistic>
      </a-space>
    </div>

    <div>
      <a-space
        ><h3>代码展示:</h3>
        <a-tag bordered color="grey" size="small"
          >{{ props.record.language }}
        </a-tag>
      </a-space>
      <div>
        <a-space style="float: right">
          <a-typography-paragraph copyable :copy-text="props.record.code"
            >{{}}
          </a-typography-paragraph>
        </a-space>
        <a-textarea
          readonly="true"
          :default-value="props.record.code"
          :auto-size="{
            minRows: 2,
            maxRows: 12,
          }"
        />
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, defineProps, withDefaults } from "vue";
import { QuestionControllerService } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import moment from "moment";

const passrate = ref(0);

interface Props {
  record: any;
}

const question = ref();

const props = withDefaults(defineProps<Props>(), {
  record: () => "",
});
const loadData = async () => {
  const res = await QuestionControllerService.getQuestionVoByIdUsingGet(
    props.record.questionId
  );
  if (res.code === 0) {
    question.value = res.data;
    passrate.value =
      Math.round(
        (question?.value?.submitNum > 0
          ? (question?.value?.acceptedNum / question?.value?.submitNum) * 100
          : 0 * 100) * 100
      ) / 100;
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
</script>

<style scoped>
#questionSubmitDetail {
}

.success {
  color: green;
}

.failure {
  color: red;
}
</style>

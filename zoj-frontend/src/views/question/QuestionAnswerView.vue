<template>
  <div id="QuestionAnswerView">
    <h3>官方答案：</h3>
    <MdViewer :value="question?.answer || ''" />
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, defineProps, withDefaults } from "vue";
import { QuestionControllerService, QuestionVO } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import MdViewer from "@/components/MdViewer.vue";

interface Props {
  id: string;
}

const question = ref<QuestionVO>();
const props = withDefaults(defineProps<Props>(), {
  id: () => "",
});
const loadData = async () => {
  const res = await QuestionControllerService.getQuestionVoByIdUsingGet(
    props.id as any
  );
  if (res.code === 0) {
    question.value = res.data;
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
#QuestionAnswerView {
  max-width: 1400px;
  margin: 0 auto;
}

#QuestionAnswerView .arco-space-horizontal .arco-space-item {
  margin-bottom: 0 !important;
}
</style>

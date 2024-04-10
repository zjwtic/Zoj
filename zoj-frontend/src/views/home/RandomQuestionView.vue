<template>
  <div id="randomQuestionView">
    <div :style="{ display: 'flex' }">
      <a-card
        :style="{ width: '360px' }"
        title="随机一题"
        :header-style="{ backgroundColor: '#F3F4F5' }"
      >
        <template #extra>
          <a-link status="success" @click="loadData()">
            <icon-refresh :style="{ fontSize: '17px' }" />
          </a-link>
        </template>
        <a-link :href="`view/question/${dataList?.id}`"
          >#{{ dataList?.id }}.&nbsp;&nbsp; {{ dataList?.title }}
        </a-link>
      </a-card>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref } from "vue";
import { QuestionControllerService } from "../../../generated";
import message from "@arco-design/web-vue/es/message";

const dataList = ref([]) as any;

const loadData = async () => {
  const res = await QuestionControllerService.getOneRandomQuestionUsingPost();
  if (res.code === 0) {
    dataList.value = res.data;
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
#randomQuestionView {
}
</style>

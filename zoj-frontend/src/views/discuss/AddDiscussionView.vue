<template>
  <div id="addDiscussionView">
    <h2>添加讨论</h2>
    <a-form :model="form" label-align="left">
      <a-form-item field="title" label="标题" style="width: 900px">
        <a-input v-model="form.title" placeholder="请输入标题" />
      </a-form-item>
      <a-form-item field="tags" label="标签" style="width: 900px">
        <a-input-tag v-model="form.tags" placeholder="请选择标签" allow-clear />
      </a-form-item>
      <a-form-item field="content" label="讨论内容" style="width: 1000px">
        <MdEditor :value="form.content" :handle-change="onContentChange" />
      </a-form-item>
      <div style="margin-top: 16px" />
      <a-form-item>
        <a-button
          type="primary"
          style="min-width: 200px; left: 45%"
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
import { PostControllerService } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRoute } from "vue-router";

const route = useRoute();
// 如果页面地址包含 update，视为更新页面
const updatePage = route.path.includes("update");

let form = ref({
  title: "",
  tags: [],
  content: "",
});

/**
 * 根据题目 id 获取老的数据
 */
const loadData = async () => {
  const id = route.query.id;
  if (!id) {
    return;
  }
  const res = await PostControllerService.getPostByIdUsingGet(id as any);
  if (res.code === 0) {
    form.value = res.data as any;
    if (!form.value.tags) {
      form.value.tags = [];
    } else {
      form.value.tags = JSON.parse(form.value.tags as any);
    }
  } else {
    message.error("加载失败，" + res.message);
  }
};

onMounted(() => {
  loadData();
});

const doSubmit = async () => {
  console.log(form.value);
  // 区分更新还是创建
  if (updatePage) {
    const res = await PostControllerService.editPostUsingPost(form.value);
    if (res.code === 0) {
      message.success("更新成功");
    } else {
      message.error("更新失败，" + res.message);
    }
  } else {
    const res = await PostControllerService.addPostUsingPost(form.value);
    if (res.code === 0) {
      message.success("创建成功");
    } else {
      message.error("创建失败，" + res.message);
    }
  }
};

const onContentChange = (value: string) => {
  form.value.content = value;
};
</script>

<style scoped>
#addDiscussionView {
  position: absolute;
  left: 40%;
  margin-left: -24%;
  width: 48%;
}
</style>

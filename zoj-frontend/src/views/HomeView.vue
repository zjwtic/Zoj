<!--<template>-->
<!--  <div id="homeView">这是 主页</div>-->
<!--  &lt;!&ndash;  <CodeViewer value="212121212" language="java" />&ndash;&gt;-->
<!--</template>-->

<!--<script setup lang="ts">-->
<!--// import CodeViewer from "@/components/CodeViewer.vue";-->
<!--</script>-->

<!--<style scoped>-->
<!--#homeView {-->
<!--}-->
<!--</style>-->
<template>
  <a-table :columns="columns" :data="data" :row-key="data">
    <template #index="{ record }">
      {{ record.key }}
    </template>
  </a-table>
</template>

<script>
import { reactive } from "vue";

export default {
  setup() {
    const columns = [
      {
        title: "序号",
        width: 70,
        slotName: "index",
      },
      {
        title: "Name",
        dataIndex: "name",
      },
      {
        title: "Salary",
        dataIndex: "salary",
      },
      {
        title: "Address",
        dataIndex: "address",
      },
      {
        title: "Email",
        dataIndex: "email",
      },
    ];
    const data = reactive([
      {
        key: "1",
        name: "Jane Doe",
        salary: 23000,
        address: "32 Park Road, London",
        email: "jane.doe@example.com",
      },
      {
        key: "2",
        name: "Alisa Ross",
        salary: 25000,
        address: "35 Park Road, London",
        email: "alisa.ross@example.com",
      },
      {
        key: "3",
        name: "Kevin Sandra",
        salary: 22000,
        address: "31 Park Road, London",
        email: "kevin.sandra@example.com",
      },
      {
        key: "4",
        name: "Ed Hellen",
        salary: 17000,
        address: "42 Park Road, London",
        email: "ed.hellen@example.com",
      },
      {
        key: "5",
        name: "William Smith",
        salary: 27000,
        address: "62 Park Road, London",
        email: "william.smith@example.com",
      },
    ]);

    return {
      columns,
      data,
    };
  },
};
</script>

<!--&lt;!&ndash;<template>&ndash;&gt;-->
<!--&lt;!&ndash;  <div id="homeView">这是 主页</div>&ndash;&gt;-->
<!--&lt;!&ndash;  &lt;!&ndash;  <CodeViewer value="212121212" language="java" />&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;</template>&ndash;&gt;-->

<!--&lt;!&ndash;<script setup lang="ts">&ndash;&gt;-->
<!--&lt;!&ndash;// import CodeViewer from "@/components/CodeViewer.vue";&ndash;&gt;-->
<!--&lt;!&ndash;</script>&ndash;&gt;-->

<!--&lt;!&ndash;<style scoped>&ndash;&gt;-->
<!--&lt;!&ndash;#homeView {&ndash;&gt;-->
<!--&lt;!&ndash;}&ndash;&gt;-->
<!--&lt;!&ndash;</style>&ndash;&gt;-->
<!--<template>-->
<!--  <a-table :columns="columns" :data="data" :row-key="data">-->
<!--    <template #index="{ record }">-->
<!--      {{ record.key }}-->
<!--    </template>-->
<!--  </a-table>-->
<!--</template>-->

<!--<script>-->
<!--import { reactive } from "vue";-->

<!--export default {-->
<!--  setup() {-->
<!--    const columns = [-->
<!--      {-->
<!--        title: "序号",-->
<!--        width: 70,-->
<!--        slotName: "index",-->
<!--      },-->
<!--      {-->
<!--        title: "Name",-->
<!--        dataIndex: "name",-->
<!--      },-->
<!--      {-->
<!--        title: "Salary",-->
<!--        dataIndex: "salary",-->
<!--      },-->
<!--      {-->
<!--        title: "Address",-->
<!--        dataIndex: "address",-->
<!--      },-->
<!--      {-->
<!--        title: "Email",-->
<!--        dataIndex: "email",-->
<!--      },-->
<!--    ];-->
<!--    const data = reactive([-->
<!--      {-->
<!--        key: "1",-->
<!--        name: "Jane Doe",-->
<!--        salary: 23000,-->
<!--        address: "32 Park Road, London",-->
<!--        email: "jane.doe@example.com",-->
<!--      },-->
<!--      {-->
<!--        key: "2",-->
<!--        name: "Alisa Ross",-->
<!--        salary: 25000,-->
<!--        address: "35 Park Road, London",-->
<!--        email: "alisa.ross@example.com",-->
<!--      },-->
<!--      {-->
<!--        key: "3",-->
<!--        name: "Kevin Sandra",-->
<!--        salary: 22000,-->
<!--        address: "31 Park Road, London",-->
<!--        email: "kevin.sandra@example.com",-->
<!--      },-->
<!--      {-->
<!--        key: "4",-->
<!--        name: "Ed Hellen",-->
<!--        salary: 17000,-->
<!--        address: "42 Park Road, London",-->
<!--        email: "ed.hellen@example.com",-->
<!--      },-->
<!--      {-->
<!--        key: "5",-->
<!--        name: "William Smith",-->
<!--        salary: 27000,-->
<!--        address: "62 Park Road, London",-->
<!--        email: "william.smith@example.com",-->
<!--      },-->
<!--    ]);-->

<!--    return {-->
<!--      columns,-->
<!--      data,-->
<!--    };-->
<!--  },-->
<!--};-->
<!--</script>-->
<template>
  <div>
    <button @click="showPopup = true">打开弹出窗口</button>
    <div v-if="showPopup" class="popup">
      <div class="popup-content">
        <h2>这是一个弹出窗口</h2>
        <p>窗口内容...</p>
        <button @click="closePopup">关闭</button>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      showPopup: false,
    };
  },
  methods: {
    closePopup() {
      this.showPopup = false;
    },
  },
};
</script>

<style>
.popup {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1000;
}

.popup-content {
  background: #fff;
  padding: 20px;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
}
</style>

<template>
  <a-row id="globalHeader" align="center" :wrap="false">
    <a-col flex="auto">
      <a-menu
        mode="horizontal"
        :selected-keys="selectedKeys"
        @menu-item-click="doMenuClick"
      >
        <a-menu-item
          key="0"
          :style="{ padding: 0, marginRight: '38px' }"
          disabled
        >
          <div class="titie-bar">
            <img class="logo" src="../assets/ojlogo.png" />
            <!--            <div class="title">z oj</div>-->
          </div>
        </a-menu-item>
        <a-menu-item v-for="item in visibleRoutes" :key="item.path">
          {{ item.name }}
        </a-menu-item>
      </a-menu>
    </a-col>
    <a-col flex="140px">
      <UserAvatarView />
    </a-col>
  </a-row>
</template>
<script setup lang="ts">
import { routes } from "@/router/routes";
import { useRoute, useRouter } from "vue-router";
import { computed, ref } from "vue";
import { useStore } from "vuex";
import checkAccess from "@/access/checkAccess";
import ACCESS_ENUM from "@/access/accessEnum";
import { UserControllerService } from "../../generated";
import message from "@arco-design/web-vue/es/message";
import router from "@/router";
import UserAvatarView from "@/views/usermessage/UserAvatarView.vue";

const store = useStore();
const rounter = useRouter(); //数据

//展示在菜单的路由
const visibleRoutes = computed(() => {
  return routes.filter((item, index) => {
    if (item.meta?.hideInMenu) {
      return false;
    }
    //  根据权限过滤菜单
    if (
      !checkAccess(store.state.user.loginUser, item?.meta?.access as string)
    ) {
      return false;
    }
    return true;
  });
});

//默认主页
const selectedKeys = ref(["/"]);

//路由跳转后，更新选中的菜单项
rounter.afterEach((to, from, failure) => {
  selectedKeys.value = [to.path];
});

//3秒后得到username值

// setTimeout(() => {
//   store.dispatch("user/getLoginUser", {
//     userName: "周俊玮",
//     userRole: ACCESS_ENUM.DMIN,
//   });A
// }, 3000);

const doMenuClick = (key: string) => {
  rounter.push({
    path: key,
  });
}; //函数
</script>
<style scoped>
.titie-bar {
  display: flex;
  align-items: center;
}

.logo {
  height: 60px;
  width: 90px;
}

.title {
  color: #444;
  margin-left: 16px;
}
</style>

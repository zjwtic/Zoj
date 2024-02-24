<template>
  <div id="userAvatarView">
    <div id="nologin" v-if="!islogin()">
      <a-space>
        <a-button @click="login">登录</a-button>
        <a-button @click="register">注册</a-button>
      </a-space>
    </div>
    <div id="haslogined" v-if="islogin()">
      <a-dropdown>
        <a-space size="medium">
          <a-avatar trigger-type="mask">
            <img alt="avatar" :src="store.state.user?.loginUser?.userAvatar" />
            <template #trigger-icon>
              <IconEdit />
            </template>
          </a-avatar>
          <div>{{ store.state.user?.loginUser?.userName }}</div>
        </a-space>
        <template #content>
          <a-doption @click="logout">退出登录</a-doption>
          <a-doption @click="usershow">个人设置</a-doption>
          <a-doption>我的题单</a-doption>
          <a-doption>参加的比赛</a-doption>
          <a-doption @click="mydiscussshow">我的讨论</a-doption>
        </template>
      </a-dropdown>
    </div>
  </div>
</template>

<script setup lang="ts">
//退出登录
import { UserControllerService } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useStore } from "vuex";
import { useRouter } from "vue-router";
import { ref } from "vue";

const store = useStore();
const router = useRouter();
const logout = async () => {
  const res = await UserControllerService.userLogoutUsingPost();
  if (res.code === 0) {
    message.success("退出登录成功");
    router.push({
      path: "/user/login",
      replace: true,
    });
  } else {
    message.error("退出失败" + res.message);
  }
};
const islogin = () => {
  return store.state.user?.loginUser?.userName !== "未登录";
};

const login = () => {
  router.push({
    path: "/user/login",
  });
};

const register = () => {
  router.push({
    path: "/user/register",
  });
};

const usershow = () => {
  router.push({
    path: "/usermessage/show",
  });
};

const mydiscussshow = () => {
  router.push({
    path: "/manage/own/discussion",
  });
};
</script>

<style scoped>
#userAvatarView {
}

#userAvatarView #nologin {
  padding-right: 30px;
}

#userAvatarView #haslogined {
}
</style>

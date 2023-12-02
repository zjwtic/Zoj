<template>
  <div id="userLoginView">
    <h2 style="margin-bottom: 40px">用户登录</h2>
    <a-form
      style="max-width: 480px; margin: 0 auto"
      label-align="left"
      auto-label-width
      :model="form"
      @submit="handleSubmit"
    >
      <a-form-item field="userAccount" label="账号" style="font-weight: bold">
        <a-input v-model="form.userAccount" placeholder="请输入账号" />
      </a-form-item>
      <a-form-item
        field="userPassword"
        tooltip="密码不少于 8 位"
        label="密码"
        style="font-weight: bold"
      >
        <a-input-password
          v-model="form.userPassword"
          placeholder="请输入密码"
        />
      </a-form-item>
      <a-form-item style="margin-left: 70px">
        <a-button type="primary" html-type="submit" style="width: 120px"
          >登录
        </a-button>
      </a-form-item>
      <a-form-item style="margin-left: 70px">
        <a-button type="primary" @click="handleRegister" style="width: 120px"
          >立即注册
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script setup lang="ts">
import { reactive } from "vue";
import { UserControllerService, UserLoginRequest } from "../../../generated";
import message from "@arco-design/web-vue/es/message";
import { useRouter } from "vue-router";
import ACCESS_ENUM from "@/access/accessEnum";
import { useStore } from "vuex";

const form = reactive({
  userAccount: "",
  userPassword: "",
} as UserLoginRequest);
const router = useRouter();
const store = useStore();
/**
 * 提交表单
 * @param data
 */
const handleSubmit = async () => {
  const res = await UserControllerService.userLoginUsingPost(form);
  //登录成功 ，跳转到主页
  if (res.code === 0) {
    await store.dispatch("user/getLoginUser");
    message.success("登录成功");
    router.push({
      path: "/home",
      replace: true,
    });
  } else {
    message.error("登录失败" + res.message);
  }
};
const handleRegister = async () => {
  router.push({
    path: "/user/register",
  });
};
</script>
<style scoped>
#userLoginView {
}
</style>

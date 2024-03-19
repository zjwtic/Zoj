<!--<template>-->
<!--  <div id="userMessageShow">-->
<!--    <a-space direction="vertical" :style="{ width: '100%' }">-->
<!--      <a-upload-->
<!--        action="/"-->
<!--        :fileList="file ? [file] : []"-->
<!--        :show-file-list="false"-->
<!--        @change="onChange"-->
<!--        @progress="onProgress"-->
<!--      >-->
<!--        <template #upload-button>-->
<!--          <div-->
<!--            :class="`arco-upload-list-item${-->
<!--              file && file.status === 'error'-->
<!--                ? ' arco-upload-list-item-error'-->
<!--                : ''-->
<!--            }`"-->
<!--          >-->
<!--            <div-->
<!--              class="arco-upload-list-picture custom-upload-avatar"-->
<!--              v-if="file && file.url"-->
<!--            >-->
<!--              <img :src="file.url" />-->
<!--              <div class="arco-upload-list-picture-mask">-->
<!--                <IconEdit />-->
<!--              </div>-->
<!--              <a-progress-->
<!--                v-if="file.status === 'uploading' && file.percent < 100"-->
<!--                :percent="file.percent"-->
<!--                type="circle"-->
<!--                size="mini"-->
<!--                :style="{-->
<!--                  position: 'absolute',-->
<!--                  left: '50%',-->
<!--                  top: '50%',-->
<!--                  transform: 'translateX(50%) translateY(-50%)',-->
<!--                }"-->
<!--              />-->
<!--            </div>-->
<!--            <div style="margin-top: 10px; font-weight: 600">-->
<!--              <a-space size="90px">-->
<!--                <a-avatar-group :size="90" :max-count="3">-->
<!--                  <a-avatar trigger-type="mask">-->
<!--                    <img alt="avatar" :src="fileurl" />-->
<!--                    <template #trigger-icon>-->
<!--                      <IconEdit />-->
<!--                    </template>-->
<!--                  </a-avatar>-->
<!--                </a-avatar-group>-->
<!--              </a-space>-->
<!--            </div>-->
<!--          </div>-->
<!--        </template>-->
<!--      </a-upload>-->
<!--    </a-space>-->
<!--  </div>-->
<!--</template>-->

<!--<script setup lang="ts">-->
<!--import { useStore } from "vuex";-->
<!--import { ref } from "vue";-->
<!--import {-->
<!--  FileControllerService,-->
<!--  UserControllerService,-->
<!--  UserUpdateMyRequest,-->
<!--} from "../../../generated";-->
<!--import message from "@arco-design/web-vue/es/message";-->
<!--import { IconCamera, IconEdit, IconUser } from "@arco-design/web-vue/es/icon";-->

<!--const store = useStore();-->
<!--const fileurl = ref(-->
<!--  store.state.user?.loginUser?.userAvatar ??-->
<!--    "https://p1-arco.byteimg.com/tos-cn-i-uwbnlip3yd/3ee5f13fb09879ecb5185e440cef6eb9.png~tplv-uwbnlip3yd-webp.webp"-->
<!--);-->
<!--const updateuser = {-->
<!--  userAvatar: fileurl,-->
<!--} as UserUpdateMyRequest;-->

<!--const biz = ref("user_avatar");-->
<!--const onChange = async (_: any, currentFile: any) => {-->
<!--  fileurl.value = URL.createObjectURL(currentFile.file);-->
<!--  // fileurl.value = await FileControllerService.uploadFileUsingPost(-->
<!--  //   "user_avatar",-->
<!--  //   currentFile-->
<!--  // );-->
<!--  // updateuser.userAvatar = fileurl.value;-->
<!--  // const res = await UserControllerService.updateMyUserUsingPost(updateuser);-->
<!--  // if (res.code === 0) {-->
<!--  //   message.success("修改成功");-->
<!--  // } else {-->
<!--  //   message.error("修改失败" + res.message);-->
<!--  // }-->
<!--};-->
<!--const onProgress = (currentFile: any) => {-->
<!--  fileurl.value = URL.createObjectURL(currentFile.file);-->
<!--  // updateuser.userAvatar = fileurl.value;-->
<!--};-->
<!--</script>-->

<style scoped>
#userMessageShow {
}
</style>
<template>
  <div id="userMessageShow">
    <a-descriptions-item>
      <a-avatar :size="100" shape="circle">
        <img alt="头像" :src="loginUser.userAvatar" />
      </a-avatar>
    </a-descriptions-item>
    <a-card title="我的信息">
      <a-descriptions :data="data" size="large" column="1" bordered />
      <template #extra>
        <a-badge status="success" text="在线" />
      </template>
    </a-card>
    <a-modal
      width="30%"
      :visible="updateMessageVisible"
      placement="right"
      @ok="handleOk(0)"
      @cancel="closeModel"
      unmountOnClose
    >
      <div style="text-align: center">
        <a-upload
          action="/"
          :fileList="file ? [file] : []"
          :show-file-list="false"
          @change="onChange"
          @progress="Onprogress"
        >
          <a-progress
            v-if="file.status === 'uploading' && file.percent < 100"
            :percent="file.percent"
            type="circle"
            size="mini"
          />

          <template #upload-button>
            <div
              class="arco-upload-list-picture custom-upload-avatar"
              v-if="updateForm || updateForm.userAvatar"
            >
              <a-avatar :size="70" shape="circle">
                <img alt="头像" :src="userAvatarImg" />
              </a-avatar>
              <div class="arco-upload-list-picture-mask">
                <IconEdit />
              </div>
            </div>
          </template>
        </a-upload>
      </div>
      <a-form
        :model="loginUser"
        label-align="right"
        title="个人信息"
        style="max-width: 480px; margin: 0 auto"
      >
        <a-form-item field="用户名称" label="名称 :">
          <a-input v-model="updateForm.userName" placeholder="请输入用户名称" />
        </a-form-item>
        <a-form-item field="账号名称" label="账号 :">
          <a-input
            v-model="updateForm.userAccount"
            placeholder="请输入账号名称"
          />
        </a-form-item>
        <!--        <a-form-item field="邮箱" label="邮箱 :">-->
        <!--          <a-input v-model="updateForm.email" placeholder="请输入邮箱" />-->
        <!--        </a-form-item>-->
        <!--        <a-form-item field="电话" label="电话 :">-->
        <!--          <a-input v-model="updateForm.phone" placeholder="请输入电话号码" />-->
        <!--        </a-form-item>-->
        <a-form-item field="userProfile" label="简介 :">
          <a-textarea
            v-model="updateForm.userProfile"
            placeholder="请输入简介"
          />
        </a-form-item>
      </a-form>
    </a-modal>

    <a-modal
      width="30%"
      :visible="updatePasswordVisible"
      placement="right"
      @ok="handleOk(1)"
      @cancel="closeModel"
      unmountOnClose
    >
      <a-form-item field="password" label="原密码" validate-trigger="blur">
        <a-input-password
          v-model="accessPassword"
          placeholder="请输入此账号原来密码..."
        />
      </a-form-item>
      <a-form-item field="password" label="现密码" validate-trigger="blur">
        <a-input-password
          v-model="password"
          placeholder="请输入要修改的密码..."
        />
      </a-form-item>
      <a-form-item field="password2" label="确认密码" validate-trigger="blur">
        <a-input-password v-model="password2" placeholder="请输入确认密码..." />
      </a-form-item>
    </a-modal>
    <div>
      <a-button
        shape="round"
        status="success"
        size="small"
        type="outline"
        style="margin: 10px"
      >
        <a-link @click="toIndex">首页</a-link>
      </a-button>
      <a-button
        shape="round"
        status="normal"
        size="medium"
        type="outline"
        style="margin: 10px"
        @click="openModalForm(0)"
        >修改用户信息
      </a-button>
      <a-button
        shape="round"
        status="normal"
        size="medium"
        type="outline"
        style="margin: 10px"
        @click="openModalForm(1)"
        >修改用户密码
      </a-button>
    </div>
  </div>
</template>
<script setup lang="ts">
import { useStore } from "vuex";
import {
  FileControllerService,
  UserControllerService,
  UserUpdateMyRequest,
} from "../../../generated";
import { ref } from "vue";
import { FileItem, Message } from "@arco-design/web-vue";
import { useRouter } from "vue-router";
import moment from "moment";
import { IconEdit } from "@arco-design/web-vue/es/icon";

const router = useRouter();
const file = ref();
/**
 * 获取用户信息
 */
const store = useStore();
let loginUser = store.state.user.loginUser;

const updateMessageVisible = ref(false);
const updatePasswordVisible = ref(false);

const password = ref();
const accessPassword = ref();
const password2 = ref();

const data = [
  {
    label: "用户名称：",
    value: loginUser.userName,
  },
  {
    label: "账号名称：",
    value: loginUser.userAccount,
  },
  {
    label: "我的简介：",
    value: loginUser.userProfile,
  },
  {
    label: "用户角色：",
    value: loginUser.userRole === "user" ? "普通用户" : "管理员",
  },
  // {
  //   label: "邮箱：",
  //   value: loginUser.email !== "" ? loginUser.email : "未填写",
  // },
  // {
  //   label: "电话：",
  //   value: loginUser.phone !== "" ? loginUser.phone : "未填写",
  // },
  // {
  //   label: "当前状态：",
  //   value: loginUser.userState !== "" ? loginUser.userState : "暂无简介",
  // },
  {
    label: "创建时间：",
    value: moment(loginUser.createTime).format("YYYY-MM-DD HH:mm:ss"),
  },
  {
    label: "修改时间：",
    value: moment(loginUser.updateTime).format("YYYY-MM-DD HH:mm:ss"),
  },
];

// const visible = ref(false);
const updateForm = ref<UserUpdateMyRequest>({
  ...store.state.user?.loginUser,
});

// 从表单中获取的用户头像
const userAvatarImg = ref(
  updateForm.value?.userAvatar ??
    "https://assets.leetcode.cn/aliyun-lc-upload/default_avatar.png?x-oss-process=image%2Fformat%2Cwebp"
);

/**
 * 上传头像
 */
const uploadAvatar = async () => {
  const res = await FileControllerService.uploadFileUsingPost(
    "user_avatar",
    file?.value.file
  );
  if (res.code === 0) {
    userAvatarImg.value = res.data;
    Message.success("上传成功");
  } else {
    Message.error("上传失败！" + res.data);
  }
};
/**
 * 打开弹窗
 */
// const openModalForm = () => {
//   userAvatarImg.value =
//     updateForm.value?.userAvatar ??
//     "https://assets.leetcode.cn/aliyun-lc-upload/default_avatar.png?x-oss-process=image%2Fformat%2Cwebp";
//   visible.value = true;
// };

/**
 * 打开弹窗
 */
const openModalForm = (type: number) => {
  if (type == 0) {
    userAvatarImg.value =
      updateForm.value?.userAvatar ??
      "https://assets.leetcode.cn/aliyun-lc-upload/default_avatar.png?x-oss-process=image%2Fformat%2Cwebp";
    updateMessageVisible.value = true;
  } else if (type == 1) {
    updatePasswordVisible.value = true;
  }
};
/**
 * 确定修改按钮
 */
// const handleOk = async () => {
//   if (file.value) {
//     await uploadAvatar();
//   }
//   const res = await UserControllerService.updateMyUserUsingPost({
//     ...updateForm.value,
//     userAvatar: userAvatarImg.value,
//   });
//   if (res.code === 0) {
//     Message.success("更新成功！");
//     visible.value = false;
//     // await store.dispatch("user/getLoginUser");
//     location.reload();
//   } else {
//     Message.error("更新失败！", res.msg);
//   }
// };
const handleOk = async (type: number) => {
  if (type == 0) {
    if (file.value) {
      await uploadAvatar();
    }
    const res = await UserControllerService.updateMyUserUsingPost({
      ...updateForm.value,
      userAvatar: userAvatarImg.value,
    });
    if (res.code === 0) {
      Message.success("更新成功！");
      updateMessageVisible.value = false;
      // await store.dispatch("user/getLoginUser");
      location.reload();
    } else {
      Message.error("更新失败！", res.msg);
    }
  } else if (type == 1) {
    if (!password.value) {
      Message.error("请输入密码！");
      return;
    }
    if (!accessPassword.value) {
      Message.error("请输入原密码！");
      return;
    }
    if (password.value != password2.value) {
      Message.error("确认密码错误,两次密码不一致！");
      return;
    }
    const res = await UserControllerService.updateMyUserUsingPost({
      ...updateForm.value,
      accessPassword: accessPassword.value,
      updatePassword: password.value,
    });
    if (res.code === 0) {
      Message.success("更新密码成功！");
      updatePasswordVisible.value = false;
      // await store.dispatch("user/getLoginUser");
      location.reload();
    } else if (res.code === 1) {
      Message.error("原密码不一致", res.meg);
    } else if (res.code === 40000) {
      Message.error("密码设置过短，至少8位", res.meg);
    } else {
      Message.error("更新密码失败", res.meg);
    }
  }
};
// const closeModel = () => {
//   visible.value = false;
// };

const closeModel = () => {
  updateMessageVisible.value = false;
  updatePasswordVisible.value = false;
};
/**
 * 回到首页
 * @param question
 */
const toIndex = () => {
  router.push({
    path: `/`,
  });
};
const onChange = async (_: never, currentFile: FileItem) => {
  file.value = {
    ...currentFile,
  };
  userAvatarImg.value = currentFile.url as string;
};

const onProgress = (currentFile: FileItem) => {
  file.value = {
    ...currentFile,
  };
  userAvatarImg.value = currentFile.url as string;
};
</script>

<style scoped>
#userMessageShow {
  margin: 0 auto;
  padding: 10px;
  max-width: 820px;
  border-radius: 10px;
  box-shadow: 0px 0px 10px rgba(35, 7, 7, 0.21);
}
</style>

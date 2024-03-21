import { StoreOptions } from "vuex";
import ACCESS_ENUM from "@/access/accessEnum";
import { UserControllerService } from "../../generated";

export default {
  namespaced: true,
  state: () => ({
    loginUser: {
      userName: "未登录",
      // userRole: ACCESS_ENUM.NOT_LOGIN,  //这里为什么没有 是区分  登录失败给userRole  未登录就没有userRole
    },
  }),
  actions: {
    async getLoginUser({ commit, state }, payload) {
      // 改为远程登录(从远程获取用户信息)
      const res = await UserControllerService.getLoginUserUsingGet();
      if (res.code === 0) {
        commit("updateUser", res.data);
      } else {
        commit("updateUser", {
          ...state.loginUser,
          userRole: ACCESS_ENUM.NOT_LOGIN,
          userAvatar:
            "https://assets.leetcode.cn/aliyun-lc-upload/default_avatar.png?x-oss-process=image%2Fformat%2Cwebp",
        });
      }
    },
  },
  mutations: {
    updateUser(state, payload) {
      state.loginUser = payload;
      if (state.loginUser.userName === null) {
        // 生成一个随机数，并格式化为字符串
        // const randomNumber = Math.random().toString(36).substring(7);
        // 将随机数附加到"Zoj用户"后面
        // state.loginUser.userName = "Zoj用户" + randomNumber;
        state.loginUser.userName = "Zoj用户";
      }
    },
  },
} as StoreOptions<any>;

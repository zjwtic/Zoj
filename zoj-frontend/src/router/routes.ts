import { RouteRecordRaw } from "vue-router";
import ExampleView from "@/views/ExampleView.vue";
import HomeView from "@/views/HomeView.vue";
import AdminView from "@/views/AdminView.vue";
import NoAuthView from "@/views/NoAuthView.vue";
import UserLayout from "@/layouts/UserLayout.vue";
import UserLoginView from "@/views/user/UserLoginView.vue";
import UserRegisterView from "@/views/user/UserRegisterView.vue";
import UserAvatarView from "@/views/usermessage/UserAvatarView.vue";
import UserMessageView from "@/views/usermessage/UserMessageView.vue";
import AddQuestionView from "@/views/question/AddQuestionView.vue";
import QuestionListView from "@/views/question/QuestionListView.vue";
import QuestionTopicView from "@/views/question/QuestionTopicView.vue";
import ManageQuestionView from "@/views/question/ManageQuestionView.vue";
import MatchShowView from "@/views/match/MatchShowView.vue";
import ExaminationShowView from "@/views/examination/ExaminationShowView.vue";
import QuestionSubmitShowView from "@/views/questionSubmit/QuestionSubmitShowView.vue";
import DiscussShowView from "@/views/discuss/DiscussShowView.vue";

import ACCESS_ENUM from "@/access/accessEnum";

export const routes: Array<RouteRecordRaw> = [
  {
    path: "/user",
    name: "用户",
    component: UserLayout,
    children: [
      {
        path: "/user/login",
        name: "用户登录",
        component: UserLoginView,
      },
      {
        path: "/user/register",
        name: "用户注册",
        component: UserRegisterView,
      },
    ],
    meta: {
      hideInMenu: true,
    },
  },

  {
    path: "/home",
    name: "主页",
    component: HomeView,
  },
  {
    path: "/",
    redirect: "/home",
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/usermessage/show",
    name: "用户信息展示",
    component: UserMessageView,
  },
  {
    path: "/topic",
    name: "题库",
    component: QuestionListView,
  },
  {
    path: "/questionlist",
    name: "提单",
    component: QuestionTopicView,
  },
  {
    path: "/match",
    name: "比赛",
    component: MatchShowView,
  },
  {
    path: "/examination",
    name: "考试",
    component: ExaminationShowView,
  },
  {
    path: "/questionsubmitshow",
    name: "提交记录",
    component: QuestionSubmitShowView,
    meta: {
      access: ACCESS_ENUM.USER,
    },
  },
  {
    path: "/discussshow",
    name: "讨论",
    component: DiscussShowView,
  },

  // {
  //   path: "/admin",
  //   name: " 管理员可见",
  //   component: AdminView,
  //   meta: {
  //     access: ACCESS_ENUM.ADMIN,
  //   },
  // },
  // {
  //     path: "/hide",
  //     name: "隐藏页面",
  //     component: ExampleView,
  //     meta: {
  //       hideInMenu: true,
  //     },
  //   },
  {
    path: "/update/question",
    name: "更新题目",
    component: AddQuestionView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/add/question",
    name: "创建题目",
    component: AddQuestionView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },

  {
    path: "/manage/question",
    name: "管理题目",
    component: ManageQuestionView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/browse/question",
    name: "浏览题目",
    component: ExampleView,
  },
  {
    path: "/usermessage/avatar",
    name: "头像展示",
    component: UserAvatarView,
  },
];

import { RouteRecordRaw } from "vue-router";
import HomeView from "@/views/HomeView.vue";
import UserLayout from "@/layouts/UserLayout.vue";
import DoContestLayout from "@/layouts/DoContestLayout.vue";
import UserLoginView from "@/views/user/UserLoginView.vue";
import UserRegisterView from "@/views/user/UserRegisterView.vue";
import UserMessageView from "@/views/usermessage/UserMessageView.vue";
import AddQuestionView from "@/views/question/AddQuestionView.vue";
import QuestionsView from "@/views/question/QuestionsView.vue";
import QuestionSubmitView from "@/views/question/QuestionSubmitView.vue";
import QuestionSubmitByOwnView from "@/views/questionSubmit/QuestionSubmitByOwnView.vue";
import ManageQuestionView from "@/views/question/ManageQuestionView.vue";
import ViewQuestionView from "@/views/question/ViewQuestionView.vue";
import DiscussionShowView from "@/views/discuss/DiscussionShowView.vue";
import DiscussionContentView from "@/views/discuss/DiscussionContentView.vue";
import AddDiscussionView from "@/views/discuss/AddDiscussionView.vue";
import AddContestView from "@/views/contest/AddContestView.vue";
import ContestsView from "@/views/contest/ContestsView.vue";
import DoContestView from "@/views/contest/DoContestView.vue";
import ContestResultView from "@/views/contest/ContestResultView.vue";
import ManageContestView from "@/views/contest/ManageContestView.vue";
import ManageDiscussionView from "@/views/discuss/ManageDiscussionView.vue";
import RanksView from "@/views/rank/RanksView.vue";

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
    path: "/online",
    name: "在线",
    component: DoContestLayout,
    children: [
      {
        path: "/online/contest/:id",
        name: "在线比赛",
        component: DoContestView,
        props: true,
      },
    ],
    meta: {
      access: ACCESS_ENUM.USER,
      hideInMenu: true,
    },
  },

  {
    path: "/home",
    name: "首页",
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
    meta: {
      access: ACCESS_ENUM.USER,
      hideInMenu: true,
    },
  },

  {
    path: "/add/contest",
    name: "添加比赛",
    component: AddContestView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
      // hideInMenu: true,
    },
  },
  {
    path: "/manage/contest",
    name: "管理比赛",
    component: ManageContestView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/contests",
    name: "比赛",
    component: ContestsView,
  },
  {
    path: "/update/contest",
    name: "更新比赛",
    component: AddContestView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
      hideInMenu: true,
    },
  },
  {
    path: "/contestresult/:id",
    name: "查看比赛结果",
    component: ContestResultView,
    props: true,
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/manage/discussion",
    name: "管理讨论",
    component: ManageDiscussionView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/discussions",
    name: "讨论",
    component: DiscussionShowView,
  },
  {
    path: "/view/discussion/:id",
    name: "讨论内容",
    props: true,
    component: DiscussionContentView,
    meta: {
      hideInMenu: true,
    },
  },
  {
    path: "/add/discussion",
    name: "添加讨论",
    component: AddDiscussionView,
    meta: {
      access: ACCESS_ENUM.USER,
      hideInMenu: true,
    },
  },
  {
    path: "/update/discussion",
    name: "更新讨论",
    component: AddDiscussionView,
    meta: {
      access: ACCESS_ENUM.USER,
      hideInMenu: true,
    },
  },

  {
    path: "/manage/own/discussion",
    name: "管理个人的讨论",
    component: ManageDiscussionView,
    meta: {
      access: ACCESS_ENUM.USER,
      hideInMenu: true,
    },
  },
  {
    path: "/update/question",
    name: "更新题目",
    component: AddQuestionView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
      hideInMenu: true,
    },
  },
  {
    path: "/add/question",
    name: "添加题目",
    component: AddQuestionView,
    meta: {
      access: ACCESS_ENUM.ADMIN,
    },
  },
  {
    path: "/view/question/:id",
    name: "在线做题",
    component: ViewQuestionView,
    props: true,
    meta: {
      access: ACCESS_ENUM.USER,
      hideInMenu: true,
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
    path: "/questions",
    name: "题库",
    component: QuestionsView,
  },
  {
    path: "/ranks",
    name: "排行榜",
    component: RanksView,
  },
  {
    path: "/question_submit",
    name: "评测",
    component: QuestionSubmitView,
  },
  {
    path: "/question_submit_by_own",
    name: "浏览自己的题目提交",
    component: QuestionSubmitByOwnView,
    meta: {
      access: ACCESS_ENUM.USER,
      hideInMenu: true,
    },
  },
];

<template>
  <div id="homeView">
    <div style="position: absolute; top: 140px; right: 170px">
      <RandomQuestionView />
    </div>
    <div style="position: absolute; top: 290px; right: 170px">
      <HomeRankView />
    </div>

    <a-space direction="vertical" style="margin-left: 170px">
      <div style="margin-bottom: 30px; margin-top: 30px; width: 1200px">
        <HomeDiscussView />
      </div>
      <div style="margin-bottom: 30px; width: 1200px">
        <HomeQuestionView />
      </div>
    </a-space>
  </div>
</template>

<script setup lang="ts">
import HomeRankView from "@/views/home/HomeRankView.vue";
import HomeDiscussView from "@/views/home/HomeDiscussView.vue";
import HomeQuestionView from "@/views/home/HomeQuestionView.vue";
import RandomQuestionView from "@/views/home/RandomQuestionView.vue";
</script>

<style scoped>
#homeView {
  /*margin-right: 70px;*/
  /*transform: scale(0.9);*/
  /*transform-origin: top left; !* 根据需要调整缩放的起点 *!*/
}
</style>

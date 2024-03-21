<template>
  <div id="ranksView">
    <a-tabs>
      <a-tab-pane key="1">
        <template #title>
          <icon-check-circle />
          AC排行榜
        </template>
        <RankAcceptedView />
      </a-tab-pane>
      <a-tab-pane key="2">
        <template #title>
          <icon-trophy />
          比赛积分排行榜
        </template>
        <RanksPointsView />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<script setup lang="ts">
import RankAcceptedView from "@/views/rank/RankAcceptedView.vue";
import RanksPointsView from "@/views/rank/RanksPointsView.vue";
</script>

<style scoped>
#ranksView {
}
</style>

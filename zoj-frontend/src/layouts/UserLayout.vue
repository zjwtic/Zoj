<template>
  <div id="DoContestLayout">
    <div id="adminbox">
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
    </div>
  </div>
</template>
<style scoped>
#DoContestLayout {
  padding-top: 30vh;
  height: 100vh;
  text-align: center;
  background-image: url("../assets/logobackground.jpg");
  background-repeat: no-repeat;
  background-position: center top;
  background-size: cover;
}

#DoContestLayout .logo {
  width: 64px;
  height: 64px;
}

#DoContestLayout #adminbox {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 13px;
  text-align: center;
  margin: 0px auto;
  margin-top: 80px;
  width: 450px;
  height: 430px;
}

#DoContestLayout .header {
  margin-top: 16px;
}

#DoContestLayout .content {
  margin-bottom: 16px;
  padding-top: 40px;
  padding-bottom: 70px;
  padding-left: 40px;
  padding-right: 40px;
}

#DoContestLayout .footer {
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>
<script></script>

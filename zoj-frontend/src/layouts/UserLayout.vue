<template>
  <div id="userLayout">
    <div id="adminbox">
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
    </div>
  </div>
</template>
<style scoped>
#userLayout {
  padding-top: 30vh;
  height: 100vh;
  text-align: center;
  background-image: url("../assets/login.jpg");
  background-repeat: no-repeat;
  background-position: center top;
  background-size: cover;
}

#userLayout .logo {
  width: 64px;
  height: 64px;
}

#userLayout #adminbox {
  background-color: rgba(255, 255, 255, 0.6);
  border-radius: 13px;
  text-align: center;
  margin: 0px auto;
  margin-top: 80px;
  width: 450px;
  height: 430px;
}

#userLayout .header {
  margin-top: 16px;
}

#userLayout .content {
  margin-bottom: 16px;
  padding-top: 40px;
  padding-bottom: 70px;
  padding-left: 40px;
  padding-right: 40px;
}

#userLayout .footer {
  padding: 16px;
  position: sticky;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}
</style>
<script></script>

<template>
  <div id="userLayout">
    <a-layout style="min-height: 100vh">
      <a-layout-header class="header">
        <a-space
          ><img src="../assets/ojlogo.png" class="logo" />
          <!--          <div>z o</div>-->
        </a-space>
      </a-layout-header>
      <a-layout-content class="content">
        <router-view />
      </a-layout-content>
      <a-layout-footer class="footer">Zoj by 周俊玮</a-layout-footer>
    </a-layout>
  </div>
</template>
<style scoped>
#userLayout {
  text-align: center;
  background: url("../assets/loginbackground.jpg");
}

#userLayout .logo {
  width: 64px;
  height: 64px;
}

#userLayout .header {
  margin-top: 16px;
}

#userLayout .content {
  margin-bottom: 16px;
  padding: 20px;
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

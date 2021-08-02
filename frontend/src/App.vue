<template>
  <div id="app" class="screen">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: "app"
};
</script>

<style lang="scss">
@import "@/assets/css/normalize.scss";
@import "@/assets/css/common.scss";

body {
  background-color: grey;
}

/* 작을 때 */
.screen {
  background-color: white;
}

/* 가로412 세로781 & 폰기종 s10 */
@media (min-width: 412px) {
  .screen {
    width: 412px;
    background-color: white;
    margin : 0 auto;
  }
}
@media (min-height: 781px) {
  .screen {
    height: 781px;
    background-color: white;
    margin : 0 auto;
  }
}
</style>

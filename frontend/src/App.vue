<template>
  <div id="app" class="screen">
    <router-view/>
  </div>
</template>

<script>
import "@/assets/css/normalize.css";
export default {
  name: "app"
};
</script>

<style>
/* 작을 때 */
.screen {
  background-color: red;
}

/* 425px 이상 */
/* 가로425 세로850 */
@media (min-width: 425px) {
  .screen {
    width: 425px;
    background-color: white;
    margin : 0 auto;
  }
}
@media (min-width: 850px) {
  .screen {
    height: 850px;
    background-color: white;
    margin : 0 auto;
  }
}
</style>
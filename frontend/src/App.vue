<template>
  <div id="app" class="screen">
    <router-view/>
  </div>
</template>

<script>
import "./assets/css/normalize.css";
export default {
  name: "app"
};
</script>

<style>
/* 작을 때 */
.screen {
  background-color: red;
}

/* 500px 이상 */
@media (min-width: 425px) {
  .screen {
    width: 425px;
    background-color: red;
    margin: 0 auto;
  }
}
</style>
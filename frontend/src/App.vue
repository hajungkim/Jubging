<template>
  <div id="app" class="screen">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/about">About</router-link>
    </div>
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
@media (min-width: 500px) {
  .screen {
    width: 500px;
    background-color: red;
  }
}
</style>
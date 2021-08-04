<template>
  <div>
  <router-link :to="{name:'Detail'}" >
    <img class="photo-img" :src="article.photosPath">
  </router-link>
  </div>
</template>

<script>
export default {
  name:'PhotoList',
  props:{
    article:{
      type:Object,
    },
  },
}
</script>

<style scoped>
.photo-img {
  display: inline-block;
  width: 185px;
  height: 185px;
  border-radius: 10%;
  margin-bottom: 14px;
}
</style>
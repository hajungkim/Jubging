<template>
  <div class="follow_container">
    <img class="photo-img" :src="article.url">
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

<style>

</style>
<template>
  <div >
    <img @click="onClick(article)" class="photo-img" :src="article.photosPath">
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
  methods:{
    onClick(article){
      this.$store.state.selectArticle=article
      this.$router.push({name:'Detail'})
    }
  }
}
</script>

<style scoped>
.photo-img {
  display: inline-block;
  width: 185px;
  height: 185px;
  border-radius: 10%;
  margin-bottom: 14px;
  cursor:pointer;
}
</style>
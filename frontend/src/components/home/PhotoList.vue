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
      this.$store.state.selectArticle = article
      this.$router.push({name:'Detail'})
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/components/home/PhotoList.scss";
</style>
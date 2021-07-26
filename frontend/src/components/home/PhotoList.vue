<template>
    <article class="location-listing">
      <router-link :to="{name:'Detail'}">
        <img src="@/assets/sample.png" width="180px" height="180px">
      </router-link>
    </article>
</template>

<script>
export default {
  name:'PhotoList',
  props:{
    img_url:{
      type:Object,
    }
  },
  data(){
  },
  created(){
    console.log(this.img_url.url)
  },
}
</script>

<style>
.location-listing{
  margin-left:10px;
  padding: 5px;
}
</style>
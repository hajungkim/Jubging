<template>
  <div class="grid-container">
      <article class="location-listing">
        <div class="article-left1">
          <router-link :to="{name:'Detail'}">
            <img src="../../assets/sample.png" width="180px" height="180px">
          </router-link>
        </div>  
      </article>
      <article class="location-listing">
        <div class="article-right2">
          <router-link :to="{name:'Detail'}">
            <img src="../../assets/sample.png" width="180px" height="180px">
          </router-link>
        </div>  
      </article>
  </div>
</template>

<script>
export default {
  name:'PhotoList',
  props:{
    img_url:{
      type:String,
    }
  },
  created(){
    console.log('@@',this.img_url)
  },
}
</script>

<style>
.grid-container{
  display: flex;
  justify-content: center;
}
.location-listing{
  padding: 5px;
}
</style>
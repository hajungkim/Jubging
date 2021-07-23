<template>
<div class="container">
  <router-view>
  </router-view>
  <div class="bottom-bar">
    <router-link :to="{name:'Home'}" @click.native="onClick(1)">홈</router-link>
    <router-link :to="{name:'Mission'}" @click.native="onClick(2)">미션</router-link>
    <router-link :to="{name:'Jubging'}" @click.native="onClick(3)">줍깅</router-link>
    <router-link :to="{name:'Ranking'}" @click.native="onClick(4)">랭킹</router-link>
    <router-link :to="{name:'My'}" @click.native="onClick(5)">마이</router-link>
  </div>
</div>
</template>

<script>
export default {
  name:'Main',
  data(){
    return {
      links:[
        {
          id:0,
          text:'홈',
          page:'/main',
        },
        {
          id:1,
          text:'미션',
          page:'/mission',
        },
        {
          id:2,
          text:'줍깅',
          page:'/jubging',
        },
        {
          id:3,
          text:'랭킹',
          page:'/ranking',
        },
        {
          id:4,
          text:'마이',
          page:'/my',
        },
      ]
    }
  },
  methods:{
    onClick(link){
      this.$store.dispatch('isCurrent',link)
    },
  },
  computed:{
  }
}
</script>

<style>
.container{
  display: flex;
  flex-direction: column;
  height:775px;
}
.bottom-bar{
  display: flex;
  justify-content: space-around;
  margin-top:auto;
  /* top:750px; */
}
</style>
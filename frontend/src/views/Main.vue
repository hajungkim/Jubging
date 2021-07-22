<template>
<div>
  <router-view>
  </router-view>
  <div class="bottom-bar">
    <router-link v-for="link in links" v-bind:key="link.id"
      :to="`${link.page}`" @click.native="onClick(link)">{{link.text}}</router-link>
  </div>
</div>
</template>

<script>
// import Home from '@/components/home/Home.vue'
// import Mission from '@/components/mission/Mission.vue'
// import Jubging from '@/components/jubging/Jubging.vue'
// import Ranking from '@/components/ranking/Ranking.vue'
// import My from '@/components/my/My.vue'
export default {
  name:'Main',
  // components: {
  //   Home,
  //   Mission,
  //   Jubging,
  //   Ranking,
  //   My,
  // },
  data(){
    return {
      links:[
        {
          id:0,
          text:'홈',
          page:'/home',
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
      this.$store.dispatch('isCurrent',link.id)
    },
  },
  computed:{
  }
}
</script>

<style>
.bottom-bar{
  display: flex;
  justify-content: space-between;
}
</style>
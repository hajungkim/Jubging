<template>
<div class="container">
  <router-view>
  </router-view>
  
  <div class="bottom-nav3">
    <router-link :to="{ name: 'Home' }" class="item" @click.native="onClick(1)" id="item-active-1">
      <font-awesome-icon icon="home" />
      <span>홈</span>
    </router-link>

    <router-link :to="{ name: 'Mission' }" class="item" @click.native="onClick(2)" id="item-active-2">
      <font-awesome-icon icon="medal" />
      <span>미션</span>
    </router-link>
    
    <router-link :to="{ name: 'Jubging' }" class="item" @click.native="onClick(3)" id="item-active-3">
      <font-awesome-icon icon="shoe-prints" />
      <span>줍깅</span>
    </router-link>
    
    <router-link :to="{ name: 'Ranking' }" class="item" @click.native="onClick(4)" id="item-active-4">
      <font-awesome-icon icon="trophy" />
      <span>랭킹</span>
    </router-link>

    <router-link :to="{ name: 'My' }" class="item" @click.native="onClick(5)" id="item-active-5">
      <font-awesome-icon icon="user" />
      <span>마이</span>
    </router-link>
  </div>

</div>
</template>

<script>
export default {
  name:'Main',
  methods:{
    onClick(link){
      this.$store.dispatch('isCurrent', link)
      if (this.$route.name !== 'Login') {
        document.querySelector('.router-link-active').classList.remove('item-active')
        document.querySelector(`#item-active-${link}`).classList.add('item-active')
      }
    },
  },
  mounted(){
    document.querySelector('.router-link-active').classList.add('item-active')
  }
}
</script>

<style lang="scss" scoped>
@import "@/views/Main.scss";

</style>

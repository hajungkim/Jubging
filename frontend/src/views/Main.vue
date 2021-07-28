<template>
<div class="container">
  <router-view>
  </router-view>
  <!-- <div class="bottom-bar">
    <router-link :to="{name:'Home'}">홈</router-link>
    <router-link :to="{name:'Mission'}">미션</router-link>
    <router-link :to="{name:'Jubging'}">줍깅</router-link>
    <router-link :to="{name:'Ranking'}">랭킹</router-link>
    <router-link :to="{name:'My'}">마이</router-link>
  </div> -->
  
  <div class="bottom-bar">
    <div class="nav">
      <div class="nav-slot bg-white round-top-left">
        <a href="#" class="nav-link active">
        <font-awesome-icon icon="home"/>
        </a>
      </div>
      <div class="nav-slot bg-white">
        <a href="#" class="nav-link">
        <font-awesome-icon icon="medal"/>
        </a>
      </div>
      <div class="nav-slot curve">
        <a href="#" role="button" class="floating-button">
        <font-awesome-icon icon="shoe-prints"/>
        </a>
      </div>
      <div class="nav-slot bg-white">
        <a href="#" class="nav-link">
        <font-awesome-icon icon="trophy"/>
        </a>
      </div>
      <div class="nav-slot bg-white round-top-right">
        <a href="#" class="nav-link">
        <font-awesome-icon icon="user"/>
        </a>
      </div>
    </div>
  </div>

</div>
</template>

<script>
export default {
  name:'Main',
  data(){
    return {
    }
  },
}
</script>

<style>

.container{
  display: flex;
  flex-direction: column;
  min-height: 781px;
}

.bottom-bar{
  display: flex;
  justify-content: space-around;
  margin-top: auto;
  height: 50px;
  align-items: center;
}

</style>

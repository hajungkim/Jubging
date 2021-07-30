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
  
  <!-- <div class="bottom-nav2">
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
  </div> -->

  <div class="bottom-nav1">
    <router-link to="#" class="item active" @click.native="onClick(1, $event)">
      <font-awesome-icon icon="home" class="icon"/>
    </router-link>

    <router-link to="#" class="item" @click.native="onClick(2, $event)">
      <font-awesome-icon icon="medal" class="icon"/>
    </router-link>
    
    <router-link :to="{name:'Jubging'}" class="item" @click.native="onClick(3, $event)">
      <font-awesome-icon icon="shoe-prints" class="icon"/>
    </router-link>
    
    <router-link :to="{name:'Ranking'}" class="item" @click.native="onClick(4, $event)">
      <font-awesome-icon icon="trophy" class="icon"/>
    </router-link>

    <router-link :to="{name:'My'}" class="item" @click.native="onClick(5, $event)">
      <font-awesome-icon icon="user" class="icon"/>
    </router-link>
  </div>

  <!-- <div class="bottom-nav3">
    <router-link to="#" class="item" @click.native="onClick(1, $event)">
      <font-awesome-icon icon="home" />
      <span>홈</span>
    </router-link>

    <router-link to="#" class="item" @click.native="onClick(2, $event)">
      <font-awesome-icon icon="medal" />
      <span>미션</span>
    </router-link>
    
    <router-link to="#" class="item" @click.native="onClick(3, $event)">
      <font-awesome-icon icon="shoe-prints" />
      <span>줍깅</span>
    </router-link>
    
    <router-link to="#" class="item" @click.native="onClick(4, $event)">
      <font-awesome-icon icon="trophy" />
      <span>랭킹</span>
    </router-link>

    <router-link to="#" class="item" @click.native="onClick(5, $event)">
      <font-awesome-icon icon="user" />
      <span>마이</span>
    </router-link>
  </div> -->

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
    onClick(link, event){
      this.$store.dispatch('isCurrent', link)
      document.querySelector('.active').classList.remove('active')
      event.currentTarget.classList.add('active')
    },
  },
  computed:{
  }
}
</script>

<style lang="scss" scoped>
.container{
  display: flex;
  flex-direction: column;
  min-height: 781px;
}

</style>

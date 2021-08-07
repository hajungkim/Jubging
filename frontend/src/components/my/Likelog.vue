<template>
  <div>
    <ul style="padding:0px; margin-top:0px;">
      <li class="like_container"
        v-for="(log,idx) in likelogs"
        :key="idx"
      >
        <img class="like_profile" :src="log.profilePath">
        <div>
          <div><span style="font-weight:bold;">'{{log.nickname}}' </span> 님의 게시글을 좋아요 했습니다.</div>
          <span class="like_date">{{log.createdDate.slice(0,10)}}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Likelog',
  data(){
    return{
      likelogs: [],
    }
  },
  computed:{
    loginUser(){
      return this.$store.state.userId
    }
  },
  created(){
    this.getLikelogs()
  },
  methods:{
    getLikelogs(){
      const URL = `http://localhost:8080/likelog/${this.loginUser}`
      const params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.likelogs = res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '@/components/my/likelog.scss'
</style>
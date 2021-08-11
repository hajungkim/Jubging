<template>
  <div>
    <ul style="padding:0px; margin-top:0px;">
      <li class="like_container"
        v-for="(log,idx) in likelogs"
        :key="idx"
        @click="moveDetail(log)"
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
import { mapState } from 'vuex'
export default {
  name: 'Likelog',
  data(){
    return{
      likelogs: [],
      BASEURL: 'http://localhost:8080',
    }
  },
  computed:{
		...mapState([
			'userId'
		]),
  },
  created(){
    this.getLikelogs()
  },
  methods:{
    getLikelogs(){
      const URL = `${this.BASEURL}/likelog/${this.userId}`
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
    },
    moveDetail(article){
      let URL = `${this.BASEURL}/article/detail/${article.articleId}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.$store.state.selectArticle = res.data.data
          this.$store.state.backPage = 5
          this.$router.push({name:'Detail'})   
        })
        .catch((e) => {
          console.error(e);
        })
    },
  }
}
</script>

<style lang="scss" scoped>
@import '@/components/my/Likelog.scss'
</style>
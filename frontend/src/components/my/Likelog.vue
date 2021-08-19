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
          <span class="like_date">{{log.date}}</span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { HTTP } from '@/util/http-common'
import { mapState } from 'vuex'

export default {
  name: 'Likelog',
  data(){
    return{
      likelogs: [],
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
      HTTP.get(`likelog/${this.userId}`)
        .then((res) => {
          this.likelogs = res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
    },
    moveDetail(article){
      HTTP.get(`article/detail/${article.articleId}`)
        .then((res) => {
          this.$store.state.selectArticle = res.data.data
          this.$store.state.backPage = 5
          this.$router.push({name:'Detail', params: { article_id: res.data.data.articleId }})   
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
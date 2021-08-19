<template>
  <div style="height:781px; overflow: auto;">
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="moveHome"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
    </div>
    <div class="search_main">
      <form @submit="onSubmit" class="search_input_box">
        <input
          v-model="keyword"
          @input="onInput"
          type="text"
          class="input_style"
          placeholder="유저 또는 해시태그를 입력해 주세요"
        >
        <button class="input_button">
          <font-awesome-icon icon="search" style="transform:scale(1.4);"/>
        </button>
      </form>
      <!-- 유저검색결과 -->
      <section class="search_user_list" v-if="isShowAuto&&isSubmit">
        <div class="search_user_list_tlt">
          유저 검색 결과
          <span class="search_user_list_text" v-if="userflag">'{{keyword}}'&nbsp;{{users.length}}건</span>
          <span class="search_user_list_text" v-else>'{{keyword}}'&nbsp;0건</span>
        </div>
        <ul class="search_users">
          <li
            v-for="(user,idx) in users"
            :key="idx"
            :data-idx=idx
          >
            <div :data-idx=idx class="search_user" @click="moveUser(user)">
              <img :data-idx=idx :src="user.profilePath" class="search_user_img">
              <p :data-idx=idx class="search_user_name">{{user.nickname}}</p>
            </div>
          </li>
        </ul>
      </section>
      <!-- 해시태그 검색결과 -->
      <section class="search_article_list" v-if="isShowAuto && isSubmit">
        <div class="search_article_list_tlt">
          해시태그 검색 결과
          <span class="search_article_list_text">'{{keyword}}' {{articles.length}}건</span>
        </div>
        <ul class="search_articles">
          <li
            v-for="(article,idx) in articles"
            :key="idx"
          > <!--:src="article.photosPath"-->
            <div @click="onClickArticle(article)" :data-idx=idx class="search_article">
              <img class="search_article img" :src="article.photosPath" :data-idx="article.articleId"> <!-- 이미지 경로 #으로? -->
              <div class="search_article_info" :data-idx="article.articleId">
                <div style="display:flex; align-items:center; justify-content: center;">
                  <img :src="article.profilePath" style="width:50px; height:50px; border-radius:50%; margin-left:-15px;">
                  <div data-idx="article.articleId" class="search_article_usernickname" style="margin-bottom: 3px;">{{article.nickname}}</div>
                </div>
                <span class="search_article_hashtags" v-for="(hash,idxx) in article.hashtags" :key="idxx">{{hash}}</span>
                <div style="margin-top:20px;">
                  <font-awesome-icon :icon="['far','heart']"/><span style="margin-left:5px; margin-right:10px;">{{article.likeCnt}}</span>
                  <font-awesome-icon :icon="['far','comment-dots']" style="margin-left:10px;"/><span style="margin-left:5px;">{{article.commentCnt}}</span>
                </div>
              </div>
            </div>
          </li>
        </ul>
      </section>
    </div>
  </div>
</template>

<script>
import { mapState } from 'vuex'
import { HTTP } from '@/util/http-common'

export default {
  name: "Search",
  data: () => {
    return {
      keyword: "",
      isShowAuto: false,
      isSubmit: false,
      isLatest: false,
      latestList: [],
      users: [],
      articles: [],
      userflag: false,
    }
  },
  craeted(){
    this.$store.state.searchflag = false;
  },
  computed: {
    ...mapState([
			'userId',
		]),
  },
  methods: {
    moveHome(){
      this.$router.push({name:"Home"})
    },
    search(){
      HTTP.get(`user/search/${this.keyword}`)
        .then((res) => {
            this.users = res.data.data
            if(this.users !== null){
              this.userflag=true
            }
            else this.userflag=false        
        })
        .catch((e) => {
          console.error(e);
        })
      this.isSubmit = true;
      this.isLatest = false;
      document.querySelector('.input_style').blur();
    },
    articleSearch(){
      HTTP.get(`hashtag/articlelist/${this.keyword}`)
        .then((res) => {
          if(res.data.data === null) {
            this.articles = [];
          }
          else
            {
              this.articles = res.data.data;
              for(let i = 0; i<res.data.data.length; i++) {
                if (this.articles[i].photosPath.includes('#')){
                  this.articles[i].photosPath = this.articles[i].photosPath.split('#')[0]
                }
                // 해시태그생성
                let splitwords = this.articles[i].content.split(' ');
                let hashwords = [];
                splitwords.forEach(e => {
                  if(e[0] == '#'){
                    hashwords.push(e);
                  }
                });
                this.articles[i].hashtags = hashwords;
              }
            }
        })
        .catch((e) => {
          console.error(e);
        }
      )
    },
    onDeleteItem(item){
      const key = item.key;
      localStorage.removeItem(key);
      const idx = this.latestList.indexOf(key)
      this.latestList.splice(idx,1);
    },
    onInput(e){
      this.keyword = e.target.value;
      this.isSubmit = false;
      if (this.keyword.length ===0 ) {
        this.isShowAuto=false;
      }
      else this.isShowAuto = true;
    },
    onSubmit(e){
      e.preventDefault();
      this.search(); 
      this.articleSearch();
    },
    onClickArticle(article){
      this.$store.state.selectArticle = article;
      this.$store.state.backPage = 2;
      this.$router.push({name:'Detail', params: { article_id: article.articleId }})
    },
    moveUser(user){
      if (user.userId === parseInt(this.userId)){
        this.$router.push({name:'My'})
      }
      else{
        this.$store.state.currentUser = user.userId;
        localStorage.setItem('currentUser', user.userId)
        this.$store.state.backPage = 2;
        this.$store.state.searchflag = true;
        this.$router.push({name:'Userprofile', params: { user_id: user.userId }})
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/views/home/Search.scss";
</style>
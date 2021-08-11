<template>
  <div style="height:781px; overflow: auto;">
    <div class="top">
      <router-link :to="{name:'Home'}" class="default-link">
        <font-awesome-icon icon="angle-left" class="fa-2x back_icon"/>
      </router-link>
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
      <!-- 최근 검색어 -->
      <section class="search_latest" v-if="isShowAuto && isSubmit">
        <div class="search_latest_tlt">
          <span>최근 검색어</span>
        </div>
        <ul class="search_latest_list">
          <li
            class="search_latest_item"
            v-for="(item,idx) in keywordLatest"
            :key="idx"
          >
            <button class="lastkeyword"><span @click="onClickLatest" style="margin-left:5px;">{{item.value}}</span>&nbsp; <span @click="onDeleteItem(item)" style="margin-right:5px;">X</span></button>
          </li>
        </ul>
      </section>
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
      <!-- 게시글 검색결과 -->
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
              <img class="articleImg" :src="photos" :data-idx="article.articleId"> <!-- 이미지 경로 #으로? -->
              <div class="search_article_info" :data-idx="article.articleId">
                <div data-idx="article.articleId" class="search_article_usernickname" >'{{article.nickname}}'</div>
                <span class="search_article_hashtags" v-for="(hash,idxx) in article.hashtags" :key="idxx">{{hash}}</span>
                <div>
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
import axios from 'axios'
import { mapState } from 'vuex'
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
      photos: [],
      BASEURL: 'http://localhost:8080',
    }
  },
  craeted(){
    this.$store.state.searchflag = false;
  },
  computed: {
    keywordLatest(){
      let sortedList = this.latestList.slice(0,);
      sortedList.sort((a,b) => b*1-a*1)
      for(let i=0; i<sortedList.length; i++){
        sortedList[i] = {key:sortedList[i],value:localStorage.getItem(sortedList[i])}
      }
      return sortedList;
    },
    ...mapState([
			'userId',
		]),
  },
  methods: {
    search(){
      const key = String(Date.now());
      if (this.isLatest === false){
        if(localStorage.length < 8){
          localStorage.setItem(key,this.keyword)
          this.latestList.unshift(key);
        }
        else{
          const delKey = this.latestList.pop()
          localStorage.removeItem(delKey);
          localStorage.setItem(key,this.keyword)
          this.latestList.unshift(key);
        }
      }
      let URL = `${this.BASEURL}/user/search/${this.keyword}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
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
      let param = {
        method: 'get',
        url: `${this.BASEURL}/hashtag/articlelist/${this.keyword}`,
      }
      axios(param)
        .then((res) => {
          if(res.data.data === null) {
            this.articles = [];
          }
          else
            {
              this.articles = res.data.data;
              for(let i = 0; i<res.data.data.length; i++) {
                if (this.articles[i].photosPath.includes('#')){
                  this.articles[i].photosPath = res.data.data.photosPath.split('#')[0]
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
    onClickLatest(e){
      this.isLatest = true;
      this.keyword = e.target.innerText;
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
        this.$router.push({name:'Userprofile', params: { user_nickname: user.nickname }})
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/views/home/Search.scss";
</style>
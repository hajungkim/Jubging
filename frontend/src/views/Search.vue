<template>
  <div style="height:781px;">
    <div class="top">
      <router-link :to="{name:'Home'}" class="default-link">
        <font-awesome-icon icon="angle-left" class="fa-2x back_icon"/>
      </router-link>
      <img class="logo" src="@/assets/textlogo.png" alt="logo" width="100px;">
    </div>
    <div class="search_main">
      <form @submit="onSubmit" class="search_input_box">
        <input
          v-model="keyword"
          @input="onInput"
          type="text"
          class="input_style"
          placeholder="검색어를 입력해주세요"
        >
        <button class="input_button">
          <font-awesome-icon icon="search" style="transform:scale(1.4);"/>
        </button>
      </form>
      <!-- 자동완성 -->
      <!-- <div class="search_full_bar"></div>
      <ul v-if="isShowAuto" class="keyword_list">
        <li
          class="keyword_item"
          v-for="(item,idx) in autocomplete"
          :key="idx"
          @click="onClickAuto"
          :data-keyword="item"  
        >
           <font-awesome-icon class="auto_search_icon" size="sm" :data-keyword="item" icon="search"/>
           <span class="auto_search_text" :data-keyword="item">{{item}}</span>
        </li>
      </ul> -->
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
            <button @click="onClickLatest">{{item.value}}</button>
            <button :data-key="item.key" @click="onDeleteItem">X</button>
          </li>
        </ul>
      </section>
      <!-- 유저검색결과 -->
      <section class="search_user_list" v-if="isShowAuto&&isSubmit">
        <div class="search_user_list_tlt">
          유저 검색 결과
          <span class="search_user_list_text" v-if="userflag">'{{keyword}}' {{users.length}}건</span>
        </div>
        <ul class="search_users">
          <li
            @click="onClickUser"
            v-for="(user,idx) in users"
            :key="idx"
            :data-idx=idx
          >
            <div :data-idx=idx class="search_user">
              <img :data-idx=idx src="@/assets/sample.png" class="search_user_img">
              <p :data-idx=idx class="search_user_name">{{user.nickname}}</p>
            </div>
          </li>
        </ul>
      </section>
      <!-- 게시글 검색결과 -->
      <section class="search_article_list" v-if="isShowAuto && isSubmit">
        <div class="search_article_list_tlt">
          게시글 검색 결과
          <span class="search_article_list_text">'{{keyword}}' {{articles.length}}건</span>
        </div>
        <ul class="search_articles">
          <li
            v-for="(article,idx) in articles"
            :key="idx"
          >
            <div @click="onClickArticle" :data-idx=idx class="search_article">
              <img class="articleImg" src="@/assets/sample.png" :data-idx="article.articleId">
              <div class="search_article_info" :data-idx="article.articleId">
                <p :data-idx="article.articleId"><span class="search_article_usernickname" >'김줍깅' </span>님 게시글</p>
                <p>
                  <span class="search_article_hashtags" v-for="(hash,idxx) in article.hashtags" :key="idxx">{{hash}}</span>
                </p>
                <font-awesome-icon :icon="['far','heart']"/><span style="margin-left:5px;">{{article.likeCnt}}</span>
                <font-awesome-icon :icon="['far','comment-dots']" style="margin-left:5px;"/><span style="margin-left:5px;">{{article.commentCnt}}</span>
              </div>
            </div>
          </li>
        </ul>
      </section>
    </div>
  </div>
</template>

<script>
// import * as Hangul from 'hangul-js';
import axios from 'axios'
export default {
  name:"Search",
  data: ()=>{
    return {
      keyword:"",
      isShowAuto:false,
      isSubmit:false,
      latestList:[],
      users:[],
      articles:[],
      userflag:false,
    }
  },
  computed:{
    keywordLatest(){
      let sortedList = this.latestList.slice(0,);
      sortedList.sort((a,b) => b*1-a*1)
      for(let i=0; i<sortedList.length; i++){
        sortedList[i]={key:sortedList[i],value:localStorage.getItem(sortedList[i])}
      }
      return sortedList;
    },
  },
  methods:{
    search(){
      const key=String(Date.now());
      if(localStorage.length<5){
        localStorage.setItem(key,this.keyword)
        this.latestList.unshift(key);
      }
      else{
        let sortedList=this.latestList;
        sortedList.sort((a,b) => b*1-a*1); //??
        const delKey=sortedList[sortedList.length-1];
        localStorage.removeItem(delKey);
        const idx=this.latestList.indexOf(delKey);
        this.latestList.splice(idx,1,key);
        localStorage.setItem(key,this.keyword)
      }
      // const token=localStorage.getItem('jwt')
      let URL = `http://localhost:8080/user/search/${this.keyword}`
      let params={
        method:'get',
        url:URL,
        headers:{
          // Authorization:`JWT ${token}`
        },
      }
      axios(params)
        .then((res)=>{
            this.users=res.data.data
            if(this.users.length>=1)this.userflag=true
            else this.userflag=false
            
        })
        .catch((e)=>{
          console.error(e);
        })
      this.isSubmit=true;
      document.querySelector('.input_style').blur();
    },
    articleSearch(){
      let param={
        method:'get',
        url:`http://localhost:8080/hashtag/articlelist/${this.keyword}`,
        headers:{
          // Authorization:`JWT ${token}`
        },
      }
      axios(param)
        .then((res)=>{
          if(res.data.data===null) this.articles=[];
          else
            {
              this.articles=res.data.data;
              for(let i=0;i<res.data.data.length;i++){
                let splitwords = this.articles[i].content.split(' ');
                let hashwords=[];
                splitwords.forEach(e => {
                  if(e[0]=='#'){
                    hashwords.push(e);
                  }
                });
                this.articles[i].hashtags=hashwords;
              }
              console.log(this.articles)
            }
        })
        .catch((e)=>{
          console.error(e);
        }
      )
    },
    onDeleteItem(e){
      const key=e.target.dataset.key;
      localStorage.removeItem(key);
      const idx=this.latestList.indexOf(key);
      this.latestList.splice(idx,1);
    },
    onInput(e){
      this.keyword=e.target.value;
      this.isSubmit=false;
      if(this.keyword.length===0) this.isShowAuto=false;
      else this.isShowAuto=true;
    },
    onSubmit(e){
      e.preventDefault();
      this.search(); // 한 키워드로 유저 부분을찾기
      this.articleSearch(); // 게시글 찾기
    },
    onClickAuto(e){
      this.keyword=e.target.dataset.keyword;
      this.search();
      this.articleSearch();
      this.isShowAuto=false;
    },
    onClickUser(e){
      const user=this.users[e.target.dataset.idx];
      const userId=user.userId;
      console.log(userId)
      // this.$router.push({name:''})
    },
    onClickLatest(e){
      this.keyword=e.target.innerText;
      this.search();
      this.articleSearch();
    },
    onClickArticle(e){
      if(e.target.dataset.id){
        // e.target.dataset.id = article_id 니까 디테일 라우터로 보내주면됨
      }
    }
  }
}
</script>

<style scoped>
.back_icon{
  margin-left:15px;
}
.logo{
  margin-right:155px;
  transform: scale(1.5);
}
.search_main{
  display:flex;
  flex-direction: column;
  align-items: center;
  margin:20px 16px 0px 16px;
}
.input_style{
  width:300px;
  height: 30px;
}
.input_button{
  background: white;
  border:0px;
  margin-left:-30px;
}
.search_latest{
  position:relative;
  width:382px;
  z-index:1;
  margin-top:20px;
}
.search_latest_tlt{
  text-align: left;
}
.search_latest_list{
  list-style: none;
  display: flex;
  justify-content: left;
  flex-wrap: wrap;
  padding:0px;
}
.search_latest_item{
  border: 1px solid;
  margin-right:3px;
  margin-bottom: 5px;
  font-size: 15px;
}
.search_user_list{
  display:flex;
  flex-direction: column;
  margin:10px 0px 0px 0px;
}
.search_user_list_tlt{
  width:380px;
}
.search_users::-webkit-scrollbar{
  display: none;
}
.search_users{
  display:flex;
  flex-wrap:wrap;
  list-style: none;
  overflow-y: scroll;
  padding:0px;
}
.search_user{
  display:flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-right:10px;
}
.search_user_img{
  width:51px;
  height: 51px;
  border-radius: 50%;
}
.search_user_name{
  margin:0;
  text-align:center;
}
.search_article_list{
  display:flex;
  flex-direction: column;
  height: 500px;
}
.search_article_list_tlt{
  width: 380px;
}
.search_articles{
  list-style: none;
  overflow-y:scroll;
  padding:0px;
  -ms-overflow-style:none;
}
.search_articles::-webkit-scrollbar{
  display: none;
}
.search_article{
  display: flex;
  margin:0px 10px 10px 0px;
  align-items:center;
  border: 1px solid lightgray;
  /* box-shadow: 0 0 2px #99979725; */
  border-radius: 10px;
}
.search_article img{
  width:50%;
  margin:0px 10px 0px 10px;
}
.search_article_info{
  text-align:left;
  display:inline-block;
}
.search_article_usernickname{
  font-weight: bold;
}
.search_article_hashtags{
  margin-right:5px;
}
</style>
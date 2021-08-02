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
      <ul v-if="true">
        <li
          class="keyword_item"
          v-for="(item,idx) in autocomplete"
          :key="idx"
          @click="onClickAuto"
          :data-keyword="item"  
        >
           <font-awesome-icon :data-keyword="item" icon="search"/>
           <span :data-keyword="item">{{item}}</span>
        </li>
      </ul> -->
      <!-- 최근 검색어 -->
      <section class="search_latest" v-if="!isShowAuto && !isSubmit">
        <div class="search_latest_tlt">
          <span>최근 검색어</span>
        </div>
        <ul class="search_latest_list">
          <li
            class="search_latest_item"
            v-for="(item,idx) in keywordLatest"
            :key="idx"
          >
            <button>{{item.value}}</button>
            <button :data-key="item.key" @click="onDeleteItem">X</button>
          </li>
        </ul>
      </section>
      <!-- 유저검색결과 -->
      <section>
        <div>
          유저 검색 결과
          <span>몇 건</span>
        </div>
        <ul>
          <li
            @click="onClickUser"
            v-for="(user,idx) in users"
            :key="idx"
            :data-idx=idx
          >
            <div :data-idx=idx>
              <img :data-idx=idx :src="user.userImg">
              <p :data-idx=idx>{{user.userName}}</p>
            </div>
          </li>
        </ul>
      </section>
      <!-- 게시글 검색결과 -->
      <section>
        <div>
          게시글 검색 결과
          <span>몇 건</span>
        </div>
        <ul>
          <li
            v-for="(article,idx) in articles"
            :key="idx"
          >
            <div>
              이미지 작성자 해시태그 
            </div>
          </li>
        </ul>
      </section>
    </div>
  </div>
</template>

<script>
// import * as Hangul from 'hangul-js';
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
    // autocomplete(){
      // const search = this.keyword;
      // const search1 = Hangul.disassemble(search).join("");
      // console.log('!!',search1)
      // let arr=[];
    //   this.keywordList
    //   .filter(function (item) {
    //     return item.name.includes(search)||item.disassemble.includes(search1)
    //   })
    //   .forEach(function (item){
    //     arr.push(item.name);
    //   })
    //   return arr.slice(0,10);
      // return 0;
    // },
  },
  methods:{
    search(){
      const key=String(Date.now());
      if(localStorage.length<4){
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
      // const URL = 'http://127.0.0.1:8000/search_auto/'
      // const params={
      //   method:'get',
      //   url:URL,
      //   headers:{
      //     Authorization:`JWT ${token}`
      //   },
      // }
      // axios(params)
      //   .then((res)=>{
      //       console.log(res)
      //   })
      //   .catch((e)=>{
      //     console.error(err);
      //   })
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
      this.search();
    },
    onClickAuto(e){
      this.keyword=e.target.dataset.keyword;
      this.search();
      this.isShowAuto=false;
    },
    onClickUser(e){
      const user=this.users[e.target.dataset.idx];
      const userId=user.userId;
      console.log(userId)
      // this.$router.push({name:''})
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
</style>
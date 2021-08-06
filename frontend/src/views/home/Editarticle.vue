<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="onClick"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
    </div>
    <div class="content">
      <span class="text">게시글 수정하기</span>
      <textarea v-model="content" type="text" style="margin-top:15px;"></textarea>
      <!-- <img src="@/assets/logo/iconlogo.png" class="logo_img"> -->
      <button class="edit_button" @click="onEdit">수정 완료</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  data(){
    return{
      content: '',
    }
  },
  created(){
    this.content = this.$store.state.selectArticle.content
  },
  methods:{
    onClick(){
      this.$router.push({name:'Detail'})
    },
    onEdit(){
      let URL = `http://localhost:8080/article/`
      let data = {
        articleId: this.$store.state.selectArticle.articleId,
        content: this.content
      }
      let params = {
        method: 'put',
        url: URL,
        data: data,
      }
      axios(params)
        .then(() => {
          this.$router.push({name:'Detail'})
        })
        .catch((e) => {
          console.error(e);
        })
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/assets/css/common.scss";
.top{
  display: flex;
  align-items: center;
  height: 50px;
}
.back_icon{
margin-left:15px;
cursor: pointer;
}
.logo{
margin-left: 130px;
transform: scale(1.5);
}
.content{
  display: flex;
  flex-direction: column;
  align-items: center;
}
.text{
  text-align: center;
  margin-top:50px;
  font-weight: bold;
  font-size:20px;
}
textarea{
  width: 360px;
  height: 400px;
  border-radius: 15px;
  resize:none;
  padding:10px;
}
textarea:focus {outline:none;}
.edit_button{
  color: #00bfa5;
  background-color: white;
  width : 360px;
  height: 40px;
  margin-top:150px;
  border-radius: 20px;
  border-color:#00bfa5;
  border:2px solid;
  font-weight: bold;
}
.logo_img{
  height: 120px;
  width: 120px;
  margin-top:10px;
  opacity: 0.6;
}
</style>
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
@import "@/views/home/Editarticle.scss";
</style>
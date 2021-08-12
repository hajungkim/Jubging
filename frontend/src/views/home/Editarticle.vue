<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="onClick"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
    </div>
    <div class="content">
      <span class="text">게시글 수정하기</span>
      <textarea v-model="content" type="text"></textarea>
      <!-- <img src="@/assets/logo/iconlogo.png" class="logo_img"> -->
      <button class="btn" @click="onEdit">수정 완료</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'
export default {
  name: 'Editarticle',
  data(){
    return{
      content: '',
      BASEURL: 'http://localhost:8080',
    }
  },
  computed:{
    ...mapState([
      'selectArticle',
		]),
  },
  created(){
    let URL = `${this.BASEURL}/article/detail/${this.$route.params.article_id}`
    let params = {
      method: 'get',
      url: URL,
    }
    axios(params)
      .then((res) => {
        this.content=res.data.data.content
      })
      .catch((e) => {
        console.error(e);
      })
    this.content = this.selectArticle.content
  },
  methods:{
    onClick(){
      this.$router.push({name:'Detail', params: { article_id: this.$route.params.article_id }})
    },
    onEdit(){
      let URL = `${this.BASEURL}/article/`
      let data = {
        articleId: this.$route.params.article_id,
        content: this.content
      }
      let params = {
        method: 'put',
        url: URL,
        data: data,
      }
      axios(params)
        .then(() => {
          this.$router.push({name:'Detail', params: { article_id: this.$route.params.article_id }})
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
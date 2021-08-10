<template>
  <div>
    <div id="header">
      <div class="to-center">
        <img class="logo" src="@/assets/logo/textlogo.png" alt="줍깅 로고">
      </div>
    </div>
    <div id="body">
      <div class="item">
        <div><h3>줍깅 후기 작성하기</h3></div>
        <textarea 
          name="text" 
          placeholder="본문에 #을 이용하여 태그를 사용해보세요"
          id="ta" 
          cols="33" 
          rows="14"
        ></textarea>
      </div>
      <input type="file">
      <div class="item">
        <div id="photo">
          <div class="item-grid">+</div>
        </div>
      </div>
      <button class="btn-next">올리기 ></button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080/'
export default {

}
</script>

<style lang="scss" scoped>
@import "@/views/jubging/NewArticle.scss";

</style>
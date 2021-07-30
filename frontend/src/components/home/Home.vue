<template>
  <div>
    <div class="main_top">
      <img src="@/assets/textlogo.png" alt="logo" class="text_logo">
      <Search/>
      <div class="follow_div">
        <label class="switch">
          <input type="checkbox" @click="followToggle()">
          <span class="slider round"></span>
        </label>
      </div>
    </div>
    <div class="photo_list">
      <div class="photo-grid">
        <div class="today-jubging"></div>
        <PhotoList
          v-for="article in photos"
          :key="article.id"
          :article="article"
          v-show="false"
        />
      </div>
      <FollowList
        v-for="article in photos"
        :key="article.id"
        :article="article"
        v-show="true"
      />
    </div>
  </div>
</template>

<script>
import PhotoList from '@/components/home/PhotoList.vue'
import Search from '@/components/home/Search.vue'
import FollowList from '@/components/home/FollowList.vue'

import { mapState } from 'vuex'

export default {
  components:{
    PhotoList,
    Search,
    FollowList,
  },
  data(){
    return {
      toggle:true
    }
  },
  methods:{
    followToggle(){
      console.log(this.toggle)
      this.toggle = !this.toggle
    }
  },
  computed:{
    ...mapState([
      'photos'
    ])
  }
}
</script>

<style scoped>
.main_top{
  display: flex;
  height: 50px;
  align-items: center;
}
.text_logo{
  width:100px;
  margin: 10px 20px 0px 15px;
  transform: scale(1.1);
}
.photo_list{
  overflow: auto;
  height: 660px;
}
/* 팔로우 토글 */
.follow_div{
  display: flex;
  margin-left: auto;
  margin-right:10px;
  justify-content: center;
  align-items: center;
}
.switch{
  position:relative;
  display: inline-block;
  width:40px;
  height:20px;
  margin: 5px 5px 0px 0px;
}
.switch input{
  opacity: 0;
  width: 0;
  height: 0;
}
.slider{
  position: absolute;
  cursor: pointer;
  top: 0;
  left:0;
  right:0;
  bottom:0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}
.slider:before{
  position: absolute;
  content:"";
  height: 15px;
  width: 15px;
  left:4px;
  bottom:2px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}
input:checked + .slider{
  background-color: #2196F3;
}
input:focus + .slider{
  box-shadow: 0 0 1px #2196F3;
}
input:checked +.slider:before{
  -webkit-transform: translateX(17px);
  -ms-transform: translateX(17px);
  transform: translateX(17px);
}
.slider.round{
  border-radius: 34px;
}
.slider.round:before{
  border-radius: 50%;
}
.today-jubging {
  width: 185px;
  height: 90px;
}
.photo-grid {
  column-count: 2;
  column-gap: 0px;
  margin-left: 14px;
}
</style>
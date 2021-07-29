<template>
  <div>
    <div class="main_top">
      <img src="@/assets/textlogo.png" alt="logo" class="text_logo">
      <Search/>
      <FollowList/>
    </div>
    <div class="photo_list">
      <PhotoList/>
    </div>
  </div>
</template>

<script>
import PhotoList from '@/components/home/PhotoList.vue'
import Search from '@/components/home/Search.vue'
import FollowList from '@/components/home/FollowList.vue'
export default {
  components:{
    PhotoList,
    Search,
    FollowList,
  },
  data(){
  },
  methods:{
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
</style>
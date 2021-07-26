<template>
  <div>
  <div class="main_top">
    <img src="@/assets/jubging.png" alt="logo" width="100px;" style="padding:10px">
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

<style>
.main_top{
  display: flex;
  height: 50px;
}
.today_jub{
  position: relative;
  height: 20px;
  margin-left:15px;
}

.photo_list{
  overflow: auto;
  height: 660px;
}
</style>
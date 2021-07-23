<template>
  <div>
    <div class="top">
      <img src="@/assets/jubging.png" alt="logo" width="100px;" style="padding:10px">
      <Search/> | 
      <FollowList/>
    </div>
    <div class="grid_photo">
      <PhotoList
        v-for="feed in imgUrl"
        :key="feed.title"
        img_url='../../assets/sample.png'
      />
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
    return{
      imgUrl:[
        {
          title:'1',
          url:"../../assets/sample.png",
        },
        {
          title:'2',
          url:"../../assets/sample.png",
        },
        {
          title:'3',
          url:"../../assets/sample.png",
        },
        // {
        //   title:'4',
        //   url:"../../assets/sample.png",
        // },
      ]
    }
  },
  methods:{
  }
}
</script>

<style>
.top{
  display: flex;
}
.grid_photo{
  height: 600px;
  overflow:auto;
}
</style>
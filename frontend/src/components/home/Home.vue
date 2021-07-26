<template>
  <div>
  <div class="top">
    <img src="@/assets/jubging.png" alt="logo" width="100px;" style="padding:10px">
    <Search/>
    <FollowList/>
  </div>
  <span class="today_jub">오늘의 줍깅수 :</span>
  <div class="grid_photo">
      <PhotoList
        v-for="img in imgs"
        :key="img.title"
        :img_url="img"
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
      imgs:[
        {
          title:'1',
          url:"@/assets/sample.png",
        },
        {
          title:'2',
          url:"@/assets/sample2.png",
        },
        {
          title:'3',
          url:"@/assets/sample3.png",
        },
        {
          title:'4',
          url:"@/assets/sample4.png",
        },
        {
          title:'5',
          url:"@/assets/sample5.png",
        },
        {
          title:'6',
          url:"@/assets/sample6.png",
        },
        {
          title:'7',
          url:"@/assets/sample7.png",
        },
        {
          title:'8',
          url:"@/assets/sample8.png",
        },
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
  height: 50px;
}
.today_jub{
  position: relative;
  height: 20px;
  margin-left:15px;
}

.grid_photo{
  overflow:auto;
  height: 650px;
  display: grid;
  grid-template-columns: 1fr 1fr;
  /* grid-auto-flow: row dense;
  grid-auto-rows: auto; */
  grid-auto-columns: 100px;
  position: absolute;
}
.grid_photo :nth-child(odd){
  margin-top:25px;
}
</style>
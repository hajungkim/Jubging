<template>
  <div class="photo-grid">
    <div class="today-jubging"></div>
    <router-link :to="{name:'Detail'}" >
      <img v-for="photo in photos" :key="photo.id" :src="photo.url" class="photo-img">
    </router-link>
  </div>
</template>

<script>
export default {
  name:'PhotoList',
  data(){
    return{
      photos:[
        {
          title:'0',
          url:'http://placehold.it/185x185',
        },
        {
          title:'1',
          url:'http://placehold.it/185x185',
        },
        {
          title:'2',
          url:'http://placehold.it/185x185',
        },
        {
          title:'3',
          url:'http://placehold.it/185x185',
        },
        {
          title:'4',
          url:'http://placehold.it/185x185',
        },
        // {
        //   title:'5',
        //   url:'http://placehold.it/185x185',
        // },
        // {
        //   title:'6',
        //   url:"@/assets/sample6.png",
        // },
        // {
        //   title:'7',
        //   url:"@/assets/sample7.png",
        // },
        // {
        //   title:'8',
        //   url:"@/assets/sample8.png",
        // },
        // {
        //   title:'9',
        //   url:"@/assets/sample8.png",
        // },
      ]
    }
  },
}
</script>

<style>
.today-jubging {
  width: 185px;
  height: 90px;
}

.photo-grid {
  column-count: 2;
  column-gap: 0px;
  margin-left: 14px;
}

.photo-img {
  display: inline-block;
  width: 185px;
  height: 185px;
  border-radius: 10%;
  margin-bottom: 14px;
}
</style>
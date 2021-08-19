<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="onClick"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
    </div>
    <div class="imgcontainer">
      <div class="posterTitle"> {{event.subject}} </div>
      <div class="date">{{event.createdDate.slice(0,10)}}</div>
      <img class="posterImg" :src="event.content">
    </div>
  </div>
</template>

<script>
import { HTTP } from '@/util/http-common'
import { mapState } from 'vuex'
export default {
  name:'Poster',
  data() {
    return {
      events:[]
    }
  },
  computed:{
    ...mapState([
      'event'
		]),
  },
  created(){
    this.getEvents
  },
  methods:{
    onClick(){
      this.$router.push({name:"Events"})
    },
    getEvents(){
      HTTP.get(`event`)
			.then((res) => {
        console.log(res.data.data)
				this.events = res.data.data
			})
			.catch((e) => {
				console.error(e);
			})
    }
  }
}
</script>

<style lang="scss" scoped>
@import "@/views/jubging/EventPoster.scss";
.imgcontainer{
  display: flex;
  flex-direction: column;
  margin-top:25px;
}
.posterTitle{
  margin-left:15px;
  margin-bottom: 5px;
}
.date{
  margin-left: 18px;
  font-size:14px;
  color: silver;
}
.posterImg{
  margin: 20px 0px 0px 16px;
  width:380px;
  height:610px;
}
</style>
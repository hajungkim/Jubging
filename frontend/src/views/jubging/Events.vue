<template>
  <div style="height:720px;">
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="onClick"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
    </div>
    <ul class="event_lists">
      <li class="event_list"  v-for="(event,idx) in events" :key="idx" @click="movePoster(event)">
        {{event.subject}}
        <span class="right">></span>
      </li>
    </ul>
  </div>
</template>

<script>
import { HTTP } from '@/util/http-common'
export default {
name:'Event',
data(){
  return{
    events:[]
  }
},
created(){
  this.getEvent()
},
methods:{
  onClick(){
    this.$router.push({name:'Jubging'})
  },
  getEvent(){
      HTTP.get(`event`)
			.then((res) => {
        console.log(res.data.data)
				this.events = res.data.data
			})
			.catch((e) => {
				console.error(e);
			})
  },
  movePoster(event){
    this.$store.dispatch('isSelectEvent',event)
    this.$router.push({name:"EventPoster", params: { eventId: event.eventId }})
  }
}
}
</script>

<style lang='scss' scoped>
@import "@/views/jubging/Events.scss";
</style>
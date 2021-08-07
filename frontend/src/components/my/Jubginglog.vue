<template>
  <div>
    <ul style="padding:0px; margin-top:0px;">
      <li class="jubging_container"
        v-for="(log,idx) in jubginglogs"
        :key="idx"
      >
        <img class="jubging_profile" src="@/assets/sample.png">
        <div>
          <div style="font-weight:bold;">'{{log.createdDate.slice(0,10)}}'</div>
          <div class="jubging_time">{{log.totalTime}}</div>
          <div class="jubging_distance">{{log.distance}} km</div>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'
export default {
  name: 'Jubginglog',
  data(){
    return{
      jubginglogs: [],
    }
  },
  computed:{
    loginUser(){
      return this.$store.state.userId
    }
  },
  created(){
    this.getJunbginglogs()
  },
  methods:{
    getJunbginglogs(){
      const URL = `http://localhost:8080/jubginglog/${this.loginUser}`
      const params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.jubginglogs = res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
    }
  }
}
</script>

<style scoped>
.jubging_container{
  display: flex;
  margin:0px 10px 10px 10px;
  align-items: center;
}
.jubging_profile{
  width: 200px;
  height: 200px;
  border-radius: 20%;
  overflow: hidden;
  margin:5px 10px 0px 0px;
}
</style>
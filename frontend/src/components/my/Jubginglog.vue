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
import { mapState } from 'vuex'
export default {
  name: 'Jubginglog',
  data(){
    return{
      jubginglogs: [],
    }
  },
  computed:{
		...mapState([
			'userId'
		]),
  },
  created(){
    this.getJunbginglogs()
  },
  methods:{
    getJunbginglogs(){
      const URL = `http://localhost:8080/jubginglog/${this.userId}`
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
    },
  }
}
</script>

<style lang="scss" scoped>
@import '@/components/my/Jubginglog.scss'
</style>
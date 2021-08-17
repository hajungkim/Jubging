<template>
  <div>
    <ul style="padding:0px; margin-top:0px;">
      <li class="jubging_container"
        v-for="(log,idx) in jubginglogs"
        :key="idx"
      >
      <div class="content_box">
        <div class="info">
          <div>
            <div class="time_dist_text">운동 시간</div>
            <div class="time_dist">{{log.totalTime}}</div>
          </div>
          <div>
            <div class="time_dist_text">운동 거리</div>
            <div class="time_dist">{{log.distance}} km</div>
          </div>
        </div>
        <div class="date_address">
          <div>
            <span style="margin-left:35px;">{{log.date}}</span>
          </div>
          <div>
            <span style="margin-right:10px;">{{log.address}}</span>
          </div>
        </div>
      </div>
      </li>
    </ul>
  </div>
</template>

<script>
import { HTTP } from '@/util/http-common'
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
      HTTP.get(`jubginglog/${this.userId}`)
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
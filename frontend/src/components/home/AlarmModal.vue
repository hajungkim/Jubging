<template>
	<div class="modal">
		<div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
				<div class="modal_top">알람창<button @click="$emit('close-modal')">닫기</button></div>
					<div>
						<ul style="padding:0px; margin:3px;">
							<li class="alarm_container"
								v-for="(alarm,idx) in alarmList"
								:key="idx"
								>
							<img class="alarm_profile" :src="alarm.profilePath">
							<div v-if="alarm.category === 'comment'" style="display:flex; align-items:center;">
								<span style="font-weight:bold;">{{alarm.nickname}}</span> 님이 게시글에 댓글을 남겼습니다.
								<span class="alarm_time">{{alarm.time}}</span>
							</div>
							<div v-if="alarm.category === 'like'" style="display:flex; align-items:center;">
								<span style="font-weight:bold;">{{alarm.nickname}}</span> 님이 게시글에 좋아요를 눌렀습니다.
								<span class="alarm_time">{{alarm.time}}</span>
							</div>
							<div v-if="alarm.category === 'follow'" style="display:flex; align-items:center;">
								<span style="font-weight:bold;">{{alarm.nickname}}</span> 님이 회원님을 팔로우 했습니다.
								<span class="alarm_time">{{alarm.time}}</span>
							</div>
							</li>
						</ul>
					</div>
			</div>
	</div>
</template>

<script>
import axios from 'axios'
export default {
	data(){
		return {
			alarmList: [],
		}
	},
	created(){
		this.getAlarm()
	},
	methods:{
		getAlarm(){
			let URL = `http://localhost:8080/alarm/${this.$store.state.userId}`
      let params = {
        method: 'get',
        url: URL,
      }
      axios(params)
        .then((res) => {
          this.alarmList = res.data.data  
          this.alarmList.forEach(element => {          
            if (element.profilePath === null) {
              element.profilePath = require("@/assets/user_default.png")
            }
          });
        })
        .catch((e) => {
          console.error(e);
        })
		}
	}
};
</script>

<style lang="scss">
@import "@/components/home/AlarmModal.scss";
</style>

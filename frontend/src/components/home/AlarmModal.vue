<template>
	<div class="modal">
		<div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
				<div class="modal-header">
        <span>알람창</span>
        <font-awesome-icon class="icon" icon="times" @click="$emit('close-modal')"/>
      </div>
			<div class="modal-content">
        <ul style="padding: 0px; margin: 0px">
					<li class="alarm_container" v-for="(alarm,idx) in alarmList" :key="idx">
						<img class="alarm_profile" :src="alarm.profilePath">
						<div v-if="alarm.category === 'comment'" class="alarm-nickname" @click="onDetail(alarm)">
							<div>
								<span style="font-weight:bold;">{{alarm.nickname}}</span>
								<span class="alarm_time"> {{alarm.time}}</span>
							</div>
							<span>게시글에 댓글을 남겼습니다.</span>
						</div>

						<div v-if="alarm.category === 'like'" class="alarm-nickname" @click="onDetail(alarm)">
							<div>
								<span style="font-weight:bold;">{{alarm.nickname}}</span>
								<span class="alarm_time"> {{alarm.time}}</span>
							</div>
							<span>게시글에 좋아요를 눌렀습니다.</span>
						</div>

						<div v-if="alarm.category === 'follow'" class="alarm-nickname" @click="onUserprofile(alarm)">
							<div>
								<span style="font-weight:bold;">{{alarm.nickname}}</span>
								<span class="alarm_time"> {{alarm.time}}</span>
							</div>
							<span>회원님을 팔로우 했습니다.</span>
						</div>
					</li>
				</ul>
      </div>
      <div class="modal-footer">
      </div>
			</div>
	</div>
</template>

<script>
import { HTTP } from '@/util/http-common'

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
      HTTP.get(`alarm/${this.$store.state.userId}`)
        .then((res) => {
          this.alarmList = res.data.data
        })
        .catch((e) => {
          console.error(e);
        })
		},
		onDetail(alarm){
			this.$router.push({name:'Detail', params: { article_id: alarm.articleId }})   
		},
		onUserprofile(alarm){
			this.$router.push({name:'Userprofile', params: { user_id: alarm.pubId }})
		}
	}
};
</script>

<style lang="scss" scoped>
@import "@/assets/css/common.scss";
@import "@/components/home/AlarmModal.scss";
</style>

<template>
  <div class="modal">
		<div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
				<div class="modal_top">좋아요 누른 사람들<button @click="$emit('close-modal')">닫기</button></div>
					<div>
						<ul style="padding:0px; margin:3px;">
              <!-- {{likePeoples}} -->
							<li class="comment_container" v-for="(likePeople,idx) in likePeoples" :key="idx">
                {{likePeople[0]}}
							<!-- <img class="comment_profile" src="@/assets/sample.png">
							<div style="display:flex; align-items:center;">
									<span style="font-weight:bold;">user_name</span> 님이 댓글을 남겼습니다.
							</div> -->
							</li>
						</ul>
					</div>
			</div>
	</div>
</template>

<script>
export default {
  props:{
    likePeoples: {
      type:Array,
    }
  },
}
</script>

<style>

</style>
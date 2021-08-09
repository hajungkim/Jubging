<template>
	<div class="modal">
		<div class="overlay" @click="$emit('close-modal')"></div>
			<div class="modal-card">
				<div class="modal_top">알람창<button @click="$emit('close-modal')">닫기</button></div>
					<div>
						<ul style="padding:0px; margin:3px;">
							<li class="comment_container">
							<img class="comment_profile" src="@/assets/sample.png">
							<div style="display:flex; align-items:center;">
									<span style="font-weight:bold;">user_name</span> 님이 댓글을 남겼습니다.
							</div>
							</li>
							<li class="comment_container">
							<img class="comment_profile" src="@/assets/sample.png">
							<div style="display:flex; align-items:center;">
									<span style="font-weight:bold;">user_name</span> 님이 좋아요를 눌렀습니다.
							</div>
							</li>
							<li class="comment_container">
							<img class="comment_profile" src="@/assets/sample.png">
							<div style="display:flex; align-items:center;">
									<span style="font-weight:bold;">user_name</span> 님이 팔로우를 하셨습니다.
							</div>
							</li>
						</ul>
					</div>
			</div>
	</div>
</template>

<script>
export default {};
</script>

<style lang="scss">
@import "@/components/home/AlarmModal.scss";
</style>

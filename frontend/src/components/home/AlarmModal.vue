<template>
    <div class="modal">
        <div class="overlay" @click="$emit('close-modal')"></div>
        <div class="modal-card">
            <div class="modal_top">알람창<button @click="$emit('close-modal')">닫기</button></div>
            <div>
                <ul style="padding:0px; margin:3px; ">
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

<style>
/* Modal */
.modal, .overlay{
    width: 100%;
    height: 100%;
    position: fixed;
    left: 0;
    top: 0;
}
.overlay{
    opacity: 0.5;
    background-color: black;
}
.modal-card{
    position: relative;
    width:392px;
    margin: auto;
    margin-top: 50px;
    padding: 10px;
    background-color: white;
    height: 400px;
    z-index: 10;
    opacity: 1;
    overflow:auto;
}
.modal_top{
    height:30px;
    display:flex;
    align-items:center;
    justify-content:space-between;
}
.comment_container{
  display: flex;
  margin:0px 10px 10px 5px;
}
.comment_profile{
  width: 40px;
  height: 40px;
  border-radius: 50%;
  overflow: hidden;
  margin:0px 10px 0px 0px;
}
</style>

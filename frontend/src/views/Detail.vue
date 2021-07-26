<template>
  <div style="height:781px">
      <div class="top">
        <img class="back_icon" src="@/assets/back.png" alt="">
        <img class="logo" src="@/assets/jubging.png" alt="logo" width="100px;">
      </div>
      <div class="article_content">
          <!--유저 정보-->
          <div class="user_info">
            <div class="profile_img">
              <img class="profile" src="@/assets/sample.png">
            </div>
            <span>usernickname</span>
          </div>
          <!--사진들-->
          <div class="article_img_container">
            <img class="article_img" src="@/assets/sample6.png" alt="">
          </div>
          <!--게시글 내용-->
          <div class="content_box">
            오늘 줍깅을 했다. 첫게시글~_ ㄷㄹ엄개ddasddad허ㅑㅑㅐ허매허
          </div>
          <!--좋아요 댓글-->
          <div class="like_comment_container">
            <div class="lcbox" >
              <img src="@/assets/heart_empty.png" alt="" style="width:20px; margin-right:5px;"><span style="margin-bottom: 3px;">10</span>
            </div>
            <div class="lcbox">
              <img src="@/assets/comments.png" alt="" style="width:20px; margin-right:5px;"> <span style="margin-bottom: 3px;">8</span>
            </div>
          </div>
      </div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
.top{
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 50px;
  width: 62%;
}
.back_icon{
  margin-left: 5px;
  width:35px;
}
.article_content{
  display:flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}
.user_info{
  display: flex;
  align-items: center;
  margin-top: 9vh;
  margin-bottom: 2.5vh;
}
.profile_img{
  width: 50px;
  height: 50px;
  border-radius: 70%;
  overflow: hidden;
  margin-right:20px;
}
.profile{
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.article_img{
  width: 300px;
  height: 300px;
}
.content_box{
  margin-top:2.5vh;
  width: 300px;
  margin-bottom:2.5vh;
  text-align: center;
}
.like_comment_container{
  display: flex;
}
.lcbox{
  display: flex;
  align-items: center;
  padding: 20px;
}
</style>
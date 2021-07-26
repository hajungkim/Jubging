<template>
  <div>
      
      <div id="header">
          <div class="goback to-center">
            <img src="" alt="뒤로가기">
          </div>
          <div class="to-center">
            <img src="" alt="줍깅 로고">
          </div>
      </div>
      <div id="body">
          <div class="item">
            <div id="info">
                <div>
                    <div>
                        <span class="bolder">0.0</span><span> km</span>
                    </div>
                    <div><b>운동 거리</b></div>
                </div>
                <div>
                    <div>
                        <span class="bolder">0</span><span>h </span>
                        <span class="bolder">00</span><span>m </span>
                        <span class="bolder">00</span><span>s </span>
                    </div>
                    <div><b>시간</b></div>
                </div>
            </div>
          </div>
        <div class="item">
            <div><h3>어디에서 조깅하셨나요?</h3></div>
            <div id="spot">
                <div class="item-grid">산</div>
                <div class="item-grid">강</div>
                <div class="item-grid">바다</div>
                <div class="item-grid">집주변</div>
            </div>
        </div>
        <div class="item">
            <div><h3>어떤 쓰레기를 주우셨나요?</h3></div>
            <div id="trash">
                <div class="item-grid">일반</div>
                <div class="item-grid">플라스틱</div>
                <div class="item-grid">캔</div>
                <div class="item-grid">비닐</div>
                <div class="item-grid">종이</div>
                <div class="item-grid">병</div>
                <div class="item-grid">스티로폼</div>
                <div class="item-grid">고철</div>
            </div>
        </div>
        <button class="btn">글 쓰기 ></button>
      </div>

  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#header {
    position: relative;
    width: 100%;
    height: 50px;
    background-color: white;
}
#body {
    display: flex;
    margin: 0 16px;
    flex-direction: column;
    justify-content: space-around;
    height: 731px;
    background-color: white;
}
#map {
    width: 100%;
    height: 0;
    padding-bottom: 130%;
}
#info {
    display: flex;
    align-items: center;
    justify-content: space-around;
}
#spot {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
#trash {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
.to-center {
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
.goback {
    position: absolute;
    left: 0;
}
.item {
    text-align: center;
}
.item-grid {
    width: 60px;
    height: 60px;
    margin: 10px;
    line-height: 60px;
    background-color: cadetblue;
    border-radius: 20px;
    font-weight: bold;
    font-size: 14px;
    border: 2px solid black;
}
.bolder {
    font-weight: bold;
    font-size: 32px;
}
.btn {
    padding: 20px;
    margin: 10px 30px;
    border-radius: 30px;
    font-weight: bold;
    font-size: 18px;
}
</style>
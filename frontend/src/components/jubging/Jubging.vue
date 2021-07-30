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
        <div id="map"></div>
        <div id="info">
            <div class="item">
                <div class="bolder">{{latitude}}</div>
                <div>거리(km)</div>
            </div>
            <div class="border"></div>
            <div class="item">
                <div class="bolder">{{longitude}}</div>
                <div>시간</div>
            </div>
        </div>
        <button class="btn">시작하기</button>
      </div>

  </div>
</template>

<script>
export default {

name: "Jubging",
components:{
},
props: {
},
data() {
	return{
        myKey: "8774c36051efa950c0ca483b2578a15c",
        latitude: 0.0,
        longitude: 0.0,
	}
},
computed:{
},
watch:{
    // latitude: function() {
    //     this.initMap(this.latitude, this.longitude)
    // }
},
created() {
},
mounted() {
    console.log("mounted")
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript()
    window.getInfo = this.getInfo
},
methods:{
    getInfo(latitude, longitude) {
        this.latitude = latitude
        this.longitude = longitude
    },
    initMap(latitude, longitude) {
        console.log("ininMap")
        var container = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng(latitude, longitude),
            level: 3
        };

        var map = new kakao.maps.Map(container, options);
        var marker = new kakao.maps.Marker({
            position: map.getCenter()
        })
        marker.setMap(map)
    },
    addScript() { 
        console.log("addScript");
        const script = document.createElement('script'); 
        script.onload = () => kakao.maps.load(this.initMap); 
        script.src = `http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=${this.myKey}&libraries=services,clusterer,drawing`; 
        document.head.appendChild(script); 
    },
},
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
    flex-direction: column;
    height: 731px;
    background-color: white;
}
#map {
    width: 100%;
    height: 0;
    padding-bottom: 130%;
}
#info {
    flex: 1;
    display: flex;
    flex-direction: row;
    align-items: center;
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
    flex: 1;
    text-align: center;
}
.bolder {
    font-weight: bold;
    font-size: 32px;
}
.border {
    border: 1px solid black;
    height: 60px
}
.btn {
    padding: 20px;
    margin: 10px 30px;
    border-radius: 30px;
    font-weight: bold;
    font-size: 18px;
    background-color: cadetblue;
}
</style>
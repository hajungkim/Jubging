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
                <div class="bolder">0.0</div>
                <div>거리(km)</div>
            </div>
            <div class="border"></div>
            <div class="item">
                <div class="bolder">00:00</div>
                <div>시간</div>
            </div>
        </div>
        <button class="btn">시작하기</button>
      </div>

  </div>
</template>

// <script type="text/javascript">
//     function getInfo(latitude) {
//         this.latitude = latitude
//         //this.longitude = longitude
//         console.log(`${this.latitude} ${this.longtitude}`)
//     }
// </script>
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
},
created() {
},
mounted() {
    console.log("mounted")
    window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
},
methods:{
    initMap() {
        console.log("ininMap")
        var container = document.getElementById('map');
        var options = {
            center: new kakao.maps.LatLng(33.450701, 126.570667),
            level: 3
        };

        var map = new kakao.maps.Map(container, options);
        if (navigator.geolocation) {
            
            // GeoLocation을 이용해서 접속 위치를 얻어옵니다
            navigator.geolocation.getCurrentPosition(function(position) {
                
                var lat = position.coords.latitude, // 위도
                    lon = position.coords.longitude; // 경도
                
                var locPosition = new kakao.maps.LatLng(lat, lon), // 마커가 표시될 위치를 geolocation으로 얻어온 좌표로 생성합니다
                    message = '<div style="padding:5px;">여기에 계신가요?!</div>'; // 인포윈도우에 표시될 내용입니다
                
                // 마커와 인포윈도우를 표시합니다
                displayMarker(locPosition, message);
                    
            });
            
        } else { // HTML5의 GeoLocation을 사용할 수 없을때 마커 표시 위치와 인포윈도우 내용을 설정합니다
            
            var locPosition = new kakao.maps.LatLng(33.450701, 126.570667),    
                message = 'geolocation을 사용할수 없어요..'
                
            displayMarker(locPosition, message);
        }
        function displayMarker(locPosition, message) {
        var marker = new kakao.maps.Marker({  
            map: map, 
            position: locPosition
        }); 
        
        var iwContent = message, // 인포윈도우에 표시할 내용
            iwRemoveable = true;

        // 인포윈도우를 생성합니다
        var infowindow = new kakao.maps.InfoWindow({
            content : iwContent,
            removable : iwRemoveable
        });
        
        // 인포윈도우를 마커위에 표시합니다 
        infowindow.open(map, marker);
        
        // 지도 중심좌표를 접속위치로 변경합니다
        map.setCenter(locPosition);      
    }
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
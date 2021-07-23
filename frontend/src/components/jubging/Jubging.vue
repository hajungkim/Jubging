<template>
  <div>
      <h1>Jubging</h1>
      <div id="map"></div>
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
        myKey: "8774c36051efa950c0ca483b2578a15c"
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

<style>
#map {
    width: 100%;
    height: 300px;
    background-color: aliceblue;
}
</style>
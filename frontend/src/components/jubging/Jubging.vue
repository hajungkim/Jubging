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
        <img height="550px" src="" alt="줍깅 설명">
        <button @click="startJubging()" class="btn">줍깅 시작</button>
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
        btnMessage: this.$store.state.jubgingMessage,
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
},
methods:{
    startJubging() {
        window.Android.startJubging()
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
    height: 100%;
    background-color: white;
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
.btn {
    padding: 20px;
    margin: 10px 30px;
    border-radius: 30px;
    font-weight: bold;
    font-size: 18px;
    background-color: cadetblue;
}
</style>
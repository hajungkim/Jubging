<template>
  <div>
    <div id="header">
      <div class="to-center">
        <img class="logo" src="@/assets/logo/textlogo.png" alt="줍깅 로고">
      </div>
      </div>
      <div id="body">
        <div class="item">
          <div id="info">
            <div>
              <div>
                <span class="bolder">{{ this.jubgingInfo.dist }}</span><span> km</span>
              </div>
              <div><b>운동 거리</b></div>
            </div>
            <div>
              <div>
                <span class="bolder">0</span><span>h </span>
                <span class="bolder">0</span><span>m </span>
                <span class="bolder">{{ this.jubgingInfo.time }}</span><span>s </span>
              </div>
              <div><b>시간</b></div>
            </div>
          </div>
        </div>
        <div class="item">
          <div><h3>어디에서 조깅하셨나요?</h3></div>
          <div id="spot">
            <div class="item-grid"  @click="toggleSpot('mountain')" :class="{'item-check': spot.mountain}">산</div>
            <div class="item-grid"  @click="toggleSpot('river')" :class="{'item-check': spot.river}">강</div>
            <div class="item-grid"  @click="toggleSpot('ocean')" :class="{'item-check': spot.ocean}">바다</div>
            <div class="item-grid"  @click="toggleSpot('arround')" :class="{'item-check': spot.arround}">집주변</div>
          </div>
        </div>
        <div class="item">
          <div><h3>어떤 쓰레기를 주우셨나요?</h3></div>
          <div id="trash">
            <div class="item-grid"  @click="toggleTrash('trash')" :class="{'item-check': trash.trash}">일반</div>
            <div class="item-grid"  @click="toggleTrash('plastic')" :class="{'item-check': trash.plastic}">플라스틱</div>
            <div class="item-grid"  @click="toggleTrash('can')" :class="{'item-check': trash.can}">캔</div>
            <div class="item-grid"  @click="toggleTrash('vinyl')" :class="{'item-check': trash.vinyl}">비닐</div>
            <div class="item-grid"  @click="toggleTrash('paper')" :class="{'item-check': trash.paper}">종이</div>
            <div class="item-grid"  @click="toggleTrash('bottle')" :class="{'item-check': trash.bottle}">병</div>
            <div class="item-grid"  @click="toggleTrash('styroform')" :class="{'item-check': trash.styroform}">스티로폼</div>
            <div class="item-grid"  @click="toggleTrash('metal')" :class="{'item-check': trash.metal}">고철</div>
          </div>
        </div>
        <button class="btn-next" @click="goNewArticle()">{{ address }} ></button>
      </div>
  </div>
</template>

<script>
export default {
components:{
},
props: {
},
data() {
	return{
    jubgingInfo: this.$store.state.jubgingInfo,
    spot: {mountain: false, river: false, ocean: false, arround: false},
    trash: {trash: false, plastic: false, can: false, vinyl: false, 
            paper: false, bottle: false, styroform: false, metal: false},
	}
},
computed:{
},
watch:{
},
created() {
},
mounted() {
  this.address =  this.$store.state.address
  this.jubgingInfo = this.$store.state.jubgingInfo
},
methods:{
  toggleSpot(here) {
    this.spot[here] = !this.spot[here]
    for (var where in this.spot) {
      if (where != here && this.spot[where]) {
        this.spot[where] = false
      }
    }
  },
  toggleTrash(what) {
    this.trash[what] = !this.trash[what]
  },
  goNewArticle() {
    var data = {spot: this.spot, trash: this.trash}
    this.$store.dispatch('setJubgingOption', data)
    this.$router.push({name:'NewArticle'})
  }
},
}
</script>

<style lang="scss" scoped>
@import "@/views/jubging/JubgingOff.scss";

</style>
<template>
  <div ref="printMe" style="width:300px; height:300px;">
    <div class="main_image">
      <img v-if="editflag" :src="cropImg" alt="">
      <img class="logoimg" src="@/assets/logo/iconlogo.png">
    </div>
    <div class="infotext">
      <span style="margin-right:42px; z-index=1050;">{{jubgingInfo.time}} 분</span>
      <span style="margin-left:42px; z-index=1050;">{{jubgingInfo.dist}}km</span>
    </div>
  </div>
</template>

<style>
.main_image{
  position:relative;
  width:300px;
  height:300px;
}
.logoimg{
  position: absolute;
  width:50px;
  height:50px;
  opacity:0.6;
  margin-top:5px;
  margin-left:-55px;
}
.infotext{
  width:300px;
  position: absolute;
  display: flex;
  justify-content: space-around;
  font-size: 25px;
  margin-top:-40px;
}
</style>

<script>
import { mapState } from 'vuex'

export default {
name: 'Editfilter',
props: {
  cropImg: String,
  editflag: Boolean,
},
data(){
  return {
    output: null
  }
},
computed:{
  ...mapState([
    'jubgingInfo',
  ]),
},
created(){
},
methods:{
  async print(){
      const el = this.$refs.printMe;
      const options = {
        type: 'dataURL'
      }
      this.$store.state.filterUrl = await this.$html2canvas(el,options)
      this.$emit('send')
    }
},
watch:{
  editflag:{
    deep:true,
    handler(){
        this.print()
    }
  }
}
}
</script>
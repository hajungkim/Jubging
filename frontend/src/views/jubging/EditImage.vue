<template>
  <div>
    <canvas id="canvas" width="400" height="300">

    </canvas>
  </div>
</template>


<script>

export default {
components:{
},
props: {
},
data() {
  return {
    canvas: null,
    ctx: null,
  }
},
computed:{
},
watch:{
},
created() {
},
mounted() {
  this.canvas = document.getElementById("canvas")
  var ctx = this.canvas.getContext("2d")

  ctx.beginPath()
  ctx.moveTo(3,5)
  ctx.lineTo(100,16)
  ctx.stroke()
},
methods:{
},
}
</script>

<style scoped>
#canvas {
  background-color: aqua;
}
</style>
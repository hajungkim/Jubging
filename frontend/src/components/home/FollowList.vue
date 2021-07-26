<template>
  <div class="follow_div">
    <label class="switch">
      <input type="checkbox" @click="followToggle()">
      <span class="slider round"></span>
    </label>
  </div>
</template>

<script>
export default {
  data(){
    return {
      toggle:true
    }
  },
  methods:{
    followToggle(){
      console.log(this.toggle)
      this.toggle = !this.toggle
    }
  }
}
</script>

<style>
.follow_div{
  display: flex;
  margin-left: auto;
  margin-left:30px;
  justify-content: center;
  align-items: center;
}
.switch{
  position:relative;
  display: inline-block;
  width:52px;
  height:28px;
}
.switch input{
  opacity: 0;
  width: 0;
  height: 0;
}
.slider{
  position: absolute;
  cursor: pointer;
  top: 0;
  left:0;
  right:0;
  bottom:0;
  background-color: #ccc;
  -webkit-transition: .4s;
  transition: .4s;
}
.slider:before{
  position: absolute;
  content:"";
  height: 20px;
  width: 20px;
  left:4px;
  bottom:4px;
  background-color: white;
  -webkit-transition: .4s;
  transition: .4s;
}
input:checked + .slider{
  background-color: #2196F3;
}
input:focus + .slider{
  box-shadow: 0 0 1px #2196F3;
}
input:checked +.slider:before{
  -webkit-transform: translateX(26px);
  -ms-transform: translateX(26px);
  transform: translateX(26px);
}
.slider.round{
  border-radius: 34px;
}
.slider.round:before{
  border-radius: 50%;
}
</style>
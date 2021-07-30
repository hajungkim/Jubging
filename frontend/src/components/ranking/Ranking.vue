<template>
  <div id="container">
    <div id="left">
      Hello
    </div>
    <div id="right">
      <div style="background-color: red; height: 10px;">Hello</div>
    </div>
    <div class="clear"></div>
  </div>
</template>

<script>
export default {

}
</script>

<style>
    #left {
      width: 200px;
      float: left;
    }
    #right {
      margin-left: 200px;
      /* Change this to whatever the width of your left column is*/
    }
    .clear {
      clear: both;
    }
</style>
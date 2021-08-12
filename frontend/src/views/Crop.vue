<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="back"/>
      <h4 class="m-0 logo">회원 정보 수정</h4>
    </div>

    <div class="container">
      <div>
        <img id="image" src="@/assets/user_default.png" alt="Picture">
      </div>
      <h3>Result</h3>
      <button type="button" id="button" @click="crop">Crop</button>
      <div id="result"></div>
      <button @click="upload">업로드</button>
    </div>

  </div>
</template>

<script>
import Cropper from 'cropperjs';
import axios from 'axios';

axios.defaults.baseURL = 'http://localhost:8080/'

export default {
  name: 'Crop',
  data() {
    return {
      cropper: '',
      croppedCanvas: ''
    }
  },
  mounted() {

    var image = document.getElementById('image');
    this.cropper = new Cropper(image, {
      aspectRatio: 1,
      viewMode: 1,
    });
    console.log(this.cropper)

  },
  methods: {
    upload() {

      this.croppedCanvas.toBlob((blob) => {
        let form = new FormData();

        form.append('file', blob, 'profile.png');
        console.log(form)
        
        axios.post('/images', form, { 
          header: { 
            'processData' : false,
            'Content-Type' : false,
            },
        })
        .then(res => {
          console.log(res.data.data)
        })
        .catch(err => {
          console.log(err)
        })
      })
    
      

    },
    crop() {
    function getRoundedCanvas(sourceCanvas) {
      console.log(sourceCanvas)
      var canvas = document.createElement('canvas');
      var context = canvas.getContext('2d');
      var width = sourceCanvas.width;
      var height = sourceCanvas.height;

      canvas.width = width;
      canvas.height = height;
      context.imageSmoothingEnabled = true;
      context.drawImage(sourceCanvas, 0, 0, width, height);
      context.globalCompositeOperation = 'destination-in';
      context.beginPath();
      context.arc(width / 2, height / 2, Math.min(width, height) / 2, 0, 2 * Math.PI, true);
      context.fill();
      return canvas;
    }

    var result = document.getElementById('result');
    var roundedCanvas;
    var roundedImage;

    console.log(this.cropper)
    // Crop
    this.croppedCanvas = this.cropper.getCroppedCanvas();

    // Round
    roundedCanvas = getRoundedCanvas(this.croppedCanvas);

    // Show
    roundedImage = document.createElement('img');
    roundedImage.src = roundedCanvas.toDataURL()
    result.innerText = '';
    result.appendChild(roundedImage);
    
    
    
    },
    back() {
      this.$router.push({ name: 'ChangeSetting'})
    }
  }
}
</script>

<style lang="scss" scoped>
  .top{
    display: flex;
    align-items: center;
    height: 50px;
    .back_icon{
      margin-left:15px;
      cursor: pointer;
    }
    .logo{
      margin-left: 130px;
      transform: scale(1.5);
    }
    .option_button{
      margin-left:120px;
      transform: scale(1.5);
    }
  }

  .container {
    margin: 20px auto;
    max-width: 640px;
  }

  img {
    max-width: 100%;
  }

  .cropper-view-box,
  .cropper-face {
    border-radius: 50%;
  }
</style>
<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="back"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
    </div>

    <div id="body">
      <div class="item-text">
        <h3>줍깅 후기 작성하기</h3>
          <textarea
            v-model="content"
            name="text" 
            placeholder="본문에 #을 이용하여 태그를 사용해보세요"
            id="ta" 
            cols="41" 
            rows="13"
            v-on:input="content_typing"
          ></textarea>
      </div>

      <div class="item-photo">
        <div v-for="(photo, i) in photos" :key="i" class="photo-grid">
          <div class="item-grid" >
            <img id="preview" :src="photo.preview" alt="">
          </div>
          <div class="file-close-button" @click="photoDeleteButton" :name="photo.number">-</div>
        </div>
        <ModalView v-show="isModalViewed" @close-modal="modalOff" :modalTitle="'줍깅 사진 편집'">
          <div class="img-container">
            <img id="image" src="@/assets/user_default.png" alt="Picture">
            <button type="button" id="button" @click="crop" class="btn">Crop</button>
          </div>
        </ModalView>
        <div class="item-grid" v-if="num<3">
          <label for="input-image">
            <div class="input-text">
              <font-awesome-icon icon="camera" class="m-0 icon"/>
              <p class="m-0">{{ num }}/3</p>
            </div>
          </label>
           <input class="item-grid" type="file" id="input-image" @change="readImage" ref="photos" multiple :disabled="num>=3"/>
        </div>
      </div>
      <button v-if="isbutton && !iscontent" class="btn" @click="sendData">올리기 ></button>
      <button v-if="!isbutton" class="btn" disabled="true">사진을 등록해주세요</button>
      <button v-if="iscontent && isbutton" class="btn" disabled="true">내용을 200자 미만으로 작성해주세요</button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import ModalView from '@/views/ModalView.vue'
import { mapState } from 'vuex'
import Cropper from 'cropperjs';

axios.defaults.baseURL = 'http://localhost:8080/'
export default {
name: 'NewArticle',
components:{
  ModalView,
},
data() {
	return{
    photoCnt: 0,
    content: '',
    photos: [],
    photosPath: '',
    files: [],
    isbutton: false,
    iscontent: false,
    isModalViewed: false,
    cropper: null,
    croppedCanvas: '',
    canvasList: [],
    num: 0,
	}
},
computed:{
  ...mapState([
			'userId',
      'jubgingOption',
      'jubgingInfo',
  ]),
},
watch:{
},
created() {
},
mounted() {
},
methods: {
  content_typing(e){
    this.max_length(e,200);
  },
  max_length(e,len){
    var val = e.target.value;
    if(val.length > len){
      this.iscontent = true
    }
    else{
      this.iscontent = false
    }
  },
  readImage(event) {
    var image = document.getElementById('image');
    var input = document.getElementById('input-image');
    var files = event.target.files;
    if (files && files.length > 0) {
      var file = files[0];
      if (URL) {
        input.value = '';
        image.src = URL.createObjectURL(file);
        this.modalOn()
      } else if (FileReader) {
        var reader = new FileReader();
        reader.onload = function (event) {
          input.value = '';
          image.src = event.target.result;
          this.modalOn()
        };
        reader.readAsDataURL(file);
      }
    }
  },
  photoDeleteButton(e) {
    var name = e.target.getAttribute('name')
    if (this.canvasList.length === 1){
      this.canvasList.pop()
    } 
    else{
      this.canvasList.splice(name-1, 1)
    }
    if (this.canvasList.length === 0){
      this.isbutton = false
    }
    this.num = this.num - 1
    this.photos = this.photos.filter(data => data.number !== Number(name))
  },
  back() {
    this.$router.push({name: 'Register'})
  },
  modalOn() {
  this.isModalViewed = true
  var image = document.getElementById('image');
  this.cropper = new Cropper(image, {
    aspectRatio: 1,
    viewMode: 1,
  });
  },

  modalOff() {
  this.isModalViewed = false
  this.cropper.destroy();
  this.cropper = null;
  },

  crop() {
  this.croppedCanvas = this.cropper.getCroppedCanvas({
    width: 300,
    height: 300,
  });
  this.photos = [...this.photos, {
      preview: this.croppedCanvas.toDataURL(),
      number: this.num + 1
    }]
  this.canvasList.push(this.croppedCanvas)
  this.isbutton = true
  this.num = this.num + 1
  this.modalOff()
  // 추가 부분
  },
  async sendData() {
    var photosPath = ''
    var j = 0
    var L = this.canvasList.length;
    var self = this
    for (let i=0; i<this.canvasList.length; i++){
      this.canvasList[i].toBlob(function (blob) {
      var form = new FormData();
      form.append('file', blob, i+".png");
      axios.post('/images', form)
        .then(res => {
          photosPath = photosPath + res.data.data + '#'
          j = j + 1
          if (j == L){
            self.sendServer(photosPath)
          }
        })
        .catch(err => {
          console.log(err)
        })
      })
    }     
    this.sendOption()
  },
  sendServer(photosPath) {
    var data = {
      content: this.content,
      photosPath: photosPath,
      userId: this.userId,
    }
    axios.post('/article', data)
      .then((res) => {
        console.log(res.data)
      })
      .catch((err)=>{
        console.error(err)
      })
  },
  sendOption(){
    // this.jubgingInfo.distance.toStirng()   distance 바꿔야함
    var data = {...this.jubgingOption.spot, ...this.jubgingOption.trash ,'distance': "2.2",'userId': parseInt(this.userId)}          
    axios.put('/mission', data)
      .then((res) => {
        console.log(res.data)
      })
      .catch((err)=>{
        console.error(err)
      })
  },
},
}
</script>

<style lang="scss" scoped>
@import "@/views/jubging/NewArticle.scss";
</style>
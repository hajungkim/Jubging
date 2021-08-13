<template>
  <div>
    <div id="header">
      <div class="to-center">
        <img class="logo" src="@/assets/logo/textlogo.png" alt="줍깅 로고">
      </div>
    </div>
    <div id="body">
      <div class="item">
        <div><h3>줍깅 후기 작성하기</h3></div>
        <textarea
          v-model="content"
          name="text" 
          placeholder="본문에 #을 이용하여 태그를 사용해보세요"
          id="ta" 
          cols="33" 
          rows="14"
        ></textarea>
      </div>

      <div class="item">
        <div v-for="(photo, i) in photos" :key="i" class="photo-grid">
          <div class="item-grid">
            <div class="file-close-button" @click="photoDeleteButton" :name="photo.number">x</div>
            <img id="preview" :src="photo.preview" alt="">
          </div>
        </div>
        <ModalView v-show="isModalViewed" @close-modal="modalOff">
          <div class="img-container">
            <img id="image" src="@/assets/user_default.png" alt="Picture">
            <button type="button" id="button" @click="crop" class="btn">Crop</button>
          </div>
        </ModalView>
        <div class="item-grid">
           <label for="input-image">{{ num }}/3</label>
           <input class="item-grid" type="file" id="input-image"  @change="readImage" ref="photos" multiple :disabled="num>=3"/>
        </div>
      </div>
      <button v-if="isbutton" class="btn-next" @click="sendData">올리기 ></button>
      <button v-if="!isbutton" class="btn-next" disabled="true">사진을 등록해주세요</button>
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
props: {
},
data() {
	return{
    photoCnt: 0,
    content: '',
    photos: [],
    files: [],
    photosPath: '',
    isbutton: false,
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

    // var num = -1
    // for(var i=0; i<this.$refs.photos.files.length; i++) {
    //   this.files = [...this.files, this.$refs.photos.files[i]]
    //   this.photos = [...this.photos, {
    //     file: this.$refs.photos.files[i],
    //     preview: URL.createObjectURL(this.$refs.photos.files[i]),
    //     number: i
    //   }]
    //   num = i
    // }
    // this.isbutton = true
    // this.photoCnt = this.photoCnt + num + i
    
  },
  photoDeleteButton(e) {
    var name = e.target.getAttribute('name')
    this.num = this.num - 1
    this.canvasList.pop()
    this.photos = this.photos.filter(data => data.number !== Number(name))
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
    width: 70,
    height: 70,
  });
  this.photos = [...this.photos, {
      preview: this.croppedCanvas.toDataURL(),
      number: this.num + 1
    }]
  // this.files = [...this.files, this.croppedCanvas.toDataURL()]
  this.canvasList.push(this.croppedCanvas)
  this.isbutton = true
  this.num = this.num + 1
  // preview.src = this.croppedCanvas.toDataURL();   //원래 프리뷰 URL.createObjectURL(this.$refs.photos.files[i])

  this.modalOff()
  },

  async sendData() {
    for (let i=0; i<this.canvasList.length; i++){
        this.canvasList[i].toBlob(function (blob) {
        console.log(blob,'블롭')
        var form = new FormData();
        form.append('file', blob, 'article.png');
        axios.post('/images', form, { header: {'processData' : false, 'Content-Type' : 'multipart/form-data',} })
        .then(res => {
          console.log(res,'!!!!!!!!!!!!!!!!')
          this.photosPath = this.photosPath.concat(res.data.data + '#')
        })
        .catch(err => {
          console.log(err)
        })
      })
    }
    console.log(this.photosPath,'photosPath')
    this.sendServer()
    this.sendOption()

    for (let i=0; i<this.files.length; i++) {
      let form = new FormData()
      form.append('file', this.files[i])
      await axios.post('/images', form, { header: { 'Content-Type': 'multipart/form-data' } })
      .then(res => {
        this.photosPath = this.photosPath.concat(res.data.data + '#')
        console.log(this.photosPath,'@@@@@@@')
      })
      .catch((err)=>{
        console.error(err)
      })
    }
    this.sendServer(this.photosPath)
    this.sendOption()
  },
  sendServer() {
    console.log(this.photosPath,'서버에보내는경로')
    var data = {
      content: this.content,
      photosPath: this.photosPath,
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
    // this.jubgingInfo.distance.toStirng()        
  //   var data = {...this.jubgingOption.spot, ...this.jubgingOption.trash ,'distance': "2.2",'userId': parseInt(this.userId)}          
  //   axios.put('/mission', data)
  //     .then((res) => {
  //       console.log(res.data)
  //     })
  //     .catch((err)=>{
  //       console.error(err)
  //     })
  },
},
}
</script>

<style lang="scss" scoped>
@import "@/views/jubging/NewArticle.scss";
</style>
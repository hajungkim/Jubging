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
            <img :src="photo.preview" alt="">
          </div>
        </div>
        <div class="item-grid">
           <label for="input-image">{{ photoCnt }}/3</label>
           <input class="item-grid" type="file" id="input-image"  @change="readImage" ref="photos" multiple :disabled="photoCnt>=3"/>
        </div>
      </div>
      <button class="btn-next" @click="sendData">올리기 ></button>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { mapState } from 'vuex'
axios.defaults.baseURL = 'http://localhost:8080/'
export default {
components:{
},
props: {
},
data() {
	return{
    photoCnt: 0,
    content: '',
    photos: [],
    files: [],
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
  readImage() {
    var num = -1
    for(var i=0; i<this.$refs.photos.files.length; i++) {
      this.files = [...this.files, this.$refs.photos.files[i]]
      this.photos = [...this.photos, {
        file: this.$refs.photos.files[i],
        preview: URL.createObjectURL(this.$refs.photos.files[i]),
        number: i
      }]
      num = i
    }
    this.photoCnt = this.photoCnt + num + i
  },
  photoDeleteButton(e) {
    var name = e.target.getAttribute('name')
    this.photos = this.photos.filter(data => data.number !== Number(name))
  },
  sendData() {
    var photosPath = ''
    for (let i=0; i<this.files.length; i++) {
      let form = new FormData()
      form.append('file', this.files[i])
      axios.post('/images', form, { header: { 'Content-Type': 'multipart/form-data' } })
      .then(res => {
        photosPath = photosPath + res.data.data + '#'
      })
      .catch((err)=>{
        console.error(err)
      })
    }
    this.sendServer(photosPath)
    this.sendOption()
  },
  sendServer(photoPath) {
    var data = {
      content: this.content,
      photosPath: photoPath,
      userId: this.userId,
    }
    axios.post('/article', data)
      .then(() => {
      })
      .catch((err)=>{
        console.error(err)
      })
  },
  sendOption(){        
    var data = {...this.jubgingOption.spot, ...this.jubgingOption.trash ,'distance': this.jubgingInfo.distance.toStirng(),'userId': parseInt(this.userId)}          
    axios.put('/mission', data)
      .then(() => {
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
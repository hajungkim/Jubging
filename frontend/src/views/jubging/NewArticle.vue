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

axios.defaults.baseURL = 'http://localhost:8080/'
export default {
components:{
},
props: {
},
data() {
	return{
    photoCnt: 0,
    photos: [],
    files: [],
	}
},
computed:{
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
    for (let i=0; i<this.files.length; i++) {
      let form = new FormData()
      form.append('file', this.files[i])
      axios.post('/image', form, { header: { 'Content-Type': 'multipart/form-data' } })
      .then(res => {
        console.log(res)
      })
    }
  }
},
}
</script>

<style lang="scss" scoped>
@import "@/views/jubging/NewArticle.scss";

#preview-image {
  display: inline;
}
.item-grid label {
  display: inline-block;
  background-color: aquamarine;
  width: 100%;
  height: 100%;
  cursor: pointer;
  border-radius: 20px;
}
.item-grid input[type="file"] {
  position: absolute;
  width: 0;
  height: 0;
  padding: 0;
  overflow: hidden;
  border: 0;
}
.item-grid > img{
  width: 70px;
  height: 70px;
  border-radius: 20px;
}
.photo-grid {
  display: flex;
}

.file-close-button {
  position: absolute;
  /* align-items: center; */
  line-height: 18px;
  z-index: 99;
  font-size: 18px;
  right: 5px;
  top: 10px;
  color: #fff;
  font-weight: bold;
  background-color: #666666;
  width: 20px;
  height: 20px;
  text-align: center;
  cursor: pointer;
}


</style>
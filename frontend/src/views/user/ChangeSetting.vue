<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="back"/>
      <h4 class="m-0 logo">회원 정보 수정</h4>
    </div>

  <ModalView v-show="isModalViewed" @close-modal="modalOff">
    <div class="img-container">
      <img id="image" src="@/assets/user_default.png" alt="Picture">
      <button type="button" id="button" @click="crop" class="btn">Crop</button>
    </div>
  </ModalView>
    
    <div class="change-setting-wrap">

      <label class="label">
        <img class="rounded avatar" id="avatar" :src="credentials.profilePath" alt="avatar">
        <input type="file" class="sr-only" id="input" name="image" accept="image/*" @change="inputChange">
      </label>

      <div class="form-group">
        <div class="form-mb">
          <input class="form-input" type="text" id="email" v-model="credentials.email" placeholder="email" disabled=true>
          <div class="form-check" id="check-email" v-if="!error.email">
            <font-awesome-icon icon="check-circle"/>
            <span id="check-email-text"> 이메일은 변경할 수 없습니다.</span>
          </div>
        </div>

        <div class="form-mb">
          <input class="form-input" type="text" id="nickname" v-model="credentials.nickname" placeholder="nickname">
          <div class="form-check" id="check-nickname">
            <font-awesome-icon icon="check-circle"/>
            <span id="check-nickname-text"> 이용 가능한 닉네임 입니다.</span>
          </div>
        </div>

        <div class="form-mb">
          <input class="form-input" type="password" id="password" v-model="credentials.password" placeholder="new password">
          <div v-if="error.password" class="text-error form-error">
            <font-awesome-icon icon="check-circle"/>
            <span> {{ error.password }}</span>
          </div>
        </div>

        <div class="form-mb">
          <input class="form-input" type="password" id="passwordConfirmation" v-model="credentials.passwordConfirmation" placeholder="password Confirmation">
          <div v-if="error.passwordConfirmation" class="text-error form-error">
            <font-awesome-icon icon="check-circle"/>
            <span> {{ error.passwordConfirmation }}</span>
          </div>
        </div>

        <button class="btn-user-mgt" @click="changeSettingAndPic" :disabled="!isSubmit" :class="{ 'btn-user-mgt-disable' : !isSubmit }">수정 완료</button>
      </div>
    </div>

  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import axios from 'axios'
import ModalView from '@/views/ModalView.vue'
import Cropper from 'cropperjs';

axios.defaults.baseURL = 'http://localhost:8080/'

export default {
  name: 'ChangeSetting',
  components: {
    ModalView
  },
  data() {
    return{
      credentials: {
        email: '',
        password: '',
        passwordConfirmation: '',
        nickname: '',
        profilePath: ''
      },
      error: {
        password: false,
        passwordConfirmation: false,
        nickname: false,
      },
      unique: {
        nickname: true,
      },
      isSubmit: false,
      isModalViewed: false,
      cropper: null,
      croppedCanvas: ''
    }
  },
  computed: {
    ...mapState([
      'userInfo', 'sendImg'
    ])
  },
  watch: {
    credentials: {
      deep: true,
      handler() {
        this.checkForm()
      }
    },
    userInfo() {
      this.credentials.email = this.userInfo.email
      this.credentials.password = this.userInfo.password
      this.credentials.passwordConfirmation = this.userInfo.password
      this.credentials.nickname = this.userInfo.nickname
      this.credentials.profilePath = this.userInfo.profilePath
    }
   },
  created() {
    this.getUserInfo()
  },
  methods:{
    ...mapActions([
			'getUserInfo',
		]),
    checkForm() {
      if (this.credentials.password.length >= 0 && !this.validPassword(this.credentials.password)) {
        this.error.password = " 영문, 숫자 포함 8 자리 이상이어야 합니다.";
      } else {
        this.error.password = false
      }

      if (this.credentials.passwordConfirmation.length >= 0 && this.credentials.password !== this.credentials.passwordConfirmation) {
        this.error.passwordConfirmation = " 비밀번호가 일치하지 않습니다.";
      } else {
        this.error.passwordConfirmation = false
      }

      axios.post('user/nicknameck', this.credentials)
      .then(res => {
        if (this.credentials.nickname === this.userInfo.nickname) {
          this.unique.nickname = true
        } else {
          this.unique.nickname = res.data.data
        }

        if (this.unique.nickname) {
          document.querySelector('#check-nickname-text').innerText = " 이용 가능한 닉네임 입니다."
          document.querySelector('#check-nickname').classList.remove('text-error')
        } else {
          document.querySelector('#check-nickname-text').innerText = " 중복된 닉네임 입니다."
          document.querySelector('#check-nickname').classList.add('text-error')
        }
      })
      .then(() => {
        let isSubmit = true;
        Object.values(this.error).map((err) => {
          if (err) {
              isSubmit = false
          }
        })
        Object.values(this.unique).map((unique) => {
          if (!unique) {
              isSubmit = false
          }
        })
        this.isSubmit = isSubmit;
      })
      .catch(err => {
        console.error(err)
      })
    },
    validPassword(password) {
      var test = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z]).*$/;
      return test.test(password);
    },
    readImage(event) {
      if (event.target && event.target.files[0]) {
        var reader = new FileReader();
        reader.onload = function(event) {
          var profileImage = document.querySelector("#profileImage");
          profileImage.setAttribute("src", event.target.result);
        }
        reader.readAsDataURL(event.target.files[0]);
      }
    },
    changeSettingAndPic() {
      let self = this

      if (this.croppedCanvas) {
        this.croppedCanvas.toBlob(function (blob) {
          var form = new FormData();
          form.append('file', blob, 'profile.png');
          axios.post('/images', form, { 
            header: { 
              'processData' : false,
              'Content-Type' : false,
              },
          })
          .then(res => {
            console.log(res.data.data)
            self.credentials.profilePath = res.data.data
          })
          .then(() => {
            self.$store.dispatch('changeSetting', self.credentials)
          })
          .catch(err => {
            console.log(err)
          })
        });
      } else {
        this.$store.dispatch('changeSetting', this.credentials)
      }
    },
    back() {
      this.$router.push({ name: 'My' })
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
    inputChange(event) {
      var image = document.getElementById('image');
      var input = document.getElementById('input');
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
    crop() {
      var avatar = document.getElementById('avatar');

      if (this.cropper) {
          this.croppedCanvas = this.cropper.getCroppedCanvas({
          width: 150,
          height: 150,
        });
        avatar.src = this.croppedCanvas.toDataURL();
      }

      this.modalOff()
    },
  },
}
</script>

<style lang="scss" scoped>
@import "@/views/user/ChangeSetting.scss";

</style>
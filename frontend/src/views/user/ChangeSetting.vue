<template>
  <div class="change-setting-wrap">

      <h1>정보 수정</h1>
      
      <div class="form-group">

         <input class="form-input" type="text" id="email" v-model="credentials.email" placeholder="email">
         <div v-if="error.email" class="text-error form-error">{{error.email}}</div>
         <div @click="validUniqueEmail(credentials)" class="form-check">
            <font-awesome-icon icon="check-circle"/>
            <span id="check-email"> 이용 가능한 이메일 입니다.</span>
         </div>

         <input class="form-input" type="text" id="nickname" v-model="credentials.nickname" placeholder="nickname">
         <div @click="validUniqueNickname(credentials)" class="form-check">
            <font-awesome-icon icon="check-circle"/>
            <span id="check-nickname"> 이용 가능한 닉네임 입니다.</span>
         </div>

         <input class="form-input" type="password" id="password" v-model="credentials.password" placeholder="new password">
         <div v-if="error.password" class="text-error form-error">{{error.password}}</div>

         <input class="form-input" type="password" id="passwordConfirmation" v-model="credentials.passwordConfirmation" placeholder="password Confirmation">
         <div v-if="error.passwordConfirmation" class="text-error form-error">{{error.passwordConfirmation}}</div>

         <button @click="changeSetting(credentials)" :disabled="!isSubmit" :class="[isSubmit ? 'form-btn' : 'form-disable-btn']">정보 변경</button>
      </div>
  </div>
</template>

<script>
import { mapActions, mapState } from 'vuex'
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080/'

export default {
  name: 'ChangeSetting',
  data() {
    return{
      credentials: {
        email: '',
        password: '',
        passwordConfirmation: '',
        nickname: ''
      },
      error: {
        email: false,
        password: false,
        passwordConfirmation: false,
        nickname: false,
      },
      unique: {
        email: true,
        nickname: true,
      },
      isSubmit: false,
    }
  },
  computed: {
    ...mapState([
      'userInfo',
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
    }
   },
  created() {
    this.getUserInfo()
  },
  methods:{
    ...mapActions([
			'getUserInfo', 'changeSetting'
		]),
    checkForm() {
      if (this.credentials.email.length >= 0 && !this.validEmail(this.credentials.email)) {
        this.error.email = "올바른 이메일 형식이 아닙니다."
      } else {
        this.error.email = false
      }

      if (this.credentials.password.length >= 0 && !this.validPassword(this.credentials.password)) {
        this.error.password = "영문, 숫자 포함 8 자리 이상이어야 합니다.";
      } else {
        this.error.password = false
      }

      if (this.credentials.passwordConfirmation.length >= 0 && this.credentials.password !== this.credentials.passwordConfirmation) {
        this.error.passwordConfirmation = "비밀번호가 일치하지 않습니다.";
      } else {
        this.error.passwordConfirmation = false
      }

      if (this.credentials.email) {
        this.validUniqueEmail()
      }
      if (this.credentials.nickname) {
        this.validUniqueNickname()
      }

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
    },
    validEmail(email) {
      var test = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return test.test(email);
    },
    validPassword(password) {
      var test = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z]).*$/;
      return test.test(password);
    },
    validUniqueEmail() {
      axios.post('user/emailck', this.credentials)
      .then(res => {
        if (this.credentials.email === this.userInfo.email) {
          this.unique.email = true
        } else {
          this.unique.email = res.data.data
        }
        return this.unique.email
      })
      .then(data => {
        let checkEmailText = document.querySelector('#check-email')
        if (data) {
          checkEmailText.innerText = " 이용 가능한 이메일 입니다."
          checkEmailText.classList.remove('text-error')
        } else {
          checkEmailText.innerText = " 중복된 이메일 입니다."
          checkEmailText.classList.add('text-error')
        }
      })
      .catch(err => {
        console.error(err)
      })
    },
    validUniqueNickname() {
      axios.post('user/nicknameck', this.credentials)
      .then(res => {
        if (this.credentials.nickname === this.userInfo.nickname) {
          this.unique.nickname = true
        } else {
          this.unique.nickname = res.data.data
        }
        return this.unique.nickname
      })
      .then(data => {
        let checkNicknameText = document.querySelector('#check-nickname')
        if (data) {
          checkNicknameText.innerText = " 이용 가능한 닉네임 입니다."
          checkNicknameText.classList.remove('text-error')
        } else {
          checkNicknameText.innerText = " 중복된 닉네임 입니다."
          checkNicknameText.classList.add('text-error')
        }
      })
      .catch(err => {
        console.error(err)
      })
    }
  },
}
</script>

<style lang="scss" scoped>
@import "@/views/user/ChangeSetting.scss";

</style>
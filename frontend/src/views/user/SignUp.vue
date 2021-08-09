<template>
  <div class="signup-wrap">

		<h1>회원가입</h1>
		
		<div class="form-group">
			<div class="form-mb">
				<input class="form-input" type="text" id="email" v-model="credentials.email" placeholder="email">
				<div v-if="error.email" class="text-error form-error">
					<font-awesome-icon icon="check-circle"/>
					<span> {{ error.email }}</span>
				</div>
				<div class="form-check" id="check-email" v-if="!error.email">
          <font-awesome-icon icon="check-circle"/>
          <span id="check-email-text"> 이용 가능한 이메일 입니다.</span>
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
				<input class="form-input" type="password" id="password" v-model="credentials.password" placeholder="password">
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

			<button class="btn" @click="signup(credentials)" :disabled="!isSubmit" :class="{ 'btn-disable' : !isSubmit }">Signup</button>
		</div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080/'

export default {
	name: 'SignUp',
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
				email: false,
				nickname: false,
			},
			isSubmit: false,
		}
	},
	watch: {
		credentials: {
			deep: true,
			handler() {
				this.checkForm()
			}
		}
	},
	methods:{
		...mapActions([
			'signup',
		]),
		checkForm() {
      if (this.credentials.email.length >= 0 && !this.validEmail(this.credentials.email)) {
        this.error.email = " 올바른 이메일 형식이 아닙니다."
      } else {
        this.error.email = false
      }

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

      axios.all([axios.post('user/emailck', this.credentials), axios.post('user/nicknameck', this.credentials)])
      .then(axios.spread((res1, res2) => {
        this.unique.email = res1.data.data
        if (!this.error.email) {
          if (this.unique.email) {
            document.querySelector('#check-email-text').innerText = " 이용 가능한 이메일 입니다."
            document.querySelector('#check-email').classList.remove('text-error')
          } else {
            document.querySelector('#check-email-text').innerText = " 중복된 이메일 입니다."
            document.querySelector('#check-email').classList.add('text-error')
         }
        }

				this.unique.nickname = res2.data.data
        if (this.unique.nickname) {
          document.querySelector('#check-nickname-text').innerText = " 이용 가능한 닉네임 입니다."
          document.querySelector('#check-nickname').classList.remove('text-error')
        } else {
          document.querySelector('#check-nickname-text').innerText = " 중복된 닉네임 입니다."
          document.querySelector('#check-nickname').classList.add('text-error')
        }
      }))
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
		validEmail(email) {
			var test = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return test.test(email);
    },
		validPassword(password) {
			var test = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z]).*$/;
			return test.test(password);
		},
	},
}
</script>

<style lang="scss" scoped>
@import "@/views/user/SignUp.scss";

</style>
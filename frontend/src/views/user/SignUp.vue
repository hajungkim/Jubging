<template>
  <div class="signup-wrap">

		<h1>회원가입</h1>
		
		<div class="from-group">

			<input class="from-input" type="text" id="email" v-model="credentials.email" placeholder="email">
			<button @click="validUniqueEmail(credentials.email)">검사</button>
			<div v-if="error.email">{{error.email}}</div>

			<input class="from-input" type="password" id="password" v-model="credentials.password" placeholder="password">
			<div v-if="error.password">{{error.password}}</div>

			<input class="from-input" type="password" id="passwordConfirmation" v-model="credentials.passwordConfirmation" placeholder="password Confirmation">
			<div v-if="error.passwordConfirmation">{{error.passwordConfirmation}}</div>

			<input class="from-input" type="text" id="nickname" v-model="credentials.nickname" placeholder="nickname">
			<button @click="validUniqueNickname(credentials.nickname)">검사</button>

			<button class="from-btn" @click="signup(credentials)" :disabled="!isSubmit">Signup</button>
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
				email: null,
				password: null,
				passwordConfirmation: null,
				nickname: null
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
			if (this.credentials.email && !this.validEmail(this.credentials.email)) {
				this.error.email = "올바른 이메일이 아닙니다."
			} else {
				this.error.email = false
			}

			if (this.credentials.password && !this.validPassword(this.credentials.password)) {
				this.error.password = "영문, 숫자 포함 8 자리 이상이어야 합니다.";
			} else {
				this.error.password = false
			}

			if (this.credentials.passwordConfirmation && this.credentials.password !== this.credentials.passwordConfirmation) {
				this.error.passwordConfirmation = "비밀번호가 일치하지 않습니다.";
			} else {
				this.error.passwordConfirmation = false
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
		validUniqueEmail(email) {
			axios.post('user/emailck', email)
			.then(res => {
				console.log(res)
				this.unique.email = res.data
			})
			.catch(err => {
        console.error(err)
       })
		},
		validUniqueNickname(nickname) {
			axios.post('user/nicknameck', nickname)
			.then(res => {
				console.log(res)
				this.unique.nickname = res.data
			})
			.catch(err => {
        console.error(err)
       })
		}
	},
}
</script>

<style lang="scss" scoped>
@import "@/views/user/SignUp.scss";

</style>
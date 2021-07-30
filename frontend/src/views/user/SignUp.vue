<template>
  <div class="signup-wrap">

		<h1>회원가입</h1>
		
		<div class="from-group">

			<input class="from-input" type="text" id="email" v-model="credentials.email" placeholder="email">
			<div v-if="error.email">{{error.email}}</div>
			<input class="from-input" type="password" id="password" v-model="credentials.password" placeholder="password">
			<div v-if="error.password">{{error.password}}</div>
			<input class="from-input" type="password" id="passwordConfirmation" v-model="credentials.passwordConfirmation" placeholder="password Confirmation">
			<div v-if="error.passwordConfirmation">{{error.passwordConfirmation}}</div>
			<input class="from-input" type="text" id="nickname" v-model="credentials.nickname" placeholder="nickname">

			<button class="from-btn" @click="signup(credentials)">Signup</button>
		</div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
	name: 'SignUp',
	data() {
		return{
			email: null,
			credentials: {
				email: null,
				password: null,
				passwordConfirmation: null,
				nickname: null
			},
			error: {
				email: null,
				password: null,
				passwordConfirmation: null,
				nickname: null
			}
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

			if (this.credentials.password) {
				this.error.password = "영문,숫자 포함 8 자리 이상이어야 합니다.";
			} else {
				this.error.password = false
			}

			if (this.credentials.passwordConfirmation && this.credentials.password !== this.credentials.passwordConfirmation) {
				this.error.passwordConfirmation = "비밀번호가 일치하지 않습니다.";
			} else {
				this.error.passwordConfirmation = false
			}

		},
		validEmail(email) {
			var re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return re.test(email);
    },
		validPassword() {

		}
	},
}
</script>

<style scoped>

.signup-wrap {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.from-group {
	display: flex;
	flex-direction: column;
	margin-top: 10%
}

.from-group > .from-input {
	width: 300px;
	height: 40px;
	background: rgba(179, 179, 179, 0.55);
	border: 0px;
	border-radius: 20px;
	padding: 0px 20px;
	margin-bottom: 14px;
	color: white;
}

.from-group > .from-btn {
	color: white;
	background: linear-gradient(278.02deg, #1CA592 -0.77%, #FFEAC1 114.65%);

	width: 340px;
	height: 40px;
	border: 0px;
	border-radius: 20px;
}

.text-decoration-none {
	text-decoration: none;
}


</style>
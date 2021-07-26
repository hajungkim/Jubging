<template>
  <div class="signup-wrap">

		<h1>회원가입</h1>
		
		<div class="from-group">

			<input class="from-input" type="text" id="email" v-model="credentials.email" placeholder="email">
			<input class="from-input" type="password" id="password" v-model="credentials.password" placeholder="password">
			<input class="from-input" type="password" id="passwordConfirmation" v-model="credentials.passwordConfirmation" placeholder="password Confirmation">
			<input class="from-input" type="text" id="nickname" v-model="credentials.nickname" placeholder="nickname">

			<button class="from-btn" @click="signup">Signup</button>
		</div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
	name: 'SignUp',
	data() {
		return{
			credentials: {
				email: null,
				password: null,
				passwordConfirmation: null,
				nickname: null
			}
		}
	},
	methods:{
		...mapActions([
			'signup',
		]),
	},
}
</script>

<style>

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
<template>
<div class="login-wrap">

	<img class="logo" src="https://i.imgur.com/M2nwxVM.png" alt="">

  <div class="from-group">
		<input class="from-input" type="text" id="username" v-model="credentials.username" placeholder="email">
		<input class="from-input" type="password" id="password" v-model="credentials.password" placeholder="password">
		<button class="from-btn" @click="login">Login</button>
		<div class="from-text-align">
			<router-link :to="{ name: 'SignUp' }" class="text-decoration-none"><span class="from-text-small">Sign Up</span></router-link>
			<router-link :to="{ name: 'FindPassword' }" class="text-decoration-none"><span class="from-text-small">Forgot your password?</span></router-link>
		</div>
  </div>

	<div class="social-group">
		<div class="social"></div>
		<div class="social"></div>
		<div class="social"></div>
	</div>
</div>
</template>

<script>
import '@/views/user/login.css'
import { mapActions } from 'vuex'

export default {
	name: 'Login',
	data() {
		return{
			credentials: {
				username: null,
				password: null,
			}
		}
	},
	methods:{
		...mapActions([
			'login',
		]),
	},
}
</script>

<style scoped>
.login-wrap {
	display: flex;
	flex-direction: column;
	align-items: center;
}

.logo {
	width: 250px;
	margin-top: 40%;
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


.from-text-align {
	display: flex;
	justify-content: space-between;
	padding: 10px;
}

.from-text-small {
	color: white;
	font-size: 14px;
}

.social-group {
	display: flex;
	flex-direction: row;
	justify-content: center;
	margin-top: 20%;
}

.social {
	width: 55px;
	height: 55px;
	background-color: black;
	border-radius: 50%;
	margin: 8px;
}

.text-decoration-none {
	text-decoration: none;
}

</style>
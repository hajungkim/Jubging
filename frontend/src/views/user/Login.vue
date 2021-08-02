<template>
<div class="login-wrap">

	<img class="logo" src="@/assets/logo/iconlogo.png" alt="">

  <div class="from-group">
		<input class="from-input" type="text" id="email" v-model="credentials.email" placeholder="email">
		<div v-if="error.email">{{error.email}}</div>
		<input class="from-input" type="password" id="password" v-model="credentials.password" placeholder="password">
		<div v-if="error.password">{{error.password}}</div>

		<button class="from-btn" @click="login(credentials)" :disabled="!isSubmit">Login</button>

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
	<button @click="logout">logout</button>
</div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
	name: 'Login',
	data() {
		return{
			credentials: {
				email: null,
				password: null,
			},
			error: {
				email: false,
				password: false,
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
			'login',
			'logout'
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

			let isSubmit = true;
			Object.values(this.error).map((err) => {
				if (err) {
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
		}
	},
}
</script>

<style lang="scss" scoped>
@import "@/views/user/Login.scss";

</style>
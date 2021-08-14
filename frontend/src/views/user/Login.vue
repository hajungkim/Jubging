<template>
<div class="login-wrap">

	<img class="logo" src="@/assets/logo/iconlogo.png" alt="">

  <div class="form-group">
		<div class="form-mb">
			<input class="form-input" type="text" id="email" v-model="credentials.email" placeholder="email">
			<div v-if="error.email" class="text-error form-error">
				<font-awesome-icon icon="check-circle"/>
				<span> {{ error.email }}</span>
			</div>
		</div>
		<div class="form-mb">
			<input class="form-input" type="password" id="password" v-model="credentials.password" placeholder="password">
			<div v-if="error.password" class="text-error form-error">
				<font-awesome-icon icon="check-circle"/>
				<span> {{ error.password }}</span>
			</div>
		</div>

		<button class="btn-user-mgt" @click="login(credentials)" :disabled="!isSubmit" :class="{ 'btn-user-mgt-disable' : !isSubmit }">Login</button>

		<div class="form-text-align">
			<router-link :to="{ name: 'SignUp' }" class="text-decoration-none"><span class="form-text-small">Sign Up</span></router-link>
			<router-link :to="{ name: 'FindPassword' }" class="text-decoration-none"><span class="form-text-small">Forgot your password?</span></router-link>
		</div>
  </div>

	<!-- <div class="social-group">
		<div class="social"></div>
		<div class="social"></div>
		<div class="social"></div>
	</div> -->
	<button @click="logout">logout 임시버튼</button>
	<span class="no-login" @click="noLogin">로그인 없이 둘러보기 ></span>
</div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
	name: 'Login',
	data() {
		return{
			credentials: {
				email: '',
				password: '',
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
		},
		noLogin() {
			this.$router.push({ name: 'Home' })
		}
	},
}
</script>

<style lang="scss" scoped>
@import "@/views/user/Login.scss";

</style>
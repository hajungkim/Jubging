<template>
  <div>
		<label for="username">username</label>
		<input type="text" id="username" v-model="credentials.username">
			
		<label for="password">password</label>
		<input type="text" id="password" v-model="credentials.password">

		<button @click="login">Login</button>
  </div>
</template>

<script>
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

<style>

</style>
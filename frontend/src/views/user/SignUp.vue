<template>
  <div>
		<label for="email">email</label>
		<input type="text" id="email" v-model="credentials.email">
			
		<label for="password">password</label>
		<input type="password" id="password" v-model="credentials.password">

		<label for="passwordConfirmation">passwordConfirmation</label>
		<input type="password" id="passwordConfirmation" v-model="credentials.passwordConfirmation">

		<label for="nickname">nickname</label>
		<input type="text" id="nickname" v-model="credentials.nickname">

		<button @click="signup">Signup</button>
  </div>
</template>

<script>
import { mapActions } from 'vuex'

export default {
	name: 'Signup',
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

</style>
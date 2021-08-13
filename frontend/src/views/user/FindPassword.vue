<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="back"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
    </div>

    <div class="findPassword-wrap">
			<div class="text-group">
				<h2 class="m-0">비밀번호 찾기</h2>
				<p class="m-0">이메일로 임시 비밀번호를 발급해드립니다</p>
			</div>
      
      <div class="form-group">
        <div class="form-mb">
          <input class="form-input" type="text" id="email" v-model="credentials.email" placeholder="email">
          <div v-if="error.email" class="text-error form-error">
            <font-awesome-icon icon="check-circle"/>
            <span> {{ error.email }}</span>
          </div>
        </div>
        <button @click="findPassword(credentials)" :disabled="!isSubmit" :class="[isSubmit ? 'btn-user-mgt' : 'btn-user-mgt-disable']">비밀번호 재발급</button>
      </div>

    </div>
  </div>
</template>

<script>
import { HTTP } from '@/util/http-common'

export default {
  name: 'FindPassword',
	data() {
		return{
			credentials: {
				email: '',
			},
      error: {
        email: false,
      },
      isSubmit: false
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
		checkForm() {
			if (this.credentials.email.length >= 0 && !this.validEmail(this.credentials.email)) {
				this.error.email = "올바른 이메일 형식이 아닙니다."
			} else {
				this.error.email = false
			}

			if (this.error.email) {
        this.isSubmit = false
      } else {
        this.isSubmit = true
      }
		},
		validEmail(email) {
			var test = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return test.test(email);
    },
    findPassword() {
      HTTP.post('user/emailck', this.credentials)
        .then(res => {
          if (res.data.data == '전송 성공') {
            alert('해당 이메일로 임시 비밀번호를 발급했습니다.')
            this.$router.push({ name: 'Login' })
          } else { 
            alert('존재하지 않는 이메일 입니다.')
          }
        })
        .catch(err => {
          console.error(err)
        })
    },
		back() {
			this.$router.push({ name: 'Login' })
		}
	},
}
</script>

<style lang="scss" scoped>
@import "@/views/user/FindPassword.scss";

</style>
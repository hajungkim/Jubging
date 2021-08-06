<template>
  <div class="findPassword-wrap">

		<h1>비밀번호 찾기</h1>
    <p>이메일로 임시 비밀번호를 발급해드립니다.</p>
		
		<div class="form-group">
			<input class="form-input" type="text" id="email" v-model="credentials.email" placeholder="email">
			<div v-if="error.email" class="text-error form-error">{{error.email}}</div>
			<button @click="findPassword(credentials)" :disabled="!isSubmit" :class="[isSubmit ? 'form-btn' : 'form-disable-btn']">비밀번호 재설정</button>
		</div>

  </div>
</template>

<script>
import axios from 'axios'

axios.defaults.baseURL = 'http://localhost:8080/'

export default {
  name: 'FindPassword',
	data() {
		return{
			credentials: {
				email: '',
			},
      error: {
        email: false,
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
      axios.post('user/emailck', this.credentials)
        .then(res => {
          if (res.data.data) {
            alert('존재하지 않는 이메일 입니다.')
          } else { 
            alert('해당 이메일로 임시 비밀번호를 발급했습니다.')
            // 임시 비밀번호 발급 요청 추가 예정
          }
        })
        .catch(err => {
          console.error(err)
        })
    }
	},
}
</script>

<style lang="scss" scoped>
@import "@/views/user/FindPassword.scss";

</style>
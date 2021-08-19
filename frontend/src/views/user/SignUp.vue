<template>
  <div>
    <div class="top">
      <font-awesome-icon icon="angle-left" class="fa-2x back_icon" @click="back"/>
      <img class="logo" src="@/assets/logo/textlogo.png" alt="logo" width="100px;">
    </div>

		<div class="signup-wrap">
			<div class="text-group">
				<h2 class="m-0">새로운 계정 만들기</h2>
				<p class="m-0">이메일은 추후 변경할 수 없습니다.</p>
			</div>
			
			<div class="form-group">

				<div class="form-mb">
					<p class="from-item-text">이메일</p>
					<div class="form-input-email">
						<input type="text" id="email" v-model="credentials.email" placeholder="email">
						<button @click="sendEmail" :disabled="error.email || !unique.email"><span>인증 메일 발송</span></button>
					</div>
					<div v-if="error.email" class="text-error form-error">
						<font-awesome-icon icon="check-circle"/>
						<span> {{ error.email }}</span>
					</div>
					<div v-show="credentials.email" class="form-check" id="check-email" v-if="!error.email">
						<font-awesome-icon icon="check-circle"/>
						<span id="check-email-text"> 이메일을 입력하세요.</span>
					</div>
				</div>

				<div class="form-mb">
					<p class="from-item-text">인증 번호</p>
					<input class="form-input" type="text" id="certificationNumber" v-model="certificationNumber" placeholder="certificationNumber" :disabled="!certification.isSend">
					<div v-if="certification.isSend && !certificationNumber" class="form-check">
						<font-awesome-icon icon="check-circle"/>
						<span> 이메일로 인증 번호를 전송했습니다.</span>
					</div>
					<div v-if="certification.isSend && certificationNumber" class="form-check form-error" id="check-certificationNumber">
						<font-awesome-icon icon="check-circle"/>
						<span> {{ certification.text }}</span>
					</div>
				</div>

				<div class="form-mb">
					<p class="from-item-text">닉네임</p>
					<input class="form-input" type="text" id="nickname" v-model="credentials.nickname" placeholder="nickname">
					<div v-show="credentials.nickname" class="form-check" id="check-nickname">
						<font-awesome-icon icon="check-circle"/>
						<span id="check-nickname-text"> 닉네임을 입력하세요.</span>
					</div>
				</div>

				<div class="form-mb">
					<p class="from-item-text">비밀번호</p>
					<input class="form-input" type="password" id="password" v-model="credentials.password" placeholder="password">
					<div v-if="error.password" class="text-error form-error">
						<font-awesome-icon icon="check-circle"/>
						<span> {{ error.password }}</span>
					</div>
				</div>

				<div class="form-mb">
					<p class="from-item-text">비밀번호 재입력</p>
					<input class="form-input" type="password" id="passwordConfirmation" v-model="credentials.passwordConfirmation" placeholder="password Confirmation">
					<div v-if="error.passwordConfirmation" class="text-error form-error">
						<font-awesome-icon icon="check-circle"/>
						<span> {{ error.passwordConfirmation }}</span>
					</div>
				</div>

				<button class="btn-user-mgt from-btn-mt" @click="signup(credentials)" :disabled="!isSubmit" :class="{ 'btn-user-mgt-disable' : !isSubmit }">Signup</button>
			</div>
		</div>
  </div>
</template>

<script>
import { mapActions } from 'vuex'
import axios from 'axios'
import { HTTP } from '@/util/http-common'

export default {
	name: 'SignUp',
	data() {
		return{
			credentials: {
				email: '',
				password: '',
				passwordConfirmation: '',
				nickname: '',
				profilePath: 'https://jubging-image.s3.ap-northeast-2.amazonaws.com/static/6b3424d5-d209-4cc6-9778-1ed48a0e56fbuser_default.png',
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
			certificationNumber: '',
			certification: {
				text: false,
				isSend: false,
				isCertificate: false,
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
		},
		certificationNumber() {
			this.certificationEmail()
		},
	},
	methods:{
		...mapActions([
			'signup',
		]),
		checkForm() {
      if (this.credentials.email.length >= 0 && !this.validEmail(this.credentials.email)) {
        this.error.email = " 올바른 이메일 형식이 아닙니다."
      } else {
        this.error.email = false
      }

      if (this.credentials.password.length >= 0 && !this.validPassword(this.credentials.password)) {
        this.error.password = " 영문, 숫자 포함 8 자리 이상이어야 합니다.";
      } else {
        this.error.password = false
      }

      if (this.credentials.passwordConfirmation.length >= 0 && this.credentials.password !== this.credentials.passwordConfirmation) {
        this.error.passwordConfirmation = " 비밀번호가 일치하지 않습니다.";
      } else {
        this.error.passwordConfirmation = false
      }

      axios.all([HTTP.post('user/emailck', this.credentials), HTTP.post('user/nicknameck', this.credentials)])
      .then(axios.spread((res1, res2) => {
        this.unique.email = res1.data.data
        if (!this.error.email) {
          if (this.unique.email) {
            document.querySelector('#check-email-text').innerText = " 이용 가능한 이메일 입니다."
            document.querySelector('#check-email').classList.remove('text-error')
          } else {
            document.querySelector('#check-email-text').innerText = " 중복된 이메일 입니다."
            document.querySelector('#check-email').classList.add('text-error')
         }
        }

				this.unique.nickname = res2.data.data
        if (this.unique.nickname) {
          document.querySelector('#check-nickname-text').innerText = " 이용 가능한 닉네임 입니다."
          document.querySelector('#check-nickname').classList.remove('text-error')
        } else {
          document.querySelector('#check-nickname-text').innerText = " 중복된 닉네임 입니다."
          document.querySelector('#check-nickname').classList.add('text-error')
        }
      }))
      .then(() => {
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
				// 백 인증 되면 
				// if (!this.certification.isCertificate) {
				// 	isSubmit = false
				// }
        this.isSubmit = isSubmit;
      })
      .catch(err => {
        console.error(err)
      })
    },
		validEmail(email) {
			var test = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      return test.test(email);
    },
		validPassword(password) {
			var test = /^.*(?=^.{8,15}$)(?=.*\d)(?=.*[a-zA-Z]).*$/;
			return test.test(password);
		},
		sendEmail() {
			const data = {
					'email': this.credentials.email
				}
      HTTP.post('/email/auth/', data)
      .then(() => {
        this.certification.isSend = true
      })
      .catch(err => {
        console.error(err)
      })
    },
		certificationEmail() {
			if (this.certificationNumber) {
				const data = {
					'authKey': this.certificationNumber,
					'email': this.credentials.email
				}
				HTTP.post('/email/authcheck/', data)
				.then(res => {
					if (res.data.data === '인증 성공') {
						this.certification.isCertificate = true
						this.certification.text = ' 인증이 완료되었습니다.'
						document.querySelector('#check-certificationNumber').classList.remove('text-error')
					} else {
						this.certification.isCertificate = false
						this.certification.text = ' 인증 번호가 틀렸습니다.'
						document.querySelector('#check-certificationNumber').classList.add('text-error')
					}
				})
				.then(() => {
					this.checkForm()
				})
				.catch(err => {
					console.error(err)
				})
			} else {
				this.certification.isCertificate = false
				this.checkForm()
			}
    },
		back() {
			this.$router.push({ name: 'Login' })
		}
	},
}
</script>

<style lang="scss" scoped>
@import "@/views/user/SignUp.scss";

</style>
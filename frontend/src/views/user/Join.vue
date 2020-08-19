<template>
  <v-form ref="joinForm" v-model="valid">
    <v-container>
      <v-row>
        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="email"
            :rules="emailRules"
            :counter="20"
            label="email"
            required
          ></v-text-field>
        </v-col>

        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="nickName"
            :rules="nameRules"
            :counter="10"
            label="NickName"
            required
            focusOut
            ref="inputNickname"
            
          >
          </v-text-field>
            <v-btn
              small
              absolute
              dark
              tile  
              right
              color="primary"
              :disabled="valid"
              @click="checkNickname">
              중복체크
            </v-btn>
          <v-spacer/>

        </v-col>

        <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="password"
            :rules="passwordRules"
            label="Password"
            type="password"
            required
          ></v-text-field>
        </v-col>

         <v-col
          cols="12"
          md="4"
        >
          <v-text-field
            v-model="passwordConfirm"
            :rules="passwordConfirmRules"
            label="PasswordConfirm"
            type="password"
            required
          ></v-text-field>
        </v-col>
            <v-btn
      :disabled="!valid"
      @click.prevent="onJoin()"
    >
      회원 가입
    </v-btn>
      </v-row>
    <br>
    <v-btn @click="onReset">초기화</v-btn>

    </v-container>

  </v-form>
</template>

<script>
import UserApi from '@/api/UserApi.js'

export default {
  data: () => {
    return {
      valid: true,
      nicknameDisable: false,
      msg: "",
      email: "",
      nickName: "",
      password: "",
      passwordConfirm: "",
      isLoading: false,
      error: {
        email: false,
        nickName: false,
        password: false,
        passwordConfirm: false,
        term: false
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      nameRules: [
        v => !!v || '닉네임을 입력해주세요.',
        v => (v && v.length <= 10) || '닉네임은 10글자 이하입니다.',
        v => !(v && v.length <= 10) || '중복검사가 필요합니다!'
      ],
      emailRules: [
        v => !!v || '이메일을 입력해주세요.',
        v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '유효하지 않은 이메일입니다.'
      ],
      passwordRules: [
        v => !!v || '패스워드를 입력해주세요.',
        // v => (v && v.length >= 8) || 'Password must be more than 8 characters'
        v => /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(v) || '비밀번호는 글자, 숫자 포함 8자 이상입니다.',
        // /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})/ : 소문자1+대문자1+특문1+8자 이상
      ],

    };
  },

  methods: {
    onJoin(){
      // 클릭단계에서 유효성 검증 이미 진행하였음 => :disable="valid"
      const singUpData = {
        nickname : this.nickName,
        email : this.email,
        password : this.password
      }
      this.$store.dispatch('user/join', singUpData)
      // 라우터 push도 user/join 진행
    },
    onReset() {
        this.$refs.joinForm.reset()
    },
    checkNickname() {
      UserApi.requestCheckNickname(
        this.nickName,
        res => {
          console.log(`응답 ${res}, ${res.data}, ${res.data.state}`)
          if(res.data.state === 'ok') {
            // 중복 검사 통과
            alert("사용가능한 아이디 입니다")
            this.nameRules = [
              v => !!v || '닉네임을 입력해주세요.',
              v => (v && v.length <= 10) || '닉네임은 10글자 이하입니다.'
            ]
          } else {
            alert("중복된 아이디 입니다")  
          }
        },
        err => {
          console.log(err)
          alert("중복검사 에러입니다")  
        }
      )
    }
  },
  computed: {
    passwordConfirmRules() {
      if (this.password === this.passwordConfirm) {
        return [ true ]
      } else {
        return [ "비밀번호가 일치하지 않습니다." ]
      }
    },
  },
  watch: {
    nickName() {
      this.nameRules = [
        v => !!v || '닉네임을 입력해주세요.',
        v => (v && v.length <= 10) || '닉네임은 10글자 이하입니다.',
        v => !(v && v.length <= 10) || '중복검사가 필요합니다!'
      ]
    }
  }
}
</script>
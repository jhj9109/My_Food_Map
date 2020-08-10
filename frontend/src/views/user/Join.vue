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
          ></v-text-field>
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

export default {
  data: () => {
    return {
      valid: true,
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
        v => !!v || 'NickName is required',
        v => (v && v.length <= 10) || 'Name must be less than 10 characters'
      ],
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || 'E-mail must be valid'
      ],
      passwordRules: [
        v => !!v || 'Password is required',
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
  },
  computed: {
    passwordConfirmRules() {
      if (this.password === this.passwordConfirm) {
        return [ true ]
      } else {
        return [ "비밀번호가 일치하지 않습니다." ]
      }
    },
  }
};
</script>
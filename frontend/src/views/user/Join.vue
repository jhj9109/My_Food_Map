
<!--
    가입하기는 기본적인 폼만 제공됩니다
    기능명세에 따라 개발을 진행하세요.
    Sub PJT I에서는 UX, 디자인 등을 포함하여 백엔드를 제외하여 개발합니다.
 -->
<template>
  <v-form v-model="valid">
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
            :rules="passwordRules"
            label="PasswordConfirm"
            type="password"
            required
          ></v-text-field>
        </v-col>
            <v-btn
      :disabled="!valid"
      @click="Join()"
    >
      회원 가입
    </v-btn>
      </v-row>
    <br>
    <v-btn>초기화</v-btn>

    </v-container>

  </v-form>
</template>

<script>
import { mapActions } from 'vuex';

export default {
  data: () => {
    return {
      valid: true,
      email: "",
      password: "",
      passwordConfirm: "",
      nickName: "",
      isTerm: false,
      isLoading: false,
      error: {
        email: false,
        password: false,
        nickName: false,
        passwordConfirm: false,
        term: false
      },
      signUpForm:{
                uid: '',
                email: '',
                password: '',
                nickname:'',
      },
      isSubmit: false,
      passwordType: "password",
      passwordConfirmType: "password",
      termPopup: false,
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
      v => (v && v.length >= 8) || 'Password must be more than 8 characters'
     ],


    };
  },

  methods: {
     ...mapActions({
      requestSignup:'user/Join'
    }),

    Join(){
     this.signUpForm.nickname = this.nickName
      this.signUpForm.email = this.email
      this.signUpForm.password = this.password
      console.log(this.signUpForm)
      this.requestSignup(this.signUpForm)
      this.$router.push('/user/login');
      
    },
  }
};
</script>



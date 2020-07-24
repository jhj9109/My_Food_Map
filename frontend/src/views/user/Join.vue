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
      @click.prevent="onJoin()"
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
      nickName: "",
      password: "",
      passwordConfirm: "",
      isTerm: false,
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
  }
};
</script>



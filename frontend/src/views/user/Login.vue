

<template>
  <div class="user" id="login">
     <v-app id="inspire">
      <v-content>
        <v-container
          class="fill-height"
          fluid
        >
          <v-row
            align="center"
            justify="center"
          >
            <v-col
              cols="12"
              sm="8"
              md="4"
            >

              <v-card class="elevation-12">
                <v-toolbar
                  color="primary"
                  dark
                  flat
                >
                  <v-toolbar-title> Login form</v-toolbar-title>
                  <v-spacer></v-spacer>
                  <v-tooltip bottom>
                    <template v-slot:activator="{ on }">
                      <v-btn
                        icon
                        large
                        target="_blank"
                        v-on="on"
                      >
                        <v-icon>mdi-code-tags</v-icon>
                      </v-btn>
                    </template>
                    <span>Source</span>
                  </v-tooltip>
                </v-toolbar>
                <v-card-text>
                  <v-form>
                    <v-text-field
                      label="Email"
                      name="email"
                      v-model="email"
                      :rules="emailRules"
                      placeholder="E-mail"
                      prepend-icon="mdi-account"
                      @keyup.enter="onLogin"
                      type="text"
                      outlined
                    ></v-text-field>
  
                    <v-text-field
                      id="password"
                      label="Password"
                      name="password"
                      v-model="password"
                      prepend-icon="mdi-lock"
                      :rules="passwordRules"
                      :counter="20"
                      placeholder="비밀번호"
                      outlined
                      @keyup.enter="onLogin"
                      type="password"
                    ></v-text-field>

                    <v-checkbox
                     light label='Stay logged in?'
                     hide-details
                     >
                    </v-checkbox>

                  </v-form>
                </v-card-text>
                <v-card-actions>
                <v-spacer/>
                  <v-btn color="primary"
                  tile
                    @click="onLogin">
                  Login</v-btn>
                </v-card-actions>
              </v-card>
              <br><br>
              
          Don`t have an account?
          <v-btn
           to="/user/join"
           >
          회원가입
          </v-btn>
          <br><br>
            Don`t remember password?
            <v-btn
            to="/user/findpw"
            >
            비밀번호 찾기
            </v-btn>
            </v-col>
            
          </v-row>


        </v-container>
      </v-content>
    </v-app>
  </div>
</template>



<script>
// import http from '../../util/http-common';

export default {
    data: () => {
    return {
      info:{},
      email: "",
      emailRules:[
        v => !!v || '이메일을 입력해주세요.',
        v => /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/.test(v) || '이메일 형식을 지켜주세요'
      ],
      password: "",
      passwordRules:[
        v => !!v || '비밀번호를 입력해주세요',
        // v => /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(v) || '비밀번호는 글자, 숫자 포함 8자 이상입니다.',
      ],
      error: {
        email: false,
        passowrd: false
      },
      isSubmit: false,
      component: this
    };
  },

  methods:{
    onLogin(){
      console.log("데이터 넣기", this.email, this.password)
      const loginData = {
        email: this.email,
        password: this.password
      }
      console.log("데이터", loginData)
      this.$store.dispatch('user/login', loginData)
    }
  },


};
</script>



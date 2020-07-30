<template>
  <div>
    <h1>User Profile Page</h1>
    <v-card
      max-width="600"
      min-height="120"
      class="mx-auto mb_custom"
      outlined="outlined"
      style="margin-top:15px;">
      <v-card-actions>
        <v-list-item>
          <v-icon
            size="70"
            color="blue"
            style="margin:8px 16px 8px 0px;padding:0px;">mdi-account</v-icon>
          <v-list-item-content>
            <v-list-item-title class="body-1">userInfo.nickname 님</v-list-item-title>
            <v-list-item-subtitle>이메일 : userInfo.email</v-list-item-subtitle>
          </v-list-item-content>
          <v-list-item-action style="width:30%;">
            <v-btn
              depressed="depressed"
              class="ma-2 widfull"
              outlined="outlined"
              color="primary">
              <v-icon left="left">mdi-pencil</v-icon>
              정보 수정
            </v-btn>
            <v-btn
              depressed="depressed"
              @click="logout()"
              class="ma-2 widfull"
              outlined="outlined"
              color="primary">
              <v-icon left="left">mdi-pencil</v-icon>
              비밀번호 수정
            </v-btn>
          </v-list-item-action>
        </v-list-item>
      </v-card-actions>
      <v-layout align-space-around justify-space-around row fill-height>
        <v-subheader><v-btn text large color="error">Follower</v-btn>: 0 <hr class="sero"> <v-btn text large color="error">Following</v-btn> : 0</v-subheader>
        <v-btn class="mx-2" fab dark small color="primary">
          <v-icon dark @click="follow">mdi-plus</v-icon>
        </v-btn>
      </v-layout>
    </v-card>
  </div>
</template>

<script>
export default {
  name: 'Profile',
  data() {
    return {
      userInfo: [],
    }
  },
  methods: {
    fetchProfile() {
      console.log('프로필 페이지')
    //   const API_USER_INFO_URL = API_BASE_URL + '/user/'
    //   const config = {}
    //   http.get(API_USER_INFO_URL, config)
    //     .then(res => {
    //       this.userInfo = res.data
    //     })
    //     .catch(err => {
    //       console.error(err)
    //     })
    },
    follow() {
      console.log('팔로우')
    },
  },
  created() {
    this.fetchProfile()
  }
}
</script>

<style>

</style>
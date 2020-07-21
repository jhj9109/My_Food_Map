import UserApi from '@/api/UserApi'
import router from '../main'
import http from "@/util/http-common";
const axios = require('axios').default

const state = {
  
    userInfo : {}, 
    id: localStorage.getItem("id")
}

const getters = {
  isLogin: function(state){
    var id = state.id
    if(id==null){
      return false
    }else{
      return true
    }
  }
}

const actions = {
  getUser(context, payload) {
    http.post(payload).then(({ data }) => {
      context.commit("setUserInfo", data);
    });
  },
  getUserInfo({ commit }){
    UserApi.requestUserInFo(res=>{
      if (res.data.state === 'ok'){
        commit('setUserInfo', res.data.message)
      }else{
        console.log(`유저 정보 조회 실패 : ${res.data.message}`)
      }
    },error=>{
      console.log(error)
    })
  },
    login({  },data){
      return UserApi.requestLogin(data,res=>{
          alert('로그인에 성공하였습니다.')
          console.log(data);
          localStorage.clear();
          localStorage.setItem('id', data.email);
      });
    },
  
    Join({ commit },data){
      UserApi.requestSignup(data,
        res=>{
          if(res.data.state === 'ok'){
            alert('회원가입에 성공하셨습니다.')
            router.push({name:'Login'})
          }else{
            alert(res.data.message)
            console.log(res)
          }
        },err=>{
          console.log(err)
        })
    },
}

const mutations = {
    getId (state) {
      state.id = localStorage.getItem("id")
    },
    delId (state) {
      localStorage.removeItem("id")
      state.id = null
    },
    setUserInfo(state, data){
      state.userInfo = data
      // console.log('성공', state.userInfo)
    },
}

export default {
    namespaced: true,
    state,
    getters,
    actions,
    mutations
  }
  
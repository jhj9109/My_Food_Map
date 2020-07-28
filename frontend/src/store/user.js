import UserApi from '@/api/UserApi'
import router from '../main'
// import http from "@/util/http-common";
// const axios = require('axios').default

export default {
    namespaced: true,

    state: {
      userInfo : {}, 
      id: localStorage.getItem("id"),
    },
    
    getters: {
      isLogin: function(state){
        var id = state.id
        if(id==null){
          return false
        }else{
          return true
        }
      },
    },
    
    mutations: {
      getId (state) {
        state.id = localStorage.getItem("id")
        console.log('getId 성공', state.id)
      },
      delId (state) {
        localStorage.removeItem("id")
        state.id = null
        console.log('delId 성공', state.id)
      },
      setUserInfo(state, data){
        state.userInfo = data
        console.log('setUserInfo 성공', state.userInfo)
      },
    },
    
    actions: {
      getUserInfo({ commit }){
        UserApi.requestUserInFo(res=>{
          if (res.data.userid != null){
            commit('setUserInfo', res.data)
          }else{
            console.log(`유저 정보 조회 실패 : ${res.data}`)
          }
        },error=>{
          console.log(error)
        })
      },
      join(context , data) {
        console.log(context)
        UserApi.requestSignup(
          data,
          res => {
            console.log("res=>", res)
            if(res.data.state === 'ok'){
              // res.data  : message:"회원가입에 성공하셨습니다.", status: "ok"
              alert('회원가입에 성공하셨습니다.')
              router.push({name:'Login'})
            } else {
              alert(res.data.message)
            }
          },
          err => {
            console.error(err)
          }
        )
      },
      login(context , data) {
        console.log("login 진입", data)
        console.log(context)
        UserApi.requestLogin(
          data,
          res => {
            console.log("res =>", res)
            if(!!res.data.userid) {
              // res.data : createDate, email, nickname, password, userid
              alert('로그인에 성공하였습니다.')
              console.log("성공, res.data =>", res.data)
              
              // 임시로 이메일 저장
              localStorage.token = res.data.email
              // 로그인 후 Map으로 일괄 이동 (나중에 수정 요구됨)
              router.push( {name : 'Map'})
            } else {
              console.log("실패, res.data =>", res.data)
              alert(res.data.message || '로그인에 실패하였습니다')
            }
          },
          err => {
            console.log("에러")
            console.error(err)
          }
        )
      },
      logout(context , header) {
        console.log(context)
        UserApi.requestLogout(
          header,
          res => {
            if(res.data.status === 'ok') {
              console.log("서버의 토큰도 삭제 되었습니다.")
            } else {
              console.log("서버의 토큰 삭제엔 실패했습니다.")
            }
          },
          err => {
            console.error(err)
          }
        )
        delete localStorage.token
        router.push({ name : 'Login'})
      },      
    },
  }
  
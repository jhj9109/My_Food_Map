import UserApi from '@/api/UserApi'
import router from '../main'
// import http from "@/util/http-common";
// const axios = require('axios').default

export default {
  name: "user",
  namespaced: true,

  state: {
    userInfo : null,
    token : false 
  },
    
  mutations: {
    setUserInfo(state, data){
      state.userInfo = data
      console.log('스토어의 setUserInfo 성공', state.userInfo)
    },
    setToken (state, token) {
      state.token = token
      console.log('스토어의 setToken 성공', state.token)
    }
  },
    
  actions: {
    getUserInfo({ commit }, userId){
      UserApi.requestUserInfo(
        userId,
        res => {
          if (userId != null) {
            commit('setUserInfo', res.data)
          } else {
            console.log(`유저 정보 조회 실패 : ${res.data}`)
          }
        },
        error => {
          console.log(error)
        }
      )
    },
    join(context , data) {
      console.log(context)
      UserApi.requestSignup(
        data,
        res => {
          console.log("res=>", res)
          if(res.data.state === "ok") {
            // res.data  : message:"회원가입에 성공하셨습니다.", status: "ok"
            alert('회원가입에 성공하셨습니다.')
            router.push({name:'Login'})
          } else {
            alert(res.data.message || "회원가입에 실패하였습니다.")
          }
        },
        err => {
          console.error(err)
          router.push( {name : 'ErrorPage'})
        }
      )
    },
    login({commit} , data) {
      UserApi.requestLogin(
        data,
        res => {
          console.log("login후 res =>", res)
          if(res.data.userid) {

            // res.data : createDate, email, nickname, password, userid
            
            const userData = {
              userId: res.data.userid,
              email: res.data.email,
              nickname: res.data.nickname,
            }
            
            // 로컬 스토리지와 Vue에 각각 저장
            localStorage.token = res.data.email // 임시로 이메일 저장
            localStorage.setItem('userInfo', JSON.stringify(userData))
            commit('setToken', res.data.email) // 임시로 이메일 저장
            commit('setUserInfo', userData)

            console.log(localStorage.token, localStorage.userInfo)
            alert('로그인에 성공하였습니다.')

            // 로그인 후 Map으로 일괄 이동 (나중에 수정 요구됨)
            router.push( {name : 'Map'})
          } else {
            console.log("실패, res.data =>", res.data)
            alert(res.data.message || '로그인에 실패하였습니다')
          }
        },
        err => {
          console.error(err)
          router.push( {name : 'ErrorPage'})
        }
      )
    },

    logout({commit} , header) {
      console.log("유저정보와 토큰 삭제합니다")
      delete localStorage.token
      delete localStorage.userInfo
      commit('setUserInfo', null)
      commit('setToken', "")
      
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
      // Logout은 로컬의 데이터는 모두 지웠으므로, 통신 에러가 나더라도 Login페이지로 이동
      // 히스토리 남기지 않기 위해 replace 사용
      router.replace({name : 'Login'})
    },      
  },
}
  
const axios = require('axios')
// const token = `Bearer ${localStorage.getItem('JWT')}`
// axios.defaults.headers.common['Authorization'] = token

// const hosturl = 'http://localhost:9999'
const hosturl = 'http://i3a409.p.ssafy.io:8399'
const appname = '/user'

const refreshAuthToken = () => {
    const AuthToken = `Bearer ${localStorage.getItem('JWT')}`
    axios.defaults.headers.common['Authorization'] = AuthToken
}

const requestSignup = (data, callback, errorCallback) => {
    console.log("Signup 요청 : ", hosturl+appname+'/join', data)
    axios.post(hosturl+appname+'/join', data)
    .then(callback)
    .catch(errorCallback)
}

const requestLogin = (data,callback,errorCallback) => {
    console.log("Login 요청 : ", hosturl+appname+'/login', data)
    axios.post(hosturl+appname+'/login', data)
    .then(callback)
    .catch(errorCallback)
}

const requestLogout = (data,callback,errorCallback) => {
    console.log("Logout 요청 : ", hosturl+appname+'/logout', data)
    refreshAuthToken()
    axios.post(hosturl+appname+'/logout', data)
    .then(callback)
    .catch(errorCallback)
}

const requestUserInfo = (data, callback, errorCallback) => {
    console.log(`UserInfo 요청 : ${hosturl}${appname}/${data.id}/${data.userId}`)
    axios.get(`${hosturl}${appname}/${data.id}/${data.userId}`)
    .then(callback)
    .catch(errorCallback)
}

const requestFollow = (data, callback, errorCallback) => {
    refreshAuthToken()
    console.log("Follow 요청 :", hosturl+appname+'/follow', data)
    axios.post(hosturl+appname+'/follow', data)
    .then(callback)
    .catch(errorCallback)
}

const UserApi = {
    requestSignup:(data,callback,errorCallback)=>requestSignup(data,callback,errorCallback),
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
    requestLogout:(data,callback,errorCallback)=>requestLogout(data,callback,errorCallback),
    requestUserInfo:(userId,callback,errorCallback)=>requestUserInfo(userId,callback,errorCallback), 
    requestFollow:(data, callback, errorCallback)=>requestFollow(data, callback, errorCallback),
}

export default UserApi

/*
 User API 예시
 */

const axios = require('axios')
const token = `Bearer ${localStorage.getItem('JWT')}`
axios.defaults.headers.common['Authorization'] = token

const hosturl = 'http://localhost:9999'
const appname = '/user'

const requestSignup = (data, callback, errorCallback) => {
    console.log("requestSignup data=>" + data)
    axios.post(hosturl+appname+'/join', data)
    .then(callback)
    .catch(errorCallback)
}

const requestLogin = (data,callback,errorCallback) => {
    console.log("requestLogin data =>" + data)
    axios.post(hosturl+appname+'/login', data)
    .then(callback)
    .catch(errorCallback)
}

const requestLogout = (data,callback,errorCallback) => {
    console.log("requestLogout data =>" + data)
    console.log("data.token값은" + data.token)
    axios.post(hosturl+appname+'/logout', data)
    .then(callback)
    .catch(errorCallback)
}

const requestUserInfo = (userId, callback, errorCallback) => {
    axios.get(hosturl+appname+'/'+userId)
    .then(callback)
    .catch(errorCallback)
}

// const requestUserInfo = (callback, errorCallback) => {
//     axios.get(hosturl+appname+'/'+localStorage.getItem('userInfo').userId)
//     .then(callback)
//     .catch(errorCallback)
// }

const UserApi = {
    requestSignup:(data,callback,errorCallback)=>requestSignup(data,callback,errorCallback),
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
    requestLogout:(data,callback,errorCallback)=>requestLogout(data,callback,errorCallback),
    requestUserInfo:(userId,callback,errorCallback)=>requestUserInfo(userId,callback,errorCallback), 
    // requestUserInfo:(callback,errorCallback)=>requestUserInfo(callback,errorCallback), 
}

export default UserApi

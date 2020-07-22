/*
 User API 예시
 */

const axios = require('axios')
const token = `Bearer ${localStorage.getItem('JWT')}`
axios.defaults.headers.common['Authorization'] = token

const hosturl = 'http://localhost:9999'
const appname = '/user'

const requestLogin = (data,callback,errorCallback) => {
    console.log(data)
    axios.post(hosturl+appname+'/login', data)
    .then(callback)
    .catch(errorCallback)

}
const requestSignup = (data, callback, errorCallback) => {
    console.log(data)
    axios.post(hosturl+appname+'/join', data)
    .then(callback)
    .catch(errorCallback)
}
const requestUserInFo = (callback, errorCallback) => {
    axios.get(hosturl+appname+'/'+localStorage.getItem('id'))
    .then(callback)
    .catch(errorCallback)
}



const UserApi = {
    requestLogin:(data,callback,errorCallback)=>requestLogin(data,callback,errorCallback),
    requestSignup:(data,callback,errorCallback)=>requestSignup(data,callback,errorCallback),
    requestUserInFo:(callback,errorCallback)=>requestUserInFo(callback,errorCallback),
 
}

export default UserApi

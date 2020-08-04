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
const requestFollow = (data, callback, errorCallback) => {
    refreshAuthToken()
    axios.post(hosturl+appname+'/follow', data)
    .then(callback)
    .catch(errorCallback)
}


const requestSignup = (data, callback, errorCallback) => {
    console.log(hosturl)
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
    refreshAuthToken()
    axios.post(hosturl+appname+'/logout', data)
    .then(callback)
    .catch(errorCallback)
}

const requestUserInfo = (userId, callback, errorCallback) => {
    axios.get(hosturl+appname+'/'+userId)
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

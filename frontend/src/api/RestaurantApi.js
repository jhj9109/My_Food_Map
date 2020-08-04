const axios = require('axios')
// const hosturl = 'http://localhost:9999'
const hosturl = 'http://i3a409.p.ssafy.io:8399'
const appname = '/restaurants'

const setAuthToken = () => {
    const AuthToken = `Bearer ${localStorage.getItem('JWT')}`
    axios.defaults.headers.common['Authorization'] = AuthToken
}
const requestLike = (data, callback, errorCallback) => {
    setAuthToken()
    axios.post(hosturl+appname+'/like', data) // userId, restaurantId
    .then(callback)
    .catch(errorCallback)
}
const requestList = (callback, errorCallback) => {
    axios.post(hosturl+appname)
    .then(callback)
    .catch(errorCallback)
}

const requestInfo = (restaurantId, callback, errorCallback) => {
    console.log(hosturl+appname+'/'+restaurantId)
    axios.get(hosturl+appname+'/'+restaurantId)
    .then(callback)
    .catch(errorCallback)
}

const requestCreate = (data, callback, errorCallback) => {
    setAuthToken()
    axios.post(hosturl+appname+'/create', data)
    .then(callback)
    .catch(errorCallback)
}
const requestReviews = (restaurantId, callback, errorCallback) => {
    console.log(hosturl+appname+'/'+restaurantId+'/reviews')
    axios.get(hosturl+appname+'/'+restaurantId+'/reviews')
    .then(callback)
    .catch(errorCallback)
}

const RestaurantApi = {
    requestLike:(data,callback,errorCallback)=>requestLike(data,callback,errorCallback),
    requestList:(callback,errorCallback)=>requestList(callback,errorCallback),
    requestInfo:(restaurantId,callback,errorCallback)=>requestInfo(restaurantId,callback,errorCallback),
    requestCreate:(data,callback,errorCallback)=>requestCreate(data,callback,errorCallback),
    requestReviews:(restaurantId,callback,errorCallback)=>requestReviews(restaurantId,callback,errorCallback),
}

export default RestaurantApi

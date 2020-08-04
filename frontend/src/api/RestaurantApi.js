const axios = require('axios')
// const hosturl = 'http://localhost:9999'
const hosturl = 'http://i3a409.p.ssafy.io:8399'
const appname = '/restaurant'

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
    axios.get(hosturl+appname+'/list')
    .then(callback)
    .catch(errorCallback)
}

const requestInfo = (restaurantId, callback, errorCallback) => {
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

const RestaurantApi = {
    requestLike:(data,callback,errorCallback)=>requestLike(data,callback,errorCallback),
    requestList:(callback,errorCallback)=>requestList(callback,errorCallback),
    requestInfo:(RestaurantId,callback,errorCallback)=>requestInfo(RestaurantId,callback,errorCallback),
    requestCreate:(data,callback,errorCallback)=>requestCreate(data,callback,errorCallback),
}

export default RestaurantApi

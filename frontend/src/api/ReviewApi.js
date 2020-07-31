const axios = require('axios')
const hosturl = 'http://localhost:9999'
const appname = '/reviews'

const setAuthToken = () => {
    const AuthToken = `Bearer ${localStorage.getItem('JWT')}`
    axios.defaults.headers.common['Authorization'] = AuthToken
}
const requestLike = (data, callback, errorCallback) => {
    setAuthToken()
    axios.post(hosturl+appname+'/like', data)
    .then(callback)
    .catch(errorCallback)
}
const requestReviewList = (callback, errorCallback) => {
    axios.get(hosturl+appname+'/list')
    .then(callback)
    .catch(errorCallback)
}

const ReviewApi = {
    requestLike:(data,callback,errorCallback)=>requestLike(data,callback,errorCallback),
    requestReviewList:(callback,errorCallback)=>requestReviewList(callback,errorCallback),
}

export default ReviewApi

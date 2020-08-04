const axios = require('axios')
// const hosturl = 'http://localhost:9999'
const hosturl = 'http://i3a409.p.ssafy.io:8399'
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

const requestReviewInfo = (reviewId, callback, errorCallback) => {
    axios.get(hosturl+appname+'/'+reviewId)
    .then(callback)
    .catch(errorCallback)
}

const requestCreate = (data, callback, errorCallback) => {
    setAuthToken()
    axios.post(hosturl+appname+'/create', data)
    // data : userId, review (place, content, rank, image(name, url, file))
    .then(callback)
    .catch(errorCallback)
}

const ReviewApi = {
    requestLike:(data,callback,errorCallback)=>requestLike(data,callback,errorCallback),
    requestReviewList:(callback,errorCallback)=>requestReviewList(callback,errorCallback),
    requestReviewInfo:(reviewId,callback,errorCallback)=>requestReviewInfo(reviewId,callback,errorCallback),
    requestCreate:(data,callback,errorCallback)=>requestCreate(data,callback,errorCallback),
}

export default ReviewApi


import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import Mypage from './views/user/Mypage.vue'
import Findpw from './views/user/Findpw.vue'

import Map from './views/map/map.vue'

import Create from './views/review/Create.vue'

/*
const checkToken = function(token){
    return token===null || token==='';
}
const  ifNotAuthenticated = (to, from, next) => {
   var token = localStorage.JWT
   console.log(token)
   if (checkToken(token)) {
    return next({name:'Login'})
   }
   return next()
 }

 const  ifAuthenticated = (to, from, next) =>{
     var token = localStorage.JWT
     console.log(token)
     if(!checkToken(token)){
        return next({name:'FeedMain'})
    }
    return next()
 } 

 */


export default [

    {
        path : '/user/login',
        name : 'Login',
        component : Login,
    },
    {
        path : '/user/join',
        name : 'Join',
        component : Join
    },
    {
        path : '/user/mypage',
        name : 'Mypage',
        component : Mypage
    },
    {
        path : '/user/findpw',
        name : 'Findpw',
        component : Findpw
    },
    {
        path : '/map/map',
        name : 'Map',
        component : Map
    },
    {
        path : '/review/create',
        name : 'Create',
        component : Create
    },   
]

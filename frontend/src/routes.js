
import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'


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
   
]


import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import Mypage from './views/user/Mypage.vue'
// import UserPage from './views/user/UserPage.vue'
import Profile from './views/user/Profile.vue' // 경민버전
import Findpw from './views/user/Findpw.vue'
import Logout from './views/user/Logout.vue'

import Map from './views/map/map.vue'

import Create from './views/review/Create.vue'
import Restaurant from './views/review/Restaurant.vue'
import RestaurantReview from './views/review/RestaurantReview.vue'

import ReviewList from './views/review/ReviewList.vue'

import ErrorPage from './views/exception/Error.vue'
import NotFound from './views/exception/NotFound.vue'

import IndexFeed from './views/feed/IndexFeed.vue'
import Components from './views/Components.vue'



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
		path : '/',
		redirect : { name : 'Map'}
	},
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
		path: '/user/logout',
		name: 'Logout',
		component : Logout,
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
		path : '/user/:userId',
		name : 'Profile',
		component : Profile
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
	{
		path : '/restaurant',
		name : 'Restaurant',
		component : Restaurant
	},
	{
		path : '/restaurant/review',
		name : 'RestaurantReview',
		component : RestaurantReview
	},
    {
        path : '/review/list',
        name : 'ReviewList',
        component : ReviewList,
    },
	{
		path : '/error',
		name : 'ErrorPage',
		component : ErrorPage
	},
	{
		path : '/components',
		name : 'Components',
		component : Components
	},
	{
		path : '/IndexFeed',
		name : 'IndexFeed',
		component : IndexFeed
	},
	{
		path : '*',
		name : 'NotFound',
		component : NotFound
	},
]
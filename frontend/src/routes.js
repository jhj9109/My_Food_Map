
import Login from './views/user/Login.vue'
import Join from './views/user/Join.vue'
import Mypage from './views/user/Mypage.vue'
// import UserPage from './views/user/UserPage.vue'
import Profile from './views/user/Profile.vue' // 경민버전
import Findpw from './views/user/Findpw.vue'
import Logout from './views/user/Logout.vue'

import Map from './views/map/map.vue'


import Restaurant from './views/restaurant/Restaurant.vue'
import RestaurantDetail from './views/restaurant/RestaurantDetail.vue'
import RestaurantReview from './views/restaurant/RestaurantReview.vue'

import ReviewList from './views/review/ReviewList.vue'
import ReviewCreate from './views/review/ReviewCreate.vue'

import ErrorPage from './views/exception/Error.vue'
import NotFound from './views/exception/NotFound.vue'

import IndexFeed from './views/feed/IndexFeed.vue'
import Components from './views/Components.vue'

import TestVue from './views/testvue/TestVue.vue'


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
		path : '/restaurant',
		name : 'Restaurant',
		component : Restaurant
	},
	{
		path : '/restaurant/:restaurantId',
		name : 'RestaurantDetail',
		component : RestaurantDetail
	},
	{
		path : '/restaurant/:restaurantId/review',
		name : 'RestaurantReview',
		component : RestaurantReview
	},

	{
		path : '/review/create',
		name : 'ReviewCreate',
		component : ReviewCreate
	},
    {
        path : '/review',
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
		path : '/test',
		name : 'TestVue',
		component : TestVue
	},
	{
		path : '*',
		name : 'NotFound',
		component : NotFound
	},
]
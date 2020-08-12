<template>
	<v-card height="56px">
		<v-app-bar
			color="backgroud_gradient"
			dark
			fixed
			scroll-target="#scrolling-techniques-3"
		>
			<v-btn to="/restaurant" class="ml-10 title" icon>
			<v-toolbar-title class="ml-4"> My Food Diary </v-toolbar-title>
			</v-btn>


			<v-spacer></v-spacer>
			<!-- 로그인 디자인을 위해 일단 제거 밑에 있어서 없어도 될 것 같기도
			<div v-if="userInfo">
			<v-list-item>
					<v-icon size="50" color="grey" style="margin:8px 16px 8px 0px;padding:0px;">mdi-account-circle</v-icon>
					<v-list-item-content>
							<v-list-item-title class="body-1">{{userInfo.nickname}}님</v-list-item-title>
					</v-list-item-content>
					<v-list-item-action style="width:30%; margin-right:10px">
							<v-btn
									depressed="depressed"
									to = "/user/logout"
									class="ma-2 widfull"
									outlined="outlined"
									color="white">
									로그아웃
							</v-btn>
					</v-list-item-action>
			</v-list-item>
			</div>
			<div v-else>
			<v-btn
					depressed="depressed"
					to = "/user/login"
					class="widfull"
					outlined="outlined"
					color="white">
					로그인
			</v-btn>
			</div>
			-->
			<!-- drawer dots로 옮김 -->
			<v-btn 
				@click.native.stop="drawerToggle = true"
				icon style="margin-top:1px">
				<v-icon>mdi-dots-horizontal</v-icon>
			</v-btn>
			<!-- drawer dots로 옮김 -->
			<v-badge
				dot
				style="margin-top:1px"
			>
			<!--:value="userInfo.알람==true"
			넣어서 새로운 알람 있을때 불들어오게-->
				<v-icon>mdi-bell</v-icon>
			</v-badge>
			<!--
			<template v-slot:extension>
				<v-tabs 
				fixed-tabs
				>
					<v-tab :to="{name : 'Map'}">Map</v-tab>
					<v-tab :to="{name : 'Restaurant'}">Home</v-tab>
					<v-tab v-if="userInfo"
						:to="{name : 'Profile', params : {userId : userInfo.userId} }">Feed
					</v-tab>
					<v-tab v-else
						:to="{name : 'Login'}">Feed
					</v-tab>
				</v-tabs>
			</template>
			-->
		</v-app-bar>

		<v-navigation-drawer
			v-model="drawerToggle"
			fixed
			temporary
			right
			width="250"
		>
			<v-list-item>
				<v-list-item-avatar>
					<!-- 아이콘 수정 -->
					<v-icon size="40">mdi-account-circle</v-icon>
				</v-list-item-avatar>
				
				<v-list-item-content v-if="userInfo">
					<v-list-item-title>{{ userInfo.nickname }}</v-list-item-title>
				</v-list-item-content>
				<!-- 비로그인 분기 -->
				<v-list-item-content v-else>
					<v-list-item-title class="text-caption"> 로그인이 필요합니다. </v-list-item-title>
				</v-list-item-content>
			</v-list-item>

			<v-divider></v-divider>

			<v-list dense>
				
				<v-list-item
					v-for="item in items"
					:key="item.title"
					link
					:to="{name : item.destination}"
				>
					<v-list-item-icon>
						<v-icon>{{ item.icon }}</v-icon>
					</v-list-item-icon>

					<v-list-item-content>
						<v-list-item-title>{{ item.title }}</v-list-item-title>
					</v-list-item-content>
				</v-list-item>
			</v-list>
		<!-- 드로워 서비스 소개 추가 -->
		<v-col
			class="backgroud_gradient py-2 text-center white--text"
			cols="12"
		>
			ⓒ {{ new Date().getFullYear() }} — MFD by MiBaSi
		</v-col>
		</v-navigation-drawer>
	</v-card>
</template>

<script>

export default {
  name: 'NavBar',
  props: ['userInfo', 'items'],
  data() {
      return {
          drawerToggle: false
      }
  },
}
</script>
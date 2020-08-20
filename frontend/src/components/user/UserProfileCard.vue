<template>
    <v-card
        max-width="374"
        min-height="120"
        class="mx-auto mb_custom"
        outlined="outlined"
        style="margin-top:15px;"
    >
        <v-card-actions>
        <v-list-item>
            <!-- 아이콘 디자인 변경, 위치 이동 사진 추가 유무에 따른 분기 필요-->
            <v-icon
                size="70"
                color="#F7B675"
                style="margin:4px 4px 0px -16px;">mdi-account-circle
            >
            </v-icon>
            <v-list-item-content>
            <v-list-item-title class="body-1">
                {{profileUser.nickname}}님
            </v-list-item-title>
            <!-- 팔로워 팔로잉 변경 -->
            <v-list-item-subtitle class="mb-1"
            >
              <v-dialog v-model="dialog" scrollable max-width="300px">
                <template v-slot:activator="{ on, attrs }">
                  <div v-bind="attrs"
                  v-on="on" @click="fetchFollowList()">
                    팔로워: {{profileUser.follower}}
                  </div>
                </template>
                <v-card>
                  <v-card-title>Follow List</v-card-title>
                  <v-divider></v-divider>
                  <v-tabs
                    v-model="tab"
                    centered
                    >
                    <v-tab>Follower</v-tab>
                    <v-tab>Following</v-tab>
                    <v-tab-item>
                        <v-list>
                            <v-list-item
                                v-for="follower in follower_list"
                                :key="follower.no"
                            >
                                <!-- 프로필 사진 -->
                                <v-list-item-avatar>
                                    <v-img :src="follower.image"></v-img>
                                </v-list-item-avatar>
                                <v-list-item-content>
                                    <v-list-item-title v-text="follower.nickname"></v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </v-list>
                    </v-tab-item>
                    <v-tab-item>
                        <v-list>
                            <v-list-item
                                v-for="following in following_list"
                                :key="following.no"
                            >
                                <!-- 프로필 사진 -->
                                <v-list-item-avatar>
                                    <v-img :src="following.image"></v-img>
                                </v-list-item-avatar>
                                <v-list-item-content>
                                    <v-list-item-title v-text="following.nickname"></v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </v-list>
                    </v-tab-item>
                  </v-tabs>
                  <v-card-text style="height: 300px;">
                  </v-card-text>
                  <v-divider></v-divider>
                  <v-card-actions>
                    <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
                팔로워: {{profileUser.follower}}
                팔로잉: {{ profileUser.following }}
            </v-list-item-subtitle>
            <!-- 이메일 -> 자기소개로 변경  -->
            <v-list-item-subtitle
            >
                {{profileUser.email}}
            </v-list-item-subtitle>
            </v-list-item-content>
        </v-list-item>
        
        <!-- 팔로워/팔로잉 디자인 수정 팔로우 했을 경우 팔로우 취소로 변경-->
        </v-card-actions>
        <v-layout class="mb-3 mr-3 justify-end">
            <!--
            <v-subheader>
            <v-btn text large color="error">Follower: {{ profileUser.follower }} </v-btn> 
            <v-btn text large color="error">Following: {{ profileUser.following }} </v-btn> 
            </v-subheader>
            -->
            <v-btn
                v-if="!userInfo || profileUser.id !== userInfo.userId"
                @click="onClick"
                dark
                small
                color=#F7B675
            >
                팔로우
            </v-btn>
        </v-layout>
    </v-card>
</template>

<script>
// import { mapState } from 'vuex'
import UserApi from '@/api/UserApi.js'

export default {
    data() {
        return {
            follower_list: [],
            following_list: [],
            dialog: false,
        }
    },
    props: [ 'profileUser', 'userInfo'],
    methods: {
        onClick() {
            console.log("onClick 반응")
            if (!this.userInfo) {
                this.$router.push({name:'Login', query:{ redirect: 'Profile', params: {nickname: this.profileUser.nickname}}})
            } else if (this.profileUser.id !== this.userInfo.userId) {
                console.log("onFollow emit")
                this.$emit('onFollow')
            }
        },
        fetchFollowList() {
            const userId = this.profileUser.id
            
            UserApi.requestFollowerList(
                userId,
                res => {
                    console.log("realSetData 콜백 성공, res:", res.data.message)
                    this.follower_list = res.data.message
                },
                err => {
                    console.error(err)
                    console.log("에러반응")
                }
            )
            UserApi.requestFollowingList(
                userId,
                res => {
                    console.log("realSetData 콜백 성공, res:", res.data.message)
                    this.follower_list = res.data.message
                },
                err => {
                    console.error(err)
                    console.log("에러반응")
                }
            )
        }
    },
    // computed: {
    //     ...mapState({
    //         userInfo: state => state.user.userInfo,    
    //     })
    // }
}
</script>

<style>

</style>
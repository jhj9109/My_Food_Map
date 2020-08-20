<template>
    <v-card
        max-width="374"
        min-height="120"
        class="mx-auto"
        outlined="outlined"
        style="margin-top:15px;"
    >
        <v-list-item three-line>
            <v-list-item-content>
                <div class="overLine mb-4"></div>
                <v-list-item-title class="headline mb-1"> {{profileUser.nickname}} 님 </v-list-item-title>
                <v-list-item-subtitle> 일반 - {{profileUser.email}}</v-list-item-subtitle>
            </v-list-item-content>

            <v-list-item-avatar size="80" style="margin-right:10px">
                <img
                   :src="profileUser.image"
                   alt="프로필 사진"
                   >
            </v-list-item-avatar>
        </v-list-item>

        <v-card-actions>
            <v-dialog v-model="dialog" scrollable max-width="300px">
                <template v-slot:activator="{ on, attrs }">
                  <div v-bind="attrs"
                  v-on="on" @click="fetchFollowList()">
                    <v-btn style="margin-bottom:20px;" depressed color="warning">Follower :{{profileUser.follower}}</v-btn> 
                    <v-btn style="margin-left:70px;margin-bottom:20px;" depressed color="warning">Following :{{profileUser.following}}</v-btn> 
                  </div>
                </template>
                <v-card>
                  <v-card-title>Follow List</v-card-title>
                  <v-divider></v-divider>
                  <v-tabs
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
                                <v-list-item-action>
                                    <v-btn
                                        v-if="!userInfo || follower.id !== userInfo.userId"
                                        @click="onClick"
                                        dark
                                        small
                                        color=#F7B675
                                    >
                                                        {{ profileUser.followed ? '언팔로우' : '팔로우' }}
                                    </v-btn>
                                </v-list-item-action>
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
                  <v-card-text style="height: 200px;">
                  </v-card-text>
                  <v-divider></v-divider>
                  <v-card-actions>
                    <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                  </v-card-actions>
                </v-card>
              </v-dialog>
        </v-card-actions>
        <v-btn
                 v-if="!userInfo || profileUser.id !== userInfo.userId"
                @click="onClick"
                style="margin-left:130px; margin-bottom:10px;"
                dark
                small
                color=grey
            >
                {{ profileUser.followed ? '언팔로우' : '팔로우' }}
            </v-btn>
  

        <!-- 팔로워/팔로잉 디자인 수정 팔로우 했을 경우 팔로우 취소로 변경-->
         
       
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
            const profileUserId = this.profileUser.id
            const userId = this.userInfo.userId
            
            UserApi.requestFollowerList(
                {
                    profileUserId,
                    userId,
                },
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
                {
                    profileUserId,
                    userId,
                },
                res => {
                    console.log("realSetData 콜백 성공, res:", res.data.message)
                    this.following_list = res.data.message
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
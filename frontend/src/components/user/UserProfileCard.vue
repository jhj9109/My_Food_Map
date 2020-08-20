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
                                        v-if="follower.userid !== userInfo.userId"
                                        @click="onClick"
                                        style="margin-left:130px; margin-bottom:10px;"
                                        dark
                                        small
                                        color=grey
                                    >
                                        {{ profileUser.followed ? 'Unfollow' : 'Follow' }}
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
                                <v-list-item-action>
                                    <v-btn
                                        v-if="following.userid !== userInfo.userId"
                                        @click="onClick"
                                        style="margin-left:130px; margin-bottom:10px;"
                                        dark
                                        small
                                        color=grey
                                    >
                                        {{ profileUser.followed ? 'Unfollow' : 'Follow' }}
                                    </v-btn>
                                </v-list-item-action>
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
         <v-row justify="center">
      <v-expansion-panels popout>
        <v-expansion-panel
          v-for="(message, i) in messages"
          :key="i"
          hide-actions
        >
          <v-expansion-panel-header>
            <v-row
              align="center"
              class="spacer"
              no-gutters
            >
              <v-col
                class="text-no-wrap"
                cols="5"
                sm="3"
              >
                <strong v-html="message.title"></strong>
              </v-col>

            </v-row>
          </v-expansion-panel-header>

          <v-expansion-panel-content v-if="message.lorem">
            <v-divider></v-divider>
            <v-card-text v-text="message.lorem"></v-card-text>
          </v-expansion-panel-content>
          
        </v-expansion-panel>
      </v-expansion-panels>
    </v-row>
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
           messages: [
        {
          title: '이용약관 보기',
          lorem: '제1장 총칙 제1조(목적)본 약관은 MY FOOD DIARY (이하 "당 사이트")가 제공하는 모든 서비스(이하 "서비스")의 이용조건 및 절차, 이용자와 당 사이트의 권리, 의무, 책임사항과 기타 필요한 사항을 규정함을 목적으로 합니다. 제2조(약관의 효력과 변경) ① 당 사이트는 귀하가 본 약관 내용에 동의하는 것을 조건으로 귀하에게 서비스를 제공할 것이며, 귀하가 본 약관의 내용에 동의하는 경우, 당 사이트의 서비스 제공 행위 및 귀하의 서비스 사용 행위에는 본 약관이 우선적으로 적용됩니다.② 당 사이트는 본 약관을 변경할 수 있으며, 변경된 약관은 당 사이트 내에 공지함으로써 이용자가 직접 확인하도록 할 것입니다. 약관을 변경할 경우에는 적용일자 및 변경사유를 명시하여 당 사이트 내에 그 적용일자 30일 전부터 공지합니다. 약관 변경 공지 후 이용자가 명시적으로 약관 변경에 대한 거부의사를 표시하지 아니하면, 이용자가 약관에 동의한 것으로 간주합니다. 이용자가 변경된 약관에 동의하지 아니하는 경우, 이용자는 본인의 회원등록을 취소(회원탈퇴)할 수 있습니다.'

        },
        {
          title: '공지사항 보기',
          lorem: '공지사항 입니다.'
          
        },
        {
          title: '사용자 작성 리뷰',
        },
      ],
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

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
                v-if="profileUser.id !== userInfo.userId"
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
export default {
    props: [ 'profileUser', 'userInfo'],
    methods: {
        onClick() {
            console.log("onClick 반응")
            if (this.userInfo && (this.profileUser.id !== this.userInfo.userId)) {
                console.log("onFollow emit")
                this.$emit('onFollow')
            }
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
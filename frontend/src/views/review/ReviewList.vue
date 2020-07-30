<template>
  <div>
    <h1>Review List</h1>
    <v-card
        class="mx-auto my-1"
        max-width="344"
        v-for="review in review_list" :key="review.no"
    >
        <v-card-text>
        <div>Word of the Day</div>
        <p class="display-1 text--primary">
            be•nev•o•lent
        </p>
        <p>{{ review.rank }}</p>
        <div class="text--primary">
            {{ review.content }}
        </div>
        </v-card-text>
        <v-card-actions>
        <v-btn icon @click="likeReview(review)">
            <v-icon color="#FF1744">mdi-heart</v-icon>
        </v-btn>
        <v-btn icon>
            <v-icon>mdi-bookmark</v-icon>
        </v-btn>
        <v-btn icon>
            <v-icon>mdi-share-variant</v-icon>
        </v-btn>
        </v-card-actions>
    </v-card>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from 'vuex'
import http from '../../util/http-common';

const API_BASE_URL = 'http://localhost:9999'
export default {
    name: 'ReviewList',
    data() {
        return {
            review_list: [],
        }
    },
    methods: {
        fetchReviewList() {
            const API_REVIEW_LIST_URL = API_BASE_URL + '/review/list'
            const config = {}
            http.post(API_REVIEW_LIST_URL, config)
              .then(res => {
                this.review_list = res.data
              })
              .catch(err => {
                console.error(err)
              })
        },
        ...mapActions({
            getUserInfo: 'user/getUserInfo',
        }),
        likeReview(review) {
            const API_LIKE_URL = API_BASE_URL + '/review/like'
            const config = {
                reviewid: review.no,
                userid: this.userInfo.userid
            }
            console.log(config)
            http.post(API_LIKE_URL, config)
              .then(res => {
                console.log('dd')
              })
              .catch(err => {
                console.error(err)
              })
            // console.log(this.review_id, this.user_id)
        }
    },
    created() {
        this.fetchReviewList()
        this.getUserInfo()
    },
    computed: {
        ...mapState({
            id: state => state.user.id,
            userInfo: state => state.user.userInfo,
        })
    },
}
</script>

<style>

</style>
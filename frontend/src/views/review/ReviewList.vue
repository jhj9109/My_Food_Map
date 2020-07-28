<template>
  <div>
      <h1>Review List</h1>
      {{ review_list }}
  </div>
</template>

<script>
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
        }
    },
    created() {
        this.fetchReviewList()
    },
}
</script>

<style>

</style>
<template>
  <div>
    <ReviewCard
      v-for="review in reviews"
      :key="review.id"
      :reviewInfo="review"
    />
    <div
      v-if="!reviews"
    >
      작성된 리뷰가 없습니다
    </div>
  </div>
</template>

<script>
import ReviewCard from '@/components/review/ReviewCard';
import RestaurantApi from '@/api/RestaurantApi.js'

export default {
  name: "RestaurantReview",
  components: {
    ReviewCard,
  },
  data(){
    return {
      reviews: null,
    }
  },
  methods:{
    setReview() {
      const restaruantId = this.$route.params.restaurantId
      RestaurantApi.requestReviews(
        restaruantId,
        res => {
          this.reviews = res.data.message
          console.log("리뷰 리스트 데이터 바인딩 성공", this.reviews)
        },
        err => {
          console.error(err)
        }
      )
    },
  },
  mounted() {
    this.setReview()
  }
}
</script>
<style>

</style>
<template>
  <div>
    <h1>Review Detail</h1>
    <ReviewCard
      class="mt-1"
      :reviewInfo="this.review"
    />
  </div>
</template>

<script>
import ReviewCard from '@/components/review/ReviewCard'
import ReviewApi from '@/api/ReviewApi.js'

export default {
  name: "ReviewDetail",
  components: {
    ReviewCard,
  },
  data() {
    return {
      review: '',
    }
  },
  methods: {
    fetchReview() {
      const reviewId = this.$route.params.reviewId
      console.log(reviewId)
      ReviewApi.requestReviewInfo(
        reviewId,
        res => {
          console.log("realSetData 콜백 성공, res:", res.data.message)
          this.review = res.data.message
        },
        err => {
          console.error(err)
          console.log("에러반응")
        }
      )
    },
  },
  mounted() {
    this.fetchReview()
  },
}
</script>

<style>

</style>
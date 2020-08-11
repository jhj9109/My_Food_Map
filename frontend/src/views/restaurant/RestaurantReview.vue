<template>
  <div>
    <ReviewCard
      v-for="review in reviews"
      :key="review.id"
      :reviewInfo="review"
    />
    <div
      v-if="!allReviews"
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
  props: ['isScrollEnd'],
  data(){
    return {
      reviews: null,
      allReviews: null,
      loading: true,
      offset: 0,
      complete: true,
    }
  },
  methods:{
    setReviews() {
      const restaruantId = this.$route.params.restaurantId
      RestaurantApi.requestReviews(
        restaruantId,
        res => {
          this.allReviews = res.data.message // 전체 데이터
          this.reviews = []
          console.log("리뷰 리스트 데이터 바인딩 성공", this.allReviews)
          this.complete = false
          this.fetchReviews()
        },
        err => {
          console.error(err)
          console.log("에러반응")
        }
      )
    },
    fetchReviews() {
      const start = this.offset * 10
      const end = this.allReviews.length <= start + 10 ? this.allReviews.length : start + 10
      this.complete = this.allReviews.length <= start + 10 ? true : this.complete
      console.log("리뷰 데이터 갱신 요청", this.allReviews.slice(start, end), this.complete)
      this.reviews = [ ...this.reviews, ...this.allReviews.slice(start, end) ]
      this.offset += 1
      this.loading = false
    },
  },
  watch: {
    isScrollEnd: function(val) {
      console.log("스크롤엔드 감지 :", val, !this.complete, this.loading)
      if (val && !this.complete && !this.loading) {
        this.loading = true
        this.fetchReviews()
      }
    }
  },
  mounted() {
    this.setReviews()
  }
}
</script>
<style>

</style>
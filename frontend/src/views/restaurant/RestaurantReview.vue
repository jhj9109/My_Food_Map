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
  props: ['isScrollEnd'],
  data(){
    return {
      reviews: null,
      allReviews: null,
      loading: true,
      offset: 0,
    }
  },
  methods:{
    setReviews() {
      const restaruantId = this.$route.params.restaurantId
      RestaurantApi.requestReviews(
        restaruantId,
        res => {
          this.allReviews = res.data.message // 전체 데이터
          console.log("리뷰 리스트 데이터 바인딩 성공", this.allReviews)
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
      const end = start + 9
      console.log("리뷰 데이터 갱신 요청", this.allReviews.slice(start, end))
      const newArray = this.allReviews.slice(start, end)
      // console.log(`fetchRestaurants 대상은 ${start}~${end}, 5개 슬라이싱`, newArray)
      this.reviews = [ ...this.allReviews, ...newArray ]
      this.offset += 1
      this.loading = false
    }
  },
  watch: {
    isScrollEnd: function(val) {
      // console.log("스크롤엔드 감지 :", val, this.loading)
      if (val && !this.loading) {
        this.loading = true
        // console.log("데이터 로딩 중", this.loading)
        this.fetchReviews()
      } else {
        // console.log("지나간다")
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
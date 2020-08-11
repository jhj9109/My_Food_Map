<template>
  <div>
    <RestaurantCard
      :restaurantInfo="restaurant"
    />
    <br>
    <!--
    상단에 음식점 정보를 넣어주고, 하단에는 리뷰
    앞 페이지와 중복되며 앞의 음식점 페이지 삭제
    -->
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
// 레스토랑 정보 받기 위한 추가
import RestaurantCard from '@/components/restaurant/RestaurantCard'; 

export default {
  name: "RestaurantReview",
  components: {
    ReviewCard,
    //레스토랑 카드 추가
    RestaurantCard,
  },
  props: ['isScrollEnd'],
  data(){
    return {
      reviews: null,
      allReviews: null,
      loading: true,
      offset: 0,
      //레스토랑 프레임
      restaurant: {},
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
    //레스토랑 정보 설정
    setRestaurant() {
      const restaruantId = this.$route.params.restaurantId
      console.log("realSetData 요청 Id 값 : ", restaruantId, typeof(restaruantId))
      RestaurantApi.requestInfo(
        restaruantId,
        res => {
          console.log("realSetData 콜백 성공, res:", res.data.message)
          this.restaurant = res.data.message
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
    this.setReviews(),
    //레스토랑 정보 받아오기
    this.setRestaurant()
  },
}
</script>
<style>

</style>
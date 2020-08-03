<template>
  <div>
    <ReviewCard
      v-for="review in reviews"
      :key="review.id"
      :reviewInfo="review"
    />
  </div>
</template>

<script>
import ReviewCard from '@/components/review/ReviewCard';
export default {
  name: "RestaurantReview",
  components: {
    ReviewCard,
  },
  data(){
    return {
      loading: false,
      reviews: []
    }
  },
  methods:{
    // 아래는 임시 데이터 세팅 메소드
    tempSetData() {
      this.reviews = [
        {
          imgUrl: "https://cdn.pixabay.com/photo/2015/04/08/13/13/food-712665__340.jpg",
          rank: 5.0,
          content: "이곳 가게 이름답게 어썸한 가게 인정? 인정"
        },
        {
          imgUrl: "",
          rank: 1.0,
          content: "위에 리뷰에 속았다. 분하다 여러분 속지 마세요. 이 가게 매우 어글리하고 맛없는 가게임!"
        },
        {
          imgUrl: "https://cdn.pixabay.com/photo/2016/10/22/20/34/wine-1761613__340.jpg",
          rank: 4.0,
          content: "전 분위기도 좋고 괜찮았어요! 저녁의 여유로움~"
        },
        {
          imgUrl: "https://cdn.pixabay.com/photo/2016/12/26/17/28/food-1932466__340.jpg",
          rank: 3.5,
          content: "강력!!!! 추천은 아니지만.... ㅎㅎ 괜찮은 식당이에요 ^^"
        },
      ]
    },
  },
  mounted() {
    // 아래의 임시 데이터 세팅을 바꿔주면 됌
    this.tempSetData()
  }
}
</script>
<style>

</style>
<template>
  <div>
    <RestaurantCard 
      :restaurantInfo="restaurant"
    />
  </div>

</template>

<script>
import RestaurantCard from '@/components/restaurant/RestaurantCard';

// import { mapState, mapMutations } from 'vuex';

export default {
  name: 'Restaurant',
  components: {
    RestaurantCard,
  },
  data() {
    return {
      restaurant: {
        id: 1,
        title: "어썸한 가게",
        content: "아직 id 받아 데이터 받기 구현 안함!",
        imgUrl: "https://cdn.vuetifyjs.com/images/cards/cooking.png",
        types: ["이탈리안","레스토랑"],
        rank: 4.5,
        likeCount: 404,
        deal_date: "10:00 ~ 21:00"
      }
    }
  },
}
</script>
<style>

</style>
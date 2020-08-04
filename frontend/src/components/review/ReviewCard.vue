<template>
  <v-card
    :loading="loading"
    class="mx-auto my-3"
    max-width="374"
  >
    <v-img
      v-if="reviewInfo.image"
      height="250"
      :src="reviewInfo.image"
    />
    
    <v-card-text>

      <div class="float-right">
        <v-row
          align="center"
          class="ml-3"
        >
          <v-rating
            :value="reviewInfo.rank"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          />
        </v-row>
      </div>
      <div> {{ reviewInfo.content }}</div>
    
    </v-card-text>
  </v-card>
</template>

<script>
  export default {
    name: "ReviewCard",
    props: ['reviewInfo'],
    data: () => ({
      loading: false,
    }),

    methods: {
			onClick(){
        // 클릭시 모달 => 도움이 됐어요
        console.log("onClick 발동")
        // console.log(`id :${this.restaurantInfo.id} 음식점 페이지로 이동`)
        // this.$router.push({ name: 'RestaurantDetail', params: { restaurantId: this.restaurantInfo.id}}); //리뷰 => 리뷰 디테일?
			}
    },
  }
</script>
<style>

</style>
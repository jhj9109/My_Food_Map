<template>
  <v-card
    :loading="loading"
    class="mx-auto my-0"
    max-width="374"
  >
    <v-img
        @click="onClick"
        height="250"
        :src="restaurantInfo.imgUrl"
    ></v-img>

    <v-card-title @click="onClick">{{ restaurantInfo.title }}</v-card-title>

    <v-card-text>
      <v-row
        align="center"
        class="mx-0"
      >
        <v-rating
          :value="restaurantInfo.rank"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ml-4">{{ restaurantInfo.value }} ({{ restaurantInfo.likeCount }})</div>
      </v-row>

      <div class="my-4 subtitle-1">
        <template v-for="type in restaurantInfo.types">
            {{ type }}
        </template>
      </div>

      <div>{{ restaurantInfo.content }}</div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title>영업 시간</v-card-title>

    <v-card-text>
			{{ restaurantInfo.deal_date }}
    </v-card-text>

  </v-card>
</template>

<script>
  export default {
    name: "RestaurantCard",
    // props: ['id', 'title', 'content', 'imgUrl', 'types', 'rank', 'likeCount', 'deal_date'],
    props: ['restaurantInfo'],
    data: () => ({
      loading: false,
    }),

    methods: {
			onClick(){
                console.log(`id :${this.restaurantInfo.id} 음식점 페이지로 이동`)
                console.log(this.$router)
                console.log(this.$route)
                if (this.$route.fullPath === "/restaurant") {
                    this.$router.push({ name: 'RestaurantDetail', params: { restaurantId: this.restaurantInfo.id}}); //리스트 => 특정 음식점
                } else {
                    this.$router.push({ name: 'RestaurantReview', params: { restaurantId: this.restaurantInfo.id}}); //특정 음식점 => 해당 리뷰
                }
			}
    },
  }
</script>
<style>

</style>
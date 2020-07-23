<template>
  <v-card
    :loading="loading"
    class="mx-auto my-0"
    max-width="374"
  >
    <v-img
      height="250"
      src="https://cdn.vuetifyjs.com/images/cards/cooking.png"
    ></v-img>

    <v-card-title>어썸한 가게</v-card-title>

    <v-card-text>
      <v-row
        align="center"
        class="mx-0"
      >
        <v-rating
          :value="4.5"
          color="amber"
          dense
          half-increments
          readonly
          size="14"
        ></v-rating>

        <div class="grey--text ml-4">4.5 (413)</div>
      </v-row>

      <div class="my-4 subtitle-1">
        이탈리안, 카페
      </div>

      <div>맛있는 이탈리안 음식점, 어썸한 우리 가게로 오세요^^</div>
    </v-card-text>

    <v-divider class="mx-4"></v-divider>

    <v-card-title>영업 시간</v-card-title>

    <v-card-text>
			0:00 ~ 24:00
    </v-card-text>

  </v-card>
</template>

<script>
  export default {
    name: "Restaurant",
    data: () => ({
      loading: false,
    }),

    methods: {
    },
  }
</script>
<style>

</style>
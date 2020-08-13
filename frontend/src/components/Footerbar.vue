<template>
    <v-card height="70px">
      <v-footer
        fixed
        flat
        tile
        dark
        width="100%"
        height="56%"
        class="backgroud_gradient"
      >

            <v-btn :to="{ name: 'Restaurant' }" class="ml-16" icon>
              <v-icon size="24px">mdi-search-web</v-icon>
            </v-btn>

            <v-btn :to="{ name: 'Map' }" class="ml-2" icon >
              <v-icon size="24px">mdi-map</v-icon>
            </v-btn>
            <!-- 피드로 가는 버튼 추가 -->
            <v-btn class="ml-2" icon>
              <v-icon>mdi-format-align-justify</v-icon>
            </v-btn>
            
            <v-btn @click="onClick" class="ml-2" icon>
              <v-icon>mdi-account</v-icon>
            </v-btn>
            <!-- 맨위로 가기 -->
            <v-btn @click="$emit('scrollToTop')" class="ml-2" icon>
              <v-icon>mdi-arrow-up</v-icon>
            </v-btn>
            <!-- 글쓰기 버튼 restaurant card로 옮김.
            <v-btn :to="{ name: 'ReviewCreate' }" class="ml-2" icon >
              <v-icon size="24px">mdi-lead-pencil</v-icon>
            </v-btn>
            -->
      </v-footer>
    </v-card>
</template>

<script>

export default {
  props: ['userInfo'],
  methods: {
    onClick() {
      if (this.userInfo) {
        this.$router.push({name : 'Profile', params : {userId : this.userInfo.userId}})
      } else {
        this.$router.push({name : 'Login'})
      }
    }
  }
}
</script>
<style >
.backgroud_gradient {
    background: linear-gradient(90deg, #fa709a, #f6d365 100%);
}
</style>
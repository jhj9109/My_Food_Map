<template>
    <v-card height="200px">
      <v-footer
        fixed
        flat
        tile
        width="100%"
        height="60%"
        class="backgroud_gradient text-center"
      >
            <v-btn :to="{ name: this.userInfo ? 'Mypage' : 'Login' }">
              <v-icon>mdi-account</v-icon>
            </v-btn>

            <v-btn :to="{ name: 'Restaurant' }" class="mx-4" icon>
              <v-icon size="24px">mdi-home</v-icon>
            </v-btn>

            <v-btn :to="{ name: 'ReviewCreate' }" class="mx-4" icon >
              <v-icon size="24px">mdi-lead-pencil</v-icon>
            </v-btn>
      </v-footer>
    </v-card>
</template>

<script>

export default {
  props: ['userInfo'],
}
</script>
<style >
.backgroud_gradient {
    background: linear-gradient(90deg, #fa709a, #f6d365 100%);
}
</style>
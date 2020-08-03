<template>
    <v-card height="100px">
      <v-footer
        fixed
        flat
        tile
        width="100%"
        class="red lighten-1 text-center"
      >
        <v-card
          flat
          tile
          width="100%"
          class="red lighten-1 text-center"
        >
          <v-card-text>
            <v-btn :to="{ name: this.userInfo ? 'Mypage' : 'Login' }">
              <v-icon>mdi-account</v-icon>
            </v-btn>

            <v-btn :to="{ name: 'Restaurant' }" class="mx-4" icon>
              <v-icon size="24px">mdi-home</v-icon>
            </v-btn>

            <v-btn :to="{ name: 'ReviewCreate' }" class="mx-4" icon >
              <v-icon size="24px">mdi-lead-pencil</v-icon>
            </v-btn>
          </v-card-text>
        </v-card>
      </v-footer>
    </v-card>
</template>

<script>

export default {
  props: ['userInfo'],
}
</script>
<style >
</style>
     
<template>
  <div class="Footerbar">
    <v-card height="200px">
      <v-footer
        fixed
        flat
      >
        <v-card
          flat
          tile
          width="100%"
          class="red lighten-1 text-center"
        >
          <v-card-text>
            <v-btn :to=" this.userInfo ? '/user/mypage' : '/user/login' ">
              <v-icon>mdi-account</v-icon>
            </v-btn>

            <v-btn to="/restaurant" class="mx-4" icon>
              <v-icon size="24px">mdi-home</v-icon>
            </v-btn>

            <v-btn to="/review/create" class="mx-4" icon >
              <v-icon size="24px">mdi-lead-pencil</v-icon>
            </v-btn>
          </v-card-text>
        </v-card>
      </v-footer>
    </v-card>
  </div>  
</template>

<script>

export default {
  props: ['userInfo'],
}
</script>
<style >
</style>
     
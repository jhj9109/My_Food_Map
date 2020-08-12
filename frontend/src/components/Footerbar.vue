<template>
    <v-card height="70px">
      <v-footer
        fixed
        flat
        tile
        dark
        width="100%"
        height="60%"
        class="backgroud_gradient text-center"
      >
            <v-btn @click="onClick" icon>
              <v-icon>mdi-account</v-icon>
            </v-btn>

            <v-btn :to="{ name: 'Restaurant' }" class="mx-2" icon>
              <v-icon size="24px">mdi-home</v-icon>
            </v-btn>

            <v-btn :to="{ name: 'ReviewCreate' }" class="mx-2" icon >
              <v-icon size="24px">mdi-lead-pencil</v-icon>
            </v-btn>
            <v-btn :to="{ name: 'Logout' }" class="mx-2" icon >
              Logout
            </v-btn>
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
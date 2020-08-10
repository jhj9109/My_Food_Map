<template>
    <v-card height="70px">
      <v-footer
        fixed
        flat
        tile
        width="100%"
        height="60%"
        class="backgroud_gradient text-center"
      > <!-- 어카운트만 버튼으로 보이는 것 수정 -->
            <v-btn :to="{ name: this.userInfo ? 'Mypage' : 'Login' }" icon>
              <v-icon>mdi-account</v-icon>
            </v-btn>

            <v-btn :to="{ name: 'Restaurant' }" class="mx-2" icon>
              <v-icon size="24px">mdi-home</v-icon>
            </v-btn>

            <v-btn :to="{ name: 'ReviewCreate' }" class="mx-2" icon >
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
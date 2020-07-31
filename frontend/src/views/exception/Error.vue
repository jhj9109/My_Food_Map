<template>
  <div>
    <h2> 에러페이지 </h2>
    <v-btn
      to="/restaurant"
    >
      메인으로
    </v-btn>
    <v-btn
      @click="goBack"
    >
      뒤로가기
    </v-btn>
  </div>
</template>

<script>

export default {
  methods: {
    goBack: function(){
      var numberOfEntries = window.history.length;
      if(numberOfEntries>2){
        this.$router.go(-1)
      }else{
      var fpath=  this.PageData.backCrumb.url;
      this.$router.push({
          path: fpath
        })
      }
    },
  }
}
</script>

<style>

</style>
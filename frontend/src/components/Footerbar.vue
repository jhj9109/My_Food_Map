<template>
  <div class="Footerbar">
    <v-bottom-navigation
      fixed
    >

      <v-btn to="/user/login">
        <v-icon>mdi-account</v-icon>
      </v-btn>

      <v-btn>
        <v-icon>mdi-home</v-icon>
      </v-btn>

      <v-btn >
        <v-icon>mdi-lead-pencil</v-icon> 
      </v-btn>
      

    </v-bottom-navigation>


    
  </div>  
</template>

<script>


export default {
  data:()=>{
    return{

    }
  },

}
</script>
<style scoped>

</style>
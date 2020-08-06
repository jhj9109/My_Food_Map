<template>
  <div>
    <v-row justify="center" align="center">
      <v-subheader>Offset Top</v-subheader>
      {{ offsetTop }}
    </v-row>
          <v-subheader>scrollHeight</v-subheader>
      {{ scrollHeight }}
    </v-row>
    <v-container
      id="scroll-target"
      style="max-height: 500px"
      class="overflow-y-auto"
      v-scroll.self="onScroll"
    >
      <v-row
        align="center"
        justify="center"
        style="height: 1500px"
      >
      </v-row>
    </v-container>
  </div>
</template>

<script>
  export default {
    data: () => ({
      offsetTop: 0,
      scrollHeight: 0,
    }),

    methods: {
      onScroll (e) {
        this.offsetTop = e.target.scrollTop
        this.scrollHeight =  e.target.scrollHeight
        console.log("scrollTop, scrollHeight", e.target.scrollTop, e.target.scrollHeight)

      },
    },
  }
</script>
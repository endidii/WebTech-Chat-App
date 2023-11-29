<template>
  <button class="channel-button" v-for="channel in channels" :key="channel.id"><span class="chat-tag"># {{channel.name}}</span></button>
</template>

<script lang="ts">
import axios from 'axios'

export default {
  data() {
    return {
      channels: []
    }
  },
  mounted() {
    axios
      .get('http://localhost:8080/users/0b816/channels')
      .then((response) => {
        this.channels = response.data
      })
  }
}
</script>

<style scoped>
.channel-button:hover{
  background-color: red;
}
</style>
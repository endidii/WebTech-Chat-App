<template>
  <div class="member" v-for="user in users" :key="user.id">
    <img class="user-img" src="../assets/cat1.jpeg" alt="user-img" />
    <p class="username">{{user.username}}</p>
  </div>
</template>

<script lang="ts">
import axios from 'axios'

export default {
  data() {
    return {
      users: []
    }
  },
  mounted() {
    axios
      .get('http://localhost:8080/channels/abca7/users')
      .then((response) => {
        this.users = response.data
      })
  }
}
</script>

<style scoped>
</style>
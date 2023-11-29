<template>
  <div class="member" v-for="user in users.values()" :key="user.id">
    <img class="user-img" src="../assets/cat1.jpeg" alt="user-img" />
    <p class="username">{{user.username}}</p>
  </div>
</template>

<script setup lang="ts">
import axios from 'axios'
import { type Ref, ref} from "vue"

type User = {
  id: string;
  username: string;
}

let users: Ref<User[]> = ref([]);

axios
    .get('http://localhost:8080/channels/abca7/users')
    .then((response) => {
      users.value = response.data
    })

</script>

<style scoped>
</style>
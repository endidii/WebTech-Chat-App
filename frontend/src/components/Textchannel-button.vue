<template>
  <button class="channel-button" v-for="channel in channels" :key="channel.id"><span class="chat-tag"># {{channel.name}}</span></button>
</template>

<script setup lang="ts">
import axios from 'axios'
import { type Ref, ref} from "vue"

type Channel = {
  id: string;
  name: string;
  description: string;
}

let channels: Ref<Channel[]> = ref([]);

axios
    .get('http://localhost:8080/users/0b816/channels')
    .then((response) => {
      channels.value = response.data
    })
</script>

<style scoped>
.channel-button:hover{
  background-color: red;
}
</style>
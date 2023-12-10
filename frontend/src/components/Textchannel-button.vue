<template>
  <button class="channel-button" v-for="channel in channels" :key="channel.id"><span class="chat-tag"># {{channel.name}}</span></button>
</template>

<script setup lang="ts">
import axios from 'axios'
import {onMounted, type Ref, ref} from "vue"

type Channel = {
  id: string;
  name: string;
  description: string;
}

let channels: Ref<Channel[]> = ref([]);

const props = defineProps({
  userData: Object
});
onMounted(() => {
  getChannels()
})
function getChannels(){
  axios
      .get(`http://localhost:8080/users/${props.userData.id}/channels`)
      .then((response) => {
        channels.value = response.data
        console.log(response.data)
      })
}
</script>

<style scoped>
.channel-button:hover{
  background-color: #006EDB;
  cursor: pointer;
}
</style>
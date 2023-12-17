<template>
  <button
    @click="buttonClicked(channel)"
    class="channel-button"
    v-for="channel in channelsForUser"
    :class="{ active: activeChannelId === channel.id }"
    :key="channel.id"
  >
    <span class="chat-tag"># {{ channel.name }}</span>
  </button>
</template>

<script setup lang="ts">
import axios from 'axios'
import { onMounted, type Ref, ref } from 'vue'
type Message = {
  id:string;
  sender:User;
  created_at:string;
}

type Channel = {
  id: string
  name: string
  description: string
  users: User[]
  messages: Message[]
}

type User = {
  id: string
  username: string
  email: string
  password: string
  messages: Message[]
  textchannels: Channel[]
  directchannels: any[]
}

const emit = defineEmits(['channelButtonClicked'])

let channelsForUser: Ref<Channel[]> = ref([])
const baseUrl = import.meta.env.VITE_BACKEND_BASE_URL;

const props = defineProps({
  userData: Object,
  activeChannelId: String
})
onMounted(() => {
  axios.get(`https://blendr-backend.onrender.com/users/${props.userData?.id}/channels`)
    .then(response => {
      let channelIds = response.data.map((item: Channel | string) => {
        if (typeof item === 'object') {
          // If item is an object, return the id property of the item
          return item.id;
        } else {
          // If item is not an object (thus a string), return the item itself
          return item;
        }
      });
      // Map each channelId to a fetch promise
      const fetchPromises = channelIds.map(async (channelId: string) => {
        try {
          const channelResponse = await axios.get(`https://blendr-backend.onrender.com/channels/${channelId}`);
          return channelResponse.data;
        } catch (error) {
          console.error("Error fetching channel ${channelId}:", error);
          return null; // Return null in case of error
        }
      });
      // Use Promise.all to wait for all promises to resolve
      Promise.all(fetchPromises).then(channelsData => {
        // Filter out any null values and update channelsForUser
        channelsForUser.value = channelsData.filter(channel => channel !== null);
      });
    });
});
function buttonClicked(channel: Channel) {
  emit('channelButtonClicked', channel)
}
</script>

<style scoped>
.channel-button:hover {
  background-color: #006edb;
  cursor: pointer;
}
.active {
  background-color: #006edb;
}
</style>
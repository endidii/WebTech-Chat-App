<template>
  <div class="member" v-for="user in users.values()" :key="user.id">
    <img class="user-img" src="../assets/cat1.jpeg" alt="user-img" />
    <p :class="['username', { 'creator-name': user.id === creatorId }]">{{user.username}}</p>
  </div>
</template>

<script setup lang="ts">
import axios from 'axios'
import { onMounted, type Ref, ref, watch } from "vue";

type User = {
  id: string;
  username: string;
}

const props = defineProps({
  activeChannelId: String
})
let users: Ref<User[]> = ref([]);
const baseUrl = import.meta.env.VITE_BACKEND_BASE_URL;
let creatorId: Ref<string | null> = ref(null);

function getTextchannel(channelId: string) {
  axios
    .get(`${baseUrl}/channels/${channelId}`)
    .then(response => {
      console.log(response.data);
      creatorId.value = response.data.creatorId; // Set the creatorId
      return response.data;
    });
}

function fetchMembers(channelId: string) {
  getTextchannel(channelId);
  axios
    .get(`${baseUrl}/channels/${channelId}/users`)
    .then(response => {
      let userIds = response.data.map((item: User | string) => {
        if (typeof item === 'object') {
          // If item is an object, return the id property of the item
          return item.id;
        } else {
          // If item is not an object (thus a string), return the item itself
          return item;
        }
      });
      // Map each userId to a fetch promise
      const fetchPromises = userIds.map(async (userId: string) => {
        try {
          const userResponse = await axios.get(`${baseUrl}/users/${userId}`);
          return userResponse.data;
        } catch (error) {
          console.error("Error fetching user ${userId}:", error);
          return null; // Return null in case of error
        }
      });
        // Use Promise.all to wait for all promises to resolve
        Promise.all(fetchPromises).then(userData => {
        // Filter out any null values and update channelsForUser
        users.value = userData.filter(user => user !== null);
      });

    });
}

watch(() => props.activeChannelId, (newChannelId, oldChannelId) => {
  if (newChannelId) {
    fetchMembers(newChannelId);
  }
});

onMounted(() => {
  if (props.activeChannelId) {
    fetchMembers(props.activeChannelId);
  }
});

</script>

<style scoped>
</style>
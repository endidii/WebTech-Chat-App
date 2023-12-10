<template>
  <button class="channel-button" v-for="channel in channelObjects" :key="channel.id"><span class="chat-tag"># {{channel.name}}</span></button>
</template>

<script setup lang="ts">
import axios from 'axios'
import {onMounted, type Ref, ref} from "vue"

type Channel = {
  id: string;
  name: string;
  description?: string;
  users: User[];
  messages: any[];
};

type User = {
  id: string;
  username: string;
  email: string;
  password: string;
  messages: any[];
  textchannels: (string | Channel)[];
  directchannels: any[];
};

let channelObjects: Ref<Channel[]> = ref([]);

const props = defineProps({
  userData: Object
});
onMounted(() => {
  getChannels()
})
function getChannels(){
  axios
      .get(`http://localhost:8080/users/${props.userData?.id}/channels`)
      .then((response) => {
        let userId = props.userData?.id;
        let json = response.data;
        json.forEach((item:Channel) => {
          if (typeof item === 'object' && item.users) {
            item.users.forEach((user:User) => {
              if (user.id === userId) {
                // Add the entire top-level channel object
                if (item.name) {
                  channelObjects.value.push(item);
                }
                // Iterate through the user's textchannels
                user.textchannels.forEach(channel => {
                  // Check if the item in textchannels is a channel object and add it
                  if (typeof channel === 'object' && channel.name) {
                    channelObjects.value.push(channel);
                  }
                });
              }
            });
          }
        });
        console.log(channelObjects.value);
      })
}
</script>

<style scoped>
.channel-button:hover{
  background-color: #006EDB;
  cursor: pointer;
}
</style>
<template>
  <button
      @click="buttonClicked(channel)"
      class="channel-button"
      v-for="channel in channelsForUser"
      :class="{ active: activeChannelId === channel.id }"
      :key="channel.id">
    <span class="chat-tag"># {{channel.name}}</span>
  </button>
</template>

<script setup lang="ts">
import axios from 'axios'
import {onMounted, type Ref, ref} from "vue"

type Message = {
  id: string;
  content: string;
  timestamp: string;
  user: User;
  textchannel: Channel;
};

type Channel = {
  id: string;
  name: string;
  description: string;
  users: User[];
  messages: any[];
};

type User = {
  id: string;
  username: string;
  email: string;
  password: string;
  messages: any[];
  textchannels: (Channel)[];
  directchannels: any[];
};


const emit = defineEmits(["channelButtonClicked"])

let channelsForUser: Ref<Channel[]> = ref([]);

const props = defineProps({
  userData: Object,
  activeChannelId: String
});
onMounted(() => {
  getChannels()
})
function buttonClicked(channel: Channel){
  emit("channelButtonClicked", channel)
}
function getChannels(){
  axios
      .get(`http://localhost:8080/users/${props.userData?.id}/channels`)
      .then((response) => {
        let userId = props.userData?.id;
        let jsonResponse = response.data;
        jsonResponse.forEach((item: Channel | string) => {
          if (typeof item === 'object' && 'users' in item) {
            const isUserInTopLevel = item.users.some(user =>
                (typeof user === 'object' && user.id === userId) || user === userId
            );

            if (isUserInTopLevel && !channelsForUser.value.some(channel => channel.id === item.id)) {
              channelsForUser.value.push(item);
            }

            item.users.forEach((user: User | string) => {
              if (typeof user === 'object' && user.id === userId) {
                user.textchannels.forEach((textchannel: Channel | string) => {
                  if (typeof textchannel === 'string') {
                    // Handle string IDs in textchannels
                    const channelObject = jsonResponse.find(channel =>
                        typeof channel === 'object' && channel.id === textchannel
                    ) as Channel | undefined;

                    if (channelObject && !channelsForUser.value.some(channel => channel.id === channelObject.id)) {
                      channelsForUser.value.push(channelObject);
                    }
                  } else if (!channelsForUser.value.some(channel => channel.id === textchannel.id)) {
                    // Handle channel objects in textchannels
                    channelsForUser.value.push(textchannel);
                  }
                });
              }
            });
          }
        });

        console.log(channelsForUser.value);
      })
}
</script>

<style scoped>
.channel-button:hover{
  background-color: #006EDB;
  cursor: pointer;
}
.active {
  background-color: #006EDB;
}
</style>
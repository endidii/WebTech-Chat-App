<template>
  <div class="message-history-div">
    <div class="message-div" v-for="message in messages.values()" :key="message.id">
      <img class="user-img" src="../assets/cat1.jpeg" alt="user-img" />
      <div class="message-info-div">
        <div class="user-date-div">
          <p class="sender">{{message.sender}}</p>
          <p class="created_at">{{message.date}}</p>
        </div>
        <p class="content">{{message.content}}</p>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { onMounted, ref, type Ref, watch } from "vue";
import axios from "axios";

type Message = {
  id: string;
  content: string;
  date: string;
  sender: User;
}

type User = {
  id: string;
  username: string;
}

const props = defineProps({
  activeChannelId: String
})
let messages: Ref<Message[]> = ref([]);

function fetchMessages(channelId: string) {
  axios
    .get(`http://localhost:8080/channels/${channelId}/messages`)
    .then(response => {
      let messageObjects = response.data.map((item: Message) => {
        if (typeof item === 'object') {
          // If item is an object, return the id property of the item
          console.log(item);
          return item
        }
      });
      messages.value = messageObjects;
      console.log(messages.value);
    });
}

watch(() => props.activeChannelId, (newChannelId, oldChannelId) => {
  if (newChannelId) {
    fetchMessages(newChannelId);
  }
});

onMounted(() => {
  if (props.activeChannelId) {
    fetchMessages(props.activeChannelId);
  }
});

</script>

<style scoped>
.message-div{
  margin-left: 20px;
  margin-top: 20px;
  display: flex;
  align-items: center;
}
.content{
  color: white;
  font-size: 20px;
}
.user-img{
  height: 55px;
  width: 55px;
  border-radius: 4px;
  margin-right: 10px;
}
.sender{
  color: #006EDB;
  font-size: 20px;
  margin-right: 8px;
}
.message-history-div{
  margin-left: 280px;
  margin-right: 280px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}
.user-date-div{
  display: flex;
  align-items: center;
}
</style>
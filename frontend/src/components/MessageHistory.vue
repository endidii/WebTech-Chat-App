<template>
  <div class="message-container">
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

    <input v-model="message_content"
           :disabled="props.isInputDisabled"
           v-on:keyup.enter="postMessage(message_content)"
           type="text" id="message-input"
           placeholder="Nachricht eingeben" />
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
  activeChannelId: String,
  userData: Object,
  isInputDisabled: Boolean
})
let messages: Ref<Message[]> = ref([]);
let message_content = ref("");

function postMessage(message: string) {
  axios
      .post("http://localhost:8080/channels/" + props.activeChannelId + "/users/" + props.userData?.id  + "/messages",
          {
            content: message
          })
      .then((response) => {
        message_content.value = "";
        console.log(message);
      })
}

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
.message-history-div {
  overflow-y: auto; /* Enables vertical scrolling */
  max-height: calc(100vh - 70px); /* Height minus input height */
  width: 100%; /* Ensure it takes the full width */
}
.user-date-div{
  display: flex;
  align-items: center;
}
#message-input{
  background-color: #141419;
  border-width: 1px;
  border-style: solid;
  border-color: white;
  color: white;
  font-size: 20px;
  padding-left: 16px;
  font-family: 'Poppins', sans-serif;
  z-index: 2; /* Ensures the input stays above the message history */
  position: fixed; /* Keeps the input fixed */
  bottom: 20px; /* Aligns it to the bottom of the viewport */
  width: calc(100% - 602px); /* Adjust as needed */
  left: 300px; /* Adjust as needed */
  height: 50px; /* Adjust as needed */
}
.message-container {
  display: flex;
  flex-direction: column;
  height: 100vh; /* Set the height to the full viewport height */
  justify-content: flex-end; /* Aligns children to the bottom */
}
</style>
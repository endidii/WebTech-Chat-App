<template>
  <div class="container-div">
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
  </div>
    <input v-model="message_content"
           :disabled="props.isInputDisabled"
           v-on:keyup.enter="postMessage(message_content)"
           type="text"
           id="message-input"
           placeholder="Nachricht eingeben" />

</template>

<script setup lang="ts">
import {nextTick, onMounted, ref, type Ref, watch} from "vue";
import axios from "axios";

type Message = {
  id: string;
  content: string;
  date: [];
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
const baseUrl = import.meta.env.VITE_BACKEND_BASE_URL;

async function postMessage(message: string) {
  if (message.trim() === '') {
    alert('Bitte gebe eine Nachricht ein!');
    return;
  }
  await axios.post(`${baseUrl}/channels/` + props.activeChannelId + "/users/" + props.userData?.id  + "/messages",
          {
            content: message
          })
  message_content.value = "";
  await fetchMessages(props.activeChannelId as string);
  console.log(message);
}

async function fetchMessages(channelId: string) {
  try {
    const response = await axios.get(`${baseUrl}/channels/${channelId}/messages`);

    // Create a new array to store the updated messages
    const updatedMessages = [];

    for (const message of response.data) {
      // Skip if the item is not an object
      if (typeof message !== 'object') continue;

      try {
        // Fetch user data for each message
        const userResponse = await axios.get(`${baseUrl}/users/${message.sender}`);
        const username = userResponse.data.username;

        // Replace sender ID with username
        updatedMessages.push({ ...message, sender: username });
      } catch (userError) {
        console.error('Error fetching user data:', userError);
        // Push the original message if there's an error fetching user data
        updatedMessages.push(message);
      }
    }

    // Update messages with the new array containing usernames
    messages.value = updatedMessages;
  } catch (error) {
    console.error('Error fetching messages:', error);
  }

  await nextTick(() => {
    const messageHistoryDiv = document.querySelector('.message-history-div');
    if (messageHistoryDiv) {
      messageHistoryDiv.scrollTop = messageHistoryDiv.scrollHeight;
    }
  });
}


watch(() => props.activeChannelId, (newChannelId) => {
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
.container-div{
  display: flex;
  flex-direction: column;
  justify-content: flex-end;
}
.message-div{
  margin-left: 20px;
  margin-top: 30px;
  display: flex;
  align-items: center;
}
.content{
  color: white;
  font-size: 20px;
  margin-top: 0;
  margin-bottom: 0;
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
  margin-bottom: 0;
  margin-top: 0;
}
.created_at{
  margin: 0;
  color: gray;
}
.message-history-div {
  overflow-y: auto; /* Enables vertical scrolling */
  max-height: calc(100vh - 240px); /* Height minus input height */
  width: 100%; /* Ensure it takes the full width */
  grid-row-start: 2;
  grid-row-end: row1-end;
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
  position: fixed; /* Keeps the input fixed */
  bottom: 20px; /* Aligns it to the bottom of the viewport */
  width: calc(100% - 622px);
  left: 300px; /* Adjust as needed */
  height: 50px; /* Adjust as needed */
}
@media (max-width: 1000px) {
  #message-input {
    width: calc(100% - 340px);
  }
  .message-history-div{
    width: calc(100% + 220px);
  }
}
</style>
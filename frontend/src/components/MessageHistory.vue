<template>
  <div v-if="show" class="container-div">
    <div class="message-history-div">
      <div class="message-div" v-for="message in messages" :key="message.id">
        <img class="user-img" src="../assets/cat1.jpeg" alt="user-img" />
        <div class="message-info-div">
          <div class="user-date-div">
            <p class="sender">{{message.sender.username}}</p>
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
import SockJS from 'sockjs-client';
import Stomp from 'webstomp-client';
import type { Client, Frame } from 'webstomp-client';
import { onBeforeUnmount } from 'vue';

type Message = {
  id: string;
  content: string;
  date: string;
  sender: Sender;
};

type Sender = {
  id: string;
  username: string;
};

const props = defineProps({
  activeChannelId: String,
  userData: Object,
  isInputDisabled: Boolean,
  show: Boolean
})
let messages: Ref<Message[]> = ref([]);
let message_content = ref("");
let stompClient: Client | null = null;
const baseUrl = import.meta.env.VITE_BACKEND_BASE_URL;
let currentSubscription: any | null = null;

const connectWebSocket = (): void => {
  const sock = new SockJS(`${baseUrl}/ws`);
  stompClient = Stomp.over(sock);

  // Note: Pass an empty object for headers if you don't have any headers to pass.
  // The connectCallback and errorCallback are passed according to the expected signature.
  stompClient.connect(
      {}, // Headers
      (frame: Frame | undefined) => {
        // Connection success callback
        console.log('Connected:', frame);
      },
      (error: Frame | CloseEvent) => {
        // Connection error callback
        console.error('Connection error:', error);
      }
  );
};

function getRandomInt(max:number) {
  return Math.floor(Math.random() * max);
}

const subscribeToChannel = (channelId: string): void => {
  if (!stompClient || !channelId) return;

  // Unsubscribe from the previous subscription if it exists
  if (currentSubscription) {
    currentSubscription.unsubscribe();
    currentSubscription = null;
  }

  const topic = "/topic/channel/"+channelId;
  currentSubscription = stompClient.subscribe(topic, (message: Frame) => {
    const newMessage = JSON.parse(message.body);
    if (newMessage && newMessage.date && newMessage.content && newMessage.sender) {
      let addMessage : Message;
      let senderconst : Sender = {
        id: newMessage.sender.id,
        username: newMessage.sender
      }
      addMessage = {
        id: getRandomInt(100000).toString(),
        content: newMessage.content,
        date: newMessage.date,
        sender: senderconst
      }
      console.log(addMessage);
      messages.value.push(addMessage);
      nextTick(() => {
        const messageHistoryDiv = document.querySelector('.message-history-div');
        if (messageHistoryDiv) {
          messageHistoryDiv.scrollTop = messageHistoryDiv.scrollHeight;
        }
      });
    } else {
      console.error("Received message does not match 'Message' structure:", newMessage);
    }
  });
};
defineExpose({ subscribeToChannel, connectWebSocket });

function getCurrentDateTime(): string {
  const now = new Date();

  const day = now.getDate().toString().padStart(2, '0');
  const month = (now.getMonth() + 1).toString().padStart(2, '0'); // Month is 0-indexed
  const year = now.getFullYear();

  const hours = now.getHours().toString().padStart(2, '0');
  const minutes = now.getMinutes().toString().padStart(2, '0');

  return `${day}.${month}.${year} ${hours}:${minutes}`;
}

async function postMessage(messageContent: string) {
  if (messageContent.trim() === '') {
    alert('Bitte gebe eine Nachricht ein!');
    return;
  }

  try {
    const savedMessage = {"content":messageContent,"date":getCurrentDateTime(),"sender":props.userData?.username};
    console.log(savedMessage);
    stompClient?.send("/app/chat.sendMessage/"+props.activeChannelId, JSON.stringify(savedMessage), {});
    // Clear the input field
    message_content.value = "";
    await axios.post(`${baseUrl}/channels/${props.activeChannelId}/users/${props.userData?.id}/messages`, {
      content: messageContent
    });
  } catch (error) {
    console.error('Error posting message:', error);
    // Optionally, handle the error (e.g., notify the user)
  }
}

async function fetchMessages(channelId: string) {
  try {
    const response = await axios.get(`${baseUrl}/channels/${channelId}/messages`);
    messages.value = response.data;
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


watch(() => props.activeChannelId, (newChannelId, oldChannelId) => {
  if (newChannelId) {
    fetchMessages(newChannelId);
    // If there is an old subscription, unsubscribe first
    if (oldChannelId && currentSubscription) {
      currentSubscription.unsubscribe();
      currentSubscription = null;
    }
    // Then subscribe to the new channel
    subscribeToChannel(newChannelId);
  }
}, { immediate: true });

onMounted(() => {
  if (props.activeChannelId) {
    fetchMessages(props.activeChannelId);
  }
  connectWebSocket();
});
onBeforeUnmount(() => {
  if (currentSubscription) {
    currentSubscription.unsubscribe();
  }
  if (stompClient && stompClient.connected) {
    stompClient.disconnect(() => {
      console.log('Disconnected from WebSocket');
    });
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
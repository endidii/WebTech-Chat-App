<template>
  <div class="sidebar-div">
    <div class="logo-div">
      <img src="/src/assets/chat.png" class="logo_img" alt="logo-img" />
      <p class="app-name">blendr<span id="underscore">_</span></p>
    </div>

    <input type="text" class="search-bar" placeholder="Textkanal Beitreten" />
    <img id="search-icon" src="../assets/search.png" alt="search-icon" />

    <p class="side-tags">Textkanäle</p>

    <TextchannelButton
        @channel-button-clicked="onChannelButtonClicked"
        v-if="user"
        :activeChannelId="activeChannelId"
        :user-data="user">
    </TextchannelButton>

    <p class="side-tags">Direktnachrichten</p>

    <button id="button1" class="chat-button">
      <img class="user-img" src="../assets/cat2.png" alt="image" />
      <span class="username">Tony (ereshkigal)</span>
    </button>

    <button class="chat-button">
      <img class="user-img" src="../assets/image1.png" alt="image" />
      <span class="username">Tony (2ny)</span>
    </button>

    <button class="chat-button">
      <img class="user-img" src="../assets/image2.png" alt="image" />
      <span class="username">Max</span>
    </button>

    <button class="chat-button">
      <img class="user-img" src="../assets/picture2.png" alt="image" />
      <span class="username">Minh Nguyet</span>
    </button>

    <button class="chat-button">
      <img class="user-img" src="../assets/picture1.jpeg" alt="image" />
      <span class="username">Phuc</span>
    </button>

    <AddDirectchannelButton v-if="user" :user-data="user"></AddDirectchannelButton>

    <AddChannelButton v-if="user" :user-data="user"></AddChannelButton>
  </div>

  <div class="channel-description">
    <p class="channel-name"> <span v-if="isInputDisabled === false" class="hashtag">#</span> {{channelName}}</p>
  </div>

  <div class="memberlist-div">
    <p id="memberlist-tag">Mitgliederliste</p>
    <member></member>
  </div>

  <input v-model="message_content"
         :disabled="isInputDisabled"
         v-on:keyup.enter="postMessage(message_content)"
         type="text" id="message-input"
         placeholder="Nachricht eingeben" />

  <div class="logged-in-div">
    <img src="../assets/cat1.jpeg" alt="user-img" />
    <div class="user-info-div">
      <p id="angemeldet-als">Angemeldet als:</p>
      <p v-if="user" id="username-p">{{ user.username }}</p>
      <p v-if="user" id="userId-p">ID: {{ user.id }}</p>
    </div>
  </div>
</template>

<script setup lang="ts">
import '../styles/memberlist.css'
import '../styles/sidebar.css'
import '../styles/channel-description.css'
import '../styles/input.css'
import '../styles/logged-in.css'
import '../components/Textchannel-button.vue'
import TextchannelButton from '@/components/Textchannel-button.vue'
import AddChannelButton from '@/components/Add-Textchannel-button.vue'
import AddDirectchannelButton from "@/components/Add-Directchannel-button.vue";
import member from '@/components/Memberlist-member.vue'
import { onMounted, ref } from 'vue'
import { useRoute } from 'vue-router';
import axios from 'axios';

type User = {
  id: string;
  username: string;
  email: string;
  password: string;
  messages: any[];
  textchannels: (Channel)[];
  directchannels: any[];
};
type Channel = {
  id: string;
  name: string;
  description: string;
  users: User[];
  messages: any[];
};

const user = ref();
const userId = ref("")
const route = useRoute();
const channelName = ref("");
const activeChannelId = ref<string | undefined>(undefined);
const isInputDisabled = ref(true);
let message_content = ref("");

onMounted(() => {
  userId.value = route.params.userId as string;
  axios
    .get("http://localhost:8080/users/" + userId.value)
    .then((response) => {
      console.log(response.data);
      console.log(userId.value);
      user.value = response.data;
    })
})

function onChannelButtonClicked(channel: Channel) {
  console.log("channel-button clicked, name: " + channel.name)
  channelName.value = channel.name;
  activeChannelId.value = channel.id;
  isInputDisabled.value = false;
  console.log("enableInput")
}

function postMessage(message_content: string) {
  axios
    .post("http://localhost:8080/channels/" + activeChannelId.value + "/users/" + userId.value  + "/messages",
        {
          content: message_content
        })
    .then((response) => {
      console.log(response.data);
    })
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&family=Roboto:wght@400;500;700&display=swap');
body {
  font-family: 'Poppins', sans-serif;
  background-color: #222429;
  margin-bottom: 1000px;
}
@media (max-width: 1000px) {
  .memberlist-div {
    display: none;
  }
  .channel-description {
    right: 0;
  }
  .logged-in-div {
    display: none;
  }
  #message-input {
    right: 20px;
  }
}
.hashtag{
  font-family: 'Poppins', sans-serif;
  font-weight: bold;
}
</style>
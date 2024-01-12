<template>
  <div class="chatUI-div">
    <div class="sidebar-div">
      <div class="logo-div">
        <img src="/src/assets/chat.png" class="logo_img" alt="logo-img" />
        <p class="app-name">blendr<span id="underscore">_</span></p>
      </div>

      <input v-on:keyup.enter="addUserToChannel(channelNameInput)"
             v-model="channelNameInput"
             type="text" class="search-bar"
             placeholder="Textkanal Beitreten" />

      <img id="search-icon" src="../assets/search.png" alt="search-icon" />

      <p class="side-tags">Textkanäle</p>

      <TextchannelButton
        :key="userKey"
        @channel-button-clicked="onChannelButtonClicked"
        v-if="user"
        :activeChannelId="activeChannelId"
        :user-data="user">
      </TextchannelButton>

      <AddDirectchannelButton v-if="user" :user-data="user"></AddDirectchannelButton>

      <AddChannelButton v-if="user" :user-data="user" @channelAdded="updateUserInfo"></AddChannelButton>
    </div>

    <div class="channel-description">
      <p class="channel-name"> <span v-if="!isInputDisabled" class="hashtag">#</span> {{channelName}}</p>
    </div>

    <div class="memberlist-div">

      <p id="memberlist-tag">Mitgliederliste</p>

      <member :activeChannelId="activeChannelId"></member>

        <UserPopup :visible="showPopup" @update:visible="showPopup = $event" />
        <button class="logged-in-button" @click="togglePopup" >
          <div class="logged-in-div">
            <img src="../assets/cat1.jpeg" alt="user-img" />
            <div class="user-info-div">
              <p id="angemeldet-als">Angemeldet als:</p>
              <p v-if="user" id="username-p">{{ user.username }}</p>
              <p v-if="user" id="userId-p">ID: {{ user.id }}</p>
            </div>
          </div>
        </button>

    </div>

    <MessageHistory
      :isInputDisabled="isInputDisabled"
      :userData="user"
      :active-channel-id="activeChannelId"></MessageHistory>
  </div>

</template>

<script setup lang="ts">
import '../styles/memberlist.css'
import '../styles/sidebar.css'
import '../styles/channel-description.css'
import '../styles/logged-in.css'
import '../components/Textchannel-button.vue'
import TextchannelButton from '@/components/Textchannel-button.vue'
import AddChannelButton from '@/components/Add-Textchannel-button.vue'
import AddDirectchannelButton from "@/components/Add-Directchannel-button.vue";
import UserPopup from "@/components/UserPopup.vue";
import member from '@/components/Memberlist-member.vue'
import { onMounted, ref, watch } from "vue";
import { useRoute } from 'vue-router';
import axios from 'axios';
import MessageHistory from "@/components/MessageHistory.vue";

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
const baseUrl = import.meta.env.VITE_BACKEND_BASE_URL;
const userKey = ref(0);
const channelNameInput = ref("");

watch(user, () => {
  userKey.value++;
}, { deep: true });

onMounted(() => {
  userId.value = route.params.userId as string;
  axios
    .get(`${baseUrl}/users/` + userId.value)
    .then((response) => {
      console.log(response.data);
      console.log(userId.value);
      user.value = response.data;
    })
})
const showPopup = ref(false);

function togglePopup() {
  showPopup.value = !showPopup.value;
}
function updateUserInfo(userOutput:User){
  axios
    .get(`${baseUrl}/users/` + userOutput.id)
    .then((response) => {
      console.log(response.data);
      console.log(userId.value);
      user.value = response.data;
    })
}

function onChannelButtonClicked(channel: Channel) {
  console.log("channel-button clicked, id: " + channel.id)
  channelName.value = channel.name;
  activeChannelId.value = channel.id;
  isInputDisabled.value = false;
  console.log("enableInput")
}
function addUserToChannel(channelName:string){
  axios
    .post(`${baseUrl}/channels/name/` + channelName + "/users/" + userId.value)
    .then(() => {
      console.log("user added to channel")
      updateUserInfo(user.value)
      channelNameInput.value = "";
    })

}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&family=Roboto:wght@400;500;700&display=swap');
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
    width: calc(100% - 570px);
  }
}
.hashtag{
  font-family: 'Poppins', sans-serif;
  font-weight: bold;
}
.chatUI-div{
  display: grid;
  grid-template-columns: 280px 1fr 280px;
  grid-template-rows: [row1-start] 130px [row1-end] 1fr [line-2] 100px [last-line];
  height: 100vh;
}
</style>
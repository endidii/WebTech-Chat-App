<template>
  <div class="sidebar-div">
    <div class="logo-div">
      <img src="/src/assets/chat.png" class="logo_img" alt="logo-img" />
      <p class="app-name">blendr<span id="underscore">_</span></p>
    </div>

    <input type="text" class="search-bar" placeholder="Suchen" />
    <img id="search-icon" src="../assets/search.png" alt="search-icon" />

    <p class="side-tags">Textkanäle</p>

    <TextchannelButton></TextchannelButton>

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

    <button class="join-button">Textkanal hinzufügen</button>

    <button class="direct-chat-button">Neue Direktnachricht</button>
  </div>

  <div class="channel-description">
    <p class="channel-name"># web-tech</p>
    <button class="leave-button">
      <span>verlassen</span>
      <img src="../assets/leave-icon.png" alt="leave" />
    </button>
  </div>

  <div class="memberlist-div">
    <p id="memberlist-tag">Mitgliederliste</p>
    <member></member>
  </div>

  <input type="text" id="message-input" placeholder="Nachricht eingeben" />

  <div class="logged-in-div">
    <img src="../assets/cat1.jpeg" alt="user-img" />
    <p>
      Angemeldet als: <br />
      Andy Thieu
    </p>
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
import member from '@/components/Memberlist-member.vue'
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
  #message-input{
    right: 20px;
  }
}
</style>
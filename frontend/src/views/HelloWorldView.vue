<script lang="ts">
import "../assets/memberlist.css"
import "../assets/sidebar.css"
import "../assets/channel-description.css"
</script>

<template>

    <div class="sidebar-div">
      <div class="logo-div">
        <img src="src/assets/chat.png" class="logo_img" alt="logo-img">
        <p class="app-name">blendr<span id="underscore">_</span></p>
      </div>
      <input type="text" class="search-bar" placeholder="Suchen">
      <p class="side-tags">Textkanäle</p>
      <div class="channel-div">
        <button class="channel-button"><span class="chat-tag"># web-tech</span></button>
      </div>
      <div class="channel-div">
        <button class="channel-button"><span class="chat-tag"># verteilte-anwendungen</span></button>
      </div>
      <div class="channel-div">
        <button class="channel-button"><span class="chat-tag"># unternehmenssoftware</span></button>
      </div>
      <p class="side-tags">Direktnachrichten</p>
      <div class="chat-div">
        <img src="../assets/cat2.png" alt="cat-image">
        <button class="channel-button"><span class="chat-tag">Tony Le (ereshkigal)</span></button>
      </div>
      <div class="chat-div">
        <img src="../assets/image1.png" alt="image">
        <button class="channel-button"><span class="chat-tag">Tony (2ny)</span></button>
      </div>
      <div class="chat-div">
        <img src="../assets/image2.png" alt="image">
        <button class="channel-button"><span class="chat-tag">Max</span></button>
      </div>

      <div class="join-div">
        <button class="join-button">Textkanal hinzufügen</button>
      </div>
    </div>

    <div class="channel-description">
      <p class="channel-name"># web-tech</p>
      <button class="leave-button">
        <span>verlassen</span>
        <img src="../assets/leave-icon.png">
      </button>
    </div>

    <div class="memberlist-div">
      <p id="memberlist-tag">Mitgliederliste</p>
      <div class="member">
        <img class="user-img" src="../assets/cat1.jpeg" alt="user-img">
        <p class="username">Andy Thieu</p>
      </div>
    </div>


</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;700&family=Roboto:wght@400;500;700&display=swap');
body{
  font-family: 'Poppins', sans-serif;
  background-color: #222429;
}
</style>
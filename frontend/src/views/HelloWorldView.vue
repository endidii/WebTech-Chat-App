<script setup lang="ts">
import "../assets/memberlist.css"
import "../assets/sidebar.css"
</script>

<template>
  <div class="sidebar-div">
    <div class="logo-div">
      <img src="src/assets/chat.png" class="logo_img">
      <p class="app-name">blendr<span id="underscore">_</span></p>
    </div>
  </div>

  <div class="memberlist-div">
    <p id="memberlist-tag">Mitgliederliste</p>
    <div class="member">
      <img class="user-img" src="../assets/cat1.jpeg" alt="">
      <p class="username">Andy Thieu</p>
    </div>
  </div>
</template>

<style>
body{
  background-color: #222429;
}
</style>
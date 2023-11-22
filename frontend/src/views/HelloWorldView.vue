<script setup lang="ts">
</script>

<template>
  <div class="logo-div">
    <img src="src/assets/chat.png" class="logo_img">
    <p class="app-name">ChatTalk</p>
  </div>
</template>

<style>
@import url('https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap');
.logo_img{
  height: 100px;
  margin-right: 10px;
}
body{
  background-color: white;
}
.app-name{
  color: black;
  font-family: "Roboto", sans-serif;
  font-weight: bold;
  font-size: 40px;
}
.logo-div{
  display: flex;
  align-items: center;
}
</style>
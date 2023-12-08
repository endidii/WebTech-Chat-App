<template>
  <div class="page-div">
    <div class="logo-div-register">
      <img src="/src/assets/chat.png" class="logo_img" alt="logo-img" />
      <p class="blendr-name">blendr<span id="underscore">_</span></p>
    </div>

    <RegistrierungComponent @user-added="pushUser"></RegistrierungComponent>

  </div>
</template>

<script setup lang="ts">
import "@/components/Registrierung-component.vue"
import RegistrierungComponent from "@/components/Registrierung-component.vue";
import { ref } from "vue";
import router from "@/router";
type User = {
  id: string;
  username: string;
  email: string;
}
const userId = ref("");
const username = ref("");
const email = ref("");

function pushUser(user: User){
  userId.value = user.id;
  username.value = user.username;
  email.value = user.email;

  router.push({ name: 'ChatUI', params: { userId: userId.value, username: username.value, email: email.value } })
}
</script>

<style>
body{
  font-family: 'Poppins', sans-serif;
  background-color: #222429;
}
.page-div{
  display: flex;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.name-mail-div p {
  font-size: 20px;
  margin-top: 10px;
}
.password-div p {
  font-size: 20px;
  margin-top: 10px;
}

.logo_img{
  height: 150px;
}
#underscore{
  font-size: 100px;
}
.logo-div-register{
  display: flex;
  align-items: center;
  justify-content: end;
  margin-right: 80px;
}
.blendr-name{
  color: white;
  font-weight: bold;
  font-size: 40px;
}
@media (max-width: 1000px) {
  .page-div{
    flex-direction: column;
    align-items: center;
    column-gap: 20px;
  }
  .logo-div-register{
    margin-bottom: 30px;
    padding-left: 70px;
  }
}
</style>

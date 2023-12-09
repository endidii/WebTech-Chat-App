<template>
<form class="sign-up-div">
<p id="register-p">Registrieren</p>

<div class="name-mail-div">
  <div class="input-div">
    <p>Name</p>
    <input v-model="username" type="text" class="user-input" id="name-input">
  </div>

  <div class="input-div">
    <p>Email</p>
    <input v-model="email" type="text" class="user-input" id="email-input">
  </div>
</div>

<div class="password-div">
  <p>Passwort</p>
  <input v-model="password" type="password" class="user-input" id="password-input">
</div>

<button type="button" class="register-button" @click="onClick">Registrieren</button>

<div class="bereits-user-div">
  <p>Bereits ein User?</p>
  <a href="">Einloggen</a>
</div>

</form>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';
import router from "@/router";

const username = ref("")
const email = ref("")
const password = ref("")
const emit = defineEmits(["userAdded"])

function onClick(){
  axios
    .post("http://localhost:8080/users", {
      username: username.value,
      email: email.value,
      password: password.value
    })
    .then((response) => {
      console.log("user erstellt: " + response.data.id)
      router.push({ name: 'ChatUI', params: { userId: response.data.id, }})
      emit("userAdded", response.data)
    })
}
</script>

<style scoped>
.input-div{
  width: 100%;
}
.register-button{
  width: 100%;
  margin-top: 20px;
  margin-bottom: 10px;
  font-family: 'Poppins', sans-serif;
  background-color: #006EDB;
  border: none;
  color: white;
  height: 45px;
  cursor: pointer;
  border-radius: 4px;
}
.user-input{
  font-family: 'Poppins', sans-serif;
  border-style: solid;
  border-width: 2px;
  border-radius: 4px;
  border-color: black;
  height: 40px;
  font-size: 17px;
  padding-left: 10px;
  width: 100%;
}
#register-p{
  font-size: 30px;
}
.name-mail-div{
  display: flex;
  column-gap: 20px;
}
.sign-up-div{
  background-color: white;
  color: black;
  padding: 20px;
  display: flex;
  flex-direction: column;
  width: 600px;
  border-radius: 8px;
}
.bereits-user-div{
  display: flex;
  column-gap: 20px;
  margin-top: 10px;
}
</style>
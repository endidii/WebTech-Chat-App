<template>
<div class="sign-up-div">
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
  <input v-model="password" type="text" class="user-input" id="password-input">
</div>
<button class="register-button" @click="onClick">Registrieren</button>
</div>
</template>

<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';

const username = ref("")
const email = ref("")
const password = ref("")
const emit = defineEmits(["userAdded"])

function onClick(){

  console.log("registered: ")
  axios
    .post("http://localhost:8080/users", {username: username.value, email: email.value, password: password.value })
    .then((response) => {
      console.log("user erstellt: " + response.data.id)
      emit("userAdded", response.data)
    })
}
</script>

<style scoped>
.input-div{
  width: 100%;
}
#name-input{
  width: 100%;
}
#email-input{
  width: 100%;
}
#password-input{
  width: 100%;
}
.register-button{
  width: 100%;
  margin-top: 20px;
  font-family: 'Poppins', sans-serif;
  background-color: #006EDB;
  border: none;
  color: white;
  height: 45px;
}
.user-input{
  border-style: solid;
  border-width: 2px;
  border-color: black;
  height: 40px;
  font-size: 20px;
  padding-left: 10px;
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
}
</style>
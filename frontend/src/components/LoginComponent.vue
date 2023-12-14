<template>
  <form class="sign-up-div">
    <p id="register-p">Anmelden</p>

    <div class="form-div">
      <div class="input-div">
        <p>Email</p>
        <input v-model="email" type="email" class="user-input" id="email-input">
      </div>

      <div class="input-div">
        <p>Passwort</p>
        <input v-model="password" type="password" class="user-input" id="password-input">
      </div>
    </div>

    <button type="button" class="register-button" @click="onClick">Anmelden</button>

    <div class="bereits-user-div">
      <p>Du hast kein Konto?</p>
      <router-link to="/">Registrieren</router-link>
    </div>

  </form>
</template>
<script setup lang="ts">
import { ref } from "vue";
import axios from "axios";
import router from "@/router";

const username = ref("")
const email = ref("")
const password = ref("")
const emit = defineEmits(["userAdded"])

function onClick(){
  if (username.value.trim() === '' || email.value.trim() === '' || password.value.trim() === '') {
    alert('Please fill in all fields');
    return;
  }
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
  width: 96%;
}
#register-p{
  font-size: 30px;
  font-weight: bold;
  margin: 10px 0 10px 0;
}
.form-div{
  display: flex;
  flex-direction: column;
}
.form-div p {
  margin: 10px 0 10px 0;
  font-size: 22px;
}
.sign-up-div{
  background-color: white;
  color: black;
  display: flex;
  flex-direction: column;
  width: 400px;
  border-radius: 8px;
  padding: 20px;
}
.bereits-user-div{
  display: flex;
  column-gap: 20px;
  margin-top: 10px;
  flex-direction: row;
}
.bereits-user-div a {
  color: #006EDB;
}
.bereits-user-div p {
  margin: 0;
}
</style>
<template>
  <div class="popup" v-if="visible">
    <button class="popup-button-delete" @click="deleteAccount">Delete Account</button>
    <button class="popup-button-logout" @click="logout">Logout</button>
  </div>
</template>

<script setup lang="ts">
import router from "@/router";
import axios from "axios";

const props = defineProps({
  visible: Boolean,
  userId: String
});

const emit = defineEmits(['update:visible']);

function deleteAccount() {
  axios.delete("http://localhost:8080/users/"+props.userId).then(response => {
    console.log(response.data);
    router.push({name: 'LoginView'})
  });
}

function logout() {
  router.push({name: 'LoginView'})
}
</script>

<style scoped>
.popup {
  position: absolute;
  bottom: 130px;
  right: 0;
  background-color: #006EDB;
  display: flex;
  flex-direction: row;
  width: 280px;
  justify-content: space-around;
  padding-top: 16px;
  border-radius: 6px 6px 0 0;
}
.popup-button-delete{
  background-color: #E3505E;
  color: white;
  border: none;
  padding: 5px 10px;
  font-size: 12px;
  font-family: 'Poppins', sans-serif;
  border-radius: 4px;
  cursor: pointer;
  width: 40%;
  height: 40px;
}
.popup-button-logout{
  background-color: white;
  color: black;
  border: none;
  padding: 5px 10px;
  font-size: 16px;
  font-family: 'Poppins', sans-serif;
  border-radius: 4px;
  cursor: pointer;
  width: 40%;
  height: 40px;
}
</style>

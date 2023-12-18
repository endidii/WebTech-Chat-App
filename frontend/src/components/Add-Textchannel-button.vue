<template>
  <form class="create-channel-form">
    <button id="add-channel-button" class="join-button" type="button" @click="openModal">Textkanal erstellen</button>
  </form>

  <form v-if="isOpen" class="modal">
    <div class="modal-content">
      <span @click="closeModal" class="close">&#10005;</span>
      <p class="header">Textkanal erstellen</p>
        <div class="name-description-div">
          <div class="input-div">
            <p class="input-tag">Name</p>
            <input v-model="name" type="text" class="user-input" id="name-input">
          </div>

          <div class="input-div">
            <p class="input-tag">Beschreibung</p>
            <input v-model="description" type="text" class="user-input" id="description-input">
          </div>
        </div>
      <button class="create-channel-button" @click="createChannel" type="button">Textkanal erstellen</button>
    </div>
  </form>

</template>

<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';

const id = ref("")
const name = ref("")
const description = ref("")
let isOpen = ref(false)
const baseUrl = import.meta.env.VITE_BACKEND_BASE_URL;
const emit = defineEmits(["channelAdded"])

const props = defineProps({
  userData: Object
});

function openModal(){
  isOpen.value = true
  console.log("Modal isOpen: " + isOpen.value)
}
function closeModal(){
  isOpen.value = false
  console.log("Modal isOpen: " + isOpen.value)
}
function createChannel(){
  if (name.value.trim() === '') {
    alert('Bitte gebe einen Namen ein!');
    return;
  }
  //create channel
  axios.post(`${baseUrl}/channels/users/${props.userData?.id}`, {
    name: name.value,
    description: description.value
  })
  .then(function (response) {
    id.value = response.data.id;
    name.value = "";
    description.value = response.data.description;
    console.log("id value: "+id.value);
    console.log(response);
    //add user to channel
    axios.post(`${baseUrl}/channels/${id.value}/users/${props.userData?.id}`).then(function (response) {
      isOpen.value = false;
      emit("channelAdded", props.userData)
    })
  })
  //close modal
  isOpen.value = false;
}

</script>

<style scoped>
/* The Modal (background) */
.modal {
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto; /* Enable scroll if needed */
  background-color: rgba(0,0,0,0.4);
  font-family: 'Poppins', sans-serif;
}
.name-description-div{
  width: 100%;
}
.input-tag{
  margin: 10px 0 0 0;
}
.modal-content {
  background-color: white;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 20%;
  border-radius: 6px;
  color: black;
}
.close {
  width: 32px;
  height: 32px;
  opacity: 0.3;
}
.close:hover {
  opacity: 1;
  cursor: pointer;
}
.create-channel-button{
  background-color: #006EDB;
  border: none;
  color: white;
  padding: 10px 20px;
  font-size: 16px;
  font-family: 'Poppins', sans-serif;
  margin-top: 30px;
  width: 100%;
}
.create-channel-button:hover{
  cursor: pointer;
}
.header{
  font-size: 30px;
  font-weight: bold;
}
.input-tag{
  font-size: 20px;
  font-weight: 500;
}
.user-input{
  margin: 10px 0 10px 0;
  padding: 10px 0 10px 10px;
  width: 96%;
  font-family: 'Poppins', sans-serif;
  font-size: 18px;
}
</style>
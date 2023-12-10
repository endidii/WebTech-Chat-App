<template>
  <form class="create-channel-form">
    <button id="add-channel-button" class="join-button" type="button" @click="openModal">Textkanal erstellen</button>
  </form>

  <div v-if="isOpen" id="myModal" class="modal">
    <div class="modal-content">
      <span @click="closeModal" class="close">&times;</span>
      <p>Textkanal erstellen</p>
        <div class="name-description-div">
          <div class="input-div">
            <p>Name</p>
            <input v-model="name" type="text" class="user-input" id="name-input">
          </div>

          <div class="input-div">
            <p>Beschreibung</p>
            <input v-model="description" type="text" class="user-input" id="description-input">
          </div>
        </div>
      <button>Textkanal erstellen</button>
    </div>
  </div>

</template>

<script setup lang="ts">
import { ref } from 'vue';
import axios from 'axios';

const name = ref("")
const description = ref("")
let isOpen = ref(false)

function openModal(){
  isOpen.value = true
  console.log("Modal isOpen: " + isOpen.value)
}
function closeModal(){
  isOpen.value = false
  console.log("Modal isOpen: " + isOpen.value)
}

</script>

<style scoped>
/* The Modal (background) */
.modal {
  position: fixed;
  z-index: 10;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  overflow: auto; /* Enable scroll if needed */
  background-color: rgba(0,0,0,0.4);
}

.modal-content {
  background-color: white;
  margin: 15% auto; /* 15% from the top and centered */
  padding: 20px;
  border: 1px solid #888;
  width: 40%;
  border-radius: 6px;
}
.close {
  color: #aaa;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: black;
  text-decoration: none;
  cursor: pointer;
}

</style>
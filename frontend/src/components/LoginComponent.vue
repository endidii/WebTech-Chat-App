<template>
  <div class="login-div">
    <form class="sign-up-div">
      <p id="register-p">Anmelden</p>

      <div class="form-div">
        <div class="input-div">
          <p>Email</p>
          <input v-model="email" type="email" class="user-input" id="email-input" @click="resetError">
        </div>

        <div class="input-div">
          <p>Passwort</p>
          <input v-model="password" type="password" class="user-input" id="password-input">
        </div>
      </div>

      <button type="button" class="register-button" @click="getUserByEmail">Anmelden</button>

      <div class="bereits-user-div">
        <p>Du hast kein Konto?</p>
        <router-link to="/signup">Registrieren</router-link>
      </div>
    </form>

    <p v-if="errorMessage" class="error-message">{{ errorMessage }} <br> {{email}} </p>
  </div>
</template>
<script setup lang="ts">
import { ref } from "vue";
import axios from "axios";
type Channel = {
  id: string;
  name: string;
  description: string;
  users: User[];
  messages: any[];
};

type User = {
  id: string;
  username: string;
  email: string;
  password: string;
  messages: any[];
  textchannels: (Channel)[];
  directchannels: any[];
};

const email = ref('');
const password = ref('');
const errorMessage = ref('');
const user = ref(null);
const emit = defineEmits(["userLoggedIn"])

const getUserByEmail = async () => {
  if (email.value.trim() === '' || password.value.trim() === '') {
    alert('Please fill in all fields');
    return;
  }
  errorMessage.value = ''; // Reset error message
  user.value = null; // Reset user data
  try {
    const response = await axios.get('https://blendr-backend.onrender.com/users/email',{
      params: {
        email: email.value,
      }
    });
    // Handle the response, e.g., store the user data
    user.value = response.data; // Store the user data
    console.log(user.value)
  } catch (error:any) {
    if (error.response && error.response.status === 404) {
      errorMessage.value = "No user found with the email:";
      const emailInput = document.getElementById('email-input');
      if (emailInput) emailInput.style.border = '2px solid #E3505E';
    } else {
      errorMessage.value = 'An error occurred. Please try again later.';
    }
  }
};

function resetError(){
  errorMessage.value = '';
  const emailInput = document.getElementById('email-input');
  if (emailInput) emailInput.style.border = "2px solid black";
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
  background-color: #006EDB;
  border: none;
  color: white;
  height: 45px;
  cursor: pointer;
  border-radius: 4px;
  transition: filter 0.3s;
}
.register-button:hover{
  filter: brightness(95%);
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
.error-message {
  color: white;
  font-size: 18px;
  background-color: #E3505E;
  border-radius: 4px;
  padding: 5px 10px 5px 10px;
  margin-top: 16px;
}
.login-div{
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
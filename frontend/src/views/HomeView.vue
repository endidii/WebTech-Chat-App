<template>
  <div class="logo-div">
    <img src="src/assets/chat.png" class="logo_img" alt="logo-img" />
    <p class="app-name">blendr<span id="underscore">_</span></p>
  </div>

  <div class="sign-up-div">
    <p id="register-p">Registrieren</p>

    <div class="name-mail-div">
      <div class="input-div">
        <p>Name</p>
        <input type="text" class="user-input" id="name-input">
      </div>

      <div class="input-div">
        <p>Email</p>
        <input type="text" class="user-input" id="email-input">
      </div>
    </div>

    <div class="password-div">
      <p>Passwort</p>
      <input type="text" class="user-input" id="password-input">
    </div>
    <button class="register-button">Registrieren</button>
  </div>
</template>

<script lang="ts">
export default{

}
</script>

<style>
body{
  font-family: 'Poppins', sans-serif;
  background-color: #222429;
  color: white;
}
.sign-up-div{
  background-color: #006EDB;
  padding: 20px;
}
.app-name{
  color: white;
  font-size: 80px;
}
.logo_img{
  height: 150px;
}
#underscore{
  font-size: 100px;
}
.name-mail-div{
  display: flex;
  column-gap: 20px;
}
#password-input{
  width: 100%;
}
.register-button{
  width: 100%;
  margin-top: 20px;
}
</style>

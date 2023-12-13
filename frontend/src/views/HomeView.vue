<template>
  <div class="page-div">
    <div class="logo-div-register">
      <img src="/src/assets/chat.png" class="logo_img" alt="logo-img" />
      <p class="blendr-name">blendr<span id="underscore">_</span></p>
    </div>

    <RegistrierungComponent></RegistrierungComponent>

  </div>
</template>

<script setup lang="ts">
import "@/components/Registrierung-component.vue"
import RegistrierungComponent from "@/components/Registrierung-component.vue";
</script>

<style>
body{
  font-family: 'Poppins', sans-serif;
  background-color: #222429;
  margin-bottom: 100px;
  margin-top: 130px;
  margin-left: 280px;
  margin-right: 280px;
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

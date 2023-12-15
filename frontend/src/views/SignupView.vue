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

<style scoped>

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
  height: 80px;
}
#underscore{
  font-size: 50px;
}
.logo-div-register{
  display: flex;
  align-items: center;
  justify-content: end;
  margin-right: 100px;
}
.blendr-name{
  color: white;
  font-weight: bold;
  font-size: 50px;
}
</style>

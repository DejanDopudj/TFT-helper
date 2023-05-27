<script setup>
import { ref } from 'vue'
import { isUserLoggedIn, login } from '../../services/authService'

if (isUserLoggedIn()) window.location.href = '/'

const username = ref('')
const password = ref('')
const usernameErrorPresent = ref(false)
const passwordErrorPresent = ref(false)
const errorMessage = ref('')

const handleLogin = () => {
  if (username.value.length === 0) {
    usernameErrorPresent.value = true
  }
  else if (password.value.length === 0) {
    passwordErrorPresent.value = true
  } else {
    login(username.value, password.value,
    () => {
      window.location.href = '/';
    },
    () => {
      errorMessage.value = 'Incorrect credentials.'
    });
  }
}

</script>

<template>
  <div class="mx-auto w-72">
    <div class="text-4xl text-light font-light">Log In</div>
    <div class="flex flex-col gap-y-6 mt-10 mb-12 tracking-wide">
      
      <div class="w-full">
        <label class="block tracking-wide text-light text-xs font-bold mb-1"
        for="login-email">
          Username or E-Mail
        </label>
        <input class="appearance-none block w-full text-light tracking-wide
        placeholder-medium caret-medium border-b bg-transparent leading-tight outline-none
        py-1 focus:transition-color duration-300" @input="usernameErrorPresent = false; errorMessage = '';"
        id="login-email" type="text" placeholder="Your username or email" autofocus="autofocus"
        :class="[ usernameErrorPresent ? 'border-red-500' : 'border-light focus:border-primary' ]"
        v-model="username" @keydown.enter="handleLogin">
      </div>

      <div class="w-full">
        <label class="block tracking-wide text-light text-xs font-bold mb-1"
        for="login-password">
          Password
        </label>
        <input class="appearance-none block w-full text-light tracking-wide
        placeholder-medium caret-medium border-b bg-transparent leading-tight outline-none
        py-1 focus:transition-color duration-300" type="password"
        :class="[ passwordErrorPresent ? 'border-red-500' : 'border-light focus:border-primary' ]"
        @input="passwordErrorPresent = false; errorMessage = '';" @keydown.enter="handleLogin"
        id="login-password" placeholder="Your password" v-model="password">
      </div>

    </div>

    <button type="submit" class="w-full h-16 bg-secondary active:bg-opacity-75
    text-black font-bold tracking-wide" id="login-button" @click="handleLogin">
      LOGIN
    </button>
    <div class="h-1 text-center">
      <div v-if="errorMessage.length !== 0" class="text-sm text-primary font-medium">
        {{ errorMessage }}
      </div>
    </div>

  </div>
</template>

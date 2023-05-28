<script setup>
import { ref } from 'vue'
import { isUserLoggedIn, register } from '../../services/authService'

if (isUserLoggedIn()) window.location.href = '/'

const username = ref('')
const email = ref('')
const password = ref('')
const confirmPassword = ref('')
const emailErrorMessage = ref('')
const usernameErrorMessage = ref('')
const passwordErrorMessage = ref('')
const confirmPasswordErrorMessage = ref('')
const errorMessage = ref('')

const isRegistrationSuccessful = ref(false)

const handleRegister = () => {
  if (isDataValid()) {
    register(email.value, username.value, password.value,
    () => {
      isRegistrationSuccessful.value = true
    },
    (msg) => {
      errorMessage.value = 'Error. Check your information.'
      if (msg) errorMessage.value = msg
    });
  }
}

const isDataValid = () => {
  let valid = true
  if (email.value.length === 0) {
    emailErrorMessage.value = 'Email cannot be empty'
    valid = false
  }
  else if (!email.value.match(/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g)) {
    emailErrorMessage.value = 'Email is invalid'
    valid = false
  }
  if (username.value.length === 0) {
    usernameErrorMessage.value = 'Username cannot be empty'
    valid = false
  }
  else if (username.value.length < 3) {
    usernameErrorMessage.value = 'Username is too short'
    valid = false
  }
  else if (username.value.length > 32) {
    usernameErrorMessage.value = 'Username is too long'
    valid = false
  }
  if (password.value.length === 0) {
    passwordErrorMessage.value = 'Password cannot be empty'
    valid = false
  }
  else if (password.value.length < 8) {
    passwordErrorMessage.value = 'Password is too short'
    valid = false
  }
  if (confirmPassword.value.length === 0) {
    confirmPasswordErrorMessage.value = 'Password confirmation cannot be empty'
    valid = false
  }
  else if (password.value !== confirmPassword.value) {
    confirmPasswordErrorMessage.value = 'Passwords do not match'
    valid = false
  }
  return valid
}

const resetErrorMessages = () => {
  emailErrorMessage.value = ''
  usernameErrorMessage.value = ''
  passwordErrorMessage.value = ''
  confirmPasswordErrorMessage.value = ''
  errorMessage.value = ''
}

</script>

<template>
  <div>
    <div v-if="!isRegistrationSuccessful" class="mx-auto w-72">
      <div class="text-4xl text-light font-light">Register</div>
      <div class="flex flex-col gap-y-6 mt-10 mb-12 tracking-wide">
        
        <div class="w-full">
          <label class="block tracking-wide text-light text-xs font-bold mb-1"
          for="login-email">
            E-Mail
          </label>
          <input class="appearance-none block w-full text-light tracking-wide
          placeholder-medium caret-medium border-b bg-transparent leading-tight outline-none
          py-1 focus:transition-color duration-300" @input="resetErrorMessages()"
          id="login-email" type="text" placeholder="Your email" autofocus="autofocus"
          :class="[ emailErrorMessage ? 'border-red-500' : 'border-light focus:border-primary' ]"
          v-model="email" @keydown.enter="handleRegister">
          <div class="h-1">
            <p v-if="emailErrorMessage.length !== 0" class="text-primary text-sm">
              {{ emailErrorMessage }}
            </p>
          </div>
        </div>

        <div class="w-full">
          <label class="block tracking-wide text-light text-xs font-bold mb-1"
          for="login-username">
            Username
          </label>
          <input class="appearance-none block w-full text-light tracking-wide
          placeholder-medium caret-medium border-b bg-transparent leading-tight outline-none
          py-1 focus:transition-color duration-300" @input="resetErrorMessages()"
          id="login-username" type="text" placeholder="Your username" autofocus="autofocus"
          :class="[ usernameErrorMessage ? 'border-red-500' : 'border-light focus:border-primary' ]"
          v-model="username" @keydown.enter="handleRegister">
          <div class="h-1">
            <p v-if="usernameErrorMessage.length !== 0" class="text-primary text-sm">
              {{ usernameErrorMessage }}
            </p>
          </div>
        </div>

        <div class="w-full">
          <label class="block tracking-wide text-light text-xs font-bold mb-1"
          for="login-password">
            Password
          </label>
          <input class="appearance-none block w-full text-light tracking-wide
          placeholder-medium caret-medium border-b bg-transparent leading-tight outline-none
          py-1 focus:transition-color duration-300" type="password"
          :class="[ passwordErrorMessage ? 'border-red-500' : 'border-light focus:border-primary' ]"
          @input="resetErrorMessages()" @keydown.enter="handleRegister"
          id="login-password" placeholder="Your password" v-model="password">
          <div class="h-1">
            <p v-if="passwordErrorMessage.length !== 0" class="text-primary text-sm">
              {{ passwordErrorMessage }}
            </p>
          </div>
        </div>

        <div class="w-full">
          <label class="block tracking-wide text-light text-xs font-bold mb-1"
          for="login-confirm-password">
            Confirm Password
          </label>
          <input class="appearance-none block w-full text-light tracking-wide
          placeholder-medium caret-medium border-b bg-transparent leading-tight outline-none
          py-1 focus:transition-color duration-300" type="password"
          :class="[ confirmPasswordErrorMessage ? 'border-red-500' : 'border-light focus:border-primary' ]"
          @input="resetErrorMessages()" @keydown.enter="handleRegister"
          id="login-confirm-password" placeholder="Confirm your password" v-model="confirmPassword">
          <div class="h-1">
            <p v-if="confirmPasswordErrorMessage.length !== 0" class="text-primary text-sm">
              {{ confirmPasswordErrorMessage }}
            </p>
          </div>
        </div>

      </div>

      <button type="submit" class="w-full h-16 bg-secondary active:bg-opacity-75
      text-black font-bold tracking-wide" id="login-button" @click="handleRegister">
        REGISTER
      </button>
      <div class="h-1 text-center">
        <div v-if="errorMessage.length !== 0" class="text-sm text-primary font-medium">
          {{ errorMessage }}
        </div>
      </div>

    </div>

    <div v-if="isRegistrationSuccessful" class="mx-auto text-light text-center">
      <div class="text-4xl">
        Success
      </div>
      <div class="flex justify-center gap-x-1.5 text-lg">
        <div>you can now</div>
        <RouterLink to="/login">
          <div class="text-primary font-medium hover:text-opacity-90">Log in</div>
        </RouterLink>
      </div>
    </div>
  </div>
</template>
<script setup>
import { isUserLoggedIn } from '../../services/authService'

const isLoggedIn = isUserLoggedIn()

</script>

<template>
  <div class="flex flex-col h-screen justify-center text-center">
    <div class="text-5xl font-medium text-primary">
      TFT Helper
    </div>
    <div class="text-secondary font-bold mt-1">
      Your Teamfight Tactics coordinator
    </div>
    
    <RouterLink v-if="!isLoggedIn" to="/register">
      <div class="flex justify-center gap-x-6 mt-6">
        <button class="w-32 text-light font-medium border border-light
        hover:opacity-80 rounded-md py-2">Get Started</button>
      </div>
    </RouterLink>

    <RouterLink v-if="isLoggedIn" to="/">
      <div class="flex justify-center gap-x-6 mt-6">
        <button class="w-32 text-light font-medium border border-light
        hover:opacity-80 rounded-md py-2">Begin</button>
      </div>
    </RouterLink>

  </div>
</template>

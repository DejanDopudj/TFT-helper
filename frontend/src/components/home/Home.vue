<script setup>
import { ref } from 'vue'
import { isUserLoggedIn } from '../../services/authService'
import { createGame } from '../../services/gameService'

const isLoggedIn = isUserLoggedIn()
const isGameLoading = ref(false)

const startNewGame = () => {
  isGameLoading.value = true
  createGame(
    (gameId) => {
      isGameLoading.value = false
      window.location.href = `/game/${gameId}`
    },
    () => {
      isGameLoading.value = false
      // boo hoo
    });
}

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

    <div v-if="isLoggedIn" class="flex justify-center gap-x-6 mt-6">
      <button class="flex justify-center gap-x-2 w-32 text-light font-medium border border-light
      hover:opacity-80 rounded-md py-2" @click="startNewGame">
        <font-awesome-icon v-if="isGameLoading" icon="fa-solid fa-circle-notch" class="my-auto animate-spin"/>
        <div>{{ isGameLoading ? 'Loading' : 'Begin' }}</div>
      </button>
    </div>

  </div>
</template>

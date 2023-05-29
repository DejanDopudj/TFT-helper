<script setup>
import { ref } from 'vue'
import { isUserLoggedIn } from '../services/authService'
import PlayerSidebar from '../components/game/PlayerSidebar.vue';
import MainBar from '../components/game/MainBar.vue';
import PlayerEditor from '../components/game/addons/PlayerEditor.vue';

if (!isUserLoggedIn()) window.location.href = '/login'

const playerBeingEdited = ref(null)

const openPlayerEdit = (player) => {
  playerBeingEdited.value = player
}

</script>

<template>
  <div class="relative">
    <PlayerSidebar class="absolute h-screen right-0 my-auto" @edit-player="openPlayerEdit"/>

    <div class="absolute w-full h-screen flex flex-col justify-end pointer-events-none">
      <MainBar class="flex justify-center" @edit-player="openPlayerEdit"/>
    </div>

    <PlayerEditor class="absolute h-screen flex justify-center"
    v-if="playerBeingEdited" :player="playerBeingEdited" @close="playerBeingEdited = null"/>
  </div>
</template>

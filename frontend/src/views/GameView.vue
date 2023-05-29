<script setup>
import { ref } from 'vue'
import { isUserLoggedIn } from '../services/authService'
import PlayerSidebar from '../components/game/PlayerSidebar.vue';
import MainBar from '../components/game/MainBar.vue';
import PlayerEditor from '../components/game/addons/PlayerEditor.vue';
import AugmentComponentSidebar from '../components/game/AugmentComponentSidebar.vue';
import AugmentAdder from '../components/game/addons/AugmentAdder.vue';

if (!isUserLoggedIn()) window.location.href = '/login'

const playerBeingEdited = ref(null)
const openAddAugments = ref(false)
const augmentsList = ref(null)

const openPlayerEdit = (player) => {
  playerBeingEdited.value = player
}

const openAugmentAdder = (augments) => {
  openAddAugments.value = true
  augmentsList.value = augments
}

</script>

<template>
  <div class="relative">
    <PlayerSidebar class="absolute h-screen right-0 my-auto" @edit-player="openPlayerEdit"/>

    <div class="absolute w-full h-screen flex flex-col justify-end pointer-events-none">
      <MainBar class="flex justify-center" @edit-player="openPlayerEdit"/>
    </div>

    <AugmentComponentSidebar class="absolute h-screen left-0 my-auto" @add-augments="openAugmentAdder"/>

    <PlayerEditor class="absolute h-screen flex justify-center"
    v-if="playerBeingEdited" :player="playerBeingEdited" @close="playerBeingEdited = null"/>
    
    <AugmentAdder class="absolute h-screen flex justify-center"
    v-if="openAddAugments" :augments="augmentsList" @close="openAddAugments = false"/>
  </div>
</template>

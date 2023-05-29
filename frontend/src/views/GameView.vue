<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'
import { isUserLoggedIn } from '../services/authService'
import { getGame } from '../services/gameService'
import PlayerSidebar from '../components/game/PlayerSidebar.vue';
import MainBar from '../components/game/MainBar.vue';
import PlayerEditor from '../components/game/addons/PlayerEditor.vue';
import AugmentComponentSidebar from '../components/game/AugmentComponentSidebar.vue';
import AugmentAdder from '../components/game/addons/AugmentAdder.vue';

if (!isUserLoggedIn()) window.location.href = '/login'

const route = useRoute()
const game = ref(null)
const showNotFound = ref(null)

getGame(
  route.params.id,
  (g) => {
    console.log(g)
    game.value = g
  },
  () => {
    showNotFound.value = true
  });

const playerBeingEdited = ref(null)
const openAddAugments = ref(false)

const openPlayerEdit = (player) => {
  playerBeingEdited.value = player
}

const openAugmentAdder = (augments) => {
  openAddAugments.value = true
  game.augments = augments
}

</script>

<template>
  <div>
    <div class="relative" v-if="game">
      <PlayerSidebar class="absolute h-screen right-0 my-auto" @edit-player="openPlayerEdit" :players="game.otherPlayers"/>
      <div class="absolute w-full h-screen flex flex-col justify-end pointer-events-none">
        <MainBar class="flex justify-center" @edit-player="openPlayerEdit" :game="game"/>
      </div>
      <AugmentComponentSidebar class="absolute h-screen left-0 my-auto"
      @add-augments="openAugmentAdder" :augments="game.augments" :components="game.components"/>


      <PlayerEditor class="absolute h-screen flex justify-center"
      v-if="playerBeingEdited" :player="playerBeingEdited" @close="playerBeingEdited = null"
      :selfEdit="game.player.id === playerBeingEdited.id" :gameId="game.id"/>

      <AugmentAdder class="absolute h-screen flex justify-center"
      v-if="openAddAugments" :augments="game.augments" @close="openAddAugments = false"/>
    </div>

    <div v-if="!game && showNotFound" class="h-screen flex flex-col justify-center text-center font-mono">
      <div class="text-xl text-primary">Game Not Found</div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRoute } from 'vue-router'
import { isUserLoggedIn } from '../services/authService'
import { getGame } from '../services/gameService'
import PlayerSidebar from '../components/game/PlayerSidebar.vue';
import MainBar from '../components/game/MainBar.vue';
import TopBar from '../components/game/TopBar.vue';
import ConnectionsButtons from '../components/game/ConnectionsButtons.vue';
import PlayerEditor from '../components/game/addons/PlayerEditor.vue';
import AugmentComponentSidebar from '../components/game/AugmentComponentSidebar.vue';
import AugmentAdder from '../components/game/addons/AugmentAdder.vue';
import ComponentAdder from '../components/game/addons/ComponentAdder.vue';
import AugmentSelector from '../components/game/addons/AugmentSelector.vue';
import AugmentConnector from '../components/game/addons/AugmentConnector.vue';
import ChampConnector from '../components/game/addons/ChampConnector.vue';
import GameFinisher from '../components/game/addons/GameFinisher.vue';

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
const openAddComponent = ref(false)
const openSelectAugment = ref(false)
const openConnectAugments = ref(false)
const openConnectChamps = ref(false)
const openFinishGame = ref(false)

const openPlayerEdit = (player) => {
  playerBeingEdited.value = player
}

const openAugmentAdder = (augments) => {
  openAddAugments.value = true
  game.augments = augments
}

const openComponentAdder = () => {
  openAddComponent.value = true
}

const openAugmentSelector = () => {
  openSelectAugment.value = true
}

const openAugmentConnector = () => {
  openConnectAugments.value = true
}

const openChampConnector = () => {
  openConnectChamps.value = true
}

const openGameFinisher = () => {
  openFinishGame.value = true
}

</script>

<template>
  <div class="h-full">
    <div class="relative h-full" v-if="game && game.place === 0">
      <PlayerSidebar class="absolute h-screen right-0 my-auto pointer-events-none" @edit-player="openPlayerEdit" :players="game.otherPlayers"/>
      <div class="absolute w-full h-screen flex flex-col justify-end pointer-events-none">
        <MainBar class="flex justify-center" @edit-player="openPlayerEdit" :game="game"/>
      </div>
    
      <div class="absolute w-full h-screen flex flex-col justify-start pointer-events-none">
        <TopBar class="flex justify-center" @open-finish-game="openGameFinisher" :game="game"/>
      </div>

      <AugmentComponentSidebar class="absolute h-screen left-0 my-auto"
      @add-augments="openAugmentAdder" @add-component="openComponentAdder" @select-augment="openAugmentSelector"
      :augments="game.augments" :components="game.components" :missingComponents="game.missingComponents"/>

      <ConnectionsButtons class="absolute h-screen right-0 pointer-events-none"
      @show-augment-connections="openAugmentConnector" @show-champ-connections="openChampConnector"/>

      <PlayerEditor class="absolute h-screen flex justify-center"
      v-if="playerBeingEdited" :player="playerBeingEdited" @close="playerBeingEdited = null"
      :selfEdit="game.player.id === playerBeingEdited.id" :gameId="game.id"/>

      <AugmentAdder class="absolute h-screen flex justify-center"
      v-if="openAddAugments" :augments="game.augments" @close="openAddAugments = false"/>
      <ComponentAdder class="absolute h-screen flex justify-center"
      v-if="openAddComponent" @close="openAddComponent = false"
      :missingComponents="game.missingComponents.filter(ms => !game.components.map(x => x.name).includes(ms.name))"/>
      <AugmentSelector class="absolute h-screen flex justify-center"
      v-if="openSelectAugment" @close="openSelectAugment = false"/>

      <AugmentConnector class="absolute h-screen flex justify-center"
      v-if="openConnectAugments" @close="openConnectAugments = false"/>
      <ChampConnector class="absolute h-screen flex justify-center"
      v-if="openConnectChamps" @close="openConnectChamps = false"/>

      <GameFinisher class="absolute h-screen flex justify-center"
      v-if="openFinishGame" @close="openFinishGame = false"/>

    </div>
    
    <div v-if="game && game.place !== 0" class="h-screen flex flex-col justify-center text-center font-mono">
      <div class="text-xl text-primary">Game is finished</div>
    </div>

    <div v-if="!game && showNotFound" class="h-screen flex flex-col justify-center text-center font-mono">
      <div class="text-xl text-primary">Game Not Found</div>
    </div>
  </div>
</template>

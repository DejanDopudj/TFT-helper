<script setup>
import { ref, defineEmits, defineProps, computed } from 'vue'

const emit = defineEmits(['editPlayer'])

const props = defineProps({
  game: {
    type: Object,
    required: true,
  }
})

const game = ref(props.game)

const openPlayerEdit = (player) => {
  emit('editPlayer', player)
}

const getChampImage = (imageName) => {
  const img = '/src/assets/images/champs/' + imageName
  return img
}

</script>

<template>
  <div class="flex justify-center gap-x-6 w-full mb-4 pointer-events-auto select-none">
    
    <!-- player stats -->
    <div class="relative cursor-pointer my-auto -translate-y-1" @click="openPlayerEdit(game.player)">
      <div class="relative rounded-full h-20 w-20 bg-dork mx-auto overflow-hidden">

        <div class="absolute right-0 w-full bottom-0 bg-primary" :style="'height: ' + game.player.hp + '%'">
        </div>
        <div class="flex flex-col justify-center absolute top-1 right-1 rounded-full h-18 w-18 bg-dark">
          <div class="text-3xl text-center text-light font-bold -mt-1">
            {{ game.player.level }}
          </div>
        </div>

      </div>

      <div class="flex justify-center absolute -bottom-2 w-full text-center mx-auto
      rounded-full text-light text-xs">
        <div class="flex flex-col justify-center text-center rounded-full w-5 h-5
        bg-primary text-dark font-extrabold">
          {{ game.player.hp }}
        </div>
      </div>
    </div>
    
    <div class="w-4/5 md:w-160 h-36 border border-dork rounded-xl">
      <!-- gold -->
      <div class="flex justify-center gap-x-2 px-4 -mt-6 w-24 h-6 rounded-t-md bg-dork mx-auto text-light">
        <font-awesome-icon icon="fa-solid fa-coins" class="my-auto"/>
        <div class="font-bold my-auto">
          {{ game.player.gold }}
        </div>
      </div>

      <div v-if="game.composition" class="flex justify-around h-full px-4 py-2 text-center">
        <div v-if="game.composition.defCarry" class="my-auto">
          <div class="relative flex flex-col justify-center h-24 w-24 rounded-full bg-primary">
            <img :src="getChampImage(`${game.composition.defCarry.replaceAll('_', '').replace('Ultimate', '')}_0.jpg`)"
            class="h-23 w-23 rounded-full mx-auto border-2 border-black"/>
            <div class="absolute -bottom-1 w-full text-sm font-bold text-center my-auto">
              <div class="w-min max-w-[5rem] truncate px-2 rounded-full bg-primary text-dark mx-auto">
                {{ game.composition.defCarry.replaceAll('_', ' ') }}
              </div>
            </div>
          </div>
          <div class="text-xs text-medium text-opacity-70 font-bold mt-1">
            MAIN
          </div>
        </div>

        <div class="px-4 py-2 my-auto rounded-full text-light">
          <div class="text-sm font-bold">COMP</div>
          <div class="text-4xl text-light font-medium font-mono">{{ game.composition.name.replaceAll('_', ' ') }}</div>
        </div>

        <div v-if="game.composition.offCarry" class="my-auto">
          <div class="relative flex flex-col justify-center h-24 w-24 rounded-full bg-secondary">
            <img :src="getChampImage(`${game.composition.offCarry.replaceAll('_', '').replace('Ultimate', '')}_0.jpg`)"
            class="h-23 w-23 rounded-full mx-auto border-2 border-black"/>
            <div class="absolute -bottom-1 w-full text-sm font-bold text-center my-auto">
              <div class="w-min max-w-[5rem] truncate px-2 rounded-full bg-secondary text-dark mx-auto">
                {{ game.composition.offCarry.replaceAll('_', ' ') }}
              </div>
            </div>
          </div>
          <div class="text-xs text-medium text-opacity-70 font-bold mt-1">
            SECONDARY
          </div>
        </div>
      </div>

    </div>

  </div>
</template>
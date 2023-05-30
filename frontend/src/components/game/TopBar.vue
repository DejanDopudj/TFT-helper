<script setup>
import { ref, defineProps, defineEmits } from 'vue'

const props = defineProps({
  game: {
    type: Object,
    required: true,
  }
})

const emit = defineEmits(['openFinishGame'])

const game = ref(props.game)

const handleOpenFinishGame = () => {
  emit('openFinishGame')
}

</script>

<template>
  <div class="relative flex justify-center gap-x-6 w-full mb-4 pointer-events-none select-none">
    <div class="flex justify-center w-140 pointer-events-auto">
      <div class="flex flex-col justify-center rounded-bl-xl bg-dork h-8 w-44
      border-medium text-light text-xl text-center px-10 font-medium">
        Turn {{ game.turn }}
      </div>

      <button class="flex flex-col justify-center text-2xl bg-primary py-2 px-4 rounded-b-xl
      font-medium tracking-wider hover:bg-opacity-90" @click="handleOpenFinishGame">
        FINISH GAME
      </button>

      <div class="flex flex-col justify-center rounded-br-xl bg-dork h-8 w-44
      border-medium text-light text-xl text-center px-10 font-medium">
        Round {{ game.round }}
      </div>
    </div>

    <div class="flex justify-center absolute w-full -bottom-8 pointer-events-none">
      <div class="font-medium"
      :class="[game.currentPosition === 'NEUTRAL' ? 'text-light' : (game.currentPosition === 'POSITIVE' ? 'text-primary' : 'text-secondary')]">
        POSITION {{ game.currentPosition }}
      </div>
    </div>

    <div v-if="game.currentPositionTrend !== 'NONE'" class="flex justify-center absolute w-full -bottom-12 pointer-events-none">
      <div class="font-medium"
      :class="[(game.currentPositionTrend === 'GOOD' ? 'text-primary' : 'text-secondary')]">
        {{ game.currentPositionTrend === 'GOOD' ? 'GREAT TREND. KEEP IT UP!' : 'BAD TREND. CHANGE STRATEGY!' }}
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineEmits, defineProps } from 'vue'

const emit = defineEmits(['editPlayer'])

const props = defineProps({
  players: {
      type: Array,
      required: true,
    },
})

const players = ref(props.players)

const openPlayerEdit = (player) => {
  emit('editPlayer', player)
}

</script>

<template>
  <div class="flex flex-col justify-center select-none">
    <div class="flex flex-col justify-between w-20 h-120">
      <div v-for="player in players">
        
        <div class="relative cursor-pointer" @click="openPlayerEdit(player)">
          <div class="relative rounded-full h-14 w-14 bg-dork mx-auto overflow-hidden">

            <div class="absolute right-0 w-full bottom-0 bg-secondary" :style="'height: ' + player.hp + '%'">
            </div>
            <div class="flex flex-col justify-center absolute top-0.5 right-0.5 rounded-full h-13 w-13 bg-dark">
              <div class="text-3xl text-center text-light font-bold -mt-1">
                {{ player.level }}
              </div>
            </div>

          </div>

          <div class="flex justify-center absolute -bottom-2 w-full text-center mx-auto
          rounded-full text-light text-xs">
            <div class="flex flex-col justify-center text-center rounded-full w-5 h-5
            bg-secondary text-dark font-extrabold">
              {{ player.hp }}
            </div>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

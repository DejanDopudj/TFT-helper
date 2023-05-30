<script setup>
import { ref, defineEmits } from 'vue'

const emit = defineEmits(['showAugmentConnections', 'showChampConnections'])

const showButtons = ref(false)

const handleShowAugmentConnections = () => {
  emit('showAugmentConnections')
}

const handleShowChampConnections = () => {
  emit('showChampConnections')
}


</script>

<template>
  <div class="flex flex-col justify-end select-none">
    <div class="flex flex-col justify-between m-4 pointer-events-auto">
      <div class="relative">
        <button class="h-10 w-10 bg-medium hover:bg-opacity-90 rounded-full" @click="showButtons = !showButtons">
          <font-awesome-icon icon="fa-solid fa-bars" class="my-auto"/>
        </button>

        <div v-if="showButtons" class="absolute -top-24 right-0 flex flex-col
        justify-bottom text-sm gap-y-2 font-medium">
          <button class="px-4 bg-medium hover:bg-opacity-90 rounded-lg"
          @click="handleShowAugmentConnections">
            Augment connections
          </button>
          <button class="px-4 bg-medium hover:bg-opacity-90 rounded-lg"
          @click="handleShowChampConnections">
            Champ connections
          </button>
        </div>
      </div>
    </div>
  </div>
</template>

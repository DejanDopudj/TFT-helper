<script setup>
import { ref } from 'vue'

const emit = defineEmits(['addAugments'])

const augments = ref([
])

const openAugmentsAdder = () => {
  emit('addAugments', augments.value)
}

</script>

<template>
  <div class="flex flex-col justify-center select-none">
    <div class="flex flex-col justify-between w-48 h-140 px-2">
      
      <!-- augments -->
      <div class="">
        <div class="text-lg text-light font-medium text-center mb-1">Augments</div>

        <div v-if="augments.length > 0" class="flex flex-col gap-y-1">
          <div v-for="augment in augments">
            <div class="text-dark text-sm tracking-wide font-bold rounded-2xl bg-primary px-2 text-center">
              {{ augment.name.replaceAll('_', ' ') }}
            </div>
          </div>
        </div>

        <div v-else class="w-full h-24 border border-dashed rounded-xl cursor-pointer hover:opacity-90 animate-pulse"
        @click="openAugmentsAdder">
          <div class="flex flex-col justify-center w-full h-full">
            <font-awesome-icon icon="fa-solid fa-plus" class="my-auto text-light text-4xl text-center mx-auto"/>
          </div>
        </div>

      </div>

    </div>
  </div>
</template>

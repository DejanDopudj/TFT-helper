<script setup>
import { ref, defineEmits, defineProps } from 'vue'

const emit = defineEmits(['addAugments'])

const props = defineProps({
  augments: {
      type: Array,
      required: true,
      validator: (value) => {
        return value.every((item) => {
          return item && typeof item.name === 'string';
        });
      },
    },
    components: {
      type: Array,
      required: true,
      validator: (value) => {
        return value.every((item) => {
          return item && typeof item.name === 'string';
        });
      },
    },
})

const augments = ref(props.augments)
const components = ref(props.components)

const openAugmentsAdder = () => {
  emit('addAugments', augments.value)
}

</script>

<template>
  <div class="flex flex-col justify-center select-none pointer-events-none">
    <div class="flex flex-col w-48 h-140 px-2 pointer-events-auto">
      
      <!-- augments -->
      <div class="h-22">
        <div class="text-lg text-light font-medium text-center mb-1">Augments</div>

        <div v-if="augments.length > 0" class="flex flex-col gap-y-1">
          <div v-for="augment in augments">
            <div class="text-dark text-sm tracking-wide font-bold rounded-2xl bg-primary px-2 text-center">
              {{ augment.name.replaceAll('_', ' ') }}
            </div>
          </div>
        </div>

        <div v-else class="w-full h-12 border border-dashed rounded-xl cursor-pointer hover:opacity-90 animate-pulse"
        @click="openAugmentsAdder">
          <div class="flex flex-col justify-center w-full h-full">
            <font-awesome-icon icon="fa-solid fa-plus" class="my-auto text-light text-3xl text-center mx-auto"/>
          </div>
        </div>

      </div>

      <!-- components -->
      <div>
        <div class="text-lg text-light font-medium text-center mb-1">Components</div>

        <div v-if="components.length > 0" class="flex flex-col gap-y-1">
          <div v-for="component in components">
            <div class="text-dark text-sm tracking-wide font-bold rounded-2xl bg-primary px-2 text-center">
              {{ component.name.replaceAll('_', ' ') }}
            </div>
          </div>
        </div>

        <!-- <div v-else class="w-full h-12 border border-dashed rounded-xl cursor-pointer hover:opacity-90 animate-pulse"
        @click="openAugmentsAdder">
          <div class="flex flex-col justify-center w-full h-full">
            <font-awesome-icon icon="fa-solid fa-plus" class="my-auto text-light text-3xl text-center mx-auto"/>
          </div>
        </div> -->

      </div>

    </div>
  </div>
</template>

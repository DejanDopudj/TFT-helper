<script setup>
import { ref, defineEmits, defineProps } from 'vue'

const emit = defineEmits(['addAugments', 'addComponent', 'selectAugment'])

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
    missingComponents: {
      type: Array,
      required: true,
    },
})

const augments = ref(props.augments)
const components = ref(props.components)
const missingComponents = ref(props.missingComponents)

const openAugmentsAdder = () => {
  if (components.value.length > 0) emit('addAugments', augments.value)
}

const openSelectAugment = () => {
  if (components.value.length > 0) emit('selectAugment')
}

const openComponentAdder = () => {
  emit('addComponent')
}

const hasAcquiredAllMissingComponents = () => {
  return missingComponents.value.length > 0 && 
  missingComponents.value.filter(mc => components.value.some(c => mc.name === c.name)).length === missingComponents.value.length
}

</script>

<template>
  <div class="flex flex-col justify-center select-none pointer-events-none">
    <div class="flex flex-col w-48 h-140 px-2 pointer-events-auto">
      
      <!-- augments -->
      <div>
        <div>
          <div class="flex justify-center gap-x-1 text-lg text-light font-medium text-center mb-1">
            <div>Augments</div>
            <button class="w-8 h-8 text-medium hover:text-light text-sm" @click="openSelectAugment">
              <font-awesome-icon icon="fa-solid fa-user-plus"
              class="my-auto text-center mx-auto"/>
            </button>
          </div>
        </div>

        <div v-if="augments.length > 0" class="flex flex-col gap-y-1">
          <div v-for="augment in augments">
            <div class="text-dark text-sm tracking-wide font-bold rounded-2xl bg-primary px-2 py-1 text-center">
              {{ augment.name.replaceAll('_', ' ') }}
            </div>
          </div>
        </div>

        <div v-if="augments.length < 3" class="w-full h-10 border border-dashed rounded-xl mt-2"
        :class="[components.length > 0 ? 'animate-pulse cursor-pointer hover:opacity-90 border-light' : 'cursor-default border-medium']"
        @click="openAugmentsAdder">
          <div class="flex flex-col justify-center w-full h-full">
            <font-awesome-icon v-if="components.length > 0" icon="fa-solid fa-plus"
              class="my-auto text-light text-3xl text-center mx-auto"/>
            <div v-else class="text-medium text-center">Add a component first</div>
          </div>
        </div>

      </div>

      <!-- components -->
      <div class="mt-2">
        <div class="flex justify-center gap-x-2 mb-1">
          <div v-if="hasAcquiredAllMissingComponents()" class="flex flex-col justify-center">
            <font-awesome-icon icon="fa-solid fa-star-of-life" class="text-light"/>
          </div>
          <div class="text-lg text-light font-medium text-center">Components</div>
        </div>

        <div class="flex flex-col gap-y-1">
          <div v-for="component in components">
            <div class="flex justify-center gap-x-2 text-dark text-sm tracking-wide font-bold rounded-2xl bg-primary px-2 py-1 text-center">
              <div v-if="missingComponents.map(x => x.name).includes(component.name)">
                <font-awesome-icon icon="fa-solid fa-star-of-life" class="my-auto text-dark"/>
              </div>
              <div>{{ component.name.replaceAll('_', ' ') }}</div>
            </div>
          </div>
        </div>

        <div class="w-full h-10 border border-dashed rounded-xl cursor-pointer hover:opacity-90 animate-pulse mt-2"
        @click="openComponentAdder">
          <div class="flex flex-col justify-center w-full h-full">
            <font-awesome-icon icon="fa-solid fa-plus" class="my-auto text-light text-3xl text-center mx-auto"/>
          </div>
        </div>

      </div>

    </div>
  </div>
</template>

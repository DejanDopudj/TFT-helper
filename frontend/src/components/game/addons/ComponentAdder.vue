<script setup>
import { ref, defineEmits, defineProps, computed } from 'vue'
import { useRoute } from 'vue-router'
import { submitComponent } from '../../../services/gameService'

const route = useRoute()

const props = defineProps({
  missingComponents: {
    type: Array,
    required: true,
  },
})

const missingComponents = ref(props.missingComponents)

const componentList = [
    { name: 'Recurve bow' },
    { name: 'Chain vest' },
    { name: 'Tear of the Goddess' },
    { name: 'Giants belt' },
    { name: 'B F Sword' },
    { name: 'Negatrons cloak' },
];

const emit = defineEmits(['close'])
const openComponentSearch = ref(false)
const searchQuery = ref('')
const chosenComponent = ref(null)

const closeSelf = () => {
  emit('close')
}

const bestMatches = computed(() => {
  return componentList.filter(component => {
    const queryLowercase = searchQuery.value.toLowerCase();
    return component.name !== chosenComponent.value?.name &&
    component.name.toLowerCase().includes(queryLowercase);
  }).slice(0, 6);
})

const handleAddComponent = (c=null) => {
  if (c) selectComponent(c)
  else {
    const matches = bestMatches.value;
    if (matches.length > 0) selectComponent(matches[0].name)
  }
  openComponentSearch.value = false
  searchQuery.value = ''
}

const selectComponent = (componentName) => {
  chosenComponent.value = { name: componentName.replaceAll(' ', '_') }
}

const removeComponent = () => {
  chosenComponent.value = null
}

const handleShowSearch = () => {
  openComponentSearch.value = true
  setTimeout(() => {
    document.getElementById('component-search').focus()
  }, 50)
}

const handleSubmitComponent = () => {
  submitComponent(route.params.id, chosenComponent.value.name,
    () => {
      closeSelf()
      window.location.href = `/game/${route.params.id}`
    },
    () => {
      // boo hoo
    });
}

</script>

<template>
  <div class="flex flex-col w-full justify-center mx-auto bg-dark bg-opacity-30" @click.self="closeSelf">
    <div class="flex justify-center mx-auto">
      <div class="flex flex-col justify-between bg-dork w-80 h-88 rounded-md py-5 px-6">

        <div>
          <div class="flex justify-between mb-2">
            <div class="text-light text-2xl font-light">Add a component</div>
            <button class="text-light text-xl w-6 h-6 my-auto hover:text-medium" @click="closeSelf">
              <font-awesome-icon icon="fa-solid fa-xmark" class="my-auto mx-auto"/>
            </button>
          </div>

          <div v-if="chosenComponent" class="flex flex-col mt-2">
            <div class="flex justify-between border border-medium text-white rounded-md px-2 py-1 cursor-pointer">
              <div>
                {{ chosenComponent.name.replaceAll('_', ' ') }}
              </div>
              <font-awesome-icon icon="fa-solid fa-xmark" class="my-auto hover:text-secondary"
              @click="removeComponent()"/>
            </div>
          </div>

          <div v-if="!chosenComponent">
            <div v-if="!openComponentSearch" class="flex flex-col mt-2">
              <div class="flex justify-between border border-medium text-white rounded-md px-2 py-1 cursor-pointer"
              @click="handleShowSearch()">
                <span>Add component</span>
                <font-awesome-icon icon="fa-solid fa-plus" class="my-auto"/>
              </div>
            </div>

            <div v-if="openComponentSearch" class="mt-2">
              <div>
                <input class="appearance-none block w-full text-light tracking-wide
                placeholder-medium caret-medium border-b bg-transparent leading-tight outline-none
                py-1 focus:transition-color duration-300"
                id="component-search" type="text" placeholder="Search" autofocus="autofocus"
                v-model="searchQuery" @keydown.enter="handleAddComponent()">
              </div>
              <div>
                <div v-for="result in bestMatches">
                  <div class="flex justify-between bg-medium px-2 py-0.5 hover:bg-opacity-80 cursor-pointer" @click="handleAddComponent(result.name)">
                    <div>{{ result.name }}</div>
                    <div v-if="missingComponents.map(x => x.name.replace('_', ' ')).includes(result.name)" class="flex flex-col justify-center">
                      <font-awesome-icon icon="fa-solid fa-star-of-life" class="text-dark text-xs"/>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>

        <button class="px-3 py-1 bg-primary text-dark enabled:hover:opacity-90
        font-medium mx-auto rounded-md disabled:bg-medium"
        :disabled="!chosenComponent" @click="handleSubmitComponent">
          Submit component
        </button>

      </div>
    </div>
  </div>

</template>

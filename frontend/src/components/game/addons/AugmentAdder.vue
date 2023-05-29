<script setup>
import { ref, defineProps, defineEmits, computed } from 'vue'
import { useRoute } from 'vue-router'
import { submitAugments } from '../../../services/gameService'

const route = useRoute()

const augmentList = [
    { name: 'Gadgeteen heart' },
    { name: 'Gadgeteen crest' },
    { name: 'Gadgeteen soul' },
    { name: 'Temper Tantrum' },
    { name: 'Celestial Blessing' },
    { name: 'Ascension' },
    { name: 'Threat Level Maximum' },
    { name: 'Infiniteam heart' },
    { name: 'Infiniteam crest' },
    { name: 'Admin heart' },
    { name: 'Ox force heart' },
    { name: 'Ox force crest' },
    { name: 'Duelist heart' },
    { name: 'Duelist crest' },
    { name: 'Exiles' },
    { name: 'Trade sector' },
    { name: 'Golden Ticket' },
    { name: 'Sureshot heart' },
    { name: 'Thrill I' },
    { name: 'Thrill II' }
];

// dont forget to replaceAll ' ' with '_'

const emit = defineEmits(['close'])
const openAugmentSearch = ref(false)
const searchQuery = ref('')

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
})

const augments = ref([])

const closeSelf = () => {
  emit('close')
}

const bestMatches = computed(() => {
  return augmentList.filter(augment => {
    const queryLowercase = searchQuery.value.toLowerCase();
    return augments.value.filter(chosenAugment => chosenAugment.name === augment.name.replaceAll(' ', '_')).length === 0 &&
    augment.name.toLowerCase().includes(queryLowercase) &&
    !props.augments.map(x => x.name.replaceAll('_', ' ')).includes(augment.name)
  }).slice(0, 5);
})

const handleAddAugment = (a=null) => {
  if (a) addAugment(a)
  else {
    const matches = bestMatches.value;
    if (matches.length > 0) addAugment(matches[0].name)
  }
  openAugmentSearch.value = false
  searchQuery.value = ''
}

const addAugment = (augmentName) => {
  if (augments.value.filter(a => a.name === augmentName.replaceAll(' ', '_')).length === 0) {
    augments.value.push({name: augmentName.replaceAll(' ', '_')})
  }
}

const removeAugment = (augmentName) => {
  augments.value = augments.value.filter(a => a.name !== augmentName.replaceAll(' ', '_'))
}

const handleShowSearch = () => {
  openAugmentSearch.value = true
  setTimeout(() => {
    document.getElementById('augment-search').focus()
  }, 50)
}

const handleSubmitAugments = () => {
  submitAugments(route.params.id, augments.value.map(x => x.name),
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
      <div class="flex flex-col justify-between bg-dork w-80 h-96 rounded-md py-5 px-6">

        <div>
          <div class="flex justify-between mb-2">
            <div class="text-light text-2xl font-light">Add augments</div>
            <button class="text-light text-xl w-6 h-6 my-auto hover:text-medium" @click="closeSelf">
              <font-awesome-icon icon="fa-solid fa-xmark" class="my-auto mx-auto"/>
            </button>
          </div>

          <div v-for="augment in augments">
            <div class="flex flex-col mt-2">
              <div class="flex justify-between border border-medium text-white rounded-md px-2 py-1 cursor-pointer">
                <div>
                  {{ augment.name.replaceAll('_', ' ') }}
                </div>
                <font-awesome-icon icon="fa-solid fa-xmark" class="my-auto hover:text-secondary"
                @click="removeAugment(augment.name)"/>
              </div>
            </div>
          </div>

          <div v-if="augments.length < 3">
            <div v-if="!openAugmentSearch" class="flex flex-col mt-2">
              <div class="flex justify-between border border-medium text-white rounded-md px-2 py-1 cursor-pointer"
              @click="handleShowSearch()">
                <span>Add augment</span>
                <font-awesome-icon icon="fa-solid fa-plus" class="my-auto"/>
              </div>
            </div>

            <div v-if="openAugmentSearch" class="mt-2">
              <div>
                <input class="appearance-none block w-full text-light tracking-wide
                placeholder-medium caret-medium border-b bg-transparent leading-tight outline-none
                py-1 focus:transition-color duration-300"
                id="augment-search" type="text" placeholder="Search" autofocus="autofocus"
                v-model="searchQuery" @keydown.enter="handleAddAugment()">
              </div>
              <div>
                <div v-for="result in bestMatches">
                  <div class="bg-medium px-2 py-0.5 hover:bg-opacity-80 cursor-pointer" @click="handleAddAugment(result.name)">
                    {{ result.name }}
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>

        <button class="px-3 py-1 bg-primary text-dark enabled:hover:opacity-90
        font-medium mx-auto rounded-md disabled:bg-medium"
        :disabled="augments.length < 3" @click="handleSubmitAugments">
          Submit augments
        </button>

      </div>
    </div>
  </div>

</template>

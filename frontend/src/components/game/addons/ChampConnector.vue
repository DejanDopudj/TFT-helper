<script setup>
import { ref, defineEmits, computed } from 'vue'
import { useRoute } from 'vue-router'
import { getChampConnection } from '../../../services/gameService'

const route = useRoute()

const champList = [
    { name: 'Lulu' },
    { name: 'Poppy' },
    { name: 'Annie' },
    { name: 'Alistar' },
    { name: 'Gnar' },
    { name: 'Morgana' },
    { name: 'Sona' },
    { name: 'Nunu' },
    { name: 'Jinx' },
    { name: 'Rell' },
    { name: 'Lucian' },
    { name: 'Sivir' },
    { name: 'Ezreal' },
    { name: 'Shen' },
    { name: 'Garen' },
    { name: 'Twisted Fate' },
    { name: 'Fiddlesticks' },
    { name: 'Ultimate Ezreal' },
    { name: 'Ashe' },
    { name: 'Blitzcrank' },
    { name: 'Renekton' },
    { name: 'Yasuo' },
    { name: 'Riven' },
    { name: 'Warwick' },
    { name: 'Mordekaiser' },
    { name: 'Vi' },
    { name: 'Vayne' },
    { name: 'Fiora' },
    { name: 'Ekko' },
    { name: 'Neeko' },
    { name: 'Viego' },
    { name: 'Lux' },
    { name: 'Leona' },
    { name: 'Kaisa' },
    { name: 'Nilah' },
    { name: 'Miss fortune' },
    { name: 'Kayle' },
    { name: 'Lee Sin' }
];

const emit = defineEmits(['close'])
const openChampSearch = ref(false)
const searchQuery = ref('')

const champs = ref([])
const responseMessage = ref('')

const closeSelf = () => {
  emit('close')
}

const bestMatches = computed(() => {
  return champList.filter(champ => {
    const queryLowercase = searchQuery.value.toLowerCase();
    return champs.value.filter(chosenChamp => chosenChamp.name === champ.name.replaceAll(' ', '_')).length === 0 &&
    champ.name.toLowerCase().includes(queryLowercase)
  }).slice(0, 5);
})

const handleAddChamp = (a=null) => {
  if (a) addChamp(a)
  else {
    const matches = bestMatches.value;
    if (matches.length > 0) addChamp(matches[0].name)
  }
  openChampSearch.value = false
  searchQuery.value = ''
}

const addChamp = (champName) => {
  if (champs.value.filter(a => a.name === champName.replaceAll(' ', '_')).length === 0) {
    champs.value.push({name: champName.replaceAll(' ', '_')})
  }
}

const removeChamp = (champName) => {
  champs.value = champs.value.filter(a => a.name !== champName.replaceAll(' ', '_'))
  responseMessage.value = ''
}

const handleShowSearch = () => {
  openChampSearch.value = true
  setTimeout(() => {
    document.getElementById('champ-search').focus()
  }, 50)
}

const handleSubmitChamps = () => {
  getChampConnection(champs.value[0].name, champs.value[1].name,
    (response) => {
      console.log(response)
      responseMessage.value = response
    },
    () => {
      responseMessage.value = 'They are not connected.'
    });
}

</script>

<template>
  <div class="flex flex-col w-full justify-center mx-auto bg-dark bg-opacity-30" @click.self="closeSelf">
    <div class="flex justify-center mx-auto">
      <div class="flex flex-col justify-between bg-dork w-80 h-88 rounded-md py-5 px-6">

        <div>
          <div class="flex justify-between mb-2">
            <div class="text-light text-2xl font-light">Champ connections</div>
            <button class="text-light text-xl w-6 h-6 my-auto hover:text-medium" @click="closeSelf">
              <font-awesome-icon icon="fa-solid fa-xmark" class="my-auto mx-auto"/>
            </button>
          </div>

          <div v-for="champ in champs">
            <div class="flex flex-col mt-2">
              <div class="flex justify-between border border-medium text-white rounded-md px-2 py-1 cursor-pointer">
                <div>
                  {{ champ.name.replaceAll('_', ' ') }}
                </div>
                <font-awesome-icon icon="fa-solid fa-xmark" class="my-auto hover:text-secondary"
                @click="removeChamp(champ.name)"/>
              </div>
            </div>
          </div>

          <div v-if="champs.length < 2">
            <div v-if="!openChampSearch" class="flex flex-col mt-2">
              <div class="flex justify-between border border-medium text-white rounded-md px-2 py-1 cursor-pointer"
              @click="handleShowSearch()">
                <span>Add champ</span>
                <font-awesome-icon icon="fa-solid fa-plus" class="my-auto"/>
              </div>
            </div>

            <div v-if="openChampSearch" class="mt-2">
              <div>
                <input class="appearance-none block w-full text-light tracking-wide
                placeholder-medium caret-medium border-b bg-transparent leading-tight outline-none
                py-1 focus:transition-color duration-300"
                id="champ-search" type="text" placeholder="Search" autofocus="autofocus"
                v-model="searchQuery" @keydown.enter="handleAddChamp()">
              </div>
              <div>
                <div v-for="result in bestMatches">
                  <div class="bg-medium px-2 py-0.5 hover:bg-opacity-80 cursor-pointer" @click="handleAddChamp(result.name)">
                    {{ result.name }}
                  </div>
                </div>
              </div>
            </div>

          </div>
        </div>

        <div class="mx-auto">
          <button v-if="!responseMessage" class="px-3 py-1 bg-primary text-dark enabled:hover:opacity-90
          font-medium rounded-md disabled:bg-medium"
          :disabled="champs.length < 2" @click="handleSubmitChamps">
            Get connection
          </button>
          <div v-else class="text-light text-center font-medium">
            <div class="text-base">{{ responseMessage.includes('They are connected') ? 'They are connected.' : 'They are not connected.' }}</div>
            <div v-if="responseMessage.includes('They are connected')" class="text-sm">{{ responseMessage.replace('They are connected,', '').split(',')[0] }}</div>
            <div v-if="responseMessage.includes('They are connected')" class="text-sm">{{ responseMessage.replace('They are connected,', '').split(',')[1] }}</div>
          </div>
        </div>

      </div>
    </div>
  </div>

</template>

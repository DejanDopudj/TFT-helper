<script setup>
import { ref, defineEmits } from 'vue'
import { useRoute } from 'vue-router'
import { setPlace } from '../../../services/gameService'

const route = useRoute()

const emit = defineEmits(['close'])

const place = ref(1)

const closeSelf = () => {
  emit('close')
}

const handeDecrementPlace = () => {
  if (place.value > 1) place.value--;
}

const handeIncrementPlace = () => {
  if (place.value < 8) place.value++;
}

const handleSetPlace = () => {
  setPlace(route.params.id, place.value,
    () => {
      closeSelf()
      window.location.href = `/`
    },
    () => {
      // boo hoo
    });
}

</script>

<template>
  
  <div class="flex flex-col w-full justify-center mx-auto bg-dark bg-opacity-30" @click.self="closeSelf">
    <div class="flex justify-center mx-auto">
      <div class="flex flex-col justify-between bg-dork w-80 h-40 rounded-md py-5 px-6">
        <div>
          <div class="flex justify-between mb-2">
            <div class="text-light text-2xl font-light">Finish game</div>
            <button class="text-light text-xl w-6 h-6 my-auto hover:text-medium" @click="closeSelf">
              <font-awesome-icon icon="fa-solid fa-xmark" class="my-auto mx-auto"/>
            </button>
          </div>

          <div class="flex justify-center gap-x-4">
            <button class="my-auto h-6 w-6 font-black bg-medium rounded-full" @click="handeDecrementPlace">
              <font-awesome-icon icon="fa-solid fa-minus" class="my-auto mx-auto"/>
            </button>
            <div class="text-4xl text-light font-bold">{{ place }}</div>
            <button class="my-auto h-6 w-6 font-black bg-primary rounded-full" @click="handeIncrementPlace">
              <font-awesome-icon icon="fa-solid fa-plus" class="my-auto mx-auto"/>
            </button>
          </div>
        </div>

        <button class="px-3 py-1 bg-primary text-dark enabled:hover:opacity-90
        font-medium mx-auto rounded-md disabled:bg-medium"
        @click="handleSetPlace">
          Finish game
        </button>

      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, defineProps, defineEmits, toRefs } from 'vue'
import { incrementPlayerLevel, decrementPlayerLevel, changePlayerHp } from '../../../services/gameService'

const emit = defineEmits(['close'])

const props = defineProps({
  player: {
    id: String,
    hp: Number,
    level: Number
  }
})

const { player } = toRefs(props)
const newHp = ref(props.player.hp)

const closeSelf = () => {
  emit('close')
}

const handleDecrementPlayerLevel = () => {
  decrementPlayerLevel(props.player,
    () => {
      props.player.level--
    },
    () => {
      // boo hoo
    });
}

const handleIncrementPlayerLevel = () => {
  incrementPlayerLevel(props.player,
    () => {
      props.player.level++
    },
    () => {
      // boo hoo
    });
}

const handleChangePlayerHp = () => {
  if (!(newHp.value < 0 || newHp.value > 100)) {
    newHp.value = parseInt(newHp.value)
    changePlayerHp(props.player, newHp.value,
    () => {
      props.player.hp = newHp.value
    },
    () => {
      // boo hoo
    });
  }

}

</script>

<template>
  <div class="flex flex-col w-full justify-center mx-auto" @click.self="closeSelf">
    <div class="flex justify-center mx-auto">
      <div class="flex flex-col bg-dork w-80 rounded-md py-4 px-6">
        <div class="flex justify-between">
          <div class="text-light text-2xl font-light">Edit player</div>
          <button class="text-light text-xl w-6 h-6 my-auto hover:text-medium" @click="closeSelf">
            <font-awesome-icon icon="fa-solid fa-xmark" class="my-auto mx-auto"/>
          </button>
        </div>
        
        <!-- level -->
        <div class="flex justify-between gap-x-4 mt-4">
          
          <div class="text-2xl text-light font-bold">
            Level {{ player.level }}
          </div>

          <div class="flex gap-x-2">
            <button class="my-auto h-6 w-6 font-black bg-medium rounded-full" @click="handleDecrementPlayerLevel">
              <font-awesome-icon icon="fa-solid fa-minus" class="my-auto mx-auto"/>
            </button>
            <button class="my-auto h-6 w-6 font-black bg-primary rounded-full" @click="handleIncrementPlayerLevel">
              <font-awesome-icon icon="fa-solid fa-plus" class="my-auto mx-auto"/>
            </button>
          </div>

        </div>

        <!-- hp -->
        <div class="flex justify-between gap-x-4 mt-2">
          
          <div class="text-2xl text-light font-bold">
            {{ player.hp }} HP
          </div>

          <div class="flex gap-x-2">
            <input type="number" class="w-14 text-light bg-transparent border rounded-md outline-none
            text-center" :placeholder="player.hp" v-model="newHp" min="0" max="100"
            :class="[newHp < 0 || newHp > 100 ? 'border-red-500' : 'border-medium focus:border-primary']">
            <button class="my-auto h-8 w-8 font-black bg-primary rounded-full" @click="handleChangePlayerHp">
              <font-awesome-icon icon="fa-solid fa-save" class="my-auto mx-auto"/>
            </button>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

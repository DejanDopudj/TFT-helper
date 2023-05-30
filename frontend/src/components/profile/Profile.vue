<script setup>
import { ref } from 'vue'
import { isUserLoggedIn, whoami } from '../../services/authService'
import { getHistoryGrade, getHoursPlayed } from '../../services/gameService'

if (!isUserLoggedIn()) window.location.href = '/login'

const user = ref(null)
const historyGrade = ref('')
const hoursPlayed = ref('')

whoami(
  (u) => {
    user.value = u
    fetchHistoryGrade()
    fetchHoursPlayed()
  },
  () => {
    window.location.href = '/'
  });

const fetchHistoryGrade = () => {
  getHistoryGrade(
    user.value.username,
    (response) => {
      historyGrade.value = response
    },
    () => {
      // oops
    });
}

const fetchHoursPlayed = () => {
  getHoursPlayed(
    user.value.username,
    (response) => {
      hoursPlayed.value = response
    },
    () => {
      // oops
    });
}

</script>

<template>
  <div v-if="user" class="mx-auto w-72 text-center select-none">
    <div>
      <img src="@/assets/images/champs/Zed_0.jpg" class="rounded-full w-24 h-24 mx-auto"/>
    </div>
    <div class="text-light text-4xl font-bold font-mono mt-2">{{ user.username }}</div>
    <div class="text-medium text-sm font-bold font-mono mt-0.5">{{ user.email }}</div>
    <div v-if="historyGrade" class="text-black font-bold mt-4 rounded-full bg-primary">
      {{ historyGrade }}
    </div>
    <div v-if="hoursPlayed" class="text-black font-bold mt-2 rounded-full bg-secondary">
      {{ hoursPlayed }}
    </div>
  </div>
</template>

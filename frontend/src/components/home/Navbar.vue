<script setup>
import { isUserLoggedIn, logout } from '../../services/authService'
import { ref, watch } from 'vue'
import { useRoute } from 'vue-router'

const route = useRoute()
const isLoginPage = ref(route.name === 'login')

watch(() => route.name, () => {
  isLoginPage.value = route.name === 'login'
})
    
const isLoggedIn = ref(isUserLoggedIn())

watch(() => isUserLoggedIn(), () => {
  isLoggedIn = isUserLoggedIn()
})

const handleLogout = () => {
  logout()
  window.location.href = '/';
}

</script>

<template>
  <div class="flex justify-between px-4 md:px-12 py-2 md:py-4">
    <div>
      <RouterLink to="/">
        <img src="@/assets/images/logo-sm.png" alt="" class="h-12">
      </RouterLink>
    </div>
    <div v-if="!isLoginPage && !isLoggedIn">
      <RouterLink to="/login">
        <button class="w-32 text-light font-medium
        hover:opacity-80 py-2">Log In</button>
      </RouterLink>
    </div>
    <div v-if="isLoggedIn" class="flex justify-center gap-x-2">
      <RouterLink to="/profile" class="py-2 w-24 text-light font-medium
        hover:opacity-80 text-center my-auto">
        Stats
      </RouterLink>
      <button class="w-24 text-light font-medium
      hover:opacity-80 py-2" @click="handleLogout">Log Out</button>
    </div>
  </div>
</template>

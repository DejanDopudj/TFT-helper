<script setup>
import { RouterView } from 'vue-router'
import Navbar from './components/home/Navbar.vue';
</script>

<template>
  <div class="overflow-auto min-h-screen bg-dark">
    <Navbar class="absolute top-0 left-0 w-full"/>
    <RouterView class="h-full"/>
  </div>
</template>


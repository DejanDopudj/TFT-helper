<template>
<div>
  <div class="flex flex-col pt-10 pb-28 gap-y-28">

    <!-- compositions -->
    <div class="flex flex-col text-center lg:text-left lg:flex-row lg:justify-between">
      <div class="w-full lg:w-1/2 lg:pr-20">
        <div class="text-light text-3xl font-medium">
          Team Comps
        </div>
        <div class="text-medium font-medium mt-4">
          Get a personalized team comp specifically tailored to the game you are playing at the moment.
        </div>
      </div>

      <div class="relative mx-auto w-full sm:w-120 md:w-160 lg:w-1/2 h-96 shrink-0 mt-12 lg:mt-0">
        <img src="src/assets/images/comps-crop.jpg" alt=""
        class="absolute left-0 top-14 z-10 skew-y-3 border border-dork -skew-x-2
        rounded-sm shadow-lg w-56 sm:w-80 lg:w-72 xl:w-80 shrink-0">
        <img src="src/assets/images/comps-full.jpg" alt=""
        class="absolute right-0 top-0 skew-y-3 border border-dork -skew-x-2
        rounded-sm shadow-lg w-72 sm:w-108 lg:w-100 xl:w-108 shrink-0">
      </div>
    </div>

    <!-- strategy -->
    <div class="flex flex-col text-center lg:text-left lg:flex-row lg:justify-between">
      <div class="mx-auto w-full lg:w-1/2 shrink-0 mt-12 lg:mt-0">
        <img src="src/assets/images/coins-flying.png" alt=""
        class="w-4/5 sm:w-2/3 lg:w-11/12 mx-auto">
      </div>

      <div class="w-full lg:w-1/2 lg:pl-20 -order-10 lg:order-1">
        <div class="text-light text-3xl font-medium">
          Dynamic Strategy
        </div>
        <div class="text-medium font-medium mt-4">
          Get strategy tips in real-time based on how your game is progressing.
        </div>
      </div>
    </div>

    <!-- progression -->
    <div class="flex flex-col text-center lg:text-left lg:flex-row lg:justify-between">
      <div class="w-full lg:w-1/2 lg:pr-20">
        <div class="text-light text-3xl font-medium">
          Promoting Growth
        </div>
        <div class="text-medium font-medium mt-4">
          Our beginner-friendly system is aimed towards teaching you how the game is played at a high level and getting you ready for your next challenge. 
        </div>
      </div>

      <div class=" mx-auto w-full lg:w-1/2 shrink-0 mt-12 lg:mt-0">
        <img src="src/assets/images/ranks.png" alt=""
        class="w-4/5 sm:w-2/3 lg:w-11/12 mx-auto -skew-y-2 -skew-x-1">
      </div>
    </div>
    </div>

    <div class="h-72 w-full border-t border-dork border-opacity-60">

    </div>
</div>

</template>

<script>
export default {
  
}
</script>
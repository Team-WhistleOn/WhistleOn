<template>
  <div id="app">
    <transition name="fade">
      <router-view />
    </transition>
  </div>
</template>

<script lang="ts">
import {Vue, Component} from 'vue-property-decorator';

@Component
export default class App extends Vue {}
</script>

<style lang="scss">
@import 'style/reset.css', 'style/variables';

#app {
  width: $full;
  height: $full;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity 200ms;
}

.fade-enter, .fade-leave-to {
  opacity: 0;
}
</style>

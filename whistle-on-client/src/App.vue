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
@import 'style/reset.css', 'style/variables', 'style/fade-transition';

#app {
  width: $full;
  height: $full;
}
</style>

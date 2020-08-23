<template>
  <main class="main">
    <banner />
    <transition name="fade">
      <router-view />
    </transition>
  </main>
</template>

<script lang="ts">
import {Vue, Component} from 'vue-property-decorator';
import Banner from '@/components/Banner.vue';

@Component({
  components: {
    Banner,
  },
})

export default class Main extends Vue {

}
</script>

<style scoped lang="scss">
@import '../style/fade-transition';
</style>

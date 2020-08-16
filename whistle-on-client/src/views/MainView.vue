<template>
  <main>
    <header-nav />
    <router-view />
    <footer-nav />
  </main>
</template>

<script lang="ts">
import {Vue, Component} from 'vue-property-decorator';
import HeaderNav from '@/components/HeaderNav.vue';
import FooterNav from '@/components/FooterNav.vue';

@Component({
  components: {
    HeaderNav,
    FooterNav,
  },
})

export default class Main extends Vue {

}
</script>

<style scoped lang="scss">

</style>

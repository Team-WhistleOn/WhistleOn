<template>
  <div class="banner">
    <div class="banner__logo" />
    <section class="banner__section">
      <i class="fas fa-times header" v-if="isOnHeader" @click="offNav" />
      <i class="fas fa-ellipsis-v" v-else @click="onHeaderNav" />
      <router-link class="banner__title" to="/">Whistle On</router-link>
      <i class="fas fa-times footer" v-if="isOnFooter" @click="offNav" />
      <i class="fas fa-bars" v-else @click="onFooterNav"/>
    </section>
    <aside class="banner__aside">
      <header-nav :isOnHeader="isOnHeader" />
      <footer-nav :isOnFooter="isOnFooter" />
    </aside>
  </div>
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
export default class Banner extends Vue {
  protected isOnHeader: boolean = false;
  protected isOnFooter: boolean = false;

  private onHeaderNav() {
    this.offNav();
    this.isOnHeader = true;
  }

  private onFooterNav() {
    this.offNav();
    this.isOnFooter = true;
  }

  private offNav() {
    this.isOnHeader = false;
    this.isOnFooter = false;
  }
}
</script>

<style scoped lang="scss">
@import
'../style/color.scss',
'../style/variables.scss',
'../style/mixins.scss',
'../style/mobile/mixins.scss';

.banner {
  @include layout-size(100%, null);
  @include set-pos(fixed, 0, 0, null, null, 1000);
  @include flex-row;
  @include align-fs;
  padding: 10px 20px;

  &__logo {
    width: 80px;
    height: 80px;
    background-image: url('../assets/whistleon-logo.jpeg');
    background-position: center;
    background-size: cover;
    background-repeat: no-repeat;
  }

  &__section {
    @include layout-size(100%, null);
    @include flex-row;
    @include align-sb;
  }

  &__title {
    font-family: 'Permanent Marker', cursive;
    font-size: 30px;
    margin-left: 50px;
  }
}

.fas {
  @include layout-size(50px, 50px);
  @include flex-row;
  @include align-center;
  font-size: 35px;
  color: #000;

  &.header,
  &.fa-ellipsis-v{
    display: none;
  }
}


@media screen and (max-width: 768px) {
  .banner {
    @include layout-size(100%, null);
    @include flex-row;
    @include align-sb;
    @include box-shadow(0, 1px, 7px, 0);
    height: 60px;
    padding: 0 20px;
    margin-bottom: 2px;
    background-color: $blue;

    &__logo {
      display: none;
    }

    &__title {
      @include flex-col;
      @include align-center;
      @include layout-size(300px, 100%);
      color: #fff;
      font-size: 30px;
      margin-left: 0;
    }

    &__section {
      @include layout-size(100%, null);
      @include flex-row;
      @include align-sb;
    }
  }

  .fas {
    color: #fff;
    font-size: 25px;
    &.header,
    &.fa-ellipsis-v{
      display: flex;
    }
  }
}
</style>

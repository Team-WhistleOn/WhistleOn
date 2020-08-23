<template>
  <header class="header">
    <nav class="header__nav" :class="{'header__nav--off': !isOnHeader}">
      <router-link class="header__router-link" to="/notice">공지사항</router-link>
      <router-link class="header__router-link" to="/">용병신청</router-link>
      <router-link class="header__router-link" to="/rating">랭킹</router-link>
      <router-link class="header__router-link" to="/game">경기일정</router-link>
      <router-link class="header__router-link" to="/qna">Q&A</router-link>
      <router-link class="header__router-link" to="/team/list">팀목록</router-link>
    </nav>
  </header>
</template>

<script lang="ts">
import {Vue, Component, Prop} from 'vue-property-decorator';
import Bus from '@/utils/bus.ts';

@Component
export default class Header extends Vue {
  @Prop() isOnHeader!: boolean;
}
</script>

<style scoped lang="scss">
@import
'../style/color.scss',
'../style/variables.scss',
'../style/mixins.scss',
'../style/mobile/mixins.scss',
'../style/animation.scss';

.header {
  @include layout-size(100%, null);
  @include set-pos(absolute, null, null, -70px, 0, 1000);

  &__nav {
    @include layout-size(100%, 70px);
    @include flex-row;
    @include box-shadow(0, 1px, 7px, 0);
    background-color: $blue;
    padding: 10px 20px;
  }

  &__router-link {
    @include flex-row;
    @include align-center;
    font-size: 22px;
    color: #fff;
    margin-right: 10px;
  }
}

@media screen and (max-width: 768px) {
  .header {
    @include toolbar-modal(-70px, null, null, 0, 1000);

    &__nav {
      @include layout-size(80%, 100vh);
      @include flex-col;
      @include align-fs;
      transition: all 500ms ease;
    }

    &__nav {
      &--off {
        transform: translateX(-200%);
        transition: all 500ms ease;
      }
    }

    &__router-link {
      @include flex-row;
      @include align-fs;
      font-size: 16px;
      margin-right: 0;
    }
  }
}
</style>

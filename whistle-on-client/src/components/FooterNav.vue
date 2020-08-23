<template>
  <footer class="footer">
    <nav class="footer__nav" :class="{'footer__nav--off': !isOnFooter}">
      <router-link
        :to="`/users/${userName}`"
        class="footer__router-link"
      >
        <i class="fas fa-user-circle" />
        <p class="footer__user-name">{{userName}}</p>
      </router-link>
      <router-link
        v-if="Boolean(teamName)"
        :to="`/team/${teamName}`"
        class="footer__router-link"
      >
        <div
          class="footer__team-logo"
          v-if="Boolean(teamLogo)"
        />
        <i class="fas fa-user-circle" v-else />
        <p class="footer__team-name">{{teamName}}</p>
      </router-link>
      <div class="footer__independent" v-else>
        <p class="footer__alert">아직 가입한 팀이 없습니다.</p>
        <router-link
          to="/team/new"
          class="footer__team"
        >
          팀생성
        </router-link>
      </div>
      <router-link
        :to="`/users/${userName}`"
        class="footer__router-link"
      >
        My Page
      </router-link>
      <router-link v-if="teamName"
        :to="`/team/${teamName}`"
        class="footer__router-link"
      >
        My Team
      </router-link>
      <p class="footer__logout" @click="LOG_OUT">로그아웃</p>
    </nav>
  </footer>
</template>

<script lang="ts">
import {Vue, Component, Prop} from 'vue-property-decorator';
import {State, Action} from 'vuex-class';
import {IRoot} from '@/types/interface';

@Component
export default class Footer extends Vue {
  @Prop() isOnFooter!: boolean;

  @State
  private readonly userName!: IRoot['userName'];

  @State((state) => state.team.teamName)
  private readonly teamName!: string | false;

  @State((state) => state.team.logo)
  private readonly teamLogo?: string;

  @Action('LOG_OUT')
  private readonly LOG_OUT!: () => Promise<boolean>;
}
</script>

<style scoped lang="scss">
@import '../style/mobile/mixins.scss';

.footer {
  @include toolbar-modal(100px, -1px, null, null, 1000);
  padding: 20px;

  &__nav {
    @include layout-size(30%, 100vh);
    @include flex-col;
    @include align-fs;
    background-color: $dark-gray;
    transition: all 500ms ease;
  }

  &__nav {
    &--off {
      transform: translateX(200%);
      transition: all 500ms ease;
    }
  }

  &__router-link {
    @include flex-row;
    @include layout-size(100%, 50px);
  }

  &__logout,
  &__independent{
    @include flex-row;
    @include layout-size(100%, 50px);
    padding: 10px;
    color: #fff;
    cursor: pointer;
  }

  &__independent {
    @include align-sb;
  }

  &__team {
    color: #fff;
    padding: 2px 5px;
    border: 1px solid #fff;
    border-radius: 5px;
    margin-right: 10px;
  }
}

.fas {
  font-size: 30px;
}

@media screen and (max-width: 768px) {
  .footer {
    @include toolbar-modal(62px, -1, null, null, 1000);

    &__nav {
      @include layout-size(80%, 100vh);
      @include flex-col;
      @include align-fs;
      transition: all 500ms ease;
    }

    &__nav {
      &--off {
        transform: translateX(200%);
        transition: all 500ms ease;
      }
    }
  }
}
</style>

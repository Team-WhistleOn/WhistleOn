<template>
  <div class="login">
    <form class="login__form" @submit.prevent="onSubmitLogin" ref="form">
      <h1 class="login__title">Whistle On Login</h1>
      <p class="login__paragraph">
        <input
          class="login__input"
          type="email"
          autocomplete="on"
          autofocus
          placeholder="이메일을 입력하세요."
          v-model="email"
        />
        <input
          class="login__input"
          type="password"
          placeholder="비밀번호를 입력하세요"
          v-model="password"
        />
      </p>
      <ul class="login__btn-group">
        <li class="login__li">
          <button class="login__btn" type="submit">로그인</button>
        </li>
        <li class="login__li">
          <router-link to="/signup" tag="button" class="login__btn">회원가입</router-link>
        </li>
      </ul>
    </form>
  </div>
</template>

<script lang="ts">
import {Component, Mixins} from 'vue-property-decorator';
import {Action} from 'vuex-class';
import formValidate from '@/mixins/formValidate';

@Component({
  components: {
    formValidate,
  },
})
export default class LoginView extends Mixins(formValidate) {
  private readonly email: string = '';
  private readonly password: string = '';

  @Action('LOG_IN')
  private readonly LOG_IN!: ({email, password}: {email: string, password: string}) => Promise<boolean>;

  private async onSubmitLogin(): Promise<void> {
    try {
      if (this.validateForm(
          Array.prototype.filter.call(this.$refs.form, (v) => v.tagName === 'INPUT'))) {
        const response = await this.LOG_IN({
          email: this.email,
          password: this.password,
        });
        if (response) {
          await this.$router.push('/main');
        } else {
          throw new Error('로그인을 실패하였습니다.');
        }
      }
    } catch (error) {
      console.error(error);
      alert(error.message);
    }
  }
}
</script>

<style scoped lang="scss">
@import
'../style/color.scss',
'../style/variables.scss',
'../style/mixins.scss',
'../style/mobile/mixins.scss';

.login {
  @include layout-size();
  padding: 100px;

  &__form {
    @include layout-size(80%, 300px);
    @include flex-col;
    @include align-sb;
    @include border-radius(15px);
    @include box-shadow(0, 4px, 10px, 3px);
    @include margin-auto;
    padding: 20px;
  }

  &__title {
    font-size: $font-bigger;
  }

  &__paragraph {
    @include flex-col;
    @include align-center;
    @include layout-size();
    margin-bottom: 50px;
  }

  &__input {
    @include layout-size($full, 50px);
    @include align-center;
    border-bottom: 1px solid #000;
  }

  &__input:first-child {
    margin-bottom: 10px;
  }

  &__btn-group {
    @include flex-row;
    @include align-sb;
    @include layout-size($full);
  }

  &__li {
    @include layout-size(45%, 40px);
  }

  &__btn {
    @include layout-size(100%, 40px);
    @include border-radius(10px);
    color: #fff;
    background-color: #000;
  }
}

@media screen and (max-width: 768px) {
  .login {
    padding: 20px;

    &__form {
      @include layout-size($full, 350px);
    }
  }
}
</style>

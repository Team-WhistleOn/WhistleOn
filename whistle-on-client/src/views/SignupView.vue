<template>
  <div class="signup">
    <vue-daum-postcode
        v-if="isOnAddress"
        @complete="pickAddress"
    />
    <form class="signup__form" @submit.prevent="onSubmitSignup" ref="form">
      <h1 class="signup__title">Whistle On Signup</h1>
      <ul class="signup__ul">
        <li class="signup__li">
          <input class="signup__input signup__input--email" type="email" placeholder="이메일" v-model="userEmail" required />
          <button class="signup__btn signup__btn--email" @click.stop="checkEmailRedundancy">중복확인</button>
          <input class="signup__input" type="text" placeholder="이름" v-model="userName" required>
        </li>
        <li class="signup__li">
          <input class="signup__input" type="password" placeholder="비밀번호" v-model="userPassword" required />
          <input class="signup__input" type="password" placeholder="비밀번호 확인" v-model="userPasswordCheck" required />
        </li>
        <li class="signup__li">
          <input class="signup__input" type="text" readonly placeholder="연고지 주소" v-model="mainAddress" @click="toggleAddress" required />
          <input class="signup__input" type="text" placeholder="상세주소" v-model="detailAddress" required />
        </li>
        <li class="signup__li signup__li--personal-info">
          <input class="signup__input" type="number" placeholder="신장(단위: cm)" id="height" v-model="userHeight" required />
          <select class="signup__select" v-model="preferencePosition" >
            <option class="signup__option" value="" selected disabled>::선호 포지션::</option>
            <option class="signup__option" v-for="position in positions" :key="position" :value="position">{{position}}</option>
          </select>
        </li>
        <li class="signup__li">
          <input class="signup__input" type="text" placeholder="생년월일 (예: 20200731)" v-model="birthDay" required />
          <input class="signup__input" type="text" :value="`${age}세`" id="age" readonly disabled />
        </li>
      </ul>
      <button class="signup__btn" type="submit">회원가입</button>
    </form>
  </div>
</template>

<script lang="ts">
import {Component, Mixins} from 'vue-property-decorator';
import {mapActions} from 'vuex';
import FormValidate from '@/mixins/formValidate.ts';
import {VueDaumPostcode} from 'vue-daum-postcode';

@Component({
  components: {
    VueDaumPostcode,
    FormValidate,
  },
  methods: {
    ...mapActions([
      'CHECK_EMAIL',
      'SIGN_UP',
    ]),
  },
})
export default class Signup extends Mixins(FormValidate) {
  private userEmail: string = '';
  private userName: string = '';
  private userPassword: string = '';
  private userPasswordCheck: string = '';
  private mainAddress: string = '';
  private detailAddress: string = '';
  private userHeight: number | '' = '';
  private birthDay: string = '';
  private readonly preferencePosition: string = '';
  private readonly positions: string[] = ['GK', 'ST', 'LW', 'RW', 'CAM', 'CM', 'CDM', 'LB', 'CB', 'RB'];

  private isCheckedEmail: boolean = false;
  private isOnAddress: boolean = false;

  get age(): number {
    return new Date().getFullYear() - Number(this.birthDay.substr(0, 4)) + 1;
  }

  public async onSubmitSignup() {
    try {
      if (this.validateForm(this.$refs.form)) {
        const response: Promise<boolean> = await this.SIGN_UP({
          email: this.userEmail,
          password: this.userPassword,
          location: `${this.mainAddress} ${this.detailAddress}`,
          userName: this.userName,
          position: this.preferencePosition,
          age: this.age,
        });
      }
    } catch (error) {
      console.error(error);
      alert(error.message);
    }
  }

  public toggleAddress() {
    this.isOnAddress = !this.isOnAddress;
  }

  public async checkEmailRedundancy() {
    try {
      if (this.$_.isEmpty(this.userEmail)) { return alert('이메일을 입력해주세요.'); }
      const response: Promise<boolean> = await this.CHECK_EMAIL({email: this.userEmail});
      if (response) {
        this.isCheckedEmail = true;
      } else {
        throw new Error('사용할 수 없는 이메일입니다.');
      }
    } catch (error) {
      console.error(error);
      alert(error.message);
    }
  }

  public pickAddress({roadAddress}: {roadAddress: string}) {
    this.mainAddress = roadAddress;
    this.toggleAddress();
  }
}
</script>

<style scoped lang="scss">
@import
'../style/color.scss',
'../style/variables.scss',
'../style/mixins.scss',
'../style/mobile/mixins.scss';

.signup {
  @include layout-size(60%);
  @include margin-auto;
  padding-top: 20px;

  &__form {
    @include box-shadow(0, 4px, 10px, 3px);
    @include border-radius(10px);
    @include flex-col;
    padding: 20px;
  }

  &__title {
    @include flex-row;
    @include align-center;
    font-size: $font-bigger;
    margin-bottom: 20px;
  }

  &__input {
    @include layout-size($full, 50px);
    @include align-center;
    border-bottom: 1px solid #000;
    margin: 0 10px 10px 0;
  }

  &__btn {
    @include layout-size(100%, 40px);
    @include border-radius(10px);
    color: #fff;
    background-color: #000;
    margin: 20px 0;
  }

  &__ul {
    li:first-child {
      @include flex-row;
      @include align-sb;
      flex-wrap: wrap;
    }
  }

  &__select {
    @include layout-size(50%, 40px);
    @include border-radius(10px);
    border: 1px solid #000;

  }

  &__input.signup__input--email {
    @include layout-size(60%, 50px);
  }

  &__btn.signup__btn--email {
    @include layout-size(35%, 35px);
    margin: 10px 0;
  }

  &__li.signup__li--personal-info {
    @include flex-row;
    @include align-sb;

    .signup__input {
      @include layout-size(50%, 50px);
    }

    .signup__select {
      text-align: center;
      text-align-last: center;
    }
  }
}

@media screen and (max-width: 768px) {
  .signup {
    @include layout-size();
    padding: 20px;
  }
}
</style>

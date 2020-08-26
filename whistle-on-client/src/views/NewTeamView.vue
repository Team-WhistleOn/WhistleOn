<template>
  <div class="new-team">
    <h1 class="new-team__title">팀 생성하기</h1>
    <form class="new-team__form" @submit.prevent="onSubmitNewTeam">
      <ol class="new-team__ol">
        <li class="new-team__li">
<!--          <i class="far fa-futbol" />-->
          <input class="new-team__file-name" value="등록된 엠블럼이 없습니다." disabled />
          <label class="new-team__label" for="file-upload">엠블럼 찾기</label>
          <input type="file" id="file-upload" placeholder="팀 엠블럼을 등록하세요." />
        </li>
        <li class="new-team__li">
          <input type="text" v-model="teamName" placeholder="팀 명을 입력하세요" required/>
        </li>
      </ol>
      <ul class="new-team__ul">
        <li class="new-team__li">
          <p>연고지 추가하기</p>
          <i class="far fa-plus-square" />
        </li>
        <li v-for="(location, index) in teamLocations" :key="index" class="new-team__li">
          <p>{{location}}</p>
          <i class="far fa-minus-square" />
        </li>
      </ul>
      <input type="text" v-model="teamDescription" placeholder="팀 설명을 입력하세요." required/>
      <button type="submit">팀 결성하기</button>
    </form>
  </div>
</template>

<script lang="ts">
import {Vue, Component, Watch} from 'vue-property-decorator';
import {VueDaumPostcode} from 'vue-daum-postcode';

@Component({
  components: {
    VueDaumPostcode,
  }
})
export default class NewTeam extends Vue {
  private teamName: string =  '';
  private teamDescription: string = '';
  private teamLogo?: any;
  private teamLocations: string[] = [];

  private onSubmitNewTeam() {
    console.log(this.teamLogo);
  }


}
</script>

<style scoped lang="scss">
@import '../style/mixins.scss', '../style/color.scss';

.new-team {

  &__label {
    @include file-input;
    color: #fff;
    background-color: $blue-2;
    cursor: pointer;
  }

  &__file-name {
    @include file-input;
    background-color: $gray-1;
    appearance: none;
    margin-right: 10px;
  }

  &__li input[type="file"] {
    @include layout-size(1px, 1px);
    padding: 0;
    margin: -1px;
    position: absolute;
    overflow: hidden;
    clip: rect(0,0,0,0);
    border: 0;
  }
}
@media screen and (max-width: 768px) {
  .new-team {

  }
}
</style>

import {Vue, Component} from 'vue-property-decorator';

@Component
export default class FormValidate extends Vue {
  protected isCheckedEmail: boolean = false;
  private pwMatched: boolean = false;

  validateForm(target: HTMLElement[]): boolean {
    const inputArr: HTMLInputElement[] = Array.prototype
      .filter.call(target, (v) => v.tagName === 'INPUT');

    const existBlank = inputArr.some((v) => !this._.isEmpty(v.value));
    const pw = inputArr.find((v) => v.id === 'password');
    const pwCheck = inputArr.find((v) => v.id === 'passwordCheck');

    if (pwCheck) { this.pwMatched = pw === pwCheck; }

    if ([existBlank, this.pwMatched, this.isCheckedEmail].find((v) => !v)) {
      alert('양식을 확인해주세요.');
      return false;
    } else {
      return true;
    }
  }
}

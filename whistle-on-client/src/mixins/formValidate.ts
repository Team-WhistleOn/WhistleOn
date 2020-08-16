import {Vue, Component} from 'vue-property-decorator';

@Component
export default class FormValidate extends Vue {
  private isValid: boolean[] = [];

  public validateForm(target: HTMLInputElement[]): boolean {
    this.isValid = new Array(target.length).fill(false);
    console.log([...target].filter((v) => v.tagName === 'INPUT'));
    if (this.isValid.find((v) => !v)) {
      alert('양식을 확인해주세요.');
      return false;
    } else {
      return true;
    }
  }
}

<template>
  <div class="home">
    <div class="home__mask" />
    <section class="home__text">
      <h1 class="home__title">Let's Whistle On & Chill</h1>
      <h2 class="home__greeting">휘슬온에 오신 것을 환영합니다 :)</h2>
    </section>
    <aside class="home__aside">
      <router-link tag="button" to="/login" class="home__btn">시작하기</router-link>
      <h3 class="home__copyright">Copyright© Team Whistle-On All right reserved</h3>
    </aside>
  </div>
</template>

<style scoped lang="scss">
@import '../style/color.scss', '../style/variables', '../style/mixins', '../style/mobile/mixins.scss';

.home {
  @include flex-col;
  @include align-sa;
  @include layout-size(100vw, 100vh);
  background: {
    image: url('../assets/home-background.jpg');
    repeat: no-repeat;
    size: cover;
  }
  color: white;

  &__mask {
    @include fix-pos;
    @include layout-size();
    background-color: $modal-mask;
  }

  &__text {
    @include flex-col;
    @include align-center;
    @include rel-pos;
  }

  &__title {
    @include fade-in;
    font-size: $font-biggest;
    filter: drop-shadow(2px 2px 8px #fff);
  }

  &__greeting {
    @include slide-from-left;
    font-size: $font-bigger;
  }

  &__aside {
    @include flex-col;
    @include align-center;
    z-index: $z-index;
  }

  &__btn {
    @include rel-pos;
    @include border-radius(10px);
    @include pointer;
    padding: 15px 70px;
    color: white;
    background-color: $blue-2;
    margin-bottom: 150px;
  }
}

@media screen and (max-width: 768px){
  .home {
    @include flex-col;
    @include align-sa;
    @include border-radius(10px);
    background-image: url('https://media.istockphoto.com/photos/soccer-ball-on-the-green-grass-of-soccer-field-picture-id531853580?k=6&m=531853580&s=612x612&w=0&h=VLmZDrnH7yr2MnbTDMggYk1m37cZrsC4QzxBOwznkpw=');
    background-size: cover;
    background-repeat: no-repeat;
    color: white;

    &__mask {
      @include fix-pos;
      @include layout-size();
      background-color: $modal-mask;
    }

    &__title {
      font-size: $font-bigger;
    }

    &__greeting {
      font-size: $font-medium;
    }

    &__btn {
      @include border-radius(0);
      @include m-fix-pos(null, null, 0, 0);
      width: $full;
      margin-bottom: 0;
    }
  }
}
</style>

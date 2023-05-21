<template>
  <div class="dashboard-container">
    <div class="dashboard-text">
      Anze管理系统
    </div>
    <div>
      欢迎关注Anze的B站账号：<a href="https://space.bilibili.com/357711289">https://space.bilibili.com/357711289</a>   Anze
    </div>
    <div>
      My Blog System!!!
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
export default {
  name: 'Dashboard',
  computed: {
    ...mapGetters([
      'name'
    ])
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>

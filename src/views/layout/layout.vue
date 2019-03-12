<template>
  <div class="app-wrapper" :class="classObj">
    <sidebar class="sidebar-container"></sidebar>
    <div class="main-container">
      <navbar></navbar>
      <app-main></app-main>
    </div>
  </div>
</template>

<script>
  import {sidebar,navbar,appMain} from './componets'

  export default {
    name: 'layout',
    components: {
      sidebar,
      navbar,
      appMain
    },
    computed: {
      sidebar() {
        return this.$store.state.app.sidebar;
      },
      classObj() {
        return {
          hideSidebar: !this.sidebar.opened
        }
      }
    }
  }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  @import "../../styles/mixin.scss";
  .app-wrapper {
    @include clearfix;
    position: relative;
    height: 100%;
    width: 100%;
  }
</style>

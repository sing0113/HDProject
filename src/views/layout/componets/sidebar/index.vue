<template>
  <scroll-bar>
    <el-menu mode="vertical"
             :default-active="$route.path"
             :collapse="isCollapse"
             background-color="#304156"
             text-color="#bfcbd9"
             active-text-color="#409EFF">
      <sidebar-item :routes="routes"></sidebar-item>
    </el-menu>
  </scroll-bar>
</template>

<script>
  import scrollBar from '@/components/scrollBar'
  import sidebarItem from './sidebarItem'
  import {mapGetters} from 'vuex'

  export default {
    name: "sidebar",
    components: {
      scrollBar,
      sidebarItem
    },
    computed: {
      ...mapGetters([
        'sidebar'
      ]),
      routes() {
        return this.$router.options.routes;
      },
      isCollapse() {
        return !this.sidebar.opened;
      }
    }
  }
</script>

<style scoped>

</style>

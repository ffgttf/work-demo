<template>
  <el-container style="min-height: 100vh ">
    <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246);">
      <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x:hidden"
               background-color="rgb(48,65,86)"
               text-color="#fff"
               active-text-color="#ffd04b"
               :collapse-transition="false"
               :collapse="isCollapse"
               router
      >
        <div style="height: 60px; line-height: 60px; text-align: center">
<!--          <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; margin-right: 5px">-->
          <b style="color: white" v-show="logoTextShow">静海水务</b>
        </div>
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-location"></i>
            <span>用户及管理权限</span>
          </template>
          <el-menu-item-group>
            <template slot="title"></template>
            <el-menu-item index="/character" class="el-icon-user-solid"> 角色管理</el-menu-item>
            <el-menu-item index="/enter" class="el-icon-s-grid"> 营业厅管理</el-menu-item>
            <el-menu-item index="/user" class="el-icon-user"> 人员账号管理</el-menu-item>
          </el-menu-item-group>
        </el-submenu>
      </el-menu>
    </el-aside>
    <el-container>
      <el-header style="font-size: 12px;border-bottom:1px solid #ccc;line-height:60px; display:flex">
        <div style="flex: 1; font-size: 20px">
          <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
        </div>
        <el-dropdown style="width:70px; cursor:pointer" @command="logout">
          <span class="el-dropdown-link">用户<i class="el-icon-arrow-down el-icon--right"></i></span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item :command="1">退出</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </el-header>
      <el-main>
        <!-- 路由占位符 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Home",
  data() {
    return {
      logoTextShow: true,
      isCollapse: false,
      sideWidth: 200,
      collapseBtnClass: 'el-icon-s-fold',
    }
  },
  methods: {
    logout() {
      this.$confirm('此操作将注销登录, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        sessionStorage.removeItem("user")
        this.$router.push('/login')
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消操作'
        });
      });
    },
    collapse() {  //点击收缩按钮触发
      this.isCollapse = !this.isCollapse
      if (this.isCollapse) { //收缩
        this.sideWidth = 64
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false
      } else {    //展开
        this.sideWidth = 200
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true
      }
    },
  }
}
</script>

<style scoped>

</style>
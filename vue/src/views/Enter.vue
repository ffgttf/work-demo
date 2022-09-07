<template>
  <el-container>
    <el-main>
      <div style="margin-bottom: 30px">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>营业厅管理</el-breadcrumb-item>
        </el-breadcrumb>
        <router-view></router-view>
      </div>
      <el-form :inline="true" :model="shop" class="demo-form-inline">
        <el-form-item label="营业厅名称">
          <el-input v-model="shop.name" placeholder="请输入营业厅名称"></el-input>
        </el-form-item>
        <el-button style="text-align: right" type="primary" plain @click="onSubmit">查询</el-button>
        <el-button style="text-align: right" @click="empty">重置</el-button>
      </el-form>
      <!--搜索表单-->
      <el-row>
        <el-button type="primary" plain @click="dialogFormVisible = true">新增</el-button>
      </el-row>
      <!--添加数据对话框表单-->
      <div class="add-form">
        <el-dialog
            title="营业厅信息"
            align="center"
            :visible.sync="dialogFormVisible"
            width="30%">
          <el-form ref="form" :model="formData" :rules="rules" label-width="80px">
            <el-form-item label="编号" prop="code">
              <el-input v-model="formData.code" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="名称" prop="name">
              <el-input v-model="formData.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="addShop">提交</el-button>
              <el-button @click="dialogFormVisible = false">取消</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
      </div>
      <!--表单-->
      <el-table size="small" current-row-key="id"
                :data="dataList" border
                :row-class-name="tableRowClassName"
                @selection-change="handleSelectionChange"
                stripe highlight-current-row>
        <el-table-column
            type="selection"
            width="55">
        </el-table-column>
        <el-table-column type="index" align="center" label="序号" :index="getIndex"></el-table-column>
        <el-table-column prop="code" label="编号" align="center"></el-table-column>
        <el-table-column prop="name" label="营业厅名称" align="center"></el-table-column>
        <el-table-column prop="createDate" label="创建时间" align="center"></el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 编辑标签弹层 -->
      <div class="update-form">
        <el-dialog title="编辑检查项" :visible.sync="editDialogVisible">
          <el-form ref="dataEditForm" :model="formData" :rules="rules" label-position="right" label-width="100px">
            <el-row>
              <el-col :span="12">
                <el-form-item label="编号" prop="name">
                  <el-input v-model="formData.code"/>
                </el-form-item>
              </el-col>
              <el-col :span="12">
                <el-form-item label="营业厅名称" prop="userName">
                  <el-input v-model="formData.name"/>
                </el-form-item>
              </el-col>
            </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible = false">取消</el-button>
            <el-button type="primary" @click="handleEdit()">确定</el-button>
          </div>
        </el-dialog>
      </div>
    </el-main>
    <el-footer>
      <el-pagination
          background
          align="center"
          @current-change="handleCurrentChange"
          @size-change="handleSizeChange"
          :current-page="currentPage"
          :page-size="pageSize"
          :page-sizes="[5, 10, 15, 20]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </el-footer>
  </el-container>
</template>

<script>
export default {

  data() {
    return {
      //默认显示条数
      pageSize: 5,
      //总记录数
      total: 10,
      //当前页码
      currentPage: 1,
      editDialogVisible: false,
      //添加数据的对话框是否展示的标记
      dialogFormVisible: false,
      shop: {
        code: '',
        name: '',
        createDate: '',
      },
      formData: {
        code: '',
        name: '',
      },
      rules: {
        code: [{required: true, message: '营业厅编号不能为空', trigger: 'blur'}],
        name: [{required: true, message: '营业厅名称不能为空', trigger: 'blur'}],
      },
      dataList: [],//当前页要展示的分页列表数据
    }
  },
  //钩子函数,创建时启用
  created() {
    this.getAll();
  },
  methods: {
    onSubmit(shop) {
      this.getByCondition();
    },
    //清空内容
    empty() {
      this.shop.name = "";
    },
    /*获取全部*/
    getAll() {
      //发送Ajax请求
      const _this = this;
      this.$axios.get('http://localhost:8090/shop/pagelist/1/' + this.pageSize).then((res) => {
        _this.dataList = res.data.data
        _this.total = res.data.total
      });
    },
    addShop() {
      //发送Ajax请求
      this.$axios.post("http://localhost:8090/shop", this.formData).then((res) => {
        console.log(res.data);
        if (res.data.code == 20011) {
          //如果操作成功,关闭弹层,显示数据
          this.dialogFormVisible = false;
          this.$message.success("添加成功");
        } else if (res.data.code == 20010) {
          this.$message.error("添加失败");
        } else {
          this.$message.error(res.data.msg);
        }
      }).finally(() => {
        this.getAll();
      });
    },
    //颜色区分
    tableRowClassName({row, rowIndex}) {
      if ((rowIndex - 1) % 4 === 0) {
        return 'warning-row';
      } else if ((rowIndex - 3) % 4 === 0) {
        return 'success-row';
      }
      return '';
    },
    //复选框选中后执行的方法
    handleSelectionChange(val) {
      this.multipleSelection = val;

      console.log(this.multipleSelection)
    },

    //翻页index递增
    getIndex(index) {
      return index + (this.currentPage - 1) * this.pageSize + 1;
    },

    //分页相关
    handleCurrentChange(currentPage) {
      const _this = this;
      this.$axios.get('http://localhost:8090/shop/pagelist/' + currentPage + '/' + this.pageSize).then((res) => {
        _this.dataList = res.data.data
        _this.total = res.data.total
      })
    },
    handleSizeChange(val) {
      const _this = this;
      this.$axios.get('http://localhost:8090/shop/pagelist/' + this.currentPage + '/' + val).then((res) => {
        _this.dataList = res.data.data
        _this.total = res.data.total
      })
    },
    //条件查询
    getByCondition() {
      //发送Ajax请求
      this.$axios.get("http://localhost:8090/shop/" + this.shop.name).then((res) => {
        this.dataList = res.data.data;
      });
    },

    //弹出编辑窗口
    handleUpdate(row) {
      console.log(row);   //row.id 查询条件
      //查询数据,根据id查询
      this.$axios.get("http://localhost:8090/shop/check/" + row.id).then((res) => {
        if (res.data.code == 20041) {
          this.formData = res.data.data;
          this.editDialogVisible = true;
        } else {
          this.$message.error(res.data.msg);
        }
      });
    },

    //编辑
    handleEdit() {
      this.$axios.put("http://localhost:8090/shop", this.formData).then((res) => {
        if (res.data.code == 20031) {
          this.editDialogVisible = false;
          //重新查询
          this.getAll();
          //弹出消息提示
          this.$message({
            message: '恭喜你，修改成功',
            type: 'success'
          });
        } else if (res.data.code == 20030) {
          this.$message.error("修改失败");
        } else {
          this.$message.error(res.data.msg);
        }
      }).finally(() => {
        this.getAll();
      });
    },
  }
}
</script>

<style scoped>

</style>
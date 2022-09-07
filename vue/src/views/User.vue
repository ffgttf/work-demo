<template>
  <el-container>
    <el-main>
      <div style="margin-bottom: 30px">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
          <el-breadcrumb-item>人员账号管理</el-breadcrumb-item>
        </el-breadcrumb>
        <router-view></router-view>
      </div>
      <el-form :inline="true" :model="user" class="demo-form-inline">
        <el-form-item label="人员账号">
          <el-input v-model="user.name" placeholder="请输入人员账号"></el-input>
        </el-form-item>
        <el-form-item label="人员名称">
          <el-input v-model="user.uname" placeholder="请输入人员名称"></el-input>
        </el-form-item>
        <el-form-item label="角色名称">
          <el-input v-model="user.userName" placeholder="请输入角色名称"></el-input>
        </el-form-item>
        <el-button style="text-align: right" type="primary" plain @click="onSubmit">查询</el-button>
        <el-button style="text-align: right" @click="empty">重置</el-button>
      </el-form>
      <el-row>
        <el-button type="primary" plain @click="dialogFormVisible = true">新建人员</el-button>
      </el-row>
      <!--添加数据对话框表单-->
      <div class="add-form">
        <el-dialog
            title="角色信息"
            align="center"
            :visible.sync="dialogFormVisible"
            width="30%">
          <el-form ref="form" :model="formData" :rules="rules" label-width="80px">
            <el-form-item label="人员账号" prop="name">
              <el-input v-model="formData.name" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="人员名称" prop="uname">
              <el-input v-model="formData.uname" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="formData.password" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="password2">
              <el-input type="password" v-model="formData.password2" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="复核人" prop="checkName">
              <el-input v-model="formData.checkName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="收款人" prop="receiveName">
              <el-input v-model="formData.receiveName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="开票点编号" prop="code">
              <el-input v-model="formData.code" autocomplete="off"></el-input>
            </el-form-item>


            <el-form-item>
              <el-button type="primary" @click="addUser">提交</el-button>
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
        <el-table-column prop="name" label="人员账号" align="center"></el-table-column>
        <el-table-column prop="uname" label="人员名称" align="center"></el-table-column>
        <el-table-column prop="userName" label="角色名称" align="center"></el-table-column>
        <el-table-column prop="createDate" label="创建时间" align="center"></el-table-column>

        <el-table-column label="操作" align="center">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <!-- 编辑标签弹层 -->
      <div class="update-form">
        <el-dialog title="人员信息0" :visible.sync="editDialogVisible">
          <el-form ref="dataEditForm" :model="formData" :rules="rules" label-position="right" label-width="100px">
            <el-row>
              <el-col :span="12">
                <el-form-item label="人员账号" prop="name">
                  <el-input v-model="formData.name"/>
                </el-form-item>
              </el-col>
              <el-form-item label="人员名称" prop="uname">
                <el-input v-model="formData.uname"/>
              </el-form-item>
              <el-form-item label="密码" prop="password">
                <el-input v-model="formData.password"/>
              </el-form-item>
              <el-form-item label="确认密码" prop="password2">
                <el-input v-model="formData.password2"/>
              </el-form-item>
              <el-form-item label="复核人" prop="checkName">
                <el-input v-model="formData.checkName" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="收款人" prop="receiveName">
                <el-input v-model="formData.receiveName" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="开票点编号" prop="code">
                <el-input v-model="formData.code" autocomplete="off"></el-input>
              </el-form-item>
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
  name: "User",
  data() {
    var validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.formData.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      user: {
        id: '',
        name: '',
        uname: '',
        userName: '',
        createDate: '',
      },
      formData: {
        name: '',
        uname: '',
        password: '',
        password2: '',
        checkName: '',
        receiveName: '',
        code: '',
      },
      rules: {
        name: [{required: true, message: '人员账号不能为空', trigger: 'blur'}],
        uname: [{required: true, message: '人员名称不能为空', trigger: 'blur'}],
        password: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          {
            min: 6,
            max: 16,
            message: "长度在 6 到 16 个字符",
            trigger: "blur",
          },],
        password2: [
          {required: true, message: '密码不能为空', trigger: 'blur'},
          {validator: validatePass2, trigger: "blur", required: true},
        ],
      },
      dialogFormVisible: false,
      dataList: [],//当前页要展示的分页列表数据
      editDialogVisible: false,
      currentPage: 1,
      //默认显示条数
      pageSize: 5,
      //总记录数
      total: 10,
    }
  },
//钩子函数,创建时启用
  created() {
    this.getAll();
  },
  methods: {
    onSubmit(user) {
      this.getByCondition();
    },
    empty() {
      this.user.name = "";
      this.user.uname = "";
      this.user.userName = "";
    },
    /*获取全部*/
    getAll() {
      //发送Ajax请求
      const _this = this;
      this.$axios.get('http://localhost:8090/emp/pagelist/1/' + this.pageSize).then((res) => {
        _this.dataList = res.data.data
        _this.total = res.data.total
      });
    },
    //条件查询
    getByCondition() {
      //发送Ajax请求
      this.$axios.get("http://localhost:8090/emp/" + this.user.name + "/" + this.user.uname + "/" + this.user.userName).then((res) => {
        this.dataList = res.data.data;
      });
    },
    //添加
    addUser() {
      //发送Ajax请求
      this.$axios.post("http://localhost:8090/emp", this.formData).then((res) => {
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
    //弹出编辑窗口
    handleUpdate(row) {
      console.log(row);   //row.id 查询条件
      //查询数据,根据id查询
      this.$axios.get("http://localhost:8090/emp/check/" + row.id).then((res) => {
        if (res.data.code == 20041) {
          this.formData = res.data.data;
          this.editDialogVisible = true;
        } else {
          this.$message.error(res.data.msg);
        }
      });
    },
    //删除
    handleDelete(row) {
      //1.弹出提示框
      this.$confirm("此操作永久删除当前数据, 是否继续?", "提示", {
        type: 'info'
      }).then(() => {
        //2.做删除业务
        this.$axios.delete("http://localhost:8090/emp/" + row.id).then((res) => {
          if (res.data.code == 20021) {
            this.$message.success("删除成功");
          } else {
            this.$message.error("删除失败");
          }
          this.getAll();
        })
      }).catch(() => {
        //3.取消删除业务
        this.$message.info("取消删除操作");
      });
    },
    //编辑
    handleEdit() {
      this.$axios.put("http://localhost:8090/emp", this.formData).then((res) => {
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
    //分页相关
    handleCurrentChange(currentPage) {
      const _this = this;
      this.$axios.get('http://localhost:8090/emp/pagelist/' + currentPage + '/' + this.pageSize).then((res) => {
        _this.dataList = res.data.data
        _this.total = res.data.total
      })
    },
    handleSizeChange(val) {
      const _this = this;
      this.$axios.get('http://localhost:8090/emp/pagelist/' + this.currentPage + '/' + val).then((res) => {
        _this.dataList = res.data.data
        _this.total = res.data.total
      })
    },
  }
}
</script>

<style scoped>

</style>
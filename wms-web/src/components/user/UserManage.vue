<script>


export default {
  name: "UserManage",

  data() {
    let checkAge = (rule, value, callback) => {
      if (value > 120) {
        callback(new Error("年龄过大"));
      } else {
        callback();
      }
    };
    let checkDuplicate = (rule, value, callback) => {
      if (this.form.id) {
        return callback();
      }
      this.$axios.get(this.$httpUrl + "/user/findByNo?no=" + this.form.no).then(res => res.data).then(res => {
        if (res.code == 200) {
          // alert("账号已存在")
          callback(new Error("账号已存在"));
        } else {
          callback();
          // console.log("1111")
          // console.log(res.data)
        }
      })
    };
    return {
      tableData: Array(10),
      pageSize: 10,
      pageNum: 1,
      total: 0,
      name: '',
      sex: '',
      sexs: [
        {
          value: '1',
          label: '男'
        }, {
          value: '0',
          label: '女'
        },
      ],
      centerDialogVisible: false,
      form: {
        id: '',
        no: '',
        name: '',
        password: '',
        age: '',
        phone: '',
        sex: '',
        roleId: '2'
      },
      rules: {
        no: [
          {required: true, message: '请输入账号', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'},
          {validator: checkDuplicate, trigger: 'blur'}
        ],
        name: [
          {required: true, message: '请输入名字', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 3, max: 8, message: '长度在 3 到 8 个字符', trigger: 'blur'}
        ],
        age: [
          {required: true, message: '请输入年龄', trigger: 'blur'},
          {min: 1, max: 3, message: '长度在 1 到 3 个字符', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,3}$/, message: '年龄必须为正常的数字', trigger: 'blur'},
          {validator: checkAge, trigger: 'blur'}
        ],
        phone: [
          {required: true, message: '请输入电话', trigger: 'blur'},
          {
            pattern: /^(13[0-9]|14[5|7]|15[0|1|2|3|5|6|7|8|9]|18[0|1|2|3|5|6|7|8|9])\d{8}$/,
            message: '电话必须为正常的数字',
            trigger: 'blur'
          },
        ],
      },
    }
  },
  methods: {
    mod(row) {
      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.form.id = row.id
        this.form.no = row.no
        this.form.name = row.name
        this.form.age = row.age + ''
        this.form.sex = row.sex + ''
        this.form.password = ''
        this.form.phone = row.phone
        this.form.roleId = row.roleId

      })

    },

    del(id) {
      this.$axios.get(this.$httpUrl + '/user/delete?id='+id, this.form).then(res => res.data).then(res => {
        if (res.code==200) {
          this.$message({
            message: '删除成功',
            type: 'success'
          });
          this.loadPost()
        } else {
          this.$message.error('错了哦，这是一条错误消息');
        }
      })
    },
    resetForm() {
      this.$refs.form.resetFields();
      //  this.form={};
    },
    resetParam() {
      this.name = '';
      this.sex = '';
      this.loadPost()
    },
    add() {
      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.resetForm()
      })
    },
    loadGet() {
      this.$axios.get(this.$httpUrl + '/user/list').then(res => res.data).then(res => {
        console.log(res)
      })
    },
    doMod() {
      this.$axios.post(this.$httpUrl + '/user/update', this.form
          //     {
          //   // no:this.form.no,
          //   // name:this.form.name,
          //   // sex:this.form.sex,
          //   // password:this.form.password,
          //   // phone:this.form.phone,
          //   // total:this.total
          // }
      ).then(res => res.data).then(res => {
        if (res) {
          // alert('保存成功')
          this.centerDialogVisible = false
          this.$message({
            message: '添加成功',
            type: 'success'
          });
          this.loadPost()
          // this.resetForm()
        } else {
          this.$message.error('错了哦，这是一条错误消息');
        }
      })
    },
    doSave() {
      this.$axios.post(this.$httpUrl + '/user/save', this.form
          //     {
          //   // no:this.form.no,
          //   // name:this.form.name,
          //   // sex:this.form.sex,
          //   // password:this.form.password,
          //   // phone:this.form.phone,
          //   // total:this.total
          // }
      ).then(res => res.data).then(res => {
        if (res) {
          // alert('保存成功')
          this.centerDialogVisible = false
          this.$message({
            message: '添加成功',
            type: 'success'
          });
          this.loadPost()
          // this.resetForm()
        } else {
          this.$message.error('错了哦，这是一条错误消息');
        }
      })
    },
    save() {
      this.$refs.form.validate((valid) => {
        if (valid) {
          if (this.form.id) {
            this.doMod()
          } else {
            this.doSave()
          }
        } else {
          console.log('error submit!!');
          return false;
        }
      })
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
          .then(() => {
            done();
          })
          .catch(() => {
          });
    },
    loadPost() {
      this.$axios.post(this.$httpUrl + '/user/listPage', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          name: this.name,
          sex: this.sex,
          roleId:'2',
        }
        // total:this.total
      }).then(res => res.data).then(res => {
        if (res.code == 200) {
          this.tableData = res.data;
          this.total = res.total;
        } else {
          alert('没有数据')
        }

      })
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum = 1;
      this.pageSize = val;
      this.loadPost()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum = val
      this.loadPost()
    }
  },
  beforeMount() {
    //this.loadGet();
    this.loadPost();
  }
}
</script>

<template>
  <div>
    <div style="margin-bottom: 5px;">
      <el-input placeholder="请输入名字" v-model="name" suffix-icon="el-icon-search" style="width: 200px;"
                @keydown.enter.native="loadPost">
      </el-input>
      <el-select v-model="sex" filterable placeholder="请选择性别" style="margin-left: 5px; width: 150px;">
        <el-option
            v-for="item in sexs"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button type="primary" style="margin-left: 12px;" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>
      <el-button type="success" @click="add">添加</el-button>
    </div>
    <el-table :data="tableData"
              :border="true"
              :header-cell-style="{background: '#f2f5fc',color:'#555555'}"
    >
      <el-table-column prop="id" label="Id" width="60">
      </el-table-column>
      <el-table-column prop="no" label="账号" width="180">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="180">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="80">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="80">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.sex === 1 ? 'primary' : 'success'"
              disable-transitions>{{ scope.row.sex === 1 ? '男' : '女' }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="roleId" label="角色" width="120">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
              disable-transitions>
            {{ scope.row.roleId === 0 ? '超级管理员' : (scope.row.roleId === 1 ? '管理员' : '用户') }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="电话" width="180">
      </el-table-column>
      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="medium" type="primary" plain @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              style="margin-left: 3px;"
              title="确定删除吗？"
              @confirm=del(scope.row.id)
          >
            <el-button slot="reference" size="medium" type="danger" plain  >删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[2, 5, 10,20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="30%"
        center
        :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="编号" prop="no">
          <el-col :span="20">
            <el-input v-model="form.no">
            </el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="名字" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name">
            </el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-col :span="20">
            <el-input v-model="form.password">
            </el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-col :span="20">
            <el-input v-model="form.age">
            </el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="性别" prop="sex">
          <el-radio-group v-model="form.sex">
            <el-radio label="0">女</el-radio>
            <el-radio label="1">男</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-col :span="20">
            <el-input v-model="form.phone">
            </el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<style scoped>

</style>
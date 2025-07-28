<script>


export default {
  name: "StorageManage",
  data() {
    return {
      tableData: Array(10),
      pageSize: 10,
      pageNum: 1,
      total: 0,
      name: '',
      centerDialogVisible: false,
      form: {
        id: '',
        name: '',
        remark: ''
      },
      rules: {
        name: [
          {required: true, message: '请输入仓库名字', trigger: 'blur'},
          {min: 2, max: 8, message: '长度在 2 到 8 个字符', trigger: 'blur'}
        ],
      },
    }
  },
  methods: {
    mod(row) {
      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.form.id = row.id
        this.form.name = row.name
        this.form.remark = row.remark
      })

    },

    del(id) {
      this.$axios.get(this.$httpUrl +'/storage/delete?id='+id, this.form).then(res => res.data).then(res => {
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
      this.form.id='';
      this.$refs.form.resetFields();
      //  this.form={};
    },
    resetParam() {
      this.name = '';
      this.loadPost()
    },
    add() {

      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.resetForm()
      })
    },
    doMod() {
      this.$axios.post(this.$httpUrl +'/storage/update', this.form
      ).then(res => res.data).then(res => {
        if (res) {
          // alert('保存成功')
          this.centerDialogVisible = false
          this.$message({
            message: '修改成功',
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
      this.$axios.post(this.$httpUrl + '/storage/save', this.form
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
      this.form.id='';
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
      this.$axios.post(this.$httpUrl +'/storage/listPage', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          name: this.name,
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
      <el-button type="primary" style="margin-left: 12px;" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>
      <el-button type="success" @click="add">添加</el-button>
    </div>
    <el-table :data="tableData"
              :border="true"
              :header-cell-style="{background: '#f2f5fc',color:'#555555'}"
    >
      <el-table-column prop="name" label="姓名" width="180">
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="380">
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
        <el-form-item label="仓库名" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name">
            </el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="备注" prop="remark" >
          <el-col :span="20">
            <el-input v-model="form.remark"
                      type="textarea"
                      :autosize="{ minRows: 2, maxRows: 4}"
                      placeholder="请输入内容"
                      >
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
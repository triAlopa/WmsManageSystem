<script>


import SelectUser from "@/components/user/SelectUser.vue";

export default {
  name: "GoodsManage",
  components: {SelectUser},
  data() {
    let checkCount = (rule, value, callback) => {
      if (value > 9999) {
        callback(new Error('数量过大'));
      } else {
        callback();
      }
    };
    return {
      tableData: [],
      storageData: [],
      goodsTypeData: [],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      name: '',
      storage: '',
      goodsType: '',
      centerDialogVisible: false,
      inDialogVisible: false,
      innerVisible: false,
    // title:'',
      tempCount:0,
      form: {
        id: '',
        name: '',
        storage: '',
        goodstype: '',
        count: 0,
        remark: ''
      },
      doGoodsForm: {
        goods: '',
        goodName: '',
        count: 0,
        userid: '',
        userName: '',
        adminId: '',
        remark: '',
        action: '',
      },
      currentRow: {},
      user: JSON.parse(sessionStorage.getItem('CurUser')),
      inGoodsRules: {},
      rules: {
        name: [
          {required: true, message: '请输入物品名', trigger: 'blur'},
        ],
        storage: [
          {required: true, message: '请选择仓库', trigger: 'blur'},
        ],
        goodstype: [
          {required: true, message: '请选择分类', trigger: 'blur'},
        ],
        count: [
          {required: true, message: '请输入数量', trigger: 'blur'},
          {pattern: /^([1-9][0-9]*){1,4}$/, message: '数量为正整数', trigger: 'blur'},
          {validator: checkCount, trigger: 'blur'}
        ],
      },
    }
  },
  methods: {
    handleCurrentChangeGoods(val) {
      this.currentRow = val;
    },
    formatterStorage(row) {
      let temp = this.storageData.find(item => {
        return item.id == row.storage
      })
      return temp && temp.name
    },
    formatterGoodsType(row) {
      let temp = this.goodsTypeData.find(item => {
        return item.id == row.goodstype
      })
      return temp && temp.name
    },
    mod(row) {
      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.form.id = row.id
        this.form.name = row.name
        this.form.storage = row.storage
        this.form.goodstype = row.goodstype
        this.form.count = row.count
        this.form.remark = row.remark
      })

    },

    del(id) {
      this.$axios.get(this.$httpUrl + '/goods/delete?id=' + id, this.form).then(res => res.data).then(res => {
        if (res.code == 200) {
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
      this.form.id = '';
      this.$refs.form.resetFields();
      //  this.form={};
    },
    resetInForm() {
      this.doGoodsForm.goods ='';
      this.doGoodsForm.userName ='';
      this.$refs.doGoodsForm.resetFields();
    },
    resetParam() {

      this.name = '';
      this.storage = '';
      this.goodsType = ''
      this.loadPost()
    },
    add() {
      this.centerDialogVisible = true
      this.$nextTick(() => {
        this.resetForm()
        this.id = '';
      })
    },
    inGoods() {
      if (!this.currentRow.id) {
        this.$message({
          message: '请选择记录,:(',
          type: 'warning'
        });
        return;
      }
      this.inDialogVisible = true

      this.$nextTick(() => {
        this.resetInForm()
        this.doGoodsForm.goods = this.currentRow.id
        this.doGoodsForm.goodName = this.currentRow.name
        this.doGoodsForm.count = this.currentRow.count
        this.doGoodsForm.adminId = this.user.id
        this.doGoodsForm.remark = this.currentRow.remark
        this.doGoodsForm.action = '1'
      })
    },
    outGoods() {
      if (!this.currentRow.id) {
        this.$message({
          message: '请选择记录,:(',
          type: 'warning'
        });
        return;
      }
      this.inDialogVisible = true
      this.$nextTick(() => {
        this.resetInForm()
        this.doGoodsForm.goods = this.currentRow.id
        this.doGoodsForm.goodName = this.currentRow.name
        this.doGoodsForm.count = this.currentRow.count
        this.tempCount = this.currentRow.count
        this.doGoodsForm.adminId = this.user.id
        this.doGoodsForm.remark = this.currentRow.remark
        this.doGoodsForm.action ='2'
      })
    },
    doMod() {
      this.$axios.post(this.$httpUrl + '/goods/update', this.form
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
      this.$axios.post(this.$httpUrl + '/goods/save', this.form
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
      // this.form.id='';
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
    doGoods() {
      // alert( '之前货物数'+this.tempCount)
      // alert(this.doGoodsForm.count)
      if(this.doGoodsForm.action!='1'){

        if(this.tempCount<this.doGoodsForm.count){
          this.$message.error('BYD 无中生有是吧😅');
          return ;
        }
         this.doGoodsForm.count=-this.doGoodsForm.count;
      }
      this.$axios.post(this.$httpUrl + '/record/save', this.doGoodsForm
      ).then(res => res.data).then(res => {
        if (res) {
          // alert('保存成功')
          this.inDialogVisible = false
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
    selectUser() {
      this.innerVisible = true;
    },
    confirmUser() {
      this.innerVisible = false;
    },
    doSelectUser(val) {
      this.doGoodsForm.userName = val.name;
      this.doGoodsForm.userid = val.id
    },
    loadPost() {
      this.$axios.post(this.$httpUrl + '/goods/listPage', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          name: this.name,
          storage: this.storage + '',
          goodsType: this.goodsType + '',
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
    loadStorage() {
      this.$axios.get(this.$httpUrl + '/storage/list').then(res => res.data).then(res => {
        if (res.code == 200) {
          console.log(res.data)
          this.storageData = res.data;

        } else {
          alert('没有数据')
        }

      })
    },
    loadGoodsType() {
      this.$axios.get(this.$httpUrl + '/goodstype/list').then(res => res.data).then(res => {
        if (res.code == 200) {
          console.log(res.data)
          this.goodsTypeData = res.data;

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
    this.loadGoodsType();
    this.loadStorage();
    this.loadPost();
  }
}
</script>

<template>
  <div>
    <div style="margin-bottom: 5px;">
      <el-input placeholder="请输入物品名" v-model="name" suffix-icon="el-icon-search" style="width: 200px;"
                @keydown.enter.native="loadPost">
      </el-input>
      <el-select v-model="storage" placeholder="请选择仓库名" style="margin-left: 5px">
        <el-option
            v-for="item in storageData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-select v-model="goodsType" placeholder="请选择分类" style="margin-left: 5px">
        <el-option
            v-for="item in goodsTypeData"
            :key="item.id"
            :label="item.name"
            :value="item.id">
        </el-option>
      </el-select>
      <el-button type="primary" style="margin-left: 12px;" @click="loadPost">查询</el-button>
      <el-button type="success" @click="resetParam">重置</el-button>
      <el-button type="success" @click="add" v-if="user.roleId!=2">添加</el-button>
      <el-button type="success" @click="inGoods" v-if="user.roleId!=2" >入库</el-button>
      <el-button type="success" @click="outGoods" v-if="user.roleId!=2">出库</el-button>
    </div>
    <el-table :data="tableData"
              :border="true"
              :header-cell-style="{background: '#f2f5fc',color:'#555555'}"
              highlight-current-row
              @current-change="handleCurrentChangeGoods"
    >
      <el-table-column prop="id" label="id" width="180">
      </el-table-column>
      <el-table-column prop="name" label="物品名" width="180">
      </el-table-column>
      <el-table-column prop="storage" label="仓库名" width="180" :formatter="formatterStorage">
      </el-table-column>
      <el-table-column prop="goodstype" label="分类名" width="180" :formatter="formatterGoodsType">
      </el-table-column>
      <el-table-column prop="count" label="数量" width="180">
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="380">
      </el-table-column>
      <el-table-column prop="operate" label="操作" v-if="user.roleId!=2">
        <template slot-scope="scope">
          <el-button size="medium" type="primary" plain @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              style="margin-left: 3px;"
              title="确定删除吗？"
              @confirm=del(scope.row.id)
          >
            <el-button slot="reference" size="medium" type="danger" plain>删除</el-button>
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
        title="物品维护"
        :visible.sync="centerDialogVisible"
        width="30%"
        center
        :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物品名" prop="name">
          <el-col :span="20">
            <el-input v-model="form.name">
            </el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="仓库名" prop="storage">
          <el-col :span="20">
            <el-select v-model="form.storage" placeholder="请选择仓库名">
              <el-option
                  v-for="item in storageData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="分类名" prop="goodstype">
          <el-col :span="20">
            <el-select v-model="form.goodstype" placeholder="请选择分类">
              <el-option
                  v-for="item in goodsTypeData"
                  :key="item.id"
                  :label="item.name"
                  :value="item.id">
              </el-option>
            </el-select>
          </el-col>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="form.count">
            </el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
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
    <el-dialog
        title=出入库
        :visible.sync="inDialogVisible"
        width="30%"
        center
        :before-close="handleClose">
      <el-dialog
          width="60%"
          title="选择申请人"
          :visible.sync="innerVisible"
          append-to-body>
        <SelectUser align="center" @doSelectUser="doSelectUser"></SelectUser>
        <el-button @click=" innerVisible = false">取 消</el-button>
        <el-button type="primary" @click="confirmUser">确 定</el-button>
      </el-dialog>
      <el-form ref="doGoodsForm" :model="doGoodsForm" :rules="inGoodsRules" label-width="80px">
        <el-form-item label="物品名" prop="name">
          <el-col :span="20">
            <el-input v-model="doGoodsForm.goodName" readonly>
            </el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="申请人" prop="username">
          <el-col :span="20">
            <el-input v-model="doGoodsForm.userName" readonly @click.native="selectUser">
            </el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="数量" prop="count">
          <el-col :span="20">
            <el-input v-model="doGoodsForm.count">
            </el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-col :span="20">
            <el-input v-model="doGoodsForm.remark"
                      type="textarea"
                      :autosize="{ minRows: 2, maxRows: 4}"
                      placeholder="请输入内容"
            >
            </el-input>
          </el-col>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click=" inDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="doGoods">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>
<style scoped>

</style>
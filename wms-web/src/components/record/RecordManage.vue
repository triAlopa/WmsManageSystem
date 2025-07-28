<script>

export default {
  name: "RecordManage",
  data() {
    return {
      user :JSON.parse(sessionStorage.getItem('CurUser')),
      tableData:[],
      goodsTypeData:[],
      storageData:[],
      pageSize: 10,
      pageNum: 1,
      total: 0,
      name: '',
      storage:'',
      goodsType:'',
      centerDialogVisible: false,
      form: {
        id:'',
        name:'',
        storage:'',
        goodstype:'',
        count:'',
        remark: ''
      },
    }
  },
  methods: {
      resetParam() {
      this.name = '';
      this.storage='';
      this.goodsType=''
      this.loadPost();
    },
    loadGoodsType() {
      this.$axios.get(this.$httpUrl +'/goodstype/list').then(res => res.data).then(res => {
        if (res.code == 200) {
         // console.log(res.data)
          this.goodsTypeData = res.data;
        } else {
          alert('没有数据')
        }

      })
    },
    loadStorage() {
      this.$axios.get(this.$httpUrl +'/storage/list').then(res => res.data).then(res => {
        if (res.code == 200) {
          // console.log(res.data)
          this.storageData = res.data;

        } else {
          alert('没有数据')
        }

      })
    },
    loadPost() {
      this.$axios.post(this.$httpUrl +'/record/listPageC', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          name: this.name,
          storage:this.storage+'',
          goodsType:this.goodsType+'',
          userId:this.user.id,
          roleId:this.user.roleId,
        }
        // total:this.total
      }).then(res => res.data).then(res => {
        if (res.code == 200) {
          console.log(res)
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
    </div>
    <el-table :data="tableData"
              :border="true"
              :header-cell-style="{background: '#f2f5fc',color:'#555555'}"
              :default-sort = "{prop: 'createtime', order: 'descending'}"
    >
      <el-table-column prop="id" label="id" width="100">
      </el-table-column>
      <el-table-column prop="goodsname" label="物品名" width="100">
      </el-table-column>
      <el-table-column prop="storagename" label="仓库名" width="100"  >
      </el-table-column>
      <el-table-column prop="goodsTypename" label="分类名" width="100" >
      </el-table-column>
      <el-table-column prop="username" label="申请人" width="100"  >
      </el-table-column>
      <el-table-column prop="adminname" label="操作人" width="100" >
      </el-table-column>
      <el-table-column prop="count" label="数量" width="100">
      </el-table-column>
      <el-table-column prop="createtime" label="操作时间" width="300"  sortable>
      </el-table-column>
      <el-table-column prop="remark" label="备注" width="380">
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
  </div>
</template>
<style scoped>

</style>
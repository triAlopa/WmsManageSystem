<script>



export default {
  name: "SelectUser",

  data() {
    return {
      currentRow: {},
      tableData: Array(5),
      pageSize: 5,
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
      form: {
        id: '',
        no: '',
        name: '',
        password: '',
        age: '',
        phone: '',
        sex: '',
        roleId: '1'
      },
    }
  },
  methods: {
    resetParam() {
      this.name = '';
      this.sex = '';
      this.loadPost()
    },
    handleCurrentChangeUser(val) {
      this.$emit("doSelectUser", val)
    },
    loadPost() {
      this.$axios.post(this.$httpUrl + '/user/listPage', {
        pageSize: this.pageSize,
        pageNum: this.pageNum,
        param: {
          name: this.name,
          sex: this.sex,
          roleId: '2',
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
    },

  },
  beforeMount() {
    this.loadPost();
  },
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
    </div>
    <el-table :data="tableData"
              :border="true"
              :header-cell-style="{background: '#f2f5fc',color:'#555555'}"
              highlight-current-row
              @current-change="handleCurrentChangeUser"

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
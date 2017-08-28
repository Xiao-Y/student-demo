<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-default/index.css">
</head>
<body>

<div id="app">
    <%-- 显示列表 --%>
    <template>
        <el-button @click="addBookButton" el-button type="info">添加</el-button>
        <el-table :data="books" style="width: 100%" :default-sort="{prop: 'id', order: 'descending'}">
            <el-table-column prop="id" label="ID" sortable width="180"></el-table-column>
            <el-table-column prop="author" label="作者"></el-table-column>
            <el-table-column prop="name" label="姓名"></el-table-column>
            <el-table-column prop="price" label="价格" sortable></el-table-column>
            <el-table-column prop="date" label="出版时间" sortable></el-table-column>
            <el-table-column label="操作" width="180">
                <template scope="scope">
                    <el-button @click="delBook(scope.$index)" el-button type="danger">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </template>

    <%-- 添加数据 --%>
    <template>
        <div id="addBook" style="display: none">
            <table>
                <tr>
                    <td>作者</td>
                    <td>
                        <el-input v-model="book.author" placeholder="请输入内容"></el-input>
                    </td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td>
                        <el-input v-model="book.name" placeholder="请输入内容"></el-input>
                    </td>
                </tr>
                <tr>
                    <td>价格</td>
                    <td>
                        <el-input v-model="book.price" placeholder="请输入内容"></el-input>
                    </td>
                </tr>
                <tr>
                    <td>出版时间</td>
                    <td>
                        <el-date-picker v-model="book.date" type="date" format="yy-MM-dd" placeholder="选择日期"></el-date-picker>
                    </td>
                </tr>
                <tr>
                    <td colspan="4">
                        <el-button @click="submitData" el-button type="info">提交</el-button>
                    </td>
                </tr>
            </table>
        </div>
    </template>
</div>
</body>
<!-- 先引入 Vue -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- 引入组件库 -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<%-- 引入axios--%>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
<script>
    Vue.prototype.$ajax = axios;  //修改原型链
    var vm = new Vue({
        el: '#app',
        created: function () {
            var $this = this;
            $this.$ajax.get('book.json').then(function (resp) {
                $this.books = resp.data
                console.info($this.books);
            }).catch(function (err) {
                console.info(err);
            });
        },
        data: {
            books: [],
            book: {}
        },
        methods: {
            addBookButton: function () {
                $("#addBook").show();
            },
            submitData: function () {
                var $this = this;
                $this.book.id = $this.books.length + 1;
                $this.books.push($this.book);
                $this.book = {};
                $("#addBook").hide();
                this.$message({
                    message: '信息添加成功',
                    type: 'success'
                });
            },
            delBook: function (index) {
                console.info(index);
                this.$confirm('此操作将永久删除该信息, 是否继续?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    //删除
                    this.books.splice(index, 1);
                    //提示信息
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            }
        }
    });
</script>
</html>

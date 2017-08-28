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
        <input @click="addBookButton" type="button" value="添加">
        <el-table :data="books" style="width: 100%">
            <el-table-column prop="author" label="作者" width="180"></el-table-column>
            <el-table-column prop="name" label="姓名" width="180"></el-table-column>
            <el-table-column prop="price" label="价格"></el-table-column>
        </el-table>
    </template>

    <%-- 添加数据 --%>
    <template>
        <div id="addBook" style="display: none">
            <table>
                <tr>
                    <td>作者</td>
                    <td><input v-model="book.author" type="text"></td>
                </tr>
                <tr>
                    <td>姓名</td>
                    <td><input v-model="book.name" type="text"></td>
                </tr>
                <tr>
                    <td>价格</td>
                    <td><input v-model="book.price" type="text"></td>
                </tr>
                <tr>
                    <td colspan="2"><input @click="submitData" type="button" value="提交"></td>
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
                console.info($this.book);
                console.info($this.book.author);
                console.info($this.book.name);
                console.info($this.book.price);
                $this.books.push($this.book);
                $this.book = {};
                $("#addBook").hide();
            }
        }
    });
</script>
</html>

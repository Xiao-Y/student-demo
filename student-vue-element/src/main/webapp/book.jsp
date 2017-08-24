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
    <template>
        <el-table :data="books" style="width: 100%">
            <el-table-column prop="author" label="日期" width="180"></el-table-column>
            <el-table-column prop="name" label="姓名" width="180"></el-table-column>
            <el-table-column prop="price" label="地址"></el-table-column>
        </el-table>
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
    new Vue({
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
            books: []
        }
    });
</script>
</html>

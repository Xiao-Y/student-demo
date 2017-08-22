<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>分栏偏移</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-default/index.css">
    <style lang="stylus" scoped>
        @import url("../../css/style.css");
    </style>
</head>
<body>
<div id="app">
    <el-row :gutter="20">
        <el-col :span="6">
            <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="6" :offset="6">
            <div class="grid-content bg-purple"></div>
        </el-col>
    </el-row>
    <el-row :gutter="20">
        <el-col :span="6" :offset="6">
            <div class="grid-content bg-purple"></div>
        </el-col>
        <el-col :span="6" :offset="6">
            <div class="grid-content bg-purple"></div>
        </el-col>
    </el-row>
    <el-row :gutter="20">
        <el-col :span="12" :offset="6">
            <div class="grid-content bg-purple"></div>
        </el-col>
    </el-row>
</div>
</body>
<!-- 先引入 Vue -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- 引入组件库 -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
    new Vue().$mount('#app')
</script>
</html>

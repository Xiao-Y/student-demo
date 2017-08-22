<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Radio 单选框</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-default/index.css">
</head>
<body>
<div id="app">
    <br/><br/>
    要使用 Radio 组件，只需要设置v-model绑定变量，选中意味着变量的值为相应 Radio label属性的值，label可以是String或者Number。
    <div class="block">
        <span class="demonstration">基础用法</span>
        <span class="wrapper">
            <template>
                <el-radio class="radio" v-model="radio1" label="1">备选项</el-radio>
                <el-radio class="radio" v-model="radio1" label="2">备选项</el-radio>
            </template>
        </span>
    </div>

    <br/><br/>
    注意：请牢记，选中的条件是绑定的变量值等于label中的值。只要在el-radio元素中设置disabled属性即可，它接受一个Boolean，true为禁用。
    <div class="block">
        <span class="demonstration">禁用状态</span>
        <span class="wrapper">
            <template>
                <el-radio disabled v-model="radio2" label="禁用">备选项</el-radio>
                <el-radio disabled v-model="radio2" label="选中且禁用">备选项</el-radio>
            </template>
        </span>
    </div>
    <br/><br/>
    结合el-radio-group元素和子元素el-radio可以实现单选组，在el-radio-group中绑定v-model，
    在el-radio中设置好label即可，无需再给每一个el-radio绑定变量，另外，还提供了change事件来响应变化，它会传入一个参数value.
    <div class="block">
        <span class="demonstration">单选框组</span>
        <span class="wrapper">
            <template>
                <el-radio-group v-model="radio3">
                    <el-radio :label="3">备选项</el-radio>
                    <el-radio :label="6">备选项</el-radio>
                    <el-radio :label="9">备选项</el-radio>
                </el-radio-group>
            </template>
        </span>
    </div>
    <br/><br/>
    <div class="block">
        <span class="demonstration">按钮样式</span>
        <span class="wrapper">
            <template>
                <el-radio-group v-model="radio3">
                    <el-radio-button label="上海"></el-radio-button>
                    <el-radio-button label="北京"></el-radio-button>
                    <el-radio-button label="广州"></el-radio-button>
                    <el-radio-button label="深圳"></el-radio-button>
                </el-radio-group>
                <div style="margin: 15px 0;"></div>
                <el-radio-group v-model="radio4">
                    <el-radio-button label="上海"></el-radio-button>
                    <el-radio-button label="北京" :disabled="true"></el-radio-button>
                    <el-radio-button label="广州"></el-radio-button>
                    <el-radio-button label="深圳"></el-radio-button>
                </el-radio-group>
                <div style="margin: 15px 0;"></div>
                <el-radio-group v-model="radio5" :disabled="true">
                    <el-radio-button label="上海"></el-radio-button>
                    <el-radio-button label="北京"></el-radio-button>
                    <el-radio-button label="广州"></el-radio-button>
                    <el-radio-button label="深圳"></el-radio-button>
                </el-radio-group>
            </template>
        </span>
    </div>
</div>
</body>
<!-- 先引入 Vue -->
<script src="https://unpkg.com/vue/dist/vue.js"></script>
<!-- 引入组件库 -->
<script src="https://unpkg.com/element-ui/lib/index.js"></script>
<script>
    var Main = {
        data() {
            return {
                radio1: '1',
                radio2: '选中且禁用',
                radio3: 3,
                radio4: '上海',
                radio5: '上海',
                radio6: '上海'
            };
        }
    }
    var Ctor = Vue.extend(Main)
    new Ctor().$mount('#app')
</script>
</html>

<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Checkbox 多选框</title>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-default/index.css">
</head>
<body>
<div id="app">
    <br/><br/>
    在el-checkbox元素中定义v-model绑定变量，单一的checkbox中，默认绑定变量的值会是Boolean，选中为true
    <div class="block">
        <span class="demonstration">基础用法</span>
        <span class="wrapper">
            <template>
                <el-checkbox v-model="checked0">备选项</el-checkbox>
                <el-checkbox v-model="checked">备选项</el-checkbox>
            </template>
        </span>
    </div>
    <br/><br/>
    设置disabled属性即可。
    <div class="block">
        <span class="demonstration">禁用状态</span>
        <span class="wrapper">
            <template>
                <el-checkbox v-model="checked1" disabled>备选项1</el-checkbox>
                <el-checkbox v-model="checked2" disabled>备选项</el-checkbox>
            </template>
        </span>
    </div>
    <br/><br/>
    checkbox-group元素能把多个 checkbox 管理为一组，只需要在 Group 中使用v-model绑定Array类型的变量即可。
    el-checkbox 的 label属性是该 checkbox 对应的值，若该标签中无内容，则该属性也充当 checkbox 按钮后的介绍。
    label与数组中的元素值相对应，如果存在指定的值则为选中状态，否则为不选中。
    <div class="block">
        <span class="demonstration">禁用状态</span>
        <span class="wrapper">
            <template>
                <el-checkbox-group v-model="checkList">
                    <el-checkbox label="复选框 A"></el-checkbox>
                    <el-checkbox label="复选框 B"></el-checkbox>
                    <el-checkbox label="复选框 C"></el-checkbox>
                    <el-checkbox label="禁用" disabled></el-checkbox>
                    <el-checkbox label="选中且禁用" disabled></el-checkbox>
                </el-checkbox-group>
            </template>
        </span>
    </div>
    <br/><br/>
    indeterminate 属性用以表示 checkbox 的不确定状态，一般用于实现全选的效果
    <div class="block">
        <span class="demonstration">indeterminate 状态</span>
        <span class="wrapper">
            <template>
                <el-checkbox :indeterminate="isIndeterminate" v-model="checkAll"
                             @change="handleCheckAllChange">全选</el-checkbox>
                <div style="margin: 15px 0;"></div>
                <el-checkbox-group v-model="checkedCities" @change="handleCheckedCitiesChange">
                    <el-checkbox v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox>
                </el-checkbox-group>
            </template>
        </span>
    </div>
    <br/><br/>
    使用 min 和 max 属性能够限制可以被勾选的项目的数量
    <div class="block">
        <span class="demonstration">可选项目数量的限制</span>
        <span class="wrapper">
            <template>
                <el-checkbox-group v-model="checkedCities1" :min="1" :max="2">
                    <el-checkbox v-for="city in cities1" :label="city" :key="city">{{city}}</el-checkbox>
                </el-checkbox-group>
            </template>
        </span>
    </div>
    <br/><br/>
    只需要把el-checkbox元素替换为el-checkbox-button元素即可。此外，Element 还提供了size属性，支持large和small两种
    <div class="block">
        <span class="demonstration">按钮样式</span>
        <span class="wrapper">
            <template>
                <div style="margin: 15px 0;"></div>
                <el-checkbox-group v-model="checkboxGroup1">
                    <el-checkbox-button v-for="city in cities" :label="city" :key="city">{{city}}</el-checkbox-button>
                </el-checkbox-group>
                <div style="margin: 15px 0;"></div>
                <el-checkbox-group v-model="checkboxGroup2" size="small">
                    <el-checkbox-button v-for="city in cities" :label="city" :disabled="city === '深圳'"
                                        :key="city">{{city}}</el-checkbox-button>
                </el-checkbox-group>
                <div style="margin: 15px 0;"></div>
                <el-checkbox-group v-model="checkboxGroup3" size="large" fill="#324057" text-color="#a4aebd" :min="1"
                                   :max="3">
                    <el-checkbox-button v-for="city in cities3" :label="city" :key="city">{{city}}</el-checkbox-button>
                </el-checkbox-group>
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
    const cityOptions = ['上海', '北京', '广州', '深圳'];

    var Main = {
        data() {
            return {
                checked0: false,
                checked: true,
                checked1: false,
                checked2: true,
                checkList: ['选中且禁用', '复选框 A'],

                checkAll: true,
                checkedCities: ['上海', '北京'],
                cities: cityOptions,
                isIndeterminate: true,

                checkedCities1: ['上海', '北京'],
                cities1: cityOptions,

                checkboxGroup1: ['上海'],
                checkboxGroup2: ['北京'],
                checkboxGroup3: ['广州'],
                cities3: cityOptions
            };
        },
        methods: {
            handleCheckAllChange(event) {
                this.checkedCities = event.target.checked ? cityOptions : [];
                this.isIndeterminate = false;
            },
            handleCheckedCitiesChange(value) {
                let checkedCount = value.length;
                this.checkAll = checkedCount === this.cities.length;
                this.isIndeterminate = checkedCount > 0 && checkedCount < this.cities.length;
            }
        }
    }
    var Ctor = Vue.extend(Main);
    new Ctor().$mount('#app');
</script>
</html>

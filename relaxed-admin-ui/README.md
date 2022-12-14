# Relaxed Admin 使用指南

## 项目简言

Relaxed-admin 是一款便捷声明式快速开发平台，适用于搭建中小型后台管理系统。

开发目的: 仅仅是为了毕设而毕设，内置了一些常用后台管理组件，方便大家二次开发。

## 技术栈

+ 开发工具 WebStorm、Intellij Idea

+ 编程语言 java、javascript 

+ 前端框架 Vue2、element-ui、vue-element-admin

+ 后端框架 Springboot、Relaxed

+ 数据存储 Redis、Mysql

+ 代码规范

  ​      前端 EditorConfig、Prettier、EsLint 

  ​      后端 spring.javaformat

+ 自动部署 Git、Jenkins、Nginx

## 一.开发标准

> 以下用开发配置管理页面作为使用示例

### 1、菜单配置

> 此处由两部分组成 后端菜单基础参数+前端路由组件表映射

菜单配置 sql如下 ( ps：也可以通过前端界面化配置)

此处父级目录路径为system,当前菜单路径为config;

实际路径访问规则: /system/config

```sql
INSERT INTO `relaxed`.`sys_menu` (`id`, `parent_id`, `title`, `icon`, `permission`, `path`, `target_type`, `uri`, `sort`, `keep_alive`, `hidden`, `type`, `always_show`, `remarks`, `deleted`, `create_by`, `update_by`, `created_time`, `updated_time`) VALUES (110700, 110000, '配置管理', NULL, NULL, 'config', 1, '', 1, 0, 0, 1, 0, NULL, 0, NULL, NULL, NOW(), NOW());

```

前端路由表映射

进入到`src/router/tables`创建`configRouteTable.js`文件

内容如下

```json
export default [
  {
    //组件访问路径 与 实际访问路径保持一致
    path:'/system/config',
     //指定组件存放路径
    component: () => import('@/views/system/config/index'),
  }
]

```

### 2、页面开发

#### index.vue

进入`src/views/system/config`目录下创建`index.vue`文件

内容如下

```vue
<template>
  <div class="app-container">
    <!--搜索组件-->
    <page-search ref="pageSearchRef" :searchConfig="searchConfig"
                 @resetBtnClick="handleResetClick" @queryBtnClick="handleSearchClick"></page-search>
    <!--表格组件-->
    <pro-table ref="pageContentRef" :content-table-config="contentConfig" :request="tableRequest"
               @newBtnClick="showNewModal" @editBtnClick="showEditModal"
               @delBtnClick="handleDelClick"
    ></pro-table>
    <!--模态表单组件-->
    <form-modal ref="formModal" :modal-config="modalConfig"
                :req-function="reqFunction"
                @submitSuccess="handleSubmit"
    ></form-modal>


  </div>
</template>

<script>

  //页面配置参数
  import {contentConfig} from "./config/content.config";
  import {searchConfig} from "./config/search.config";
  import {modalConfig} from "./config/modal.config";
  //页面相关请求方法
  import {getPage, addObj, putObj, delObj} from "@/api/config";

  export default {
    name: "config",
    data() {
      return {
        //页面相关配置
        contentConfig: contentConfig,
        searchConfig: searchConfig,
        modalConfig: modalConfig,
        //表格请求
        tableRequest: getPage,
        //表单请求
        reqFunction: {
          create: addObj,
          update: putObj
        },
      }
    },
    methods: {
      //表格相关
      showNewModal() {
        this.$refs.formModal.add({title: '新增配置'})
      },
      showEditModal(item) {
        this.$refs.formModal.update({title: '编辑配置', item})
      },
      //删除数据
      handleDelClick(item) {
        delObj(item.id).then(res => {
          this.$refs.pageContentRef.refreshTable(false)
        })
      },
      //模态框相关 提交成功后刷新表格
      handleSubmit(res) {
        this.$refs.pageContentRef.refreshTable(false)
      },
      //搜索框相关 搜索数据
      handleSearchClick(formData) {
        this.$refs.pageContentRef.searchTable(formData)
      },
      //重置搜索
      handleResetClick() {
        this.$refs.pageContentRef.resetTable()
      },

    }

  }
</script>

<style lang="scss" scoped>

</style>

```

#### contentConfig.js

```js
export const contentConfig={
  title: '配置列表',
  showSelectColumn:false,
  tableProps:{
  'row-style': {
      height: '10px'
    },
    'cell-style': {
      padding: '2px 0'
    },

  },
  propList:[
    {
      prop: 'id',
      label:'主键',
      minWidth: 100,
      hidden: true
    },
    {
      prop: 'name',
      label:'配置名称',
      minWidth: 180,
    },
    {
      prop: 'confKey',
      label:'配置键',
      minWidth: 150,
    },
    {
      prop: 'confValue',
      label:'配置值',
      minWidth: 150,
    },
    {
      prop: 'category',
      label:'分类',
      minWidth: 150,
    },
    {
      prop: 'remarks',
      label:'备注',
      minWidth: 150,
    },
    {
      prop: 'createdTime',
      label:'创建时间',
      minWidth:180,
      slotName: 'createdTime'
    },
    {
      prop: 'updatedTime',
      label:'更新时间',
      minWidth:180,
      slotName: 'updatedTime'
    },
    {

      label:'操作',
      minWidth:250,
      slotName:'handler'
    }
  ]
}

```



#### search.config.js

```js
export const searchConfig={
  formItems:[
    {
      type:'text',
      label: '配置键',
      field:'confKey',
      placeholder:'请输入配置键'
    },
    {
      type:'text',
      label: '配置名称',
      field:'name',
      placeholder:'请输入配置名称'
    },

  ],
}

```



#### modal.config.js

```js
export const modalConfig= {

  formItems:[
    {
      type:'text',
      label: '配置id',
      field:'id',
      isHidden: true
    },
    {
      type:'text',
      label: '配置名称',
      field:'name',
      placeholder:'请输入配置名称',
      rules:[
        { required: true, message: '请输入配置名称', trigger: 'blur' },
      ]

    },
    {
      type:'text',
      label: '配置键值',
      field:'confKey',
      placeholder:'请输入配置键值',
      rules:[
        { required: true, message: '请输入配置键值', trigger: 'blur' },

      ]
    },
    {
      type:'text',
      label: '配置值',
      field:'confValue',
      placeholder:'请输入配置值',
      rules:[
        { required: true, message: '请输入配置值', trigger: 'blur' },

      ]
    },
    {
      type:'text',
      label: '分类',
      field:'category',
      placeholder:'请输入分类',
      rules:[
        { required: true, message: '请输入分类', trigger: 'blur' },

      ]
    },
    {
      type:'text',
      label: '备注信息',
      field:'remarks',
      placeholder:'请输入备注',
      config: {
         type:"textarea"
       }
    },

  ],
}

```



#### @/api/config

```js
import yiRequest from "@/utils/request";
/**
 * 获取配置分页信息
 * @param queryInfo
 * @returns {Promise | Promise<unknown>}
 */
export function getPage(queryInfo) {
  return yiRequest.get({
    url: '/system/config/page',
    params: queryInfo
  })
}

/**
 * 添加配置信息
 * @param payload
 * @returns {Promise | Promise<unknown>}
 */
export function addObj(payload) {
  return yiRequest.post({
    url: '/system/config',
    data:payload,
  })
}

/**
 * 编辑配置信息
 * @param payload
 * @returns {Promise | Promise<unknown>}
 */
export function putObj(payload) {
  return yiRequest.put({
    url: '/system/config',
    data:payload,
  })
}

/**
 * 删除配置信息
 * @param id
 * @returns {Promise | Promise<unknown>}
 */
export function delObj(id) {
  return yiRequest.delete({
    url: '/system/config/' + id,
  })
}


```



## 组件API

### 一、ProTable表格组件

**Table Attributes**

| 参数              | 说明                                          | 类型            | 可选值                                                       | 默认值                                                       |
| ----------------- | --------------------------------------------- | --------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| title             | 表格标题                                      | String          | —                                                            | —                                                            |
| showHeader        | 显示表头                                      | Boolean         | —                                                            | true                                                         |
| tableProps        | el-table内部表格属性                          | Ref             | 一                                                           | {}                                                           |
| pageProps         | 分页属性                                      | Object          | 一                                                           | {  pageSizes:[10, 20, 30, 40],  layout: "total, sizes, prev, pager, next, jumper" } |
| page              | 分页器参数                                    | Object          | 一                                                           | {current:0,size:10}                                          |
| showFooter        | 底部工具条含分页器                            | Boolean         | 一                                                           | true                                                         |
| showSelectColumn  | 是否显示多选列                                | Boolean         | 一                                                           | false                                                        |
| headerProps       | 头部功能显示                                  | Object          | 一                                                           | {   visible: true,   leftVisible: true,   rightVisible: true } |
| loading           | 是否开启loading加载                           | Boolean         | 一                                                           | true                                                         |
| multiSort         | 表头多列排序，需启用后端排序sortable:'custom' | Boolean         | 一                                                           | false                                                        |
| request           | 表格数据请求函数                              | Function        | 一                                                           | 一                                                           |
| responseHandler   | 表格响应数据处理函数                          | Function        | 一                                                           | (data)=>{return data}                                        |
| beforeSearchTable | 请求参数预处理                                | Function        | 一                                                           | (formData={},isResetPage=true)=>{ }                          |
| lazyLoad          | 懒加载  页面初始化时加载数据                  | Boolean         | 一                                                           | false                                                        |
| propList          | 表格属性列                                    | Array(PropItem) | 一                                                           | 一                                                           |
| permissions       | 表格内按钮权限标识                            | Object          | {   create: 'xx:xx:add',   update: 'xx:xx:edit',   delete: 'xx:xx:delete' } | 一                                                           |

**PropItem**

| 参数                          | 说明           | 类型    | 可选值           | 默认值 |
| ----------------------------- | -------------- | ------- | ---------------- | ------ |
| prop                          | 属性字段       | String  | 一               | 一     |
| label                         | 属性描述       | String  | 一               | 一     |
| hidden                        | 是否隐藏       | Boolean | 一               | 一     |
| slotName                      | 自定义插槽名称 | 一      | 行数据 score.row | 一     |
| ...el-table-column-attributes | 原生表格列属性 | Object  | 一               | 一     |

**TableEvent**

| 事件名       | 说明         | 参数 |
| ------------ | ------------ | ---- |
| newBtnClick  | 新建按钮点击 | 一   |
| editBtnClick | 编辑按钮点击 | item |
| delBtnClick  | 删除按钮点击 | item |

**TableMethod**

| 方法名           | 说明                  | 参数                 |
| ---------------- | --------------------- | -------------------- |
| toggleExpandAll  | 树形菜单展开/折叠操作 | 一                   |
| getSelectionRows | 得到多选选中行数据    | []                   |
| searchTable      | 查询表格数据          | formData,isResetPage |
| getSearchForm    | 获取当前查询参数      | {}                   |
| refreshTable     | 刷新表格              | isResetPage          |
| resetTable       | 重置表格              | 一                   |

### 二、Form

> 表单组件

**Form Attributes**

| 参数       | 说明            | 类型            | 可选值 | 默认值 |
| ---------- | --------------- | --------------- | ------ | ------ |
| showHeader | 是否显示表单头  | Boolean         | 一     | false  |
| colLayout  | 列布局          | Object          | 一     | 一     |
| formProps  | el-form属性绑定 | Object          | 一     | 一     |
| formItems  | 表单项          | Array(FormItem) | 一     | []     |

**FormItem**

| 参数        | 说明                                                   | 类型     | 可选值                                                       | 默认值 |
| ----------- | ------------------------------------------------------ | -------- | ------------------------------------------------------------ | ------ |
| type        | 类型                                                   | String   | text, password, number ,select dict-select , dict-radio-group , tree-select, radio-group, datepicker，slot | 一     |
| label       | 标签                                                   | String   | 一                                                           | 一     |
| field       | 字段                                                   | String   | 一                                                           | 一     |
| isHidden    | 是否隐藏                                               | Boolean  | 一                                                           | 一     |
| rules       | 字段验证规则，同el-form-item                           | object   | 一                                                           | 一     |
| placeholder | 提示文字                                               | String   | 一                                                           | 一     |
| itemProps   | el-form-item属性绑定                                   | Object   | 一                                                           | 一     |
| config      | 配置，不同类型采取不同配置                             | Object   | 一                                                           | 一     |
| 一          | radio-group                                            |          |                                                              |        |
| remoteLoad  | 远程加载数据,当为true需要指定request false 指定options | Boolean  | 一                                                           | 一     |
| request     | 数据加载函数                                           | Function | 一                                                           | 一     |
| options     | 本地数据                                               | Array    | 一                                                           | 一     |
| otherProps  | el原生组件属性                                         | Object   | 一                                                           | 一     |
| 一          | select                                                 |          |                                                              |        |
| remoteLoad  | 远程加载数据,当为true需要指定request false 指定options | Boolean  | 一                                                           | 一     |
| request     | 数据加载函数                                           | Function | 一                                                           | 一     |
| options     | 本地数据                                               | Array    | 一                                                           | 一     |
| otherProps  | el原生组件属性                                         | Object   | 一                                                           | 一     |
| 一          | datepicker                                             |          |                                                              |        |
| type        | 显示类型，不同类型指定不同参数即可                     | String   | year/month/date/dates/months/years week/datetime/datetimerange/ daterange/monthrange | 一     |
| 一          | dict-select\|\|dict-radio-group                        |          |                                                              |        |
| dictCode    | 字典Code                                               | String   | 一                                                           | 一     |
| clearable   | 是否可清除                                             | Boolean  | 一                                                           | 一     |
| 一          | tree-select                                            |          |                                                              |        |
| clearable   | 是否可清除                                             | Boolean  | 一                                                           | 一     |
| multiple    | 多选                                                   | Boolean  | 一                                                           | 一     |
| normalizer  | 格式化器，指定显示文本规则                             | Function | 一                                                           | 一     |
| remoteLoad  | 远程加载数据,当为true需要指定request false 指定options | Boolean  | 一                                                           | 一     |
| request     | 数据加载函数                                           | Function | 一                                                           | 一     |
| options     | 本地数据                                               | Array    | 一                                                           | 一     |

**Form Method**

| 方法名       | 说明                     | 参数 |
| ------------ | ------------------------ | ---- |
| isCreateForm | 是否为创建表单(计算属性) | 一   |
| isUpdateForm | 是否为更新表单(计算属性) | 一   |

### 三、FormModal

> 基于表单属性上扩展

**Form Modal Attribute**

| 参数         | 说明                                                         | 类型     | 可选值 | 默认值                                                       |
| ------------ | ------------------------------------------------------------ | -------- | ------ | ------------------------------------------------------------ |
| appendToBody | Dialog 自身是否插入至 body 元素上。嵌套的 Dialog 必须指定该属性并赋值为 true | Boolean  | 一     | false                                                        |
| beforeSubmit | 请求提交前函数处理,可以阻断请求                              | Function | 一     | (payload)=>payload                                           |
| reqFunction  | 保存、更新函数                                               | Object   | 一     | {   create: (payload)=>Promise.resolve(),   update: (payload)=>Promise.resolve() } |

**Form Modal Method**

| 方法名 | 说明     | 参数                                        |
| ------ | -------- | ------------------------------------------- |
| add    | 添加表单 | payload ={title:'表单标题',item:'初始数据'} |
| update | 更新表单 | payload ={title:'表单标题',item:'初始数据'} |
| close  | 关闭表单 | 一                                          |

### 四、PageSearch

> 搜索组件 一>基于表单属性上扩展

**PageSearch Attribute**

| 参数 | 说明 | 类型 | 可选值 | 默认值 |
| ---- | ---- | ---- | ------ | ------ |
|      |      |      |        |        |

**PageSearch Method**

| 方法名           | 说明         | 参数        |
| ---------------- | ------------ | ----------- |
| handleResetClick | 重置按钮点击 | 一          |
| handleQueryClick | 查询按钮点击 | formData={} |


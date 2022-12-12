
// 表单行为类型，标识当前表单是用来新建的还是更新的
const FORM_ACTION = {
  NONE: 'none',
  CREATE: 'create',
  UPDATE: 'update'
}

export default {

  data(){
    return{
      formAction:FORM_ACTION.NONE,
    }
  },
  computed:{
    isCreateForm(){
      return this.formAction===FORM_ACTION.CREATE
    } ,
    isUpdateForm(){
      return this.formAction===FORM_ACTION.UPDATE
    } ,
  },
  methods:{
    /**
     * 构建创建表单
     * @param payload
     */
    buildCreateForm(payload){
      this.formAction=FORM_ACTION.CREATE
      this.createdFormCallback(payload);
    },
    /**
     * 构建创建表单成功后的回调
     * @param payload
     */
    createdFormCallback(payload){

    },
    /**
     * 构建更新表单数据
     * @param payload
     */
    buildUpdatedForm(payload){
      this.formAction=FORM_ACTION.UPDATE
      this.updatedFormCallback(payload);
    },

    updatedFormCallback(payload){

    },

  }
}

import {convertValueType} from '@/utils/index'
export default {
  install: (Vue,enumContext)=>{
    const enumPlugin={}
    //检查枚举是否存在
    const checkEnumExist=function(enumContext,constName){
      return !enumContext || !Object.prototype.hasOwnProperty.call(enumContext, constName)
    }

    /**
     * 根据枚举值得到描述
     * @param constName
     * @param value
     * @param defaultValue
     * @returns {string|{attrsGroups: [string], attrs: [string, string]}|isValidOrders.desc}
     */
    enumPlugin.getDescByValue=function (constName,value,valueType,defaultValue) {
       //1.检查枚举是否在上下文中存在
       if(checkEnumExist(enumContext,constName)){
         return ''
       }
      let currentEnum = enumContext[constName];
       let desc='';
      for (let item in currentEnum) {
        let enumValue = currentEnum[item].value;
        if (enumValue&&valueType){
          enumValue=convertValueType(enumValue,valueType)
        }
        if (enumValue === value) {
          desc=currentEnum[item].label;
          break
        }
      }
      if (!desc&&defaultValue){
          desc=defaultValue
      }
      return desc
    }
    /**
     * 得到枚举列表
     * @param constName
     * @param valueType 1 Number 2String 3Boolean
     * @returns  [ { "value": "male", "label": "男" }, { "value": "female", "label": "女" } ]
     */
    enumPlugin.getEnumList=function (constName,valueType) {
      //1.检查枚举是否在上下文中存在
      if(checkEnumExist(enumContext,constName)){
        return {}
      }
      let currentEnum = enumContext[constName];
      const result = [];
      for (let item in currentEnum) {
        let currentEnumElement = {...currentEnum[item]};
        if (currentEnumElement.value&&valueType){
          currentEnumElement.value=convertValueType(currentEnumElement.value,valueType)
        }
        result.push(currentEnumElement);
      }
      return result
    }
    /**
     * 得到枚举值键值对
     * @param constName
     * @returns { "male": "男", "female": "女" }
     */
    enumPlugin.getEnumPair=function (constName) {
      //1.检查枚举是否在上下文中存在
      if(checkEnumExist(enumContext,constName)){
        return {}
      }
      let currentEnum = enumContext[constName];
      let result = {};
      for (let item in currentEnum) {
        let key = currentEnum[item].value + '';
        result[key] = currentEnum[item].label;
      }
      return result
    }
    // 4. 添加实例方法
    Vue.prototype.$enums = enumPlugin
  }
}

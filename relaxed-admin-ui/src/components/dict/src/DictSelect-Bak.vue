<template>
    <div class="dict-select">
      <el-select  :value="selectedValue"
                  :clearable="clearable"
                  :placeholder="placeholder"
                  @change="handleSelectChange">
        <el-option
          v-for="item in this.options.dictItems"
          :key="item.value"
          :label="item.name"
          :value="item.value"
          :disabled="item.status===0">
        </el-option>
      </el-select>
    </div>
</template>

<script>



    export default {
      name: "DictSelect",
      props:{
        value:{
          required: true
        }  ,
        dictKey:{
          type:String,
          required: true
        },
        placeholder:{
          type:String,
          default:''
        },
        clearable:{
          type: Boolean,
          default: false
        }
      },
      data() {
        return {
          options: {},
          selectedValue: this.value
        }
      },
      watch: {
        value () {
          this.selectedValue = this.value
        }
      },

      created(){
          //初始化字典缓存
        this.getDictDataByKey(this.dictKey)
      },
      methods:{
          async getDictDataByKey(dictKey){
            const dictItem = await this.$store.dispatch('dict/getDictItem',dictKey)
            this.options=dictItem
          },
        handleSelectChange(selectValue){
          this.$emit('input',selectValue)
        }
      }
    }
</script>

<style scoped>

</style>

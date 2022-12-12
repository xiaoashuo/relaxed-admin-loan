import {mapGetters,mapActions} from 'vuex'

export default {
  name:'DictMixin',
  props:{
    dictCode: {
      type: String,
      required: true
    },
    itemFilter: {  // 用于过滤出指定的字典项
      type: Function,
      default: null
    },
    itemIsDisabled: { // 给字典项添加是否禁用的属性
      type: Function,
      default: (dictItem) => {
        // 根据字典项启用禁用设置是否禁用
        return dictItem.status !== 1
      }
    }
  },
  computed:{
    ...mapGetters('dict',['dictDataCache']),
    dictData(){
      let dictData= this.dictDataCache(this.dictCode)
      if (!dictData){
        this.fillDictCache([this.dictCode]).finally()
      }
      return dictData||{}
    },
    dictItems(){
      let dictItems=[]
      // 处理数据
      if (this.dictData && this.dictData.dictItems) {
        let originDictItems = this.dictData.dictItems

        for (let item of originDictItems){
          // 过滤字典项
          if (this.itemFilter && !this.itemFilter(item)) {
            continue
          }
          // 字典项是否 disable
          item.disabled = this.itemIsDisabled && this.itemIsDisabled(item)
          // 添加字典项
          dictItems.push(item)
        }
      }
      return dictItems
    }
  },
  created(){
    let dictData= this.dictDataCache(this.dictCode)
    if (!dictData){
      this.fillDictCache([this.dictCode]).finally()
    }
  },
  methods:{
    ...mapActions('dict',['fillDictCache']),
  }
}

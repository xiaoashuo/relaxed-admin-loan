import PageSearch from '@/components/page-search'
import ProTable from "@/components/table/src/ProTable";
import FormModal from "@/components/form/src/FormModal";
import ProFormModal from '@/components/form/src/ProFormModal'
import {DictRadioGroup,DictSelect,DictCheckBoxGroup,DictText,DictTag} from "@/components/dict";



export function registerComponents(Vue) {

  Vue.component('DictRadioGroup',DictRadioGroup)
  Vue.component('DictSelect',DictSelect)
  Vue.component('DictCheckBoxGroup',DictCheckBoxGroup)
  Vue.component('DictText',DictText)
  Vue.component('DictTag',DictTag)

  Vue.component('FormModal',FormModal)
  Vue.component('ProFormModal',ProFormModal)
  Vue.component('ProTable',ProTable)

  Vue.component('PageSearch',PageSearch)

}

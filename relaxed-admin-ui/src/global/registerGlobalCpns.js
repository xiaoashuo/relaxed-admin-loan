import PageSearch from '@/components/page-search'
import ProForm from '@/components/form/src/ProForm.vue'
import FormModal from "@/components/form/src/FormModal";
import ProFormModal from '@/components/form/src/ProFormModal'
import {DictRadioGroup,DictSelect,DictCheckBoxGroup,DictText,DictTag} from "@/components/dict";

import { YiTable,YiProTable } from '@/components/table'

export function registerComponents(Vue) {

  Vue.component('DictRadioGroup',DictRadioGroup)
  Vue.component('DictSelect',DictSelect)
  Vue.component('DictCheckBoxGroup',DictCheckBoxGroup)
  Vue.component('DictText',DictText)
  Vue.component('DictTag',DictTag)

  Vue.component('FormModal',FormModal)
  Vue.component('ProFormModal',ProFormModal)
  Vue.component('ProForm',ProForm)
  Vue.component('YiTable',YiTable)
  Vue.component('YiProTable',YiProTable)

  Vue.component('PageSearch',PageSearch)

}


import {registerFilter} from "@/global/registerFilter";
import {registerDirective} from "@/global/registerDirective";
import {registerComponents} from "@/global/registerGlobalCpns";
import {registerPlugin} from "@/global/registerPlugin";

export function globalRegister(vue) {
  registerFilter(vue)
  registerDirective(vue)
  registerComponents(vue)
  registerPlugin(vue)

}

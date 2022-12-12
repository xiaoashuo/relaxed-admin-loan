import store from "@/store";

export function usePermission(verifyPermission) {
   const permissions= store.getters.permissions
  //name='test'   !name =false !!name=true  强制转为boolean值
  return  !!permissions.find(item=>item===verifyPermission)
}
